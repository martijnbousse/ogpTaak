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
 * @author 	Martijn Boussé, Wout Vekemans
 * @version	1.0
 *
 */
public class WorldTest {
	
	private static World world1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		world1 = new World(1000,1000);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsTerminated() {
		fail("Not yet implemented");
	}

	@Test
	public void testTerminate() {
		fail("Not yet implemented");
	}

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

	/**
	 * Test method for {@link asteroids.World#isValidMaxDimension(double)}.
	 */
	@Test
	public void testIsValidMaxDimension() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asteroids.World#setMaxWidth(double)}.
	 */
	@Test
	public void testSetMaxWidth() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asteroids.World#getHeight()}.
	 */
	@Test
	public void testGetHeight() {
		assertEquals(world1.getHeight(),1000,Util.EPSILON);
	}

	/**
	 * Test method for {@link asteroids.World#isValidHeight(double)}.
	 */
	@Test
	public void testIsValidHeight() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asteroids.World#getMaxHeight()}.
	 */
	@Test
	public void testGetMaxHeight() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asteroids.World#setMaxHeight(double)}.
	 */
	@Test
	public void testSetMaxHeight() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asteroids.World#getAllCollidables()}.
	 */
	@Test
	public void testGetAllCollidables() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asteroids.World#getNbCollidables()}.
	 */
	@Test
	public void testGetNbCollidables() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asteroids.World#canHaveAsCollidable(collidable.Collidable)}.
	 */
	@Test
	public void testCanHaveAsCollidable() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asteroids.World#hasAsCollidable(collidable.Collidable)}.
	 */
	@Test
	public void testHasAsCollidable() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asteroids.World#hasProperCollidables()}.
	 */
	@Test
	public void testHasProperCollidables() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asteroids.World#addAsCollidable(collidable.Collidable)}.
	 */
	@Test
	public void testAddAsCollidable() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asteroids.World#removeAsCollidable(collidable.Collidable)}.
	 */
	@Test
	public void testRemoveAsCollidable() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asteroids.World#evolve(double)}.
	 */
	@Test
	public void testEvolve() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asteroids.World#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
