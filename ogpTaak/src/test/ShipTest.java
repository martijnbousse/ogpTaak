package test;

import static org.junit.Assert.*;
import gameObjects.Ship;
import gameObjects.World;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import support.Vector;


import asteroids.Util;

/**
 * A class collecting tests for the class of ships.
 * 
 * REMINDER: 	collide and collidesWith in all its versions won't be tested, because they only use already tested methods. 
 * 				They are only used do make a distinction between the dynamic type of the collidable.
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
	private Ship mutableTerminatedShip;

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
		mutableTerminatedShip = new Ship();
		mutableTerminatedShip.terminate();
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
	
	// mass
	
	@Test
	public void testGetMass() {
		assertEquals(mutableShip1.getMass(),10,Util.EPSILON);
	}
	
	@Test
	public void testIsValidMass_NaNCase() {
		assertFalse(Ship.isValidMass(Double.NaN));
	}
	
	@Test
	public void testIsValidMass_NegativeCase() {
		assertFalse(Ship.isValidMass(-1));
	}
	
	@Test
	public void testIsValidMass_ZeroCase() {
		assertFalse(Ship.isValidMass(0));
	}
	
	@Test
	public void testIsValidMass_LegalCase() {
		assertTrue(Ship.isValidMass(5));
	}
	
	// thruster amount
	
	@Test
	public void testGetThrusterAmount() {
		assertEquals(Ship.getThrusterAmount(),1.1*Math.pow(10,21),Util.EPSILON);
	}
	
	@Test
	public void testIsValidThrusterAmount_NaNCase() {
		assertFalse(Ship.isValidThrusterAmount(Double.NaN));
	}
	
	@Test
	public void testIsValidThrusterAmount_NegativeCase() {
		assertFalse(Ship.isValidThrusterAmount(-5));
	}
	
	@Test
	public void testIsValidThrusterAmount_ZeroCase() {
		assertFalse(Ship.isValidThrusterAmount(0));
	}
	
	@Test
	public void testIsValidThrusterAmount_LegalCase() {
		assertTrue(Ship.isValidThrusterAmount(25));
	}
	
	// acceleration
	
	@Test
	public void testGetAcceleration() {
		assertEquals(mutableShip1.getAcceleration(), 1.1*Math.pow(10,21)/1000/10, Util.EPSILON);
	}
	
	// enabling thruster
	
	@Test
	public void testSetThrusterEnabled() {
		mutableShip1.setThrusterEnabled(true);
		assertTrue(mutableShip1.isThrusterEnabled());
	}
	
	@Test
	public void testIsThrusterEnabled() {
		assertFalse(mutableShip1.isThrusterEnabled());
	}
	
	// thrust
	
	@Test
	public void testThrust_LegalCase() {
		mutableShip1.setThrusterEnabled(true);
		mutableShip1.thrust(1E-16);
		assertTrue(mutableShip1.getVelocity().equals(new Vector(mutableShip1.getAcceleration()*Math.cos(1),mutableShip1.getAcceleration()*Math.sin(1)).scale(1E-16)));
	}
	
	@Test
	public void testThrust_NaNCase() {
		mutableShip1.setThrusterEnabled(true);
		mutableShip1.thrust(Double.NaN);
		assertTrue(mutableShip1.getVelocity().equals(new Vector(0.0,0.0)));
	}
	
	@Test
	public void testThrust_NegativeAmountCase() {
		mutableShip1.setThrusterEnabled(true);
		mutableShip1.thrust(-1.0);
		assertTrue(mutableShip1.getVelocity().equals(new Vector(0.0,0.0)));
	}
	
	@Test
	public void testThrust_TooHighAmountCase() {
		mutableShip1.setThrusterEnabled(true);
		mutableShip1.thrust(4000000000000000000000000000000000.0);
		assertTrue(mutableShip1.getVelocity().equals(new Vector(300000.0*Math.cos(1),300000.0*Math.sin(1))));
	}
	
	@Test(expected = IllegalStateException.class)
	public void testThrust_ThisIsTerminatedCase() {
		mutableTerminatedShip.setThrusterEnabled(true);
		mutableTerminatedShip.thrust(1);
	}
	
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
	
	// TODO: fireBullets moet anders getest worden .. aparte test klasse met:
	// (bidirectionele relatie testen) addasbullet, removeasbullet, hasproperbullet, canhaveasbullet allemaal apart checken
	
	@Test
	public void testCanFireBullets_NoWorldAttachedCase() {
		assertFalse(ship2.canFireBullets());
	}
	@Test
	public void testCanFireBullets_ThisIsTerminated() {
		assertFalse(terminatedShip.canFireBullets());
	}
	
	// fireBullet
	
	@Test
	public void testFireBullet_LegalCase() {
		ship1.fireBullet();
		assertEquals(ship1.getWorld().getNbBullets(),1);
	}

}
