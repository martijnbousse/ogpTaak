package test;

import static org.junit.Assert.*;
import gameObjects.Asteroid;
import gameObjects.Collidable;
import gameObjects.Ship;
import gameObjects.World;

import org.junit.Before;
import org.junit.Test;

import asteroids.Util;

import support.*;

/**
 * A class collecting tests for debugging the method bounceOfBoundary of collidables in the class of collidables.
 * 
 * @author 	Martijn Boussé, Wout Vekemans
 * @version	1.0
 *
 */

public class CollidableTest_DebuggingBounceOfBoundary {

	// world
	private World world;
	
	// asteroid located in a world
	private Collidable asteroid;
	private Collidable ship;
	
	@Before
	public void setUp() throws Exception {
		world = new World();
		asteroid = new Asteroid(new Vector(150,100), new Vector(0,-10), 10);
		ship = new Ship(new Vector(1000,1000), new Vector(0,0), 10, 10, 0);
		world.addAsCollidable(asteroid);
		world.addAsCollidable(ship);
	}
	
	@Test
	public void testGetWorld() {
		assertTrue(asteroid.getWorld()!=null);
	}
	
	@Test
	public void testGetNbCollidables() {
		assertEquals(world.getNbAsteroids(),1);
	}
	
	@Test
	public void testGetDistanceToClosestBoundary() {
		assertEquals(asteroid.getDistanceToClosestBoundary(),90,Util.EPSILON);	
	}
	
	@Test
	public void testGetTimeToCollisionWithBoundary() {
		assertEquals(asteroid.getTimeToCollisionWithBoundary(),9,Util.EPSILON);	
	}
	
	@Test
	public void testGetNextCollision() {
		Collision next = world.getNextCollision();
		assertEquals(next.getFirst(),asteroid);
		assertEquals(next.getSecond(),null);
		assertEquals(next.getTime(),9,Util.EPSILON);
	}
	
}
