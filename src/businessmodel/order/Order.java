
package businessmodel.order;

import java.util.ArrayList;
import java.util.Locale;

import org.joda.time.DateTime;

import businessmodel.Car;
import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
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
	private DateTime estimated_endtime;

	private DateTime order_placed;

	private DateTime order_placed_on_workpost;

	private DateTime standardtime_on_assemblyline;

	private DateTime completion_time;

	private DateTime user_end_date;

	private Car car;

	private boolean completed;

	/**
	 * A constructor for the class Order.
	 * 
	 * @param   user
	 *          the user that makes the new Order.
	 * @param   caroptions
	 *          the car options of the new Order.
	 * @throws 	UnsatisfiedRestrictionException
	 */
	public Order(User user, ArrayList<CarOption> options, DateTime user_end_date) throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		if (!user.canPlaceOrder()) throw new NoClearanceException();
		setUser(user);
		setCar(new Car(options));
		this.setUser_end_date(user_end_date);
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
		return this.estimated_endtime;
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
		this.estimated_endtime = deliverydate;
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
	//
	@Override
	public String toString() {
		return "user: " + this.user.toString() + ", delivery date= " + this.estimated_endtime.toString("EEE, dd MMM yyyy HH:mm:ss", Locale.ROOT); 
	}

		/**
	 * 
	 * @param date
	 */
	public void updateCompletionTime(DateTime date){
		this.completion_time = date;
	}

	public void updateEstimatedDate(int delay) {
		this.getEstimateDate().plusMinutes(delay);		
	}

	public void setCompletionDate(DateTime date){
		this.completion_time = date;
	}

	public DateTime getCompletionDate(){
		return this.completion_time;
	}

	public DateTime getTimestamp(){
		return this.order_placed;
	}

	public void setTimestamp(DateTime timestamp) {
		this.order_placed = timestamp;
	}

	public void setPlacedOnWorkpost(DateTime date){
		this.setOrder_placed_on_workpost(date);
	}

	public Car getCar() {
		return this.car;
	}

	private void setCar(Car car) {
		if (car == null) throw new IllegalArgumentException("Bad car!");
		this.car = car;
	}

	public DateTime getUser_end_date() {
		return user_end_date;
	}

	private void setUser_end_date(DateTime user_end_date) {
		this.user_end_date = user_end_date;
	}

	public DateTime getOrder_placed_on_workpost() {
		return order_placed_on_workpost;
	}

	public void setOrder_placed_on_workpost(DateTime order_placed_on_workpost) {
		this.order_placed_on_workpost = order_placed_on_workpost;
	}

	public DateTime getStandardtime_on_assemblyline() {
		return standardtime_on_assemblyline;
	}

	public void setStandardtime_on_assemblyline(
			DateTime standardtime_on_assemblyline) {
		this.standardtime_on_assemblyline = standardtime_on_assemblyline;
	}

	public boolean isCompleted() {
		return this.completed;
	}

	public void setCompleted() {
		this.completed = true;
	}
}
