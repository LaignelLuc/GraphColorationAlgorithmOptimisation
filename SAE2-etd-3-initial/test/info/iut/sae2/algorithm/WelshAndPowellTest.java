/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.Color;
import info.iut.sae2.graphs.GraphLoader;
import info.iut.sae2.graphs.IEdge;
import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author jpmelisse
 */
public class WelshAndPowellTest {
    
    /**
     * Test getOrder function to get an ordered arrayList of nodes.
    */
    @Test
    public void testGetOrder(){
        
        // Get a new graph from files
        String nodeFileName = "dataset/simple_nodes.csv";
        String edgeFileName = "dataset/simple_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        WelshAndPowell wb = new WelshAndPowell();
        
        ArrayList<INode> newMap = wb.buildNodeColorationOrder(g);
        
        //System.out.println(newMap.size());
        Assert.assertEquals(g.numberOfNodes(), newMap.size());
        System.out.println(newMap);
        ArrayList<Integer> map = WelshAndPowell.getDegree(g, newMap);
        System.out.println(map);
    }
    
    /**
     * Test getDegree function to get an ordered 
     * map of nodes with degrees.
    */
    @Test
    public void testGetDegree(){
        
        // Get a new graph from files
        String nodeFileName = "dataset/simple_nodes.csv";
        String edgeFileName = "dataset/simple_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        HashMap newMap = WelshAndPowell.getDegree(g);
        
        //System.out.println(newMap.size());
        Assert.assertEquals(g.numberOfNodes(), newMap.size());
        System.out.println(newMap.values());
        System.out.println(newMap.keySet());
    }
    
    /**
     * Test Welsh and Powell Color algorithm on a test graph.
    */
    @Test
    public void testWPColor(){
        // Get a new graph from files
        String nodeFileName = "dataset/simple_nodes.csv";
        String edgeFileName = "dataset/simple_edges.csv";
        
        IGraph g = GraphLoader.loadFromFile(nodeFileName, edgeFileName);
        
        // Apply Welsh and Powell algorithm
        g.welshAndPowell();
        
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
        
        // Optionnal : We can see the max colors used in this graph
        HashSet<Color> colors = new HashSet<>();
        for (INode n : g.getNodes()){
            colors.add(g.getNodeColor(n));
        }
        System.out.println("Nombre de couleurs : "+colors.size());
    }
}
