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
	
	// TODO: de andere getAll's terug naar hier halen en werken met class cast exceptions!
	
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
	 * 			Otherwise, true if and only if this world and the collidable are not yet terminated and the collidable fits this world's dimensions.
	 * 			| else result ==
	 * 			|	!((this.isTerminated()) || collidable.isTerminated()) && collidable.getRadius() < Math.min(getWidth()/2, getHeight()/2)
	 */
	@Raw
	public boolean canHaveAsCollidable(Collidable collidable) {
		return ((collidable != null) && !( (this.isTerminated()) || (collidable.isTerminated())) && collidable.getRadius() < Math.min(getWidth()/2, getHeight()/2));
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
		Collision next = getNextCollision();
		if (next != null && Util.fuzzyLessThanOrEqualTo(0.0, next.getTime()) && Util.fuzzyLessThanOrEqualTo(next.getTime(),dt) && !Util.fuzzyEquals(0, dt) ) {
			System.out.println("NEXT COLLISION:" + next.toString()); //PRINT NEXT COLLISION
			for(Collidable collidable : getAllCollidables()) {
				collidable.move(next.getTime());
			}
			resolveCollision(next);
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
	
//	public void evolve(double dt) throws IllegalArgumentException {
//		Collision nextCollision = getNextCollision();
//		if ((nextCollision != null) && (nextCollision.getTime()<dt)) {//time is altijd groter dan nul, zie inv collision EN getTime moet STRIKT kleiner zijn dan dt (anders kan getTime lichtjes groter zijn dan dt -> negatieve tijd)
//			System.out.println("NEXT COLLISION:" + nextCollision.toString()); //PRINT NEXT COLLISION
//			for(Collidable collidable : getAllCollidables()) {
//				collidable.move(nextCollision.getTime());
//			}
//			resolveCollision(nextCollision);
//			evolve(dt-nextCollision.getTime());
//		}
//		else {
//			for(Collidable collidable : getAllCollidables()) {
//				collidable.move(dt);
//				if(collidable instanceof Ship) {
//					((Ship) collidable).thrust();
//				}
//			}
//		}
//	}

	private void resolveCollision(Collision next) {
		System.out.println("Actually entered resolveCollision");
		Collidable first = next.getFirst();
		Collidable second = next.getSecond();
		if(second == null) {
			if (Bullet.class.isInstance(first) && !((Bullet) first).bouncedOnce())
				((Bullet) first).setBouncedOnce(); //TODO: bullet mag niet eigen schip kapot maken
			first.bounceOfBoundary();	
			//TODO overlap met bullet: collide immediately
		}
		else if(Bullet.class.isInstance(first) || Bullet.class.isInstance(second)){
//			if(!((Bullet) first).getSource().equals(second) || !((Bullet) second).getSource().equals(first))
				first.terminate();
				second.terminate();
		}
		else if(first.getClass().equals((second.getClass()))) {
				first.bounce(second); // er staat nu een if test in bounce!
		}
		else {
			if(Asteroid.class.isInstance(first))
				second.terminate();
			else
				first.terminate();
		}
	}
	
	/**
	 * Returns the first collision that will happen in this world.
	 * 
	 * 
	 */
//	public Collision getNextCollision() {
//		Collidable first = null;
//		Collidable second = null;
//		ArrayList<Collidable> collidables = new ArrayList<Collidable>(getAllCollidables());
//		
//		double time = Double.MAX_VALUE;
//		for(int i = 0; i<getNbCollidables(); i++) {
//			
//			double collisionWithBoundary = collidables.get(i).getTimeToCollisionWithBoundary();
//			
//			for(int j = i+1; j<getNbCollidables(); j++) {
//				
////				if(!collidables.get(j).equals(collidables.get(i).getLastCollision()) && !collidables.get(i).equals(collidables.get(j).getLastCollision()))	{
//					// NIET NODIG?
//				if(!collidables.get(i).overlap(collidables.get(j))) {
//					double collisionWithOther = collidables.get(i).getTimeToCollision(collidables.get(j));
////					double firstCollisionTime = Math.min(collisionWithBoundary,collisionWithOther);
//					if(!Util.fuzzyLessThanOrEqualTo(time, firstCollisionTime) && Util.fuzzyLessThanOrEqualTo(0,firstCollisionTime)) {					
//							time = firstCollisionTime;
//							first = collidables.get(i);
//						if(Util.fuzzyEquals(time,collisionWithBoundary)) {
//							second = null;
//						} else {
//							second = collidables.get(j);
//						}
//					}
//				}
//			}
//		}
		
		//TODO: BELANGRIJK!!!!!!!!!!
		// Als een collidable de rand bereikt (en niet bounced) dan blijkt het dat getNextCollision constant dezelfde Collision (zie prompt) blijft
		// berekenen en teruggeven. Het probleem zit em in evolve bij evolve(dt-next.getTime()). Volgens mij wordt dat verschil enorm klein ma net 
		// niet klein genoeg.
		
		
		
		
		
		
////		EENS OPNIEUW GESCHREVEN, WERKT NIET, maar hiet zit het probleem! 
//		double time = Double.MAX_VALUE;
//		for(int i = 0; i<getNbCollidables(); i++) {
//			for(int j = i+1; j<getNbCollidables(); j++) {
//				if(!collidables.get(i).overlap(collidables.get(j))) {
//					double possibleNewCollisionTime = collidables.get(i).getTimeToCollision(collidables.get(j));
//					if (!Util.fuzzyLessThanOrEqualTo(time,possibleNewCollisionTime) && Util.fuzzyLessThanOrEqualTo(0,possibleNewCollisionTime))
//						time = possibleNewCollisionTime; 
//						first = collidables.get(i);
//						second = collidables.get(j);
//				}
//			}
//			double collisionWithBoundaryTime = collidables.get(i).getTimeToCollisionWithBoundary(); 
//			if (!Util.fuzzyLessThanOrEqualTo(time,collisionWithBoundaryTime) && Util.fuzzyLessThanOrEqualTo(0,collisionWithBoundaryTime)) {
//				time = collisionWithBoundaryTime;
//				first = collidables.get(i);
//				second = null;
//				}
//		}

		
//		if(time == Double.POSITIVE_INFINITY || Util.fuzzyLessThanOrEqualTo(time,0.0)) { // Tweede voorwaarde mag niet weg, dan werken bullets niet meer!!
//			return null;
//		}
//
////		try {
//			Collision nextCollision = new Collision(first, second, time);
//			return nextCollision;
//		//TODO: niet de bedoeling, vervangen door de fuzzy in de if hierboven. Of toch een illegalargument houden?   
////		} catch(IllegalArgumentException e) {	
////			return null;
////		}
//
//	}
	
	// WERKT ALLEMAAL NIET....
	
	public Collision getNextCollision() {
		Collision nextCollisionWithCollidable = getNextCollisionWithCollidable();
		Collision nextCollisionWithBoundary = getNextCollisionWithBoundary();
		if (nextCollisionWithBoundary == null) {
			return nextCollisionWithCollidable;
		}
		else if (nextCollisionWithCollidable == null)
			return nextCollisionWithBoundary;
		else if (Util.fuzzyLessThanOrEqualTo(nextCollisionWithBoundary.getTime(),nextCollisionWithCollidable.getTime())) {
			return nextCollisionWithBoundary;
		} else {
		return nextCollisionWithCollidable;
		}
	}
	
	public Collision getNextCollisionWithCollidable() {
		// Allocation
		Collidable first = null;
		Collidable second = null;
		ArrayList<Collidable> collidables = new ArrayList<Collidable>(getAllCollidables());
		double time = Double.MAX_VALUE;
		// Loop
		for(int i = 0; i<getNbCollidables(); i++) {
			for(int j = i+1; j<getNbCollidables(); j++) {
				double possibleNewCollisionTime = collidables.get(i).getTimeToCollision(collidables.get(j));
				if (!Util.fuzzyLessThanOrEqualTo(time,possibleNewCollisionTime) && 0<possibleNewCollisionTime)
					time = possibleNewCollisionTime; 
					first = collidables.get(i);
					second = collidables.get(j);
			}
		}
		// Return 
		if (time == Double.MAX_VALUE)
			return null;
		System.out.println(time>0);
		return new Collision(first, second, time);
	}
	
	public Collision getNextCollisionWithBoundary() {
		// Allocation
		Collidable first = null;
		ArrayList<Collidable> collidables = new ArrayList<Collidable>(getAllCollidables());
		double time = Double.MAX_VALUE;
		// Loop
		for(int i = 0; i<getNbCollidables(); i++) {
			if (!collidables.get(i).overlapWithBoundary()) {
			double collisionWithBoundaryTime = collidables.get(i).getTimeToCollisionWithBoundary(); 
			if (!Util.fuzzyLessThanOrEqualTo(time,collisionWithBoundaryTime) && Util.fuzzyLessThanOrEqualTo(0,collisionWithBoundaryTime)) {
				time = collisionWithBoundaryTime;
				first = collidables.get(i);
			}
		}
		}
		// Return
		if ((time == Double.MAX_VALUE) && Util.fuzzyLessThanOrEqualTo(time,0.0))
			return null;
		return new Collision(first, null, time);
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
