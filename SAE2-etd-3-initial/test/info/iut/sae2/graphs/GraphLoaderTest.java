/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.graphs;

import java.util.HashMap;
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
        String nodeFileName = "simple_nodes.csv";
        String edgeFileName = "simple_edge.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        // Check number of nodes
        Assert.assertEquals(12, g.numberOfNodes());
        
        // Check 1st node coordinates
        Coord testCoord = new Coord(13.0, 7.5);
        INode node1 = g.getNodes().get(0);
        Assert.assertEquals(testCoord, g.getNodePosition(node1));
        
        // Verify present of Edges
        INode src = g.getNodes().get(0);
        INode tgt = g.getNodes().get(3);
        Assert.assertTrue(g.existEdge(src, tgt, false));
        
        // Edge not present btw 0 and 1
        tgt = g.getNodes().get(1);
        Assert.assertFalse(g.existEdge(src, tgt, false));
    }
}
