package info.iut.sae2.algorithm;
import info.iut.sae2.graphs.Color;
import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import info.iut.sae2.properties.ColorProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author llaignel
 */
public class SixColoration extends ColorAlgorithm {
    
    
    public SixColoration(){
        
    }
    
     /**
     * Compute the result of the Six Coloration algorithm and returns it.
     * @param g the graph on which the algorithm is applied
     * @param parameters map of parameters associating parameters names to parameters values
     * @return the result of the algorithm
     */
    @Override
    public ColorProperty apply(IGraph g, Map<String, Object> parameters){
        ColorProperty res = new ColorProperty();
        IGraph copy = g.copy(); 
        ArrayList<INode> delOrder = new ArrayList<>(); //Order in which the nodes are deleted
        
        while(!copy.getNodes().isEmpty()){  //while the graph is not empty
            
            int cpt = 0;
            boolean loop = true;
            
            while(cpt < copy.getNodes().size() && loop){  //check every node
                INode node = copy.getNodes().get(cpt); //the target node in the loop
                int degreeOfNode = copy.getNeighbors(node).size();
                if(degreeOfNode <= 5){  //the first node we get with a degree below 5
                    delOrder.add(node);
                    copy.delNode(node); //remove the node
                    loop=false;
                }else{
                    cpt++;
                }
            } 
        }
        
        Color currentColor = new Color();
        Map<Color,Integer> colorHierarchy;
        
        for(int i = delOrder.size()-1 ; i>=0; i--){
            if
        }
    }
    
    
    
}
