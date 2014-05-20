package businessmodel.user;

/**
 * A class representing a custom shop manager.
 * 
 * @author SWOP team 10
 *
 */
public class CustomShopManager extends User {

	/**
	 * A constructor to create a new customs shop manager with a given first name, last name and user name.
	 * 
	 * @param 	firstname
	 * 			The first name of the new customs shop manager.
	 * @param 	lastname
	 * 			The last name of the new customs shop manager.
	 * @param 	username
	 * 			The user name for the new customs shop manager.
	 * @throws	IllegalArgumentException
	 */
	public CustomShopManager(String firstname, String lastname, String username) throws IllegalArgumentException {
		super(firstname,lastname,username);
	}
	
	@Override
	public boolean canOrderSingleTask(){
		return true;
	}
	
	@Override
	public boolean canPlaceOrder(){
		return true;
	}
}
