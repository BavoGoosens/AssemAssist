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
	 * A variable which represents the  username of the User.
	 */
	private String username;
	
	/**
	 * A variable which represents the password of the User.
	 */
	private String password;

	/**
	 * The constructor for a specific user of the system.
	 * 
	 * @param firstname
	 *  	  the first name of the User.
	 * @param lastname
	 * 		  the last name of the User.
	 * @param username
	 * 		  the username of the User.
	 * @param password
	 * 		  the password of the User.
	 * @param usertype
	 * 		  the specific type of User.
	 */
	public User (String firstname, String lastname, String username, String password){
		this.firstname = firstname;
		this.lastname = lastname;
		this.setUsername(username);
		this.setPassword(password);
	}
	
	/**
	 * This method returns the name of the User.
	 * 
	 * @return String
	 * 		   A string representing the name of this User.
	 */
	private String getUsername() {
		return username;
	}

	/**
	 * This method sets the name of the User.
	 * 
	 * @param username
	 * 		  the username of this User.
	 */
	private void setUsername(String username) {
		this.username = username;
	}

	/**
	 * This method returns the password of this User.
	 * 
	 * @return String
	 * 		   a String representing the password of the User.
	 */
	private String getPassword() {
		return password;
	}

	/**
	 * This method sets the password for this User.
	 * 
	 * @param password
	 * 		  A string representing the password.
	 */
	private void setPassword(String password) {
		this.password = password;
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

	/**
	 * This method lets the User update his/her user name and password.
	 * 
	 * @param loginusername
	 * 		  the current user name.
	 * @param loginpassword
	 * 		  the current password.
	 * @param newusername
	 * 		  the new user name.
	 * @param newpassword
	 * 		  the new password.
	 */
	public void updateUser(String loginusername, String loginpassword, String newusername, String newpassword){
		if (this.getUsername().equals(loginusername) && this.getPassword().equals(loginpassword)){
			this.setUsername(newusername);
			this.setPassword(newpassword);
		} 
	}
	
	/**
	 * This method is used to authenticate a user.
	 * 
	 * @param uname the user name to be authenticated.
	 * @param pwd the password to be authenticated.
	 * @return boolean 
	 * 		   True if the supplied user name and password are correct otherwise False.
	 */
	public boolean authenticate(String uname, String pwd){
		return (this.getUsername().equals(uname) && this.getPassword().equals(pwd));
	}
	


	@Override
	public String toString() {
		return "firstname= " + firstname + ", lastname= " + lastname
				+ ", username= " + username;
	}
}
