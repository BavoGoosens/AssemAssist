package exceptions;
import businessmodel.*;

public class NoClearanceException extends Exception {
	
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
