/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package info.iut.sae2.graphs;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpmelisse
 */
public class ColorTest {
    
    
    /**
     * Test of getR method, of class Color.
     */
    @Test
    public void testGetR() {
        System.out.println("getR");
        Color instance = new Color();
        int expResult = 0;
        int result = instance.getR();
        assertEquals(expResult, result);
        assertFalse(result==255);
        // New color (Red)
        Color color2 = new Color(255,0,0,255);
        result = color2.getR();
        expResult = 255;
        assertEquals(expResult, result);
        assertFalse(result==0);
    }

    /**
     * Test of getG method, of class Color.
     */
    @Test
    public void testGetG() {
        System.out.println("getG");
        Color instance = new Color();
        int expResult = 0;
        int result = instance.getG();
        assertEquals(expResult, result);
        assertFalse(result==255);
        // New color (Green)
        Color color2 = new Color(0,255,0,255);
        result = color2.getG();
        expResult = 255;
        assertEquals(expResult, result);
        assertFalse(result==0);
    }

    /**
     * Test of getB method, of class Color.
     */
    @Test
    public void testGetB() {
        System.out.println("getB");
        Color instance = new Color();
        int expResult = 0;
        int result = instance.getB();
        assertEquals(expResult, result);
        assertFalse(result==255);
        // New color (Blue)
        Color color2 = new Color(0,0,255,255);
        result = color2.getB();
        expResult = 255;
        assertEquals(expResult, result);
        assertFalse(result==0);
    }

    /**
     * Test of getA method, of class Color.
     */
    @Test
    public void testGetA() {
        System.out.println("getA");
        Color instance = new Color();
        int expResult = 0;
        int result = instance.getA();
        assertEquals(expResult, result);
        assertFalse(result==255);
        // New color (Red, alpha determine opacity)
        // opacity : 50% -> 128
        Color color2 = new Color(255,0,0,128);
        result = color2.getA();
        expResult = 128;
        assertEquals(expResult, result);
        assertFalse(result==0);
    }

    
    /**
     * Test of setR method, of class Color.
     */
    @Test
    public void testSetR() {
        System.out.println("setR");
        Color instance = new Color();
        int expResult = 0;
        int result = instance.getR();
        assertEquals(expResult, result);
        assertFalse(result==255);
        
        // Change Red color of the object
        int r = 255;
        instance.setR(r);
        result = instance.getR();
        expResult = 255;
        assertEquals(expResult, result);
        assertFalse(result==0);
        
    }

    /**
     * Test of setG method, of class Color.
     */
    @Test
    public void testSetG() {
        System.out.println("setG");
        Color instance = new Color();
        int expResult = 0;
        int result = instance.getG();
        assertEquals(expResult, result);
        assertFalse(result==255);
        
        // Change Green color of the object
        int g = 255;
        instance.setG(g);
        result = instance.getG();
        expResult = 255;
        assertEquals(expResult, result);
        assertFalse(result==0);
    }

    /**
     * Test of setB method, of class Color.
     */
    @Test
    public void testSetB() {
        System.out.println("setB");
        Color instance = new Color();
        int expResult = 0;
        int result = instance.getB();
        assertEquals(expResult, result);
        assertFalse(result==255);
        
        // Change Blue color of the object
        int b = 255;
        instance.setB(b);
        result = instance.getB();
        expResult = 255;
        assertEquals(expResult, result);
        assertFalse(result==0);
    }

    /**
     * Test of setA method, of class Color.
     */
    @Test
    public void testSetA() {
        System.out.println("setA");
        Color instance = new Color();
        int expResult = 0;
        int result = instance.getA();
        assertEquals(expResult, result);
        assertFalse(result==255);
        
        // Change opacity of the object
        int a = 255;
        instance.setA(a);
        result = instance.getA();
        expResult = 255;
        assertEquals(expResult, result);
        assertFalse(result==0);
    }

    /**
     * Test of interpolate method, of class Color.
     */
    @Test
    public void testInterpolate() {
        System.out.println("interpolate");
        Color start = null;
        Color end = null;
        int step = 0;
        int nbSteps = 0;
        Color expResult = null;
        Color result = Color.interpolate(start, end, step, nbSteps);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    /**
     * Test of equals method, of class Color.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Color instance = new Color();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Color.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Color instance = new Color();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
