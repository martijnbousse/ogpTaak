package gameObjects;

//TODO > FINISHED

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import support.Vector;

import be.kuleuven.cs.som.annotate.*;
import asteroids.Util;

/**
 * A class of asteroids as special kinds of collidables involving a mass.
 * 
 * @version	1.0
 * @author 	Martijn Boussé, Wout Vekemans
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
	@Raw
	public Asteroid(Vector position, Vector velocity, double radius) throws IllegalArgumentException {
		super(position, velocity,radius);
	}
	
	/**
	 * Initialize this new asteroid with all default values.
	 * 
	 * @effect 	This new asteroid is initialized with position (10,10), velocity (0,0) and radius 10.
	 * 			| this(new Vector(10,10), new Vector(0,0), 10)
	 */
	public Asteroid() {
		this(new Vector(10,10), new Vector(0,0),10);
	}
	
	/**
	 * Terminate this asteroid as an instance of collidable. In addition, if this asteroid can spawn to smaller asteroids, it will spawn them.
	 */
	@Override
	public void terminate() {
		Set<Asteroid> children = new HashSet<Asteroid>();
		if (canSpawn()) {	
			children= createChildren();
		}
		World oldWorld = getWorld();
		super.terminate();
		for(Asteroid asteroid : children) {
			oldWorld.addAsCollidable(asteroid);
		}
	}
	
	/**
	 * Check whether this asteroid can spawn lesser asteroids.
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
	 * Return a list with the children of this asteroid.
	 * 
	 * @return	Two new smaller asteroids are created and returned in the form of a set.
	 * 			| let
	 * 			|  	firstNewPosition = getPosition().subtract(new Vector(getRadius()/2,0))
	 *			|	secondNewPosition = getPosition().add(new Vector(getRadius()/2,0))
	 *			| 	firstNewVelocity = new Vector(newSpeed*Math.cos(randomDirection),newSpeed*Math.cos(randomDirection))
	 *			|	secondNewVelocity = new Vector(newSpeed*Math.cos(randomDirection+Math.PI),newSpeed*Math.cos(randomDirection+Math.PI))
	 *			| in
	 * 			|	result.contains(new Asteroid(firstNewPosition,firstNewVelocity,getRadius()/2))
	 * 			| 	result.contains(new Asteroid(secondNewPosition,secondNewVelocity,getRadius()/2))
	 */
	public Set<Asteroid> createChildren() {
		Set<Asteroid> children = new HashSet<Asteroid>();
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
			// add new asteroids
			children.add(firstNewAsteroid);
			children.add(secondNewAsteroid);
		}
		catch (Exception e) {
			// spawn calculations have failed, do nothing.
		}
		return children;
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
	
	/**
	 * This asteroid collides with the given collidable.
	 * 
	 * @param	collidable
	 * 			The given collidable.
	 * @effect	The given collidable collides with this asteroid.
	 * 			| collidable.collidesWith(this)
	 */
	@Override
	public void collide(Collidable collidable) {
		collidable.collidesWith(this);
	}
	
	/**
	 * This asteroid collides with the given ship.
	 * 
	 * @param	ship
	 * 			The given ship.
	 * @effect 	The given ship collides with this asteroid.
	 * 			| ship.collidesWith(this)
	 */
	@Override
	protected void collidesWith(Ship ship) {
		ship.collidesWith(this);
	}
	
	/**
	 * This asteroid collides with the given asteroid.
	 * 
	 * @param	asteroid
	 * 			The given asteroid.
	 * @effect 	This asteroid bounces with the given asteroid.
	 * 			| bounce(asteroid)
	 */
	@Override
	protected void collidesWith(Asteroid asteroid) {
		bounce(asteroid);
	}
	
	/**
	 * This asteroid collides with the given bullet.
	 * 
	 * @param	bullet
	 * 			The given bullet.
	 * @effect 	This asteroid and the given bullet are terminated.
	 * 			| this.terminate()
	 * 			| bullet.terminate()
	 */
	@Override
	protected void collidesWith(Bullet bullet) {
		bullet.terminate();
		this.terminate();
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
