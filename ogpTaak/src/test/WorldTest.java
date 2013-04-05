package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import collidable.*;

import asteroids.*;

/**
 * A class collecting tests for the class of worlds.
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
		//TODO bidirectionele associatie moet hier getest worden of die volledig verbroken wordt.
		fail("Not yet implemented");
	}
	
	// width

	@Test
	public void testGetWidth() {
		assertEquals(world1.getWidth(),1000,Util.EPSILON);
	}

	@Test
	public void testIsValidWidth() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMaxWidth() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsValidMaxDimension() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMaxWidth() {
		fail("Not yet implemented");
	}
	
	// height

	@Test
	public void testGetHeight() {
		assertEquals(world1.getHeight(),1000,Util.EPSILON);
	}

	@Test
	public void testIsValidHeight() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMaxHeight() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMaxHeight() {
		fail("Not yet implemented");
	}

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

	@Test
	public void testCanHaveAsCollidable() {
		fail("Not yet implemented");
	}

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
	
	// evolve
	
	@Test
	public void testEvolve() {
		fail("Not yet implemented");
	}

}
