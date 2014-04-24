package businessmodel.exceptions;

public class IllegalNumberException extends RuntimeException {
	
	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 4944557335300879869L;
	
	/**
	 * The number that caused the exception.
	 */
	private int number;
	
	/**
	 * Creates a new illegal number exception.
	 */
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

	private void setNumber(int number) {
		this.number = number;
	}

}
