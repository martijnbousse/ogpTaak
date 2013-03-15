package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.Ship;
import asteroids.SumOverflowException;
import asteroids.Util;
import asteroids.Vector;

/**
 * A class collecting tests for the class of ships.
 * 
 * @author 	Martijn Boussé, Wout Vekemans
 * @version	1.0
 *
 */
public class ShipTest {
	
	/**
	 * Variable referencing a ship with position (10,5) km, velocity (5,10) km/s, radius 15 km and direction pi/2 rad.
	 */
	private static Ship ship;
	
	/**
	 * Variable referencing a ship with position (40,30) km, velocity (-10,-10) km/s, radius 10 km and direction pi rad.
	 */
	private static Ship ship2;
	
	/**
	 * Variable referencing a ship with position (-40,-30) km, velocity (-10,-10) km/s, radius 10 km and direction pi rad.
	 */
	private static Ship ship3;
	
	/**
	 * Variable referencing a ship with position (max,max) km, velocity (0,0) km/s, radius 15 km and direction pi/2 rad.
	 */
	private static Ship shipFarAway;
	
	/**
	 * Variable referencing a ship with default settings.
	 */
	private static Ship shipDefault;
	
	/**
	 * Variable referencing a ship with default settings.
	 */
	private Ship mutableShip;
	
	/**
	 * Variable referencing a ship with position (0,0) km, velocity (1,1) km/s, radius 30 km and direction pi/2 rad.
	 */
	private Ship mutableShip2;
	
	/**
	 * Variable referencing a ship with position (max,max) km, velocity (1,1) km/s, radius 30 km and direction pi/2 rad.
	 */
	private Ship mutableShip3;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ship = new Ship(new Vector(10,5), new Vector(5,10), 15, Math.PI/2);
		ship2 = new Ship(new Vector(40,30), new Vector(-10,-10), 10, Math.PI);
		ship3 = new Ship(new Vector(0,0), new Vector(-10,-10), 10, Math.PI);
		shipFarAway = new Ship(new Vector(Double.MAX_VALUE,Double.MAX_VALUE), new Vector(0,0), 15, Math.PI/2);
		shipDefault= new Ship();
	}

	@Before
	public void setUp() throws Exception {
		mutableShip = new Ship();
		mutableShip2 = new Ship(new Vector(0,0), new Vector(1,1), 30, Math.PI/2);
		mutableShip3 = new Ship(new Vector(Double.MAX_VALUE,Double.MAX_VALUE), new Vector(1,1), 30, Math.PI/2);
	}
	
	// position
	
	@Test
	public void testGetPosition() {
		assertTrue(ship.getPosition().equals(new Vector(10,5)));
	}
	
	@Test
	public void testSetPosition_LegalCase() {
		mutableShip.setPosition(new Vector(15,10));
		assertTrue(mutableShip.getPosition().equals(new Vector(15,10))); 
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPosition_IllegalCase() {
		mutableShip.setPosition(null);
	}
	
	// velocity

	@Test
	public void testGetVelocity() {
		assertTrue(ship.getVelocity().equals(new Vector(5,10)));
	}

	@Test
	public void testSetVelocity_LegalCase() {
		mutableShip.setVelocity(new Vector(25,20));
		assertTrue(mutableShip.getVelocity().equals(new Vector(25,20)));
	}
	
	@Test	
	public void testSetVelocity_NullCase() {
		mutableShip.setVelocity(null);  
		assertTrue(mutableShip.getVelocity().equals(new Vector(0,0)));
	}
	
	@Test
	public void testSetVelocity_IllegalCase() {
		mutableShip.setVelocity(new Vector(300000,300000));
		assertTrue(mutableShip.getVelocity().equals(new Vector(0,0)));
	}

	@Test
	public void testSetSpeedLimit_LegalCase() {
		mutableShip.setSpeedLimit(150);
		assertTrue(Util.fuzzyEquals(mutableShip.getSpeedLimit(),150));
	}
	
	@Test
	public void testSetSpeedLimit_IllegalCase() {
		mutableShip.setSpeedLimit(150);
		mutableShip.setSpeedLimit(400000);
		assertTrue(Util.fuzzyEquals(mutableShip.getSpeedLimit(),150));
	}

	// direction
	
	@Test
	public void testGetDirection() {
		assertTrue(Util.fuzzyEquals(ship.getDirection(),Math.PI/2));
	}

	@Test
	public void testSetDirection_LegalCase() {
		mutableShip.setDirection(Math.PI);
		assertTrue(Util.fuzzyEquals(mutableShip.getDirection(),Math.PI));
	}

	// radius
	
	@Test
	public void testSetMinRadius_LegalCase() {
		Ship.setMinRadius(25);
		assertTrue(Util.fuzzyEquals(Ship.getMinRadius(),25));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetMinRadius_IllegalCase() {
		Ship.setMinRadius(-25);
	}

	@Test
	public void testGetRadius() {
		assertTrue(Util.fuzzyEquals(ship.getRadius(),15));
	}
	
	// move
	
	@Test 
	public void testMove_LegalCaseZero() {
		Vector oldPosition = mutableShip2.getPosition();
		mutableShip2.move(0);
		assertEquals(mutableShip2.getPosition(),oldPosition);
	}
	
	@Test 
	public void testMove_LegalCaseOne() {
		Vector oldPosition = mutableShip2.getPosition();
		mutableShip2.move(1);
		assert(mutableShip2.getPosition().equals(oldPosition.add(mutableShip2.getVelocity()).scale(1)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMove_IllegalCase() {
		mutableShip2.move(-1);
	}
	
	@Test
	public void testMove_InfinityCase1() {
		Vector oldPosition = mutableShip2.getPosition();
		mutableShip2.move(Double.POSITIVE_INFINITY);
		assertTrue(mutableShip2.getPosition().equals(oldPosition));
	}
	
	@Test
	public void testMove_InfinityCase2() {
		Vector oldPosition = mutableShip3.getPosition();
		mutableShip3.move(10);
		assertTrue(mutableShip3.getPosition().equals(oldPosition));
	}
	
	// turn
	
	@Test
	public void testTurn_LegalCase() {
		mutableShip.turn(Math.PI);
		assertTrue(Util.fuzzyEquals(mutableShip.getDirection(),Math.PI ));
	}
	
	// thrust
	
	@Test
	public void testThrust_LegalCase() {
		mutableShip.thrust(1.0);
		assertTrue(mutableShip.getVelocity().equals(new Vector(1.0,0.0)));
	}
	
	@Test
	public void testThrust_NaNCase() {
		mutableShip.thrust(Double.NaN);
		assertTrue(mutableShip.getVelocity().equals(new Vector(0.0,0.0)));
	}
	
	@Test
	public void testThrust_NegativeAmountCase() {
		mutableShip.thrust(-1.0);
		assertTrue(mutableShip.getVelocity().equals(new Vector(0.0,0.0)));
	}
	
	@Test
	public void testThrust_TooHighAmountCase() {
		mutableShip.thrust(400000.0);
		assertTrue(mutableShip.getVelocity().equals(new Vector(300000.0,0.0)));
	}
	
	// getDistanceBetween
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetDistanceBetween_NullCase() {
		shipDefault.getDistanceBetween(null);
	}
	
	@Test
	public void testGetDistanceBetween_ThisCase() {
		assertTrue(Util.fuzzyEquals(shipDefault.getDistanceBetween(shipDefault),0.0));
	}
	
	@Test
	public void testGetDistanceBetween_LegalCase() {
		assertTrue(Util.fuzzyEquals(shipDefault.getDistanceBetween(ship),Math.sqrt(10.0*10.0+5.0*5.0)-15.0-10.0));
	}
	
	@Test(expected=SumOverflowException.class)
	public void testGetDistanceBetween_OverflowCase() {
		assertTrue(Util.fuzzyEquals(shipDefault.getDistanceBetween(shipFarAway),Double.POSITIVE_INFINITY));
	}
	
	// overlap
	
	@Test
	public void testOverlap_TrueCase() {
		assertEquals(true,shipDefault.overlap(ship));
	}
	
	@Test(expected=SumOverflowException.class)
	public void testOverlap_FalseCase() {
		assertEquals(false,shipDefault.overlap(shipFarAway));
	}
	
	@Test
	public void testOverlap_ThisCase() {
		assertEquals(true,shipDefault.overlap(shipDefault));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testOverlap_IllegalCase() {
		shipDefault.overlap(null);
	}
	
	// getTimeToCollision
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetTimeToCollision_NullCase() {
		shipDefault.getTimeToCollision(null);
	}
	
	@Test
	public void testGetTimeToCollision_CollisionCase() {
		assertTrue(Util.fuzzyEquals(0.5870,ship.getTimeToCollision(ship2)));
	}
	
	@Test
	public void testGetTimeToCollision_NoCollisionCase1() { // standard no collision case
		assertTrue(Util.fuzzyEquals(Double.POSITIVE_INFINITY,ship.getTimeToCollision(ship3)));
	}
	
	@Test
	public void testGetTimeToCollision_NoCollisionCase2() { // ships overlap
		assertTrue(Util.fuzzyEquals(Double.POSITIVE_INFINITY,ship.getTimeToCollision(shipDefault)));
	}
	
	@Test
	public void testGetTimeToCollision_NoCollisionCase3() { // ships travel the same speed in the same direction without overlapping:
		assertTrue(Util.fuzzyEquals(Double.POSITIVE_INFINITY,ship2.getTimeToCollision(ship3)));
	}
	
	// getCollisionPosition
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetCollisionPosition_NullCase() {
		shipDefault.getCollisionPosition(null);
	}
	
	@Test
	public void testGetCollisionPosition_CollisionCase() {
		assertTrue((new Vector(25.6518,18.8259).equals(ship.getCollisionPosition(ship2))));
	}
	
	@Test
	public void testGetCollisionPosition_NoCollisionCase() {
		assertEquals(null,ship.getCollisionPosition(ship3));
	}
	
	
	
}
