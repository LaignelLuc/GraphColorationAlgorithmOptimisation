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
    Color getColor();
    void setColor(Color c);
    Coord getCoord();
    void setCoord(Coord c);
    Size getSize();
    void setSize(Size s);
    ArrayList<INode> getNeighbors();
    ArrayList<INode> getSuccessors();
    ArrayList<INode> getPredecessors();
}
