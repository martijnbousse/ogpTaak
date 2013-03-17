/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.SumOverflowException;
import asteroids.TimesOverflowException;
import asteroids.Util;
import asteroids.Vector;

/**
 * A class collecting tests for the class of vectors.
 * 
 * @author 	Martijn Boussé, Wout Vekemans
 * @version	1.0
 *
 */
public class VectorTest {
	
	/**
	 * Variable referencing a vector with components (1,2).
	 */
	private static Vector vector12;
	
	/**
	 * Variable referencing vectors with components (2,2), (1,-4), (0,0) and (inf,inf).
	 */
	private Vector vector22, vector1min4, vectorMaxMax;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		vector12 = new Vector(1,2);
	}

	@Before
	public void setUp() throws Exception {
		vector22 = new Vector(2,2);
		vector1min4 = new Vector(1,-4);
		vectorMaxMax = new Vector(Double.MAX_VALUE,Double.MAX_VALUE);
	}
	
	// get

	@Test
	public void testGetXComponent() {
		assertTrue(Util.fuzzyEquals(vector12.getXComponent(),1.0));
	}
	
	@Test
	public void testGetYComponent() {
		assertTrue(Util.fuzzyEquals(vector12.getYComponent(),2.0));
	}
	
	// add
	
	@Test
	public void testAdd_LegalCase() {
		Vector result = vector22.add(vector1min4);
		assertTrue(result.equals(new Vector(3,-2)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAdd_NullCase() {
		vector22.add(null);
	}
	
	@Test(expected=SumOverflowException.class)
	public void testAdd_SumOverflowCase() {
		vector22.add(vectorMaxMax);
	}
	
	// subtract
	
	@Test
	public void testSubtract_LegalCase() {
		Vector result = vector22.subtract(vector1min4);
		assertTrue(result.equals(new Vector(1,6)));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSubtract_NullCase() {
		vector22.subtract(null);
	}
	
	@Test(expected=SumOverflowException.class)
	public void testSubtract_SumOverflowCase() {
		vector1min4.subtract(vectorMaxMax);
	}
	
	// scale
	
	@Test
	public void testScale_LegalCase() {
		Vector result = vector22.scale(5);
		assertTrue(result.equals(new Vector(10,10)));
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testScale_NaNCase() {
		vector22.scale(Double.NaN);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testScale_NegativeAmountCase() {
		vector22.scale(-1);
	}
	
	@Test(expected=TimesOverflowException.class)
	public void testScale_TimesOverflowCase() {
		vector22.scale(Double.MAX_VALUE);
	}
	
	// dotproduct
	
	@Test
	public void testDotProduct_LegalCase() {
		double result = vector22.dotProduct(vector1min4);
		assertTrue(Util.fuzzyEquals(result,-6.0));
		System.out.println(0.0-Double.MAX_VALUE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDotProduct_NullCase() {
		vector22.dotProduct(null);
	}
	
	@Test(expected=TimesOverflowException.class)
	public void testDotProduct_TimesOverflowCase() {
		vector22.dotProduct(vectorMaxMax);
	}
	
	// equals
	
	@Test
	public void testEquals_LegalCase() {
		assertTrue(vector22.equals(vector22));
	}
	
	@Test
	public void testEquals_NullCase() {
		assertFalse(vector22.equals(null));
	}
	
	@Test
	public void testEquals_FalseCase() {
		assertFalse(vector22.equals(vector1min4));
	}
}
