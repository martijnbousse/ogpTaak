package collidable;

import asteroids.Vector;
import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @version	1.0
 * @author 	Martijn Boussé, Wout Vekemans
 *
 */
public class Asteroid extends Collidable{

	//TODO: documentatie zie p 470
	public Asteroid(Vector position, Vector velocity, double radius, double mass) {
		super(position, velocity,radius, mass);
	}
}
