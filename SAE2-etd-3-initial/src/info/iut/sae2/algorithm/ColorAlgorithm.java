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
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author llaignel
 */
abstract class ColorAlgorithm implements Algorithm<ColorProperty> {

    /**
     * Compute the result of the algorithm and returns it.
     *
     * @param g the graph on which the algorithm is applied
     * @param parameters map of parameters associating parameters names to
     * parameters values
     * @return the result of the algorithm
     */
    @Override
    public abstract ColorProperty apply(IGraph g, Map<String, Object> parameters);

    protected static final double GOLDEN_RATIO = 0.618033988749895; //0.618 to stay in the HSV wheel values
    protected double hue = 0.0; // starting hue value (red)
    protected ArrayList<Color> colorUsed = new ArrayList<>(); // the list of color used in the graph
    protected ColorProperty p = new ColorProperty(); // the color property that is gonna be applied to the graph

    /**
     * Returns the next distinct color using golden ratio hue stepping.
     *
     * @param saturation
     * @param value
     * @param alpha
     */
    public static Color nextColor(float saturation, float value, int alpha, double hue) {
        return hsvToColor((float) hue, saturation, value, alpha);
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
            case 0 -> {
                r = value;
                g = t;
                b = p;
            }
            case 1 -> {
                r = q;
                g = value;
                b = p;
            }
            case 2 -> {
                r = p;
                g = value;
                b = t;
            }
            case 3 -> {
                r = p;
                g = q;
                b = value;
            }
            case 4 -> {
                r = t;
                g = p;
                b = value;
            }
            default -> {
                r = value;
                g = p;
                b = q;
            }
        }

        return new Color(Math.round(r * 255), Math.round(g * 255), Math.round(b * 255), alpha);
    }

    /**
     * Returns the minimal color possible for a given Node.
     *
     * @param colorUsed array of all the color used in the graph
     * @param treatedGraph the treated graph
     * @param treatedNode the treated node
     * @param colorProperty the color property used for the graph
     * @return the minimal color for the node or null if not found
     */
    public static Color getMinimalColor(ArrayList<Color> colorUsed, IGraph treatedGraph, INode treatedNode, ColorProperty colorProperty) {
        //HashSet that contains the color used by the neighbors
        HashSet<Color> isColorUsedByNeighbour = new HashSet<>();
        for (INode neighbour : treatedGraph.getNeighbors(treatedNode)) {
            isColorUsedByNeighbour.add(colorProperty.getNodeValue(neighbour));
        }

        Color foundColor = null;
        boolean colorFound = false;
        int i = 0;
        //We take the first color that is not in the HashSet
        while (!colorFound && i < colorUsed.size()) {
            Color currentColor = colorUsed.get(i);

            if (!isColorUsedByNeighbour.contains(currentColor)) {
                foundColor = currentColor;
                colorFound = true;
            }

            i++;
        }

        return foundColor;
    }

    /**
     * First fit algorithm used for WelshAndPowell and 6-coloration algorithm.
     *
     * @param nodeOrder order in which the nodes are colored
     * @param g the graph
     * @return the updated Color Property
     */
    public ColorProperty firstFit(ArrayList<INode> nodeOrder, IGraph g) {

        this.colorUsed = new ArrayList<>();
        this.hue = 0.0;
        this.p = p;

        Color firstColor = hsvToColor((float) hue, 1.0f, 1.0f, 255);
        colorUsed.add(firstColor);
        p.setNodeValue(nodeOrder.get(0), firstColor);

        for (int i = 1; i < nodeOrder.size(); i++) {
            INode currentNode = nodeOrder.get(i);
            Color minimalColor = getMinimalColor(colorUsed, g, currentNode, this.p);
            if (minimalColor == null) {
                hue = (hue + GOLDEN_RATIO) % 1.0;
                Color newColor = nextColor(hue);
                colorUsed.add(newColor);
                p.setNodeValue(currentNode, newColor);
            } else {
                p.setNodeValue(currentNode, minimalColor);
            }
        }
        return p;
    }

    /**
     * firstFit Overload to modify an existing ColorProperty.
     *
     * @param nodeOrder nodeOrder
     * @param g graph
     * @param p color property
     * @return modified color property.
     */
    public ColorProperty firstFit(ArrayList<INode> nodeOrder, IGraph g, ColorProperty p) {
        this.colorUsed = new ArrayList<>();
        this.hue = 0.0;
        this.p = new ColorProperty();

        //We put the base color for the first node in the color property
        Color firstColor = hsvToColor((float) hue, 1.0f, 1.0f, 255);
        colorUsed.add(firstColor);
        this.p.setNodeValue(nodeOrder.get(0), firstColor);

        //for all the other nodes of the node order, we assign the lowest color
        for (int i = 1; i < nodeOrder.size(); i++) {
            INode currentNode = nodeOrder.get(i);
            assignMinimalColor(currentNode, g);
        }
        return this.p;
    }

    /**
     * Assigns the minimal color possible to a node, and creates a new color if
     * needed.
     *
     * @param node the treated node
     * @param g the graph used
     */
    public void assignMinimalColor(INode node, IGraph g) {
        Color minimalCol = getMinimalColor(colorUsed, g, node, p);
        if (minimalCol == null) {
            hue = (hue + GOLDEN_RATIO) % 1.0;
            Color newColor = nextColor(hue);
            colorUsed.add(newColor);
            p.setNodeValue(node, newColor);
        } else {
            p.setNodeValue(node, minimalCol);
        }
    }

    /**
     * Builds the order in which the nodes are colored.
     *
     * @param g the graph
     * @return an ArrayList containing the order.
     */
    protected abstract ArrayList<INode> buildNodeColorationOrder(IGraph g);

}
