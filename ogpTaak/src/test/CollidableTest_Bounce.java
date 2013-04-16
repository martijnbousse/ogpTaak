package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import collidable.*;
import exceptions.TimesOverflowException;
import asteroids.*;

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
	
	// located in mutableWorld1
	private Collidable mutableCollidable1;
	private Collidable mutableCollidable2;
	private Collidable mutableCollidable3;
	private Collidable mutableCollidable4;
	private Collidable mutableCollidable5;
	
	// located in mutableWorld2
	private Collidable mutableCollidable6;
	private Collidable mutableCollidable7;
	
	// located in no world
	private Collidable mutableCollidable8;
	
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
		mutableCollidable5 = new Ship(new Vector(100,325), new Vector(-1,-1), 50, 1000, 0);
		mutableWorld1.addAsCollidable(mutableCollidable1);
		mutableWorld1.addAsCollidable(mutableCollidable2);
		mutableWorld1.addAsCollidable(mutableCollidable3);
		mutableWorld1.addAsCollidable(mutableCollidable4);
		
		mutableWorld2 = new World();
		mutableCollidable6 = new Ship(new Vector(100,100), new Vector(1,0), 50, 1000, 0);
		mutableCollidable7 = new Ship(new Vector(200,100), new Vector(-1,0), 50, 1000, 0);
		mutableWorld2.addAsCollidable(mutableCollidable5);
		mutableWorld2.addAsCollidable(mutableCollidable6);
		
		mutableCollidable8 = new Ship();
		
		terminatedCollidable1 = new Ship();
		terminatedCollidable1.terminate();
		terminatedCollidable2 = new Ship();
		terminatedCollidable2.terminate();
	}
	
	// bounce legal case
	@Test
	public void testBounce_LegalCase() {
		mutableCollidable1.bounce(mutableCollidable2);
		mutableCollidable1.move(1);
		mutableCollidable2.move(1);
		assertFalse(mutableCollidable1.overlap(mutableCollidable2));
	}
	
	// bounce illegal case (the velocities of the collidables are not updated)
	@Test
	public void testBounce_IllegalCase() {
		mutableCollidable3.bounce(mutableCollidable4);
		mutableCollidable1.move(1);
		mutableCollidable2.move(1);
		assertTrue(mutableCollidable1.overlap(mutableCollidable2));
	}
	
	// bounce null case
	@Test(expected=IllegalArgumentException.class)
	public void testBounce_NullCase() {
		mutableCollidable1.bounce(null);
	}
	
	// bounce other not in a world case
	@Test(expected=IllegalArgumentException.class)
	public void testBounce_OtherNotInWorldCase() {
		mutableCollidable1.bounce(mutableCollidable8);
	}
	
	// bounce this not in a world case
	@Test(expected=IllegalStateException.class)
	public void testBounce_ThisNotInWorldCase() {
		mutableCollidable8.bounce(mutableCollidable1);
	}
	
	// bounce and bouncewithboundary op deze manier uitschrijven, moeten al deze cases exceptions opgooien.
	
	// bounce other is terminated case
	@Test(expected=IllegalArgumentException.class)
	public void testBounce_OtherIsTerminatedCase() {
		mutableCollidable1.bounce(terminatedCollidable1);
	}
	
	// bounce this is terminated case
	@Test(expected=IllegalStateException.class)
	public void testBounce_ThisIsTerminatedCase() {
		terminatedCollidable1.bounce(mutableCollidable1);
	}
		
	
	// bounce overlap case
	
	// bounce one of both terminated, both terminated other terminated
	
	// bounce two ships in different worlds, thiscase and othercase
	
	// bounce with ship not in world, this and other case
	
	// two ships not in world bouncing
	
	
}
