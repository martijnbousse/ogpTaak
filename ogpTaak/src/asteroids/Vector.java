package asteroids;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of vectors involving an x- and y-component.
 * 
 * @Invar	\\TODO: invarianten?
 * 
 * @author	Martijn Boussé, Wout Vekemans
 * @version	1.0
 *
 */
@Value
public class Vector {
	
	/**
	 * Variable referencing the x-component of this vector.
	 */
	private final double xcomponent;
	
	/**
	 * Variable referencing the y-component of this vector.
	 */
	private final double ycomponent;
	
	/**
	 * Initialize this new vector with given x- and y-component.
	 * 
	 * @param 	xcomponent
	 * 			The x-component for this new vector.
	 * @param 	ycomponent
	 * 			The y-component for this new vector.
	 * @post	The x-component of this new vector is equal to the given x-component.
	 * 			| (new this).getXComponent() == xcomponent
	 * @post	The y-component of this new vector is equal to the given y-component.
	 * 			| (new this).getYComponent() == ycomponent
	 */
	//TODO: Moet in de documentatie ook fuzzyEquals gebruikt worden?
	//TODO: totaal, nominaal of defensief?
	public Vector(double xcomponent, double ycomponent){
		
		//TODO: exceptions? and @raw ?
		
		this.xcomponent = xcomponent;
		this.ycomponent = ycomponent;
	}
	
	/**
	 * Return the x-component of this vector.
	 */
	@Basic @Immutable
	public double getXComponent(){
		return this.xcomponent;
		// TODO: @raw ?
	}
	
	/**
	 * Return the y-component of this vector.
	 */
	@Basic @Immutable
	public double getYComponent(){
		return this.ycomponent;
	}
	
	/**
	 * Check whether this vector is equal to the given object.
	 * 
	 * @return	True if and only if the given object is effective, 
	 * 			if this vector and the given object belong to the same class, 
	 * 			and if this vector and the other object interpreted as a vector 
	 * 			have equal x- and y-components.
	 * 			| result == 
	 * 			|	( (other != null)
	 * 			|  && (this.getClass() == other.getClass())
	 * 			|  && (this.getXComponent() == ((Vector) other).getXComponent())
	 * 			|  && (this.getYComponent() == ((Vector) other).getYComponent())
	 */
	@Override
	//TODO: documentatie ook fuzzyEquals ipv == ?
	public boolean equals(Object other){
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		Vector otherVector = (Vector) other;
		return Util.fuzzyEquals(this.getXComponent(),otherVector.getXComponent())
				&& Util.fuzzyEquals(this.getYComponent(),otherVector.getYComponent());
	}
	
	/**
	 * Returns the magnitude of this vector.
	 * 
	 * @return	Returns the magnitude of this vector.
	 * 			| result == Math.sqrt( this.getXComponent()^2 + this.getYComponent()^2 )
	 */
	//TODO: exceptions?
	public double getMagnitude(){
		return Math.sqrt(this.getXComponent()*this.getXComponent()+ this.getYComponent()*this.getYComponent() );
	}
	
}
