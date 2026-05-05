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
public class AlgoTri implements Algorithm {

    @Override
    public Object apply(IGraph g, Map parameters) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Take an ArrayList of Integer and return it to 
    // a decreasing order
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
                nodes.set(indexMax, nodes.get(indexI));
            }
        }
        
    }
    
}
