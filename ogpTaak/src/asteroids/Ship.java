package asteroids;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ships involving a position, a velocity, a direction and a radius.
 * 
 * @invar	Each ship can have its speed limit as its speed limit.
 * 			| canHaveAsSpeedLimit(speedLimit)
 * @invar	Each ship can have its radius as its radius
 * 			| canHaveAsRadius(radius)
 * @invar	The minimum radius that applies to all ships must be a valid minimum radius.
 * 			| isValidMinRadius(minRadius)
 * @invar	Each ship can have its velocity as its velocity
 * 			| canHaveAsVelocity(velocity)
 * @invar 	The position that applies to all ships must be a valid position.
 * 			| isValidPosition(position)
 * @invar 	The direction that applies to all ships must be a valid direction.
 * 			| isValidDirection(direction)
 * 
 *  //TODO: invarianten nakijken
 * 
 * @version 1.0
 * @author Martijn Boussé, Wout Vekemans
 *
 */
public class Ship implements IShip{
	
	//TODO: nieuwe excepties aanmaken? Of enkel illegalArgument gebruiken?
	//TODO: reasoning about floating point numbers -> util fuzzy's 
	//TODO: opgave p.3 laatste paragraaf van 1.1 in orde? mail sturen/forum
	
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
	 * @effect	| this.setVelocity(velocity)
	 * @post	The new direction of this new ship is equal to the given direction.
	 * 			| new.getDirection() == direction
	 * @post	The new radius of this new ship is equal to the given radius.
	 * 			| (new this).getRadius() == radius
	 * @throws	IllegalArgumentException
	 * 			This new ship cannot have the given position as its position.
	 * 			| !isValidPosition(position)
	 * @throws	IllegalArgumentException
	 * 			This ship cannot have the given radius as its radius
	 * 			| !isValidRadius(radius)
	 */
	@Raw
	public Ship(Vector position, Vector velocity, double radius, double direction) throws IllegalArgumentException{
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
	 * 			|this(new Vector(0,0), new Vector(0,0), minRadius, 0)
	 */
	public Ship(){
		this(new Vector(0, 0), new Vector(0, 0), minRadius, 0);
	}
	
	/**
	 * Return the position of this ship.
	 */
	@Basic
	public Vector getPosition(){
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
	 * 			The given position is not effective.
	 * 			| !isValidPosition(position)
	 */
	// TODO: @raw ?
	public void setPosition(Vector position) throws IllegalArgumentException{
		if ( !isValidPosition(position) ) 
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
	public static boolean isValidPosition(Vector position){
		return ( !Double.isNaN(position.getXComponent()) 
				 && !Double.isNaN(position.getYComponent()) 
				 && position != null );
	}
	
	/**
	 * Variable registering the position of this ship.
	 */
	private Vector position;
	
	/**
	 * Return the velocity of this ship.
	 */
	@Basic
	public Vector getVelocity(){
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
	// TODO: @raw ?
	public void setVelocity(Vector velocity){
		if (canHaveAsVelocity(velocity)) {
			this.velocity = velocity;
		}
	}
	
	/**
	 * Check whether this ship can have the given velocity as its velocity.
	 * 
	 * @param 	velocity
	 * 			The velocity to check.
	 * @return	True if and only if velocity is effective and if the speed of the given velocity is bigger then or equal to zero
	 * 			and smaller then or equal to the speed limit.
	 * 			| result == !( velocity == null
	 * 						&& Util.fuzzyLessThanOrEqualTo(0.0,velocity.getMagnitude())
	 * 						&& Util.fuzzyLessThanOrEqualTo(velocity.getMagnitude(),this.speedLimit) )
	 */
	public boolean canHaveAsVelocity(Vector velocity){
		return 	!Double.isNaN(velocity.getXComponent())
				&& !Double.isNaN(velocity.getYComponent())
				&& (velocity != null)
				&& Util.fuzzyLessThanOrEqualTo(0.0,velocity.dotProduct(velocity))
				&& Util.fuzzyLessThanOrEqualTo(velocity.dotProduct(velocity),this.speedLimit);
				
	}
	
	/**
	 * Variable registering the velocity of this ship.
	 */
	private Vector velocity;
	
	public void setSpeedLimit(double newLimit){
		if(canHaveAsSpeedLimit(newLimit)){
			this.speedLimit = newLimit;
		}
	}
	
	/**
	 * Returns the speed limit of this ship.
	 */
	@Basic
	public double getSpeedLimit() {
		return this.speedLimit;
	}
	
	/**
	 * Check whether this ship can have the given speed limit as its speed limit.
	 * @param 	newLimit
	 * 			The limit to check;
	 * @return	True if and only if the given speed limit is less than the speed of light.
	 * 			| result == Util.fuzzyLessThanOrEqualTo(newLimit,SPEED_OF_LIGHT)
	 */
	//TODO: fuzzy in doc?
	public boolean canHaveAsSpeedLimit(double newLimit){
		return 	!Double.isNaN(newLimit) 
				&& Util.fuzzyLessThanOrEqualTo(newLimit,SPEED_OF_LIGHT);
	}
	
	/**
	 * Variable registering the speed limit of 
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
	public double getDirection(){
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
	public void setDirection(double direction){		//TODO: in asteroids w -PI/2 gegeven als direction, wat doen we hiermee
		//assert isValidDirection(direction);
		this.direction = direction;
	}
	
	/**
	 * Check whether the given direction is a valid direction for any ship.
	 * 
	 * @param 	direction
	 * 			The direction to check.
	 * @return	True if and only if the given direction is in between zero and two times pi.
	 * 			| result == Util.fuzzyLessThanOrEqualTo(0.0, direction) 
	 * 						&& Util.fuzzyLessThanOrEqualTo(direction, 2*Math.PI)
	 */
	public static boolean isValidDirection(double direction){
		return 	!Double.isNaN(direction)
				&& Util.fuzzyLessThanOrEqualTo(0.0, direction) 
				&& Util.fuzzyLessThanOrEqualTo(direction, 2*Math.PI);
	}
	
	/**
	 * Variable registering the direction of this ship.
	 */
	private double direction = 0.0;
	
	/**
	 * 
	 * @param 	newMinRadius
	 * 			The new value for the minRadius
	 * @post	The minRadius for all ships is set to the given value.
	 * 			| (new Ship).minRadius == newMinRadius
	 * @throws 	IllegalArgumentException
	 * 			The given value for minRadius is invalid for this parameter.
	 * 			| ! isValidMinRadius(newMinRadius)
	 */
	public static void setMinRadius(double newMinRadius) throws IllegalArgumentException{
		if(!isValidMinRadius(newMinRadius))
			throw new IllegalArgumentException();
		minRadius = newMinRadius;
	}
	
	/**
	 * Check whether the given minimum radius is a valid minimum radius for any ship.
	 * @param 	minRadius
	 * 			The minimum radius to check.
	 * @return	True if and only if the given minimum radius is greater than zero.
	 * 			| result == minRadius > 0
	 */
	//TODO: hoe gebruiken we hier Util?
	public static boolean isValidMinRadius(double minRadius){
		return 	!Double.isNaN(minRadius)
				&& minRadius > 0;
	}
	
	/**
	 * Returns the minimum radius for all ships.
	 */
	@Basic
	public static double getMinRadius(){
		return minRadius;
	}
	
	/**
	 * Variable registering the minimum radius of all ships.
	 */
	private static double minRadius = 10.0;
	
	/**
	 * Check whether the given radius is a valid radius for the ship.
	 * @param 	radius
	 * 			the radius to check
	 * @return	True if and only if the given radius is greater than the mininum radius.
	 * 			| result == radius >= minRadius
	 */
	public boolean canHaveAsRadius(double radius){	
		//TODO raw?
		return 	!Double.isNaN(radius)
				&& (Util.fuzzyLessThanOrEqualTo(minRadius,radius));
	}
	
	/**
	 * Return the radius of this ship.
	 */
	@Basic @Raw @Immutable
	public double getRadius(){
		return radius;
	}
	
	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius;
	
	/**
	 * Move this ship for the given amount of time.
	 * 
	 * @param 	dt
	 * 			The amount of time to move.
	 * @effect	... via setPosition
	 * @throws 	IllegalArgumentException
	 * 			This ship cannot accept the given amount of time to move.
	 * 			| ! canAcceptforMove(time)
	 */
	public void move(double dt) throws IllegalArgumentException{
		if (!canAcceptForMove(dt))
			throw new IllegalArgumentException();
		try{
//			setPosition(new Vector(getPosition().getXComponent()+getVelocity().getXComponent()*dt,
//										getPosition().getYComponent()+getVelocity().getYComponent()*dt));
			
			setPosition(this.getPosition().add(this.getVelocity().scale(dt)));
			//add(getPosition()+timesFactor(getVelocity,dt))
		} catch(ArithmeticException exc){
			//TODO: implementatie + sumOverflowException -> geen arithmetic
		}
	}
	
	public boolean canAcceptForMove(double dt){
		return Util.fuzzyLessThanOrEqualTo(0,dt);
				//TODO: ook nul wordt niet toegelaten!
				//TODO: time moet altijd groter zijn dan nul, zie ook thrust, misschien kan dit gezet worden als invariant + isValidTime() (static)
			 	// canAcceptForMove gebruikt dan isValidTime() + een specifieke voorwaarde (zie dependent properties in hb)
	}
	
	
	/**
	 * Return a boolean reflecting whether this ship can accept the given angle for turning.
	 * 
	 * @param 	angle
	 * 			The angle to be checked.
	 * @return	True if and only if the direction of this ship incremented with the given angle is a valid direction for any ship.
	 * 			| isValidDirection(getDirection()+angle)
	 */
	public boolean canAcceptForTurn(double angle){
		return isValidDirection(getDirection()+angle);
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
	public void turn(double angle){
		//assert canAcceptForTurn(angle);
		setDirection(getDirection() + angle);
	}
	// in praktijk wordt iets wat constant wordt aangepast eerder totaal geimplementeerd. 
		//in het geval van direction kan een totale implementatie met modulo gemaakt worden.
	//TODO het is toch gevraagd om het nominaal te doen ?
	
	
	/**
	 * Change the velocity of this ship with a given amount. 
	 * @param 	amount
	 * 			The amount to add.
	 * @post	The given amount was added to the velocity of the ship, in the direction the ship is heading.
	 * 			| new.getVelocity().getXcomponent() == 
	 * 			|	this.getVelocity().getXComponent()+amount*Math.cos(this.getDirection())
	 * 			| new.getVelocity().getYcomponent() == 
	 * 			|	this.getVelocity().getYComponent()+amount*Math.sin(this.getDirection())
	 */
	public void thrust(double amount){
		if(!canAcceptAsThrustAmount(amount)){
			amount = 0;
		}
		this.velocity=new Vector(velocity.getXComponent()+amount*Math.cos(direction),velocity.getYComponent()+amount*Math.sin(direction));
	}
	
	/**
	 * Returns a boolean whether this ship can accept the given amount to thrust.
	 * @param 	amount
	 * 			The amount to check
	 * @return	True if and only if the given amount is greater than zero	
	 * 			| result == amount > 0
	 */
	public boolean canAcceptAsThrustAmount(double amount){
		return amount > 0;
	}
	
	/**
	 * Returns the distance between this ship and the given ship.
	 * @param  	other
	 * 			The other ship.
	 * @return	Returns the distance between this ship and the given ship. If this ship is equal to the given ship the result is always zero.
	 * 			| let 
	 * 			|	delta = this.getPosition().subtract(other.getPosition())
	 * 			| in
	 * 			| 	if(other.equals(this))
	 * 			|		result == 0
	 * 			| 	else 	
	 * 			|		result == Math.sqrt(delta.times(delta)) - this.getRadius() - other.getRadius()
	 * @throws 	IllegalArgumentException
	 * 			The given ship is not effective.
	 * 			| (ship == null)
	 */
	public double getDistanceBetween(Ship other) throws IllegalArgumentException{
		if (other == null)
			throw new IllegalArgumentException("Non effective ship!");
		if(other.equals(this))
			return 0;
		try{
			Vector delta = this.getPosition().subtract(other.getPosition());
			return Math.sqrt(delta.dotProduct(delta)) - this.getRadius() - other.getRadius();
		} catch(TimesOverflowException exc){
			return 0;
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
	public boolean overlap(Ship other) throws IllegalArgumentException{
		if (other.equals(this))
			return true;
		return getDistanceBetween(other) < 0;
	}
	
	/**
	 * Returns when this ship, if ever, will collide with the given ship.
	 * 
	 * @param 	other
	 * 			The other ship.
	 * @return	Returns the time until this ship and the given ship will collide.
	 * 			| let 
	 * 			| 	deltaR = getPosition().subtract(other.getPosition())
	 *			| 	deltaV = getVelocity().subtract(other.getVelocity())
	 *			| 	a = deltaR.times(deltaR)
	 *			|	b = deltaV.times(deltaV)
	 *			|	c = deltaV.times(deltaR)
	 *			|	d = c*c - (b)*(a-sigma*sigma)
	 *			| in
	 *			|	if (Util.fuzzyLessThanOrEqualTo(0,c))
	 *			|		result == Double.POSITIVE_INFINITY
	 *			|	else if (Util.fuzzyLessThanOrEqualTo(d,0))
	 *			| 		result == Double.POSITIVE_INFINITY
	 *			|	else
	 *			|		result == -(c+Math.sqrt(d))/b
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
	 * @param 	other
	 * 			The other ship.
	 * @return	Returns the position where this ship and the given ship will collide.
	 * 			| let
	 * 			| 	deltaT = getTimeToCollision(other)
	 *			|	newPositionThis = new Vector(this.getPosition().getXComponent()+deltaT*this.getVelocity().getXComponent(),
	 *			| 											this.getPosition().getYComponent()+deltaT*this.getVelocity().getYComponent())
	 *			|	newPositionOther = new Vector(other.getPosition().getXComponent()+deltaT*other.getVelocity().getXComponent(),
	 *			|											other.getPosition().getYComponent()+deltaT*other.getVelocity().getYComponent())
	 *			|	theta = Math.atan((newPositionOther.getYComponent()-newPositionThis.getYComponent())/(newPositionOther.getXComponent()-newPositionThis.getXComponent()))
	 *			|   directionRadius = new Vector(Math.cos(theta),Math.sin(theta));
	 *			| in
	 *			|	result == newPositionThis.add(directionRadius.scale(this.getRadius()))
	 * @throws	IllegalArgumentException
	 * 			The given ship is not effective.
	 * 			| (ship == null)
	 */
	public Vector getCollisionPosition(Ship other) throws IllegalArgumentException{ //TODO: arithmetic exceptions?
		if (other == null)
			throw new IllegalArgumentException("Non effective ship!");	
		double deltaT = getTimeToCollision(other); 
		if (deltaT == Double.POSITIVE_INFINITY)
			return null;
		
		Vector newPositionThis = new Vector(this.getPosition().getXComponent()+deltaT*this.getVelocity().getXComponent(),
								this.getPosition().getYComponent()+deltaT*this.getVelocity().getYComponent());
		Vector newPositionOther = new Vector(other.getPosition().getXComponent()+deltaT*other.getVelocity().getXComponent(),
												other.getPosition().getYComponent()+deltaT*other.getVelocity().getYComponent());
		
		
		//double theta = Vector.getAngle(newPositionThis,newPositionOther);
		double theta = Math.atan2(newPositionOther.getXComponent()-newPositionThis.getXComponent(),newPositionOther.getYComponent()-newPositionThis.getYComponent());
		
//		try {
			Vector directionRadius = new Vector(Math.cos(theta),Math.sin(theta));
			return newPositionThis.add(directionRadius.scale(this.getRadius()));
//		} catch (ArithmeticException exc) {
//			return new Vector(newPositionThis.getXComponent(),newPositionThis.getYComponent()+this.getRadius());
//		}
	}
	
	
	//TODO: toString
}
