package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.IEdge;
import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import info.iut.sae2.properties.ColorProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

/**
 * FiveColoration Algorithm class.
 *
 * @author llaignel
 */
public class FiveColorationPlanarAlgorithm extends ColorAlgorithm {

    
    private HashSet<INode> maskedNodes = new HashSet<>();

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
        colorUsed.add(hsvToColor((float) hue, 1.0f, 1.0f, 255));
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
        
        // if the number of nodes in the graph is lower than 5 apply WelshAndPowell
        // Base case
        if (copy.getNodes().size() <= 5) {
            WelshAndPowell WPColorAlgo = new WelshAndPowell();
            p = WPColorAlgo.applyToExistingColorProperty(copy, p);
            return;
        }
        
        ArrayList<INode> nodesOfDegreeFour = new ArrayList<>();
        //We put all the degree 4 nodes in a list
        while (nodeToTreat != null
                && getNotDeletedNeighbors(nodeToTreat).size() <= 4
                && copy.getNodes().size() > 5) {

            copy.delNode(nodeToTreat); // We delete the degree 4 node from the copy of the graph 
            maskedNodes.add(nodeToTreat);
            nodesOfDegreeFour.add(nodeToTreat);
            nodeToTreat = getNodeToTreat(copy); //We get the next nodeToTreat
        }
        
        //If the removal of nodes of degree 4 drops the count of node to 5 or under we can apply WelshAndPowell
        if (copy.getNodes().size() <= 5) {

            WelshAndPowell WPColorAlgo = new WelshAndPowell();
            p = WPColorAlgo.applyToExistingColorProperty(copy, p);
                
        } else { //Otherwise we treat the nodes of degree 5

            ArrayList<INode> nonAdjacentNodes = getNonAdjacentNeighbors(nodeToTreat, copy);
            INode u = nonAdjacentNodes.get(0);
            INode v = nonAdjacentNodes.get(1);
            
            //fusion of node v in u
            copy.delNode(nodeToTreat);
            maskedNodes.add(nodeToTreat);
            for (INode neighbor : new ArrayList<>(getNotDeletedNeighbors(v))) {
                if (!neighbor.equals(u) && !copy.existEdge(u, neighbor, false)) {
                    copy.addEdge(u, neighbor);
                }
            }
            copy.delNode(v);
            maskedNodes.add(v);
            fiveColoration(getNodeToTreat(copy), ogGraph);
            p.setNodeValue(v, p.getNodeValue(u));
            assignMinimalColor(nodeToTreat, ogGraph);

        }
        
        //we color all nodes of degree four
        for (int i = nodesOfDegreeFour.size() - 1; i >= 0; i--) {
            assignMinimalColor(nodesOfDegreeFour.get(i), ogGraph);
        }
    }
    
    /**
     * Gets the next node to treat in the graph.
     * @param g the graph to treat
     * @return the next node to treat
     */
    private INode getNodeToTreat(IGraph g) {
        for (int cpt = 0; cpt < g.numberOfNodes(); cpt++) {
            INode currentNode = g.getNodes().get(cpt);
            if (g.getNeighbors(currentNode).size() <= 5
                    && !maskedNodes.contains(currentNode)) {
                return currentNode;
            }
        }
        return null;
    }
    
    /**
     * Gets the first two non adjacent Neighbors from a node.
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
     * Gets the neighbors list from a node without the deleted nodes from the graph (optimization).
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
     * This Algorithm does not use a deletion order.
     * @param g graph treated
     * @return nothing in this case.
     */
    @Override
    protected ArrayList<INode> buildNodeColorationOrder(IGraph g) {
        throw new UnsupportedOperationException("This algorithm does not use this function.");
    }
}
