/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.graphs;

import java.util.ArrayList;
import info.iut.sae2.properties.SizeProperty;
import info.iut.sae2.properties.ColorProperty;
import info.iut.sae2.properties.LayoutProperty;
/**
 *
 * @author llaignel
 */
public class Node implements INode{
    
   
    private ArrayList<INode> neighbors;
    private ArrayList<INode> succesors;
    private ArrayList<INode> predecessors;
    
    public Node(){
        
        neighbors = new ArrayList<>();
        predecessors = new ArrayList<>();
        succesors = new ArrayList<>();
    }
    
    public Node(ArrayList<INode> listOfSuccesors, ArrayList<INode> listOfPredecessors){
        
        neighbors = new ArrayList<>();
        succesors = listOfSuccesors;
        predecessors = listOfPredecessors;
        neighbors.addAll(listOfSuccesors);
        neighbors.addAll(listOfPredecessors);
    }
    
    
    @Override
    public ArrayList<INode> getNeighbors(){
        return neighbors;
    }
    
    @Override
    public ArrayList<INode> getSuccessors(){
        return succesors;
    }
    
    public ArrayList<INode> getPredecessors(){
        return predecessors;
    }
    
}
