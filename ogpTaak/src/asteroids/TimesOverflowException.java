/**
 * 
 */
package asteroids;

/**
 * A class of exceptions signaling overflows in multiplications.
 * 
 * @version	1.0
 * @author 	Martijn Boussé, Wout Vekemans
 * 
 *
 */
@SuppressWarnings("serial")
public class TimesOverflowException extends RuntimeException {
	/**
	 * Initialize this new times overflow exception.
	 * 
	 * @effect	This new times overflow exception is initialized as a new runtime exception involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public TimesOverflowException() {
		super();
	}
}
