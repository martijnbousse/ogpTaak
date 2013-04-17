package asteroids;

import collidable.Collidable;
import be.kuleuven.cs.som.annotate.*;
import exceptions.IllegalTimeException;


/**
 * A class of collisions involving two collidables, the time to collide and a position.
 * 
 * @invar	The time of each collision must be a valid time.
 * 			| isValidTime(time)
 * @invar	The collidables of each collision must be valid collidables.
 * 			| isValidCollidables(first,second)
 * 
 * @author	Martijn Bousse, Wout Vekemans
 * @version	1.0
 *
 */
@Value
public class Collision {

	/**
	 * Initialize this new collision with given first collidable, given second collidable and given time.
	 * 
	 * @param	first
	 * 			The first collidable.
	 * @param	second
	 * 			The second collidable.
	 * @param	time
	 * 			The given time.
	 * @post	The new first collidable of this new collision is equal to the given first collidable.
	 * 			| new.getFirst = first
	 * @post	The new second collidable of this new collision is equal to the given second collidable.
	 * 			| new.getSecond = second
	 * @post	The new time of this new collision is equal to the given time.
	 * 			| new.getTime = time
	 * @throws 	IllegalArgumentException
	 * 			The given time or the collidables are invalid.
	 * 			| !isValidTime(time) || !isValidCollidable(first,second)
	 */
	public Collision(Collidable first, Collidable second, double time) throws IllegalArgumentException, IllegalTimeException {
		if(!isValidTime(time)) {
			throw new IllegalTimeException(time);
		}
		if(!isValidCollidables(first, second)) {
			throw new IllegalArgumentException();
		}
		this.first = first;
		this.second = second;
		this.time = time;
	}
	
	/**
	 * Returns the first collidable of this collision.
	 */
	@Basic @Immutable @Raw
	public Collidable getFirst() {
		return first;
	}
	
	/**
	 * Variable referencing the first collidable of this collision.
	 */
	private Collidable first;
	
	/**
	 * Returns the second collidable of this collision.
	 */
	@Basic @Immutable @Raw
	public Collidable getSecond() {
		return second;
	}
	
	/**
	 * Variable referencing the second collidable of this collision.
	 */
	private Collidable second;
	
	/**
	 * Check whether the given collidables are valid collidables for any collision.
	 * 
	 * @param 	first
	 * 			The first collidable.
	 * @param	second
	 * 			The second collidable.
	 * @return	True if and only if the given collidables are both effective, non-terminated and touching each other
	 * 			or the first collidable is touching the border of its world.
	 * 			| result == first != null && !first.isTerminated() && 
					((second == null ) || (!second.isTerminated())) 
	 */
	//TODO: touching each other?
	public boolean isValidCollidables(Collidable first, Collidable second) {
		return first != null && !first.isTerminated() && 
				((second == null ) || (!second.isTerminated())) ;
	}
	
	/**
	 * Returns the time of this collision.
	 */
	@Basic @Immutable @Raw
	public double getTime() {
		return time;
	}
	
	/**
	 * Checks whether the given time is a valid time for any collision.
	 * 	
	 * @param 	time
	 * 			The given time.
	 * @return	True if and only if the given time is bigger than zero.
	 * 			| result == (time>0)
	 */
	public boolean isValidTime(double time) {
		return (time > 0);
	}
	
	/**
	 * Variable referencing the time of this collision.
	 */
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
	 * @return	A string consisting of the textual representation of the first collidable,
	 * 			the second collidable and the time of this collision, seperated by spaces
	 * 			and enclosed with square brackets. If the second collidable is null, than
	 * 			only the first collidable and the time are given, together with the textual
	 * 			comment "with boundary".
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
