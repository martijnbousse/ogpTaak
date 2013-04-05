/**
 * Martijn Bouss�, 1st master WIT
 * Wout Vekemans, 2nd bach ingenieurswetenschappen cws-elt
 * 
 * repository: https://github.com/martijnbousse/ogpTaak
 */

package collidable;

import asteroids.Util;
import asteroids.Vector;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ships as special kinds of collidables involving as additional property a direction.
 * 
 * @invar 	The direction of each ship must be a valid direction.
 * 			| isValidDirection(getDirection())
 * @invar	The mass of each ship must be a valid mass.
 * 			| isValidMass(getMass())
 * 
 * @version 2.0
 * @author Martijn Bouss�, Wout Vekemans
 *
 */
public class Ship extends Collidable implements IShip{
	
	/**
	 * Initialize this new ship with given position, given velocity, given radius, given mass and given direction.
	 * 
	 * @param 	position
	 * 			The position for this new ship.
	 * @param	velocity
	 * 			The velocity for this new ship.
	 * @param 	radius
	 * 			The radius for this new ship.
	 * @param	mass
	 * 			The mass for this new ship.
	 * @param 	direction
	 * 			The direction for this new ship.
	 * @effect	This new ship is initialized as a collidable with the given position, 
	 * 			the given velocity, the given radius and the given mass.
	 * 			| super(position, velocity, radius, mass)
	 * @pre		The given direction must be a valid direction for any ship.
	 * 			| isValidDirection(direction)
	 * @post	The new direction of this new ship is equal to the given direction.
	 * 			| (new this).getDirection() == direction
	 * @post	The new mass of this new ship is equal to the given mass.
	 * 			| (new this).getMass() == mass
	 */
	@Raw
	public Ship(Vector position, Vector velocity, double radius, double mass, double direction) throws IllegalArgumentException {
		super(position,velocity, radius);
		setDirection(direction);
		if (!isValidMass(mass)) // zoals radius in collidable, geen setter want massa verandert nooit.
			throw new IllegalArgumentException();
		this.mass = mass;
	}

	/**
	 * Initialize this new ship with all default values.
	 * 
	 * @effect 	This new ship is initialized with position (10,10), velocity (0,0) ,
	 * 			radius 10 and zero as its direction.
	 * 			| this(new Vector(10,10), new Vector(0,0), 10, 0)
	 */
	@Raw
	public Ship() {
		this(new Vector(10, 10), new Vector(0, 0), 10, 10, 1);
	}
	
	/**
	 * Return the direction of this ship.
	 */
	@Basic
	public double getDirection() {
		return this.direction;
	}
	
	/**
	 * Set the direction of this ship to the given direction.
	 * 
	 * @param 	direction
	 * 			The new direction for this ship.
	 * @pre		The given direction must be a valid direction for any ship.
	 * 			| isValidDirection(direction)
	 * @post 	The new direction of this ship is equal to the given direction.
	 * 			| (new this).getDirection() == direction
	 */
	@Raw
	public void setDirection(double direction) {
		assert isValidDirection(direction);
		this.direction = direction;
	}
	
	/**
	 * Check whether the given direction is a valid direction for any ship.
	 * 
	 * @param 	direction
	 * 			The direction to check.
	 * @return	True if and only if the given direction is a number and if it is in between zero and two times pi.
	 * 			| result == !Double.isNaN(direction)
	 * 			|			&& Util.fuzzyLessThanOrEqualTo(0.0,direction) 
	 * 			|			&& Util.fuzzyLessThanOrEqualTo(direction,2*Math.PI)
	 */
	public static boolean isValidDirection(double direction) {
		return 	!Double.isNaN(direction)
				&& Util.fuzzyLessThanOrEqualTo(0.0, direction)
				&& Util.fuzzyLessThanOrEqualTo(direction, 2*Math.PI);
	}
	
	/**
	 * Variable registering the direction of this ship.
	 */
	private double direction = 0.0;
	
	/**
	 * Returns the mass of this ship.
	 */
	@Override @Basic @Immutable
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Check whether the given mass is a valid mass for any ship.
	 * 
	 * @param	mass
	 * 			The mass to check.
	 * @return	True if and only if the given mass is a number and if it is bigger then zero.
	 * 			| result == !Double.isNaN(mass)
	 * 			|			&& Util.fuzzyLessThanOrEqualTo(0.0,mass)  	
	 */
	public static boolean isValidMass(double mass) {
		return !Double.isNaN(mass)
				&& Util.fuzzyLessThanOrEqualTo(0.0,mass);
	}
	
	/**
	 * Variable registering the mass of this ship.
	 */
	public final double mass; 
	//TODO: ik heb die hier gezet omdat mass volgens mij final is, maar dan krijg je error als die in collidable staat.
	// maar dan hebt ge in elke subklasse in een variable mass?
	
	/**
	 * Return the acceleration of this ship according to Newton's third law.
	 * 
	 * @return	The acceleration of this ship.
	 * 			| result == getThrusterAmount()/getMass()
	 */
	public double getAcceleration() {
		return getThrusterAmount()/getMass();
	}
	
	/**
	 * Returns the thruster amount of this ship.
	 * 
	 * @return	...
	 * 			| ...
	 */
	public double getThrusterAmount() {
		if(isThrusterEnabled()==true)
			return this.thrusterAmount;
		return 0;
	}
	
	/**
	 * Set the thruster amount of this ship to the given thruster amount.
	 * 
	 * @param 	thrusterAmount
	 * 			The given thruster amount.
	 * @post	...
	 * 			| ...
	 */
	public void setThrusterAmount(double thrusterAmount) {
		if(isValidThrusterAmount(thrusterAmount)) {
			this.thrusterAmount = thrusterAmount;
		}
	}
	
	/**
	 * Check whether the given thruster amount is a valid thruster amount for any ship.
	 * 	
	 * @param 	thrusterAmount
	 * 			The thruster amount to check.
	 * @return	True if and only if the given thruster amount is a number and if it is bigger then zero.
	 */
	public boolean isValidThrusterAmount(double thrusterAmount) {
		return !Double.isNaN(thrusterAmount)
				&& (0 < thrusterAmount);
	}
	
	/**
	 * Variable registering the trusher amount of this ship.
	 */
	private double thrusterAmount = 1.1*Math.pow(10,18);
	
	/**
	 * Enable or disable the thruster corresponding to the given flag.
	 * 	
	 * @param 	flag
	 * 			The given flag.
	 * @post	...
	 * 			| ...
	 */
	public void setThrusterEnabled(boolean flag) {
		this.isThrusterEnabled = flag;
	}
	// todo: p 472, methodes enableThruster, disableThruster?
	// TODO heb hier setThrusterEnabled..
	
	/**
	 * Check whether the thruster of this ship is enabled.
	 */
	@Basic
	public boolean isThrusterEnabled() {
		return this.isThrusterEnabled;
	}
	
	/**
	 * Variable registering whether or not the thruster of this ship is enabled.
	 */
	private boolean isThrusterEnabled;
		
	/**
	 * Add the given angle to the direction of this ship.
	 * 
	 * @param 	angle
	 * 			The angle to be added.
	 * @pre		This ship can accept the given angle for turning.
	 * 			| canAcceptForTurn(angle)
	 * @effect	The new direction of this ship is set to the direction of this ship incremented with the given angle.
	 * 			| setDirection(getDirection()+angle)
	 */
	public void turn(double angle) {
		assert canAcceptForTurn(angle);
		setDirection(getDirection()+angle);
	}
	
	/**
	 * Return a boolean reflecting whether this ship can accept the given angle for turning.
	 * 
	 * @param 	angle
	 * 			The angle to be checked.
	 * @return	True if and only if the direction of this ship incremented with the given angle is a valid direction for this ship. 
	 * 			| isValidDirection(getDirection()+angle)
	 */
	public boolean canAcceptForTurn(double angle) {
		return isValidDirection(getDirection()+angle);
	}
	
	/**
	 * Change the velocity of this ship with a given amount. 
	 * 
	 * @param 	amount
	 * 			The amount to add.
	 * @effect	If the given amount is a valid amount then the velocity of this ship is incremented with the amount in the direction which the ship is heading.
	 * 			If this new velocity should exceed the speed limit of this ship then the velocity is scaled until the magnitude is equal to the speed limit.
	 * 			| let
	 * 			| 	newVelocity = velocity.add((new Vector(Math.cos(direction),Math.sin(direction)).scale(amount)))
	 * 			| in
	 * 			| 	if isValidThrustAmount(amount)
	 * 			|		then
	 * 			|			if newVelocity.dotProduct(newVelocity)>this.speedLimit
	 * 			|				then setVelocity(newVelocity.scale(newVelocity.dotProduct(newVelocity)/speedLimit))
	 * 			|			else
	 * 			|				then setVelocity(newVelocity)
	 */
	public void thrust(double amount) {
		if(!isValidThrustAmount(amount))
			amount = 0.0;
		Vector newVelocity = this.getVelocity().add((new Vector(Math.cos(direction),Math.sin(direction)).scale(amount)));
		if(Math.sqrt(newVelocity.dotProduct(newVelocity))>this.getSpeedLimit())
			setVelocity(newVelocity.scale((Double) (this.getSpeedLimit()/Math.sqrt(newVelocity.dotProduct(newVelocity)))));
		else{
			setVelocity(newVelocity);
		}
	}
	
	/**
	 * Returns a boolean whether this ship can accept the given amount to thrust.
	 * 
	 * @param 	amount
	 * 			The amount to check.
	 * @return	True if and only if the given amount is a number and if it is greater than zero.	
	 * 			| result == !Double.isNaN(amount) && (amount > 0)
	 */
	public static boolean isValidThrustAmount(double amount) {
		return	!Double.isNaN(amount)
				&& (amount > 0);
	} //TODO: isValidThrustAmount �n isValidThrusterAmount ?
	
	/**
	 * This ship fires a bullet.
	 * 
	 * @effect 	...
	 * 			| ...
	 */
	public void fireBullet() {
		// TODO: canFireBullet houdt rekening met: initiele positie al ingenomen en partially located outside of the world.
		// TODO: nieuwe bullet aanmaken, juiste parameters meegeven via parameters this ship en toevoegen aan de lijst met collidables in world. (setWorld())
	}
	
	/**
	 * Return a textual representation of this ship.
	 * 
	 * @return	A string consisting of the textual representation of a collidable and
	 * 			complemented with the mass and direction of this ship, separated by
	 * 			spaces and ended with a square bracket.
	 * 			| result.equals(super.toString() 
	 * 			|	+ " Mass: " + getMass() + " Direction: " + getDirection() + "]");
	 */
	@Override
	public String toString(){
		return super.toString() + " Mass: " + getMass() +  " Direction: " + getDirection() + "]";
	}
}
