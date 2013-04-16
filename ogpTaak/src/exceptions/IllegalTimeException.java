package exceptions;

public class IllegalTimeException extends RuntimeException {
	/**
	 * Initialize this new illegal time exception.
	 * 
	 * @effect	...
	 * 			| super()
	 */
	public IllegalTimeException(Double time) {
		super(time.toString());
	}
}
