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
	//TODO: volgens mij is dit niet raw ; aangezien Vector(0,0) valid input is
	public Vector(double xcomponent, double ycomponent){
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
	 * Return the y-component of this vector.
	 */
	@Basic @Immutable @Raw
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
	 * 			|  && (Util.fuzzyEquals(this.getXComponent(),((Vector) other).getXComponent())
	 * 			|  && (Util.fuzzyEquals(this.getYComponent(),((Vector) other).getYComponent())
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
	
	//TODO: hashcode
	//TODO: toString
		
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
	//TODO: nieuwe methode scale(vector, scaling factor)
	//TODO: dotProduct static maken, maar veel problemen
	public double dotProduct(Vector other) throws IllegalArgumentException, TimesOverflowException {
		if (other == null)
			throw new IllegalArgumentException("Non effective vector!");
		
		double x=this.getXComponent()*other.getXComponent();
		double y=this.getYComponent()*other.getYComponent();
		
		if(this.getXComponent()==0 || other.getXComponent()==0)
			x = 0;
		if(this.getYComponent()==0 || other.getYComponent()==0)
			y = 0;
		else if (! (Util.fuzzyLessThanOrEqualTo(this.getXComponent(),Double.POSITIVE_INFINITY/(other.getXComponent())) ||
					Util.fuzzyLessThanOrEqualTo(this.getYComponent(),Double.POSITIVE_INFINITY/(other.getYComponent()))))
			throw new TimesOverflowException();
		return x+y;
		
		
//		if (other == null){
//			throw new IllegalArgumentException("Non effective vector!");
//		}
//		double xResult = this.getXComponent()*other.getXComponent();
//		double yResult = this.getYComponent()*other.getYComponent();
//		try{
//			if( !(Util.fuzzyLessThanOrEqualTo(this.getXComponent(),Double.POSITIVE_INFINITY/other.getXComponent())))
//				throw new TimesOverflowException();
//		} catch(ArithmeticException exc){
//			xResult = 0;
//		}
//		try{
//			if( !(Util.fuzzyLessThanOrEqualTo(this.getYComponent(),Double.POSITIVE_INFINITY/other.getYComponent())))
//				throw new TimesOverflowException();
//		} catch(ArithmeticException exc){
//			yResult = 0;
//		}
//		return xResult + yResult;
	}
	
	/**
	 * Multiply this vector with a given factor
	 * @param 	scaleFactor
	 * 			The given factor
	 * @pre 	The scalefactor is a valid scalefactor
	 * 			| isValidScaleFactor(scaleFactor)
	 * @return	The resulting vector is equal to this vector scaled with the given factor
	 * 			| result == new Vector(vector.getXComponent()*scaleFactor,vector.getYComponent()*scaleFactor)
	 * @throws	TimesOverflowException
	 * 			The multiplication overflows.
	 * 			| Util.fuzzyLessThanOrEqualTo(this.getXComponent(), Double.POSITIVE_INFINITY/scaleFactor) || 
	 *			|	Util.fuzzyLessThanOrEqualTo(this.getYComponent(), Double.POSITIVE_INFINITY/scaleFactor)					
	 */
	public Vector scale(double scaleFactor){
		assert isValidScaleFactor(scaleFactor);
		if(!Util.fuzzyLessThanOrEqualTo(this.getXComponent(), Double.POSITIVE_INFINITY/scaleFactor) || 
				!Util.fuzzyLessThanOrEqualTo(this.getYComponent(), Double.POSITIVE_INFINITY/scaleFactor))
			throw new TimesOverflowException();
		return new Vector(this.getXComponent()*scaleFactor,
							this.getYComponent()*scaleFactor);
	}
	
	/**
	 * Return a boolean reflecting whether this scalefactor is a valid scalefactor
	 * @param 	scaleFactor
	 * 			The scalefactor to be checked.
	 * @return	True if and only if the scalefactor is a valid scalefactor
	 * 			| result == !Double.isNaN(scaleFactor) && Util.fuzzyEquals(scaleFactor,0)
	 */
	public boolean isValidScaleFactor(double scaleFactor){
		return !Double.isNaN(scaleFactor) && Util.fuzzyEquals(scaleFactor,0);
	}
	
	
	//TODO: sum + overflow
	
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
	public Vector subtract(Vector other) throws IllegalArgumentException {
		if (other == null)
			throw new IllegalArgumentException("Non effective vector!");
		return new Vector(this.getXComponent()-other.getXComponent(),this.getYComponent()-other.getYComponent());
	}
	
	
	
	
	

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
