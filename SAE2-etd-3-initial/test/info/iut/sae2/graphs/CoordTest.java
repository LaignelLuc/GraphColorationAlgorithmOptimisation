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
        // New coordinates
        Coord c1 = new Coord(1.5,2.5);
        // Another point
        Coord c2 = new Coord(3.5,4.5);
        double expResult = Math.sqrt(8); // sqrt(8)
        double result = c2.dist(c1);
        double tolerant = 1e-6;
        assertEquals(expResult, result, tolerant);
        // If we inversed c2 and c1, result should be the same
        result = c1.dist(c2);
        assertEquals(expResult, result, tolerant);
    }

    /**
     * Test of norm method, of class Coord.
     */
    @Test
    public void testNorm() {
        System.out.println("norm");
        Coord instance = new Coord(3.0,4.0);
        double expResult = 5.0; // From origin : pt (0,0)
        // See pytagoras theorem
        double tolerant = 1e-6;
        double result = instance.norm();
        assertEquals(expResult, result, tolerant);
    }

    /**
     * Test of mult method, of class Coord.
     */
    @Test
    public void testMult_double() {
        System.out.println("mult");
        double scalar = 2.0;
        Coord instance = new Coord(10,10);
        instance.mult(scalar);
        // Test with another coordinates
        Coord c1 = new Coord(20,20);
        double tolerant = 1e-6;
        assertEquals(c1.getX(), instance.getX(), tolerant);
        assertEquals(c1.getY(), instance.getY(), tolerant);
    }

    /**
     * Test of add method, of class Coord.
     */
    @Test
    public void testAdd_double_double() {
        System.out.println("add with doubles");
        double x = 5.0;
        double y = 6.0;
        Coord instance = new Coord(1.0,1.0);
        instance.add(x, y);
        // Compare with result Coord
        Coord expResult = new Coord(6.0, 7.0);
        double tolerant = 1e-6;
        assertEquals(expResult.getX(), instance.getX(), tolerant);
        assertEquals(expResult.getY(), instance.getY(), tolerant);
    }

    /**
     * Test of add method, of class Coord.
     */
    @Test
    public void testAdd_Coord() {
        System.out.println("add with a coordinate");
        Coord c = new Coord(5.0, 6.0);
        Coord instance = new Coord(1.0, 1.0);
        instance.add(c);
        // Compare with result Coord
        Coord expResult = new Coord(6.0, 7.0);
        double tolerant = 1e-6;
        assertEquals(expResult.getX(), instance.getX(), tolerant);
        assertEquals(expResult.getY(), instance.getY(), tolerant);
    }

    /**
     * Test of add method, of class Coord.
     */
    @Test
    public void testAdd_Coord_Coord() {
        System.out.println("Add Test between 2 coordinates");
        Coord c1 = new Coord(5.0, 6.0);
        Coord c2 = new Coord(1.0, 1.0);
        
        // Compare with result Coord
        Coord expResult = new Coord(6.0, 7.0);
        double tolerant = 1e-6;
        Coord result = Coord.add(c1, c2);
        assertEquals(expResult.getX(), result.getX(), tolerant);
        assertEquals(expResult.getY(), result.getY(), tolerant);
        
    }

    /**
     * Test of minus method, of class Coord.
     */
    @Test
    public void testMinus() {
        System.out.println("Minus Test between 2 coordinates");
        Coord c1 = new Coord(5.0, 6.0);
        Coord c2 = new Coord(1.0, 1.0);
        
        // Compare with result Coord
        Coord expResult = new Coord(4.0, 5.0);
        double tolerant = 1e-6;
        Coord result = Coord.minus(c1, c2);
        assertEquals(expResult.getX(), result.getX(), tolerant);
        assertEquals(expResult.getY(), result.getY(), tolerant);
    }

    /**
     * Test of mult method, of class Coord.
     */
    @Test
    public void testMult_Coord_double() {
        System.out.println("mult of a coordinate with a scalar");
        Coord c1 = new Coord(1.0, 2.0);
        double scalar = 3.0;
        
        // Compare with result Coord
        Coord expResult = new Coord(3.0, 6.0);
        double tolerant = 1e-6;
        Coord result = Coord.mult(c1, scalar);
        assertEquals(expResult.getX(), result.getX(), tolerant);
        assertEquals(expResult.getY(), result.getY(), tolerant);
    }
    
}
