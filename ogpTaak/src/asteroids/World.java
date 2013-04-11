package asteroids;

import be.kuleuven.cs.som.annotate.*;
import java.util.*;
import collidable.*;


/**
 * A class of virtual worlds, containing asteroids, ships and bullets.
 *
 * @Invar	|isValidWidth(getWidth())
 * @Invar	|isValidHeight(getHeight())
 * @Invar	|isValidMaxDimension(getMaxWidth())
 * @Invar	|isValidMaxDimension(getMaxHeight())
 * @Invar	The collidables attached to each world must be proper collidables for that world.
 * 			| hasProperCollidables()
 * 
 * @version	1.0
 * @author 	Wout Vekemans, Martijn Bousse
 *
 */
public class World {
	/**
	 * Initialize this new world with given width, height and no collidables attached to it.
	 * 
	 * @param 	width
	 * 			The width for this new world.
	 * @param 	height
	 * 			The height for this new world.
	 * @post	The new width of this new world is equal to the given width.
	 * 			| (new this).getWidth().equals(width)
	 * @post	The new height of this new world is equal to the given height.
	 * 			| (new this).getHeight().equals(height)
	 * @post	No collidables are attached to this new world.
	 * 			| (new this).getNbCollidables() == 0
	 */
	@Raw
	public World(double width, double height) {
		if(isValidWidth(width))
			this.width = width;
		else {
			this.width = maxWidth;
		}
		if(isValidHeight(height))
			this.height = height;
		else {
			this.height =maxHeight;
		}
	}
	
	/**
	 * Initialize this new world with all default values.
	 * 
	 * @effect	This new world is initialized with width and height equal to Double.MAX_VALUE.
	 */
	public World() {
		this(Double.MAX_VALUE,Double.MAX_VALUE);
	}
	
	/**
	 * Check whether this world is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	/**
	 * Terminate this world.
	 * 
	 * @post	This World is terminated.
	 * 			| (new this).isTerminated()
	 * @effect	Each non-terminated collidable attached to this world is removed from this world.
	 * 			| for each collidable in getAllCollidables:
	 * 			|	if (!collidable.isTerminated())
	 * 			|		then this.removeAsCollidable(collidable)
	 */
	//TODO: p. 437, alle non-terminated collidables worden verwijderd, maar er kunnen geen terminated collidables in die lijst zitten?? 
	//																	toch wordt het zo gedaan in de cursus?
	//TODO: neen! origineel kunnen er wel terminated collidables inzitten, maar ik heb dat verandert dat dat niet kan! dus if test mag weg.
	public void terminate() {
		for (Collidable collidable : collidables) {
			if (!collidable.isTerminated()) {
				collidable.setWorld(null);
				this.collidables.remove(collidable);
				// Hier wordt niet collidable.terminate() aangeroepen! Moet in evolve.
			}		
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
	@Basic @Immutable
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Check whether the given width is a valid width for any world.
	 * 
	 * @param 	width 
	 * 			The width to check.
	 * @return	| result == !Double.isNaN(width) 
	 *				&& Util.fuzzyLessThanOrEqualTo(width, maxWidth)
	 *				&& 0 < width
	 */
	public static boolean isValidWidth(double width) {
		return !Double.isNaN(width) 
				&& Util.fuzzyLessThanOrEqualTo(width, maxWidth)
				&& 0 < width;
	}
	
	//TODO: isValidWidth En isValidMaxDimension?
	
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
	 * Check whether the given maximum dimension is a valid maximum dimension for any world.
	 * 
	 * @param 	maxDimension
	 * 			The maximum dimension to check.
	 * @return	| result == !Double.isNaN(maxDimension)
	 * 			|			&& (maxDimension > 0)
	 */
	public static boolean isValidMaxDimension(double maxDimension) {
		return 	!Double.isNaN(maxDimension)
				&& (maxDimension > 0);
	}
	
	/**
	 * 
	 * @param 	maxWidth
	 * 			The new maximum width for all worlds
	 * @post 	(new World).maxWidth == maxWidth
	 */
	public static void setMaxWidth(double maxWidth){
		if(isValidMaxDimension(maxWidth))
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
	@Basic @Immutable
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Check whether the given height is a valid height for any world.
	 * 
	 * @param 	height 
	 * 			The height to check.
	 * @return	| result == !Double.isNaN(height) 
	 *				&& Util.fuzzyLessThanOrEqualTo(width, maxHeight)
	 *				&& 0 < height
	 */
	public static boolean isValidHeight(double height) {
		return !Double.isNaN(height) 
				&& Util.fuzzyLessThanOrEqualTo(height, maxHeight)
				&& 0 < height;
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
	 * 
	 * @param 	maxHeight
	 * 			The new maximum height for all worlds.
	 * @post 	(new World).maxHeight == maxHeight
	 */
	public static void setMaxHeight(double maxHeight){
		if(isValidMaxDimension(maxHeight))
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
	 * Return a set collecting all collidables associated with this world.
	 * 
	 * @return	The resulting set does not contain a null reference.
	 * 			| !result.contains(null)
	 * @return	Each collidable in the resulting set is attached to this world, and vice versa.
	 * 			| for each collidable in collidables:
	 * 			|	(result.contains(collidable) == this.hasAsCollidable(collidable))
	 */
	public Set<Collidable> getAllCollidables() {
		return new HashSet<Collidable>(this.collidables);
	}
	
	/**
	 * Return the number of collidables that are attached to this world.
	 * 
	 * @return	The number of collidables that are attached to this world.
	 * 			| card( {collidable in collidables | hasAsCollidable(collidable)})   //TODO: zie p. 410 -> Wat is dit voor iets raar?
	 */
	public int getNbCollidables() {
		return getAllCollidables().size();
	}
		
	/**
	 * Check whether this world has the given collidable as one of the collidables attached to it.
	 * 
	 * @param 	collidable
	 * 			The collidable to check.
	 */
	@Basic @Raw
	public boolean hasAsCollidable(Collidable collidable) {
		return this.collidables.contains(collidable); // constant time
	}
	
	/**
	 * Checks whether this world can have the given collidable as one of its collidables.
	 * 
	 * @param 	collidable
	 * 			The collidable to check.  
	 * @return  False if the given collide is not effective
	 * 			| if (collidable == null)
	 * 			|	then result == false
	 * 			Otherwise, true if and only if this world is not yet terminated or the given collidable is also terminated.
	 * 			| else result ==
	 * 			|	( (!this.isTerminated()) || collidable.isTerminated() )
	 */
	// een terminated world kan geen collidable krijgen en een getermineerde collidable kan ook niet toegevoegd worden.
	// let op ! aangepast tov p 425, je kan dus NIET een terminated collidable toevoegen nu, origineel ging dat wel...
	@Raw
	public boolean canHaveAsCollidable(Collidable collidable) {
		return ((collidable != null) && !( (this.isTerminated()) || (collidable.isTerminated())));
	}
	
	/**
	 * Check whether this world has proper collidables attached to it.
	 * 
	 * @return	True if and only if this world can have each of its collidables as a collidable attached to it,
	 * 			and if each of these collidables references this world as their world.
	 * 			| result == 
	 * 			|	for each collidable in collidables:
	 * 			|		( if (this.hasAsCollidable(collidable))
	 * 						then canHaveAsCollidable(collidable)
	 * 						  && (collidable.getWorld() == this))
	 */
	// encapsulating the class invariants cf representation invariants for the set.
	@Raw
	public boolean hasProperCollidables() {
		for (Collidable collidable: this.collidables) {
			if (!canHaveAsCollidable(collidable))
				return false;
			if (collidable.getWorld() != this)
				return false;
		}
		return true;
	}
	
	/**
	 * Add the given collidable to the set of collidables attached to this world.
	 * 
	 * @param 	collidable
	 * 			The collidable to be added.
	 * @post	This world has the given collidable as one of its collidables.
	 * 			| (new this).hasAsCollidable(collidable)
	 * @post 	The given collidable references this world as the world to which it is attached
	 * 			| (new collidable).getWorld() == (new this)
	 * @throws  IllegalArgumentException
	 * 			This world cannot have the given collidable as one of its collidables.
	 * 			| ! canHaveAsCollidable(collidable)
	 * @throws  IllegalArgumentException
	 * 			The given collidable is already attached to some world.
	 * 			| collidable.getWorld() != null
	 * 			| 		&& collidable != null
	 */
	public void addAsCollidable(Collidable collidable) throws IllegalArgumentException{
		if (!canHaveAsCollidable(collidable))
			throw new IllegalArgumentException();
		if (collidable.getWorld() != null)
			throw new IllegalArgumentException();
		this.collidables.add(collidable);
		collidable.setWorld(this);        // merk op: de volgorde, zie ook setWorld documentatie
	}
	
	/**
	 * Remove the given collidable from the set of collidables attached to this world.
	 * 
	 * @param 	collidable
	 * 			The collidable to be removed.
	 * @post	This world does not have the given collidable as one of its collidables.
	 * 			| ! (new this).hasAsCollidable(collidable)
	 * @post    If this world has the given collidable as one of its collidables, the given collidable is no longer attached to any world.
	 * 			| if (hasAsCollidable(collidable))
	 * 			| 	then (new collidable).getWorld() == null
	 */
	public void removeAsCollidable(Collidable collidable) {
		if (hasAsCollidable(collidable))
			this.collidables.remove(collidable);
			collidable.setWorld(null);		// merk op: de volgorde, zie ook setWorld documentatie
	}
	
	// OPMERKINGEN BIJ REPRESENTATIE INVARIANTEN
	// * Representation invariants == trade-off between simplicity and efficiency in implementation of mutators and inspectors. (p. 420 2de alinea)
	// * definitie p. 417 overgenomen en dat doorgezet voor de methodes die de bidirectionele relatie verzorgen.
	// * onmiddellijk initialiseren (p. 416)
	// * final - the instance variable itself is qualified final, meaning that it will reference the same set during the entire lifetime 
	//   of the world for which it collects collidables (p. 416)
	// * HashSet - this class offers constant time performance for the basic operations (add, remove, contains and size), 
	//   assuming the hash function disperses the elements properly among the buckets. 
	// * invar: effective -> geen zorgen maken over nullpointers, want volgens regel 92 moet je set rechtstreeks aanroepen.
	
	// TODO: of HashTree ? zie documentatie, moeten grondige verklaring geven waarom we voor set/hashset kiezen.
	/**
	 * Set collecting references to collidables attached to this world.
	 * 
	 * @Invar	The set of collidables is effective.
	 * 			| collidables != null
	 * @Invar	Each element in the set of collidables references a collidable that is an acceptable collidable for this world.
	 * 			| for each collidable in collidables:
	 * 			|	canHaveAsCollidable(collidable)
	 * @Invar	Each collidable in the set of collidables references this world as the world to which it is attached.
	 * 			| for each collidable in collidables:
	 * 			|	(collidable.getWorld() == this)	
	 */
	private final Set<Collidable> collidables = new HashSet<Collidable>();
	
	/**
	 * Advances the time in this world.
	 * 
	 * @param dt
	 */
	public void evolve(double dt) throws IllegalArgumentException{
		// collidable.terminate() aanroepen + verwijderen van de lijst van collidables in de if structuur.
		// Als dan terminate wordt aangeroepen op een asteroid, wordt in de gespecialiseerde terminate van asteroid twee nieuwe asteroids toegevoegd.
		
		
		
		
		// TODO: 1.6 Advacing Time
		// 1 getNextCollision
		// 2 move voor t (uit collision data)
		// 3 resolve the collision met die if structuur
		// 4 ...
		// 5 move(t)
		
		while(dt!=0) {
			Collision next = getNextCollision();
			if(!Util.fuzzyLessThanOrEqualTo(next.getTime(),dt) && next.getTime() >= 0) {
				for(Collidable collidable : getAllCollidables()) {
					collidable.move(dt);
					if(collidable instanceof Ship) {
						((Ship) collidable).thrust(dt);
					}
				}
				dt=0;
			}
			else if(next.getTime() > 0 && next.getTime() != Double.POSITIVE_INFINITY) {
				for(Collidable collidable : getAllCollidables()) {
					collidable.move(next.getTime());
				}
				dt-=next.getTime();
			} else {
			}
		}
		
	}
	
	/**
	 * Predict the collisions in this world.
	 * 
	 * 
	 */
	public void predictCollisions() {
		// return type = lijst van collisions (value class?)
		// bevat collisionposition, timetocollision, this en other.
		// ofwel ipv een type "collision" kan je ook een map meegeven en in evolve bereken je wat je nodig hebt.
		// als we dat doen met een datastructuur moet ook hier weer representatie invarianten voor geschreven worden.
		
		// zie ook efficient prediction.
		
		// kunnen dit misschien enkel uitvoeren als er een verandering is gebeurt (zie extra) 
		// via een methode getNextCollision halen we dan uit de datastructuur de juiste gegevens voor in evolve.
	}
	
	/**
	 * Returns the first collision that will happen in this world.
	 * 
	 * 
	 */
	public Collision getNextCollision() {
		// return type = collision?
		Collidable first = null;
		Collidable second = null;
		double time = Double.MAX_VALUE;
		for(Collidable collidable : getAllCollidables()) {
			double collisionWithBoundary = collidable.getTimeToCollisionWithBoundary();
			for(Collidable collidable2 : getAllCollidables()) {
				double collisionWithOther = collidable.getTimeToCollision(collidable2);
				double firstCollisionTime = Math.min(collisionWithBoundary,collisionWithOther);
				if(!Util.fuzzyLessThanOrEqualTo(time, firstCollisionTime) && Util.fuzzyLessThanOrEqualTo(0,firstCollisionTime)) {					
						time = firstCollisionTime;
						first = collidable;
					if(time == collisionWithBoundary) {
						second = null;
					} else {
						second = collidable2;
					}
				}
			}
		}
		if(time == Double.MAX_VALUE)
			return null;
		if(second != null) {
			Vector position = first.getCollisionPosition(second);
			return new Collision(first, second, time, position);
		}
		else { 
			Vector position = first.getCollisionWithBoundaryPosition();
			return new Collision(first, second, time, position);
		}
	}
	
	/**
	 * Return a textual representation of this world.
	 * 
	 * @return	A string consisting of the textual representation of the width and the height of this world,		// TODO: and a listing of all its collidables?
	 * 			separated by a space and enclosed in square brackets.
	 * 			| result.equals(
	 * 			|	"[" + "Width: " + getWidth().toString() 
	 * 			|		+ " Height: " + getHeight().toString() + "]" )
	 */
	@Override
	public String toString(){
		return "[" + "Width: " + getWidth() 
				   + " Height: " + getHeight() + "]";
	}
}
