package collidable;

import be.kuleuven.cs.som.annotate.*;
import asteroids.Vector;
//TODO: vector ook in collidable?

/**
 * A class of bullets as special kinds of collidables involving as additional property an association with ships.
 * 
 * @invar	The source of each bullet must be a valid source for a bullet.
 * 			| isValidSource(getSource())
 * 
 * @version	1.0
 * @author 	Martijn Boussé, Wout Vekemans
 *
 */
public class Bullet extends Collidable {

	/**
	 * Initialize this new bullet with given position, given velocity, given radius, given mass, given direction and given source.
	 * 
	 * @param 	position
	 * 			The position for this new bullet.
	 * @param	velocity
	 * 			The velocity for this new bullet.
	 * @param 	radius
	 * 			The radius for this new bullet.
	 * @param	mass
	 * 			The mass for this new bullet.
	 * @param	source
	 * 			The source for this new bullet.
	 * @effect	This new bullet is initialized as a collidable with the given position, 
	 * 			the given velocity, the given radius and the given mass.
	 * 			| super(position, velocity, radius, mass)
	 * @post	The new source of this new bullet is equal to the given source.
	 * 			| (new this).getSource() == source
	 */
	public Bullet(Vector position, Vector velocity, double radius, Ship source) {
		super(position, velocity, radius);
		if(!isValidSource(source))
			throw new IllegalArgumentException();
		this.source = source;
	}
	
	/**
	 * Returns the source of this bullet.
	 */
	@Basic @Immutable
	public Ship getSource() {
		return this.source;
	}
	
	/**
	 * Check whether the given source is a valid source for any bullet.
	 * @param 	source
	 * 			The source to check
	 * @return	| result = source != null
	 */
	public static boolean isValidSource(Ship source) {
		return (source != null);
	}
	
//	/**
//	 * Set the source for this bullet to the given source.
//	 * 
//	 * @param 	source
//	 * 			The new source for this bullet.
//	 * @post	| (new this).getSource() == source
//	 * @throws	IllegalArgumentException
//	 * 			| !isValidSource(source)
//	 */
//	@Raw
//	private void setSource(Ship source) throws IllegalArgumentException{
//		if(!isValidSource(source))
//			throw new IllegalArgumentException();
//		this.source = source;
//	}
	
	//TODO: aangezien source final is? in constructor én getSource is @Immutable
	
	/**
	 * Variable registering the source of this bullet.
	 */
	private final Ship source; 
	
	/**
	 * Symbolic constant registering the density of all bullets.
	 */
	public static double DENSITY = 7.8e-12;

	/**
	 * Returns the mass of this bullet.
	 */
	@Override @Basic @Immutable
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Variable registering the mass of this bullet.
	 */
	public final double mass = (4/3)*Math.PI*Math.pow(getRadius(),3)*DENSITY; 
	
	
	// TODO: alreadyBounced ? om te kunnen nagaan of de bullet al gebounced heeft met de rand in evolve.
	
	/**
	 * Return a textual representation of this bullet.
	 * 
	 * @return	A string consisting of the textual representation of a collidable and
	 * 			complemented with the mass and source of this bullet, separated by
	 * 			spaces and ended with a square bracket.
	 * 			| result.equals(super.toString() 
	 * 			|	+ " Mass: " + getMass() + " Source: " + getSource() + "]");
	 */
	@Override
	public String toString(){
		return super.toString() + " Mass: " + getMass() +  " Source: " + getSource() + "]";
	}
}
