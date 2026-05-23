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
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author jpmelisse
 */
public class SixColorationPlanarAlgorithmTest {

    /**
     * Test Six Color algorithm on a test graph.
     */
    @Test
    public void testSixColor() {
        // Get a new graph from files
        String nodeFileName = "dataset/simple_nodes.csv";
        String edgeFileName = "dataset/simple_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        // Apply Six Coloration algorithm
        g.sixColors();
        
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
        
        // Six coloration algorithm can color a graph up to six colors max.
        HashSet<Color> colors = new HashSet<>();
        for (INode n : g.getNodes()){
            colors.add(g.getNodeColor(n));
        }
        
        // Verify if colors <= 6
        Assert.assertTrue(colors.size() <= 6);
        System.out.println("Nombre de couleurs : "+colors.size());
    }
    
    /**
     * Test Six Color algorithm on a large graph.
     */
    @Test
    public void testSixColorBigGraph() {
        // Get a new graph from files
        String nodeFileName = "dataset/planar_grid_100_nodes.csv";
        String edgeFileName = "dataset/planar_grid_100_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        // Apply Six Coloration algorithm
        g.sixColors();
        
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
        
        // Six coloration algorithm can color a graph up to six colors max.
        HashSet<Color> colors = new HashSet<>();
        for (INode n : g.getNodes()){
            colors.add(g.getNodeColor(n));
        }
        
        // Verify if colors <= 6
        Assert.assertTrue(colors.size() <= 6);
        System.out.println("Nombre de couleurs : "+colors.size());
    }
    
    /**
     * Test Six Color algorithm on a large planar graph.
     */
    @Test
    public void testSixColorBigPlanar() {
        // Get a new graph from files
        String nodeFileName = "dataset/planar_2000_nodes.csv";
        String edgeFileName = "dataset/planar_2000_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        // Apply Six Coloration algorithm
        g.sixColors();
        
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
        
        // Six coloration algorithm can color a graph up to six colors max.
        HashSet<Color> colors = new HashSet<>();
        for (INode n : g.getNodes()){
            colors.add(g.getNodeColor(n));
        }
        
        // Verify if colors <= 6
        Assert.assertTrue(colors.size() <= 6);
        System.out.println("Nombre de couleurs : "+colors.size());
    }
}
