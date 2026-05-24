/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.graphs;

/**
 * Implementation of the IEdge abstract class to build edges in a graph.
 * @author llaignel
 */
public class Edge implements IEdge{
    /**
     * Source node of the edge.
     */
    private INode sourceNode;
    
    /**
     * Target node of the edge.
     */
    private INode targetNode;
    
    
    /**
     * Constructor for the edge class.
     * @param aSourceNode the source node of the edge.
     * @param aTargetNode the target node of the edge.
     */
    public Edge(INode aSourceNode, INode aTargetNode){
        this.sourceNode = aSourceNode;
        this.targetNode = aTargetNode;
        
    }
    
    /**
     * Getter that returns the source node.
     * @return the source node of the edge.
     */
    @Override
    public INode source(){
        return sourceNode;
    }
    
    /**
     * Getter that returns the target node of the edge.
     * @return 
     */
    @Override
    public INode target(){
        return targetNode;
    }
    
}   
