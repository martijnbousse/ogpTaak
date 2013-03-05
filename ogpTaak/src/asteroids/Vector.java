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
	//TODO: @raw ?
	//TODO: totaal, nominaal of defensief?
	//TODO: nadenken over referentie lekken ! zie laatste les
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
	 * 			| result == Math.sqrt(this.getXComponent()*this.getXComponent()+this.getYComponent()*this.getYComponent())
	 */
	//TODO: exceptions?
	@Immutable	
	//TODO: immutable want alle componenten zijn ook immutable
	//TODO: arithmetic exceptions?
	public double getMagnitude(){
		return Math.sqrt(this.getXComponent()*this.getXComponent()+this.getYComponent()*this.getYComponent() );
	}
	
	/**
	 * Returns the product of the other vector and this vector.
	 *
	 * @param 	other
	 * 			The other vector for the product.
	 * @return	Returns the product of this vector and the given vector.
	 * 			| result == this.getXComponent()*other.getXComponent() + this.getYComponent()*other.getYComponent()
	 * @throws 	IllegalArgumentException
	 * 			The other vector is not effective.
	 * 			| (other == null) 
	 * @throws	...
	 * 			|
	 */
	//TODO: times van dezelfde vector is hetzelfde als getMagnitude -> redundantie! getMagnitude weglaten? Of beide houden, maar dan return in magnitude aanpassen, ifv times.
	//TODO: arithmetic exceptions? overflows -> hier of in ship oplossen? best hier veronderstel ik
	public double times(Vector other) throws IllegalArgumentException {
		if (other == null)
			throw new IllegalArgumentException("Non effective vector!");
		return (this.getXComponent()*other.getXComponent() + this.getYComponent()*other.getYComponent());
	}
	
	/**
	 * Returns the subtraction of the other vector from this vector.
	 * 	
	 * @param 	other
	 * 			The other vector to subtract.
	 * @return	The resulting vector is equal to the subtraction of this vector and the other vector.
	 * 			| result.equals(this.getXComponent()-other.getXComponent(),this.getYComponent()-other.getYComponent());
	 * 
	 *  //TODO: onthoud dat hier equals en niet == gebruikt moet worden! checken of dat steeds het geval is!
	 *  
	 * @throws 	IllegalArgumentException
	 * 			The other vector is not effective.
	 * 			| (other == null)
	 * @throws	...
	 * 			|
	 */
	//TODO: arithmetic exceptions? overflows
	//TODO: zie p. 296, ifv negate ??
	public Vector subtract(Vector other) throws IllegalArgumentException {
		if (other == null)
			throw new IllegalArgumentException("Non effective vector!");
		return new Vector(this.getXComponent()-other.getXComponent(),this.getYComponent()-other.getYComponent());
	}
	
}
