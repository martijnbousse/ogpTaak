package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import collidable.*;
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
		mutableCollidable5 = new Ship(new Vector(300,325), new Vector(1,0), 50, 1000, 0);
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
		mutableCollidable9 = new Ship(new Vector(100,90), new Vector(0,1), 10, 1000, 0); 	// upper boundary
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
	
	// bounce: legal case
	@Test
	public void testBounce_LegalCase() {
		mutableCollidable1.bounce(mutableCollidable2);
		mutableCollidable1.move(1);
		mutableCollidable2.move(1);
		assertFalse(mutableCollidable1.overlap(mutableCollidable2));
	}
	
	// bounce: illegal case (the collidables move closer to each other)
	@Test
	public void testBounce_IllegalCase() {
		double oldTimeToCollision = mutableCollidable3.getTimeToCollision(mutableCollidable4);
		mutableCollidable3.bounce(mutableCollidable4);
		mutableCollidable3.move(1);
		mutableCollidable4.move(1);
		assertTrue(oldTimeToCollision>mutableCollidable3.getTimeToCollision(mutableCollidable4));
	}
	
	// bounce: null case
	@Test(expected=IllegalArgumentException.class)
	public void testBounce_NullCase() {
		mutableCollidable1.bounce(null);
	}
	
	// bounce: other not in a world case
	@Test(expected=IllegalArgumentException.class)
	public void testBounce_OtherNotInWorldCase() {
		mutableCollidable1.bounce(mutableCollidable13);
	}
	
	// bounce: this not in a world case
	@Test(expected=IllegalStateException.class)
	public void testBounce_ThisNotInWorldCase() {
		mutableCollidable13.bounce(mutableCollidable1);
	}
	
	// bounce: both collidables not in a world case
	@Test(expected=IllegalArgumentException.class)
	public void testBounce_BothCollidablesNotInAWorldCase() {
		mutableCollidable13.bounce(mutableCollidable14);
	}
	
	// bounce: other is terminated case 
	// (REMINDER: hasProperWorld() in bounce() also calls the complementary checker canHaveAsCollidable() 
	//				from the bidirectional association. Hence, terminated collidables are also included.
	@Test(expected=IllegalArgumentException.class)
	public void testBounce_OtherIsTerminatedCase() {
		mutableCollidable1.bounce(terminatedCollidable1);
	}
	
	// bounce: this is terminated case
	// (REMINDER: hasProperWorld() in bounce() also calls the complementary checker canHaveAsCollidable() 
		//				from the bidirectional association. Hence, terminated collidables are also included.
	@Test(expected=IllegalStateException.class)
	public void testBounce_ThisIsTerminatedCase() {
		terminatedCollidable1.bounce(mutableCollidable1);
	}
	
//	// bounce: overlap case
//	@Test(expected=IllegalArgumentException.class)
//	public void testBounce_OverlapCase() {
//		mutableCollidable4.bounce(mutableCollidable5);
//	}
	
	// bounce: this ship is in another world case
	@Test(expected=IllegalArgumentException.class)
	public void testBounce_ThisInAnotherWorldCase() {
		mutableCollidable1.bounce(mutableCollidable7);
	}
	
	// bounce: other ship is in another world case
	@Test(expected=IllegalArgumentException.class)
	public void testBounce_OtherInAnotherWorldCase() {
		mutableCollidable7.bounce(mutableCollidable1);
	}
	
	// bounce of boundary: left case
	@Test
	public void testBounceOfBoundary_LeftCase() {
		mutableCollidable8.bounceOfBoundary();
		mutableCollidable8.move(1);
		assertFalse(mutableCollidable8.overlapWithBoundary());
	}
	
	// bounce of boundary: upper case
	@Test
	public void testBounceOfBoundary_UpperCase() {
		mutableCollidable9.bounceOfBoundary();
		mutableCollidable9.move(1);
		assertFalse(mutableCollidable9.overlapWithBoundary());
	}
	
	// bounce of boundary: right case
	@Test
	public void testBounceOfBoundary_RightCase() {
		mutableCollidable10.bounceOfBoundary();
		mutableCollidable10.move(1);
		assertFalse(mutableCollidable10.overlapWithBoundary());
	}
	
	// bounce of boundary: right case
	@Test
	public void testBounceOfBoundary_LowerCase() {
		mutableCollidable11.bounceOfBoundary();
		mutableCollidable11.move(1);
		assertFalse(mutableCollidable11.overlapWithBoundary());
	}
	
	// bounce of boundary: illegal case
	@Test
	public void testBounceOfBoundary_IllegalCase() {
		double oldTimeToCollision = mutableCollidable12.getTimeToCollisionWithBoundary();
		mutableCollidable12.bounceOfBoundary();
		mutableCollidable12.move(1);
		assertTrue(oldTimeToCollision>mutableCollidable12.getTimeToCollisionWithBoundary());
	}
	
	// bounce of boundary: terminated case
	@Test(expected=IllegalStateException.class)
	public void testBounceOfBoundary_TerminatedCase() {
		terminatedCollidable1.bounceOfBoundary();
	}
	
	// bounce of boundary: not in a world case
	@Test(expected=IllegalStateException.class)
	public void testBounceOfBoundary_NotInAWorldCase() {
		mutableCollidable13.bounceOfBoundary();
	}
	
}
