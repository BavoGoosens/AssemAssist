package businessmodel.user;


public class CustomShopManager extends User {

	/**
	 * A constructor to create a new customs shop manager.
	 * 
	 * @param 	firstname
	 * 			the first name of the new customs shop manager.
	 * @param 	lastname
	 * 			the last name of the new customs shop manager.
	 * @param 	username
	 * 			the user name for the new customs shop manager.
	 */
	public CustomShopManager(String firstname, String lastname, String username) throws IllegalArgumentException {
		super(firstname,lastname,username);
	}
	
	@Override
	public boolean canOrderSingleTask(){
		return true;
	}
}
