/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package info.iut.sae2.graphs;

import java.util.ArrayList;

/**
 *
 * @author rbourqui
 */
public interface INode {
    ArrayList<INode> getNeighbors();
    ArrayList<INode> getSuccessors();
    ArrayList<INode> getPredecessors();
}
