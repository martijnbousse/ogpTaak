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
 * @invar	|isValidWidth(getWidth())
 * @invar	|isValidHeight(getHeight())
 * @invar	|isValidMaxDimension(getMaxWidth())
 * @invar	|isValidMaxDimension(getMaxHeight())
 * 
 * 
 * @version	1.0
 * @author Wout Vekemans, Martijn Boussé
 *
 */
public class World {
	/**
	 * Initialize this new world with given width and height.
	 * @param width
	 * @param height
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
	 * Return the width of this world.
	 */
	@Basic @Immutable
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * 
	 * @param 	width 
	 * 			the width to check.
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
	
	@Basic @Immutable
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * 
	 * @param 	height 
	 * 			the height to check.
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
	 * 			The new maximum Height for all worlds
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
	 * Returns a list of all ships in this world.
	 */
	//TODO: set + 7.5.1 p489
	public List<Ship> getShips() {
		List<Ship> ships = new ArrayList<Ship>();
		for(Collidable flying : flyingObjects)
			if(flying instanceof Ship)
				ships.add((Ship) flying);
		return ships;
	}
	
	/**
	 * Return the number of ships in this world.
	 * @return	result == getShips().size()
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
		for(Collidable flying : flyingObjects)
			if(flying instanceof Asteroid)
				asteroids.add((Asteroid) flying);
		return asteroids;
	}
	
	/**
	 * Return the number of asteroids in this world.
	 * @return	result == getAsteroids().size()
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
		for(Collidable flying : flyingObjects)
			if(flying instanceof Bullet)
				bullets.add((Bullet) flying);
		return bullets;
	}
	
	/**
	 * Return the number of bullets in this world.
	 * @return	result == getBullets().size()
	 */
	public int getNbOfBullets() {
		return getBullets().size();
	}
	
	/**
	 * Checks whether this world can have the given collidable as one of its collidables.
	 * @param 	collidable
	 * 			The collidable to check.  
	 * @return  ...
	 * 			| 
	 */
	public boolean canHaveAsCollidable(Collidable collidable) {
		return false;
	}
	
	/**
	 * Check whether this world has the given collidable as one of the collidables attached to it.
	 * @param collidable
	 */
	@Basic
	public boolean hasAsCollidable(Collidable collidable) {
		return false;
	}
	
	/**
	 * Check whether this world has proper collidables attached to it.
	 * @return	...
	 * 			| 
	 */
	public boolean hasProperCollidables() {
		return false;
	}
	
	/**
	 * Add the given collidable to the set of collidables attached to this world.
	 * @param 	collidable
	 * 			The collidable to be added.
	 * @post	This world has the given collidable as one of its collidables.
	 * 			| new.hasAsCollidable(collidable)
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
		
	}
	
	/**
	 * Remove the given collidable from the set of collidables attached to this world.
	 * @param 	collidable
	 * 			The collidable to be removed.
	 * @post	This world does not have the given collidable as one of its collidables.
	 * 			| ! new.hasAsCollidable(collidable)
	 * @post    If this world has the given collidable as one of its collidables, the given collidable is no longer attached to any world.
	 * 			| if (hasAsCollidable(collidable))
	 * 			| 	then (new collidable).getWorld() == null
	 */
	public void removeAsCollidable(Collidable collidable) {
		
	}
	
	//TODO: 1 of 3 methodes om collidablesss toe te voegen en te checken?
	
	private List<Collidable> flyingObjects;
	
	public void evolve(double dt) {
		
	}
}
