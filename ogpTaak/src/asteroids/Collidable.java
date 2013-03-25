package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class Collidable {
	public Collidable(Vector position, Vector velocity, double mass){
		setSpeedLimit(SPEED_OF_LIGHT);
		setPosition(position);
		setVelocity(velocity);
		this.mass = mass;
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
	 * @param 	speedLimit
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
	
	public double getMass() {
		return this.mass;
	}
	
	public double mass;
	
	public boolean canHaveAsWorld(World world) {
		return world != null;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	private World world;
}
