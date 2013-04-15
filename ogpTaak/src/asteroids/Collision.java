package asteroids;

import be.kuleuven.cs.som.annotate.*;
import collidable.Collidable;


/**
 * A class of collisions involving two collidables, the time to collide and a position.
 * 
 * @invar	...
 * 			| isValidTime(time)
 * @invar	...
 * 			| isValidCollidables(first,second)
 * @author	Martijn Bousse, Wout Vekemans
 * @version	1.0
 *
 */
@Value
public class Collision {

	/**
	 * Initialize this new collision with given collidables and time.
	 * @post	...
	 * 			| new.getTime = time
	 * @post	...
	 * 			| new.getFirst = first
	 * @post	...
	 * 			| new.getSecond = second
	 * @throws 	IllegalArgumentException
	 * 			The given time,position or first collidable is invalid
	 * 			| !isValidTime(time) || !isValidFirstCollidable(first)
	 */
	public Collision(Collidable first, Collidable second, double time) throws IllegalArgumentException {
		if(!isValidTime(time) || !isValidCollidables(first, second)) {
			throw new IllegalArgumentException();
		}
		this.first = first;
		first.setLastCollision(second);
		this.second = second;
		if(second!=null)
			second.setLastCollision(first);
		this.time = time;
	}
	
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
	 * 			| result == first != null && !first.isTerminated() && 
					((second == null ) || (!second.isTerminated())) 
	 */
	public boolean isValidCollidables(Collidable first, Collidable second) {
		return first != null && !first.isTerminated() && 
				((second == null ) || (!second.isTerminated())) ;
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
		return (time>0);
	}
	
	private double time;

}
