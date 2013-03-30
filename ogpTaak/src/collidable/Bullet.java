package collidable;

import be.kuleuven.cs.som.annotate.Basic;
import asteroids.Vector;

/**
 * A class of bullets as special kinds of collidables involving as additional property an association with ships.
 * 
 * @invar	The source of each bullet must be a valid source for a bullet.
 * 			| isValidSource(getSource())
 * 
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
		setSource(source);
	}
	
	/**
	 * Returns the source of this bullet.
	 */
	@Basic
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
		return source != null;
	}
	
	/**
	 * Set the source for this bullet to the given source.
	 * 
	 * @param 	source
	 * 			The new source for this bullet.
	 * @post	| (new this).getSource() == source
	 * @throws	IllegalArgumentException
	 * 			| !isValidSource(source)
	 */
	private void setSource(Ship source) throws IllegalArgumentException{
		if(!isValidSource(source))
			throw new IllegalArgumentException();
		this.source = source;
	}
	
	/**
	 * Variable registering the source of this bullet.
	 */
	private Ship source; 
	
	/**
	 * Symbolic constant registering the density of all bullets.
	 */
	public static double DENSITY = 7.8e-12;


	// TODO overflow
	@Override
	public double getMass() {
		return (4/3)*Math.PI*Math.pow(getRadius(),3)*DENSITY;
	}
	
	
}
