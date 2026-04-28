/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.graphs;
import info.iut.sae2.properties.SizeProperty;
import info.iut.sae2.properties.ColorProperty;
import info.iut.sae2.properties.LayoutProperty;
import java.util.ArrayList;
/**
 *
 * @author llaignel
 */
public class Edge implements IEdge{
    
    private INode sourceNode;
    private INode targetNode;
    private Double edgeWidth;
    private Color edgeColor;
    private ArrayList<Coord> edgeCoordinates;
    
    public Edge(INode aSourceNode, INode aTargetNode, Double anEdgeWidth, Color anEdgeColor, ArrayList<Coord> someCoordinates ){
        this.sourceNode = aSourceNode;
        this.targetNode = aTargetNode;
        this.edgeWidth = anEdgeWidth;
        edgeColor = anEdgeColor;
        edgeCoordinates = someCoordinates;
    }
    
    public Edge(INode aSourceNode, INode aTargetNode){
        this.sourceNode = aSourceNode;
        this.targetNode = aTargetNode;
        this.edgeWidth = SizeProperty.DEFAULT_EDGE_WIDTH;
        edgeColor = ColorProperty.DEFAULT_EDGE_COL;
        edgeCoordinates = LayoutProperty.DEFAULT_EDGE_POS;
    }
    
    @Override
    public INode source(){
        return sourceNode;
    }
    
    @Override
    public INode target(){
        return targetNode;
    }
    
    @Override
    public Color getColor() { return edgeColor; }

    @Override
    public void setColor(Color c) { this.edgeColor = c; }

    @Override
    public Double getWidth() { return edgeWidth; }

    @Override
    public void setWidth(Double width) { this.edgeWidth = width; }
    
    @Override
    public ArrayList<Coord> getCoord(){
        return edgeCoordinates;
    }
    
    @Override
    public void setCoord(ArrayList<Coord> bends){
        edgeCoordinates = bends;
    }
    
}   
