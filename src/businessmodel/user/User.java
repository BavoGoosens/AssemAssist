package businessmodel.user;
/**
 * A class representing a user of the system.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public abstract class User {
	
	/**
	 * The first name of the user.
	 */
	private final String firstname;
	
	/**
	 * The last name of the user.
	 */
	private final String lastname;
	
	/**
	 * The user name of the user.
	 */
	private String username;


	/**
	 * Creates a user with a given first name, last name and user name.
	 * 
	 * @param 	firstname
	 *  	  	The first name of the user.
	 * @param 	lastname
	 * 		  	The last name of the user.
	 * @param 	username
	 * 		  	The user name of the user.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			| If firstname, lastname or username is equal to 'null'
	 * 			| firstname == null || lastname == null || username == null
	 */
	public User (String firstname, String lastname, String username) throws IllegalArgumentException {
		if (firstname == null) throw new IllegalArgumentException("Bad first name!");
		if (lastname == null) throw new IllegalArgumentException("Bad last name!");
		if (username == null) throw new IllegalArgumentException("Bad user name!");
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
	}
	
	/**
	 * Returns the user name of the user.
	 * 
	 * @return The user name of the user.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Returns the first name of the user.
	 * 
	 * @return The first name of the user.
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Returns the last name of the user.
	 * 
	 * @return The last name of the user.
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * Returns whether the user can place an order.
	 * 
	 * @return True if the user can place an order.
	 */
	public boolean canPlaceOrder(){
		return false;
	}
	
	/**
	 * Returns whether the user can advance the assembly line.
	 * 
	 * @return True if the user can advance the assembly line.
	 */
	public boolean canAdvanceAssemblyLine(){
		return false;
	}
	
	/**
	 * Returns whether the user can perform an assembly task.
	 * 
	 * @return True if the user can perform an assembly task.
	 */
	public boolean canPerfomAssemblyTask(){
		return false;
	}
	
	/**
	 * Returns whether the user can order a single task.
	 * 
	 * @return	True if the user can order a single task.
	 */
	public boolean canOrderSingleTask(){
		return false;
	}

    public boolean canViewStatistics() {return false;}

    public boolean canViewAssemblyLines(){return false;}

    public boolean canChangeAlgorithm() {
        return false;
    }

    /**
	 * Returns a string representation of the user.
	 */
	@Override
	public String toString() {
		return "firstname= " + firstname + ", lastname= " + lastname;
	}

}
