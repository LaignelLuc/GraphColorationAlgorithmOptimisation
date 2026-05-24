package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.Color;
import info.iut.sae2.graphs.IEdge;
import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import info.iut.sae2.properties.ColorProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * FiveColoration Algorithm class for the coloration of graphs.
 *
 * @author llaignel
 */
public class FiveColorationPlanarAlgorithm extends ColorAlgorithm {

    /**
     * HashSet that receives the deleted nodes of the graph for optimization.
     */
    private HashSet<INode> maskedNodes = new HashSet<>();

    /**
     * Map that tracks the extra edges created by the fusion of nodes (they're virtual neighbors and do not exist in the original graph).
     */
    private HashMap<INode, HashSet<INode>> fusionEdges = new HashMap<>();

    /**
     * Copy of the treated graph in order to not modify the original graph.
     */
    private IGraph copy;

    /**
     * Calls the five coloration algorithm and returns the resulting color
     * property.
     *
     * @param g the treated graph
     * @param parameters map of parameters associating parameters names to
     * parameters values
     * @return result of the FiveColoration algorithm
     */
    @Override
    public ColorProperty apply(IGraph g, Map<String, Object> parameters) {
        hue = 0.0;
        colorUsed = new ArrayList<>();
        copy = g.copy();
        p = new ColorProperty();
        maskedNodes = new HashSet<>();
        fusionEdges = new HashMap<>();
        //We pre-generate 5 colors so we don't exceed the limit of colors used.
        for (int i = 0; i < 5; i++) {
            colorUsed.add(nextColor(hue));
            hue = (hue + GOLDEN_RATIO) % 1.0;
        }
        hue = 0.0;
        fiveColoration(getNodeToTreat(copy), g);
        return p;
    }

    /**
     * Computes the result of the five coloration algorithm for a given graph.
     *
     * @param nodeToTreat the first node we treat
     * @param ogGraph the original graph
     */
    private void fiveColoration(INode nodeToTreat, IGraph ogGraph) {

        //if the number of nodes in the graph is lower than 5, color naively
        //Base case
        if (copy.getNodes().size() <= 5) {
            for (INode n : copy.getNodes()) {
                if (p.getNodeValue(n) == null) {
                    if(fusionEdges.containsKey(n)){
                        assignMinimalColorFiltered(n, ogGraph);
                    }else{
                        assignMinimalColor(n, copy);
                    }
                }
            }
        }

        ArrayList<INode> nodesOfDegreeFour = new ArrayList<>();
        //We put all the degree 4 nodes in a list
        while (nodeToTreat != null
                && getNotDeletedNeighbors(nodeToTreat).size() <= 4
                && copy.getNodes().size() > 5) {

            copy.delNode(nodeToTreat); //We delete the degree 4 node from the copy of the graph 
            maskedNodes.add(nodeToTreat);
            nodesOfDegreeFour.add(nodeToTreat);
            nodeToTreat = getNodeToTreat(copy); //We get the next nodeToTreat
        }

        //If the removal of nodes of degree 4 drops the count of node to 5 or under we can color naïvely once again
        if (copy.getNodes().size() <= 5) {

            for (INode n : copy.getNodes()) {
                if(fusionEdges.containsKey(n)){
                    assignMinimalColorFiltered(n, ogGraph);
                }else{
                    assignMinimalColor(n, copy);
                }
            }

        } else { //Otherwise we treat the nodes of degree 5

            ArrayList<INode> nonAdjacentNodes = getNonAdjacentNeighbors(nodeToTreat, copy);
            INode u = nonAdjacentNodes.get(0);
            INode v = nonAdjacentNodes.get(1);

            //fusion of node v in u
            copy.delNode(nodeToTreat);
            maskedNodes.add(nodeToTreat);
            for (INode neighbor : getNotDeletedNeighbors(v)) {
                if (!neighbor.equals(u) && !copy.existEdge(u, neighbor, false)) {
                    copy.addEdge(u, neighbor);
                    if (!fusionEdges.containsKey(u)) {
                        fusionEdges.put(u, new HashSet<>());
                    }
                    fusionEdges.get(u).add(neighbor);

                    if (!fusionEdges.containsKey(neighbor)) {
                        fusionEdges.put(neighbor, new HashSet<>());
                    }
                    fusionEdges.get(neighbor).add(u);
                }
            }
            copy.delNode(v);
            maskedNodes.add(v);
            fiveColoration(getNodeToTreat(copy), ogGraph);
            if (!ogGraph.getNeighbors(u).contains(v)) {
                p.setNodeValue(v, p.getNodeValue(u));
            } else {
                assignMinimalColorFiltered(v, ogGraph);
            }
            assignMinimalColorFiltered(nodeToTreat, ogGraph);

        }

        //we color all nodes of degree four
        for (int i = nodesOfDegreeFour.size() - 1; i >= 0; i--) {
            assignMinimalColorFiltered(nodesOfDegreeFour.get(i), ogGraph);
        }
    }

    /**
     * Gets the next node to treat in the graph.
     *
     * @param g the graph to treat
     * @return the next node to treat
     */
    private INode getNodeToTreat(IGraph g) {
        INode result = null;
        int cpt = 0;
        while (cpt < g.numberOfNodes() && result == null) {
            INode currentNode = g.getNodes().get(cpt);
            if (g.getNeighbors(currentNode).size() <= 5 && !maskedNodes.contains(currentNode)) {
                result = currentNode;
            }
            cpt++;
        }
        return result;
    }

    /**
     * Gets the first two non adjacent Neighbors from a node.
     *
     * @param nodeToTreat the node to treat
     * @param g the graph
     * @return the two non adjacent Neighbors
     */
    private ArrayList<INode> getNonAdjacentNeighbors(INode nodeToTreat, IGraph g) {
        ArrayList<INode> res = new ArrayList<>();
        ArrayList<INode> neighbors = getNotDeletedNeighbors(nodeToTreat);
        boolean found = false;
        int i = 0;

        while (!found && i < neighbors.size()) {
            INode u = neighbors.get(i);
            int j = i + 1;

            while (!found && j < neighbors.size()) {
                INode v = neighbors.get(j);

                if (!g.getNeighbors(u).contains(v)) {
                    res.add(u);
                    res.add(v);
                    found = true;
                }
                j++;
            }
            i++;
        }
        return res;
    }

    /**
     * Gets the neighbors list from a node without the deleted nodes from the
     * graph (optimization).
     *
     * @param n the treated node.
     * @return the list of neighbors
     */
    private ArrayList<INode> getNotDeletedNeighbors(INode n) {
        ArrayList<IEdge> inOutEdges = copy.getInOutEdges(n);
        ArrayList<INode> neighbors = new ArrayList<>();
        int i = 0;

        while (i < inOutEdges.size()) {
            IEdge e = inOutEdges.get(i);
            INode other = n.equals(e.source()) ? e.target() : e.source();

            if (!maskedNodes.contains(other)) {
                neighbors.add(other);
            }
            i++;
        }
        return neighbors;
    }

    /**
     * Assigns the smallest available color to a node, considering both its
     * original neighbors and and the neighbors resulting of the fusion of nodes.
     *
     * @param node the node to color
     * @param ogGraph the original graph for neighbor lookup
     */
    private void assignMinimalColorFiltered(INode node, IGraph ogGraph) {
        HashSet<Color> neighborColors = new HashSet<>();

        for (INode neighbor : ogGraph.getNeighbors(node)) {
            Color c = p.getNodeValue(neighbor);
            if (c != null) {
                neighborColors.add(c);
            }
        }

        //Also account for virtual neighbors created during fusion
        //if the neighbor has no neighbors then we give it an empty set as default value.
        for (INode neighbor : (fusionEdges.get(node) == null ? new HashSet<INode>() : fusionEdges.get(node))) {
            Color c = p.getNodeValue(neighbor);
            if (c != null) {
                neighborColors.add(c);
            }
        }

        int i = 0;
        boolean found = false;
        while (i < colorUsed.size() && !found) {
            Color c = colorUsed.get(i);
            if (!neighborColors.contains(c)) {
                p.setNodeValue(node, c);
                found = true;
            }
            i++;
        }
    }

    /**
     * This Algorithm does not use a deletion order.
     *
     * @param g graph treated
     * @return nothing in this case.
     */
    @Override
    protected ArrayList<INode> buildNodeColorationOrder(IGraph g) {
        throw new UnsupportedOperationException("This algorithm does not use this function.");
    }
}
