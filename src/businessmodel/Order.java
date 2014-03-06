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
	 * A variable that represents the order is completed or not.
	 */
	private boolean completed;
	
	/**
	 * A variable that represents an user.
	 */
	private User user;
	
	/**
	 * A variable that holds the delivery date for an order.
	 */
	private Date deliverydate;
	
	/**
	 * The constructor for an order
	 * 
	 * @param car
	 *        the car that will be created.
	 */
	public Order(User user, ArrayList<Component> components, Date deliverydate){
		setUser(user);
		this.car = new Car(components);
		setCompleted(false);
		setDate(deliverydate);
	}
	
	private void setUser(User user) {
		this.user = user;
	}

	/**
	 * This method returns the ordered car
	 * 
	 * @return this.car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * a method to see if the order is completed or not.
	 * 
	 * @return   this.completed
	 */
	public boolean getCompleted(){
		return this.completed;
	}
	
	/**
	 * A variable to get the delivery date. 
	 * @return  this.deliverydate
	 */
	public Date getDate() {
		return this.deliverydate;
	}

	/**
	 * 
	 * @param deliverydate
	 */
	private void setDate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}

	/**
	 * 
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 
	 * @param completed
	 */
	private void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
