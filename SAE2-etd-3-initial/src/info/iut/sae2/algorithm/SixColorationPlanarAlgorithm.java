package info.iut.sae2.algorithm;
import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import info.iut.sae2.properties.ColorProperty;
import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author llaignel
 */
public class SixColorationPlanarAlgorithm extends ColorAlgorithm {
    
    private double hue = 0.0;
    
    public SixColorationPlanarAlgorithm(){
        
    }
    
     /**
     * Compute the result of the Six Coloration algorithm and returns it.
     * @param g the graph on which the algorithm is applied
     * @param parameters map of parameters associating parameters names to parameters values
     * @return the result of the algorithm
     */
    @Override
    public ColorProperty apply(IGraph g, Map<String, Object> parameters){
        ArrayList<INode> order = buildNodeColorationOrder(g);
        return firstFit(order, g);
    }
        
    @Override
    protected ArrayList<INode> buildNodeColorationOrder(IGraph g) {
        IGraph copy = g.copy();
        ArrayList<INode> delOrder = new ArrayList<>();

        while (!copy.getNodes().isEmpty()) {
            int cpt = 0;
            boolean found = false;
            while (cpt < copy.getNodes().size() && !found) {
                INode node = copy.getNodes().get(cpt);
                if (copy.getNeighbors(node).size() <= 5) {
                    delOrder.add(node);
                    copy.delNode(node);
                    found = true;
                } else {
                    cpt++;
                }
            }
        }
        java.util.Collections.reverse(delOrder);
        return delOrder;
    }    
}
