package gameObjects;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

import java.util.*;

import support.Collision;

/**
 * A class of virtual worlds, containing asteroids, ships and bullets.
 * 
 * @Invar | isValidWidth(getWidth())
 * @Invar | isValidHeight(getHeight())
 * @Invar | isValidMaxDimension(getMaxWidth())
 * @Invar | isValidMaxDimension(getMaxHeight())
 * @Invar | hasProperCollidables()
 * 
 * @version 1.0
 * @author Wout Vekemans, Martijn Bousse
 * 
 */
public class World {
	/**
	 * Initialize this new world with given width, height and no collidables
	 * attached to it.
	 * 
	 * @param width
	 *            The width for this new world.
	 * @param height
	 *            The height for this new world.
	 * @post The new width of this new world is equal to the given width. | (new
	 *       this).getWidth().equals(width)
	 * @post The new height of this new world is equal to the given height. |
	 *       (new this).getHeight().equals(height)
	 * @post No collidables are attached to this new world. | (new
	 *       this).getNbCollidables() == 0
	 */
	@Raw
	public World(double width, double height) {
		if (isValidWidth(width))
			this.width = width;
		else {
			this.width = maxWidth;
		}
		if (isValidHeight(height))
			this.height = height;
		else {
			this.height = maxHeight;
		}
	}

	/**
	 * Initialize this new world with all default values.
	 * 
	 * @effect This new world is initialized with width and height equal to
	 *         Double.MAX_VALUE. | this(Double.MAX_VALUE,Double.MAX_VALUE)
	 */
	public World() {
		this(Double.MAX_VALUE, Double.MAX_VALUE);
	}

	/**
	 * Check whether this world is terminated.
	 */
	@Basic
	@Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}

	/**
	 * Terminate this world.
	 * 
	 * @post This World is terminated. | (new this).isTerminated()
	 * @effect Each non-terminated collidable attached to this world is removed
	 *         from this world. | for each collidable in getAllCollidables: | if
	 *         (!collidable.isTerminated()) | then
	 *         this.removeAsCollidable(collidable)
	 */
	public void terminate() {
		Iterator<Collidable> iter = getAllCollidables().iterator();
		while (iter.hasNext()) {
			removeAsCollidable(iter.next());
		}
		this.isTerminated = true;
	}

	/**
	 * Variable registering whether or not this world is terminated.
	 */
	private boolean isTerminated = false;

	/**
	 * Return the width of this world.
	 */
	@Basic
	@Immutable
	public double getWidth() {
		return this.width;
	}

	/**
	 * Check whether the given width is a valid width for any world.
	 * 
	 * @param width
	 *            The width to check.
	 * @return | result == !Double.isNaN(width) &&
	 *         Util.fuzzyLessThanOrEqualTo(width, maxWidth) && 0 < width
	 */
	public static boolean isValidWidth(double width) {
		return !Double.isNaN(width)
				&& Util.fuzzyLessThanOrEqualTo(width, maxWidth) && 0 < width;
	}

	/**
	 * Variable registering the width of this world.
	 */
	private final double width;

	/**
	 * Returns the maximum width for all worlds.
	 */
	public static double getMaxWidth() {
		return maxWidth;
	}

	/**
	 * 
	 * @param maxWidth
	 *            The new maximum width for all worlds
	 * @post (new World).maxWidth == maxWidth
	 */
	public static void setMaxWidth(double maxWidth) {
		if (isValidMaxDimension(maxWidth))
			World.maxWidth = maxWidth;
		else {
			World.maxWidth = Double.MAX_VALUE;
		}
	}

	/**
	 * Variable registering the maximum width of any world.
	 */
	private static double maxWidth = Double.MAX_VALUE;

	/**
	 * Returns the height of this world.
	 */
	@Basic
	@Immutable
	public double getHeight() {
		return this.height;
	}

	/**
	 * Check whether the given height is a valid height for any world.
	 * 
	 * @param height
	 *            The height to check.
	 * @return | result == !Double.isNaN(height) | &&
	 *         Util.fuzzyLessThanOrEqualTo(width, maxHeight) | && 0 < height
	 */
	public static boolean isValidHeight(double height) {
		return !Double.isNaN(height)
				&& Util.fuzzyLessThanOrEqualTo(height, maxHeight) && 0 < height;
	}

	/**
	 * Variable registering the height of this world.
	 */
	private final double height;

	/**
	 * Returns the maximum height for all worlds.
	 */
	public static double getMaxHeight() {
		return maxHeight;
	}

	/**
	 * Set the maximum height for all worlds to the given height.
	 * 
	 * @param maxHeight
	 *            The new maximum height for all worlds.
	 * @post | (new World).maxHeight == maxHeight
	 */
	public static void setMaxHeight(double maxHeight) {
		if (isValidMaxDimension(maxHeight))
			World.maxHeight = maxHeight;
		else {
			World.maxHeight = Double.MAX_VALUE;
		}
	}

	/**
	 * Variable registering the maximum height of any world.
	 */
	private static double maxHeight = Double.MAX_VALUE;

	/**
	 * Check whether the given maximum dimension is a valid maximum dimension
	 * for any world.
	 * 
	 * @param maxDimension
	 *            The maximum dimension to check.
	 * @return | result == !Double.isNaN(maxDimension) | && (maxDimension > 0)
	 */
	public static boolean isValidMaxDimension(double maxDimension) {
		return !Double.isNaN(maxDimension) && (maxDimension > 0);
	}

	/**
	 * Return a set collecting all collidables associated with this world.
	 * 
	 * @return The resulting set does not contain a null reference. |
	 *         !result.contains(null)
	 * @return Each collidable in the resulting set is attached to this world,
	 *         and vice versa. | for each collidable in collidables: |
	 *         (result.contains(collidable) == this.hasAsCollidable(collidable))
	 */
	public Set<Collidable> getAllCollidables() {
		return new HashSet<Collidable>(this.collidables);
	}

	/**
	 * Return the number of collidables that are attached to this world.
	 * 
	 * @return The number of collidables that are attached to this world. |
	 *         card( {collidable in collidables | hasAsCollidable(collidable)})
	 */
	public int getNbCollidables() {
		return getAllCollidables().size();
	}

	/**
	 * Return a set collecting all ships associated with this world.
	 * 
	 * @return The resulting set does not contain a null reference. |
	 *         !result.contains(null)
	 * @return Each ship in the resulting set is attached to this world, and
	 *         vice versa. | for each ship in ships: | (result.contains(ship) ==
	 *         this.hasAsCollidable(ship))
	 */
	public Set<Ship> getAllShips() {
		Set<Ship> ships = new HashSet<Ship>();
		for (Collidable collidable : collidables)
			try {
				ships.add((Ship) collidable);
			} catch (ClassCastException e) {
				// ignore
			}
		return ships;
	}

	/**
	 * Return the number of ships that are attached to this world.
	 * 
	 * @return The number of ships that are attached to this world. | card(
	 *         {collidable in collidables | collidable instanceof Ship})
	 */
	public int getNbShips() {
		return getAllShips().size();
	}

	/**
	 * Return a set collecting all asteroids associated with this world.
	 * 
	 * @return The resulting set does not contain a null reference. |
	 *         !result.contains(null)
	 * @return Each asteroid in the resulting set is attached to this world, and
	 *         vice versa. | for each asteroid in asteroids: |
	 *         (result.contains(asteroid) == this.hasAsCollidable(asteroid))
	 */
	public Set<Asteroid> getAllAsteroids() {
		Set<Asteroid> asteroids = new HashSet<Asteroid>();
		for (Collidable collidable : collidables)
			try {
				asteroids.add((Asteroid) collidable);
			} catch (ClassCastException e) {
				// ignore
			}
		return asteroids;
	}

	/**
	 * Return the number of asteroids that are attached to this world.
	 * 
	 * @return The number of asteroids that are attached to this world. | card(
	 *         {collidable in collidables | collidable instanceof Asteroid})
	 */
	public int getNbAsteroids() {
		return getAllAsteroids().size();
	}

	/**
	 * Return a set collecting all bullets associated with this world.
	 * 
	 * @return The resulting set does not contain a null reference. |
	 *         !result.contains(null)
	 * @return Each bullet in the resulting set is attached to this world, and
	 *         vice versa. | for each bullet in bullets: |
	 *         (result.contains(bullet) == this.hasAsCollidable(bullet))
	 */
	public Set<Bullet> getAllBullets() {
		Set<Bullet> bullets = new HashSet<Bullet>();
		for (Collidable collidable : collidables)
			try {
				bullets.add((Bullet) collidable);
			} catch (ClassCastException e) {
				// ignore
			}
		return bullets;
	}

	/**
	 * Return the number of bullets that are attached to this world.
	 * 
	 * @return The number of bullets that are attached to this world. | card(
	 *         {collidable in collidables | collidable instanceof Bullet})
	 */
	public int getNbBullets() {
		return getAllBullets().size();
	}

	/**
	 * Check whether this world has the given collidable as one of the
	 * collidables attached to it.
	 * 
	 * @param 	collidable
	 *          The collidable to check.
	 */
	@Basic
	@Raw
	public boolean hasAsCollidable(Collidable collidable) {
		return this.collidables.contains(collidable);
	}

	/**
	 * Checks whether this world can have the given collidable as one of its
	 * collidables.
	 * 
	 * @param 	collidable
	 *        	The collidable to check.
	 * @return 	False if the given collide is not effective
	 *  	 	| if (collidable == null) 	
	 *  		| 	then result == false 
	 *  		Otherwise, true if and only if this world and the collidable are not yet terminated and the
	 *         	collidable fits this world's dimensions. 
	 *         	| else result == 
	 *         	|	!((this.isTerminated()) || collidable.isTerminated()) 	
	 *         	| 		&& collidable.getRadius() < Math.min(getWidth()/2, getHeight()/2)
	 */
	@Raw
	public boolean canHaveAsCollidable(Collidable collidable) {
		return ((collidable != null)
				&& !((this.isTerminated()) || (collidable.isTerminated())) && collidable
				.getRadius() < Math.min(getWidth() / 2, getHeight() / 2));
	}
	//TODO: deze checker zorgt er nu voor dat de collidable 'past' in de wereld, 
	// maar houdt niet tegen dat een collidable buiten de wereld kan geplaatst worden!
	// zie test op overlapWithBoundary ! 

	/**
	 * Check whether this world has proper collidables attached to it.
	 * 
	 * @return True if and only if this world can have each of its collidables
	 *         as a collidable attached to it, and if each of these collidables
	 *         references this world as their world. | result == | for each
	 *         collidable in collidables: | ( if
	 *         (this.hasAsCollidable(collidable)) | then
	 *         canHaveAsCollidable(collidable) | && (collidable.getWorld() ==
	 *         this))
	 */
	@Raw
	public boolean hasProperCollidables() {
		for (Collidable collidable : this.collidables) {
			if (!canHaveAsCollidable(collidable))
				return false;
			if (collidable.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Add the given collidable to the set of collidables attached to this
	 * world.
	 * 
	 * @param collidable
	 *            The collidable to be added.
	 * @post This world has the given collidable as one of its collidables. |
	 *       (new this).hasAsCollidable(collidable)
	 * @post The given collidable references this world as the world to which it
	 *       is attached | (new collidable).getWorld() == (new this)
	 * @throws IllegalArgumentException
	 *             This world cannot have the given collidable as one of its
	 *             collidables. | !canHaveAsCollidable(collidable)
	 * @throws IllegalArgumentException
	 *             The given collidable is already attached to some world. |
	 *             collidable.getWorld() != null | && collidable != null
	 */
	public void addAsCollidable(Collidable collidable) throws IllegalArgumentException {
		if (!canHaveAsCollidable(collidable))
			throw new IllegalArgumentException();
		if (collidable.getWorld() != null)
			throw new IllegalArgumentException();
		this.collidables.add(collidable);
		collidable.setWorld(this);
	}

	/**
	 * Remove the given collidable from the set of collidables attached to this
	 * world.
	 * 
	 * @param collidable
	 *            The collidable to be removed.
	 * @post This world does not have the given collidable as one of its
	 *       collidables. | ! (new this).hasAsCollidable(collidable)
	 * @post If this world has the given collidable as one of its collidables,
	 *       the given collidable is no longer attached to any world. | if
	 *       (hasAsCollidable(collidable)) | then (new collidable).getWorld() ==
	 *       null
	 */
	public void removeAsCollidable(Collidable collidable) {
		if (hasAsCollidable(collidable))
			this.collidables.remove(collidable);
		collidable.setWorld(null);
	}

	/**
	 * Set collecting references to collidables attached to this world.
	 * 
	 * @Invar The set of collidables is effective. | collidables != null
	 * @Invar Each element in the set of collidables references a collidable
	 *        that is an acceptable collidable for this world. | for each
	 *        collidable in collidables: | canHaveAsCollidable(collidable)
	 * @Invar Each collidable in the set of collidables references this world as
	 *        the world to which it is attached. | for each collidable in
	 *        collidables: | (collidable.getWorld() == this)
	 */
	private final Set<Collidable> collidables = new HashSet<Collidable>();

	/**
	 * Advances the time in this world.
	 * 
	 * @param dt
	 *            The amount of time to advance
	 * @param collisionListener
	 * @effect | let | next = getNextCollision() | in | if next.getTime() > dt |
	 *         then for each collidable in collidables | collidable.move(dt) |
	 *         if(collidable instanceof Ship) | then (Ship)
	 *         collidable.thrust(dt) | else | for each collidable in collidables
	 *         | collidable.move(next.getTime()) | resolveCollision(next) |
	 *         evolve(dt-next.getTime())
	 */
	public void evolve(double dt) throws IllegalArgumentException{
		if(!Util.fuzzyEquals(0.0, dt)) {
			Collision next = null;
			try{
				next = getNextCollision();
			} catch(Exception exc) {
				//numeric error causing fail in calculation of next collision.
				//do nothing
			}
			if (next != null 
					&& Util.fuzzyLessThanOrEqualTo(0.0, next.getTime()) 
					&& Util.fuzzyLessThanOrEqualTo(next.getTime(),dt)
					 ) {
				for(Collidable collidable : getAllCollidables()) {
					collidable.move(next.getTime());
				}
				resolveCollision(next);
				if (next.getSecond()!=null)
				evolve(dt-next.getTime());
			}
			else {
				for(Collidable collidable : getAllCollidables()) {
					collidable.move(dt);
					if(collidable instanceof Ship) {
						((Ship) collidable).thrust(dt);
					}
				}
			}
		}
	}

	/**
	 * Resolve the given collision.
	 * 
	 * @param next
	 *            The given collision.
	 * @effect If there is only one collidable involved with the collision, then
	 *         this this collidable bounces of the boundary. | if
	 *         (next.getSecond() == null) | then
	 *         next.getFirst().bounceOfBoundary() Else the two collidables
	 *         collide. | else next.getFirst().collides(next.getSecond())
	 */
	private void resolveCollision(Collision next) {
		Collidable first = next.getFirst();
		Collidable second = next.getSecond();
		if (second == null) {
			first.bounceOfBoundary();
		} else {
			first.collide(second);
		}
	}
	
	/**
	 *  Returns the first collision of a collidable with a boundary of this world.
	 *  
	 *  @effect For each collidable in collidables which is not overlapping with a boundary,  the time 
	 *  		to collision with the boundary is bigger than or equal to the time of the result.
	 *  		| result == next
	 *  		| 	such that for each collidable in collidables
	 *  		|		if !collidables.get(i).overlapWithBoundary()
	 *  		|   		then Util.fuzzyLessThanOrEqualTo(next.getTime(),collidable.getTimeToCollisionWithBoundary())
	 *  @effect If the time is equal to Double.MAX_VALUE, the result is null. \\TODO: ok? zie ook verder
	 *  		| result == null
	 *  		|	if (time == Double.MAX_VALUE)
	 */
	public Collision getNextCollisionWithBoundary() {
		ArrayList<Collidable> collidables = new ArrayList<Collidable>(getAllCollidables());
		Collidable first = null;
		double time = Double.MAX_VALUE;
		
		for (int i = 0; i < getNbCollidables(); i++) { 
			if (!collidables.get(i).overlapWithBoundary()) {
				double collisionWithBoundary = collidables.get(i).getTimeToCollisionWithBoundary();
				if(!Util.fuzzyLessThanOrEqualTo(time,collisionWithBoundary)) {
					time = collisionWithBoundary;
					first = collidables.get(i);
				}
			}
		}
		if (time == Double.MAX_VALUE)
			return null;
		return new Collision(first,null,time);
	}
	
	/**
	 *  Returns the first collision of two collidables that will happen in this world.
	 *  
	 *  @effect For each collidable in collidables, the time to collision with another non-overlapping collidable 
	 *  		is bigger than or equal to than the time of the result.
	 *  		| result == next
	 *  		| 	such that for each i in 0..getNbCollidables()-1:   \\TODO: klopt dit wel .. ?
	 *  		|		if !collidables.get(i).overlap(collidables.get(i+1))
	 *  		|			then Util.fuzzyLessThanOrEqualTo(next.getTime(),collidables.get(i).getTimeToCollision(collidables.get(i+1));
	 *  		If the time is equal to Double.MAX_VALUE or the time is a negative value, the result is null. \\TODO: goed zo?? laatste is wat vreemd
	 *  		| result == null
	 *  		|	if (time == Double.MAX_VALUE || Util.fuzzyLessThanOrEqualTo(time, 0.0))
	 */
	public Collision getNextCollisionWithOther() {
		ArrayList<Collidable> collidables = new ArrayList<Collidable>(getAllCollidables());
		Collidable first = null;
		Collidable second = null;
		double time = Double.MAX_VALUE;
		
		for (int i = 0; i < getNbCollidables(); i++) { 
			for (int j = i + 1; j < getNbCollidables(); j++) { 
				if (!collidables.get(i).overlap(collidables.get(j))) {
					double collisionWithOther = collidables.get(i).getTimeToCollision(collidables.get(j));
					if(!Util.fuzzyLessThanOrEqualTo(time,collisionWithOther) && Util.fuzzyLessThanOrEqualTo(0,collisionWithOther)) {
						time = collisionWithOther;
						first = collidables.get(i);
						second = collidables.get(j);
					}
				}	
			}			
		}
		if (time == Double.MAX_VALUE || Util.fuzzyLessThanOrEqualTo(time, 0.0)) //TODO: negatief nodig?
			return null;
		return new Collision(first,second,time);
	}
	
	/**
	 *  Returns the first collision that will happen in this world.
	 *  
	 *  @effect If there is no collision with another collidable nor with the boundary return null.
	 *  		| if (getNextCollisionWithBoundary() == null) && (getNextCollisionWithOther() == null)
	 *  		| 	then result == null
	 *  @effect If there is no collision with the boundary, return the collision with another collidable.
	 *  		| if (getNextCollisionWithBoundary() == null)
	 *  		| 	then result == getNextCollisionWithOther(); 
	 *  @effect If there is no collision with another collidable, return the collision with the boundary.
	 *  		| if (getNextCollisionWithOther() == null)
	 *  		| 	then result == getNextCollisionWithBoundary()
	 *  @effect If there is a collision with the boundary and with another collidable, return the first collision.
	 *  		| if (Util.fuzzyLessThanOrEqualTo(getNextCollisionWithBoundary().getTime(),getNextCollisionWithOther().getTime()))
	 *  		|	then result == getNextCollisionWithBoundary()
	 *  		| else getNextCollisionWithOther()
	 */
	public Collision getNextCollision() {
		Collision nextWithBoundary = getNextCollisionWithBoundary();
		Collision nextWithOther = getNextCollisionWithOther();
		
		if (nextWithBoundary == null && nextWithOther == null)
			return null;
		if (nextWithOther == null)
			return nextWithBoundary;
		if (Util.fuzzyLessThanOrEqualTo(nextWithBoundary.getTime(),nextWithOther.getTime()))
			return nextWithBoundary;
		return nextWithOther;
	}
	
	/**
	 * Return a textual representation of this world.
	 * 
	 * @return A string consisting of the textual representation of the width
	 *         and the height of this world, separated by a space and enclosed
	 *         in square brackets. | result.equals( | "[" + "Width: " +
	 *         getWidth().toString() | + " Height: " + getHeight().toString() +
	 *         "]" )
	 */
	@Override
	public String toString() {
		return "[" + "Width: " + getWidth() + " Height: " + getHeight() + "]";
	}
}
