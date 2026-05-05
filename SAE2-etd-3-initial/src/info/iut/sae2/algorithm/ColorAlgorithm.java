/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.algorithm;
import info.iut.sae2.graphs.Color;
import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.properties.ColorProperty;
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
    public ColorProperty apply(IGraph g, Map<String, Object> parameters){
        return null;
    }
    
    private static final double GOLDEN_RATIO = 0.618033988749895; //0.618 to stay in the HSV wheel values
    private static double HUE = 0.0;

    /**
     * Returns the next distinct color using golden ratio hue stepping.
     *
     * @param saturation
     * @param value    
     * @param alpha     
     */
    public static Color nextColor(float saturation, float value, int alpha) {
        HUE = (HUE + GOLDEN_RATIO) % 1.0;
        return hsvToColor((float) HUE, saturation, value, alpha);
    }

    /**
     * Overload of nextColor with default values. 
     */
    public static Color nextColor() {
        return nextColor(0.75f, 0.95f, 255);
    }

    /**
     * Conversion of an HSV wheel color to an RGBA colour.
     */
    private static Color hsvToColor(float hue, float saturation, float value, int alpha) {
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
    
}
