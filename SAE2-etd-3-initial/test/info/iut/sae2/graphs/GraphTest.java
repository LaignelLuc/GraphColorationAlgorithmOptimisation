/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.graphs;

import info.iut.sae2.properties.ColorProperty;
import info.iut.sae2.properties.LayoutProperty;
import info.iut.sae2.properties.SizeProperty;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author jpmelisse
 * 
 * Some Unit Test for the class Graph 
 * 
 */
public class GraphTest {
    
    // Test number Of Nodes
    @Test
    public void testNumberOfNodes(){
        String nodeFileName = "dataset/simple_nodes.csv";
        String edgeFileName = "dataset/simple_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        // Check number of nodes
        Assert.assertEquals(12, g.numberOfNodes());
    }
    
    // Test number of Edges
    @Test
    public void testNumberOfEdges(){
        String nodeFileName = "dataset/simple_nodes.csv";
        String edgeFileName = "dataset/simple_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        // Check number of Edges
        Assert.assertEquals(16, g.numberOfEdges());
    }
    
    // Test some functions on nodes
    @Test
    public void testBasicFunctionsOnNodes(){
        // New Graph
        IGraph g = new Graph();
        // First nodes is empty
        Assert.assertEquals(0, g.numberOfNodes());
        // Add first node
        INode n1 = new Node();
        g.addNode(n1);
        // First node added
        Assert.assertEquals(1, g.numberOfNodes());
        // Add 3 nodes
        INode n2 = new Node();
        INode n3 = new Node();
        INode n4 = new Node();
        // Add them to graph
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        // 4 nodes in graph
        Assert.assertEquals(4, g.numberOfNodes());
        // We also add some characteristics for the nodes
        g.setAllNodesColors(ColorProperty.DEFAULT_NODE_COL);
        g.setAllNodesPositions(LayoutProperty.DEFAULT_NODE_POS);
        g.setAllNodesSizes(SizeProperty.DEFAULT_NODE_SIZE);
        // Remove 2 nodes
        g.delNode(n1);
        g.delNode(n4);
        // 2 nodes left
        Assert.assertEquals(2, g.numberOfNodes());
    }
    
    // Test some functions on edges
    @Test
    public void testBasicFuntionsOnEdges(){
        // New Graph
        IGraph g = new Graph();
        // Add 3 nodes
        INode n1 = new Node();
        INode n2 = new Node();
        INode n3 = new Node();
        // Add them to graph
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        // First edges is empty
        Assert.assertEquals(0, g.numberOfEdges());
        // Add 1 edge btw n1 and n3 (can be directed or not)
        // n1 is source and n3 is target
        IEdge e1 = new Edge(n1,n3);
        // We also create the reversed arc n3-> n1
        IEdge e2 = new Edge(n3,n1);
        //Add e1 to graph
        g.addEdge(e1);
        // First edge added
        Assert.assertEquals(1, g.numberOfEdges());
        // Verify that only e1 is present
        IEdge testEdge = g.getEdge(n1, n3, true);
        Assert.assertTrue(e1.equals(testEdge));
        Assert.assertFalse(e1.equals(e2));
        // Test if non-directed case
        IEdge testEdge2 = g.getEdge(n3, n1, false);
        // Test if e1 == testEdge2 since non-directed
        Assert.assertTrue(e1.equals(testEdge2));
        // testEdge2 is not e2 since not presented in graph
        Assert.assertFalse(e2.equals(testEdge2));
        // Add another edge
        IEdge e3 = new Edge(n2,n1);
        g.addEdge(e3);
        // 2 edges in graph
        Assert.assertEquals(2, g.numberOfEdges());
        // Add some characteristics to edges
        g.setAllEdgesColors(ColorProperty.DEFAULT_EDGE_COL);
        g.setAllEdgesPositions(LayoutProperty.DEFAULT_EDGE_POS);
        g.setAllEdgesWidths(SizeProperty.DEFAULT_EDGE_WIDTH);
        // Remove 1 edge
        g.delEdge(e1);
        // 1 edge left
        Assert.assertEquals(1, g.numberOfEdges());
    }
    
    // Test some functions about the edges of a node
    @Test
    public void testGetNodeEdges(){
        // New graph
        String nodeFileName = "dataset/simple_nodes.csv";
        String edgeFileName = "dataset/simple_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        // node 0
        INode n0 = g.getNodes().get(0);
        // Retrieve all out-going Edges from n0
        ArrayList<IEdge> outEdges = g.getOutEdges(n0);
        // n0 has 2 edges out
        Assert.assertEquals(2, outEdges.size());
        // node 3
        INode n3 = g.getNodes().get(3);
        //System.out.println(g.getNodePosition(n3));
        // Retrieve all in-going and out-going Edges
        outEdges = g.getOutEdges(n3);
        ArrayList<IEdge> inEdges = g.getInEdges(n3);
        ArrayList<IEdge> inOutEdges = g.getInOutEdges(n3);
        // n3 has 2 edges in and 1 edge out
        Assert.assertEquals(2, inEdges.size());
        Assert.assertEquals(1, outEdges.size());
        Assert.assertEquals(3, inOutEdges.size());
    }
    
    // Test some functions about the nodes from an edge
    @Test
    public void testGetNodesfromEdge(){
        // New graph
        String nodeFileName = "dataset/simple_nodes.csv";
        String edgeFileName = "dataset/simple_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        // edge 0
        IEdge e0 = g.getEdges().get(0);
        // e0 has node 0 on source and node 3 on target
        INode n0 = g.getNodes().get(0);
        INode n3 = g.getNodes().get(3);
        Assert.assertEquals(n0, e0.source());
        Assert.assertFalse(n0.equals(e0.target()));
        Assert.assertEquals(n3, e0.target());
        Assert.assertFalse(n3.equals(e0.source()));
        
        // edge 10
        IEdge e10 = g.getEdges().get(10);
        // e10 has node 4 on source and node 9 on target
        INode n4 = g.getNodes().get(4);
        INode n9 = g.getNodes().get(9);
        Assert.assertEquals(n4, e10.source());
        Assert.assertFalse(n0.equals(e10.source()));
        Assert.assertFalse(n3.equals(e10.source()));
        Assert.assertFalse(n9.equals(e10.source()));
        Assert.assertEquals(n9, e10.target());
        Assert.assertFalse(n0.equals(e10.target()));
        Assert.assertFalse(n3.equals(e10.target()));
        Assert.assertFalse(n4.equals(e10.target()));
        
    }
    
    // Test some functions about the neighbors of a node
    @Test
    public void testGetNodeNeighbors(){
        // New graph
        String nodeFileName = "dataset/simple_nodes.csv";
        String edgeFileName = "dataset/simple_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        // node 0
        INode n0 = g.getNodes().get(0);
        // n0 has 2 successors : n3 and n6
        ArrayList<INode> sucessorsN0 = g.getSuccesors(n0);
        Assert.assertEquals(2, sucessorsN0.size());
        // n3 and n6
        INode n3 = g.getNodes().get(3);
        Assert.assertEquals(n3, sucessorsN0.get(0));
        Assert.assertFalse(n3.equals(sucessorsN0.get(1)));
        INode n6 = g.getNodes().get(6);
        Assert.assertEquals(n6, sucessorsN0.get(1));
        Assert.assertFalse(n6.equals(sucessorsN0.get(0)));
        
        // node 11
        INode n11 = g.getNodes().get(11);
        // n11 has 3 predecessors : n1, n2 and n8
        ArrayList<INode> predecessorsN11 = g.getPredecessors(n11);
        Assert.assertEquals(3, predecessorsN11.size());
        // n1, n2 and n8
        INode n1 = g.getNodes().get(1);
        Assert.assertEquals(n1, predecessorsN11.get(0));
        Assert.assertFalse(n1.equals(predecessorsN11.get(1)));
        Assert.assertFalse(n1.equals(predecessorsN11.get(2)));
        INode n2 = g.getNodes().get(2);
        Assert.assertEquals(n2, predecessorsN11.get(1));
        Assert.assertFalse(n2.equals(predecessorsN11.get(0)));
        Assert.assertFalse(n2.equals(predecessorsN11.get(2)));
        INode n8 = g.getNodes().get(8);
        Assert.assertEquals(n8, predecessorsN11.get(2));
        Assert.assertFalse(n8.equals(predecessorsN11.get(0)));
        Assert.assertFalse(n8.equals(predecessorsN11.get(1)));
        
        // node 5
        INode n5 = g.getNodes().get(5);
        // n5 has 4 neighors : 2 predecessors (n1 and n3) 
        // and 2 succesors (n6 and n8)
        ArrayList<INode> neighborsN5 = g.getNeighbors(n5);
        Assert.assertEquals(4,neighborsN5.size());
        // n1
        Assert.assertEquals(n1, neighborsN5.get(0));
        Assert.assertFalse(n1.equals(neighborsN5.get(1)));
        Assert.assertFalse(n1.equals(neighborsN5.get(2)));
        Assert.assertFalse(n1.equals(neighborsN5.get(3)));
        // n3
        Assert.assertEquals(n3, neighborsN5.get(1));
        Assert.assertFalse(n3.equals(neighborsN5.get(0)));
        Assert.assertFalse(n3.equals(neighborsN5.get(2)));
        Assert.assertFalse(n3.equals(neighborsN5.get(3)));
        // n6
        Assert.assertEquals(n6, neighborsN5.get(2));
        Assert.assertFalse(n6.equals(neighborsN5.get(0)));
        Assert.assertFalse(n6.equals(neighborsN5.get(1)));
        Assert.assertFalse(n6.equals(neighborsN5.get(3)));
        // n8
        Assert.assertEquals(n8, neighborsN5.get(3));
        Assert.assertFalse(n8.equals(neighborsN5.get(0)));
        Assert.assertFalse(n8.equals(neighborsN5.get(1)));
        Assert.assertFalse(n8.equals(neighborsN5.get(2)));
    }
    
    // Test some functions to get degrees of a node
    @Test
    public void testGetAllDegreeNode(){
        // New graph
        String nodeFileName = "dataset/simple_nodes.csv";
        String edgeFileName = "dataset/simple_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        // node 0
        INode n0 = g.getNodes().get(0);
        // n0 has 2 succesors, so an outdegree of 2
        int outDegreeN0 = g.outDegree(n0);
        Assert.assertEquals(2, outDegreeN0);
        
        // node 11
        INode n11 = g.getNodes().get(11);
        // n11 has 3 predecessors, so an indegree of 3
        int inDegreeN11 = g.inDegree(n11);
        Assert.assertEquals(3, inDegreeN11);
        
        // node 5
        INode n5 = g.getNodes().get(5);
        // n5 has 4 neighbors, so a degree of 4
        int degreeN5 = g.degree(n5);
        Assert.assertEquals(4, degreeN5);
    }
}
