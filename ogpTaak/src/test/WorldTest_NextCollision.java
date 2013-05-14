package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import gameObjects.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.Util;

import support.Collision;
import support.Vector;

/**
 * A class collecting tests concerning the methods for determining the next collision in the class of worlds.
 * 
 * @author 	Martijn Boussé, Wout Vekemans
 * @version	1.0
 *
 */
public class WorldTest_NextCollision {
	
	/**
	 * A world containing three collidables that will collide with the boundary.
	 */
	private World world1;
	private Collidable collidable1;
	private Collidable collidable2;
	private Collidable collidable3;
	
	/**
	 * A world containing four collidables that will collide with each other.
	 */
	private World world2;
	private Collidable collidable4;
	private Collidable collidable5;
	private Collidable collidable6;
	private Collidable collidable7;
	
	/**
	 * A world containing four collidables of which two will collide with the boundary
	 * and two with each other.
	 */
	private World world3;
	private Collidable collidable8;
	private Collidable collidable9;
	private Collidable collidable10;
	private Collidable collidable11;
	
	/**
	 * A world containing two collidables with zero velocity.
	 */
	private World world4;
	private Collidable collidable12;
	private Collidable collidable13;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
	
		world1 = new World(1000,1000);
		collidable1 = new Ship(new Vector(100,100), new Vector(0,-10), 10, 10, 0);
		collidable2 = new Ship(new Vector(500,500), new Vector(0,-10), 10, 10, 0);
		collidable3 = new Ship(new Vector(100,700), new Vector(10,0), 10, 10, 0);
		world1.addAsCollidable(collidable1);
		world1.addAsCollidable(collidable2);
		world1.addAsCollidable(collidable3);
		
		world2 = new World(1000,1000);
		collidable4 = new Ship(new Vector(100,100), new Vector(10,0), 10, 10, 0);
		collidable5 = new Ship(new Vector(400,100), new Vector(-10,0), 10, 10, 0);
		collidable6 = new Ship(new Vector(100,500), new Vector(10,0), 10, 10, 0);
		collidable7 = new Ship(new Vector(900,500), new Vector(-10,0), 10, 10, 0);
		world2.addAsCollidable(collidable4);
		world2.addAsCollidable(collidable5);
		world2.addAsCollidable(collidable6);
		world2.addAsCollidable(collidable7);
		
		world3 = new World(1000,1000);
		collidable8 = new Ship(new Vector(400,500), new Vector(10,0), 10, 10, 0);
		collidable9 = new Ship(new Vector(600,500), new Vector(-10,0), 10, 10, 0);
		collidable10 = new Ship(new Vector(400,400), new Vector(0,-10), 10, 10, 0);
		collidable11 = new Ship(new Vector(400,600), new Vector(10,0), 10, 10, 0);
		world3.addAsCollidable(collidable8);
		world3.addAsCollidable(collidable9);
		world3.addAsCollidable(collidable10);
		world3.addAsCollidable(collidable11);
		
		world4 = new World(1000,1000);
		collidable12 = new Ship(new Vector(100,100), new Vector(0,0), 10, 10, 0);
		collidable13 = new Ship(new Vector(900,900), new Vector(0,0), 10, 10, 0);
		world4.addAsCollidable(collidable12);
		world4.addAsCollidable(collidable13);
	}
	
	
	// Case 1: Only collisions with boundary
	
	@Test // Does the method return the earliest collision?
	public void testGetNextCollisionWithBoundary_Case1() {
		Collision next = world1.getNextCollisionWithBoundary();
		for(Collidable collidable: world1.getAllCollidables()) {
			assertTrue(Util.fuzzyLessThanOrEqualTo(next.getTime(),collidable.getTimeToCollisionWithBoundary()));
		}
	}
	
	@Test // Does the method return null?
	public void testGetNextCollisionWithOther_Case1() {
		assertEquals(world1.getNextCollisionWithOther(),null);
	}
	
	@Test // Does the method return the correct collision?
	public void testGetNextCollision_Case1() {
		assertEquals(world1.getNextCollision(),world1.getNextCollisionWithBoundary());
	}
	
	
	// Case 2: Only collisions with each other
	
	@Test // Does the method return the earliest collision?
	public void testGetNextCollisionWithOther_Case2() {
		ArrayList<Collidable> collidables = new ArrayList<Collidable>(world2.getAllCollidables());
		Collision next = world2.getNextCollisionWithOther();

		for (int i = 0; i < world2.getNbCollidables(); i++) { 
			for (int j = i + 1; j < world2.getNbCollidables(); j++) { 
				assertTrue(Util.fuzzyLessThanOrEqualTo(next.getTime(),
							collidables.get(i).getTimeToCollision(collidables.get(j))));
			}			
		}	
	}
	
	// getNextCollisionWithBoundary will also find a (non-relevant) collision.
	
	@Test // Does the method return the correct collision?
	public void testGetNextCollision_Case2() {
		assertEquals(world2.getNextCollision(),world2.getNextCollisionWithOther());
	}
	
	
	// Case 3: Collisions with each other and with the boundary
	
	@Test // Does the method return the earliest collision?
	public void testGetNextCollisionWithBoundary_Case3() {
		Collision next = world3.getNextCollisionWithBoundary();
		for(Collidable collidable: world3.getAllCollidables()) {
			assertTrue(Util.fuzzyLessThanOrEqualTo(next.getTime(),collidable.getTimeToCollisionWithBoundary()));
		}
	}
	
	@Test // Does the method return the earliest collision?
	public void testGetNextCollisionWithOther_Case3() {
		ArrayList<Collidable> collidables = new ArrayList<Collidable>(world3.getAllCollidables());
		Collision next = world3.getNextCollisionWithOther();

		for (int i = 0; i < world3.getNbCollidables(); i++) { 
			for (int j = i + 1; j < world3.getNbCollidables(); j++) { 
				assertTrue(Util.fuzzyLessThanOrEqualTo(next.getTime(),
							collidables.get(i).getTimeToCollision(collidables.get(j))));
			}			
		}	
	}
	
	@Test // Does the method return the correct collision?
	public void testGetNextCollision_Case3() {
		assertEquals(world3.getNextCollision(),world3.getNextCollisionWithOther());
	}
	
	
	// Case 4: No collisions
	
	@Test // Does the method return null?
	public void testGetNextCollisionWithBoundary_Case4() {
		assertEquals(world4.getNextCollisionWithBoundary(),null);
	}
	
	@Test // Does the method return null?
	public void testGetNextCollisionWithOther_Case4() {
		assertEquals(world4.getNextCollisionWithOther(),null);
	}
	
	@Test // Does the method return null?
	public void testGetNextCollision_Case4() {
		assertEquals(world4.getNextCollision(),null);
	}
	
	
	
}
