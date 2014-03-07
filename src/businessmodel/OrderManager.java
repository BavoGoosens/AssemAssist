package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class that represents an order manager.
 * This class handles all the orders for a car manufacturing company.
 * 
 * @author   SWOP team 10
 *
 */
public class OrderManager {

	/**
	 * A list that holds all the orders of a car manufacturing company.
	 */
	LinkedList<Order> orders;

	/**
	 * A list that holds all the car models of a car manufacturing company.
	 */
	ArrayList<CarModel> carmodels;

	/**
	 * A constructor for the class OrderManager.
	 * 
	 * @param    carmodels
	 *           the car models that an car manufacturing company offers.
	 */
	public OrderManager(ArrayList<CarModel> carmodels){
		this.orders = new LinkedList<Order>();
		setCarmodels(carmodels);
	}

	/**
	 * A method to get the orders of this order manager.
	 * 
	 * @return   this.orders.
	 */
	public LinkedList<Order> getOrders() {
		return this.orders;
	}

	/**
	 * A method to get the car models of this order manager.
	 * 
	 * @return   this.carmodels
	 */
	public ArrayList<CarModel> getCarmodels() {
		return carmodels;
	}

	/**
	 * A method to set the car models of this order manager to the given car models.
	 * 
	 * @param    carmodels
	 *           the new car models of this order manager.
	 */
	private void setCarmodels(ArrayList<CarModel> carmodels){
		this.carmodels = carmodels;
	}


	/**
	 * A method to add an order to this order manager.
	 * 
	 * @param   order
	 *          the order that needs to be added.
	 */
	public void addOrder(Order order){
		this.getOrders().add(order);
	}

	/**
	 * A method to remove an order of this order manager.
	 * 
	 * @param    order
	 * 			 the order that needs to be removed.
	 */
	public void deleteOrder(Order order){
		this.getOrders().remove(order);
	}

	// @return aanpassen
	/**
	 * A method to get the completed orders of this order manager.
	 * 
	 * @return  the completed orders of this order manager.
	 */
	public ArrayList<Order> getCompletedOrders(){
		ArrayList<Order> completedorders = new ArrayList<Order>();
		for (Order order: this.getOrders()){
			if (order.isCompleted() == true)
				completedorders.add(order);
		}
		return completedorders;
	}

	/**
	 * A method to get the pending orders of this order manager.
	 * 
	 * @return the pending orders of this order manager.
	 */
	public LinkedList<Order> getPendingOrders(){
		LinkedList<Order> pendingorders = new LinkedList<Order>();
		for (Order order: this.getOrders()){
			if (order.isCompleted() == false)
				pendingorders.add(order);
		}
		return pendingorders;
	}
	
	/**
	 * A method to get the completed orders of a given user of this order manager.
	 * 
	 * @return  the completed orders of a given user of this order manager.
	 */
	public ArrayList<Order> getCompletedOrders(User user){
		ArrayList<Order> completedorders = new ArrayList<Order>();
		for (Order order: this.getOrders()){
			if (order.isCompleted() == true && order.getUser() == user)
				completedorders.add(order);
		}
		return completedorders;
	}

	/**
	 * A method to get the pending orders of a given user of this order manager.
	 * 
	 * @return the pending orders of a given user this order manager.
	 */
	public ArrayList<Order> getPendingOrders(User user){
		ArrayList<Order> pendingorders = new ArrayList<Order>();
		for (Order order: this.getOrders()){
			if (order.isCompleted() == false && order.getUser() == user)
				pendingorders.add(order);
		}
		return pendingorders;
	}
	

	@Override
	public String toString() {
		return "orders= " + orders.toString() + ", carmodels= " + carmodels.toString();
	}

	// nog te implementeren
//	public void updateEstimatedTime() {
//		for(Order order: this.getPendingOrders()){
//			order.setDate(order.getDate().);
//		}
//	}
}
