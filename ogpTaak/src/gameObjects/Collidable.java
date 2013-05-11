package gameObjects;

import support.Vector;
import exceptions.InvalidPositionException;
import exceptions.SumOverflowException;
import exceptions.TimesOverflowException;
import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of collidables involving a position, a velocity, a radius and a mass.
 * 
 * @invar 	The position of each collidable must be a valid position.
 * 			| canHaveAsPosition(getPosition())
 * @invar	Each collidable can have its velocity as its velocity.
 * 			| canHaveAsVelocity(getVelocity())
 * @invar	Each collidable can have its speed limit as its speed limit.
 * 			| canHaveAsSpeedLimit(getSpeedLimit())
 * @invar	Each collidable can have its radius as its radius.
 * 			| canHaveAsRadius(getRadius())
 * @invar	The minimum radius that applies to all collidables must be a valid minimum radius.
 * 			| isValidMinRadius(getMinRadius())
 * @invar	Each collidable must have a proper world to which it is attached.
 * 			| hasProperWorld()
 * 
 * @version	2.0
 * @author Martijn Boussé, Wout Vekemans
 * 
 */
//TODO: @raw nakijken
public abstract class Collidable {
	/**
	 * Initialize this new collidable with given position, given velocity, given radius and given mass
	 * and not yet attached to a world.
	 * 
	 * @param 	position
	 * 			The position for this new collidable.
	 * @param 	velocity
	 * 			The velocity for this new collidable.
	 * @param 	radius
	 * 			The radius for this new collidable.
	 * @param 	mass
	 * 			The mass for this new collidable.
	 * @post	The new position of this new collidable is equal to the given position.
	 * 			| (new this).getPosition().equals(position)
	 * @post	The new velocity of this new collidable is equal to the given velocity.
	 * 			| (new this).getVelocity().equals(velocity) 
	 * @post	The new radius of this new collidable is equal to the given radius.
	 * 			| (new this).getRadius() == radius
	 * @post	The new mass of this new collidable is equal to the given mass.
	 * 			| (new this).getMass() == mass
	 * @throws	IllegalArgumentException
	 * 			This new collidable cannot have the given position as its position.
	 * 			| !isValidPosition(position)
	 * @throws	IllegalArgumentException
	 * 			This new collidable cannot have the given radius as its radius
	 * 			| !isValidRadius(radius)
	 */
	@Raw @Model
	protected Collidable(Vector position, Vector velocity, double radius) throws IllegalArgumentException {
		setSpeedLimit(SPEED_OF_LIGHT);
		setPosition(position);
		setVelocity(velocity);
		if(!canHaveAsRadius(radius)){
			throw new IllegalArgumentException();	
		}
		this.radius = radius;
	}
	
	
	/**
	 * Check whether this collidable is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	/**
	 * Terminate this collidable. 
	 */
	public void terminate() {
		if (getWorld() != null) 
			this.getWorld().removeAsCollidable(this);
		this.isTerminated = true; 
	}
	
	/**
	 * Variable registering whether or not this collidable is terminated.
	 */
	private boolean isTerminated = false;
		
	/**
	 * Returns the position of this collidable.
	 */
	@Basic
	public Vector getPosition() {
		return this.position;
	}
	
	/**
	 * Set the position of this collidable to the given position.
	 * 
	 * @param	position 
	 * 	      	The new position for this collidable.
	 * @post 	The new position of this collidable is equal to the given position.
	 * 			| (new this).getPosition().equals(position)
	 * @throws	IllegalArgumentException
	 * 			The given position is not a valid position.
	 * 			| !isValidPosition(position)
	 */
	@Raw
	protected void setPosition(Vector position) throws InvalidPositionException {
		if (!canHaveAsPosition(position)) 
			throw new InvalidPositionException();	
		this.position = position;
	}
	
	/**
	 * Check whether this collidable can have its position as its position.
	 * 
	 * @param 	position
	 * 			The position to check.
	 * @effect 	If this collidable has a world, true if and only if the given position is effective, and inbetween the borders of the collidables world.
	 * 			| if getWorld() != null
	 * 			| 	then result == (position != null) && Util.fuzzyLessThanOrEqualTo(position.getXComponent()+getRadius(),getWorld().getWidth())
	 *			|		&& Util.fuzzyLessThanOrEqualTo(position.getYComponent()+getRadius(),getWorld().getHeight())
	 *			| 		&& Util.fuzzyLessThanOrEqualTo(0.0,position.getXComponent()-getRadius())
	 *			| 		&& Util.fuzzyLessThanOrEqualTo(0.0,position.getYComponent()-getRadius());
	 * @effect	If this collidable doesn't have a world, true if and only if the given position is effective and its coordinates are inbetween zero and the maximum value.
	 * 			| if getWorld() == null
	 * 			|	then result == (position != null)
	 *			|		&& Util.fuzzyLessThanOrEqualTo(position.getXComponent()+getRadius(),Double.MAX_VALUE)
	 *			|		&& Util.fuzzyLessThanOrEqualTo(position.getYComponent()+getRadius(),Double.MAX_VALUE)
	 *			|		&& Util.fuzzyLessThanOrEqualTo(0.0,position.getXComponent()-getRadius())
	 *			|		&& Util.fuzzyLessThanOrEqualTo(0.0,position.getYComponent()-getRadius())
	 */	
	@Raw
	public boolean canHaveAsPosition(Vector position) {
		if (getWorld() == null)
			return (position != null)
				&& Util.fuzzyLessThanOrEqualTo(position.getXComponent()+getRadius(),Double.MAX_VALUE)
				&& Util.fuzzyLessThanOrEqualTo(position.getYComponent()+getRadius(),Double.MAX_VALUE)
				&& Util.fuzzyLessThanOrEqualTo(0.0,position.getXComponent()-getRadius())
				&& Util.fuzzyLessThanOrEqualTo(0.0,position.getYComponent()-getRadius());
		else {
			return (position != null)
				&& Util.fuzzyLessThanOrEqualTo(position.getXComponent()+getRadius(),getWorld().getWidth())
				&& Util.fuzzyLessThanOrEqualTo(position.getYComponent()+getRadius(),getWorld().getHeight())
				&& Util.fuzzyLessThanOrEqualTo(0.0,position.getXComponent()-getRadius())
				&& Util.fuzzyLessThanOrEqualTo(0.0,position.getYComponent()-getRadius());
		}
	}
		
	/**
	 * Variable registering the position of this collidable.
	 */
	private Vector position;
	
	/**
	 * Returns the velocity of this collidable.
	 */
	@Basic
	public Vector getVelocity() {
		return this.velocity;
	}
	
	/**
	 * Set the velocity of this collidable to the given velocity.
	 * 
	 * @param	velocity 
	 * 	      	The new velocity for this collidable.
	 * @post 	If this collidable can have the given velocity as its velocity then the new velocity of this collidable is equal to the given velocity.
	 * 			| if canHaveAsVelocity(velocity)
	 * 			| 	then (new this).getVelocity().equals(velocity)
	 */
	@Raw
	protected void setVelocity(Vector velocity) {
		if (canHaveAsVelocity(velocity))
			this.velocity = velocity;
	}
	
	/**
	 * Check whether this collidable can have the given velocity as its velocity.
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
				&& Util.fuzzyLessThanOrEqualTo(Math.sqrt(velocity.dotProduct(velocity)), getSpeedLimit());	
	}
	
	/**
	 * Variable registering the velocity of this collidable.
	 */
	private Vector velocity;
	
	/**
	 * Returns the speed limit of this collidable.
	 */
	@Basic @Raw
	public double getSpeedLimit() {
		return this.speedLimit;
	}
	
	/**
	 * Set the speed limit of this collidable to the given limit.
	 * 
	 * @param 	speedLimit
	 * 			The new speed limit for this collidable.
	 * @post	If this collidable can have the given limit as its speed limit then the new speed limit of this collidable is equal to the given speed limit.
	 * 			| if canHaveAsSpeedLimit(speedLimit)
	 * 			|	then (new this).getSpeedLimit() == speedLimit
	 */
	@Raw
	public void setSpeedLimit(double speedLimit) {
		if(canHaveAsSpeedLimit(speedLimit))
			this.speedLimit = speedLimit;
	}
	
	/**
	 * Check whether this collidable can have the given speed limit as its speed limit.
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
	 * Variable registering the speed limit of this collidable.
	 */
	private double speedLimit;
	
	/**
	 * Symbolic constant registering the speed of light.
	 */
	public static double SPEED_OF_LIGHT = 300000.0; 
	
	/**
	 * Returns the minimum radius for all collidables.
	 */
	@Basic @Immutable @Raw
	public static double getMinRadius() {
		return minRadius;
	}
	
	/**
	 * Set the minimum radius that applies to all collidables to the given minimum radius.
	 * 
	 * @param 	minRadius
	 * 			The new minimum radius for all collidables.
	 * @post	The new minimum radius that applies to all collidables is set to the given minimum radius.
	 * 			| (new Collidable).minRadius == minRadius
	 * @throws 	IllegalArgumentException
	 * 			The given minimum radius is not a valid minimum radius.
	 * 			| !isValidMinRadius(minRadius)
	 */
	public static void setMinRadius(double minRadius) throws IllegalArgumentException {
		if(!isValidMinRadius(minRadius))
			throw new IllegalArgumentException();
		Collidable.minRadius = minRadius;
	}
	
	/**
	 * Check whether the given minimum radius is a valid minimum radius for any collidable.
	 * 
	 * @param 	minRadius
	 * 			The minimum radius to check.
	 * @return	True if and only if the given minimum radius is a number and if it is greater than zero.
	 * 			| result == !Doulbe.isNaN(minRadius)
	 * 			|			&& (minRadius > 0)
	 */
	public static boolean isValidMinRadius(double minRadius) {
		return 	!Double.isNaN(minRadius)
				&& (Util.fuzzyLessThanOrEqualTo(0, minRadius));
	}
	
	/**
	 * Variable registering the minimum radius that applies to all collidables.
	 */
	private static double minRadius = 0;
	
	/**
	 * Return the radius of this collidable.
	 */
	@Basic @Immutable
	public double getRadius() {
		return radius;
	}
	
	/**
	 * Check whether the given radius is a valid radius for this collidable.
	 * 
	 * @param 	radius
	 * 			The radius to check.
	 * @return	True if and only if the given radius is a number and if it is greater than the mininum radius.
	 * 			| result == !Double.isNaN(minRadius)
	 * 			|			&& (radius >= minRadius)
	 */
	@Raw
	public boolean canHaveAsRadius(double radius) {	
		return 	!Double.isNaN(radius)
				&& (Util.fuzzyLessThanOrEqualTo(getMinRadius(), radius));
	}
	
	/**
	 * Variable registering the radius of this collidable.
	 */
	private final double radius;
	
	/**
	 * Returns the mass of this collidable.
	 */
	public abstract double getMass();
	
	/**
	 * Return the world of this collidable.
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Set the world of this collidable to the given world.
	 * 
	 * @param	world
	 * 			The new world to attach this collidable to.
	 * @pre		If the given world is effective, it must already reference this collidable as one of its collidables.
	 * 			| if (world != null)
	 * 				then world.hasAsCollidable(this)
	 * @pre		If the given world is not effective and this collidable references an effective world,
	 * 			that world may not reference this collidable as one of its collidables.						
	 * 			| if ((world == null) && (getWorld() != null))
	 * 			| 	then !getWorld().hasAsCollidable(this)
	 * @post	This collidable references the given world as the world to which it is attached.
	 * 			| (new this).getWorld() == world
	 */
	@Raw
	void setWorld(World world) {
		assert ( (world == null) || world.hasAsCollidable(this) );
		assert ( (world != null) || (getWorld() == null) || (!getWorld().hasAsCollidable(this)) );
		this.world = world;
	}
	
	/**
	 * Check whether this collidable has a proper world to which it is attached.
	 * 
	 * @return	True if and only if this collidable can have its world as the world to which it is attached,
	 * 			and if that world is either not effective or has this collidable as one of its collidables.
	 * 			| result == ( canHaveAsWorld(getWorld()) 
	 * 						&& ( (getWorld() == null) || getWorld().hasAsCollidable(this))) 
	 */
	@Raw
	public boolean hasProperWorld() {
		return ( canHaveAsWorld(getWorld()) 
				&& ( (getWorld() == null) || getWorld().hasAsCollidable(this)));
	}
	
	/**
	 * Check whether this collidable can be attached to the given world.
	 * 
	 * @param 	world
	 * 			The world to check.
	 * @return	True if and only if the given world is not effective or if it can have this collidable as one of its collidables.
	 * 			| result == ((world == null) || (world.canHaveAsCollidable(this)))
	 */
	// canHaveAsWorld calls the complementary checker in the bidirectional association.
	@Raw
	public boolean canHaveAsWorld(World world) { 
		return ((world == null) || world.canHaveAsCollidable(this));
	}
		
	/**
	 * Variable registering the world of this collidable.
	 */
	private World world = null;
	
	/**
	 * Returns a boolean reflecting whether this collidable and the given collidable overlap.
	 * 
	 * @param 	other
	 * 			The other collidable.
	 * @effect	True if and only if the distance between this collidable and the given collidable is negative 
	 * 			or if the given collidable is equal to this collidable.
	 * 			| result == (getDistanceBetween(other) < 0) || (other.equals(this))  
	 * @throws 	IllegalArgumentException 
	 * 			The given collidable is not effective.
	 * 			| (other == null)
	 */
	//REMINDER: IllegalStateException and IllegalArgumentException regarding the terminated state of this collidable
	//          and the given collidable are thrown via getDistanceBetween(other).
	public boolean overlap(Collidable other) throws IllegalArgumentException, IllegalStateException {
		if (other == null)
			throw new IllegalArgumentException("Non effective collidable!");
		if (other.equals(this))
			return true;
		return (getDistanceBetween(other) < 0);
	}

	/**
	 * Returns the distance between this collidable and the given collidable.
	 * 
	 * @param  	other
	 * 			The other collidable.
	 * @effect	If this collidable is equal to the given collidable the result is always zero.
	 * 			| if (other == this)
	 * 			|	then result == 0.0
	 * @effect	If the result is positive, this collidable and the given collidable don't overlap
	 * 			| if (result > 0.0)
	 * 			|	then this.overlap(other) == false
	 * @effect	If the result is negative, this collidable and the given collidable overlap
	 * 			| if (result < 0.0)
	 * 			|	then this.overlap(other) == true
	 * @throws 	IllegalArgumentException
	 * 			The given collidable is not effective.
	 * 			| (other == null)
	 * @throws	IllegalStateException
	 * 			This collidable is terminated.
	 * 			| isTerminated()
	 * @throws	IllegalArgumentException
	 * 			The given collidable is terminated.
	 * 			| other.isTerminated()
	 */
	public double getDistanceBetween(Collidable other) throws IllegalArgumentException, IllegalStateException {
		if (isTerminated())
			throw new IllegalStateException("This collidable is terminated!");
		if (other == null)
			throw new IllegalArgumentException("Non effective collidable!");
		if (other.isTerminated())
			throw new IllegalStateException("The given collidable is terminated!");
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
	 * Returns a boolean reflecting whether this collidable and the given collidable overlap.
	 * 
	 * @effect	True if and only if the distance between this collidable and the boundary is negative 
	 * 			| result == (getDistanceBetween(other) < 0)) 
	 */
	//REMINDER: IllegalStateException regarding the terminated state of this collidable
	//          are thrown via getDistanceBetween(other).
	public boolean overlapWithBoundary() throws IllegalStateException {
		return (getDistanceToClosestBoundary() < 0);
	}
	
	/**
	 * Calculate the distance from this collidable to the closest boundary of its world.
	 * 
	 * @effect 	If the result is positive, this collidable and the boundary don't overlap
	 * 			| if (result > 0.0)
	 * 			| 	then this.overlapWithBoundary() == false
	 * @effect 	If the result is negative, this collidable and the boundary overlap
	 * 			| if (result < 0.0)
	 * 			| 	then this.overlapWithBoundary() == true
	 * @effect 	If this collidable has the null reference as its world, then the distance is infinity.
	 * 			| if getWorld() == null
	 * 			|	then result == Double.POSITIVE_INFINITY
	 * @throws	IllegalStateException
	 * 			This collidable is terminated.
	 * 			| isTerminated()
	 */
	@Raw
	public double getDistanceToClosestBoundary() throws IllegalStateException {
		if (isTerminated())
			throw new IllegalStateException("This collidable is terminated!");
		if(getWorld() !=null) {
			double minX = getPosition().getXComponent() - radius;
			double maxX = getWorld().getWidth() -getPosition().getXComponent() - radius;
			double minY = getPosition().getYComponent() - radius;
			double maxY = getWorld().getHeight() - getPosition().getYComponent() - radius;
			return Math.min(Math.min(minY, minX), Math.min(maxY, maxX));
		}
		return Double.POSITIVE_INFINITY;
	}
	
	/**
	 * Returns when this collidable, if ever, will collide with the given collidable.
	 * 
	 * @param 	other
	 * 			The other collidable.
	 * @effect	True if and only if the collidables touch each other after moving the calculated time.
	 *			| if this.move(result) && other.move(result)
	 *			|	then this.getDistanceBetween(other) == 0
	 *
	 *  //TODO: welke van de twee documentaties? zie ook testen (heb mail gestuud hiervoor)
	 *
	 * @effect	If this collidable is equal to the given collidable the result is always zero.
	 * 			| if (other == this)
	 * 			|	then result == 0.0
	 * @effect	If the result is positive, this collidable and the given collidable don't overlap
	 * 			| if (result > 0.0)
	 * 			|	then this.overlap(other) == false
	 * @effect	If the result is negative, this collidable and the given collidable overlap
	 * 			| if (result < 0.0)
	 * 			|	then this.overlap(other) == true	
	 * @throws 	IllegalArgumentException
	 * 			The given collidable is not effective.
	 * 			| (other == null)
	 */
	public double getTimeToCollision(Collidable other) throws IllegalArgumentException{
		if (other == null)
			throw new IllegalArgumentException("Non effective collidable!");
		
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
	 * Returns when this collidable, if ever, will collide with the boundary.
	 * 
	 * @effect	If this collidable has an effective world and is not terminated, then if this collidable is moved 
	 * 			over the result, then the distace to the closest boundary of its world is zero.
	 * 			| if getWorld() != null && !isTerminated()
	 * 			| 	then this.move(result)
	 * 			|		new.getDistanceToClosestBoundary == 0
	 */
	//TODO: doc?
	public double getTimeToCollisionWithBoundary() {
		if(getWorld() != null && !isTerminated()) {
			return Math.min(getMinXCollision(), getMinYCollision());
		}
		return Double.POSITIVE_INFINITY;
	}


	/**
	 * Calculates the time to collision with a vertical boundary of this collidables world.
	 * 
	 * @effect	| if result != Double.POSITIVE.INFINITY
	 * 			|	then this.move(result)
	 * 			|		new.getDistanceToClosestBoundary == 0
	 */
	//TODO: doc?
	private double getMinXCollision() {
		if(!Util.fuzzyEquals(0, getVelocity().getXComponent())) {
			double maximumXTime = (getWorld().getWidth() - getPosition().getXComponent() - getRadius())/getVelocity().getXComponent();
			double zeroXTime = -(getPosition().getXComponent() - getRadius())/getVelocity().getXComponent();
			if(!Util.fuzzyLessThanOrEqualTo(0, getVelocity().getXComponent())) {
				return zeroXTime;
			}
			return maximumXTime;
		}
		return Double.POSITIVE_INFINITY;
	}

	/**
	 * Calculates the time to collision with a horizontal boundary of this collidables world.
	 * 
	 * @effect	| if result != Double.POSITIVE.INFINITY
	 * 			|	then this.move(result)
	 * 			|		new.getDistanceToClosestBoundary == 0
	 */
	//TODO: doc?
	private double getMinYCollision() {
		if(!Util.fuzzyEquals(0, getVelocity().getYComponent())) {
			double maximumYTime = (getWorld().getHeight() - getPosition().getYComponent() - getRadius())/getVelocity().getYComponent();
			double zeroYTime = -(getPosition().getYComponent() - getRadius())/getVelocity().getYComponent();
			if(!Util.fuzzyLessThanOrEqualTo(0, getVelocity().getYComponent())) {
				return zeroYTime;
			}
			return maximumYTime;
		}
		return Double.POSITIVE_INFINITY;
	}
	
	/**
	 * Return a boolean reflecting whether this collidable can bounce of the boundary.
	 * 
	 * @return	True if and only if the world to which this 
	 * 			collidable is attached to is effective and is a proper world for this collidable, 
	 * 			which also includes that this collidable is not terminated and effective. 
	 * 			And if the time to collision of this collidable with the boundary is equal to zero.
	 * 			| result == ( !((getWorld() == null) && hasProperWorld())
	 *			|			&& (Util.fuzzyEquals(getTimeToCollisionWithBoundary(),0.0)))
	 */
	// REMINDER: hasProperWorld() also calls the complementary checker canHaveAsCollidable() from the bidirectional association.
	//           Hence, terminated collidables are also included in the if construct.	
	public boolean canBounceOfBoundary() {
		return ( !((getWorld() == null) && hasProperWorld())
				&& (Util.fuzzyEquals(getTimeToCollisionWithBoundary(),0.0)));
	}


	/**
	 * This collidable bounces with the boundary of its world.
	 * 
	 * @effect	If the time to collision with a boundary of this collidable is equal to zero and this collidable 
	 * 			is moved for a small enough time interval, then this collidable does not overlap with that boundary.
	 * 			| let
	 * 			|	dt be a small enough time interval
	 * 			| in	
	 * 			| 	if Util.fuzzyEquals(this.getTimeToCollisionWithBoundary(),0.0)
	 * 			|		then move(dt)
	 * 			|			 this.getDistancetoClosestBoundary > 0
	 * 			Else if the time to collision with a boundary of this collidable is bigger than zero and this collidable
	 * 			is moved for a small time interval, then the new time to collision with that boundary will be smaller 
	 * 			than the previous time to collision with that boundary.
	 * 			|	else move(dt)
	 * 			| 		 getTimeToCollisionWithBoundary > (new this).getTimeToCollisionWithBoundary	
	 */
	// REMINDER: hasProperWorld() also calls the complementary checker canHaveAsCollidable() from the bidirectional association.
	//           Hence, terminated collidables are also included in the if construct.	
	public void bounceOfBoundary(){
		if (canBounceOfBoundary()) {
			Vector newVelocity;
			if(Util.fuzzyEquals(getPosition().getXComponent(),getRadius())) {
				newVelocity = new Vector(-getVelocity().getXComponent(),getVelocity().getYComponent());
			}
			else if(Util.fuzzyEquals(getPosition().getYComponent()+getRadius(),getWorld().getHeight())) {
				newVelocity = new Vector(getVelocity().getXComponent(),-getVelocity().getYComponent());
			}
			else if(Util.fuzzyEquals(getPosition().getXComponent()+getRadius(),getWorld().getWidth())) {
				newVelocity = new Vector(-getVelocity().getXComponent(),getVelocity().getYComponent());
			}
			else {
				newVelocity = new Vector(getVelocity().getXComponent(),-getVelocity().getYComponent());
			}
			setVelocity(newVelocity);
		}
	}


	/**
	 * Return a boolean reflecting whether this collidable can bounce with the given collidable.
	 * 
	 * @param 	other
	 * 			The other collidable to check.
	 * @return	True if and only if the world to which the other collidable is attached to is effective and is a proper world for the other 
	 * 			collidable, which also includes that the other collidable is not terminated and effective. And if the world to which this 
	 * 			collidable is attached to is effective and is a proper world for this collidable, which also includes that this collidable
	 * 			is not terminated and effective. And if the time to collision of this collidable with the other collidable is equal to zero.
	 * 			| result == ( !((other.getWorld() == null) && other.hasProperWorld())  
	 *						&& !((getWorld() == null) && hasProperWorld())
	 *						&& ((this.getWorld() == other.getWorld())) )
	 *						&& (Util.fuzzyEquals(getTimeToCollision(other),0.0));	
	 */
	//REMINDER: canBounce does not have to check if other is non-effective, because the method bounce has a restricted use. 
	//          It can only be used for collidables in the list of collidables of a world, which can never be null. 
	public boolean canBounce(Collidable other) {
		return ( !((other.getWorld() == null) && other.hasProperWorld())  
				&& !((getWorld() == null) && hasProperWorld())
				&& ((this.getWorld() == other.getWorld())) )
				&& (Util.fuzzyEquals(getTimeToCollision(other),0.0));	
	}


	/**
	 * This collidable bounces with the other collidable.
	 * 
	 * @param 	other
	 * 			The other collidable.
	 * @effect 	If this collidable and the other collidable can bounce than the velocity vectors
	 * 			are both updated.
	 * 			| if canBounce(other)
	 * 			| 	then (new this).getVelocity() != this.getVelocity()
	 * 			| 		 (new other).getVelocity() != other.getVelocity()
	 * 			| else 	(new this).getVelocity() == this.getVelocity()
	 * 			| 		 (new other).getVelocity() == other.getVelocity()
	 */
	// REMINDER: hasProperWorld() also calls the complementary checker canHaveAsCollidable() from the bidirectional association.
	//           Hence, terminated collidables are also included in the if construct.	
	protected void bounce(Collidable other) {
		if (canBounce(other)) {
				try {
					Vector deltaR = other.getPosition().subtract(this.getPosition());
					Vector deltaV = other.getVelocity().subtract(this.getVelocity());
					double sigma = this.getRadius()+other.getRadius();
					
					if(Util.fuzzyLessThanOrEqualTo(Math.abs(2*this.getMass()*other.getMass()*deltaR.dotProduct(deltaV)),Double.MAX_VALUE)
							&& Util.fuzzyLessThanOrEqualTo(sigma*(this.getMass()+other.getMass()), Double.MAX_VALUE)) {
						
						double J = 2*this.getMass()*other.getMass()*deltaR.dotProduct(deltaV)/(sigma*(this.getMass()+other.getMass()));
						Vector Jvector = deltaR.scale(J/sigma);
						
						Vector newVelocityThis = this.getVelocity().add(Jvector.scale(1/this.getMass()));
						Vector newVelocityOther = other.getVelocity().subtract(Jvector.scale(1/other.getMass()));
						
						this.setVelocity(newVelocityThis);
						other.setVelocity(newVelocityOther);
						
					} else { 
						throw new TimesOverflowException();
					}
				} catch(Exception e) {
					// if the calculation fails at any moment, the two collidables stop moving (design choice)
					this.setVelocity(new Vector(0,0));
					other.setVelocity(new Vector(0,0));
				}
		}
	}


	/**
	 * Move this collidable for the given amount of time.
	 * 
	 * @param 	dt
	 * 			The amount of time to move.
	 * @effect	The new position of this collidable is set to position of this collidable incremented with the velocity of this collidable, 
	 * 			which is scaled with the given amount of time.
	 * 			| setPosition(getPosition().add(getVelocity.scale(dt)))
	 * @throws 	IllegalArgumentException
	 * 			This collidable cannot accept the given amount of time to move.  
	 * 			| !isValidTime(time)
	 * @throws	IllegalStateException
	 * 			This collidable is terminated.
	 * 			| isTerminated()
	 */
	public void move(double dt) throws IllegalArgumentException, IllegalStateException {
		if (isTerminated())
			throw new IllegalStateException();
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
		catch(InvalidPositionException exc3) {
			// do nothing
		}
	}


	/**
	 * Check whether the given time is a valid time for any collidable.
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
	 * This collidable collides with the given collidable.
	 * 
	 * @param 	collidable
	 * 			The given collidable.
	 */
	public abstract void collide(Collidable collidable);
	
	/**
	 * This collidable collides with the given ship.
	 * 
	 * @param 	ship
	 * 			The given ship.
	 */
	abstract void collidesWith(Ship ship);
	
	/**
	 * This collidable collides with the given asteroid.
	 * 
	 * @param 	asteroid
	 * 			The given asteroid.
	 */
	abstract void collidesWith(Asteroid asteroid);
	
	/**
	 * This collidable collides with the given bullet.
	 * 	
	 * @param 	bullet
	 * 			The given bullet.
	 */
	abstract void collidesWith(Bullet bullet);
	
	/**
	 * Return a textual representation of this collidable.
	 * 
	 * @return	A string consisting of the textual representation of the position, 
	 * 			the velocity and the radius of this collidable, separated by a space 
	 * 			and preceded by a square bracket.
	 * 			| result.equals(
	 * 			|	"[" + "Position: " + getPosition().toString() 
	 * 			|		+ " Velocity: " + getVelocity().toString()
	 * 			|		+ " Radius: " + getRadius() + "]" )
	 */
	@Override
	public String toString(){
		return "[" + "Position: " + getPosition().toString() 
				   + " Velocity: " + getVelocity().toString()
				   + " Radius: " + getRadius();
	}
}
