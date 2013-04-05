package collidable;

import be.kuleuven.cs.som.annotate.*;
import asteroids.Vector;

/**
 * A class of asteroids as special kinds of collidables involving no additional properties ?
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
	public Asteroid(Vector position, Vector velocity, double radius) {
		super(position, velocity,radius);
	}
	
	/**
	 * Symbolic constant registering the density of all asteroids.
	 */
	public static double DENSITY = 2.65e-12;

	/**
	 * Return the mass of this asteroid.
	 * 
	 */
	//TODO: overflow
	@Override @Basic @Immutable
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Variable registering the mass of this asteroid.
	 */
	public final double mass = (4/3)*Math.PI*Math.pow(getRadius(),3)*DENSITY; 
	
	/**
	 * Terminate this asteroid as an instance of collidable. In addition ...
	 * 
	 * 
	 */
	@Override
	public void terminate() {
		super.terminate();
		//TODO: zie 1.7
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
