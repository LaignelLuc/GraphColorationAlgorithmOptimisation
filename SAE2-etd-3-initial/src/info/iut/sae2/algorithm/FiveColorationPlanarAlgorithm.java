package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.Color;
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

    /**
     * HashSet of deleted nodes to reduce complexity.
     */
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
        fiveColoration(getFirstNodeToTreat(copy), g);
        return p;
    }

    /**
     * Computes the result of the five coloration algorithm for a given graph.
     *
     * @param nodeToTreat
     * @param ogGraph
     */
    private void fiveColoration(INode nodeToTreat, IGraph ogGraph) {

        if (copy.getNodes().size() <= 5) {
            WelshAndPowell WPColorAlgo = new WelshAndPowell();
            p = WPColorAlgo.applyToExistingColorProperty(copy, p);
            return;
        }

        ArrayList<INode> nodesOfDegreeFour = new ArrayList<>();

        while (nodeToTreat != null
                && getEffectiveOrder(nodeToTreat).size() <= 4
                && copy.getNodes().size() > 5) {

            copy.delNode(nodeToTreat);
            maskedNodes.add(nodeToTreat);
            nodesOfDegreeFour.add(nodeToTreat);
            nodeToTreat = getFirstNodeToTreat(copy);
        }

        if (copy.getNodes().size() <= 5) {

            WelshAndPowell WPColorAlgo = new WelshAndPowell();
            p = WPColorAlgo.applyToExistingColorProperty(copy, p);
                
        } else {

            ArrayList<INode> nonAdjacentNodes = getNonAdjacentNeighbors(nodeToTreat, copy);
            INode u = nonAdjacentNodes.get(0);
            INode v = nonAdjacentNodes.get(1);

            copy.delNode(nodeToTreat);
            maskedNodes.add(nodeToTreat);
            for (INode neighbor : new ArrayList<>(getEffectiveOrder(v))) {
                if (!neighbor.equals(u) && !copy.existEdge(u, neighbor, false)) {
                    copy.addEdge(u, neighbor);
                }
            }
            copy.delNode(v);
            maskedNodes.add(v);
            fiveColoration(getFirstNodeToTreat(copy), ogGraph);
            p.setNodeValue(v, p.getNodeValue(u));
            assignMinimalColor(nodeToTreat, ogGraph);

        }

        for (int i = nodesOfDegreeFour.size() - 1; i >= 0; i--) {
            assignMinimalColor(nodesOfDegreeFour.get(i), ogGraph);
        }
    }

    private INode getFirstNodeToTreat(IGraph g) {
        for (int cpt = 0; cpt < g.numberOfNodes(); cpt++) {
            INode currentNode = g.getNodes().get(cpt);
            if (g.getNeighbors(currentNode).size() <= 5
                    && !maskedNodes.contains(currentNode)) {
                return currentNode;
            }
        }
        return null;
    }

    private ArrayList<INode> getNonAdjacentNeighbors(INode nodeToTreat, IGraph g) {
        ArrayList<INode> res = new ArrayList<>();
        ArrayList<INode> neighbors = getEffectiveOrder(nodeToTreat);
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

    private ArrayList<INode> getEffectiveOrder(INode n) {
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

    @Override
    protected ArrayList<INode> buildNodeColorationOrder(IGraph g) {
        throw new UnsupportedOperationException("This algorithm does not use this function.");
    }
}
