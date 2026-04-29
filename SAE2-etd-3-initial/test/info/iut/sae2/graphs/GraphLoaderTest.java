/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.graphs;

import org.junit.Test;
import org.junit.Assert;
/**
 *
 * @author jpmelisse
 * 
 * contains all test functions for the class GraphLoader
 */
public class GraphLoaderTest {
    

    /**
     * Test for loadFromFile method.
     */
    @Test
    public void testLoadFromFile(){
        // Get a new graph from files
        String nodeFileName = "dataset/simple_nodes.csv";
        String edgeFileName = "dataset/simple_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        // Check number of nodes and Edges
        Assert.assertEquals(12, g.numberOfNodes());
        Assert.assertEquals(16, g.numberOfEdges());
        
        // Check 1st node coordinates
        Coord testCoord = new Coord(13.0, 7.5);
        INode node1 = g.getNodes().get(0);
        Coord nodeCoord = g.getNodePosition(node1);
        Assert.assertEquals(testCoord.x, nodeCoord.x, 0.01);
        Assert.assertEquals(testCoord.y, nodeCoord.y, 0.01);
        
        // Verify present of Edges
        INode src = g.getNodes().get(0);
        INode tgt = g.getNodes().get(3);
        Assert.assertTrue(g.existEdge(src, tgt, false));
        
        // Edge not present btw 0 and 1
        tgt = g.getNodes().get(1);
        Assert.assertFalse(g.existEdge(src, tgt, false));
    }
}
