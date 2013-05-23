package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gameObjects.Collidable;
import gameObjects.Ship;
import gameObjects.World;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import support.Vector;

/**
 * A class collecting tests for all methods concerning bouncing of collidables or boundaries from the class of collidables.
 * 
 * @author 	Martijn Boussé, Wout Vekemans
 * @version	1.0
 *
 */

public class CollidableTest_Bounce {
	
	// worlds
	private World mutableWorld1;
	private World mutableWorld2;
	private World mutableWorld3;
	
	// located in mutableWorld1
	private Collidable mutableCollidable1;
	private Collidable mutableCollidable2;
	private Collidable mutableCollidable3;
	private Collidable mutableCollidable4;
	private Collidable mutableCollidable5;
	
	// located in mutableWorld2
	private Collidable mutableCollidable6;
	private Collidable mutableCollidable7;
	
	// located in mutableWorld3
	private Collidable mutableCollidable8;
	private Collidable mutableCollidable9;
	private Collidable mutableCollidable10;
	private Collidable mutableCollidable11;
	private Collidable mutableCollidable12;
	
	// located in no world
	private Collidable mutableCollidable13;
	private Collidable mutableCollidable14;
	
	// terminated collidables
	private Collidable terminatedCollidable1;
	private Collidable terminatedCollidable2;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		mutableWorld1 = new World();
		mutableCollidable1 = new Ship(new Vector(100,100), new Vector(1,0), 50, 1000, 0);
		mutableCollidable2 = new Ship(new Vector(200,100), new Vector(-1,0), 50, 1000, 0);
		mutableCollidable3 = new Ship(new Vector(100,300), new Vector(1,0), 50, 1000, 0);
		mutableCollidable4 = new Ship(new Vector(300,300), new Vector(-1,0), 50, 1000, 0);
		mutableCollidable5 = new Ship(new Vector(600,325), new Vector(1,0), 50, 1000, 0);
		mutableWorld1.addAsCollidable(mutableCollidable1);
		mutableWorld1.addAsCollidable(mutableCollidable2);
		mutableWorld1.addAsCollidable(mutableCollidable3);
		mutableWorld1.addAsCollidable(mutableCollidable4);
		mutableWorld1.addAsCollidable(mutableCollidable5);
		
		mutableWorld2 = new World();
		mutableCollidable6 = new Ship(new Vector(100,100), new Vector(1,0), 50, 1000, 0);
		mutableCollidable7 = new Ship(new Vector(200,100), new Vector(-1,0), 50, 1000, 0);
		mutableWorld2.addAsCollidable(mutableCollidable6);
		mutableWorld2.addAsCollidable(mutableCollidable7);
		
		mutableWorld3 = new World(200,200);
		mutableCollidable8 = new Ship(new Vector(10,100), new Vector(-1,0), 10, 1000, 0); 	// left boundary
		mutableCollidable9 = new Ship(new Vector(100,190), new Vector(0,1), 10, 1000, 0); 	// upper boundary
		mutableCollidable10 = new Ship(new Vector(190,100), new Vector(1,0), 10, 1000, 0); 	// right boundary
		mutableCollidable11 = new Ship(new Vector(100,10), new Vector(0,-1), 10, 1000, 0); 	// lower boundary
		mutableCollidable12 = new Ship(new Vector(100,100), new Vector(1,1), 10, 1000, 0); // middle of world
		mutableWorld3.addAsCollidable(mutableCollidable8);
		mutableWorld3.addAsCollidable(mutableCollidable9);
		mutableWorld3.addAsCollidable(mutableCollidable10);
		mutableWorld3.addAsCollidable(mutableCollidable11);
		mutableWorld3.addAsCollidable(mutableCollidable12);
		
		mutableCollidable13 = new Ship(new Vector(100,100), new Vector(1,0), 50, 1000, 0);
		mutableCollidable14 = new Ship(new Vector(200,100), new Vector(-1,0), 50, 1000, 0);
		
		terminatedCollidable1 = new Ship();
		terminatedCollidable1.terminate();
		terminatedCollidable2 = new Ship();
		terminatedCollidable2.terminate();
	}
	
	// canBounce
	
	@Test
	public void testCanBounce_LegalCase() {
		assertTrue(mutableCollidable1.canBounce(mutableCollidable2));	
	}
	
	@Test
	public void testCanBounce_IllegalCase() {
		assertFalse(mutableCollidable3.canBounce(mutableCollidable4));
	}
	
	@Test
	public void testCanBounce_OtherNotInAWorldCase() {
		assertFalse(mutableCollidable1.canBounce(mutableCollidable13));
	}
	
	@Test
	public void testCanBounce_ThisNotInAWorldCase() {
		assertFalse(mutableCollidable13.canBounce(mutableCollidable1));
	}
	
	@Test
	public void testCanBounce_BothNotInAWorldCase() {
		assertFalse(mutableCollidable13.canBounce(mutableCollidable14));
	}
	
	@Test
	public void testCanBounce_OtherIsTerminatedCase() {
		assertFalse(mutableCollidable1.canBounce(terminatedCollidable1));
	}
	
	@Test
	public void testCanBounce_ThisIsTerminatedCase() {
		assertFalse(terminatedCollidable1.canBounce(mutableCollidable1));
	}
	
	@Test
	public void testCanBounce_ThisInAnotherWorldCase() {
		assertFalse(mutableCollidable1.canBounce(mutableCollidable7));
	}
	
	@Test
	public void testCanBounce_OtherInAnotherWorldCase() {
		assertFalse(mutableCollidable7.canBounce(mutableCollidable1));
	}
	
	// bounce
	
	@Test
	public void testBounce_LegalCase() {
		Vector oldVelocity1 = mutableCollidable1.getVelocity();
		Vector oldVelocity2 = mutableCollidable2.getVelocity();
		mutableCollidable1.collide(mutableCollidable2);
		assertFalse(mutableCollidable1.getVelocity().equals(oldVelocity1));
		assertFalse(mutableCollidable2.getVelocity().equals(oldVelocity2));
	}
	
	@Test
	public void testBounce_IllegalCase() {
		Vector oldVelocity3 = mutableCollidable3.getVelocity();
		Vector oldVelocity4 = mutableCollidable4.getVelocity();
		mutableCollidable3.collide(mutableCollidable4);
		assertTrue(mutableCollidable3.getVelocity().equals(oldVelocity3));
		assertTrue(mutableCollidable4.getVelocity().equals(oldVelocity4));
	}
	
	@Test
	public void testBounce_OtherNotInWorldCase() {
		Vector oldVelocity1 = mutableCollidable1.getVelocity();
		Vector oldVelocity13 = mutableCollidable13.getVelocity();
		mutableCollidable1.collide(mutableCollidable13);
		assertTrue(mutableCollidable1.getVelocity().equals(oldVelocity1));
		assertTrue(mutableCollidable13.getVelocity().equals(oldVelocity13));
	}

	@Test
	public void testBounce_ThisNotInWorldCase() {
		Vector oldVelocity13 = mutableCollidable13.getVelocity();
		Vector oldVelocity1 = mutableCollidable1.getVelocity();
		mutableCollidable13.collide(mutableCollidable1);
		assertTrue(mutableCollidable13.getVelocity().equals(oldVelocity13));
		assertTrue(mutableCollidable1.getVelocity().equals(oldVelocity1));
	}
	
	@Test
	public void testBounce_BothCollidablesNotInAWorldCase() {
		Vector oldVelocity14 = mutableCollidable14.getVelocity();
		Vector oldVelocity13 = mutableCollidable13.getVelocity();
		mutableCollidable14.collide(mutableCollidable13);
		assertTrue(mutableCollidable14.getVelocity().equals(oldVelocity14));
		assertTrue(mutableCollidable13.getVelocity().equals(oldVelocity13));
	}
	
	@Test
	public void testBounce_OtherIsTerminatedCase() {
		Vector oldVelocity1 = mutableCollidable1.getVelocity();
		Vector oldVelocity2 = terminatedCollidable1.getVelocity();
		mutableCollidable1.collide(terminatedCollidable1);
		assertTrue(mutableCollidable1.getVelocity().equals(oldVelocity1));
		assertTrue(terminatedCollidable1.getVelocity().equals(oldVelocity2));
	}

	@Test
	public void testBounce_ThisIsTerminatedCase() {
		Vector oldVelocity1 = terminatedCollidable1.getVelocity();
		Vector oldVelocity2 = mutableCollidable1.getVelocity();
		terminatedCollidable1.collide(mutableCollidable1);
		assertTrue(terminatedCollidable1.getVelocity().equals(oldVelocity1));
		assertTrue(mutableCollidable1.getVelocity().equals(oldVelocity2));
	}
	
	@Test
	public void testBounce_ThisInAnotherWorldCase() {
		Vector oldVelocity1 = mutableCollidable1.getVelocity();
		Vector oldVelocity2 = mutableCollidable7.getVelocity();
		mutableCollidable1.collide(mutableCollidable7);
		assertTrue(mutableCollidable1.getVelocity().equals(oldVelocity1));
		assertTrue(mutableCollidable7.getVelocity().equals(oldVelocity2));
	}
	
	@Test
	public void testBounce_OtherInAnotherWorldCase() {
		Vector oldVelocity1 = mutableCollidable7.getVelocity();
		Vector oldVelocity2 = mutableCollidable1.getVelocity();
		mutableCollidable7.collide(mutableCollidable1);
		assertTrue(mutableCollidable7.getVelocity().equals(oldVelocity1));
		assertTrue(mutableCollidable1.getVelocity().equals(oldVelocity2));
	}
	
	// canBounceOfBoundary
	
	@Test
	public void testCanBounceOfBoundary_LeftCase() {
		assertTrue(mutableCollidable8.canBounceOfBoundary());
	}
	
	@Test
	public void testCanBounceOfBoundary_UpperCase() {
		assertTrue(mutableCollidable9.canBounceOfBoundary());
	}
	
	@Test
	public void testCanBounceOfBoundary_RightCase() {
		assertTrue(mutableCollidable10.canBounceOfBoundary());
	}
	
	@Test
	public void testCanBounceOfBoundary_LowerCase() {
		assertTrue(mutableCollidable11.canBounceOfBoundary());
	}
	
	@Test
	public void testCanBounceOfBoundary_FalseCase() {
		assertFalse(mutableCollidable12.canBounceOfBoundary());
	}
	
	@Test
	public void testCanBounceOfBoundary_ThisIsTerminatedCase() {
		assertFalse(terminatedCollidable1.canBounceOfBoundary());
	}
	
	@Test
	public void testCanBounceOfBoundary_ThisNotInAWorldCase() {
		assertFalse(mutableCollidable13.canBounceOfBoundary());
	}
	
	// bounceOfBoundary
	
	@Test
	public void testBounceOfBoundary_LeftCase() {
		mutableCollidable8.bounceOfBoundary();
		mutableCollidable8.move(1);
		assertTrue(mutableCollidable8.getDistanceToClosestBoundary() > 0);
	}
	
	@Test
	public void testBounceOfBoundary_UpperCase() {
		mutableCollidable9.bounceOfBoundary();
		mutableCollidable9.move(1);
		assertTrue(mutableCollidable9.getDistanceToClosestBoundary() > 0);
	}
	
	@Test
	public void testBounceOfBoundary_RightCase() {
		mutableCollidable10.bounceOfBoundary();
		mutableCollidable10.move(1);
		assertTrue(mutableCollidable10.getDistanceToClosestBoundary() > 0);
	}
	
	@Test
	public void testBounceOfBoundary_LowerCase() {
		mutableCollidable11.bounceOfBoundary();
		mutableCollidable11.move(1);
		assertTrue(mutableCollidable11.getDistanceToClosestBoundary() > 0);
	}
	
	@Test
	public void testBounceOfBoundary_FalseCase() {
		double oldTimeToCollision = mutableCollidable12.getTimeToCollisionWithBoundary();
		mutableCollidable12.bounceOfBoundary();
		mutableCollidable12.move(1);
		assertTrue(oldTimeToCollision>mutableCollidable12.getTimeToCollisionWithBoundary());
	}
}
