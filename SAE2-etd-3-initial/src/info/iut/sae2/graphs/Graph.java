/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.graphs;

import info.iut.sae2.algorithm.SixColoration;
import info.iut.sae2.properties.ColorProperty;
import info.iut.sae2.properties.LayoutProperty;
import info.iut.sae2.properties.SizeProperty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author llaignel
 */
public class Graph implements IGraph {

    /**
     * Set of the edges contained by the graph.
     */
    private ArrayList<IEdge> edges;

    /**
     * Set of the nodes contained by the graph.
     */
    private ArrayList<INode> nodes;
    
    private SizeProperty sizes;
    
    private ColorProperty colors;
    
    private LayoutProperty layout;

    /**
     * Constructor for graph class.
     */
    public Graph() {
        edges = new ArrayList<>();
        nodes = new ArrayList<>();
        sizes = new SizeProperty();
        colors = new ColorProperty();
        layout = new LayoutProperty();
    }

    /**
     * Instanciates an IGraph. This method is used to create a new empty graph
     * of the same specific type as the target object
     *
     * @return the new IGraph
     */
    @Override
    public IGraph createGraph() {
        return new Graph();
    }

    /**
     * Instanciates an IGraph. This method is used to create a new copy graph of
     * the same specific type as the target object
     *
     * @return the new copy
     */
    @Override
    public IGraph copy() {
        Graph copy = this;
        return copy;
    }

    /**
     * Adds a new empty node to the graph and returns it.
     *
     * @return the added node
     */
    @Override
    public INode addNode() {
        INode newNode = new Node();
        nodes.add(newNode);
        return newNode;
    }

    /**
     * Adds a given node to the graph and returns it.
     *
     * @param n the node to add in the graph
     * @return the added node
     */
    @Override
    public INode addNode(INode n) {
        nodes.add(n);
        return n;
    }

    /**
     * Adds a given edge and returns an edge to the graph and returns it.
     *
     * @param e the edge to add
     * @return the added edge
     */
    @Override
    public IEdge addEdge(IEdge e) {
        edges.add(e);
        return e;
    }

    /**
     * Adds an edge between the two given nodes and returns it.
     *
     * @param src source of the edge to add
     * @param tgt target of the edge to add
     * @return the added edge
     */
    @Override
    public IEdge addEdge(INode src, INode tgt) {
        IEdge newEdge = new Edge(src, tgt);
        edges.add(newEdge);
        return newEdge;
    }

    /**
     * Deletes a given node from the graph
     *
     * @param n the node to be deleted
     */
    @Override
    public void delNode(INode n) {
        nodes.remove(n);
        colors.delNode(n);
        layout.delNode(n);
        sizes.delNode(n);
        for(IEdge edge : this.getInOutEdges(n)){
            delEdge(edge);
        }
    }

    /**
     * Deletes a given edge from the graph
     *
     * @param e the edge to be deleted
     */
    @Override
    public void delEdge(IEdge e) {
        edges.remove(e);
        colors.delEdge(e);
        layout.delEdge(e);
        sizes.delEdge(e);
    }

    /**
     * Returns the number of nodes in the graph
     *
     * @return the number of nodes
     */
    @Override
    public int numberOfNodes() {
        return nodes.size();
    }

    /**
     * Returns the number of edges in the graph
     *
     * @return the number of edges
     */
    @Override
    public int numberOfEdges() {
        return edges.size();
    }

    /**
     * Returns the neighbors (succesors and predecessors) of a given node in the
     * graph
     *
     * @param n the node whose neighborhood is queried
     * @return the neighbors of the node
     */
    @Override
    public ArrayList<INode> getNeighbors(INode n) {
        // The neighbors of a node come from the in-going
        // and out-going of the node.
        ArrayList<IEdge> inOutEdges = getInOutEdges(n);
        
        if (inOutEdges.isEmpty()) {
            return new ArrayList<>();
        } else {
            ArrayList<INode> neighbors = new ArrayList<>();
            // We look from inOutEgdes and look if the node n is
            // present in source or target. Then we take the other
            // end of the e
            for (IEdge e : inOutEdges){
                if (n.equals(e.source())){
                    // n is in the source, we take the target
                    INode node = e.target();
                    neighbors.add(node);
                }
                else {
                    // n is in the target, we take the source
                    INode node = e.source();
                    neighbors.add(node);
                }
            }
            return neighbors;
        }
    }

    /**
     * Returns the sucessors of a given node in the graph
     *
     * @param n the node whose succesors are queried
     * @return the succesors of the node
     */
    @Override
    public ArrayList<INode> getSuccesors(INode n) {
        // The succesors of a node come from the out-going of
        // the node.
        ArrayList<IEdge> outEdges = getOutEdges(n);
        
        if (outEdges.isEmpty()) {
            return new ArrayList<>();
        } else {
            ArrayList<INode> succesors = new ArrayList<>();
            // We look from outEgdes and get all targets
            for (IEdge e : outEdges){
                INode node = e.target();
                succesors.add(node);
            }
            return succesors;
        }
    }

    /**
     * Returns the predecessors of a given node in the graph
     *
     * @param n the node whose predecessors are queried
     * @return the predecessors of the node
     */
    @Override
    public ArrayList<INode> getPredecessors(INode n) {
        // The predecessors of a node come from the in-going of
        // the node.
        ArrayList<IEdge> inEdges = getInEdges(n);
        
        if (inEdges.isEmpty()) {
            return new ArrayList<>();
        } else {
            ArrayList<INode> predecessors = new ArrayList<>();
            // We look from inEgdes and get all sources
            for (IEdge e : inEdges){
                INode node = e.source();
                predecessors.add(node);
            }
            return predecessors;
        }
    }

    /**
     * Returns the indicent edges of a given node
     *
     * @param n the node whose incident edges are queried
     * @return the incident edges of the node
     */
    @Override
    public ArrayList<IEdge> getInOutEdges(INode n) {
        ArrayList<IEdge> InOutEdgesOfN = new ArrayList<>();
        for (IEdge edge : edges) {
            if (source(edge) == n || target(edge) == n) {
                InOutEdgesOfN.add(edge);
            }
        }
        return InOutEdgesOfN;
    }

    /**
     * Returns the in-going edges of a given node in the graph
     *
     * @param n the node whose in-going are queried
     * @return the in-going edges of the node
     */
    @Override
    public ArrayList<IEdge> getInEdges(INode n) {
        ArrayList<IEdge> InEdgesOfN = new ArrayList<>();
        for (IEdge edge : edges) {
            if (target(edge) == n) {
                InEdgesOfN.add(edge);
            }
        }
        return InEdgesOfN;
    }

    /**
     * Returns the out-going edges of a given node in the graph
     *
     * @param n the node whose out-going are queried
     * @return the out-going edges of the node
     */
    @Override
    public ArrayList<IEdge> getOutEdges(INode n) {
        ArrayList<IEdge> OutEdgesOfN = new ArrayList<>();
        for (IEdge edge : edges) {
            if (source(edge) == n) {
                OutEdgesOfN.add(edge);
            }
        }
        return OutEdgesOfN;
    }

    /**
     * Returns the nodes of the graph
     *
     * @return the nodes of the graph
     */
    @Override
    public ArrayList<INode> getNodes() {
        return nodes;
    }

    /**
     * Returns the edges of the graph
     *
     * @return the edges of the graph
     */
    @Override
    public ArrayList<IEdge> getEdges() {
        return edges;
    }

    /**
     * Returns the source node of a given edge in the graph
     *
     * @param e the edge whose source is queried
     * @return the source of the edge
     */
    @Override
    public INode source(IEdge e) {
        return e.source();
    }

    /**
     * Returns the target node of a given edge in the graph
     *
     * @param e the edge whose target is queried
     * @return the target of the edge
     */
    @Override
    public INode target(IEdge e) {
        return e.target();
    }

    /**
     * Returns the in-degree (number of predecessors) of a given node in the
     * graph
     *
     * @param n the node whose in-degree is queried
     * @return the in-degree of the node
     */
    @Override
    public int inDegree(INode n) {
        return getPredecessors(n).size();
    }

    /**
     * Returns the out-degree (number of succesors) of a given node in the graph
     *
     * @param n the node whose out-degree is queried
     * @return the out-degree of the node
     */
    @Override
    public int outDegree(INode n) {
        return getSuccesors(n).size();
    }

    /**
     * Returns the degree (number of neighbors) of a given node in the graph
     *
     * @param n the node whose degree is queried
     * @return the degree of the node
     */
    @Override
    public int degree(INode n) {
        return getNeighbors(n).size();
    }

    /**
     * Returns true if there exists an edge between the two given nodes,
     * considering the orientation of the edges or not.
     *
     * @param src the source node of the putative edge
     * @param tgt the target node of the putative edge
     * @param oriented if true, the function consider the edge as directed
     * (arc), and undirected otherwise
     * @return true if the edge exists, false otherwise
     */
    @Override
    public boolean existEdge(INode src, INode tgt, boolean oriented) {
        boolean res = false;
        int i = 0;
        while (i < edges.size() && !res) { //for every edge in edges
            if (!oriented) {    //if its not oriented
                if (edges.get(i).source() == src && edges.get(i).target() == tgt    
                        || edges.get(i).source() == tgt && edges.get(i).target() == src) {  //we check if we can find the nodes in either positions
                    res = true;
                }
            } else {    //if its oriented
                if (edges.get(i).source() == src && edges.get(i).target() == tgt) { //we check if we find the nodes in the correct positions
                    res = true;
                }
            }
            i++;
        }
        return res;
    }

    /**
     * Returns an existing edge between the source and target nodes while
     * considering the orientation of not.
     *
     * @param src the source node of the putative edge
     * @param tgt the target node of the putative edge
     * @param oriented if true, the function consider the edge as directed
     * (arc), and undirected otherwise
     * @return the edge if the edge exists, null otherwise
     */
    @Override
    public IEdge getEdge(INode src, INode tgt, boolean oriented) {
        boolean res = false;
        IEdge wantedEdge = null;
        int i = 0;
        while (i < edges.size() && !res) { //for every edge in edges
            if (!oriented) {    //if its not oriented
                if (edges.get(i).source() == src && edges.get(i).target() == tgt    
                        || edges.get(i).source() == tgt && edges.get(i).target() == src) {  //we check if we can find the nodes in either positions
                    res = true;
                    wantedEdge = edges.get(i);
                }
            } else {    //if its oriented
                if (edges.get(i).source() == src && edges.get(i).target() == tgt) { //we check if we find the nodes in the correct positions
                    res = true;
                    wantedEdge = edges.get(i);
                }
            }
            i++;
        }
        return wantedEdge;
    }

    /**
     * Returns the SizeProperty associated to the IGraph.
     *
     * @return The SizeProperty of the IGraph
     */
    @Override
    public SizeProperty getSizes() {
        return sizes;
    }

    /**
     * Returns the size of a given node in the graph
     *
     * @param n the node of interest
     * @return the size of the node
     */
    @Override
    public Size getNodeSize(INode n) {
        Size s = sizes.getNodeValue(n);
        return s != null ? s : SizeProperty.DEFAULT_NODE_SIZE;
    }

    /**
     * Returns the width of a given edge in the graph
     *
     * @param e the edge of interest
     * @return the width of the edge
     */
    @Override
    public Double getEdgeWidth(IEdge e) {
        Double d = sizes.getEdgeValue(e);
        return d != null ? d : SizeProperty.DEFAULT_EDGE_WIDTH;
    }

    /**
     * Set the size of a given node in the graph
     *
     * @param n the node of interest
     * @param s new size of the node
     */
    @Override
    public void setNodeSize(INode n, Size s) {
        sizes.setNodeValue(n, s);
    }

    /**
     * Set the width of a given edge in the graph
     *
     * @param e the edge of interest
     * @param width new width of the edge
     */
    @Override
    public void setEdgeWidth(IEdge e, Double width) {
        sizes.setEdgeValue(e, width);
    }

    /**
     * Set the size of all nodes in the graph
     *
     * @param s new size of the nodes
     */
    @Override
    public void setAllNodesSizes(Size s) {
        for(INode node : nodes){
            sizes.setNodeValue(node, s);
        }
    }

    /**
     * Set the width of all edges in the graph
     *
     * @param width new width of the edges
     */
    @Override
    public void setAllEdgesWidths(Double width) {
        for (IEdge e : edges){
            sizes.setEdgeValue(e, width);
        }
    }

    /**
     * Returns the LayoutProperty associated to the IGraph.
     *
     * @return The LayoutProperty of the IGraph
     */
    @Override
    public LayoutProperty getLayout() {
        return layout;
    }

    /**
     * Returns the coordinates of a given node in the graph
     *
     * @param n the node of interest
     * @return the coordinates of the node
     */
    @Override
    public Coord getNodePosition(INode n) {
        Coord coord =  layout.getNodeValue(n);
        return coord != null ? coord : LayoutProperty.DEFAULT_NODE_POS;
    }

    /**
     * Returns the coordinates of a given edge (ie the coordinates of its bends)
     * in the graph
     *
     * @param e the edge of interest
     * @return the coordinates of the bends of the edge
     */
    @Override
    public ArrayList<Coord> getEdgePosition(IEdge e) {
        ArrayList<Coord> edgeCoord = layout.getEdgeValue(e);
        return edgeCoord != null ? edgeCoord : LayoutProperty.DEFAULT_EDGE_POS;
    }

    /**
     * Set the coordinates of a given node in the graph
     *
     * @param n the node of interest
     * @param c new coordinates of the node
     */
    @Override
    public void setNodePosition(INode n, Coord c) {
        layout.setNodeValue(n, c);
    }

    /**
     * Set the coordinates of a given edge (ie the coordinates of its bends) in
     * the graph
     *
     * @param e the edge of interest
     * @param bends the bends of the edge
     */
    @Override
    public void setEdgePosition(IEdge e, ArrayList<Coord> bends) {
        layout.setEdgeValue(e, bends);
    }

    /**
     * Set the coordinates of all nodes in the graph
     *
     * @param c new coordinates of the nodes
     */
    @Override
    public void setAllNodesPositions(Coord c) {
        for (INode node : nodes){
            layout.setNodeValue(node, c);
        }
    }

    /**
     * Set the coordinates of all edges (ie the coordinates of its bends) in the
     * graph
     *
     * @param bends the bends of the edges
     */
    @Override
    public void setAllEdgesPositions(ArrayList<Coord> bends) {
        for (IEdge e : edges){
            layout.setEdgeValue(e, bends);
        }
    }

    /**
     * Returns the coordinates of the bouding box of the graph drawing. The
     * bouding box of a drawing of a graph is the minimum rectangle such that
     * all nodes and edges of the graph are inside the rectangle. Please remind
     * that the y-axis is oriented toward the bottom.
     *
     * @return an ArrayList containing the coordinates of the top-left corner of
     * the bonding box (at the first index in the ArrayList) and the coordinates
     * of the bottom-right corner of the bounding box (second index)
     *
     */
    @Override
    public ArrayList<Coord> getBoundingBox() {
        ArrayList<Coord> boundingBox = new ArrayList<>();
        Coord topLeft = new Coord();
        Coord bottomRight = new Coord();
        for(INode node : nodes){
            Coord nodeCoord = getNodePosition(node);
            Size nodeSize = getNodeSize(node);
            Double leftBorderGap = nodeCoord.x-nodeSize.getW()/2;
            Double topBorderGap = nodeCoord.y-nodeSize.getH()/2;
            Double rightBorderGap = nodeCoord.x+nodeSize.getW()/2;
            Double bottomBorderGap = nodeCoord.y+nodeSize.getH()/2;
            if (leftBorderGap < topLeft.x){
                topLeft.x = leftBorderGap;
            }
            if (topBorderGap < topLeft.y){
                topLeft.y = topBorderGap;
            }
            if (rightBorderGap > bottomRight.x){
                bottomRight.x = rightBorderGap;
            }
            if (bottomBorderGap > bottomRight.y){
                bottomRight.y = bottomBorderGap;
            }            
        }
        
        // Get border values
        boundingBox.add(topLeft);
        boundingBox.add(bottomRight);
        
        return boundingBox;
    }

    /**
     * Returns the ColorProperty associated to the IGraph.
     *
     * @return The ColorProperty of the IGraph
     */
    @Override
    public ColorProperty getColor() {
        return colors;
    }

    /**
     * Returns the color of a given node in the graph.
     *
     * @param n the node of interest
     * @return the color of the node
     */
    @Override
    public Color getNodeColor(INode n) {
        Color c = colors.getNodeValue(n);
        return c != null ? c : ColorProperty.DEFAULT_NODE_COL;
    }

    /**
     * Returns the color of a given edge in the graph.
     *
     * @param e the edge of interest
     * @return the color of the edge
     */
    @Override
    public Color getEdgeColor(IEdge e) {
        Color c = colors.getEdgeValue(e);
        return c != null ? c : ColorProperty.DEFAULT_EDGE_COL;
    }

    /**
     * Set the color of a given node in the graph.
     *
     * @param n the node of interest
     * @param c new color of the node
     */
    @Override
    public void setNodeColor(INode n, Color c) {
        colors.setNodeValue(n, c);        
    }

    /**
     * Set the color of a given edge in the graph.
     *
     * @param e the edge of interest
     * @param c the bends of the edge
     */
    @Override
    public void setEdgeColor(IEdge e, Color c) {
        colors.setEdgeValue(e, c);
    }

    /**
     * Set the color of all nodes in the graph.
     *
     * @param c the new color of the nodes
     */
    @Override
    public void setAllNodesColors(Color c) {
        for (INode node : nodes){
            colors.setNodeValue(node, c);
        }
    }

    /**
     * Set the color of all edges in the graph.
     *
     * @param c the new color of the edges
     */
    @Override
    public void setAllEdgesColors(Color c) {
        for (IEdge e : edges){
            colors.setEdgeValue(e, c);
        }
    }

    /**
     * Color the nodes with Well & Powell algorithm.
     */
    @Override
    public void welshAndPowell() {
    }

    /**
     * Color the nodes with a 6 colors algorithm (only for planar graphs)
     */
    @Override
    public void sixColors() {
//        SixColoration sixColorAlgo = new SixColoration();
//        sixColorAlgo.apply(this, );
    }

    /**
     * Color the nodes with a 5 colors algorithm (only for planar graphs)
     */
    @Override
    public void fiveColors() {
    }
}
