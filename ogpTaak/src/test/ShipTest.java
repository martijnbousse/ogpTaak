package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import gameObjects.Bullet;
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
 * @version	2.0
 *
 */
public class ShipTest {
	
	private static World world1;
	private static Ship ship1;
	private static Ship ship2;
	private static Ship terminatedShip;
	private static Bullet bullet1;
	private static Bullet bullet2;
	private static Bullet terminatedBullet;
	
	private World mutableWorld1;
	private Ship mutableShip1;
	private Bullet mutableBullet1;
	
	private Ship mutableShip2;
	private Ship mutableTerminatedShip;
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		world1 = new World();
		ship1 = new Ship();
		world1.addAsCollidable(ship1);
		bullet1 = new Bullet(new Vector(100,100), new Vector(10,10), 10);
		ship1.addAsBullet(bullet1);
		
		ship2 = new Ship();
		bullet2 = new Bullet(new Vector(100,100), new Vector(10,10), 10);
		
		terminatedShip = new Ship();
		terminatedShip.terminate();

		terminatedBullet = new Bullet(new Vector(100,100), new Vector(0,0),10);
		ship1.addAsBullet(terminatedBullet);
		terminatedBullet.terminate();
	}

	@Before
	public void setUp() throws Exception {
		mutableWorld1 = new World();
		mutableShip1 = new Ship();
		mutableWorld1.addAsCollidable(mutableShip1);
		mutableBullet1 = new Bullet(new Vector(100,100), new Vector(0,0),10);
		
		mutableShip2 = new Ship();
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
		mutableShip2.setDirection(Math.PI);
		assertEquals(mutableShip2.getDirection(),Math.PI,Util.EPSILON);
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
		assertEquals(mutableShip2.getMass(),10,Util.EPSILON);
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
		assertEquals(mutableShip2.getAcceleration(), 1.1*Math.pow(10,21)/1000/10, Util.EPSILON);
	}
	
	// enabling thruster
	
	@Test
	public void testSetThrusterEnabled() {
		mutableShip2.setThrusterEnabled(true);
		assertTrue(mutableShip2.isThrusterEnabled());
	}
	
	@Test
	public void testIsThrusterEnabled() {
		assertFalse(mutableShip2.isThrusterEnabled());
	}
	
	// thrust
	
	@Test
	public void testThrust_LegalCase() {
		mutableShip2.setThrusterEnabled(true);
		mutableShip2.thrust(1E-16);
		assertTrue(mutableShip2.getVelocity().equals(new Vector(mutableShip2.getAcceleration()*Math.cos(1),mutableShip2.getAcceleration()*Math.sin(1)).scale(1E-16)));
	}
	
	@Test
	public void testThrust_NaNCase() {
		mutableShip2.setThrusterEnabled(true);
		mutableShip2.thrust(Double.NaN);
		assertTrue(mutableShip2.getVelocity().equals(new Vector(0.0,0.0)));
	}
	
	@Test
	public void testThrust_NegativeAmountCase() {
		mutableShip2.setThrusterEnabled(true);
		mutableShip2.thrust(-1.0);
		assertTrue(mutableShip2.getVelocity().equals(new Vector(0.0,0.0)));
	}
	
	@Test
	public void testThrust_TooHighAmountCase() {
		mutableShip2.setThrusterEnabled(true);
		mutableShip2.thrust(4000000000000000000000000000000000.0);
		assertTrue(mutableShip2.getVelocity().equals(new Vector(300000.0*Math.cos(1),300000.0*Math.sin(1))));
	}
	
	@Test(expected = IllegalStateException.class)
	public void testThrust_ThisIsTerminatedCase() {
		mutableTerminatedShip.setThrusterEnabled(true);
		mutableTerminatedShip.thrust(1);
	}
	
	// turn
	
	@Test
	public void testTurn_LegalCase() {
		mutableShip2.turn(Math.PI);
		assertEquals(mutableShip2.getDirection(),1+Math.PI,Util.EPSILON);
	}
	
	@Test
	public void testCanAcceptForTurn_LegalCase() {
		assertTrue(ship1.canAcceptForTurn(1));
	}
	
	@Test
	public void testCanAcceptForTurn_IllegalCase() {
		assertFalse(ship1.canAcceptForTurn(10));
	}
	
	// canHaveAsBullet
	
	@Test 
	public void testCanHaveAsBullet_LegalCase() {
		assertTrue(ship1.canHaveAsBullet(bullet1));
	}
	
	@Test
	public void testCanHaveAsBullet_NullCase() {
		assertFalse(ship1.canHaveAsBullet(null));
	}
	
	@Test 
	public void testCanHaveAsBullet_ThisNotAttachedCase() {
		assertFalse(ship2.canHaveAsBullet(bullet1));
	}
	
	@Test
	public void testCanHaveAsBullet_ThisIsTerminatedCase() {
		assertFalse(terminatedShip.canHaveAsBullet(bullet1));
	}
	
	@Test
	public void testCanHaveAsBullet_OtherIsTerminatedCase() {
		assertFalse(ship1.canHaveAsBullet(terminatedBullet));
	}
	
	// getAllBullets
	
	@Test
	public void testGetAllBullets_NullReference() {
		assertFalse(world1.getAllBullets().contains(null));
	}
	
	@Test
	public void testGetAllBullets_WorldReference() {
		
		for(Bullet bullet : world1.getAllBullets()) {
			assertEquals(world1.getAllBullets().contains(bullet),world1.hasAsCollidable(bullet));
		}
	}
	
	// hasAsBullet 
	
	@Test
	public void testHasAsBullet_LegalCase() {
		assertTrue(ship1.hasAsBullet(bullet1));
	}
	
	@Test
	public void testHasAsBullet_IllegalCase1() {
		assertFalse(ship2.hasAsBullet(bullet1));
	}
	
	@Test
	public void testHasAsBullet_IllegalCase2() {
		assertFalse(ship1.hasAsBullet(bullet2));
	}
	
	@Test
	public void testHasAsBullet_NullCase() {
		assertFalse(ship1.hasAsBullet(null));
	}
	
	// hasProperBullets
	
	@Test
	public void testHasProperBullets() {
		for(Bullet bullet : world1.getAllBullets()) {
			assertTrue(bullet.getWorld().equals(ship1));
			assertTrue(ship1.hasAsBullet(bullet1));
		}
	}
	
	// addAsBullet
	
	@Test
	public void testAddAsBullet_LegalCase() {
		mutableShip1.addAsBullet(mutableBullet1);
		// associatie wordt consistent opgebouwd.
		assertTrue(mutableShip1.hasAsBullet(mutableBullet1));
		assertEquals(mutableBullet1.getSource(),mutableShip1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddAsBullet_NullCase() {
		mutableShip1.addAsBullet(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddAsBullet_ThisIsNotAttachedCase() {
		mutableShip2.addAsBullet(mutableBullet1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddAsBullet_ThisIsTerminatedCase() {
		terminatedShip.addAsBullet(mutableBullet1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddAsBullet_AlreadyAttachedCase() {
		mutableShip1.addAsBullet(mutableBullet1);
		mutableShip2.addAsBullet(mutableBullet1);
	}
	
	// removeAsBullet
	
	@Test
	public void testRemoveAsBullet_LegalCase() {
		mutableShip1.addAsBullet(mutableBullet1);
		mutableShip1.removeAsBullet(mutableBullet1);
		// associatie wordt consistent afgebouwd.
		assertFalse(mutableShip1.hasAsBullet(mutableBullet1));
		assertEquals(mutableBullet1.getSource(),null);
	}
	
	@Test
	public void testRemoveAsBullet_NotInSetCase() {
		mutableShip1.removeAsBullet(mutableBullet1);
	}
		
	// canFireBullets
	
	@Test 
	public void testCanFireBullets_LegalCase() {
		assertTrue(mutableShip1.canFireBullets());
	}
	
	@Test
	public void testCanFireBullets_IllegalCase() {
		mutableShip1.fireBullet();
		mutableShip1.fireBullet();
		mutableShip1.fireBullet();
		assertFalse(mutableShip1.canFireBullets());
	}
	
	// fireBullet
	
	@Test
	public void testFireBullet() {
		mutableShip1.fireBullet();
		assertEquals(mutableShip1.getWorld().getNbBullets(),1);
		ArrayList<Bullet> bullets = new ArrayList<Bullet>(mutableShip1.getAllBullets());
		Bullet check = bullets.get(0);
		assertEquals(check.getSource(),mutableShip1);
	}



}
