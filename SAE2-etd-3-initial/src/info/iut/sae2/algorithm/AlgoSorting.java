/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author jpmelisse
 */
public class AlgoSorting implements Algorithm {

    @Override
    public Object apply(IGraph g, Map parameters) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Take an ArrayList of Integer and return it to 
    // a decreasing order (Variant of selection sort)
    public static void getDecreasingOrder(IGraph g, ArrayList<INode> nodes){
        
        int indexI, indexMax, maxLocal;
        // We loop in the arrayList
        for (int i = 0; i < nodes.size()-1; i++) {
            
            // Initiation of values
            indexI = i;
            maxLocal = g.degree(nodes.get(i));
            indexMax = -1;
            
            // We look again the arrayList from index indexI+1
            for (int j = indexI+1; j < nodes.size(); j++) {
                
                if (g.degree(nodes.get(j)) > maxLocal) {
                    maxLocal = g.degree(nodes.get(j));
                    indexMax = j;
                }
            }
            // In the end, we exchange i and maxLocal
            if (indexMax != -1) {
                INode n = nodes.get(indexMax);
                nodes.set(indexMax, nodes.get(indexI));
                nodes.set(indexI, n);         
            }
        }
        
    }
    
    // Merge sort algorithm (from s1 Cycle 5)
    
    /**
     * Merge sort, restricted to an interval.
     *
     * @param g
     * @param nodes
     * @param first
     * @param last
     */
    public static void mergeSortPartial(IGraph g, ArrayList<INode> nodes, 
                                        int first, int last) {

        if (last > first) {
            int milieu = (first + last) / 2;

            mergeSortPartial(g, nodes, first, milieu);
            mergeSortPartial(g, nodes, milieu + 1, last);
            fusion(g, nodes, first, milieu, last);
        }
    }

    /**
     * Merging action of two sorted intervals.
     *
     * @param g
     * @param nodes
     * @param first
     * @param middle
     * @param last
     */
    public static void fusion(IGraph g, ArrayList<INode> nodes,
                          int first, int middle, int last) {

        ArrayList<INode> tmp = new ArrayList<>(nodes);

        int posGauche = first;
        int posDroite = middle + 1;
        int posTmp = first;

        // Merging of 2 sorted parts
        while (posGauche <= middle && posDroite <= last) {

            // Decreasing order
            if (g.degree(nodes.get(posGauche)) >=
                g.degree(nodes.get(posDroite))) {

                tmp.set(posTmp, nodes.get(posGauche));
                posGauche++;

            } 
            else {

                tmp.set(posTmp, nodes.get(posDroite));
                posDroite++;
            }

            posTmp++;
        }

        // Left part to add
        while (posGauche <= middle) {
            tmp.set(posTmp, nodes.get(posGauche));
            posGauche++;
            posTmp++;
        }

        // Right part to add
        while (posDroite <= last) {
            tmp.set(posTmp, nodes.get(posDroite));
            posDroite++;
            posTmp++;
        }

        // Recopy in nodes
        for (int i = first; i <= last; i++) {
            nodes.set(i, tmp.get(i));
        }
    }
    

    /**
     * Merge Sort.
     *
     * @param g
     * @param nodes
     */
    public static void mergeSort(IGraph g, ArrayList<INode> nodes) {
       mergeSortPartial(g, nodes, 0, nodes.size() - 1);
    }   
        
    
    // Java Sort Collection
    public static void getDecreasingOrderJavaSort(IGraph g, 
                                                  ArrayList<INode> nodes) {

        nodes.sort((n1, n2) ->
                   Integer.compare(g.degree(n2), g.degree(n1))
                    );
    }
    
}
