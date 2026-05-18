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
        Color start = new Color(255,0,0,255); // Red Color
        Color end = new Color(0,0,255,255); // Blue Color
        int step = 5;
        int nbSteps = 10;
        Color expResult = new Color(128,0,127,255); // Purple Color
        // The step give the Color interpolated between the colors start
        // and end.
        Color result = Color.interpolate(start, end, step, nbSteps);
        assertEquals(expResult, result);
        
        // Change step to 10
        step = 10;
        result = Color.interpolate(start, end, step, nbSteps);
        // result is end Color
        assertEquals(end, result);
        
        // Change step to 0
        step = 0;
        result = Color.interpolate(start, end, step, nbSteps);
        // result is start Color
        assertEquals(start, result);
    }

    
    /**
     * Test of equals method, of class Color.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        // Test if 2 colors are the same
        Color c1 = new Color(255,0,0,255); // Red Color
        Color c2 = new Color(0,0,255,255); // Blue Color
        Color c3 = new Color(c2); // Blue Color
        Color c4 = new Color(255,0,0,255); // Red Color
        assertFalse(c2.equals(c1));
        assertTrue(c3.equals(c2));
        assertTrue(c4.equals(c1));
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
        
        // Red Color
        Color c1 = new Color(255,0,0,255);
        expResult = 15810; // 255x31+255x31
        result = c1.hashCode();
        assertEquals(expResult, result);
        
        // White Color
        Color c2 = new Color(255,255,255,255);
        expResult = 31620; // 255x31*4
        result = c2.hashCode();
        assertEquals(expResult, result);
    }
    
}
