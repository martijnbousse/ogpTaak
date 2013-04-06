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
 * @invar	The minimum radius that applies to all collidables must be a valid minimum radius.
 * 			| isValidMinRadius(getMinRadius())
 * @invar	Each collidable must have a proper world to which it is attached.
 * 			| hasProperWorld()
 * 
 * @version	1.0
 * @author Martijn Boussé, Wout Vekemans
 * 
 */
//TODO: Belangrijke mutators moeten IllegalStateException opgooien! bounce, invertspeed, etc 
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
		this.isTerminated = true; 
		// Alle verantwoordelijkheid voor de biderectionele associatie ligt in world.
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
	 * Check whether this collidable can have its position as its position.
	 * 
	 * @param 	position
	 * 			The position to check.
	 * @return 	True if and only if the given position is effective.
	 * 			| result == (position != null)
	 *    //TODO: documentatie!
	 */	
	@Raw
	public boolean canHaveAsPosition(Vector position) {
		if (getWorld() == null)
			return (position != null)
					&& Util.fuzzyLessThanOrEqualTo(position.getXComponent()+getRadius(),Double.MAX_VALUE)
					&& Util.fuzzyLessThanOrEqualTo(position.getYComponent()+getRadius(),Double.MAX_VALUE)
					&& Util.fuzzyLessThanOrEqualTo(0.0,position.getXComponent()-getRadius())
					&& Util.fuzzyLessThanOrEqualTo(0.0,position.getYComponent()-getRadius());   
		//TODO: what about this? misschien aparte checker, isValidPosition?
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
	//TODO: voorlopig kunnen we die hier laten staan aangezien alle minradius hetzelfde zijn, of we kunnen er nu al voor kiezen om die in de subklasses te duwen?
	//TODO: het vreemde hieraan is dat we die setter hebben, zei de assistent. 
	
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
	 * @pre		If the given world is effective, it must already reference this collidable as one of its collidables. // verklaart de volgorde in addAsCollidable
	 * 			| if (world != null)
	 * 				then world.hasAsCollidable(this)
	 * @pre		If the given world is not effective and this collidable references an effective world,  // verklaart removeAsCollidable
	 * 			that world may not reference this collidable as one of its collidables.						// ofwel binding "af"maken ofwel binding breken
	 * 			| if ((world == null) && (getWorld() != null))
	 * 			| 	then !getWorld().hasAsCollidable(this)
	 * @post	This collidable references the given world as teh world to which it is attached.
	 * 			| (new this).getWorld() == world
	 */
	//TODO: setWorld moet zeer beperkt toegankelijk zijn, package visible gaat hier niet (zoals op p. 440) hoe dan? nominaal gedaan zoals in het hb.
	//TODO: world ook @raw? zie p. 440
	@Raw
	public void setWorld(@Raw World world) {
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
	 * 						&& ( (getWorld() == null) || getWorld.hasAsCollidable(this))) 
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
	// canHaveAsWorld is de complementaire checker in de bidirectionele associatie. Aangezien world de controllerende klasse is, 
	// moet hier gewoon de checker uit world aangeroepen worden.
	@Raw
	public boolean canHaveAsWorld(World world) { 
		return ((world == null) || world.canHaveAsCollidable(this));
	}
		
	/**
	 * Variable registering the world of this collidable.
	 */
	private World world = null;
	
	/**
	 * Returns the distance between this collidable and the given collidable.
	 * 
	 * @param  	other
	 * 			The other collidable.
	 * @return	Returns the distance between this collidable and the given collidable. 
	 * 			If this collidable is equal to the given collidable the result is always zero.
	 * 			| if (other == this)
	 * 			|	then result == 0.0
	 * 			| else if (result != 0.0)
	 * 			|	then this.overlap(other) == false
	 * 
	 *   //TODO: zoiets?
	 * 
	 * 			| let 
	 * 			|	delta = this.getPosition().subtract(other.getPosition())
	 * 			| in
	 * 			| 	if(other.equals(this))
	 * 			|		result == 0.0
	 * 			| 	else 	
	 * 			|		result == Math.sqrt(delta.times(delta)) - this.getRadius() - other.getRadius() 
	 * @throws 	IllegalArgumentException
	 * 			The given collidable is not effective.
	 * 			| (other == null)
	 * @throws	IllegalStateException
	 * 			This collidable is terminated.
	 * 			| isTerminated()
	 * @throws	IllegalStateException
	 * 			The given collidable is terminated.
	 * 			| other.isTerminated()
	 */
	public double getDistanceBetween(Collidable other) throws IllegalArgumentException {
		if (isTerminated())
			throw new IllegalStateException("This collidable is terminated!");
		if (other == null)
			throw new IllegalArgumentException("Non effective collidable!");		//TODO: goed zo? komt nu wel een aantal keer voor, aparte checker voor nodig?
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
	 * @param 	other
	 * 			The other collidable.
	 * @return	True if and only if the distance between this collidable and the given collidable is negative 
	 * 			or if the given collidable is equal to this collidable.
	 * 			| result == (getDistanceBetween(other) < 0) || (other.equals(this))  
	 * @throws 	IllegalArgumentException 
	 * 			The given collidable is not effective.
	 * 			| (other == null)
	 * @throws	IllegalStateException
	 * 			This collidable is terminated.
	 * 			| isTerminated()
	 * @throws	IllegalStateException
	 * 			The given collidable is terminated.
	 * 			| other.isTerminated()
	 */
	public boolean overlap(Collidable other) throws IllegalArgumentException {
		if (isTerminated())
			throw new IllegalStateException("This collidable is terminated!");
		if (other == null)
			throw new IllegalArgumentException("Non effective collidable!");	
		if (other.isTerminated())
			throw new IllegalStateException("The given collidable is terminated!");
		if (other.equals(this))
			return true;
		return (getDistanceBetween(other) < 0);
	}
	
	/**
	 * Returns when this collidable, if ever, will collide with the given collidable.
	 * 
	 * @param 	other
	 * 			The other collidable.
	 * @effect	Returns the time until this collidable and the given collidable will collide, if ever.
	 * @effect	True if and only if the collidables touch each other after moving the calculated time.
	 *			| if this.move(result) && other.move(result)
	 *			|	then this.getDistanceBetween(other) == 0
	 * @throws 	IllegalArgumentException
	 * 			The given collidable is not effective.
	 * 			| (other == null)
	 * @throws	IllegalStateException
	 * 			This collidable is terminated.
	 * 			| isTerminated()
	 * @throws	IllegalStateException
	 * 			The given collidable is terminated.
	 * 			| other.isTerminated()
	 */
	public double getTimeToCollision(Collidable other) throws IllegalArgumentException{
		if (isTerminated())
			throw new IllegalStateException("This collidable is terminated!");
		if (other == null)
			throw new IllegalArgumentException("Non effective collidable!");
		if (other.isTerminated())
			throw new IllegalStateException("The given collidable is terminated!");
		
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
	
	
	//TODO snelheid = 0 arithmetics
	/**
	 * Returns when this collidable, if ever, will collide with the boundary.
	 * 
	 * @return	The amount of time until this ship collides with the boundary of its world.
	 * 			| ...
	 * @throws	...
	 * 			| ...
	 */
	public double getTimeToCollisionWithBoundary() {
		if(hasProperWorld()) {
			return Math.min(getMinXCollision(), getMinYCollision());
		}
		return Double.POSITIVE_INFINITY;
	}
	
	private double getMinXCollision() {
		if(!Util.fuzzyEquals(0, getVelocity().getXComponent())) {
			double maximumXTime = (getWorld().getWidth() - getPosition().getXComponent() - getRadius())/getVelocity().getXComponent();
			double zeroXTime = -getPosition().getXComponent() - getRadius()/getVelocity().getXComponent();
			if(!Util.fuzzyLessThanOrEqualTo(0, getVelocity().getXComponent())) {
				return zeroXTime;
			}
		return maximumXTime;
		}
		return Double.POSITIVE_INFINITY;
	}
	
	private double getMinYCollision() {
		if(!Util.fuzzyEquals(0, getVelocity().getYComponent())) {
			double maximumYTime = (getWorld().getHeight() - getPosition().getYComponent() - getRadius())/getVelocity().getYComponent();
			double zeroYTime = -getPosition().getYComponent() - getRadius()/getVelocity().getYComponent();
			if(!Util.fuzzyLessThanOrEqualTo(0, getVelocity().getYComponent())) {
				return zeroYTime;
			}
		return maximumYTime;
		}
		return Double.POSITIVE_INFINITY;
	}
	
	/**
	 * Returns the position where this collidable and the given collidable will collide.
	 * 
	 * @param 	other
	 * 			The other collidable.
	 * @return	Returns the position where this collidable and the given collidable will collide. //TODO: documentatie ook in termen van methodes
	 * 			
	 * @effect	... geen idee?
	 * 
	 * 
	 * 
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
	 * @throws	IllegalStateException
	 * 			This collidable is terminated.
	 * 			| isTerminated()
	 * @throws	IllegalStateException
	 * 			The given collidable is terminated.
	 * 			| other.isTerminated()
	 */
	public Vector getCollisionPosition(Collidable other) throws IllegalArgumentException{
		if (isTerminated())
			throw new IllegalStateException("This collidable is terminated!");
		if (other == null)
			throw new IllegalArgumentException("Non effective collidable!");
		if (other.isTerminated())
			throw new IllegalStateException("The given collidable is terminated!");
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
	/**
	 * Inverts the speed of this collidable.
	 * 
	 * @return	...
	 * 			| ...
	 */
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
	
	/**
	 * This ship bounces with the other ship.
	 * 
	 * @param 	other
	 * 			The other collidable.
	 * @effect 	| let	
	 * 			| 	deltaR = other.getPosition().subtract(this.getPosition());
	 * 			| 	deltaV = other.getVelocity().subtract(this.getVelocity());double sigma = this.getRadius()+other.getRadius();
	 * 			| 	J = 2*this.getMass()*deltaR.dotProduct(deltaV)/(sigma*(this.getMass()+other.getMass()));
	 * 			| 	Jvector = deltaR.scale(J/sigma);
	 * 			| 	newVelocityThis = this.getVelocity().add(Jvector.scale(1/this.getMass()));
	 * 			| 	newVelocityOther = other.getVelocity().subtract(Jvector.scale(1/other.getMass()));
	 * 			|in
	 * 			| 	this.setVelocity(newVelocityThis);
	 * 			| 	other.setVelocity(newVelocityOther);
	 */
	public void bounce(Collidable other) {
		try {
			Vector deltaR = other.getPosition().subtract(this.getPosition());
			Vector deltaV = other.getVelocity().subtract(this.getVelocity());
			double sigma = this.getRadius()+other.getRadius();
			
			if(Util.fuzzyLessThanOrEqualTo(2*this.getMass()*deltaR.dotProduct(deltaV), Double.MAX_VALUE)
					&& Util.fuzzyLessThanOrEqualTo(sigma*(this.getMass()+other.getMass()), Double.MAX_VALUE)) {
				double J = 2*this.getMass()*deltaR.dotProduct(deltaV)/(sigma*(this.getMass()+other.getMass()));
				Vector Jvector = deltaR.scale(J/sigma);
				
				Vector newVelocityThis = this.getVelocity().add(Jvector.scale(1/this.getMass()));
				Vector newVelocityOther = other.getVelocity().subtract(Jvector.scale(1/other.getMass()));
				
				this.setVelocity(newVelocityThis);
				other.setVelocity(newVelocityOther);
			} else { 
				throw new TimesOverflowException();
			}
		} catch(Exception exc) {
			// TODO wat doen we hiermee?
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
	}
	
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
				   + " Radius: " + getRadius() + "]";
	}
}
