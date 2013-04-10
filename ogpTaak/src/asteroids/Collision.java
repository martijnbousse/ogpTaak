package asteroids;

import be.kuleuven.cs.som.annotate.*;
import collidable.Collidable;


/**
 * A class of collisions involving two collidables, the time to collide and a postion.
 * 
 * @invar	...
 * 			| isValidTime(time)
 * @invar	...
 * 			| isValidFirstCollidable(first)
 * @invar	...
 * 			| isValidPosition(position)
 * @author	Martijn Bouss�, Wout Vekemans
 * @version	1.0
 *
 */
@Value
public class Collision {

	/**
	 * Initialize this new collision with given collidables, time and position.
	 * @post	...
	 * 			| new.getPosition = position
	 * @post	...
	 * 			| new.getTime = time
	 * @post	...
	 * 			| new.getFirst = first
	 * @post	...
	 * 			| new.getSecond = second
	 * @throws 	IllegalArgumentException
	 * 			The given time,position or first collidable is invalid
	 * 			| !isValidTime(time) || !isValidFirstCollidable(first) || !isValidPosition(position)
	 */
	public Collision(Collidable first, Collidable second, double time,
			Vector position) throws IllegalArgumentException {
		if(!isValidTime(time) || !isValidFirstCollidable(first) || !isValidPosition(position)) {
			throw new IllegalArgumentException();
		}
		this.first = first;
		this.second = second;
		this.time = time;
		this.position = position;
	}
	
	@Basic @Immutable @Raw
	public Vector getPosition() {
		return position;
	}
	
	/**
	 * ...
	 * 
	 * @return	...
	 * 			| result position != null
	 */
	public boolean isValidPosition(Vector position) {
		return position != null;
	}
	
	private Vector position;
	
	@Basic @Immutable @Raw
	public Collidable getFirst() {
		return first;
	}
	
	private Collidable first;
	
	/**
	 * ...
	 * @param 	first
	 * 			...
	 * @return	...
	 * 			| result == first != null && !first.isTerminated()
	 */
	public boolean isValidFirstCollidable(Collidable first) {
		return first != null && !first.isTerminated();
	}
	
	@Basic @Immutable @Raw
	public Collidable getSecond() {
		return second;
	}
	
	private Collidable second;
	
	@Basic @Immutable @Raw
	public double getTime() {
		return time;
	}
	
	/**
	 * ...	
	 * @param 	time
	 * @return	...
	 * 			| result == Util.fuzzyLessThanOrEqualTo(0, time)
	 */
	public boolean isValidTime(double time) {
		return Util.fuzzyLessThanOrEqualTo(0, time);
	}
	
	private double time;

}
