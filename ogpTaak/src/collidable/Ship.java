/**
 * Martijn Boussé, 1st master WIT
 * Wout Vekemans, 2nd bach ingenieurswetenschappen cws-elt
 * 
 * repository: https://github.com/martijnbousse/ogpTaak
 */

package collidable;

import asteroids.SumOverflowException;
import asteroids.TimesOverflowException;
import asteroids.Util;
import asteroids.Vector;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ships as special kinds of collidables involving as additional property a direction.
 * 
 * @invar 	The direction of each ship must be a valid direction.
 * 			| isValidDirection(getDirection())
 * 
 * @version 2.0
 * @author Martijn Boussé, Wout Vekemans
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
	 * 
	 * 
	 * 
	 * 
	 * //TODO: dit mag allemaal weg?
	 * @post	The new position of this new ship is equal to the given position.
	 * 			| (new this).getPosition().equals(position)
	 * @post	The new velocity of this new ship is equal to the given velocity.
	 * 			| (new this).getVelocity().equals(velocity) 
	 * @post	The new radius of this new ship is equal to the given radius.
	 * 			| (new this).getRadius() == radius
	 * @throws	IllegalArgumentException
	 * 			This new ship cannot have the given position as its position.
	 * 			| !isValidPosition(position)
	 * @throws	IllegalArgumentException
	 * 			This ship cannot have the given radius as its radius
	 * 			| !isValidRadius(radius)
	 * 
	 * //TODO: dit was niet nodig?
	 * @throws	IllegalArgumentException
	 * 			The x- or y-component of the vector position is not a valid number.
	 * 			| !Vector.isValidNumber(position.getXComponent())
	 * 			| || !Vector.isValidNumber(position.getYComponent())
	 * @throws	IllegalArgumentException
	 * 			The x- or y-component of the vector velocity is not a valid number.
	 * 			| !Vector.isValidNumber(velocity.getXComponent())
	 * 			| || !Vector.isValidNumber(velocity.getYComponent())
	 */
	@Raw
	public Ship(Vector position, Vector velocity, double radius, double mass, double direction) throws IllegalArgumentException {
		super(position,velocity, radius);
		if(!canHaveAsRadius(radius)){
			throw new IllegalArgumentException();	
		}
		setDirection(direction);
		setMass(mass);
	}
	
	private void setMass(double mass) {
		
	}

	/**
	 * Initialize this new ship with all default values.
	 * @effect 	This new ship is initialized with position and velocity (0,0), 
	 * 			the minimal radius as its radius and zero as its direction
	 * 			| this(new Vector(0,0), new Vector(0,0), minRadius, 0)
	 */
	@Raw
	public Ship() {
		this(new Vector(0, 0), new Vector(0, 0), 10, 10, 1);
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
	
	
	
	public double getAcceleration() {
		return getThrusterAmount()/getMass();
	}
	
	public double getThrusterAmount() {
		return this.thrusterAmount;
	}
	
	public void setThrusterAmount(double thrusterAmount) {
		if(isValidThrusterAmount(thrusterAmount)) {
			this.thrusterAmount = thrusterAmount;
		}
	}
	
	public boolean isValidThrusterAmount(double thrusterAmount) {
		return !Double.isNaN(thrusterAmount)
				&& 0 < thrusterAmount;
	}
	
	private double thrusterAmount = 1.1*Math.pow(10,18);
	
	public void setThrusterEnabled(boolean isEnabled) {
		this.isThrusterEnabled = isEnabled;
	}
	// todo: p 472, methodes enableThruster, disableThruster?
	
	public boolean getThrusterEnabled() {
		return this.isThrusterEnabled;
	}
	
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
	}
	
	/**
	 * Return a textual representation of this ship.
	 * 
	 * @return	A string consisting of the textual representation of the position, the velocity, the radius and the direction of this ship,
	 * 			separated by a space and enclosed in square brackets.
	 * 			| result.equals(
	 * 			|	"[" + "Position: " + getPosition().toString() 
	 * 			|		+ " Velocity: " + getVelocity().toString()
	 * 			|		+ " Radius: " + getRadius().toString() 
	 * 			|		+ " Direction: " + getDirection().toString() "]" )
	 */
	@Override
	public String toString(){
		return "[" + "Position: " + getPosition().toString() 
				   + " Velocity: " + getVelocity().toString()
				   + " Radius: " + getRadius() 
				   + " Direction: " + getDirection() + "]";
	}

	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return 0;
	}
}
