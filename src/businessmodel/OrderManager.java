package businessmodel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	 * A list that holds all the completed orders of a car manufacturing company.
	 */
	LinkedList<Order> completedorders;

	/**
	 * A list that holds all the pending orders for a car manufacturing company.
	 */
	LinkedList<Order> pendingorders;

	/**
	 * A list that holds all the car models of a car manufacturing company.
	 */
	ArrayList<CarModel> carmodels;
	
	/**
	 * A production scheduler this Order Manager uses.
	 */
	ProductionScheduler production;

	/**
	 * A constructor for the class OrderManager.
	 * 
	 * @param    carmodels
	 *           the car models that an car manufacturing company offers.
	 */
	public OrderManager(ArrayList<CarModel> carmodels){
		this.pendingorders = new LinkedList<Order>();
		this.completedorders = new LinkedList<Order>();
		Calendar start = Calendar.getInstance();
		start.set(Calendar.HOUR_OF_DAY, 8);
		start.set(Calendar.MINUTE,0);
		start.set(Calendar.SECOND,0);
		this.setProductionManager(new ProductionScheduler(this, start));
		this.setCarmodels(carmodels);
	}

	/**
	 * A method that adds a new Order.
	 * 
	 * @param order
	 * 		  An Order that needs to be added.
	 */
	public void placeOrder(Order order){
		this.addOrder(order);
		this.getProductionManager();
	}
	
	/**
	 * A method to get the orders of this order manager.
	 * 
	 * @return All the orders of this order manager.
	 */
	public LinkedList<Order> getOrders() {
		LinkedList<Order> temp = this.getPendingOrders();
		for(Order order: this.getCompletedOrders()){
			temp.add(order);
		}
		return temp;
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
		this.getPendingOrders().add(order);
	}

	/**
	 * A method to remove an order of this order manager.
	 * 
	 * @param    order
	 * 			 the order that needs to be removed.
	 */
	public void CompleteOrder(Order order){
		this.getPendingOrders().remove(order);
		this.getCompletedOrders().add(order);
	}

	/**
	 * A method to get the completed orders of this order manager.
	 * 
	 * @return  the completed orders of this order manager.
	 */
	public LinkedList<Order> getCompletedOrders(){
		return this.completedorders;
	}

	/**
	 * A method to get the pending orders of this order manager.
	 * 
	 * @return the pending orders of this order manager.
	 */
	public LinkedList<Order> getPendingOrders(){
		return this.pendingorders;
	}

	/**
	 * A method to get the completed orders of a given user of this order manager.
	 * 
	 * @return  the completed orders of a given user of this order manager.
	 */
	public ArrayList<Order> getCompletedOrders(User user){
		ArrayList<Order> completedorders = new ArrayList<Order>();
		for (Order order: this.getCompletedOrders()){
			if (order.getUser() == user)
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
		// The scheduled orders for today
		pendingorders.addAll(this.getProductionManager().getScheduledOrders());
		// The pending orders
		for (Order order: this.getPendingOrders()){
			if (order.getUser() == user)
				pendingorders.add(order);
		}
		return pendingorders;
	}


	public ProductionScheduler getProductionManager() {
		return production;
	}

	public void setProductionManager(ProductionScheduler production) {
		this.production = production;
	}

	@Override
	public String toString() {
		return "orders= " + this.getOrders().toString() + ", carmodels= " + carmodels.toString();
	}

	/**
	 * A method to update the completion time of the pending orders.
	 * @param index
	 */
	public void updateEstimatedTime(int index) {
		if(index == 60){
			for(Order order: this.getPendingOrders()){
				Calendar time = order.getDate();
				order.getDate().set(time.get(Calendar.YEAR),
						time.get(Calendar.MONTH),
						time.get(Calendar.DAY_OF_MONTH),
						time.get(Calendar.HOUR_OF_DAY+1),
						time.get(Calendar.MINUTE));
			}	
		}
		if(index == -60){
			for(Order order: this.getPendingOrders()){
				Calendar time = order.getDate();
				order.getDate().set(time.get(Calendar.YEAR),
						time.get(Calendar.MONTH),
						time.get(Calendar.DAY_OF_MONTH),
						time.get(Calendar.HOUR_OF_DAY-1),
						time.get(Calendar.MINUTE));
			}	
		}
	}

	/**
	 * A method that moves a finished order from the pending list to the finished list.
	 * 
	 * @param finished 
	 * 		  The Order that needs to be moved.
	 */
	public void finishedOrder(Order finished) {
		this.getCompletedOrders().add(finished);
	}

	/**
	 * This method returns the number of specified pending orders if possible.
	 * 
	 * @param nb
	 * 		  The number of orders 
	 * 
	 * @return LinkedList<Order>
	 * 		   A list with the requested orders.
	 */
	public LinkedList<Order> getNbOrders(int nb) {
		LinkedList<Order> res = new LinkedList<Order>();
		for (int i = 0 ; i < nb - 1; i++){
			Order tmp = this.getPendingOrders().pollFirst();
			if (tmp == null)
				break;
			res.add(tmp);
		}
		return res;
	}
}