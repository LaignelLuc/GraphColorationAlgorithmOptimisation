/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.graphs;

/**
 *
 * @author llaignel
 */
public class Edge implements IEdge{
    
    private Node sourceNode;
    private Node targetNode;
    
    public Edge(Node aSourceNode, Node aTargetNode){
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
