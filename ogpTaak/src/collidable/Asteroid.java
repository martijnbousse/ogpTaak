package collidable;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;
import asteroids.Util;
import asteroids.Vector;

/**
 * A class of asteroids as special kinds of collidables involving a mass.
 * 
 * @version	1.0
 * @author 	Martijn Bouss�, Wout Vekemans
 *
 */
public class Asteroid extends Collidable{

	/**
	 * Initialize this new asteroid with given position, given velocity, given radius, given mass, given direction and given source.
	 * 
	 * @param 	position
	 * 			The position for this new asteroid.
	 * @param	velocity
	 * 			The velocity for this new asteroid.
	 * @param 	radius
	 * 			The radius for this new asteroid.
	 * @param	mass
	 * 			The mass for this new asteroid.
	 * @effect	This new asteroid is initialized as a collidable with the given position, 
	 * 			the given velocity, the given radius and the given mass.
	 * 			| super(position, velocity, radius, mass)
	 */
	public Asteroid(Vector position, Vector velocity, double radius) {
		super(position, velocity,radius);
	}
	
	/**
	 * Terminate this asteroid as an instance of collidable. In addition, if this asteroid can spawn to smaller asteroids, it will spawn them.	
	 */
	@Override
	public void terminate() {
		if (canSpawn()) {	
			spawn();
		}
		// TODO: comment de lijn hieronder eens en speel dan eens :p dan zie je pas hoe goed bounce werkt!
		super.terminate();
	}
	
	/**
	 * Check whether this asteroid can spawn to lesser asteroids.
	 * 
	 * @return	True if and only if this asteroid is located within a world and if its radius is larger than or equal to 30 km. 
	 * 			| result == (getWorld() != null)
	 * 			|			&& Util.fuzzyLessThanOrEqualTo(30.0,getRadius());
	 */
	public boolean canSpawn() {
		return (getWorld() != null)
				&& !isTerminated()
				&& Util.fuzzyLessThanOrEqualTo(30.0,getRadius());
		}
	
	/**
	 * This asteroids spawns two new smaller asteroids.
	 * 
	 * @effect	Two new smaller asteroids are added to the world of this asteroid with a new position, new velocity and half the radius.
	 * 			| let
	 * 			|  	firstNewPosition = getPosition().subtract(new Vector(getRadius()/2,0))
	 *			|	secondNewPosition = getPosition().add(new Vector(getRadius()/2,0))
	 *			| 	firstNewVelocity = new Vector(newSpeed*Math.cos(randomDirection),newSpeed*Math.cos(randomDirection))
	 *			|	secondNewVelocity = new Vector(newSpeed*Math.cos(randomDirection+Math.PI),newSpeed*Math.cos(randomDirection+Math.PI))
	 *			| in
	 * 			|	getWorld().addAsCollidable(new Asteroid(firstNewPosition,firstNewVelocity,getRadius()/2));
	 * 			| 	getWorld().addAsCollidable(new Asteroid(secondNewPosition,secondNewVelocity,getRadius()/2))
	 */
	public void spawn() {
		Asteroid firstNewAsteroid = null;
		Asteroid secondNewAsteroid = null;
		try {
			// create random direction
			Random r = new Random();
			double randomDirection = 2*Math.PI * r.nextDouble();
			double newSpeed = 1.5*Math.sqrt(getVelocity().dotProduct(getVelocity()));
			// check speed limit
			if (Util.fuzzyLessThanOrEqualTo(getSpeedLimit(),newSpeed))
				newSpeed = getSpeedLimit();	
			// create vectors
			Vector firstNewVelocity = new Vector(newSpeed*Math.cos(randomDirection),newSpeed*Math.sin(randomDirection));
			Vector secondNewVelocity = new Vector(-newSpeed*Math.cos(randomDirection),-newSpeed*Math.sin(randomDirection));
			Vector firstNewPosition = getPosition().add(new Vector((getRadius()/2)*Math.cos(randomDirection),(getRadius()/2)*Math.sin(randomDirection)));
			Vector secondNewPosition = getPosition().subtract(new Vector((getRadius()/2)*Math.cos(randomDirection),(getRadius()/2)*Math.sin(randomDirection)));
			// create asteroids
			firstNewAsteroid = new Asteroid(firstNewPosition,firstNewVelocity,getRadius()/2);
			secondNewAsteroid = new Asteroid(secondNewPosition,secondNewVelocity,getRadius()/2);
		}
		catch (Exception e) {
			// spawn calculations have failed, do nothing.
		}
		// add new asteroids
		getWorld().addAsCollidable(firstNewAsteroid);
		getWorld().addAsCollidable(secondNewAsteroid);	
	}
	
	/**
	 * Symbolic constant registering the density of all asteroids.
	 */
	public static double DENSITY = 2.65e12;

	/**
	 * Return the mass of this asteroid.
	 * 
	 */
	@Override @Basic @Immutable
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Variable registering the mass of this asteroid.
	 */
	public final double mass = (4/3)*Math.PI*Math.pow(getRadius(),3)*DENSITY;
	
	public void collide(Collidable collidable) {
		collidable.collidesWith(this);
	}
	
	public void collidesWith(Ship ship) {
		ship.collidesWith(this);
	}
	
	public void collidesWith(Asteroid asteroid) {
		bounce(asteroid);
	}
	
	public void collidesWith(Bullet bullet) {
		this.terminate();
		bullet.terminate();
	}
	
	/**
	 * Return a textual representation of this asteroid.
	 * 
	 * @return	A string consisting of the textual representation of a collidable and
	 * 			complemented with the mass of this asteroid, separated by spaces and 
	 * 			ended with a square bracket.
	 * 			| result.equals(super.toString() 
	 * 			|	+ " Mass: " + getMass() + "]");
	 */
	@Override
	public String toString(){
		return super.toString() + " Mass: " + getMass() + "]";
	}
	
}
