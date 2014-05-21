package businessmodel.user;

/**
 * A class representing a mechanic.
 * 
 * @author SWOP team 10
 *
 */
public class Mechanic extends User {

	/**
	 * Creates a new mechanic with a given first name, last name and user name.
	 * 
	 * @param 	firstname
	 * 			The first name of the new mechanic.
	 * @param 	lastname
	 * 			The last name of the new mechanic.
	 * @param 	username
	 * 			The user name of the new mechanic.
	 * 
	 * @throws	IllegalArgumentException
	 */
	public Mechanic(String firstname, String lastname, String username) throws IllegalArgumentException {
		super(firstname,lastname,username);
	}
	
	@Override
	public boolean canPerfomAssemblyTask(){
		return true;
	}

    @Override
    public boolean canViewAssemblyLines() {
        return true;
    }
}
