package BusinessModel;
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
	
	/**
	 * A method to get the orders of an garage holder.
	 * 
	 * @return   this.orders
	 */
	public ArrayList<Order> getOrders() {
		return this.orders;
	}
	
	/**
	 * A method to add an order to the list of orders for this garage holder.
	 * 
	 * @param   order
	 * 			the order that will be added to the list of orders.
	 */
	public void addOrder(Order order){
		this.getOrders().add(order);
	}
	
	/**
	 * 
	 * @param order
	 */
	public void removeOrder(Order order){
		this.getOrders().remove(order);
	}
}
