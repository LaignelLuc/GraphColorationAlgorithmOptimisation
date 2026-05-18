/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package info.iut.sae2.graphs;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bryum
 */
public class CoordTest {
    
    public CoordTest() {
    }

    /**
     * Test of getX method, of class Coord.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Coord instance = new Coord();
        double expResult = 0.0;
        double result = instance.getX();
        double tolerant = 1e-6;
        assertEquals(expResult, result, tolerant);
        
        // New Point
        Coord c1 = new Coord(42,43);
        expResult = 42.0;
        result = c1.getX();
        assertEquals(expResult, result, tolerant);        
    }

    /**
     * Test of getY method, of class Coord.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Coord instance = new Coord();
        double expResult = 0.0;
        double result = instance.getY();
        double tolerant = 1e-6;
        assertEquals(expResult, result, tolerant);
        
        // New Point
        Coord c1 = new Coord(42,43);
        expResult = 43.0;
        result = c1.getY();
        assertEquals(expResult, result, tolerant);
    }

    /**
     * Test of setX method, of class Coord.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        Coord instance = new Coord();
        double expResult = 0.0;
        double result = instance.getX();
        double tolerant = 1e-6;
        assertEquals(expResult, result, tolerant);
        
        // Change X coordinate
        double x = 42.0; 
        instance.setX(x);
        result = instance.getX();
        assertEquals(x, result, tolerant);
    }

    /**
     * Test of setY method, of class Coord.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        Coord instance = new Coord();
        double expResult = 0.0;
        double result = instance.getY();
        double tolerant = 1e-6;
        assertEquals(expResult, result, tolerant);
        
        // Change Y coordinate
        double y = 7.0;
        instance.setY(y);
        result = instance.getY();
         assertEquals(y, result, tolerant);
    }

    /**
     * Test of dist method, of class Coord.
     */
    @Test
    public void testDist() {
        System.out.println("dist");
        Coord c = null;
        Coord instance = new Coord();
        double expResult = 0.0;
        double result = instance.dist(c);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of norm method, of class Coord.
     */
    @Test
    public void testNorm() {
        System.out.println("norm");
        Coord instance = new Coord();
        double expResult = 0.0;
        double result = instance.norm();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mult method, of class Coord.
     */
    @Test
    public void testMult_double() {
        System.out.println("mult");
        double scalar = 0.0;
        Coord instance = new Coord();
        instance.mult(scalar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class Coord.
     */
    @Test
    public void testAdd_double_double() {
        System.out.println("add");
        double x = 0.0;
        double y = 0.0;
        Coord instance = new Coord();
        instance.add(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class Coord.
     */
    @Test
    public void testAdd_Coord() {
        System.out.println("add");
        Coord c = null;
        Coord instance = new Coord();
        instance.add(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class Coord.
     */
    @Test
    public void testAdd_Coord_Coord() {
        System.out.println("add");
        Coord c1 = null;
        Coord c2 = null;
        Coord expResult = null;
        Coord result = Coord.add(c1, c2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of minus method, of class Coord.
     */
    @Test
    public void testMinus() {
        System.out.println("minus");
        Coord c1 = null;
        Coord c2 = null;
        Coord expResult = null;
        Coord result = Coord.minus(c1, c2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mult method, of class Coord.
     */
    @Test
    public void testMult_Coord_double() {
        System.out.println("mult");
        Coord c1 = null;
        double scalar = 0.0;
        Coord expResult = null;
        Coord result = Coord.mult(c1, scalar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
