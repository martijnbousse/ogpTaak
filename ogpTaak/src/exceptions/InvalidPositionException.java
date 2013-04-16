/**
 * 
 */
package exceptions;

/**
 * A class of exceptions signaling invalid positions.
 * 
 * @version	1.0
 * @author 	Martijn Boussé, Wout Vekemans
 * 
 *
 */
@SuppressWarnings("serial")
public class InvalidPositionException extends RuntimeException {
	/**
	 * Initialize this new invalid position exception.
	 * 
	 * @effect	...
	 * 			| super()
	 */
	public InvalidPositionException() {
		super();
	}
}
