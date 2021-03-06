package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import support.Vector;

import exceptions.TimesOverflowException;
import gameObjects.*;
import asteroids.*;

/**
 * A class collecting tests for the class of collidables.
 * 
 * @author 	Martijn Bouss�, Wout Vekemans
 * @version	2.0
 *
 */
public class CollidableTest {
	
	private static World world1;
	private static World world2;
	private static World world3;
	
	private static Collidable collidable1;
	private static Collidable collidable2;
	private static Collidable collidable3;
	private static Collidable collidable4;
	private static Collidable collidable5;
	private static Collidable collidable6;
	private static Collidable collidable7;
	private static Collidable collidable8;
	private static Collidable collidable9;
	
	private static Collidable terminatedCollidable;
	
	private World mutableWorld1;
	private Collidable mutableCollidable1;
	private Collidable mutableCollidable2;
	private Collidable mutableCollidable3;
	
	private World mutableWorld2;
	private Collidable mutableCollidable7;
	private Collidable mutableCollidable8;
	private Collidable mutableCollidable9;
	private Collidable mutableCollidable10;
	private Collidable mutableCollidable11;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		world1 = new World();
		world2 = new World();
		world3 = new World();
		
		collidable1 = new Ship(new Vector(100,50), new Vector(5,10), 30, 1000, Math.PI/2);
		world1.addAsCollidable(collidable1);
		
		collidable2 = new Ship(new Vector(200,50), new Vector(5,10), 30, 1000, Math.PI/2);
		collidable3 = new Ship(new Vector(200,250), new Vector(-5,-10), 30, 1000, Math.PI/2);
		collidable4 = new Ship(new Vector(400,50), new Vector(50,10), 30, 1000, Math.PI/2);
		collidable5 = new Ship(new Vector(400,650), new Vector(5,10), 30, 1000, Math.PI/2);
		collidable6 = new Ship(new Vector(Double.MAX_VALUE,Double.MAX_VALUE), new Vector(5,10), 30, 1000, Math.PI/2);
		collidable7 = new Ship(new Vector(100,50), new Vector(5,10), 30, 1000, Math.PI/2);
		collidable9 = new Ship(new Vector(110,50), new Vector(5,10), 30, 1000, Math.PI/2);
		
		collidable8 = new Ship(new Vector(10,10), new Vector(0,0), 30, 1000, Math.PI/2);
		world3.addAsCollidable(collidable8);
		
		terminatedCollidable = new Ship();
		terminatedCollidable.terminate();	
	}

	@Before
	public void setUp() throws Exception {
		mutableWorld1 = new World();
		mutableCollidable1 = new Ship(new Vector(100,100), new Vector(1,1), 50, 800, Math.PI/2);
		mutableCollidable2 = new Ship(new Vector(Double.MAX_VALUE-50,Double.MAX_VALUE-50), new Vector(1,1), 50, 800, Math.PI/2);
		mutableWorld1.addAsCollidable(mutableCollidable1);
		mutableWorld1.addAsCollidable(mutableCollidable2);
		mutableCollidable3 = new Ship(new Vector(200,200), new Vector(-1,-1), 50, 800, Math.PI/2);
		
		mutableWorld2 = new World(100,100);
		mutableCollidable7 = new Ship(new Vector(20,50), new Vector(-1,1), 10, 10,0);
		mutableCollidable8 = new Ship(new Vector(80,50), new Vector(1,1), 10, 10,0);
		mutableCollidable9 = new Ship(new Vector(50,20), new Vector(1,-1), 10, 10,0);
		mutableCollidable10 = new Ship(new Vector(50,80), new Vector(1,1), 10, 10,0);
		mutableCollidable11 = new Ship(new Vector(50,50), new Vector(0,0), 10, 10,0);
		mutableWorld2.addAsCollidable(mutableCollidable7);
		mutableWorld2.addAsCollidable(mutableCollidable8);
		mutableWorld2.addAsCollidable(mutableCollidable9);
		mutableWorld2.addAsCollidable(mutableCollidable10);
		mutableWorld2.addAsCollidable(mutableCollidable11);
	}
	
	// terminate
	
	@Test
	public void testTerminate() {
		World oldWorld = mutableCollidable1.getWorld();
		mutableCollidable1.terminate();
		assertTrue(mutableCollidable1.isTerminated());
		assertFalse(oldWorld.hasAsCollidable(mutableCollidable1));
	}
	
	// position
	
	@Test
	public void testGetPosition() {
		assertTrue(collidable1.getPosition().equals(new Vector(100,50)));
	}
	
	// canHaveAsPosition: collidable not attached to a world.
	
	@Test
	public void testCanHaveAsPosition_NoWorld_NullCase() {
		assertFalse(collidable2.canHaveAsPosition(null));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCanHaveAsPosition_NoWorld_NaNCase() {
		collidable2.canHaveAsPosition(new Vector(Double.NaN,Double.NaN));
	}
	
	@Test
	public void testCanHaveAsPosition_NoWorld_LegalCase() {
		assertTrue(collidable2.canHaveAsPosition(new Vector(500,500)));
	}
	
	@Test
	public void testCanHaveAsPosition_NoWorld_NegativeCase1() {
		assertFalse(collidable2.canHaveAsPosition(new Vector(-1,-1)));
	}
	
	@Test
	public void testCanHaveAsPosition_NoWorld_NegativeCase2() {
		assertFalse(collidable2.canHaveAsPosition(new Vector(-1,10)));
	}
	
	@Test
	public void testCanHaveAsPosition_NoWorld_NegativeCase3() {
		assertFalse(collidable2.canHaveAsPosition(new Vector(10,-1)));
	}
	
	@Test
	public void testCanHaveAsPosition_NoWorld_ZeroCase() {
		assertFalse(collidable2.canHaveAsPosition(new Vector(0,0)));
	}
	
	@Test
	public void testCanHaveAsPosition_NoWorld_LowerBorderCase1() {
		assertTrue(collidable2.canHaveAsPosition(new Vector(collidable2.getRadius(),100)));
	}
	
	@Test
	public void testCanHaveAsPosition_NoWorld_LowerBorderCase2() {
		assertTrue(collidable2.canHaveAsPosition(new Vector(100,collidable2.getRadius())));
	}
	
	@Test
	public void testCanHaveAsPosition_NoWorld_UpperBorderCase1() {
		assertTrue(collidable2.canHaveAsPosition(new Vector(Double.MAX_VALUE-collidable2.getRadius(),100)));
	}
	
	@Test
	public void testCanHaveAsPosition_NoWorldUpperBorderCase2() {
		assertTrue(collidable2.canHaveAsPosition(new Vector(100,Double.MAX_VALUE-collidable2.getRadius())));
	}
	
	// canHaveAsPosition: collidable attached to a world.
	
	@Test
	public void testCanHaveAsPosition_NullCase() {
		assertFalse(collidable1.canHaveAsPosition(null));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCanHaveAsPosition_NaNCase() {
		collidable1.canHaveAsPosition(new Vector(Double.NaN,Double.NaN));
	}
	
	@Test
	public void testCanHaveAsPosition_LegalCase() {
		assertTrue(collidable1.canHaveAsPosition(new Vector(500,500)));
	}
	
	@Test
	public void testCanHaveAsPosition_NegativeCase1() {
		assertFalse(collidable1.canHaveAsPosition(new Vector(-1,-1)));
	}
	
	@Test
	public void testCanHaveAsPosition_NegativeCase2() {
		assertFalse(collidable1.canHaveAsPosition(new Vector(-1,10)));
	}
	
	@Test
	public void testCanHaveAsPosition_NegativeCase3() {
		assertFalse(collidable1.canHaveAsPosition(new Vector(10,-1)));
	}
	
	@Test
	public void testCanHaveAsPosition_ZeroCase() {
		assertFalse(collidable1.canHaveAsPosition(new Vector(0,0)));
	}
	
	@Test
	public void testCanHaveAsPosition_LowerBorderCase1() {
		assertTrue(collidable1.canHaveAsPosition(new Vector(collidable1.getRadius(),100)));
	}
	
	@Test
	public void testCanHaveAsPosition_LowerBorderCase2() {
		assertTrue(collidable1.canHaveAsPosition(new Vector(100,collidable1.getRadius())));
	}
	
	@Test
	public void testCanHaveAsPosition_UpperBorderCase1() {
		assertTrue(collidable1.canHaveAsPosition(new Vector(collidable1.getWorld().getWidth()-collidable1.getRadius(),100)));
	}
	
	@Test
	public void testCanHaveAsPosition_UpperBorderCase2() {
		assertTrue(collidable1.canHaveAsPosition(new Vector(100,collidable1.getWorld().getHeight()-collidable1.getRadius())));
	}
	
	// velocity
	
	@Test
	public void testGetVelocity() {
		assertTrue(collidable1.getVelocity().equals(new Vector(5,10)));
	}
	
	@Test
	public void testSetSpeedLimit_LegalCase() {
		mutableCollidable1.setSpeedLimit(150);
		assertEquals(mutableCollidable1.getSpeedLimit(),150,Util.EPSILON);
	}
	
	@Test
	public void testSetSpeedLimit_IllegalCase() {
		mutableCollidable1.setSpeedLimit(150);
		mutableCollidable1.setSpeedLimit(400000);
		assertEquals(mutableCollidable1.getSpeedLimit(),150,Util.EPSILON);
	}
	
	// canHaveAsSpeedLimit
	
	@Test
	public void testCanHaveAsSpeedLimit_NaNCase() {
		assertFalse(collidable1.canHaveAsSpeedLimit(Double.NaN));
	}
	
	@Test
	public void testCanHaveAsSpeedLimit_LegalCase() {
		assertTrue(collidable1.canHaveAsSpeedLimit(250));
	}
	
	@Test
	public void testCanHaveAsSpeedLimit_MaxCase() {
		assertFalse(collidable1.canHaveAsSpeedLimit(400000));
	}
	
	@Test
	public void testCanHaveAsSpeedLimit_NegativeCase() {
		assertFalse(collidable1.canHaveAsSpeedLimit(-1));
	}
	
	// canHaveAsVelocity
	
	@Test
	public void testCanHaveAsVelocity_NullCase() {
		assertFalse(collidable1.canHaveAsVelocity(null));
	}

	@Test(expected=TimesOverflowException.class)
	public void testCanHaveAsVelocity_OverFlowCase() {
		assertFalse(collidable1.canHaveAsVelocity(new Vector(Double.MAX_VALUE,Double.MAX_VALUE)));
	}
	
	@Test
	public void testCanHaveAsVelocity_MaxCase() {
		assertFalse(collidable1.canHaveAsVelocity(new Vector(400000,400000)));
	}
	
	@Test
	public void testCanHaveAsVelocity_MinCase() {
		assertTrue(collidable1.canHaveAsVelocity(new Vector(0,0)));
	}

	// minRadius
	
	@Test
	public void testSetMinRadius_LegalCase() {
		Collidable.setMinRadius(25);
		assertEquals(Collidable.getMinRadius(),25,Util.EPSILON);
		Collidable.setMinRadius(0);
	}
	
	@Test
	public void testGetMinRadius() {
		assertEquals(Collidable.getMinRadius(),0,Util.EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetMinRadius_IllegalCase() {
		Collidable.setMinRadius(-25);
	}
	
	@Test 
	public void testIsValidMinRadius_NaNCase() {
		assertFalse(Collidable.isValidMinRadius(Double.NaN));
	}
	
	@Test 
	public void testIsValidMinRadius_LegalCase() {
		assertTrue(Collidable.isValidMinRadius(50));
	}
	
	@Test 
	public void testIsValidMinRadius_IllegalCase() {
		assertFalse(Collidable.isValidMinRadius(-1));
	}
	
	// radius
	
	@Test
	public void testGetRadius() {
		assertEquals(collidable1.getRadius(),30,Util.EPSILON);
	}
	
	@Test
	public void testCanHaveAsRadius_NaNCase() {
		assertFalse(collidable1.canHaveAsRadius(Double.NaN));
	}
	
	@Test
	public void testCanHaveAsRadius_LegalCase() {
		assertTrue(collidable1.canHaveAsRadius(50));
	}
	
	@Test
	public void testCanHaveAsRadius_IllegalCase() {
		Collidable.setMinRadius(1);
		assertFalse(collidable1.canHaveAsRadius(0));
	}
	
	@Test
	public void testCanHaveAsRadius_NegativeCase() {
		assertFalse(collidable1.canHaveAsRadius(-10));
	}
	
	// world
	
	@Test
	public void testGetWorld() {
		assertEquals(collidable1.getWorld(),world1);
	}
		
	// canHaveAsWorld 
	
	@Test
	public void testCanHaveAsWorld_LegalCase() {
		assertTrue(collidable2.canHaveAsWorld(world1));
	}
	
	
	@Test
	public void testCanHaveAsWorld_LegalCase2() {
		assertTrue(collidable1.canHaveAsWorld(world2));
	}

	@Test
	public void testCanHaveAsWorld_NullCase() {
		assertTrue(collidable2.canHaveAsWorld(null));
	}
	//REMINDER: collidable1 can still have another world as its world, but the precondition of setWorld 
	//			prohibits this if the collidable is already attached to another world.
	
	// hasProperWorld
	
	@Test
	public void testHasProperWorld_LegalCase() {
		assertTrue(collidable1.hasProperWorld());
	}
	
	@Test
	public void testHasProperWorld_LegalCase2() {
		assertTrue(collidable2.hasProperWorld());
	}
	
	// move
	
	@Test 
	public void testMove_LegalZeroCase() {
		Vector oldPosition = mutableCollidable1.getPosition();
		mutableCollidable1.move(0);
		assertEquals(mutableCollidable1.getPosition(),oldPosition);
	}
	
	@Test 
	public void testMove_LegalOneCase() {
		Vector oldPosition = mutableCollidable1.getPosition();
		mutableCollidable1.move(1);
		assert(mutableCollidable1.getPosition().equals(oldPosition.add(mutableCollidable1.getVelocity()).scale(1)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMove_IllegalCase() {
		mutableCollidable1.move(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMove_NaNCase() {
		mutableCollidable1.move(Double.NaN);
	}
	
	@Test
	public void testMove_InfinityCase1() {
		Vector oldPosition = mutableCollidable1.getPosition();
		mutableCollidable1.move(Double.POSITIVE_INFINITY);
		assertTrue(mutableCollidable1.getPosition().equals(oldPosition));
	}
	
	@Test
	public void testMove_InfinityCase2() {
		Vector oldPosition = mutableCollidable2.getPosition();
		mutableCollidable2.move(10);
		assertTrue(mutableCollidable2.getPosition().equals(oldPosition));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testMove_ThisIsTerminatedCase() {
		terminatedCollidable.move(-1);
	}
	
	// isValidTime
	
	@Test
	public void testIsValidTime_LegalZeroCase() {
		assertTrue(Collidable.isValidTime(0));
	}
	
	@Test
	public void testIsValidTime_LegalOneCase() {
		assertTrue(Collidable.isValidTime(1));
	}
	
	@Test
	public void testIsValidTime_IllegalCase() {
		assertFalse(Collidable.isValidTime(-1));
	}
	
	@Test
	public void testIsValidTime_LegalCase() {
		assertTrue(Collidable.isValidTime(10));
	}
	
	@Test
	public void testIsValidTime_NaNCase() {
		assertFalse(Collidable.isValidTime(Double.NaN));
	}
	
	// overlap
	
	@Test
	public void testOverlap_TrueCase() {
		assertTrue(collidable1.overlap(collidable9));
	}
	
	@Test
	public void testOverlap_FalseCase() {
		assertFalse(collidable1.overlap(collidable3));
	}
	
	@Test
	public void testOverlap_ThisCase() {
		assertTrue(collidable1.overlap(collidable1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testOverlap_NullCase() {
		collidable1.overlap(null);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testOverlap_ThisIsTerminatedCase() {
		terminatedCollidable.overlap(collidable1);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testOverlap_OtherIsTerminatedCase() {
		collidable1.overlap(terminatedCollidable);
	}
	
	// getDistanceBetween
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetDistanceBetween_NullCase() {
		collidable1.getDistanceBetween(null);
	}
	
	@Test
	public void testGetDistanceBetween_ThisCase() {
		assertEquals(collidable1.getDistanceBetween(collidable1),0,Util.EPSILON);
	}
	
	@Test
	public void testGetDistanceBetween_LegalCase() {
		assertFalse(collidable1.overlap(collidable3));
	}
	
	@Test
	public void testGetDistanceBetween_OverlapCase() {
		assertTrue(collidable1.overlap(collidable7));
	}
	
	@Test
	public void testGetDistanceBetween_OverflowCase() {
		assertEquals(collidable1.getDistanceBetween(collidable6),Double.POSITIVE_INFINITY,Util.EPSILON);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testGetDistanceBetween_ThisIsTerminatedCase() {
		terminatedCollidable.getDistanceBetween(collidable1);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testGetDistanceBetween_OtherIsTerminatedCase() {
		collidable1.getDistanceBetween(terminatedCollidable);
	}
	
	// overlapWithBoundary
	
	@Test
	public void testOverlapWithBoundary_TrueCase() {
		assertTrue(collidable8.overlapWithBoundary());
	}
	
	@Test
	public void testOverlapWithBoundary_FalseCase() {
		assertFalse(collidable1.overlapWithBoundary());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testOverlapWithBoundary_ThisIsTerminatedCase() {
		terminatedCollidable.overlapWithBoundary();
	}
	
	// getDistanceToClosestBoundary
	
	@Test
	public void testGetDistanceToClosestBoundary_LegalCase() {
		assertFalse(collidable1.overlapWithBoundary());
	}
	
	@Test
	public void testGetDistanceToClosestBoundary_OverlapCase() {
		assertTrue(collidable8.overlapWithBoundary());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testGetDistanceToClosestBoundary_ThisIsTerminatedCase() {
		terminatedCollidable.getDistanceToClosestBoundary();
	}
	
	@Test
	public void testGetDistanceToClosestBoundary_NotInAWorldCase() {
		assertEquals(collidable2.getDistanceToClosestBoundary(),Double.POSITIVE_INFINITY,Util.EPSILON);
	}
	
	// getTimeToCollision 
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetTimeToCollision_NullCase() {
		collidable1.getTimeToCollision(null);
	}
	
	@Test
	public void testGetTimeToCollision_CollisionCase1() {
		double result = mutableCollidable1.getTimeToCollision(mutableCollidable3);
		mutableCollidable1.move(result);
		mutableCollidable3.move(result);
		assertEquals(mutableCollidable1.getDistanceBetween(mutableCollidable3),0,Util.EPSILON);
	}
	
	@Test
	public void testGetTimeToCollision_CollisionCase2() {
		assertFalse(mutableCollidable1.overlap(mutableCollidable3));
	}
	
	@Test
	public void testGetTimeToCollision_NoCollisionCase() { // standard no collision case
		assertEquals(Double.POSITIVE_INFINITY,collidable1.getTimeToCollision(collidable4),Util.EPSILON);
	}
	
	@Test
	public void testGetTimeToCollision_OverlapCase() { // ships overlap
		assertEquals(Double.POSITIVE_INFINITY,collidable1.getTimeToCollision(collidable2),Util.EPSILON);
	}
	
	@Test
	public void testGetTimeToCollision_OnOneLineCase() { // ships travel the same speed in the same direction without overlapping
		assertEquals(Double.POSITIVE_INFINITY,collidable1.getTimeToCollision(collidable5),Util.EPSILON);
	}
	
	@Test
	public void testGetTimeToCollision_OverflowCase() {	// the calculation overflows
		assertEquals(Double.POSITIVE_INFINITY,collidable6.getTimeToCollision(collidable5),Util.EPSILON);
	}
	
	// getTimeToCollisionWithBoundary
	
	@Test
	public void testGetTimeToCollisionWithBoundary_ThisNotInWorldCase() {
		assertEquals(Double.POSITIVE_INFINITY,collidable2.getTimeToCollisionWithBoundary(), Util.EPSILON);
	}
	
	@Test
	public void testGetTimeToCollisionWithBoundary_TerminatedCase() {
		assertEquals(Double.POSITIVE_INFINITY,terminatedCollidable.getTimeToCollisionWithBoundary(), Util.EPSILON);
	}
	
	@Test
	public void testGetTimeToCollisionWithBoundary_LeftLegalCase() {
		double time = mutableCollidable7.getTimeToCollisionWithBoundary();
		mutableCollidable7.move(time);
		assertEquals(mutableCollidable7.getDistanceToClosestBoundary(),0.0,Util.EPSILON);
	}
	
	@Test
	public void testGetTimeToCollisionWithBoundary_RightLegalCase() {
		double time = mutableCollidable8.getTimeToCollisionWithBoundary();
		mutableCollidable8.move(time);
		assertEquals(mutableCollidable8.getDistanceToClosestBoundary(),0.0,Util.EPSILON);
	}
	
	@Test
	public void testGetTimeToCollisionWithBoundary_BottomLegalCase() {
		double time = mutableCollidable9.getTimeToCollisionWithBoundary();
		mutableCollidable9.move(time);
		assertEquals(mutableCollidable9.getDistanceToClosestBoundary(),0.0,Util.EPSILON);
	}
	
	@Test
	public void testGetTimeToCollisionWithBoundary_TopLegalCase() {
		double time = mutableCollidable10.getTimeToCollisionWithBoundary();
		mutableCollidable10.move(time);
		assertEquals(mutableCollidable10.getDistanceToClosestBoundary(),0.0,Util.EPSILON);
	}
	
	@Test
	public void testGetTimeToCollisionWithBoundary_ZeroVelocityCase() {
		assertEquals(Double.POSITIVE_INFINITY, mutableCollidable11.getTimeToCollisionWithBoundary(),Util.EPSILON);
	}
	
}