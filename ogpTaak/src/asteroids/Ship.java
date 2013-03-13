package asteroids;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ships involving a position, a velocity, a direction and a radius.
 * 
 * @invar	Each ship can have its radius as its radius
 * 			| canHaveAsRadius(radius)
 * @invar	The minimum radius that applies to all ships must be a valid minimum radius.
 * 			| isValidMinRadius(minRadius)
 * @invar 	The position that applies to all ships must be a valid position.
 * 			| isValidPosition(position)
 * @invar	Each ship can have its velocity as its velocity
 * 			| canHaveAsVelocity(velocity)
 * @invar	Each ship can have its speed limit as its speed limit.
 * 			| canHaveAsSpeedLimit(speedLimit)
 * @invar 	The direction that applies to all ships must be a valid direction.
 * 			| isValidDirection(direction)
 * @invar	The time to move that applies to all ships must be a valid time.
 * 			| isValidTime(dt) //TODO: correct?
 *
 * @version 1.0
 * @author Martijn Boussé, Wout Vekemans
 *
 */
public class Ship implements IShip{
	
	//TODO: invarianten nakijken
	//TODO: nieuwe excepties aanmaken? Of enkel illegalArgument gebruiken?
	//TODO: reasoning about floating point numbers -> util fuzzy's 
	//TODO: opgave p.3 laatste paragraaf van 1.1 in orde? mail sturen/forum
	//TODO: @raw annotatie ??
	//TODO: isNaN by direction, radius, minRadius, ... cf een checker: isValidNumber? cfc isValidComponent bij Vector
	
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
	 * @effect	| this.setVelocity(velocity) //TODO: ?
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
	 * 			The x- or y-component of the vector position is not a valid component.
	 * 			| !Vector.isValidComponent(position.getXComponent())
	 * 			| || !Vector.isValidComponent(position.getYComponent())
	 * @throws	IllegalArgumentException
	 * 			The x- or y-component of the vector velocity is not a valid component.
	 * 			| !Vector.isValidComponent(velocity.getXComponent())
	 * 			| || !Vector.isValidComponent(velocity.getYComponent())
	 */
	//TODO: is dit zo in orde? Het vreemde is dat we nu eigenlijk 
	
	//TODO: ook radius en direction gebruiken isNaN.. anders moeten we een aparte checker definieren in ship "isValidNumber" die test op isNaN en die gebruiken in 
	// de checkers voor radius en direction. <-> We hebben ook al zo'n methode (isValidComponent) in Vector staan.
	
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
	 * 			The given position is not a valid position.
	 * 			| !isValidPosition(position)
	 */
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
		return (position != null );
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
	 * 			|			&& Util.fuzzyLessThanOrEqualTo(0.0,velocity.getMagnitude())
	 * 			|			&& Util.fuzzyLessThanOrEqualTo(velocity.getMagnitude(),this.speedLimit) )
	 */
	
	// TODO getmagnitude bestaat niet meer. doc aanpassen
	public boolean canHaveAsVelocity(Vector velocity){
		return 	(velocity != null)
					&& Util.fuzzyLessThanOrEqualTo(0.0,velocity.dotProduct(velocity))
					&& Util.fuzzyLessThanOrEqualTo(velocity.dotProduct(velocity),this.speedLimit);
				
	}
	
	/**
	 * Variable registering the velocity of this ship.
	 */
	private Vector velocity;
	
	/**
	 * Returns the speed limit of this ship.
	 */
	@Basic
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
	public void setSpeedLimit(double speedLimit){
		if(canHaveAsSpeedLimit(speedLimit)){
			this.speedLimit = speedLimit;
		}
	}
	
	/**
	 * Check whether this ship can have the given speed limit as its speed limit.
	 * 
	 * @param 	speedLimit
	 * 			The speed limit to check.
	 * @return	True if and only if the given speed limit is a number and if it is less than the speed of light.
	 * 			| result == !Double.isNaN(speedLimit)
	 * 			|			&& Util.fuzzyLessThanOrEqualTo(speedLimit,SPEED_OF_LIGHT)
	 */
	//TODO: kleiner dan nul?
	public boolean canHaveAsSpeedLimit(double speedLimit){
		return 	!Double.isNaN(speedLimit) 
				&& Util.fuzzyLessThanOrEqualTo(speedLimit,SPEED_OF_LIGHT);
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
	//TODO: moet hierbij "==" ook vervangen worden door fuzzyEquals?
	public void setDirection(double direction){
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
	 * Returns the minimum radius for all ships.
	 */
	@Basic
	public static double getMinRadius(){
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
	public static void setMinRadius(double minRadius) throws IllegalArgumentException{
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
	 * 			|			&& minRadius > 0
	 */
	//TODO: hoe gebruiken we hier Util?
	public static boolean isValidMinRadius(double minRadius){
		return 	!Double.isNaN(minRadius)
				&& minRadius > 0;
	}
	
	/**
	 * Variable registering the minimum radius that applies to all ships.
	 */
	private static double minRadius = 10.0;
	
	/**
	 * Return the radius of this ship.
	 */
	@Basic @Raw @Immutable
	public double getRadius(){
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
	public boolean canHaveAsRadius(double radius){	
		return 	!Double.isNaN(radius)
				&& (Util.fuzzyLessThanOrEqualTo(minRadius,radius));
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
	//TODO: isNaN? of enkel voor de attributen van ship.
	public static boolean isValidTime(double dt) {
		return Util.fuzzyLessThanOrEqualTo(0,dt);
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
	 * 			This ship cannot accept the given amount of time to move.    //TODO: change
	 * 			| !isValidTime(time)
	 */
	public void move(double dt) throws IllegalArgumentException{
		if (!isValidTime(dt))
			throw new IllegalArgumentException();
		try{
			setPosition(getPosition().add(getVelocity().scale(dt)));
		} catch(SumOverflowException exc){} 
		catch(TimesOverflowException exc2){}
		//TODO test deze gevallen, mss doorgeven aan facade
	}
	
	/**
	 * Return a boolean reflecting whether this ship can accept the given angle for turning.
	 * 
	 * @param 	angle
	 * 			The angle to be checked.
	 * @return	True if and only if the direction of this ship incremented with the given angle is a valid direction for any ship. //TODO: any ship of this ship? want canAccept is niet static!
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
		assert canAcceptForTurn(angle);
		setDirection(getDirection()+angle);
	}
	
	/**
	 * Change the velocity of this ship with a given amount. 
	 * 
	 * @param 	amount
	 * 			The amount to add.
	 * @post	The given amount was added to the velocity of the ship, in the direction the ship is heading.
	 * 			| (new this).getVelocity().getXcomponent() == 
	 * 			|	this.getVelocity().getXComponent()+amount*Math.cos(this.getDirection())
	 * 			| (new this).getVelocity().getYcomponent() == 
	 * 			|	this.getVelocity().getYComponent()+amount*Math.sin(this.getDirection())
	 */
	public void thrust(double amount){
		if(!canAcceptAsThrustAmount(amount)){
			amount = 0.0;
		}
		this.velocity=new Vector(velocity.getXComponent()+amount*Math.cos(direction),velocity.getYComponent()+amount*Math.sin(direction));
		//TODO: velocity w groter dan limit, vector scalen zodat lengte is lightspeed
		//TODO: herschrijven met add en scale + @effect ipv @post gebruiken.
	}
	
	/**
	 * Returns a boolean whether this ship can accept the given amount to thrust.
	 * 
	 * @param 	amount
	 * 			The amount to check.
	 * @return	True if and only if the given amount is greater than zero.	
	 * 			| result == amount > 0
	 */
	public boolean canAcceptAsThrustAmount(double amount){
		return amount > 0;
	}
	//TODO: static? isValidThrustAmount, volgens mij static aangezien dit niet afhankelijk is van object-afhankelijke variabelen. 
	
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
			return 0.0;
		try{
			Vector delta = this.getPosition().subtract(other.getPosition());
			return Math.sqrt(delta.dotProduct(delta)) - this.getRadius() - other.getRadius();
		} catch(TimesOverflowException exc){
			return Double.POSITIVE_INFINITY; //TODO: waarom nul? eerder inf?
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
	//TODO: opruimen + schrijven ifv add, scale, ... + documentatie 
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
//		double theta = Math.atan2(newPositionOther.getXComponent()-newPositionThis.getXComponent(),newPositionOther.getYComponent()-newPositionThis.getYComponent());
		
		double theta = Math.atan2(newPositionOther.getYComponent()-newPositionThis.getYComponent(),newPositionOther.getXComponent()-newPositionThis.getXComponent());
		
		if(newPositionOther.getXComponent()-newPositionThis.getXComponent()<0)
			theta+= Math.PI*2;
//		try {
			Vector directionRadius = new Vector(Math.cos(theta),Math.sin(theta));
			return newPositionThis.add(directionRadius.scale(this.getRadius()));
//		} catch (ArithmeticException exc) {
//			return new Vector(newPositionThis.getXComponent(),newPositionThis.getYComponent()+this.getRadius());
//		}
	}
	
	
	//TODO: toString
}
