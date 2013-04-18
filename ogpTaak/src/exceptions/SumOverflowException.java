package exceptions;

/**
 * A class of exceptions signaling overflows in sums.
 * 
 * @version	1.0
 * @author 	Martijn Boussé, Wout Vekemans
 * 
 */
@SuppressWarnings("serial")
public class SumOverflowException extends RuntimeException {
	/**
	 * Initialize this new sum overflow exception.
	 * 
	 * @effect	This new sum overflow exception is initialized as a new runtime exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public SumOverflowException() {
		super();
	}
}
