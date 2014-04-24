package businessmodel.exceptions;

public class IllegalSchedulingAlgorithmException extends RuntimeException {
	
	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a new illegal scheduling algorithm exception with a given message.
	 * 
	 * @param 	message
	 * 			The message of the exception.
	 */
	public IllegalSchedulingAlgorithmException(String message) {
		super(message);
	}
	
	/**
	 * Creates a new illegal scheduling algorithm exception.
	 */
	public IllegalSchedulingAlgorithmException(){
		super("There went something wrong with the scheduling algorithm");
	}
	
}
