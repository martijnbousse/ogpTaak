package exceptions;

/**
 * A class of exceptions signaling illegal times.
 * 
 * @version	1.0
 * @author 	Martijn Boussé, Wout Vekemans
 * 
 *
 */
@SuppressWarnings("serial")
public class IllegalTimeException extends RuntimeException {
	/**
	 * Initialize this new illegal time exception.
	 * 
	 * @effect	This new illegal time exception is initialized as a new runtime exception involving 
	 * 			the amount of time as its diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalTimeException(Double time) {
		super(time.toString());
	}
}
