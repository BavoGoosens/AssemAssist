package businessmodel;

import java.util.ArrayList;

/**
 * A class that represents an order manager.
 * This class handles all the orders for a car manufacturing company.
 * @author   SWOP team 10
 *
 */
public class OrderManagement {

	/**
	 * A list that holds all the orders of a car manufacturing company.
	 */
	ArrayList<Order> orders;

	/**
	 * A list that holds all the car models of a car manufacturing company.
	 */
	ArrayList<CarModel> carmodels;

	/**
	 * A constructor for the clas OrderManager.
	 * 
	 * @param    carmodels
	 *           the car models that an car manufacturing company offers.
	 */
	public OrderManagement(ArrayList<CarModel> carmodels){
		this.orders = new ArrayList<Order>();
		setCarmodels(carmodels);
	}

	/**
	 * A method to get the orders of this order manager.
	 * 
	 * @return   this.orders.
	 */
	public ArrayList<Order> getOrders() {
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
			if (order.getCompleted() == true)
				completedorders.add(order);
		}
		return completedorders;
	}

	/**
	 * A method to get the pending orders of this order manager.
	 * 
	 * @return the pending orders of this order manager.
	 */
	public ArrayList<Order> getPedingOrders(){
		ArrayList<Order> pendingorders = new ArrayList<Order>();
		for (Order order: this.getOrders()){
			if (order.getCompleted() == false)
				pendingorders.add(order);
		}
		return pendingorders;
	}
}
