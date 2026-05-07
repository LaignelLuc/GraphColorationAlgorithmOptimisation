/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.algorithm;
import info.iut.sae2.graphs.Color;
import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import info.iut.sae2.properties.ColorProperty;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author llaignel
 */
abstract class ColorAlgorithm implements Algorithm<ColorProperty>{
    
    /**
     * Compute the result of the algorithm and returns it.
     * @param g the graph on which the algorithm is applied
     * @param parameters map of parameters associating parameters names to parameters values
     * @return the result of the algorithm
     */
    @Override
    public abstract ColorProperty apply(IGraph g, Map<String, Object> parameters);
    
    public static final double GOLDEN_RATIO = 0.618033988749895; //0.618 to stay in the HSV wheel values

    /**
     * Returns the next distinct color using golden ratio hue stepping.
     *
     * @param saturation
     * @param value    
     * @param alpha     
     */
    public static Color nextColor(float saturation, float value, int alpha, double hue) {
        return hsvToColor((float)hue, saturation, value, alpha);
    }

    /**
     * Overload of nextColor with default values. 
     */
    public static Color nextColor(double hue) {
        return nextColor(0.75f, 0.95f, 255, hue);
    }

    /**
     * Conversion of an HSV wheel color to an RGBA colour.
     */
    public static Color hsvToColor(float hue, float saturation, float value, int alpha) {
        int sectorIndex = (int) (hue * 6); // sector index
        float sectorOffset = hue * 6 - sectorIndex;
        float p = value * (1 - saturation); // the darkest value of this sector of the wheel
        float q = value * (1 - sectorOffset * saturation); // dimming value
        float t = value * (1 - (1 - sectorOffset) * saturation); // brightening value

        float r, g, b;
        switch (sectorIndex % 6) {
            case 0 -> { r = value; g = t; b = p; }
            case 1 -> { r = q; g = value; b = p; }
            case 2 -> { r = p; g = value; b = t; }
            case 3 -> { r = p; g = q; b = value; }
            case 4 -> { r = t; g = p; b = value; }
            default -> { r = value; g = p; b = q; }
        }

        return new Color(Math.round(r * 255), Math.round(g * 255), Math.round(b * 255), alpha);
    }
    
    public static Color getMinimalColor(ArrayList<Color> colorUsed, IGraph treatedGraph, INode treatedNode, ColorProperty colorProperty){
        boolean[] isColorUsedByNeighbour = new boolean[colorUsed.size()];
            
        for(INode neighbour : treatedGraph.getNeighbors(treatedNode)){
            if(colorUsed.contains(colorProperty.getNodeValue(neighbour))){
                isColorUsedByNeighbour[colorUsed.indexOf(colorProperty.getNodeValue(neighbour))] = true;
            }
        }
        
        boolean usableColorFound = false;
        int cpt = 0;
        while(!usableColorFound && cpt < isColorUsedByNeighbour.length){
            if(!isColorUsedByNeighbour[cpt]){
                usableColorFound = true;
            }else{
                cpt++;
            }
        }
        
        if(usableColorFound){
            return colorUsed.get(cpt);
        }else{
            return null;
        }
    }
    
    public ColorProperty firstFit(ArrayList<INode> nodeOrder, IGraph g) {
        ColorProperty res = new ColorProperty();
        ArrayList<Color> colorUsed = new ArrayList<>();
        double hue = 0.0;

        Color firstColor = hsvToColor((float)hue, 1.0f, 1.0f, 255);
        colorUsed.add(firstColor);
        res.setNodeValue(nodeOrder.get(0), firstColor);

        for (int i = 1; i < nodeOrder.size(); i++) {
            INode currentNode = nodeOrder.get(i);
            Color minimalColor = getMinimalColor(colorUsed, g, currentNode, res);
            if (minimalColor == null) {
                hue = (hue + GOLDEN_RATIO) % 1.0;
                Color newColor = nextColor(hue);
                colorUsed.add(newColor);
                res.setNodeValue(currentNode, newColor);
            } else {
                res.setNodeValue(currentNode, minimalColor);
            }
        }
        return res;
    }
    
    protected abstract ArrayList<INode> buildNodeColorationOrder(IGraph g);
    
}
