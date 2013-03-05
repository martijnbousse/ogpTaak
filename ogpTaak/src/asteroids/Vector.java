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
	//TODO: documentatie ook fuzzyEquals ipv == ja
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
	 * Returns the product of the other vector and this vector.
	 *
	 * @param 	other
	 * 			The other vector for the product.
	 * @return	Returns the product of this vector and the given vector.
	 * 			| result == this.getXComponent()*other.getXComponent() + this.getYComponent()*other.getYComponent()
	 * @throws 	IllegalArgumentException
	 * 			The other vector is not effective.
	 * 			| (other == null) 
	 * @throws	TimesOverflowException
	 * 			The multiplication overflows.
	 * 			| Util.fuzzyLessThanOrEqualTo(this.getXComponent(),Double.POSITIVE_INFINITY/other.getXComponent()) ||  
	 *				Util.fuzzyLessThanOrEqualTo(this.getYComponent(),Double.POSITIVE_INFINITY/other.getYComponent())
	 */
	@Immutable
	public double times(Vector other) throws IllegalArgumentException, TimesOverflowException {
		if (other == null)
			throw new IllegalArgumentException("Non effective vector!");
		else if (! (Util.fuzzyLessThanOrEqualTo(this.getXComponent(),Double.POSITIVE_INFINITY/(other.getXComponent()+Util.EPSILON)) ||  
				 	Util.fuzzyLessThanOrEqualTo(this.getYComponent(),Double.POSITIVE_INFINITY/(other.getYComponent()+Util.EPSILON))))
			throw new TimesOverflowException();
		return (this.getXComponent()*other.getXComponent() + this.getYComponent()*other.getYComponent());
	}
	
	/**
	 * Returns the subtraction of the other vector from this vector.
	 * 	
	 * @param 	other
	 * 			The other vector to subtract.
	 * @return	The resulting vector is equal to the subtraction of this vector and the other vector.
	 * 			| result.equals(this.getXComponent()-other.getXComponent(),this.getYComponent()-other.getYComponent()); 
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

	public static double getAngle(Vector vector1,Vector vector2) {
		double xDiff = vector1.getXComponent()-vector1.getXComponent();
		double yDiff = vector1.getYComponent()-vector2.getYComponent();
		if(xDiff > 0){
			if(yDiff > 0){
				return Math.atan(yDiff/xDiff)+Math.PI;
			}
			if(yDiff < 0){
				return Math.atan(-yDiff/xDiff)+Math.PI;
			}
		}
		else if(xDiff < 0){
			if(yDiff > 0){
				return Math.atan(-yDiff/xDiff);
			}
			if(yDiff < 0){
				return Math.atan(yDiff/xDiff);
			}
		}
		else if(xDiff == 0){
			return Math.PI/2;
		}
		return 0;
	}	
}
