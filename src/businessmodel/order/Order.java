
package businessmodel.order;

import java.util.ArrayList;

import org.joda.time.DateTime;

import businessmodel.Car;
import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.user.User;

/**
 * A class representing a specific car order
 * 
 * @author SWOP Team 10 2014
 *
 */
public abstract class Order {

	/**
	 * A variable that represents an user.
	 */
	private User user;

	/**
	 * The estimated time of delivery.
	 */
	private DateTime estimatedatetime;

	private DateTime timestamp;
	
	private DateTime placedonworkpost;

	private DateTime standardtime;

	private DateTime completiondatetime;
	
	private ArrayList<CarOption> caroptions;
	
	/**
	 * A constructor for the class Order.
	 * 
	 * @param   user
	 *          the user that makes the new Order.
	 * @param   caroptions
	 *          the car options of the new Order.
	 */
	public Order(User user, ArrayList<CarOption> options) throws IllegalArgumentException, NoClearanceException{
		setUser(user);
		setCapoptions(options);
	}

	/**
	 * A method to set the user of this order to the given order.
	 * 
	 * @param   user
	 *          the new user of this order.
	 * @throws  IllegalArgumentException
	 * 			!canHaveAsUser(user)
	 */
	private void setUser(User user) throws IllegalArgumentException, NoClearanceException {
		if (user == null) throw new IllegalArgumentException("Bad user!");
		if (!user.canPlaceOrder()) throw new NoClearanceException(user);
		this.user = user;
	}

	/**
	 * A variable to get the delivery date.
	 *  
	 * @return  DateTime
	 * 			this.deliverydate
	 */
	public DateTime getEstimateDate() {
		return this.estimatedatetime;
	}

	/**
	 * A method to set the delivery date to the given delivery date.
	 * 
	 * @param   deliverydate
	 *          the delivery date of this order.
	 * @throws 	IllegalArgumentException
	 * 			delivery == null
	 */
	public void setEstimateDate(DateTime deliverydate) throws IllegalArgumentException {
		if(deliverydate == null)
			throw new IllegalArgumentException();
		this.estimatedatetime = deliverydate;
	}

	/**
	 * A method to get the user that placed this order.
	 * 
	 * @return  User
	 * 			this.user
	 */
	public User getUser() {
		return this.user;
	}
	//+ ", delivery date= " + this.estimatedatetime.toString("EEE, dd MMM yyyy HH:mm:ss", Locale.ROOT);
	@Override
	public String toString() {
		return "user: " + this.user.toString() ;
	}

	/**
	 * Method to check if car options from two orders are the same or not
	 * 
	 * @param order
	 * @return true if car options are the same else return false
	 * @throws IllegalArgumentException
	 */
	public boolean equalsCarOptions(Object order) throws IllegalArgumentException{

		if (order == null) throw new IllegalArgumentException("bad order");

		ArrayList<CarOption> orders = this.getCar().getOptionsClone();
		ArrayList<CarOption> orders2 = ((StandardCarOrder) order).getCar().getOptionsClone();
		ArrayList<CarOption> temp = new ArrayList<CarOption>();
		
		if (orders.size() < orders2.size()){
			temp = orders;
			orders= orders2;
			orders2 = temp;
		}
			
		for(CarOption carOption: orders){
			boolean sameCarOptions = false;
			for(CarOption carOption2: orders2){
				if (carOption.toString().equals(carOption2.toString())){
					sameCarOptions = true;
				}
			}
			if(!sameCarOptions)
				return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param date
	 */
	public void updateCompletionTime(DateTime date){
		this.completiondatetime = date;
	}

	/**
	 * 
	 * @return null
	 */
	public Car getCar(){
		return null;
	}

	public void updateEstimatedDate(int delay) {
		this.getEstimateDate().plusMinutes(delay);		
	}
	
	public void setCompletionDate(DateTime date){
		this.completiondatetime = date;
	}
	
	public DateTime getCompletionDate(){
		return this.completiondatetime;
	}

	public DateTime getTimestamp(){
		return this.timestamp;
	}
	
	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setPlacedOnWorkpost(DateTime date){
		this.placedonworkpost = date;
	}
	
	public ArrayList<CarOption> getCarOptions() {
		return caroptions;
	}

	private void setCapoptions(ArrayList<CarOption> capoptions) {
		this.caroptions = capoptions;
	}
}
