/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.graphs;

import java.util.ArrayList;

/**
 *
 * @author llaignel
 */
public class Node implements INode{
    
    private Color nodeColour;
    private Coord[] nodeCoordinates;
    private Size nodeSize;
    
    public Node(){
        this.nodeCoordinates = new Coord[2];
        
    }
    
    public Node(Color aColor, Coord[] someCoords, Size aSize){
        this.nodeCoordinates = someCoords;
        nodeColour = aColor;
        nodeCoordinates = someCoords;
        nodeSize = aSize;
    }
    
}
