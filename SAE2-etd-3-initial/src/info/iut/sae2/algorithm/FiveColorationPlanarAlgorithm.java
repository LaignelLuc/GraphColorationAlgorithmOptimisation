/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author llaignel
 */
public class FiveColorationPlanarAlgorithm extends ColorAlgorithm{
    
    private double hue = 0.0;
    private ArrayList<Color> colorUsed = new ArrayList<>();
    
    /**
     * Compute the result of the Six Coloration algorithm and returns it.
     * @param g the graph on which the algorithm is applied 
     * @param parameters map of parameters associating parameters names to parameters values
     * @return the result of the algorithm
     */
    @Override
    public ColorProperty apply(IGraph g, Map<String, Object> parameters){
        hue = 0.0;
        colorUsed = new ArrayList<>();
        IGraph copy = g.copy();
        ColorProperty p = new ColorProperty();
        colorUsed.add(hsvToColor((float)hue,1.0f, 1.0f, 255));
        return fiveColoration(copy, p, getFirstNodeToTreat(g));
    }
    
    private ColorProperty fiveColoration(IGraph copy, ColorProperty p, INode nodeToTreat) {
        if (copy.getNodes().size() <= 5) {
            WelshAndPowell WPColorAlgo = new WelshAndPowell();
            return WPColorAlgo.applyToExistingColorProperty(copy, p);
        }

        int nodeToTreatOrder = copy.getNeighbors(nodeToTreat).size();
        Color colToApply;

        if (nodeToTreatOrder <= 4) {
            IGraph snapshot = copy.copy();
            copy.delNode(nodeToTreat);

            p = fiveColoration(copy, p, getFirstNodeToTreat(copy));

            Color minimalCol = getMinimalColor(colorUsed, snapshot, nodeToTreat, p);
            if (minimalCol == null) {
                hue = (hue + GOLDEN_RATIO) % 1.0;
                colToApply = nextColor(hue);
                colorUsed.add(colToApply);
            } else {
                colToApply = minimalCol;
            }
            p.setNodeValue(nodeToTreat, colToApply);
            return p;

        } else { // order == 5
            ArrayList<INode> nonAdjacentNodes = getNonAdjacentNeighbors(nodeToTreat, copy);
            INode u = nonAdjacentNodes.get(0);
            INode v = nonAdjacentNodes.get(1);

            IGraph snapshot = copy.copy(); 
            copy.delNode(nodeToTreat);

            for (INode neighbor : new ArrayList<>(copy.getNeighbors(v))) {
                if (!neighbor.equals(u) && !copy.existEdge(u, neighbor, false)) {
                    copy.addEdge(u, neighbor);
                }
            }
            copy.delNode(v);

            p = fiveColoration(copy, p, u);
            p.setNodeValue(v, p.getNodeValue(u));

            Color minimalCol = getMinimalColor(colorUsed, snapshot, nodeToTreat, p);
            if (minimalCol == null) {
                hue = (hue + GOLDEN_RATIO) % 1.0;
                colToApply = nextColor(hue);
                colorUsed.add(colToApply);
            } else {
                colToApply = minimalCol;
            }
            p.setNodeValue(nodeToTreat, colToApply);
            return p;
        }
    }
    
    private INode getFirstNodeToTreat(IGraph g){
        boolean nodeFound = false;
        int cpt = 0;
        INode nodeToTreat = null;
        while(!nodeFound && cpt < g.numberOfNodes()){
            INode currentNode = g.getNodes().get(cpt);
            if(g.getNeighbors(currentNode).size()<=5){
                nodeToTreat = currentNode;
                nodeFound = true;
            }else{
                cpt++;
            }
        }
        return nodeToTreat;
    }
    
    private ArrayList<INode> getNonAdjacentNeighbors(INode nodeToTreat, IGraph g){
        ArrayList<INode> res = new ArrayList<>();
        boolean found = false;
        int i = 0;
        int j = 0;
        while(!found && i< g.getNeighbors(nodeToTreat).size()){
            INode u = g.getNeighbors(nodeToTreat).get(i);
            j=i+1;
            while(!found && j<g.getNeighbors(nodeToTreat).size()){
                INode v = g.getNeighbors(nodeToTreat).get(j);
                if(!g.getNeighbors(u).contains(v)){
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
    
    @Override
    protected ArrayList<INode> buildNodeColorationOrder(IGraph g){
        throw new UnsupportedOperationException("This algorithm does not use this function.");
    }
    
}
