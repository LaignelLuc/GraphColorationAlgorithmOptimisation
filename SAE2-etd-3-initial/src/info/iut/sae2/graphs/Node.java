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
    
    private Color nodeColour;
    private Coord nodeCoordinate;
    private Size nodeSize;
    private ArrayList<INode> neighbors;
    private ArrayList<INode> succesors;
    private ArrayList<INode> predecessors;
    
    public Node(){
        nodeCoordinate = LayoutProperty.DEFAULT_NODE_POS;
        nodeColour = ColorProperty.DEFAULT_NODE_COL;
        nodeSize = SizeProperty.DEFAULT_NODE_SIZE;
        neighbors = new ArrayList<>();
        predecessors = new ArrayList<>();
        succesors = new ArrayList<>();
    }
    
    public Node(Color aColor, Coord aCoord, Size aSize, ArrayList<INode> listOfSuccesors, ArrayList<INode> listOfPredecessors){
        nodeCoordinate = aCoord;
        nodeColour = aColor;
        nodeCoordinate = aCoord;
        nodeSize = aSize;
        neighbors = new ArrayList<>();
        succesors = listOfSuccesors;
        predecessors = listOfPredecessors;
        neighbors.addAll(listOfSuccesors);
        neighbors.addAll(listOfPredecessors);
    }
    
    @Override
    public Color getColor(){
        return nodeColour;
    }
    
    @Override
    public void setColor(Color c){
        nodeColour = c;
    }
    
    @Override
    public Coord getCoord(){
        return nodeCoordinate;
    }
    
    @Override
    public void setCoord(Coord c){
        nodeCoordinate = c;
    }
    
    @Override
    public Size getSize(){
        return nodeSize;
    }
    
    @Override
    public void setSize(Size s){
        nodeSize = s;
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
