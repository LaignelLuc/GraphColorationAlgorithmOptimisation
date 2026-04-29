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
    
    
    
    public Edge(INode aSourceNode, INode aTargetNode){
        this.sourceNode = aSourceNode;
        this.targetNode = aTargetNode;
        
    }
    
    @Override
    public INode source(){
        return sourceNode;
    }
    
    @Override
    public INode target(){
        return targetNode;
    }
    
}   
