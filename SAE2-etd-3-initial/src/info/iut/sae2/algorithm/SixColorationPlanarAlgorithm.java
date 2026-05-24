package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import info.iut.sae2.properties.ColorProperty;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * SixColoration Algorithm class for the coloration of graphs.
 *
 * @author llaignel
 */
public class SixColorationPlanarAlgorithm extends ColorAlgorithm {

    /**
     * Compute the result of the Six Coloration algorithm and returns it.
     *
     * @param g the graph on which the algorithm is applied
     * @param parameters map of parameters associating parameters names to
     * parameters values
     * @return the result of the algorithm
     */
    @Override
    public ColorProperty apply(IGraph g, Map<String, Object> parameters) {
        ArrayList<INode> order = buildNodeColorationOrder(g);
        return firstFit(order, g);
    }


    /**
     * Creates the coloration order for the SixColorationAlgorithm.
     *
     * @param g the treated graph
     * @return the coloration order for the nodes
     */
    @Override
    protected ArrayList<INode> buildNodeColorationOrder(IGraph g) {
        IGraph copy = g.copy();
        ArrayList<INode> delOrder = new ArrayList<>();
        
        HashMap<INode, Integer> degreesOfNodes = new HashMap<>(); //This HashMap contains the degrees if each node we treat
        Deque<INode> nodesOfDegreeFive = new ArrayDeque<>();
        
        //We first build the stack of nodes that have a degree lower or equal to 5
        for (INode node : copy.getNodes()) {
            int deg = copy.getNeighbors(node).size();
            degreesOfNodes.put(node, deg);
            if (deg <= 5){
                nodesOfDegreeFive.push(node);
            }
        }
        
        while (!nodesOfDegreeFive.isEmpty()) { 
            INode node = nodesOfDegreeFive.pop();
            if (copy.getNodes().contains(node)){ //If we didn't already delete the node 
                delOrder.add(node); //add the node into the order 
                for (INode neighbor : copy.getNeighbors(node)) { //for each neighbors of the node
                    int newDegree = degreesOfNodes.get(neighbor) - 1; //their new degree is going to be one lower because 
                    //we deleted one of its neighbors(the one at the top of the stack)
                    degreesOfNodes.put(neighbor, newDegree);
                    if (newDegree == 5){ //if the neighbor has a new degree of 5 or lower he is elligible to be put into the treatment stack
                        nodesOfDegreeFive.push(neighbor);
                    }
                }
                copy.delNode(node); //finally we actually delete the node from the copy of the graph
            }
        }

        Collections.reverse(delOrder); //since for 6-coloration we treat nodes in the reverse order of their deletion
        return delOrder;
    }
}
