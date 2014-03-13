package businessmodel;
import java.util.ArrayList;

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
	 * @param 	password
	 * 			the password fot his garage holder.
	 */
	public GarageHolder(String firstname, String lastname, String username, String password) {
		super(firstname,lastname,username,password);
	}
}
