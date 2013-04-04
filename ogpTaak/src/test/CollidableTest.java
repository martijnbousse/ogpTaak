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
	
	//TODO: werkt niet, ik zie het niet onmiddellijk.
	
	
	private static World world1;
	private static Collidable collidable1;
	private World mutableWorld1;
	private Collidable mutableCollidable1;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		world1 = new World();
		collidable1 = new Ship(new Vector(10,5), new Vector(5,10), 30, 1000, Math.PI/2);
		initWorld(world1);
	}

	@Before
	public void setUp() throws Exception {
		mutableWorld1 = new World(5000, 5000);
		mutableCollidable1 = new Ship(new Vector(0,0), new Vector(1,1), 50, 800, Math.PI/2);
//		mutableWorld1.addAsCollidable(mutableCollidable1);
	}
	
	// initialisatie
	
	private static void initWorld(World world) {
		world.addAsCollidable(collidable1);
	}
	
	// position
	
	@Test
	public void testGetPosition() {
		assertTrue(collidable1.getPosition().equals(new Vector(10,5)));
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
		assertEquals(collidable1.getRadius(),15,Util.EPSILON);
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
	
//	@Test	TODO: op de rand?
//	public void testMove_InfinityCase2() {
//		Vector oldPosition = mutableCollidable2.getPosition();
//		mutableCollidable2.move(10);
//		assertTrue(mutableCollidable2.getPosition().equals(oldPosition));
//	}
	
	
	
}
