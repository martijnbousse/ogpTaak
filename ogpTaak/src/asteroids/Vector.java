package asteroids;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of vectors involving an x- and y-component.
 * 
 * @Invar	
 * 
 * @author	Martijn Boussé, Wout Vekemans
 * @version	1.0
 *
 */
@Value
public class Vector {
	
	//TODO: documentatie nakijken
	//TODO: invarianten
	//TODO: isNaN
	//TODO: @raw
	//TODO: VectorTest klasse schrijven
	
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
	 * 			| !isValidComponent(xcomponent) || !isValidComponent(ycomponent)
	 */
	//TODO: Moet in de documentatie ook fuzzyEquals gebruikt worden?
	@Raw
	public Vector(double xcomponent, double ycomponent) throws IllegalArgumentException{
		if(!isValidComponent(xcomponent) || !isValidComponent(ycomponent))
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
	 * Checks whether the given component is a valid component.
	 * 
	 * @param 	component
	 * 			The component to check.
	 * @return	True if and only if the given component is a number.
	 * 			| !Double.isNaN(component)
	 */
	public static boolean isValidComponent(double component){
		return !Double.isNaN(component);
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
		return Util.fuzzyEquals(this.getXComponent(),otherVector.getXComponent())
				&& Util.fuzzyEquals(this.getYComponent(),otherVector.getYComponent());
	}
	
	/**
	 * @return	The representation of this vector by means of a string.
	 * 			| "xComponent: "+xcomponent+" yComponent: "+ycomponent
	 */
	@Override
	public String toString() {
        return "xComponent: "+xcomponent+" yComponent: "+ycomponent;
    }
	
	//TODO: hashcode
		
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
	public double dotProduct(Vector other) throws IllegalArgumentException, TimesOverflowException {
		if (other == null)
			throw new IllegalArgumentException("Non effective vector!");
		
		double x=this.getXComponent()*other.getXComponent();
		double y=this.getYComponent()*other.getYComponent();
		
		if(this.getXComponent()==0 || other.getXComponent()==0)
			x = 0;
		if(this.getYComponent()==0 || other.getYComponent()==0)
			y = 0;
		else if ( !Util.fuzzyLessThanOrEqualTo(this.getXComponent(),Math.abs(Double.POSITIVE_INFINITY/(other.getXComponent()))) ||
					!Util.fuzzyLessThanOrEqualTo(this.getYComponent(),Math.abs(Double.POSITIVE_INFINITY/(other.getYComponent()))))
			throw new TimesOverflowException();
		return x+y;
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
	 * 			| scaleFactor!=0 && !Util.fuzzyLessThanOrEqualTo(this.getXComponent(), Double.POSITIVE_INFINITY/scaleFactor) || 
	 *			|	!Util.fuzzyLessThanOrEqualTo(this.getYComponent(), Double.POSITIVE_INFINITY/scaleFactor)
	 * @throws	IllegalArgumentException
	 * 			The scalefactor is invalid.
	 * 			| !isValidScaleFactor(scaleFactor)				
	 */
	//TODO iets met negatieve getallen en min oneindig
	public Vector scale(double scaleFactor){
		if(!isValidScaleFactor(scaleFactor))
			throw new IllegalArgumentException();
		if(scaleFactor!=0 && !Util.fuzzyLessThanOrEqualTo(this.getXComponent(), Double.POSITIVE_INFINITY/scaleFactor) || 
				!Util.fuzzyLessThanOrEqualTo(this.getYComponent(), Double.POSITIVE_INFINITY/scaleFactor))
			throw new TimesOverflowException();
		return new Vector(this.getXComponent()*scaleFactor,
							this.getYComponent()*scaleFactor);
	}
	
	/**
	 * Return a boolean reflecting whether this scalefactor is a valid scalefactor
	 * @param 	scaleFactor
	 * 			The scalefactor to be checked.
	 * @return	True if and only if the scalefactor is a valid scalefactor.
	 * 			| result == !Double.isNaN(scaleFactor) && Util.fuzzyEquals(scaleFactor,0)
	 */
	//TODO: we hebben reeds een methode isValidComponent die net hetzelfde doet. cf een zelfde methode in ship?
	public boolean isValidScaleFactor(double scaleFactor){
		return !Double.isNaN(scaleFactor);
	}
	
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
	 * 			| this.getXComponent() > Double.POSITIVE_INFINITY-other.getXComponent() || this.getYComponent() > Double.POSITIVE_INFINITY-other.getYComponent()
	 */
	public Vector add(Vector other){
		if (other == null)
			throw new IllegalArgumentException("Non effective vector!");
		if(! Util.fuzzyLessThanOrEqualTo(this.getXComponent(),Double.POSITIVE_INFINITY-other.getXComponent()) || 
				! Util.fuzzyLessThanOrEqualTo(this.getYComponent(),Double.POSITIVE_INFINITY-other.getYComponent()))
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
	 * 			| !Util.fuzzyLessThanOrEqualTo(Double.NEGATIVE_INFINITY-other.getXComponent(),this.getXComponent()) || 
	 *			|	!Util.fuzzyLessThanOrEqualTo(Double.NEGATIVE_INFINITY-other.getYComponent(),this.getYComponent())
	 */
	public Vector subtract(Vector other) throws IllegalArgumentException {
		if (other == null)
			throw new IllegalArgumentException("Non effective vector!");
		if(!Util.fuzzyLessThanOrEqualTo(Double.NEGATIVE_INFINITY-other.getXComponent(),this.getXComponent()) || 
			!Util.fuzzyLessThanOrEqualTo(Double.NEGATIVE_INFINITY-other.getYComponent(),this.getYComponent()))
			throw new SumOverflowException();
		return new Vector(this.getXComponent()-other.getXComponent(),this.getYComponent()-other.getYComponent());
	}
	
	
	
	
	
//TODO: wat mag hiermee?
	//TODO weg denk ik
	public static double getAngle(Vector vector1,Vector vector2) {
		double xDiff = vector1.getXComponent()-vector2.getXComponent();
		double yDiff = vector1.getYComponent()-vector2.getYComponent();
		if(xDiff > 0){
			if(yDiff > 0){
				return Math.atan(yDiff/xDiff)+Math.PI;
			}
			if(yDiff < 0){
				return Math.atan(-yDiff/xDiff)+Math.PI;
			}
			return Math.PI;
		}
		else if(xDiff < 0){
			if(yDiff > 0){
				return Math.atan(-yDiff/xDiff);
			}
			if(yDiff < 0){
				return Math.atan(yDiff/xDiff);
			}
			return 0;
		}
		else if(xDiff == 0){
			if(yDiff > 0)
				return 3*Math.PI/2;
			else{
				return Math.PI/2;
			}
		}
		return 0;
	}	
}
