package businessmodel.exceptions;
import businessmodel.user.User;

public class NoClearanceException extends Exception {
	
	private static final long serialVersionUID = 2038156514090663821L;
	private User user;
	
	public NoClearanceException() {}

	public NoClearanceException(String message) {
		super(message);
	}
	
	public NoClearanceException(User user) {
		this.setUser(user);
	}
	
	public NoClearanceException(User user, String message) {
		super(message);
		this.setUser(user);
	}
	
	public User getUser() {
		return this.user;
	}

	private void setUser(User user) {
		this.user = user;
	}
	
	
	
	

}
