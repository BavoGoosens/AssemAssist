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
	public Order(User user, ArrayList<Component> components){
		setUser(user);
		this.car = new Car(components);
		this.deliverydate = new Date();	
	}

	/**
	 * A method to set the user of this order to the given order.
	 * @param   user
	 *          the new user of this order.
	 * @throws  IllegalArgumentException
	 * 			!canHaveAsUser(user)
	 */
	private void setUser(User user) throws IllegalArgumentException {
		if (!canHaveAsUser(user)) 
			throw new IllegalArgumentException();
		this.user = user;
	}

	/**
	 * A method to check if this order can have the given user.
	 * @param   user
	 *          the new user of this order.
	 * @return  true if user is instanceof garage holder
	 */
	private boolean canHaveAsUser(User user) {
		return (user instanceof GarageHolder);		
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
	public void setDate(Date deliverydate) {
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
	
	@Override
	public String toString() {
		return "user: " + this.user.toString() + ", delivery date= " + this.deliverydate.toString();
	}

}
