package collidable;

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
	//TODO: geen speciale properties?
	public Asteroid(Vector position, Vector velocity, double radius, double mass) {
		super(position, velocity,radius, mass);
	}
}
