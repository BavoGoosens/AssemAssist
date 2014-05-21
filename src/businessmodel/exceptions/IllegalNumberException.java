package businessmodel.exceptions;

/**
 * IllegalNumberException
 * 
 * @author SWOP team 10
 *
 */
public class IllegalNumberException extends RuntimeException {

	private static final long serialVersionUID = 4944557335300879869L;
	private int number;
	public IllegalNumberException() {}
	
	/**
	 * Creates a new illegal number exception with a given message.
	 * 
	 * @param 	message
	 * 			The message of the exception.
	 */
	public IllegalNumberException(String message) {
		super(message);
	}
	
	/**
	 * Creates a new illegal number exception with a given number.
	 * 
	 * @param 	number
	 * 			The number of the exception.
	 */
	public IllegalNumberException(int number) {
		this.setNumber(number);
	}
	
	/**
	 * Creates a new illegal number exception with a given number and a given message.
	 * 
	 * @param	number
	 * 			The number of the exception.
	 * @param 	message
	 * 			The message of the exception.
	 */
	public IllegalNumberException(int number, String message) {
		super(message);
		this.setNumber(number);
	}
	
	/**
	 * Returns the number of the exception.
	 * 
	 * @return	The number that caused the exception.
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Set the number of the exception.
	 * @param number
	 */
	private void setNumber(int number) {
		this.number = number;
	}

}
