package businessmodel;

/**
 * A class that represents a garage holder.
 * 
 * @author SWOP team 10 2013-2014 
 *
 */
public class GarageHolder extends User {

	/**
	 * A constructor to create a new garage holder.
	 * 
	 * @param 	firstname
	 * 			the first name of the new garage holder.
	 * @param 	lastname
	 * 			the last name of the new garage holder.
	 * @param 	username
	 * 			the user name for the new garage holder.
	 */
	public GarageHolder(String firstname, String lastname, String username) throws IllegalArgumentException {
		super(firstname,lastname,username);
	}
	
	@Override
	public boolean canPlaceOrder(){
		return true;
	}
}
