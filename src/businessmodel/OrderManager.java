package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;
import businessmodel.exceptions.IllegalNumberException;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.scheduler.Scheduler;
import businessmodel.user.User;

/**
 * A class that represents an order manager.
 * This class handles all the orders for a car manufacturing company.
 * 
 * @author   SWOP team 10
 */
public class OrderManager {

	/**
	 * A list that holds all the completed orders of a car manufacturing company.
	 */
	private LinkedList<Order> completedorders;

	/**
	 * A list that holds all the pending orders for a car manufacturing company.
	 */
	private LinkedList<Order> pendingorders;

	/**
	 * A list that holds all the car models of a car manufacturing company.
	 */
	private ArrayList<CarModel> carmodels = new ArrayList<CarModel>();

	/**
	 * A scheduler this Order Manager uses.
	 */
	private Scheduler scheduler;

	/**
	 * A constructor for the class OrderManager.
	 * 
	 * @param    carmodels
	 *           the car models that a car manufacturing company offers.
	 */
	public OrderManager(ArrayList<CarModel> carmodels) throws IllegalArgumentException {
		this.pendingorders = new LinkedList<Order>();
		this.completedorders = new LinkedList<Order>();
		this.scheduler = new Scheduler(this);
		this.setCarModels(carmodels);
	}

	/**
	 * A method that adds a new Order.
	 * 
	 * @param order
	 * 		  An Order that needs to be added.
	 */
	public void placeOrder(Order order) throws IllegalArgumentException {
		this.addOrder(order);
		
	}

	/**
	 * A method to get the orders of this order manager.
	 * 
	 * @return 	LinkedList<Order>
	 * 			All the orders of this order manager.
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
	 * @return  ArrayList<CarModel> 
	 * 			this.carmodels
	 */
	public ArrayList<CarModel> getCarmodels() {
		return carmodels;
	}

	/**
	 * A method to add an order to this order manager.
	 * 
	 * @param   order
	 *          the order that needs to be added.
	 */
	public void addOrder(Order order) throws IllegalArgumentException {
		if (order == null) throw new IllegalArgumentException("Bad order!");
		this.getPendingOrders().add(order);
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
	public ArrayList<Order> getCompletedOrders(User user) throws IllegalArgumentException, NoClearanceException {
		if (user == null) throw new IllegalArgumentException("Bad user!");
		if (!user.canPlaceOrder()) throw new NoClearanceException(user);
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
	 * @return 	ArrayList<Order>
	 * 			the pending orders of a given user managed by this order manager.
	 */
	public ArrayList<Order> getPendingOrders(User user) throws IllegalArgumentException, NoClearanceException {
		if (user == null) throw new IllegalArgumentException("Bad user!");
		if (!user.canPlaceOrder()) throw new NoClearanceException(user);
		ArrayList<Order> pendingorders = new ArrayList<Order>();
		pendingorders.addAll(this.getScheduler().getOrders());
		for (Order order: this.getPendingOrders()){
			if (order.getUser() == user)
				pendingorders.add(order);
		}
		return pendingorders;
	}

	/**
	 * A method that returns the Scheduler for this OrderManager.
	 * 
	 * @return	this.scheduler
	 */
	public Scheduler getScheduler() {
		return this.scheduler;
	}

	/**
	 * A method that sets the scheduler for this OrderManager.
	 * 
	 * @param 	scheduler
	 * 			The Scheduler this OrderManager uses.
	 */
	public void setScheduler(Scheduler scheduler) throws IllegalArgumentException {
		if (scheduler == null) throw new IllegalArgumentException("Bad production scheduler!");
		this.scheduler = scheduler;
	}

	@Override
	public String toString() {
		return "orders= " + this.getOrders().toString() + ", carmodels= " + carmodels.toString();
	}

	/**
	 * A method that moves a finished order from the pending list to the finished list.
	 * 
	 * @param finished 
	 * 		  The Order that needs to be moved.
	 */
	public void finishedOrder(Order finished) throws IllegalArgumentException {
		if (finished == null) throw new IllegalArgumentException("Bad order!");
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
		if (nb < 0) throw new IllegalNumberException(nb, "Bad number!");
		LinkedList<Order> res = new LinkedList<Order>();
		for (int i = 0 ; i < nb; i++){
			Order tmp = this.getPendingOrders().pollFirst();
			if (tmp == null)
				break;
			res.add(tmp);
		}
		return res;
	}
	
	private void setCarModels(ArrayList<CarModel> carmodels) throws IllegalArgumentException {
		if (carmodels == null) throw new IllegalArgumentException("Bad list of car models!");
		this.carmodels = carmodels;
	}

	/**
	 * A method to place an order in front of the pending orders.
	 * @param order
	 */
	public void placeOrderInFront(Order order) {
		this.getPendingOrders().addFirst(order);		
	}
}