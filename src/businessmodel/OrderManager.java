package businessmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import org.joda.time.DateTime;

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


	public LinkedList<Order> getPendingOrders(){
		return this.pendingorders;
	}

	/**
	 * A list that holds all the completed orders of a car manufacturing company.
	 */
	private LinkedList<Order> completedorders;

	/**
	 * A list that holds all the car models of a car manufacturing company.
	 */
	private ArrayList<CarModel> carmodels = new ArrayList<CarModel>();

	/**
	 * A scheduler this Order Manager uses.
	 */
	private Scheduler scheduler;

	private LinkedList<Order> pendingorders;

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
		order.setTimestamp(this.getScheduler().getCurrentTime());
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
	protected ArrayList<Order> getPendingOrders(User user) throws IllegalArgumentException, NoClearanceException {
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
		LinkedList<Order> single_task_orders = getSingleTaskOrdersNextDay();
		
		if(single_task_orders!= null){
			for(Order order: single_task_orders){
				res.add(order);
				getPendingOrders().remove(order);
			}
		}
		
		for (int i = 0; i < (nb - single_task_orders.size()); i++){
			res.add(getPendingOrders().poll());
		}
		return res;
	}

	private LinkedList<Order> getSingleTaskOrdersNextDay() {
		LinkedList<Order> temp = new LinkedList<Order>();
		
		for(Order order: this.getPendingOrders()){
			if(order.getUser_end_date()!= null){
				if(order.getUser_end_date().getDayOfWeek()-1 == this.getScheduler().getCurrentTime().getDayOfWeek()){
					int index = this.getPendingOrders().indexOf(order);
					temp.add(this.getPendingOrders().get(index));
				}
			}
		}
		
		return temp;
	}

	private void setCarModels(ArrayList<CarModel> carmodels) throws IllegalArgumentException {
		if (carmodels == null) throw new IllegalArgumentException("Bad list of car models!");
		this.carmodels = carmodels;
	}

	/**
	 * A method to place an order in front of the pending orders.
	 * @param order
	 */
	public void PlaceOrderInFront(Order order) {
		this.getPendingOrders().add(order);		
	}
}