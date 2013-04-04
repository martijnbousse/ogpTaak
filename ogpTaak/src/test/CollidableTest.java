package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import collidable.*;

import asteroids.*;

/**
 * A class collecting tests for the class of collidables.
 * 
 * @author 	Martijn Boussé, Wout Vekemans
 * @version	1.0
 *
 */
public class CollidableTest {
	
	private static World world1;
	private static Collidable collidable1;
	private static Collidable collidable2;
	private static Collidable collidable3;
	
	private World mutableWorld1;
	private Collidable mutableCollidable1;
	private Collidable mutableCollidable2;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		world1 = new World();
		collidable1 = new Ship(new Vector(100,50), new Vector(5,10), 30, 1000, Math.PI/2);
		collidable2 = new Ship(new Vector(110,50), new Vector(5,10), 30, 1000, Math.PI/2);
		collidable3 = new Ship(new Vector(200,250), new Vector(-5,-10), 30, 1000, Math.PI/2);
		
		world1.addAsCollidable(collidable1);
	}

	@Before
	public void setUp() throws Exception {
		mutableWorld1 = new World();
		mutableCollidable1 = new Ship(new Vector(100,100), new Vector(1,1), 50, 800, Math.PI/2);
		mutableCollidable2 = new Ship(new Vector(Double.MAX_VALUE-50,Double.MAX_VALUE-50), new Vector(1,1), 50, 800, Math.PI/2);
		mutableWorld1.addAsCollidable(mutableCollidable1);
		mutableWorld1.addAsCollidable(mutableCollidable2); //TODO: meerdere collidables ineens toevoegen? 
	}
	
	// position
	
	@Test
	public void testGetPosition() {
		assertTrue(collidable1.getPosition().equals(new Vector(100,50)));
	}
	
	//TODO: setPosition is protected. Hoe testen?
	
	// velocity
	
	@Test
	public void testGetVelocity() {
		assertTrue(collidable1.getVelocity().equals(new Vector(5,10)));
	}
	
	//TODO: setVelocity is protected. Hoe testen?
	
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
	
	// radius
	
	@Test
	public void testSetMinRadius_LegalCase() {
		Collidable.setMinRadius(25);
		assertEquals(Collidable.getMinRadius(),25,Util.EPSILON);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetMinRadius_IllegalCase() {
		Collidable.setMinRadius(-25);
	}

	@Test
	public void testGetRadius() {
		assertEquals(collidable1.getRadius(),30,Util.EPSILON);
	}
	
	// world
	
	@Test
	public void testGetWorld() {
		assertTrue(collidable1.getWorld() == world1);
	}
	
	//TODO: properWorld, canHaveAsWorld, ... setWorld zou afgeschermd zijn
	
	@Test
	public void testSetWorld() {
		//TODO: implement! belangrijke! consistentie van bindingen aantonen
	}
	
	
	// move
	
	@Test 
	public void testMove_LegalCaseZero() {
		Vector oldPosition = mutableCollidable1.getPosition();
		mutableCollidable1.move(0);
		assertEquals(mutableCollidable1.getPosition(),oldPosition);
	}
	
	@Test 
	public void testMove_LegalCaseOne() {
		Vector oldPosition = mutableCollidable1.getPosition();
		mutableCollidable1.move(1);
		assert(mutableCollidable1.getPosition().equals(oldPosition.add(mutableCollidable1.getVelocity()).scale(1)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMove_IllegalCase() {
		mutableCollidable1.move(-1);
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
	
	// getDistanceBetween //TODO: documentatie aanpassen
	
//	@Test(expected=IllegalArgumentException.class)
//	public void testGetDistanceBetween_NullCase() {
//		shipDefault.getDistanceBetween(null);
//	}
//	
//	@Test
//	public void testGetDistanceBetween_ThisCase() {
//		assertTrue(Util.fuzzyEquals(shipDefault.getDistanceBetween(shipDefault),0.0));
//	}
//	
//	@Test
//	public void testGetDistanceBetween_LegalCase() {
//		assertTrue(Util.fuzzyEquals(shipDefault.getDistanceBetween(ship),Math.sqrt(10.0*10.0+5.0*5.0)-15.0-10.0));
//	}
//	
//	@Test
//	public void testGetDistanceBetween_OverflowCase() {
//		System.out.println(shipDefault.getDistanceBetween(shipFarAway));
//		assertEquals(shipDefault.getDistanceBetween(shipFarAway),Double.POSITIVE_INFINITY,0);
//	}
	
	// getTimeToCollision //TODO: documentatie aanpassen
	
//	@Test(expected=IllegalArgumentException.class)
//	public void testGetTimeToCollision_NullCase() {
//		shipDefault.getTimeToCollision(null);
//	}
//	
//	@Test
//	public void testGetTimeToCollision_CollisionCase() {
//		assertTrue(Util.fuzzyEquals(0.5870,ship.getTimeToCollision(ship2)));
//	}
//	
//	@Test
//	public void testGetTimeToCollision_NoCollisionCase1() { // standard no collision case
//		assertTrue(Util.fuzzyEquals(Double.POSITIVE_INFINITY,ship.getTimeToCollision(ship3)));
//	}
//	
//	@Test
//	public void testGetTimeToCollision_NoCollisionCase2() { // ships overlap
//		assertTrue(Util.fuzzyEquals(Double.POSITIVE_INFINITY,ship.getTimeToCollision(shipDefault)));
//	}
//	
//	@Test
//	public void testGetTimeToCollision_NoCollisionCase3() { // ships travel the same speed in the same direction without overlapping:
//		assertTrue(Util.fuzzyEquals(Double.POSITIVE_INFINITY,ship2.getTimeToCollision(ship3)));
//	}
	
	// getTimeToCollisionWithBoundary //TODO
	
	// getCollisionPosition //TODO: documentatie aanpassen
	
//		@Test(expected=IllegalArgumentException.class)
//		public void testGetCollisionPosition_NullCase() {
//			shipDefault.getCollisionPosition(null);
//		}
//		
//		@Test
//		public void testGetCollisionPosition_CollisionCase() {
//			assertTrue((new Vector(25.6518,18.8259).equals(ship.getCollisionPosition(ship2))));
//		}
//		
//		@Test
//		public void testGetCollisionPosition_NoCollisionCase() {
//			assertEquals(null,ship.getCollisionPosition(ship3));
//		}
	
	// overlap
	
	@Test
	public void testOverlap_TrueCase() {
		assertEquals(true,collidable1.overlap(collidable2));
	}
	@Test
	public void testOverlap_FalseCase() {
		assertEquals(false,collidable1.overlap(collidable3));
	}
	
	@Test
	public void testOverlap_ThisCase() {
		assertEquals(true,collidable1.overlap(collidable1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testOverlap_IllegalCase() {
		collidable1.overlap(null);
	}
}
