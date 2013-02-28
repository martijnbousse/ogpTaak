package asteroids;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ships involving a position, a velocity, a direction and a radius.
 * 
 * //TODO: invarianten
 * @invar	
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
	 * @post	The new position of this new ship is equal to the given position.
	 * 			| (new this).getPosition().equals(position)
	 * @throws	IllegalArgumentException
	 * 			This new ship cannot have the given position as its position.
	 * 			| !isValidPosition(position)
	 * 
	 * 
	 * 
	 * @post	//TODO: velocity totaal
	 * 
	 * 
	 * 
	 * @Pre		The given direction must be a valid direction for any ship.
	 * 			| isValidDirection(direction)
	 * @post	The new direction of this new ship is equal to the given direction.
	 * 
	 * 
	 * 
	 * @post	The new radius of this new ship is equal to the given radius.
	 * 			| (new this).getRadius() == radius
	 * 
	 * 
	 * 
	 */
	public Ship(Vector position, Vector velocity, double radius, double angle) throws IllegalArgumentException{
		setPosition(position);
		setVelocity(velocity);
		if(!isValidRadius(radius)){
			throw new IllegalArgumentException();	
		}
		this.radius=radius;
		setSpeedLimit(SPEED_OF_LIGHT);
	}
	
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
	 * 			| position == null
	 */
	// TODO: @raw ?
	// private want enkel in move zal de positie veranderd kunnen worden?
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
	 * 			| result == (other != null)
	 */
	public static boolean isValidPosition(Vector position){
			return ( position != null );
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
	 * 			| if isValidVelocity
	 * 			| then (new this).getVelocity().equals(velocity)
	 */
	// TODO: @raw ?
	// private want enkel in move zal de snelheid veranderd kunnen worden?
	@Raw //bv in thrust can die werken op een velocity object dat niet voldoet aan zijn invariant
	public void setVelocity(Vector velocity){
		if (canHaveAsVelocity(velocity))
			this.velocity = velocity;
	}
	
	/**
	 * Check whether this ship can have the given velocity as its velocity.
	 * 
	 * @param 	velocity
	 * 			The velocity to check.
	 * @return	True if and only if the speed of the given velocity is bigger then or equal to zero
	 * 			and smaller then or equal to the speed limit.
	 * 			| result == (velocity.getMagnitude() <= 0)
	 * 						&& (velocity.getMagnitude() >= this.speedLimit)
	 */
	//TODO: dit als instance method aangezien afhankelijk van speedlimit, dat verschillend kan zijn voor elk ship
	//       cf isvalidposition ??
	public boolean canHaveAsVelocity(Vector velocity){
		return (velocity.getMagnitude() <= 0)
				&& (velocity.getMagnitude() >= this.speedLimit);
	}
	
	/**
	 * Variable registering the velocity of this ship.
	 */
	private Vector velocity = null;
	
	private void setSpeedLimit(double newLimit){
		if(isValidSpeedLimit(newLimit)){
			this.speedLimit=newLimit;
		}
		else {
			this.speedLimit = SPEED_OF_LIGHT;
		}
	}
	
	private boolean isValidSpeedLimit(double newLimit){
		return newLimit <= SPEED_OF_LIGHT;
	}
	
	/**
	 * Variable registering the speed limit of 
	 */
	private double speedLimit;
	
	/**
	 * Symbolic constant registering the speed of light.
	 */
	public static double SPEED_OF_LIGHT = 300000; 
	
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
	public void setDirection(double direction){
		this.direction = direction;
	}
	
	/**
	 * Check whether the given direction is a valid direction for any ship.
	 * 
	 * @param 	direction
	 * 			The direction to check.
	 * @return	True if and only if the given direction is in between zero and two times pi.
	 * 			| result == (direction >= 0.0) && (direction <= 2*Math.PI) )
	 */
	public static boolean isValidDirection(double direction){
		return ( (direction >= 0.0) && (direction <= 2*Math.PI) );
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
	public void setMinRadius(double newMinRadius) throws IllegalArgumentException	{
		if(!isValidMinRadius(newMinRadius)){
			throw new IllegalArgumentException();
		}
		minRadius = newMinRadius;
	}
	
	/**
	 * Check whether the given minimum radius is a valid minimum radius for the ship.
	 * @param 	minRadius
	 * 			the minRadius to check
	 * @return	True if and only if the given minimum radius is greater than 0
	 * 			| result == minRadius > 0
	 */
	public boolean isValidMinRadius(double minRadius){
		return minRadius > 0;
	}
	
	private static double minRadius;
	
	/**
	 * Check whether the given radius is a valid radius for the ship.
	 * @param 	radius
	 * 			the radius to check
	 * @return	True if and only if the given radius is greater than the mininum radius.
	 * 			| result == radius >= minRadius
	 */
	public boolean isValidRadius(double radius){
		return (radius >= minRadius);
	}
	
	/**
	 * Return the radius of this ship.
	 */
	@Basic @Raw @Immutable
	public double getRadius(){
		return radius;
	}
	
	private final double radius;

}
