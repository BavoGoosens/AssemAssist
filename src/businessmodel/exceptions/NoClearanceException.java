package businessmodel.exceptions;
import businessmodel.user.User;

public class NoClearanceException extends Exception {
	
	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 2038156514090663821L;
	private User user;
	
	public NoClearanceException() {}

	public NoClearanceException(String message) {
		super(message);
	}
	
	public NoClearanceException(User user) {
		this.user = user;
	}
	
	public NoClearanceException(User user, String message) {
		super(message);
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
	
	
	

}
