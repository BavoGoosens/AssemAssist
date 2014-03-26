package businessmodel;

/**
 * A class that represents a manager.
 * 
 * @author SWOP team 10 2014 
 *
 */
public class Manager extends User {

	/**
	 * A constructor to create a new manager.
	 * 
	 * @param 	firstname
	 * 			the first name of the new manager.
	 * @param 	lastname
	 * 			the last name of the new manager.
	 * @param 	username
	 * 			the user name of the new manager.
	 */
	public Manager(String firstname, String lastname, String username) throws IllegalArgumentException {
		super(firstname,lastname,username);
	}
	
	@Override
	public boolean canAdvanceAssemblyLine(){
		return true;
	}
}
