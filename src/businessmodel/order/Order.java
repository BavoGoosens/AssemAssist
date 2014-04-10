
package businessmodel.order;

import java.util.ArrayList;

import org.joda.time.DateTime;

import businessmodel.Car;
import businessmodel.CarOption;
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

	private DateTime standardtime;

	private DateTime completiondatetime;

	/**
	 * A constructor for the class Order.
	 * 
	 * @param   user
	 *          the user that makes the new order.
	 * @param   components
	 *          the components of the new car.
	 * @param   deliverydate
	 *          the delivery date of the new order.
	 */
	public Order(User user) throws IllegalArgumentException, NoClearanceException{
		setUser(user);
		this.timestamp = new DateTime();
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
	 * A method to update the estimated completion time of an order.
	 * @param	order
	 * 			the order for which the completion date needs to be updated.
	 */
	//TODO updaten 
	public void updateEstimatedCompletionTimeOfOrder(Order previousorder) {
		DateTime date;
		if (previousorder == null){
			DateTime datetemp = new DateTime();
			date = new DateTime(datetemp.getYear(), datetemp.getMonthOfYear(), datetemp.getDayOfMonth(), 8, 0);
			this.setEstimateDate(date);
			return;
		}
		else 
			date = previousorder.getEstimateDate();

		if (date.getHourOfDay() <= 21)
			this.setEstimateDate(date.plusMinutes(60));
		else{
			date.plusHours(8);
			this.setEstimateDate(date.plusMinutes(60));
		}
	}

	/**
	 * Method to check if caroptions from two orders are the same or not
	 * 
	 * @param order
	 * @return true if caroptions are the same else return false
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
	
}
