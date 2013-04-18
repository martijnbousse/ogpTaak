package test;

import static org.junit.Assert.*;
import gameObjects.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import support.Collision;
import support.Vector;


import asteroids.*;

/**
 * A class collecting tests for the class of worlds.
 * 
 * REMINDER : resolveCollision won't be tested here, because it only uses other methods, tested elsewhere
 * 
 * @author 	Martijn Bouss�, Wout Vekemans
 * @version	1.0
 *
 */
public class WorldTest {
	
	private static World world1;
	private static Collidable collidable1;
	private static Collidable collidable2;
	private static Collidable collidable3;
	
	private static Collidable collidable4;
	
	private World mutableWorld1;
	private Collidable mutableCollidable1;
	private Collidable mutableCollidable2;
	
	private Collidable mutableCollidable4;
	
	private World mutableWorld2;
	private Collidable mutableCollidable3;
	
	private World mutableWorld3;
	private Ship mutableShip1;
	private Asteroid mutableAsteroid1;
	
	private World mutableWorld4;
	private Ship mutableShip2;
	private Ship mutableShip3;
	
	private World mutableWorld5;
	private Ship mutableShip4;
	private Ship mutableShip5;
	
	
	private World terminatedWorld;
	
	private Collidable terminatedCollidable;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		world1 = new World(1000,1000);
		collidable1 = new Ship();
		collidable2 = new Ship();
		collidable3 = new Ship();
		world1.addAsCollidable(collidable1);
		world1.addAsCollidable(collidable2);
		world1.addAsCollidable(collidable3);
		
		collidable4 = new Ship();
	}
	
	@Before
	public void setUp() throws Exception {
		mutableWorld1 = new World();
		mutableCollidable1 = new Ship();
		mutableCollidable2 = new Ship();
		mutableWorld1.addAsCollidable(mutableCollidable1);
		mutableWorld1.addAsCollidable(mutableCollidable2);
		
		mutableWorld2 = new World();
		mutableCollidable3 = new Ship();
		mutableWorld2.addAsCollidable(mutableCollidable3);
		
		mutableWorld3 = new World();
		mutableShip1 = new Ship();
		mutableAsteroid1 = new Asteroid();
		mutableWorld3.addAsCollidable(mutableShip1);
		mutableWorld3.addAsCollidable(mutableAsteroid1);
		mutableShip1.fireBullet();
		
		mutableWorld4 = new World(1000,1000);
		mutableShip2 = new Ship(new Vector(100,500), new Vector(-10,0), 10, 10, 0);
		mutableShip3 = new Ship(new Vector(200,700), new Vector(-10,0), 10, 10, 0);
		mutableWorld4.addAsCollidable(mutableShip2);
		mutableWorld4.addAsCollidable(mutableShip3);
		
		mutableWorld5 = new World(1000,1000);
		mutableShip4 = new Ship(new Vector(250,250), new Vector(10,0), 10,10,0);
		mutableShip5 = new Ship(new Vector(370,250), new Vector(-10,0), 10,10,0);
		mutableWorld5.addAsCollidable(mutableShip4);
		mutableWorld5.addAsCollidable(mutableShip5);
		
		mutableCollidable4 = new Ship();
		terminatedCollidable = new Ship();
		terminatedCollidable.terminate();
		terminatedWorld = new World();
		terminatedWorld.terminate();
		
	}	
	
	// terminate

	@Test
	public void testIsTerminated() {
		assertEquals(false,world1.isTerminated());
	}

	@Test
	public void testTerminate() {
		mutableWorld1.terminate();
		assertEquals(mutableWorld1.getNbCollidables(),0);
	}
	
	// maxDimension

	@Test
	public void testIsValidMaxDimension_NaNCase() {
		assertFalse(World.isValidMaxDimension(Double.NaN));
	}
	
	@Test
	public void testIsValidMaxDimension_NegativeCase() {
		assertFalse(World.isValidMaxDimension(-1));
	}
	
	@Test
	public void testIsValidMaxDimension_ZeroCase() {
		assertFalse(World.isValidMaxDimension(0));
	}
	
	@Test
	public void testIsValidMaxDimension_LegalCase() {
		assertTrue(World.isValidMaxDimension(75));
	}
	
	// width

	@Test
	public void testGetWidth() {
		assertEquals(world1.getWidth(),1000,Util.EPSILON);
	}
	
	@Test
	public void testGetMaxWidth() {
		assertEquals(World.getMaxWidth(),Double.MAX_VALUE,Util.EPSILON);
	}
	
	@Test
	public void testSetMaxWidth() {
		
	}

	@Test
	public void testSetMaxWidth_IllegalCases() {
		World.setMaxHeight(-1);
		assertEquals(World.getMaxWidth(), Double.MAX_VALUE, Util.EPSILON);
	}
	
	@Test
	public void testSetMaxWidth_LegalCase() {
		World.setMaxWidth(755);
		assertEquals(World.getMaxWidth(),755, Util.EPSILON);
	}

	@Test
	public void testIsValidWidth_NaNCase() {
		assertFalse(World.isValidWidth(Double.NaN));
	}
	
	@Test
	public void testIsValidWidth_MaxCase() {
		assertTrue(World.isValidWidth(World.getMaxWidth()));
	}
	
	@Test
	public void testIsValidWidth_ZeroCase() {
		assertFalse(World.isValidWidth(0));
	}
	
	@Test
	public void testIsValidWidth_NegativeCase() {
		assertFalse(World.isValidWidth(-1));
	}
	
	// height

	@Test
	public void testGetHeight() {
		assertEquals(world1.getHeight(),1000,Util.EPSILON);
	}
	
	@Test
	public void testGetMaxHeight() {
		assertEquals(World.getMaxHeight(),Double.MAX_VALUE,Util.EPSILON);
	}

	@Test
	public void testSetMaxHeight_LegalCase() {
		World.setMaxHeight(755);
		assertEquals(World.getMaxHeight(),755, Util.EPSILON);
	}
	
	@Test
	public void testSetMaxHeight_IllegalCases() {
		World.setMaxHeight(-1);
		assertEquals(World.getMaxHeight(), Double.MAX_VALUE, Util.EPSILON);
	}

	@Test
	public void testIsValidHeight_NaNCase() {
		assertFalse(World.isValidHeight(Double.NaN));
	}
	
	@Test
	public void testIsValidHeight_MaxCase() {
		assertTrue(World.isValidHeight(World.getMaxHeight()));
	}
	
	@Test
	public void testIsValidHeight_ZeroCase() {
		assertFalse(World.isValidHeight(0));
	}
	
	@Test
	public void testIsValidHeight_NegativeCase() {
		assertFalse(World.isValidHeight(-1));
	}
	
	// getAllCollidables

	@Test
	public void testGetAllCollidables_NullReference() {
		assertEquals(false,world1.getAllCollidables().contains(null));
	}
	
	@Test
	public void testGetAllCollidables_WorldReference() {
		for(Collidable collidable : world1.getAllCollidables()) {
			assertEquals(world1.getAllCollidables().contains(collidable),world1.hasAsCollidable(collidable));
		}
	}
	
	@Test
	public void testGetNbCollidables() {
		assertEquals(3,world1.getNbCollidables());
	}
	
	// getAllShips 
	
	@Test
	public void testGetAllShips_NullReference() {
		assertEquals(false,mutableWorld3.getAllShips().contains(null));
	}
	
	@Test
	public void testGetAllShips_WorldReference() {
		for(Ship ship : mutableWorld3.getAllShips()) {
			assertEquals(mutableWorld3.getAllShips().contains(ship),mutableWorld3.hasAsCollidable(ship));
		}
	}
	
	@Test
	public void testGetNbShips() {
		assertEquals(1,mutableWorld3.getNbShips());
	}
	
	// getAllAsteroids 
	
	@Test
	public void testGetAllAsteroids_NullReference() {
		assertEquals(false,mutableWorld3.getAllAsteroids().contains(null));
	}
	
	@Test
	public void testGetAllAsteroids_WorldReference() {
		for(Asteroid asteroid : mutableWorld3.getAllAsteroids()) {
			assertEquals(mutableWorld3.getAllAsteroids().contains(asteroid),mutableWorld3.hasAsCollidable(asteroid));
		}
	}
	
	@Test
	public void testGetNbAsteroids() {
		assertEquals(1,mutableWorld3.getNbAsteroids());
	}
	
	// getAllBullets
	
	@Test
	public void testGetAllBullets_NullReference() {
		assertEquals(false,mutableWorld3.getAllBullets().contains(null));
	}
	
	@Test
	public void testGetAllBullets_WorldReference() {
		for(Bullet bullet : mutableWorld3.getAllBullets()) {
			assertEquals(mutableWorld3.getAllBullets().contains(bullet),mutableWorld3.hasAsCollidable(bullet));
		}
	}
	
	@Test
	public void testGetNbBullets() {
		assertEquals(1,mutableWorld3.getNbBullets());
	}
	
	// canHaveAsCollidable
	
	@Test
	public void testCanHaveAsCollidable_NullCase() {
		assertFalse(world1.canHaveAsCollidable(null));
	}
	
	@Test
	public void testCanHaveAsCollidable_OtherIsTerminatedCase() {
		assertFalse(world1.canHaveAsCollidable(terminatedCollidable));
	}
	
	// hasAsCollidable
	
	@Test
	public void testHasAsCollidable_LegalCase() {
		assertTrue(world1.hasAsCollidable(collidable1));
	}
	
	@Test
	public void testHasAsCollidable_IllegalCase() {
		assertFalse(world1.hasAsCollidable(collidable4));
	}
	
	@Test
	public void testHasAsCollidable_NullCase() {
		assertFalse(world1.hasAsCollidable(null));
	}
	
	// hasProperCollidables
	
	@Test
	public void testHasProperCollidables() {
		for(Collidable collidable : world1.getAllCollidables()) {
			assertTrue(collidable.getWorld().equals(world1));
			assertTrue(world1.hasAsCollidable(collidable));
		}
	}
	
	// addAsCollidable
	
	@Test
	public void testAddAsCollidable_LegalCase() {
		mutableWorld1.addAsCollidable(mutableCollidable4);
		// associatie wordt consistent opgebouwd.
		assertTrue(mutableWorld1.hasAsCollidable(mutableCollidable4));
		assertEquals(mutableCollidable4.getWorld(),mutableWorld1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddAsCollidable_AlreadyAttachedCase() {
		mutableWorld1.addAsCollidable(mutableCollidable3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddAsCollidable_NullCase() {
		mutableWorld1.addAsCollidable(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddAsCollidable_TerminatedWorldCase() {
		terminatedWorld.addAsCollidable(mutableCollidable4);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddAsCollidable_TerminatedCollidableCase() {
		mutableWorld1.addAsCollidable(terminatedCollidable);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddAsCollidable_TerminatedWorldAndCollidableCase() {
		terminatedWorld.addAsCollidable(terminatedCollidable); 
	}
		
	// removeAsCollidable
	
	@Test
	public void testRemoveAsCollidable_LegalCase() {
		mutableWorld1.removeAsCollidable(mutableCollidable1);
		// associatie wordt consistent afgebouwd.
		assertFalse(mutableWorld1.hasAsCollidable(mutableCollidable1));
		assertEquals(mutableCollidable1.getWorld(),null);
	}
	
	@Test
	public void testRemoveAsCollidable_NotInSetCase() {
		mutableWorld1.removeAsCollidable(mutableCollidable4);
	}
	
	// getNextCollision
	
	@Test
	public void testGetNextCollision() {
		Collision next = mutableWorld4.getNextCollision();
		Collision check = new Collision(mutableShip2, null, 9);
		assertTrue(next.equals(check));
	}
	
	// evolve
	
	@Test
	public void testEvolve_CollisionCase() {
		mutableWorld5.evolve(6);
		assertTrue(mutableShip4.getTimeToCollision(mutableShip5) == Double.POSITIVE_INFINITY);
	}
	
	@Test
	public void testEvolve_NonCollisionCase() {
		double old = mutableShip5.getTimeToCollision(mutableShip4);
		mutableWorld5.evolve(2);
		assertTrue(mutableShip5.getTimeToCollision(mutableShip4) < old);
		
	}
}
