package test;

import static org.junit.Assert.*;

import gameObjects.Ship;
import gameObjects.World;
import gameObjects.Bullet;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import support.Vector;



/**
 * A class collecting tests for the bidirectional association in the class of bullet.
 * 
 * @author 	Martijn Boussé, Wout Vekemans
 * @version	1.0
 *
 */
public class BulletTest {
	
	private World mutableWorld1;
	private Ship mutableShip1;
	
	private static Bullet bullet1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bullet1 = new Bullet(new Vector(100,100), new Vector(10,10), 10);
	}
	
	@Before
	public void setUp() throws Exception {
		mutableWorld1 = new World();
		mutableShip1 = new Ship();
		mutableWorld1.addAsCollidable(mutableShip1);
		mutableShip1.fireBullet();
	}
	
	// getSource
	@Test
	public void testGetSource() {
		ArrayList<Bullet> bullets = new ArrayList<Bullet>(mutableShip1.getAllBullets());
		Bullet check = bullets.get(0);
		assertEquals(check.getSource(),mutableShip1);
	}
	
	// hasProperSource
	
	@Test
	public void testHasProperSource_LegalCase() {
		ArrayList<Bullet> bullets = new ArrayList<Bullet>(mutableShip1.getAllBullets());
		Bullet check = bullets.get(0);
		assertTrue(check.hasProperSource());
	}
	
	@Test
	public void testHasProperSource_NullCase() {
		// A bullet can have null as its source,
		// i.e. when the bidirectional association is destroyed.
		assertTrue(bullet1.hasProperSource());
	}
	
	// canHaveAsSource (calls the complementary checker in the bidirectional association)
	
	@Test
	public void testCanHaveAsSource_NullCase() {
		// A bullet can have null as its source,
		// i.e. when the bidirectional association is destroyed.
		assertTrue(bullet1.canHaveAsSource(null));
	}

}
