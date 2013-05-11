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
	 * @effect	This new bullet is initialized as a collidable with the given position, 
	 * 			the given velocity, the given radius and the given mass.
	 * 			| super(position, velocity, radius, mass)
	 */
	public Bullet(Vector position, Vector velocity, double radius) throws IllegalArgumentException {
		super(position, velocity, radius);
	}
	
	/**
	 *  Terminate this bullet as an instance of collidable. In addition, remove the bullet from its source. //TODO: ok zo?
	 */
	@Override
	public void terminate() {
		getSource().removeAsBullet(this);
		super.terminate();
	}
	
	/**
	 * Returns the source of this bullet.
	 */
	@Basic @Immutable
	public Ship getSource() {
		return this.source;
	}
	
	/**
	 * Set the source of this bullet to the given ship.
	 * 
	 * @param	ship
	 * 			The new ship to attach this bullet to.
	 * @pre		If the given ship is effective, it must already reference this bullet as one of its bullets.
	 * 			| if (ship != null)
	 * 			|	then ship.hasAsBullet(this)
	 * @pre		If the given ship is not effective and this bullet references an effective ship,
	 * 			that ship may not reference this bullet as one of its bullets.						
	 * 			| if ((ship == null) && (getShip() != null))
	 * 			| 	then !getSource().hasAsBullet(this)
	 * @post	This bullet references the given ship as the ship to which it is attached.
	 * 			| (new this).getSource() == ship
	 */
	@Raw //TODO: package visible gemaakt!
	void setSource(Ship ship) {
		assert ( (ship == null) || ship.hasAsBullet(this) );
		assert ( (ship != null) || (getSource() == null) || (!getSource().hasAsBullet(this)) );
		this.source = ship;
	}

	/**
	 * Check whether this bullet has a proper ship to which it is attached.
	 * 
	 * @return	True if and only if this bullet can have its ship as the ship to which it is attached,
	 * 			and if that ship is either not effective or has this bullet as one of its bullets.
	 * 			| result == ( canHaveAsSource(getSource()) 
	 * 			|			&& ( (getSource() == null) || getSoure().hasAsBullet(this))) 
	 */
	@Raw
	public boolean hasProperSource() {
		return ( canHaveAsSource(getSource()) 
				&& ( (getSource() == null) || getSource().hasAsBullet(this)));
	}
	
	/**
	 * Check whether this bullet can be attached to the given ship.
	 * 
	 * @param 	source
	 * 			The ship to check.
	 * @return	True if and only if the given ship is not effective or if it can have this bullet as one of its bullets.
	 * 			| result == ((source == null) || (source.canHaveAsBullet(this)))
	 */
	// canHaveAsSource calls the complementary checker in the bidirectional association.
	@Raw
	public boolean canHaveAsSource(Ship source) { 
		return ((source == null) || source.canHaveAsBullet(this));
	}
	
	
	/**
	 * Variable registering the source of this bullet.
	 */
	private Ship source = null; 
	
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
	 * Symbolic constant registering the density of all bullets.
	 */
	public static double DENSITY = 7.8e-12;

	/**
	 * Returns whether this bullet has already bounced with the boundary.
	 */
	@Basic
	public boolean hasBouncedOnce() {
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
	 * Bounces this bullet off the boundary if it hasn't bounced yet.
	 * 
	 * @effect	| if bouncedOnce()
	 * 			|	then terminate()
	 * 			| else 
	 * 			|	super.bounceOfBoundary()
	 * 			|	setBouncedOnce()
	 */
	@Override
	public void bounceOfBoundary() {
		if (hasBouncedOnce()) {
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
