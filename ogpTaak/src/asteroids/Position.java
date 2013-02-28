package asteroids;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Value;

/**
 * A class of positions involving an x and y component.
 * 
 * @version	1.0
 * @author 	Martijn Boussé, Wout Vekemans
 *
 */
@Value
public class Position {
	
	/**
	 * Variable referencing the y component of this position.
	 */
	private final double ycomponent;
	
	/**
	 * Initialize this new position with given x and y components.
	 * 
	 * @param 	xcomponent
	 * 			The x position for this new position.
	 * @param 	ycomponent
	 * 			The y component for this new position.
	 * @post	The x component for this new position is equal to the given x component.
	 * 			| (new this).getXComponent() == xcomponent 
	 * @post	The y component for this new position is equal to the given y component.
	 * 			| (new this).getYComponent() == ycomponent 
	 * @throws	?
	 */
	public Position(double xcomponent, double ycomponent){
		this.xcomponent = xcomponent;
		this.ycomponent = ycomponent;
	}
	
	/**
	 * Return the x component of this position.
	 */
	@Basic @Immutable
	public double getXComponent(){
		return this.xcomponent;
	}
	
	/**
	 * Variable referencing the x component of this position.
	 */
	private final double xcomponent;
	
	/**
	 * Return the y component of this position.
	 */
	@Basic @Immutable
	public double getYComponent(){
		return this.ycomponent;
	}
	
	
	
	/**
	 * Compute the sum of this position and the other position.
	 * 
	 * @param 	other
	 * 			The other position to add.
	 * @return	The x and y components of the resulting position is equal to the sum 
	 * 			of the corresponding components of both positions.
	 * 			| result.getXComponent() == this.getXComponent + other.getXComponent 
	 * 			| && result.getYComponent() == this.getYComponent + other.getYComponent
	 * @throws	IllegalArgumentException
	 * 			The other position is not effective.
	 * 			| other == null
	 */
	public Position add(Position other)
		throws IllegalArgumentException{
		if (other == null)
			throw new IllegalArgumentException("Non-effective position");
		return new Position(getXComponent() + other.getXComponent(), getYComponent() + other.getYComponent());
	}
	
	/**
	 * Compute the subtraction of the other position from this position.
	 * 
	 * @param 	other
	 * 			The position to subtract.
	 * @return	The x and y components of the resulting position is equal to the sum 
	 * 			of the corresponding x and y components of this position and the negation of the other position.
	 * @throws	IllegalArgumentException
	 * 			The other position is not effective.
	 * 			| other == null
	 * 
	 * 
	 * Moet ook een negate() gedefinieerd worden?
	 * 
	 */
	public Position subtract(Position other)
			throws IllegalArgumentException{
			if (other == null)
				throw new IllegalArgumentException("Non-effective position");
			return new Position(getXComponent() - other.getXComponent(),getYComponent() - other.getYComponent());
		}
	
	/**
	 * Compute the negation of this position.
	 * 
	 * @return	...
	 * 		    | ...
	 * 
	 * 
	 * nodig?
	 */
	public Position negate(){
		//TODO:implement
		return null;
		
	}
	
	/**
	 * Compute the product of this position with the given factor.
	 * 
	 * @param 	factor
	 * 			The factor to multiply with.
	 * @return	The x and y components of the resulting position are equal to the product 
	 * 			of the corresponding components of this position and the given factor.
	 * 			| result.getXComponent() == factor * this.getXComponent
	 * 			| && result.getYComponent() == factor * this.getYComonent
	 */
	public Position times(double factor){
		return new Position(factor*getXComponent(),factor*getYComponent());
	}
	
	/**
	 * Check whether this position is equal to the given object.
	 * 
	 * @return	True if and only if the given object is effective, 
	 * 			if this position and the given object belong to the same class, 
	 * 			and if this position and the other object interpreted as a position 
	 * 			have equal x and y components.
	 * 			| ...
	 */
	@Override
	public boolean equals(Object other){
		return true;
	}

	
	
	

}
