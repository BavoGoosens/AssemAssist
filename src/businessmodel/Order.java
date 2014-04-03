package businessmodel;

import java.util.ArrayList;
import java.util.Locale;

import org.joda.time.DateTime;

import businessmodel.exceptions.NoClearanceException;

/**
 * A class representing a specific car order
 * 
 * @author SWOP Team 10 2014
 *
 */
public class Order {

	/**
	 * A variable that contains the specific car being ordered.
	 */
	private Car car;

	/**
	 * A variable that represents an user.
	 */
	private User user;

	/**
	 * A variable that holds the delivery date for an order.
	 */
	private DateTime deliverydate;

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
	public Order(User user, ArrayList<CarOption> options) throws IllegalArgumentException, NoClearanceException{
		setUser(user);
		this.car = new Car(options);
		this.deliverydate = new DateTime();	
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
	 * This method returns the new car for this order.
	 * 
	 * @return 	Car
	 * 			this.car
	 */
	public Car getCar() {
		return car;
	}

//	/**
//	 * a method to see if the order is completed or not.
//	 * 
//	 * @return	boolean
//	 * 			True if and only if all the components of the car of this order are completed.
//	 */
//	public boolean isCompleted(){
//		Boolean completed = true;
//		for(Component component: this.car.getComponents())
//			if(component.isCompleted() == false)
//				completed = false;
//		return completed;
//	}

	/**
	 * A variable to get the delivery date.
	 *  
	 * @return  DateTime
	 * 			this.deliverydate
	 */
	public DateTime getDate() {
		return this.deliverydate;
	}

	/**
	 * A method to set the delivery date to the given delivery date.
	 * 
	 * @param   deliverydate
	 *          the delivery date of this order.
	 * @throws 	IllegalArgumentException
	 * 			delivery == null
	 */
	public void setDate(DateTime deliverydate) throws IllegalArgumentException {
		if(deliverydate == null)
			throw new IllegalArgumentException();
		this.deliverydate = deliverydate;
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
	
	@Override
	public String toString() {
		return "user: " + this.user.toString() + ", delivery date= " + this.deliverydate.toString("EEE, dd MMM yyyy HH:mm:ss", Locale.ROOT);
	}

}
