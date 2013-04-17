package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import collidable.Ship;
import asteroids.Util;
import asteroids.World;

/**
 * A class collecting tests for the class of ships.
 * 
 * @author 	Martijn Boussé, Wout Vekemans
 * @version	1.0
 *
 */
public class ShipTest {
	
	private static World world1;
	private static Ship ship1;
	private static Ship ship2;
	private static Ship terminatedShip;
	
	private Ship mutableShip1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		world1 = new World();
		ship1 = new Ship();
		ship2 = new Ship();
		terminatedShip = new Ship();
		world1.addAsCollidable(ship1);
		terminatedShip.terminate();
	}

	@Before
	public void setUp() throws Exception {
		mutableShip1 = new Ship();
	}
	
	// direction
	
	@Test
	public void testGetDirection() {
		assertEquals(ship1.getDirection(),1,Util.EPSILON);
	}

	@Test
	public void testSetDirection_LegalCase() {
		mutableShip1.setDirection(Math.PI);
		assertEquals(mutableShip1.getDirection(),Math.PI,Util.EPSILON);
	}
	
	@Test
	public void testIsValidDirection_LegalCase() {
		assertTrue(Ship.isValidDirection(1));
	}
	
	@Test
	public void testIsValidDirection_ZeroCase() {
		assertTrue(Ship.isValidDirection(0));
	}
	
	@Test
	public void testIsValidDirection_MaxCase() {
		assertTrue(Ship.isValidDirection(2*Math.PI));
	}
	
	@Test
	public void testIsValidDirection_OutOfBoundsCase1() {
		assertFalse(Ship.isValidDirection(-1));
	}
	
	@Test
	public void testIsValidDirection_OutOfBoundsCase2() {
		assertFalse(Ship.isValidDirection(3*Math.PI));
	}
	
	@Test
	public void testIsValidDirection_NaNCase() {
		assertFalse(Ship.isValidDirection(Double.NaN));
	}
	
	// mass TODO: testen
	
	// thrust, accelaration, al dieje bazaar TODO: testen
	
//	@Test
//	public void testThrust_LegalCase() {
//		mutableShip.thrust(1.0);
//		assertTrue(mutableShip.getVelocity().equals(new Vector(1.0,0.0)));
//	}
//	
//	@Test
//	public void testThrust_NaNCase() {
//		mutableShip.thrust(Double.NaN);
//		assertTrue(mutableShip.getVelocity().equals(new Vector(0.0,0.0)));
//	}
//	
//	@Test
//	public void testThrust_NegativeAmountCase() {
//		mutableShip.thrust(-1.0);
//		assertTrue(mutableShip.getVelocity().equals(new Vector(0.0,0.0)));
//	}
//	
//	@Test
//	public void testThrust_TooHighAmountCase() {
//		mutableShip.thrust(400000.0);
//		assertTrue(mutableShip.getVelocity().equals(new Vector(300000.0,0.0)));
//	}
	
	// turn
	
	@Test
	public void testTurn_LegalCase() {
		mutableShip1.turn(Math.PI);
		assertEquals(mutableShip1.getDirection(),1+Math.PI,Util.EPSILON);
	}
	
	@Test
	public void testCanAcceptForTurn_LegalCase() {
		assertTrue(ship1.canAcceptForTurn(1));
	}
	
	@Test
	public void testCanAcceptForTurn_IllegalCase() {
		assertFalse(ship1.canAcceptForTurn(10));
	}
		
	// canFireBullets
	
	@Test 
	public void testCanFireBullets_LegalCase() {
		assertTrue(ship1.canFireBullets());
	}
	
	@Test
	public void testCanFireBullets_NoWorldAttachedCase() {
		assertFalse(ship2.canFireBullets());
	}
	@Test
	public void testCanFireBullets_ThisIsTerminated() {
		assertFalse(terminatedShip.canFireBullets());
	}
	
	// fireBullet TODO: testen
	
	// collidesWith(Collidable collidable) TODO
	
	// collidesWith(Ship ship) TODO
	
	// collidesWith(Asteroid asteroid) TODO
	
	// collidesWith(Bullet bullet) TODO
	
	// toString TODO
	
	
}
