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
	 * A list that holds all the orders of this garage holder.
	 */
	public ArrayList<Order> orders; 
	
	
	public GarageHolder(String firstname, String lastname, String username, String password) {
		super(firstname,lastname,username,password);
	}
}
