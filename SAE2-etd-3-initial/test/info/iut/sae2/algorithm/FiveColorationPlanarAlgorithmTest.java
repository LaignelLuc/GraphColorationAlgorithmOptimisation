/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.Color;
import info.iut.sae2.graphs.GraphLoader;
import info.iut.sae2.graphs.IEdge;
import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import info.iut.sae2.properties.ColorProperty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpmelisse
 */
public class FiveColorationPlanarAlgorithmTest {
    
    /**
     * Test Five Color algorithm on a test graph.
     */
    @Test
    public void testFiveColor() {
        // Get a new graph from files
        String nodeFileName = "dataset/simple_nodes.csv";
        String edgeFileName = "dataset/simple_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        // Apply Five Coloration algorithm
        g.fiveColors();
        
        // Verify that each node is colored different from their
        // neighbors's colors (best to loop in edges)
        for (IEdge e : g.getEdges()){
            // Get source node and color
            INode n1 = e.source();
            Color c1 = g.getNodeColor(n1);
            // Get target node and color
            INode n2 = e.target();
            Color c2 = g.getNodeColor(n2);
            Assert.assertFalse(c1.equals(c2));    
        }
        
        // Five coloration algorithm can color a graph up to five colors max.
        HashSet<Color> colors = new HashSet<>();
        for (INode n : g.getNodes()){
            colors.add(g.getNodeColor(n));
        }
        
        // Verify if colors <= 5
        Assert.assertTrue(colors.size() <= 5);
        System.out.println("Nombre de couleurs : "+colors.size());
    }
    
}
