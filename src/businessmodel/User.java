package businessmodel;
/**
 * A class representing a User of the system.
 * 
 * @author Team 10
 *
 */
public abstract class User {
	
	/**
	 * A variable which represents the first name of the User.
	 */
	private final String firstname;
	
	/**
	 * A variable which represents the last name of the User.
	 */
	private final String lastname;
	
	/**
	 * A variable which represents the  user name of the User.
	 */
	private String username;
	

	/**
	 * The constructor for a specific user of the system.
	 * 
	 * @param firstname
	 *  	  the first name of the User.
	 * @param lastname
	 * 		  the last name of the User.
	 * @param username
	 * 		  the user name of the User.
	 * @param usertype
	 * 		  the specific type of User.
	 */
	public User (String firstname, String lastname, String username) throws IllegalArgumentException {
		if (firstname == null) throw new IllegalArgumentException("Bad first name!");
		if (lastname == null) throw new IllegalArgumentException("Bad last name!");
		this.firstname = firstname;
		this.lastname = lastname;
		this.setUsername(username);
	}
	
	/**
	 * This method returns the user name of the User.
	 * 
	 * @return String
	 * 		   A string representing the user name of this User.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * This method sets the name of the User.
	 * 
	 * @param username
	 * 		  the username of this User.
	 */
	private void setUsername(String username) throws IllegalArgumentException {
		if (username == null) throw new IllegalArgumentException("Bad username!");
		this.username = username;
	}

	/**
	 * This method returns the first name of the User.
	 * 
	 * @return String 
	 * 		   the first name of this User.
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * This method returns the last name of the User.
	 * 
	 * @return String 
	 * 		   the last name of this User.
	 */
	public String getLastname() {
		return lastname;
	}
	
	public boolean canPlaceOrder(){
		return false;
	}

	public boolean canAdvanceAssemblyLine(){
		return false;
	}

	public boolean canPerfomAssemblyTask(){
		return false;
	}
	
	public boolean canOrderSingleTask(){
		return false;
	}

	@Override
	public String toString() {
		return "firstname= " + firstname + ", lastname= " + lastname;
	}
}
