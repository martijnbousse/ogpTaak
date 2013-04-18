package test;

import static org.junit.Assert.*;
import gameObjects.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import asteroids.*;

/**
 * A class collecting tests for the class of worlds.
 * 
 * @author 	Martijn Boussé, Wout Vekemans
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
	private World mutableWorld2;
	private World terminatedWorld;
	
	private Collidable mutableCollidable1;
	private Collidable mutableCollidable2;
	private Collidable mutableCollidable3;
	private Collidable mutableCollidable4;
	private Collidable terminatedCollidable;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		collidable1 = new Ship();
		collidable2 = new Ship();
		collidable3 = new Ship();
		collidable4 = new Ship();
		world1 = new World(1000,1000);
		initWorld(world1);

	}
	
	private static void initWorld(World world) {
		world.addAsCollidable(collidable1);
		world.addAsCollidable(collidable2);
		world.addAsCollidable(collidable3);
	}
	
	
	@Before
	public void setUp() throws Exception {
		mutableCollidable1 = new Ship();
		mutableCollidable2 = new Ship();
		mutableCollidable3 = new Ship();
		mutableCollidable4 = new Ship();
		terminatedCollidable = new Ship();
		terminatedCollidable.terminate();
		
		mutableWorld1 = new World();
		mutableWorld2 = new World();
		terminatedWorld = new World();
		terminatedWorld.terminate();
		
		mutableWorld1.addAsCollidable(mutableCollidable1);
		mutableWorld1.addAsCollidable(mutableCollidable2);
		mutableWorld2.addAsCollidable(mutableCollidable3);
		
	}	
	
	// terminate

	@Test
	public void testIsTerminated() {
		assertEquals(false,world1.isTerminated());
	}

	@Test
	public void testTerminate() {
		mutableWorld1.terminate();
		assertEquals(mutableWorld1.getNbCollidables(),0);  //TODO: werkt niet, concurrentModificationException? 
		// http://www.coderanch.com/t/233932/threads/java/deal-Concurrent-Modification-Exception
		// of java docs
		
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
		fail("Not yet implemented");
	}
	
	// isValidWidth

	@Test
	public void testIsValidWidth_NaNCase() {
		assertFalse(World.isValidWidth(Double.NaN));
	}
	
	@Test
	public void testIsValidWidth_MaxCase() {
		assertTrue(World.isValidWidth(Double.MAX_VALUE));
	}
	
	@Test
	public void testIsValidWidth_ZeroCase() {
		assertFalse(World.isValidWidth(0));
	}
	
	@Test
	public void testIsValidWidth_NegativeCase() {
		assertFalse(World.isValidWidth(-1));
	}
	
	// maxDimension

	@Test
	public void testIsValidMaxDimension() {
		fail("Not yet implemented");
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
	public void testSetMaxHeight() {
		fail("Not yet implemented");
	}
	
	// isValidHeight

	@Test
	public void testIsValidHeight_NaNCase() {
		assertFalse(World.isValidHeight(Double.NaN));
	}
	
	@Test
	public void testIsValidHeight_MaxCase() {
		assertTrue(World.isValidHeight(Double.MAX_VALUE));
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
	
	// getAllShips //TODO: test 				Alle drie hetzelfde als getAllCollidables
	
	// getAllAsteroids //TODO: test
	
	// getAllBullets //TODO: test
	
	@Test
	public void testGetNbCollidables() {
		assertEquals(3,world1.getNbCollidables());
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
//		for(Collidable collidable : world1.getAllCollidables()) {
//		}
		//TODO: implement
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
	
	// evolve TODO: testen
	
	@Test
	public void testEvolve() {
		fail("Not yet implemented");
	}
	
	// resolveCollision TODO: testen
	
	// 

}
