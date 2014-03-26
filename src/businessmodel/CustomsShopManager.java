package businessmodel;

public class CustomsShopManager extends User {

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
	public CustomsShopManager(String firstname, String lastname, String username) {
		super(firstname,lastname,username);
	}
	
	@Override
	public boolean canOrderSingleTask(){
		return true;
	}
}
