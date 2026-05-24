/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import info.iut.sae2.properties.ColorProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Welsh and Powell algorithm class for the coloration of graphs.
 * @author jpmelisse
 */
public class WelshAndPowell extends ColorAlgorithm {

    /**
     * Compute the result of the algorithm and returns it.
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
     * Method for rearrange nodes from highest degree to lowest degree.
     *
     * @param g the graph
     * @return An arrayList of nodes ordered by their degree
     */
    @Override
    protected ArrayList<INode> buildNodeColorationOrder(IGraph g) {
        // First we get the degrees for all node
        //HashMap <INode, Integer> degreesNodes = getDegree(g);

        // NewOrder
        ArrayList<INode> newOrder = g.getNodes();

        //System.out.println(newOrder);
        // Then we change order
        // Selection Sort
        //AlgoSorting.getDecreasingOrder(g, newOrder);  
        // Merge Sort
        //AlgoSorting.mergeSort(g, newOrder);
        // Java Sort
        AlgoSorting.getDecreasingOrderJavaSort(g, newOrder);

        return newOrder;
    }

    /**
     * Method to get degrees for all nodes from a graph.
     *
     * @param g the graph
     * @return a map of nodes with their degree
     */
    public static HashMap<INode, Integer> getDegree(IGraph g) {

        HashMap<INode, Integer> newOrder = new HashMap<>();

        for (INode n : g.getNodes()) {
            int degree = g.degree(n);
            newOrder.put(n, degree);
        }
        return newOrder;
    }

    /**
     * Method to get degrees from an ordered ArrayList of nodes.
     *
     * @param g a graph
     * @param nodes An arrayList of ordered nodes
     * @return An arrayList of degrees
     */
    public static ArrayList<Integer> getDegree(IGraph g,
            ArrayList<INode> nodes) {
        ArrayList<Integer> newOrder = new ArrayList<>();

        for (INode n : nodes) {
            int degree = g.degree(n);
            newOrder.add(degree);
        }
        return newOrder;
    }
}
