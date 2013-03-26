package collidable;

import asteroids.Vector;

/**
 * 
 * @version	1.0
 * @author 	Martijn Boussé, Wout Vekemans
 *
 */
public class Bullet extends Collidable {

	public Bullet(Vector position, Vector velocity, double radius, double mass) {
		super(position, velocity, radius, mass);
		
	}

}
