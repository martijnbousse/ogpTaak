package asteroids;

import be.kuleuven.cs.som.annotate.*;

import java.util.*;

import collidable.Asteroid;
import collidable.Bullet;
import collidable.Collidable;
import collidable.Ship;


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
 * @author 	Wout Vekemans, Martijn Boussé
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
	 * 			| (new this).getheight().equals(height)
	 * @post	No collidables are attached to this new world.
	 * 			| (new this).getNbCollidables() == 0
	 */
	public World(double width, double height){
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
	 * @Effect	Each non-terminated collidable attached to this world is removed from this world.
	 * 			| for each collidable in getAllCollidables:
	 * 			|	if (!collidable.isTerminated())
	 * 			|		then this.removeAsCollidable(collidable)
	 */
	//TODO: p. 437, alle non-terminated collidables worden verwijderd, maar er kunnen geen terminated collidables in die lijst zitten?? 
	//																	toch wordt het zo gedaan in de cursus?
	public void terminate() {
		for (Collidable collidable: collidables) {
			if (!collidable.isTerminated()) {
				collidable.setWorld(null);
				this.collidables.remove(collidable);
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
	public boolean isValidWidth(double width) {
		return !Double.isNaN(width) 
				&& Util.fuzzyLessThanOrEqualTo(width, maxWidth)
				&& 0 < width;
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
	public boolean isValidHeight(double height) {
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
	
	
	//TODO: toch een getAllCollidables en getNbCollidables nodig, wordt gebruik van gemaakt in de constructor post annotatie en in documentatie van methodes.
	//       je kan dat ook formuleren aan de hand van de drie aparte getters, want anders is er wel wat redundantie.
	
	
	
	/**
	 * Returns a list of all ships in this world.
	 */
	//TODO: set + 7.5.1 p489, moet set worden!
	public List<Ship> getShips() {
		List<Ship> ships = new ArrayList<Ship>();
		for(Collidable flying : collidables)
			if(flying instanceof Ship)
				ships.add((Ship) flying);
		return ships;
	}
	
	/**
	 * Return the number of ships in this world.
	 * 
	 * @return	| result == getShips().size()
	 */
	public int getNbOfShips() {
		return getShips().size();
	}
	
	/**
	 * Returns a list of all asteroids in this world.
	 */
	@Basic
	public List<Asteroid> getAsteroids() {
		List<Asteroid> asteroids = new ArrayList<Asteroid>();
		for(Collidable flying : collidables)
			if(flying instanceof Asteroid)
				asteroids.add((Asteroid) flying);
		return asteroids;
	}
	
	/**
	 * Return the number of asteroids in this world.
	 * 
	 * @return	| result == getAsteroids().size()
	 */
	public int getNbOfAsteroids() {
		return getAsteroids().size();
	}
	
	/**
	 * Returns a list of all bullets in this world.
	 */
	@Basic
	public List<Bullet> getBullets() {
		List<Bullet> bullets = new ArrayList<Bullet>();
		for(Collidable flying : collidables)
			if(flying instanceof Bullet)
				bullets.add((Bullet) flying);
		return bullets;
	}
	
	/**
	 * Return the number of bullets in this world.
	 * 
	 * @return	| result == getBullets().size()
	 */
	public int getNbOfBullets() {
		return getBullets().size();
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
	//TODO: een terminated world kan geen collidable krijgen en een getermineerde collidable kan ook niet toegevoegd worden.
	public boolean canHaveAsCollidable(Collidable collidable) {
		return ((collidable != null) && ((!this.isTerminated()) || collidable.isTerminated()));
	}
	
	/**
	 * Check whether this world has the given collidable as one of the collidables attached to it.
	 * 
	 * @param 	collidable
	 * 			The collidable to check.
	 */
	@Basic
	public boolean hasAsCollidable(Collidable collidable) {
		return this.collidables.contains(collidable); // constant time
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
		collidable.setWorld(this);			//TODO: setworld is een "hulpmethode" en moet dus sterk afgeschermd worden!
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
			collidable.setWorld(null);
	}
	
	//TODO: 1 of 3 methodes om collidablesss toe te voegen en te checken?
	
	
	// OPMERKINGEN
	// * Representation invariants == trade-off between simplicity and efficiency in implementation of mutators and inspectors. (p. 420 2de alinea)
	// * definitie p. 417 overgenomen
	// * onmiddellijk initialiseren (p. 416)
	// * final - the instance variable itself is qualified final, meaning that it will reference the same set during the entire lifetime 
	// of the world for which it collects collidables (p. 416)
	// * HashSet - this class offers constant time performance for the basic operations (add, remove, contains and size), 
	//   assuming the hash function disperses the elements properly among the buckets. TODO: of HashTree ? zie documentatie
	// * invar: effective -> geen zorgen maken over nullpointers, want volgens regel 92 moet je set rechtstreeks aanroepen.
	
	// TODO: p. 421 representatie inv cf klasse inv (encapsulated in hasProperCollidables) ~ hetzelfde?
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
	 * 
	 * @param dt
	 */
	public void evolve(double dt) {
		
	}
	
	//TODO: toString
}
