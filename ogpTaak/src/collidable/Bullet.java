package collidable;

import be.kuleuven.cs.som.annotate.Basic;
import asteroids.Vector;

/**
 * A class of bullets as special kinds of collidables involving as additional property an association with ships.
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
	 * @post	The new souce of this new bullet is equal to the given source.
	 * 			| (new this).getSource() == source
	 */
	public Bullet(Vector position, Vector velocity, double radius, double mass, Ship source) {
		super(position, velocity, radius, mass);
		setSource(source);
	}
	
	/**
	 * Returns the source of this bullet.
	 */
	@Basic
	public Ship getSource() {
		return this.source;
	}
	
	
	// TODO: oppassen met de implementatie van setSource()! Beschouwen we dit nu als uni of bidirectionele assocatie? 
	//			indien bidirectioneel, dan moet setSource afgeschermd worden.
	// TODO: is bidirectioneel nodig? moet je aan een ship kunnen vragen wat/waar al zijn bullets zijn?
	// TODO: eender welke associatie, we kunnen best bullet de controllerende klasse maken.
	// TODO: je hebt een setSource nodig denk ik aangezien je ook een terminate moet hebben die de associatie afbreekt? source = null
	
	// TODO: gemaakt op basis van p 338, buiten geen exceptions
	
	/**
	 * Set the source for this bullet to the given source.
	 * 
	 * @param 	source
	 * 			The new source for this bullet.
	 * @post	The new source for this bullet is the same as the given source.
	 * 			| (new this).getSource() == source
	 */
	public void setSource(Ship source) {
		this.source = source;
	}
	
	
	/**
	 * Variable registering the source of this bullet.
	 */
	private Ship source; //TODO: final? want source mag nooit veranderen, buiten als je terminate.

}
