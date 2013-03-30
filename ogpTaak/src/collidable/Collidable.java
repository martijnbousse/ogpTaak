package collidable;

import asteroids.SumOverflowException;
import asteroids.TimesOverflowException;
import asteroids.Util;
import asteroids.Vector;
import asteroids.World;
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
 * @invar	The minimum radius that applies to all collidable must be a valid minimum radius.
 * 			| isValidMinRadius(getMinRadius())
 * @invar	The world of each collidable must be a valid world.
 * 			| isValidWorld(getWorld())
 * 
 * @version	1.0
 * @author Martijn Boussé, Wout Vekemans
 * 
 */
//TODO: alle annotaties nazien
//TODO: invarianten toevoegen
public abstract class Collidable {
	/**
	 * Initialize this new collidable with given position, given velocity, given radius and given mass.
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
		this.radius = radius;
	}
	
	
	/**
	 * Check whether this collidable is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	public void terminate() {
		//TODO : break relation
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
	protected void setPosition(Vector position) throws IllegalArgumentException {
		if (!canHaveAsPosition(position)) 
			throw new IllegalArgumentException();	
		this.position = position;
	}
	
	/**
	 * Check whether the given position is a valid position for any collidable.
	 * 
	 * @param 	position
	 * 			The position to check.
	 * @return 	True if and only if the given position is effective.
	 * 			| result == (position != null)
	 */
	//TODO: FUZZZAAAAAY
	
	public boolean canHaveAsPosition(Vector position) {
		return (position != null)
				&& getPosition().getXComponent() + getRadius() < getWorld().getWidth()
				&& getPosition().getYComponent() + getRadius() < getWorld().getHeight()
				&& getPosition().getXComponent() - getRadius() > 0
				&& getPosition().getYComponent() - getRadius() > 0;
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
				&& Util.fuzzyLessThanOrEqualTo(Math.sqrt(velocity.dotProduct(velocity)),getSpeedLimit());	
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
	//TODO: misschien niet meer static? zie sectie 8.2.3 p 524
	//TODO: wat zei de assistent hier weer over? shizzle met setMinRadius. Iets van niet alle ships hebben dezelfde minradius, maar ik weet niet meer hoe op te lossen.
	
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
	public void setMinRadius(double minRadius) throws IllegalArgumentException {
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
				&& (minRadius > 0);
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
	 * 			| result == !Doulbe.isNaN(minRadius)
	 * 			|			&& (radius >= minRadius)
	 */
	@Raw
	public boolean canHaveAsRadius(double radius) {	
		return 	!Double.isNaN(radius)
				&& (Util.fuzzyLessThanOrEqualTo(getMinRadius(),radius));
	}
	
	/**
	 * Variable registering the radius of this collidable.
	 */
	private final double radius;
	
	/**
	 * Returns the mass of this collidable.
	 */
	public abstract double getMass();
	
//	/**
//	 * Variable registering the mass of this collidable.
//	 */
//	public double mass;
	
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
	 * 			The new world for this collidable.
	 * @post	The new world of this collidable is equal to the given world.
	 * 			| (new this).getWorld().equals(world)
	 * @throws	IllegalArgumentException
	 * 			The given world is not a valid world.
	 * 			| !isValidWorld(world)
	 */
	public void setWorld(World world) {
		if (!isValidWorld(world)) 
			throw new IllegalArgumentException();	
		this.world = world;
	}
	
	/**
	 * Check whether this collidable has a proper world associated with it.
	 * @return
	 */
	public boolean hasProperWorld() {
		return false;
	}
	
	/**
	 * Check whether the given world is a valid world for any collidable.
	 * 
	 * @param 	world
	 * 			The world to check.
	 * @return	True if and only if the given world is effective.
	 * 			| result == (world != null)
	 */
	public static boolean isValidWorld(World world) { 
		return world != null;
	}
		
	/**
	 * Variable registering the world of this collidable.
	 */
	private World world;
	
	/**
	 * Returns the distance between this collidable and the given collidable.
	 * 
	 * @param  	other
	 * 			The other collidable.
	 * @return	Returns the distance between this collidable and the given collidable. 
	 * 			If this collidable is equal to the given collidable the result is always zero.
	 * 			| let 
	 * 			|	delta = this.getPosition().subtract(other.getPosition())
	 * 			| in
	 * 			| 	if(other.equals(this))
	 * 			|		result == 0.0
	 * 			| 	else 	
	 * 			|		result == Math.sqrt(delta.times(delta)) - this.getRadius() - other.getRadius() //TODO: dan overlappen ze niet ipv code.
	 * @throws 	IllegalArgumentException
	 * 			The given collidable is not effective.
	 * 			| (other == null)
	 */
	public double getDistanceBetween(Collidable other) throws IllegalArgumentException {
		if (other == null)
			throw new IllegalArgumentException("Non effective collidable!");
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
	 * @param 	other
	 * 			The other collidable.
	 * @return	True if and only if the distance between this collidable and the given collidable is negative 
	 * 			or if the given collidable is equal to this collidable.
	 * 			| result == (getDistanceBetween(other) < 0) || (other.equals(this))  
	 * @throws 	IllegalArgumentException 
	 * 			The given collidable is not effective.
	 * 			| (other == null)
	 */
	public boolean overlap(Collidable other) throws IllegalArgumentException {
		if (other == null)
			throw new IllegalArgumentException("Non effective collidable!");
		if (other.equals(this))
			return true;
		return (getDistanceBetween(other) < 0);
	}
	
	/**
	 * Returns when this collidable, if ever, will collide with the given collidable.
	 * 
	 * @param 	other
	 * 			The other collidable.
	 * @return	Returns the time until this collidable and the given collidable will collide.
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
	 * 			The given collidable is not effective.
	 * 			| (other == null)
	 */
	//TODO: geen kopie van de code, maar in termen van andere methodes (overlap)
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
	 * Returns the position where this collidable and the given collidable will collide.
	 * 
	 * @param 	other
	 * 			The other collidable.
	 * @return	Returns the position where this collidable and the given collidable will collide. //TODO: documentatie ook in termen van methodes
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
	 * 			The given collidable is not effective.
	 * 			| (other == null)
	 */
	public Vector getCollisionPosition(Collidable other) throws IllegalArgumentException{
		if (other == null)
			throw new IllegalArgumentException("Non effective collidable!");	
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
	
	// TODO: documentatie
	public void invertSpeed() {
		Vector newVelocity;
		if(Util.fuzzyEquals(getPosition().getXComponent()-getRadius(),0)) {
			newVelocity = new Vector(-getPosition().getXComponent(),getPosition().getYComponent());
		}
		else if(Util.fuzzyEquals(getPosition().getYComponent()+getRadius(),getWorld().getHeight())) {
			newVelocity = new Vector(getPosition().getXComponent(),-getPosition().getYComponent());
		}
		else if(Util.fuzzyEquals(getPosition().getXComponent()+getRadius(),getWorld().getWidth())) {
			newVelocity = new Vector(-getPosition().getXComponent(),getPosition().getYComponent());
		}
		else if(Util.fuzzyEquals(getPosition().getYComponent()-getRadius(),0)) {
			newVelocity = new Vector(getPosition().getXComponent(),-getPosition().getYComponent());
		}
		else {
			newVelocity = getVelocity();
		}
		setVelocity(newVelocity);
	}
	
	public void bounce(Collidable other) {
		// TODO implement
	}
	
	//TODO gettimetocollisionwithboundary
	
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
}
