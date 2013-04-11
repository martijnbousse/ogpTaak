package asteroids;

import be.kuleuven.cs.som.annotate.*;
import collidable.Collidable;


/**
 * A class of collisions involving two collidables, the time to collide and a postion.
 * 
 * @invar	...
 * 			| isValidTime(time)
 * @invar	...
 * 			| isValidCollidables(first,second)
 * @invar	...
 * 			| isValidPosition(position)
 * @author	Martijn Bousse, Wout Vekemans
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
		if(!isValidTime(time) || !isValidCollidables(first, second) || !isValidPosition(position)) {
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
	 * @param	second
	 * 			...
	 * @return	True if and only if the given collidables are both effective, non-terminated and touching each other
	 * 			or the first collidable is touching the border of its world.
	 * 			| result == first != null && !first.isTerminated()
	 */
	public boolean isValidCollidables(Collidable first, Collidable second) {
		return first != null && !first.isTerminated() && 
				((second == null ) || 
						(!second.isTerminated() && first.getDistanceBetween(second)==0)) ;
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
