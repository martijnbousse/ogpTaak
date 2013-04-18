package gameObjects;

import support.Vector;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of bullets as special kinds of collidables involving a mass and an association with ships.
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
	
	/**
	 * Returns whether this bullet has already bounced with the boundary.
	 */
	@Basic
	public boolean bouncedOnce() {
		return this.bouncedOnce;
	}
	
	/**
	 * Sets this bullet as already bounced with the boundary.
	 * 
	 * @post	This bullet has bounced with the boundary.
	 * 			(new this).bouncedOnce() == true
	 */
	public void setBouncedOnce() {
		this.bouncedOnce = true;
	}
	
	/**
	 * Variable registering whether this bullet has already bounced with the boundary.
	 */
	private boolean bouncedOnce = false;
	
	/**
	 * 
	 */
	@Override
	public void bounceOfBoundary() {
		if (bouncedOnce()) {
			terminate();
		}
		else {
			setBouncedOnce();
			super.bounceOfBoundary();
		}
	}
	
	/**
	 * This bullet collides with the given collidable.
	 * 
	 * @param	collidable
	 * 			The given collidable.
	 * @effect	The given collidable collides with this bullet.
	 * 			| collidable.collidesWith(this)
	 */
	public void collide(Collidable collidable) {
		collidable.collidesWith(this);
	}
	
	/**
	 * This bullet collides with the given ship.
	 * 
	 * @param	ship
	 * 			The given ship.
	 * @effect	The given ship collides with this bullet.
	 * 			| ship.collidesWith(this)
	 */
	public void collidesWith(Ship ship) {
		ship.collidesWith(this);
	}
	
	/**
	 * This bullet collides with the given asteroid.
	 * 
	 * @param	asteroid
	 * 			The given asteroid.
	 * @effect	The given asteroid collides with this bullet.
	 * 			| asteroid.collidesWith(this)
	 */
	public void collidesWith(Asteroid asteroid) {
		asteroid.collidesWith(this);
	}
	
	/**
	 * This bullet collides with the given bullet.
	 * 
	 * @param	bullet
	 * 			The given bullet.
	 * @effect	This bullet and the given bullet are terminated.
	 * 			| this.terminate()
	 * 			| bullet.terminate()
	 */
	public void collidesWith(Bullet bullet) {
		this.terminate();
		bullet.terminate();
	}
	
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
