package exceptions;

/**
 * A class of exceptions signaling invalid positions.
 * 
 * @version	1.0
 * @author 	Martijn Bouss�, Wout Vekemans
 * 
 *
 */
@SuppressWarnings("serial")
public class InvalidPositionException extends RuntimeException {
	/**
	 * Initialize this new invalid position exception.
	 * 
	 * @effect	This new invalid position exception is initialized as a new runtime exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public InvalidPositionException() {
		super();
	}
}
