package asteroids;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of vectors involving an x- and y-component.
 * 
 * @Invar	The x-component of each vector must be a valid number.
 * 			| isValidNumber(getXComponent())
 * @Invar	The y-component of each vector must be a valid number.
 * 			| isValidNumber(getYComponent())
 * 
 * @author	Martijn Boussé, Wout Vekemans
 * @version	1.0
 *
 */
@Value
public class Vector {
	
	//TODO: documentatie nakijken
	//TODO: @raw
	
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
	 * @throws 	IllegalArgumentException
	 * 			The given x or y component is invalid.
	 * 			| !isValidNumber(xcomponent) || !isValidNumber(ycomponent)
	 */
	@Raw
	public Vector(double xcomponent, double ycomponent) throws IllegalArgumentException{
		if(!isValidNumber(xcomponent) || !isValidNumber(ycomponent))
			throw new IllegalArgumentException();
		this.xcomponent = xcomponent;
		this.ycomponent = ycomponent;
	}
	
	/**
	 * Return the x-component of this vector.
	 */
	@Basic @Immutable @Raw
	public double getXComponent(){
		return this.xcomponent;
	}
	
	/**
	 * Variable referencing the x-component of this vector.
	 */
	private final double xcomponent;
	
	/**
	 * Return the y-component of this vector.
	 */
	@Basic @Immutable @Raw
	public double getYComponent(){
		return this.ycomponent;
	}
	
	/**
	 * Variable referencing the y-component of this vector.
	 */
	private final double ycomponent;
	
	/**
	 * Returns the sum of the other vector and this vector.
	 * 	
	 * @param 	other
	 * 			The other vector to add.
	 * @return	The resulting vector is equal to the sum of this vector and the other vector.
	 * 			| result.equals(this.getXComponent()+other.getXComponent(),this.getYComponent()+other.getYComponent()); 
	 * @throws 	IllegalArgumentException
	 * 			The other vector is not effective.
	 * 			| (other == null)
	 * @throws	SumOverflowException
	 * 			One of the sums overflows.
	 * 			| this.getXComponent() > Double.MAX_VALUE-other.getXComponent() 
	 * 			| || this.getYComponent() > Double.MAX_VALUE-other.getYComponent()
	 */
	public Vector add(Vector other) throws IllegalArgumentException, SumOverflowException {
		if (other == null)
			throw new IllegalArgumentException("Non effective vector!");
		if( !Util.fuzzyLessThanOrEqualTo(this.getXComponent(),Double.MAX_VALUE-other.getXComponent()) 
			|| !Util.fuzzyLessThanOrEqualTo(this.getYComponent(),Double.MAX_VALUE-other.getYComponent()))
			throw new SumOverflowException();
		return new Vector(this.getXComponent()+other.getXComponent(),this.getYComponent()+other.getYComponent());
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
	 * @throws	SumOverflowException
	 * 			The subtraction overflows
	 * 			| !Util.fuzzyLessThanOrEqualTo(Double.NEGATIVE_INFINITY-other.getXComponent(),this.getXComponent()) 
	 *			| || !Util.fuzzyLessThanOrEqualTo(Double.NEGATIVE_INFINITY-other.getYComponent(),this.getYComponent())
	 */
	//TODO: - inf - eender wat is altijd = - inf, ? via max value -> maar klopt niet
	//TODO: overflow via de maximale waarde Double.MAX_VALUE
	public Vector subtract(Vector other) throws IllegalArgumentException, SumOverflowException {
		if (other == null)
			throw new IllegalArgumentException("Non effective vector!");
//		if( !Util.fuzzyLessThanOrEqualTo(Double.NEGATIVE_INFINITY-other.getXComponent(),this.getXComponent())
//			|| !Util.fuzzyLessThanOrEqualTo(Double.NEGATIVE_INFINITY-other.getYComponent(),this.getYComponent()))
		if( !Util.fuzzyLessThanOrEqualTo(this.getXComponent(),Double.MAX_VALUE+other.getXComponent())
				|| !Util.fuzzyLessThanOrEqualTo(this.getXComponent(),Double.MAX_VALUE+other.getXComponent()))
			throw new SumOverflowException();
		return new Vector(this.getXComponent()-other.getXComponent(),this.getYComponent()-other.getYComponent());
	}
	
	/**
	 * Multiply this vector with the given scale factor.
	 * 
	 * @param 	scaleFactor
	 * 			The given factor.
	 * @return	The resulting vector is equal to this vector scaled with the given factor.
	 * 			| result == new Vector(vector.getXComponent()*scaleFactor,vector.getYComponent()*scaleFactor)
	 * @throws	TimesOverflowException
	 * 			The multiplication overflows.
	 * 			| scaleFactor!=0 && !Util.fuzzyLessThanOrEqualTo(this.getXComponent(), Double.Double.MAX_VALUE/scaleFactor) || 
	 *			|	!Util.fuzzyLessThanOrEqualTo(this.getYComponent(), Double.Double.MAX_VALUE/scaleFactor)
	 * @throws	IllegalArgumentException
	 * 			The scalefactor is invalid.
	 * 			| !isValidScaleFactor(scaleFactor)				
	 */
	public Vector scale(double scaleFactor) throws IllegalArgumentException, TimesOverflowException {
		if(!isValidScaleFactor(scaleFactor))
			throw new IllegalArgumentException();
		if(scaleFactor!=0 && !Util.fuzzyLessThanOrEqualTo(this.getXComponent(), Double.MAX_VALUE/scaleFactor) || 
				!Util.fuzzyLessThanOrEqualTo(this.getYComponent(), Double.MAX_VALUE/scaleFactor))
			throw new TimesOverflowException();
		return new Vector(this.getXComponent()*scaleFactor,
							this.getYComponent()*scaleFactor);
	}
	
	/**
	 * Returns the product of the given vector and this vector.
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
	 * 			| Util.fuzzyLessThanOrEqualTo(this.getXComponent(),Math.abs(Double.MAX_VALUE/other.getXComponent())) ||  
	 *				Util.fuzzyLessThanOrEqualTo(this.getYComponent(),Math.abs(Double.MAX_VALUE/other.getYComponent()))
	 */
	@Immutable
	public double dotProduct(Vector other) throws IllegalArgumentException, TimesOverflowException {
		if (other == null)
			throw new IllegalArgumentException("Non effective vector!");
		
		double x=this.getXComponent()*other.getXComponent();
		double y=this.getYComponent()*other.getYComponent();
		
		if(this.getXComponent()==0 || other.getXComponent()==0)
			x = 0;
		if(this.getYComponent()==0 || other.getYComponent()==0)
			y = 0;
		else if ( !Util.fuzzyLessThanOrEqualTo(this.getXComponent(),Math.abs(Double.MAX_VALUE/(other.getXComponent()))) ||
					!Util.fuzzyLessThanOrEqualTo(this.getYComponent(),Math.abs(Double.MAX_VALUE/(other.getYComponent()))))
			throw new TimesOverflowException();
		return x+y;
	}
	
	/**
	 * Checks whether the given component is a valid component.
	 * 
	 * @param 	number
	 * 			The number to check.
	 * @return	True if and only if the given number is a number.
	 * 			| !Double.isNaN(number)
	 */
	public static boolean isValidNumber(double number){
		return !Double.isNaN(number);
	}
	
	/**
	 * Return a boolean reflecting whether this scalefactor is a valid scalefactor
	 * @param 	scaleFactor
	 * 			The scalefactor to be checked.
	 * @return	True if and only if the scalefactor is a valid scalefactor.
	 * 			| result == !Double.isNaN(scaleFactor) && Util.fuzzyEquals(scaleFactor,0)
	 */
	public boolean isValidScaleFactor(double scaleFactor){
		return (isValidNumber(scaleFactor) && Util.fuzzyLessThanOrEqualTo(0.0,scaleFactor));
	}
	
	/**
	 * Check whether this vector is equal to the given object.
	 * 
	 * @return	True if and only if the given object is effective, 
	 * 			if this vector and the given object belong to the same class, 
	 * 			and if this vector and the other object interpreted as a vector 
	 * 			have equal x- and y-components.
	 * 			| result == 
	 * 			|  	( (other != null)
	 * 			|  && (this.getClass() == other.getClass())
	 * 			|  && (Util.fuzzyEquals(this.getXComponent(),((Vector) other).getXComponent())
	 * 			|  && (Util.fuzzyEquals(this.getYComponent(),((Vector) other).getYComponent()) )
	 */
	@Override
	public boolean equals(Object other){
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		Vector otherVector = (Vector) other;
		return (Util.fuzzyEquals(this.getXComponent(),otherVector.getXComponent())
				&& Util.fuzzyEquals(this.getYComponent(),otherVector.getYComponent()));
	}
	
	/**
	 * Return a textual representation of this vector.
	 * 
	 * @return	A string consisting of the textual representation of the position, the velocity, the radius and the direction of this ship.
	 * 			| result.equals(
	 * 			| 			"xComponent: "+xcomponent+" yComponent: "+ycomponent)
	 */
	@Override
	public String toString() {
        return "xComponent: "+xcomponent+" yComponent: "+ycomponent;
    }
	
	//TODO: hashcode?
//	/**
//	 * Return the hash code for this vector.
//	 */
//	@Override
//	public int hashCode() {
//		return getXComponent().hashCode() + getYComponent().hashCode(); // .hashCode() werkt niet op double
//	}
	
}
