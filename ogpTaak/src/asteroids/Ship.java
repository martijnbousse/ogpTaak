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
	 * Variable registering the position of this ship.
	 * \\TODO: initialisatie?
	 */
	private Vector position = null;
	
	/**
	 * Variable registering the velocity of this ship.
	 */
	private Vector velocity = null;
	
	/**
	 * Variable registering the direction of this ship.
	 */
	private double direction = 0.0;
	
	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius = 0.0;
	
	
	
	
	
	
	/**
	 * Variable registering the radius limit that applies to all ships.
	 */
	private static double radiusLimit = 10.0;
	
	
	
	
	
	
	/**
	 * Variable registering the speed limit of 
	 */
	private final double speedLimit = 300000;
	
	
	/**
	 * Symbolic constant registering the speed of light.
	 */
	public static double SPEED_OF_LIGHT = 300000; 
	
	
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
	 * 
	 * 
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
	public Ship(Vector position, Vector velocity, double direction, double radius){
		
	}
	
	public Ship(){
		// default
		//this(new Position(0,0),0,0,0,0);
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
	 * Return the radius of this ship.
	 */
	@Basic @Immutable
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * Return the radius limit that applies to all ships.
	 */
	@Basic
	public static double getRadiusLimit(){
		return radiusLimit;
	}
	
	
	/**
	 * Set the radius limit that applies to all ships to the given radius limit. 
	 */
	public static void setRadiusLimit(double radiusLimit) throws IllegalArgumentException{
		if ( !isValidRadiusLimit(radiusLimit) )
			throw new IllegalArgumentException();
		Ship.radiusLimit = radiusLimit;
	}
	
	/**
	 * Check whether the given radius limit is a valid radius limit for any ship.
	 * 
	 * @param 	radiusLimit
	 * 			The radius limit to check.
	 * @return	True if and only if the given radius limit is at least zero.
	 * 			| result == (radiusLimit >= 0)
	 */
	public static boolean isValidRadiusLimit(double radiusLimit){
		return ( radiusLimit >= 0 );
	}
	

}
