package asteroids;

import be.kuleuven.cs.som.annotate.*;
import java.util.*;

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
	 * Checks whether this world can have a given bullet as one of its bullets.
	 * @param 	bullet
	 * 			the bullet to check
	 * @return	| result == bullet != null
	 * 			| 	&& bullet.getWorld() != null
	 */
	public boolean canHaveAsBullet(Bullet bullet) {
		return 	bullet != null 
				&& bullet.getWorld() == null;
	}
	
	//TODO: 1 of 3 methodes om DFOs toe te voegen en te checken?
	
	private List<Collidable> flyingObjects;
}
