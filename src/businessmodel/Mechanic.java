package businessmodel;

/**
 * A class that represents a mechanic.
 * 
 * @author SWOP team 10 2013-2014 
 *
 */
public class Mechanic extends User {

	/**
	 * A constructor to create a new mechanic.
	 * 
	 * @param 	firstname
	 * 			the first name of the new mechanic.
	 * @param 	lastname
	 * 			the last name of the new mechanic.
	 * @param 	username
	 * 			the user name of the new mechanic.
	 */
	public Mechanic(String firstname, String lastname, String username) {
		super(firstname,lastname,username);
	}
	
	@Override
	public boolean canPerfomAssemblyTask(){
		return true;
	}
}
