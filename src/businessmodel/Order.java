package businessmodel;

import java.util.ArrayList;
import java.util.Date;

import component.Component;

/**
 * A class representing a specific car order
 * 
 * @author SWOP Team 10
 *
 */
public class Order {

	/**
	 * A variable that contains the specific car being ordered
	 */
	private Car car;

	/**
	 * A variable that represents an user.
	 */
	private User user;

	/**
	 * A variable that holds the delivery date for an order.
	 */
	private Date deliverydate;

	/**
	 * A constructor for the class Order.
	 * A new car is created with the given components.
	 * 
	 * @param   user
	 *          the user that makes the new order.
	 * @param   components
	 *          the components of the new car.
	 * @param   deliverydate
	 *          the delivery date of the new order.
	 */
	public Order(User user, ArrayList<Component> components, Date deliverydate){
		setUser(user);
		this.car = new Car(components);
		setDate(deliverydate);
	}

	/**
	 * A method to set the user of this order to the given user.
	 * 
	 * @param   user
	 *          the new user of this order.
	 */
	private void setUser(User user) {
		this.user = user;
	}

	/**
	 * This method returns the new car for this order.
	 * 
	 * @return this.car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * a method to see if the order is completed or not.
	 * 
	 * @return   True if and only if all the components of the car of this order are completed.
	 */
	public boolean isCompleted(){
		Boolean completed = true;
		for(Component component: this.car.getComponents())
			if(component.isCompleted() == false)
				completed = false;
		return completed;
	}

	/**
	 * A variable to get the delivery date.
	 *  
	 * @return  this.deliverydate
	 */
	public Date getDate() {
		return this.deliverydate;
	}

	/**
	 * A method to set the delivery date to the given delivery date.
	 * 
	 * @param   deliverydate
	 *          the delivery date of this order.
	 */
	private void setDate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}

	/**
	 * A method to get the user that placed this order.
	 * 
	 * @return   this.user
	 */
	public User getUser() {
		return this.user;
	}
}
