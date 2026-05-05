/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jpmelisse
 */

public class WelshAndPowell implements Algorithm{

    @Override
    public Object apply(IGraph g, Map parameters) {
        
        // Here we reorder the nodes according to its degree
        // Then we color in the order of highest degree first
        
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Method for rearrange nodes from highest degree to lowest degree
    public static ArrayList<INode> getOrder(IGraph g){
        
        // First we get the degrees for all node
        HashMap <INode, Integer> degreesNodes = getDegree(g);
        
        // NewOrder
        ArrayList<INode> newOrder = new ArrayList<>();
        for (INode node : degreesNodes.keySet()){
            newOrder.add(node);
        }
        //System.out.println(newOrder);
        
        // Then we change order
        AlgoTri.getDecreasingOrder(g, newOrder);  
        return newOrder;
    }
    
    // Method to get degrees for all nodes
    public static HashMap<INode, Integer> getDegree(IGraph g){
        
        HashMap <INode, Integer> newOrder = new HashMap<>();
        
        for (INode n : g.getNodes()){
            int degree = g.degree(n);
            newOrder.put(n, degree);
        }
        return newOrder;
    }
    
}