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
		this.second = second;
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
		return (time > 0);
	}
	
	private double time;
	
	/**
	 * Check whether this collision is equal to the given object.
	 * 
	 * @return	True if and only if the given object is effective, 
	 * 			if this collision and the given object belong to the same class, 
	 * 			and if this collision and the other object interpreted as a collision 
	 * 			have equal first, second and time.
	 * 			| result == 
	 * 			|  	( (other != null)
	 * 			|  && (this.getClass() == other.getClass())
	 * 			|  && (this.getFirst().equals(otherCollision.getFirst()))
	 * 			|  && (this.getSecond().equals(otherCollision.getSecond())) 
	 * 			|  && (Util.fuzzyEquals(this.getTime(),otherCollision.getTime())) )
	 */
	@Override
	public boolean equals(Object other){
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		Collision otherCollision = (Collision) other;
		return (this.getFirst().equals(otherCollision.getFirst())
				&& this.getSecond().equals(otherCollision.getSecond())
				&& Util.fuzzyEquals(this.getTime(),otherCollision.getTime()));
	}
	
	/**
	 * Return a textual representation of this collision.
	 * 
	 * @return	A string consisting of the textual representation of a collision 
	 * 			.......... , separated by spaces and ended with a square bracket.
	 * 			| .....
	 */
	@Override
	public String toString(){
		if (second == null)
			return "[" + " First: " + getFirst().toString() +  " with boundary " + "]";
		return "[" + " First: " + getFirst().toString() +  " and second: " + getSecond().toString() + "]";
	}
	
	/**
	 * Return the hash code for this collision.
	 */
	@Override
	public int hashCode() {
		return ( getFirst().hashCode() + getSecond().hashCode() + ((Double) getTime()).hashCode()); 
	}

}
