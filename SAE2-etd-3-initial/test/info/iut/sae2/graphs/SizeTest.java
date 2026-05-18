/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package info.iut.sae2.graphs;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpmelisse
 */
public class SizeTest {
    
    public SizeTest() {
    }

    /**
     * Test of getH method, of class Size.
     */
    @Test
    public void testGetH() {
        System.out.println("getH");
        Size instance = new Size(1.0, 5.0);
        double expResult = 5.0;
        double result = instance.getH();
        double tolerant = 1e-6;
        assertEquals(expResult, result, tolerant);
    }

    /**
     * Test of getW method, of class Size.
     */
    @Test
    public void testGetW() {
        System.out.println("getW");
        Size instance = new Size(1.0, 5.0);
        double expResult = 1.0;
        double result = instance.getW();
        double tolerant = 1e-6;
        assertEquals(expResult, result, tolerant);
    }

    /**
     * Test of setW method, of class Size.
     */
    @Test
    public void testSetW() {
        System.out.println("setW");
        Size instance = new Size();
        double expResult = 0.0;
        double result = instance.getW();
        double tolerant = 1e-6;
        assertEquals(expResult, result, tolerant);
        
        // Change width
        int w = 5;
        instance.setW(w);
        result = instance.getW();
        assertEquals(w, result, tolerant);         
    }

    /**
     * Test of setH method, of class Size.
     */
    @Test
    public void testSetH() {
        System.out.println("setH");
        Size instance = new Size();
        double expResult = 0.0;
        double result = instance.getH();
        double tolerant = 1e-6;
        assertEquals(expResult, result, tolerant);
        
        // Change height
        int h = 8;
        instance.setH(h);
        result = instance.getH();
        assertEquals(h, result, tolerant);
    }
    
}
