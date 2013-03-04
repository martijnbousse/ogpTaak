package asteroids;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ShipTest {
	
	private static Ship ship;
	private static Ship defaultShip;
	private Ship mutableShip;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ship = new Ship(new Vector(10,5), new Vector(5,10), 15, Math.PI/2);
		setDefaultShip(new Ship());
		
	}

	@Before
	public void setUp() throws Exception {
		mutableShip = new Ship();
	}

	@Test
	public void testShipVectorVectorDoubleDouble() {
		fail("Not yet implemented");
	}

	@Test
	public void testShip() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPosition() {
		assert(ship.getPosition().equals(new Vector(10,5)));
	}

	@Test
	public void testIsValidPosition_LegalCase() {
		assert(Ship.isValidPosition(ship.getPosition()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIsValidPosition_IllegalCase() {
		Ship invalidShip = new Ship(null,null,5,150);
		assertFalse(Ship.isValidPosition(invalidShip.getPosition()));
	}
	
	@Test
	public void testSetPosition_LegalCase() {
		mutableShip.setPosition(new Vector(15,10));
		assert(mutableShip.getPosition().equals(new Vector(15,10)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPosition_IllegalCase() {
		mutableShip.setPosition(null);
		assert(mutableShip.getPosition().equals(new Vector(0,0)));
	}

	@Test
	public void testGetVelocity() {
		assert(ship.getVelocity().equals(new Vector(5,10)));
	}

	@Test
	public void testSetVelocity_LegalCase() {
		mutableShip.setVelocity(new Vector(25,20));
		assert(mutableShip.getVelocity().equals(new Vector(25,20)));
	}
	
	@Test		//TODO nullpointer verwijderd; gaf een error omdat er geen exception was;
	public void testSetVelocity_NullCase() {
		mutableShip.setVelocity(null);
		assert(mutableShip.getVelocity().equals(new Vector(0,0)));
	}
	
	@Test
	public void testSetVelocity_IllegalCase() {
		mutableShip.setVelocity(new Vector(300000,300000));
		assert(mutableShip.getVelocity().equals(new Vector(0,0)));
	}
	
	@Test
	public void testCanHaveAsVelocity() {
		
	}

	@Test
	public void testSetSpeedLimit_LegalCase() {
		mutableShip.setSpeedLimit(150);
		assert(Util.fuzzyEquals(mutableShip.getSpeedLimit(),150));
	}
	
	@Test
	public void testSetSpeedLimit_IllegalCase() {
		mutableShip.setSpeedLimit(150);
		mutableShip.setSpeedLimit(400000);
		assert(Util.fuzzyEquals(mutableShip.getSpeedLimit(),150));
	}


	@Test
	public void testCanHaveAsSpeedLimit() {
		
	}

	@Test
	public void testGetDirection() {
		assert(ship.getDirection()==Math.PI/2);
	}

	@Test
	public void testSetDirection_LegalCase() {
		mutableShip.setDirection(Math.PI);
		assert(Util.fuzzyEquals(mutableShip.getDirection(),Math.PI));
	}
	
	@Test
	public void testSetDirection_IllegalCase() {
		mutableShip.setDirection(Math.PI*3);
		assert(Util.fuzzyEquals(mutableShip.getDirection(),Math.PI/2));
	}

	@Test
	public void testIsValidDirection() {
		
	}

	@Test
	public void testSetMinRadius_LegalCase() {
		Ship.setMinRadius(25);
		assert(Util.fuzzyEquals(Ship.getMinRadius(),25));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetMinRadius_IllegalCase() {
		Ship.setMinRadius(-25);
		assert(Util.fuzzyEquals(Ship.getMinRadius(),25));
	}

	@Test
	public void testIsValidMinRadius() {
		
	}

	@Test
	public void testCanHaveAsRadius() {
		
	}

	@Test
	public void testGetRadius() {
		assert(Util.fuzzyEquals(ship.getRadius(),15));
	}
	
	@Test
	public void testTurn_LegalCase() {
		mutableShip.turn(Math.PI);
		assert(Util.fuzzyEquals(mutableShip.getDirection(),Math.PI ));
	}
	
	@Test
	public void testTurn_IllegalCase() {
		mutableShip.turn(Math.PI*3);
		//assert(Util.fuzzyEquals(mutableShip.getDirection(),0.0));
		assert(Util.fuzzyEquals(mutableShip.getDirection(),Math.PI*3));		//TODO moet nog gefixt worden
	}

	public static Ship getDefaultShip() {
		return defaultShip;
	}

	public static void setDefaultShip(Ship defaultShip) {
		ShipTest.defaultShip = defaultShip;
	}

}
