package asteroids;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ships involving a position, a velocity, a direction and a radius.
 * 
 * @invar 	The position of each ship must be a valid position.
 * 			| isValidPosition(getPosition())
 * @invar	Each ship can have its velocity as its velocity.
 * 			| canHaveAsVelocity(getVelocity())
 * @invar	Each ship can have its speed limit as its speed limit.
 * 			| canHaveAsSpeedLimit(getSpeedLimit())
 * @invar 	The direction of each ship must be a valid direction.
 * 			| isValidDirection(getDirection())
 * @invar	Each ship can have its radius as its radius.
 * 			| canHaveAsRadius(getRadius())
 * @invar	The minimum radius that applies to all ships must be a valid minimum radius.
 * 			| isValidMinRadius(getMinRadius())
 *
 * @version 1.0
 * @author Martijn Boussé, Wout Vekemans
 *
 */
public class Ship implements IShip{
	
	/**
	 * Initialize this new ship with given position, given velocity, given direction and given radius.
	 * 
	 * @param 	position
	 * 			The position for this new ship.
	 * @param	velocity
	 * 			The velocity for this new ship.
	 * @param 	direction
	 * 			The direction for this new ship.
	 * @param 	radius
	 * 			The radius for this new ship.
	 * @pre		The given direction must be a valid direction for any ship.
	 * 			| isValidDirection(direction)
	 * @post	The new position of this new ship is equal to the given position.
	 * 			| (new this).getPosition().equals(position)
	 * @post	The new velocity of this new ship is equal to the given velocity.
	 * 			| (new this).getVelocity().equals(velocity) 
	 * @post	The new direction of this new ship is equal to the given direction.
	 * 			| (new this).getDirection() == direction
	 * @post	The new radius of this new ship is equal to the given radius.
	 * 			| (new this).getRadius() == radius
	 * @throws	IllegalArgumentException
	 * 			This new ship cannot have the given position as its position.
	 * 			| !isValidPosition(position)
	 * @throws	IllegalArgumentException
	 * 			This ship cannot have the given radius as its radius
	 * 			| !isValidRadius(radius)
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
	public Ship(Vector position, Vector velocity, double radius, double direction) throws IllegalArgumentException {
		setSpeedLimit(SPEED_OF_LIGHT);
		setPosition(position);
		setVelocity(velocity);
		if(!canHaveAsRadius(radius)){
			throw new IllegalArgumentException();	
		}
		this.radius=radius;		
		setDirection(direction);
	}
	
	/**
	 * Initialize this new ship with all default values.
	 * @effect 	This new ship is initialized with position and velocity (0,0), 
	 * 			the minimal radius as its radius and zero as its direction
	 * 			| this(new Vector(0,0), new Vector(0,0), minRadius, 0)
	 */
	@Raw
	public Ship() {
		this(new Vector(0, 0), new Vector(0, 0), minRadius, 0);
	}
	
	/**
	 * Return the position of this ship.
	 */
	@Basic
	public Vector getPosition() {
		return this.position;
	}
	
	/**
	 * Set the position of this ship to the given position.
	 * 
	 * @param	position 
	 * 	      	The new position for this ship.
	 * @post 	The new position of this ship is equal to the given position.
	 * 			| (new this).getPosition().equals(position)
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position.
	 * 			| !isValidPosition(position)
	 */
	@Raw
	public void setPosition(Vector position) throws IllegalArgumentException {
		if (!isValidPosition(position)) 
			throw new IllegalArgumentException();	
		this.position = position;
	}
	
	
	/**
	 * Check whether the given position is a valid position for any ship.
	 * 
	 * @param 	position
	 * 			The position to check.
	 * @return 	True if and only if the given position is effective.
	 * 			| result == (position != null)
	 */
	public static boolean isValidPosition(Vector position) {
		return (position != null);
	}
	
	/**
	 * Variable registering the position of this ship.
	 */
	private Vector position;
	
	/**
	 * Return the velocity of this ship.
	 */
	@Basic
	public Vector getVelocity() {
		return this.velocity;
	}
	
	/**
	 * Set the velocity of this ship to the given velocity.
	 * 
	 * @param	velocity 
	 * 	      	The new velocity for this ship.
	 * @post 	If the given velocity is a valid velocity then the new velocity of this ship is equal to the given velocity.
	 * 			| if isValidVelocity(velocity)
	 * 			| 	then (new this).getVelocity().equals(velocity)
	 */
	@Raw
	public void setVelocity(Vector velocity) {
		if (canHaveAsVelocity(velocity))
			this.velocity = velocity;
	}
	
	/**
	 * Check whether this ship can have the given velocity as its velocity.
	 * 
	 * @param 	velocity
	 * 			The velocity to check.
	 * @return	True if and only if velocity is effective and if the speed of the given velocity is bigger then or equal to zero
	 * 			and smaller then or equal to the speed limit.
	 * 			| result == !( velocity == null
	 * 			|			&& Util.fuzzyLessThanOrEqualTo(0.0,velocity.dotProduct(velocity))
	 * 			|			&& Util.fuzzyLessThanOrEqualTo(velocity.dotProduct(velocity),this.speedLimit) )
	 */
	@Raw
	public boolean canHaveAsVelocity(Vector velocity) {
		return 	(velocity != null)
				&& Util.fuzzyLessThanOrEqualTo(0.0,Math.sqrt(velocity.dotProduct(velocity)))
				&& Util.fuzzyLessThanOrEqualTo(Math.sqrt(velocity.dotProduct(velocity)),getSpeedLimit());	
	}
	
	/**
	 * Variable registering the velocity of this ship.
	 */
	private Vector velocity;
	
	/**
	 * Returns the speed limit of this ship.
	 */
	@Basic @Raw
	public double getSpeedLimit() {
		return this.speedLimit;
	}
	
	/**
	 * Set the speed limit of this ship to the given limit.
	 * 
	 * @param 	newLimit
	 * 			The new speed limit for this ship.
	 * @post	If the given speed limit is a valid speed limit then the new speed limit of this ship is equal to the given speed limit.
	 * 			| if canHaveAsSpeedLimit(speedLimit)
	 * 			|	then (new this).getSpeedLimit() == speedLimit
	 */
	@Raw
	public void setSpeedLimit(double speedLimit) {
		if(canHaveAsSpeedLimit(speedLimit))
			this.speedLimit = speedLimit;
	}
	
	/**
	 * Check whether this ship can have the given speed limit as its speed limit.
	 * 
	 * @param 	speedLimit
	 * 			The speed limit to check.
	 * @return	True if and only if the given speed limit is a number and if it is less than the speed of light.
	 * 			| result == !Double.isNaN(speedLimit)
	 * 			|			&& Util.fuzzyLessThanOrEqualTo(speedLimit,SPEED_OF_LIGHT)
	 * 			|			&& Util.fuzzyLessThanOrEqualTo(0.0,speedLimit)
	 */
	@Raw
	public boolean canHaveAsSpeedLimit(double speedLimit) {
		return 	!Double.isNaN(speedLimit) 
				&& Util.fuzzyLessThanOrEqualTo(speedLimit,SPEED_OF_LIGHT)
				&& Util.fuzzyLessThanOrEqualTo(0.0,speedLimit);
	}
	
	/**
	 * Variable registering the speed limit of this ship.
	 */
	private double speedLimit;
	
	/**
	 * Symbolic constant registering the speed of light.
	 */
	public static double SPEED_OF_LIGHT = 300000.0; 
	
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
	 * @Pre		The given direction must be a valid direction for any ship.
	 * 			| isValidDirection(direction)
	 * @Post 	The new direction of this ship is equal to the given direction.
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
	 * Returns the minimum radius for all ships.
	 */
	@Basic @Immutable @Raw
	public static double getMinRadius() {
		return minRadius;
	}
	
	/**
	 * Set the minimum radius that applies to all ships to the given minimum radius.
	 * 
	 * @param 	minRadius
	 * 			The new minimum radius for all ships.
	 * @post	The new minimum radius that applies to all ships is set to the given minimum radius.
	 * 			| (new Ship).minRadius == minRadius
	 * @throws 	IllegalArgumentException
	 * 			The given minimum radius is not a valid minimum radius.
	 * 			| !isValidMinRadius(minRadius)
	 */
	public static void setMinRadius(double minRadius) throws IllegalArgumentException {
		if(!isValidMinRadius(minRadius))
			throw new IllegalArgumentException();
		Ship.minRadius = minRadius;
	}
	
	/**
	 * Check whether the given minimum radius is a valid minimum radius for any ship.
	 * 
	 * @param 	minRadius
	 * 			The minimum radius to check.
	 * @return	True if and only if the given minimum radius is a number and if it is greater than zero.
	 * 			| result == !Doulbe.isNaN(minRadius)
	 * 			|			&& (minRadius > 0)
	 */
	public static boolean isValidMinRadius(double minRadius) {
		return 	!Double.isNaN(minRadius)
				&& (minRadius > 0);
	}
	
	/**
	 * Variable registering the minimum radius that applies to all ships.
	 */
	private static double minRadius = 10.0;
	
	/**
	 * Return the radius of this ship.
	 */
	@Basic @Immutable
	public double getRadius() {
		return radius;
	}
	
	/**
	 * Check whether the given radius is a valid radius for this ship.
	 * 
	 * @param 	radius
	 * 			The radius to check.
	 * @return	True if and only if the given radius is a number and if it is greater than the mininum radius.
	 * 			| result == !Doulbe.isNaN(minRadius)
	 * 			|			&& (radius >= minRadius)
	 */
	@Raw
	public boolean canHaveAsRadius(double radius) {	
		return 	!Double.isNaN(radius)
				&& (Util.fuzzyLessThanOrEqualTo(getMinRadius(),radius));
	}
	
	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius;
		
	/**
	 * Check whether the given time is a valid time for any ship.
	 * 
	 * @param 	dt
	 * 			The time to check.
	 * @return	True if and only if the given time is greater then or equal to zero.
	 * 			| result == (Util.fuzzyLessThanOrEqualTo(0,dt))
	 */
	public static boolean isValidTime(double dt) {
		return  !Double.isNaN(dt)
				&& Util.fuzzyLessThanOrEqualTo(0,dt);
	}
	
	/**
	 * Move this ship for the given amount of time.
	 * 
	 * @param 	dt
	 * 			The amount of time to move.
	 * @effect	The new position of this ship is set to position of this ship incremented with the velocity of this ship, 
	 * 			which is scaled with the given amount of time.
	 * 			| setPosition(getPosition().add(getVelocity.scale(dt)))
	 * @throws 	IllegalArgumentException
	 * 			This ship cannot accept the given amount of time to move.  
	 * 			| !isValidTime(time)
	 */
	public void move(double dt) throws IllegalArgumentException {
		if (!isValidTime(dt))
			throw new IllegalArgumentException();
		try{
			setPosition(getPosition().add(getVelocity().scale(dt)));
		} catch(SumOverflowException exc) {
			setPosition(getPosition());
		} 
		catch(TimesOverflowException exc2) {
			setPosition(getPosition());
		}
	}
	
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
		Vector newVelocity = this.velocity.add((new Vector(Math.cos(direction),Math.sin(direction)).scale(amount)));
		if(Math.sqrt(newVelocity.dotProduct(newVelocity))>this.speedLimit)
			setVelocity(newVelocity.scale((Double) (this.speedLimit/Math.sqrt(newVelocity.dotProduct(newVelocity)))));
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
	 * Returns the distance between this ship and the given ship.
	 * 
	 * @param  	other
	 * 			The other ship.
	 * @return	Returns the distance between this ship and the given ship. If this ship is equal to the given ship the result is always zero.
	 * 			| let 
	 * 			|	delta = this.getPosition().subtract(other.getPosition())
	 * 			| in
	 * 			| 	if(other.equals(this))
	 * 			|		result == 0.0
	 * 			| 	else 	
	 * 			|		result == Math.sqrt(delta.times(delta)) - this.getRadius() - other.getRadius()
	 * @throws 	IllegalArgumentException
	 * 			The given ship is not effective.
	 * 			| (ship == null)
	 */
	public double getDistanceBetween(Ship other) throws IllegalArgumentException {
		if (other == null)
			throw new IllegalArgumentException("Non effective ship!");
		if(other.equals(this))
			return 0.0;
		try{
			Vector delta = this.getPosition().subtract(other.getPosition());
			return Math.sqrt(delta.dotProduct(delta)) - this.getRadius() - other.getRadius();
		} catch(TimesOverflowException exc){
			return Double.POSITIVE_INFINITY;
		}
	}
	
	/**
	 * Returns a boolean reflecting whether this ship and the given ship overlap.
	 * 
	 * @param 	other
	 * 			The other ship.
	 * @return	True if and only if the distance between this ship and the given ship is negative or if the given ship is equal to this ship.
	 * 			| result == (getDistanceBetween(ship) < 0) || (other.equals(this))  
	 * @throws 	IllegalArgumentException 
	 * 			The given ship is not effective.
	 * 			| (ship == null)
	 */
	public boolean overlap(Ship other) throws IllegalArgumentException {
		if (other == null)
			throw new IllegalArgumentException("Non effective ship!");
		if (other.equals(this))
			return true;
		return (getDistanceBetween(other) < 0);
	}
	
	/**
	 * Returns when this ship, if ever, will collide with the given ship.
	 * 
	 * @param 	other
	 * 			The other ship.
	 * @return	Returns the time until this ship and the given ship will collide.
	 * 			| let 
	 * 			|	sigma 	= this.getRadius() + other.getRadius()
	 * 			| 	deltaR 	= getPosition().subtract(other.getPosition())
	 *			| 	deltaV 	= getVelocity().subtract(other.getVelocity())
	 *			| 	dotProductR 	= deltaR.times(deltaR)
	 *			|	dotProductV 	= deltaV.times(deltaV)
	 *			|	dotProductVR 	= deltaV.times(deltaR)
	 *			|	d = (dotProductVR*dotProductVR) - dotProductV*(dotProductR-(sigma*sigma))
	 *			| in
	 *			|	if (Util.fuzzyLessThanOrEqualTo(0.0,dotProductVR))
	 *			|		result == Double.POSITIVE_INFINITY
	 *			|	else if (Util.fuzzyLessThanOrEqualTo(d,0.0))
	 *			| 		result == Double.POSITIVE_INFINITY
	 *			|	else
	 *			|		result == -(dotProductVR+Math.sqrt(d))/dotProductV)
	 * @throws 	IllegalArgumentException
	 * 			The given ship is not effective.
	 * 			| (ship == null)
	 */
	public double getTimeToCollision(Ship other) throws IllegalArgumentException{
		if (other == null)
			throw new IllegalArgumentException("Non effective ship!");
		
		Vector deltaR = other.getPosition().subtract(this.getPosition());
		Vector deltaV = other.getVelocity().subtract(this.getVelocity());
		
		try {
			double sigma = this.getRadius() + other.getRadius();
			double dotProductR = deltaR.dotProduct(deltaR);
			double dotProductV = deltaV.dotProduct(deltaV);
			double dotProductVR = deltaV.dotProduct(deltaR);
			double d = (dotProductVR*dotProductVR) - dotProductV*(dotProductR-(sigma*sigma));
			
			if (Util.fuzzyLessThanOrEqualTo(0,dotProductVR))
				return Double.POSITIVE_INFINITY;
			else if (Util.fuzzyLessThanOrEqualTo(d,0))
				return Double.POSITIVE_INFINITY;
			else
				try {
					return (-(dotProductVR+Math.sqrt(d))/dotProductV);
				} catch (ArithmeticException exc) {
					return Double.POSITIVE_INFINITY;
				}	
		} catch (TimesOverflowException exc) {
			return Double.POSITIVE_INFINITY;
		}
	}
	
	/**
	 * Returns the position where this ship and the given ship will collide.
	 * 
	 * @param 	other
	 * 			The other ship.
	 * @return	Returns the position where this ship and the given ship will collide.
	 * 			| let
	 * 			| 	dt = getTimeToCollision(other)
	 *			|	newPositionThis = this.getPosition().add(this.getVelocity().scale(dt))
	 *			|	newPositionOther = other.getPosition().add(other.getVelocity().scale(dt))
	 *			|	theta = Math.atan2(newPositionOther.getYComponent()-newPositionThis.getYComponent(),
	 *			|						newPositionOther.getXComponent()-newPositionThis.getXComponent())
	 *			|   directionRadius = new Vector(Math.cos(theta),Math.sin(theta));
	 *			| in
	 *			|	if dt == Double.POSITIVE_INFINITY
	 *			|		then result == null
	 *			|   if newPositionOther.getXComponent()-newPositionThis.getXComponent()<0
	 *			|		then theta+= Math.PI*2
	 *			|	result == newPositionThis.add(directionRadius.scale(this.getRadius()))
	 * @throws	IllegalArgumentException
	 * 			The given ship is not effective.
	 * 			| (ship == null)
	 */
	public Vector getCollisionPosition(Ship other) throws IllegalArgumentException{
		if (other == null)
			throw new IllegalArgumentException("Non effective ship!");	
		double dt = getTimeToCollision(other); 
		if (dt == Double.POSITIVE_INFINITY)
			return null;
		
		Vector newPositionThis = this.getPosition().add(this.getVelocity().scale(dt));
		Vector newPositionOther = other.getPosition().add(other.getVelocity().scale(dt));
		
		double theta = Math.atan2(newPositionOther.getYComponent()-newPositionThis.getYComponent(),
									newPositionOther.getXComponent()-newPositionThis.getXComponent());
		
		if(newPositionOther.getXComponent()-newPositionThis.getXComponent()<0)
			theta+= Math.PI*2;

		Vector directionRadius = new Vector(Math.cos(theta),Math.sin(theta));
		return newPositionThis.add(directionRadius.scale(this.getRadius()));	
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
}
