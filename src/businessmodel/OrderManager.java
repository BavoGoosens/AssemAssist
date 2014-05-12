package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.exceptions.IllegalNumberException;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.order.Order;
import businessmodel.user.User;

/**
 * A class that represents an order manager.
 * This class handles all the orders for a car manufacturing company.
 * 
 * @author   SWOP team 10
 */
public class OrderManager implements Subject {

	private ArrayList<Observer> observers;
	
	/**
	 * A list that holds all the completed orders of a car manufacturing company.
	 */
	private LinkedList<Order> completedorders;

	/**
	 * A list that holds all the car models of a car manufacturing company.
	 */
	private ArrayList<VehicleModel> carmodels = new ArrayList<VehicleModel>();

	/**
	 * A scheduler this Order Manager uses.
	 */
	private Scheduler scheduler;

	/**
	 * List of the pending orders.
	 */
	private LinkedList<Order> pendingorders;

	/**
	 * A constructor for the class OrderManager.
	 * 
	 * @param    carmodels
	 *           the car models that a car manufacturing company offers.
	 */
	public OrderManager(ArrayList<VehicleModel> carmodels) throws IllegalArgumentException {
		this.pendingorders = new LinkedList<Order>();
		this.completedorders = new LinkedList<Order>();
		this.scheduler = new Scheduler(this);
		this.observers = new ArrayList<Observer>();
		this.setCarModels(carmodels);
	}

	/**
	 * A method to get the car models of this order manager.
	 * 
	 * @return  ArrayList<CarModel> 
	 * 			this.carmodels
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<VehicleModel> getCarmodels() {
		return (ArrayList<VehicleModel>) carmodels.clone();
	}

	// for testing
	@SuppressWarnings("unchecked")
	public LinkedList<Order> getCompletedOrdersClone(){
		return (LinkedList<Order>) this.completedorders.clone();
	}

	/**
	 * A method to add an order to this order manager.
	 * 
	 * @param   order
	 *          the order that needs to be added.
	 */
	protected void placeOrder(Order order) throws IllegalArgumentException {
		if (order == null) throw new IllegalArgumentException("Bad order!");
		order.setTimestampOfOrder(this.getScheduler().getCurrentTime());
		this.setEstimatedCompletionDateOfOrder(order);
		if(this.getScheduler().canAddOrder())
			this.getScheduler().addOrderToSchedule(order);
		else
			this.getPendingOrders().add(order);
	}

	protected LinkedList<Order> getPendingOrders(){
		return this.pendingorders;
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
	 * A method to get the completed orders of this order manager.
	 * 
	 * @return  the completed orders of this order manager.
	 */
	protected LinkedList<Order> getCompletedOrders(){
		return this.completedorders;
	}
	
	/**
	 * A method to get the completed orders of a given user of this order manager.
	 * 
	 * @return  the completed orders of a given user of this order manager.
	 */
	protected ArrayList<Order> getCompletedOrders(User user) throws IllegalArgumentException, NoClearanceException {
		if (user == null) throw new IllegalArgumentException("Bad user!");
		if (!user.canPlaceOrder()) throw new NoClearanceException(user);
		ArrayList<Order> completedorders = new ArrayList<Order>();
		for (Order order: this.getCompletedOrders()){
			if (order.getUser() == user)
				completedorders.add(order);
		}
		return (ArrayList<Order>) completedorders;
	}

	/**
	 * A method that moves a finished order from the pending list to the finished list.
	 * 
	 * @param finished 
	 * 		  The Order that needs to be moved.
	 */
	protected void finishedOrder(Order finished) throws IllegalArgumentException {
		if (finished == null) throw new IllegalArgumentException("Bad order!");
		this.getCompletedOrders().add(finished);
		this.notifyObservers();
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
	protected LinkedList<Order> getNbOrders(int nb) {

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
			Order order = getPendingOrders().poll();
			if (order != null)
				res.add(order);
		}
		return res;
	}

	/**
	 * A method to place an order in front of the pending orders.
	 * @param order
	 */
	protected void placeOrderInFront(Order order) {
		this.getPendingOrders().add(order);		
	}

	/**
	 * Method to set an estimated completion time for a particular order.
	 * @param order
	 */
	protected void setEstimatedCompletionDateOfOrder(Order order){
		Order previousorder = this.getPreviousOrder(order);
		if(previousorder != null) {
			if(previousorder.getEstimatedDeliveryDate() == null){
				order.setEstimatedDeliveryDateOfOrder(this.getScheduler().getCurrentTime().plusHours(3));
			}else if(previousorder.getEstimatedDeliveryDate().getHourOfDay() <= 21){
				order.setEstimatedDeliveryDateOfOrder(previousorder.getEstimatedDeliveryDate().plusHours(1));
			}else {
				order.setEstimatedDeliveryDateOfOrder(previousorder.getEstimatedDeliveryDate().plusDays(1).withHourOfDay(11).withMinuteOfHour(0));
			}
		}else{
			order.setEstimatedDeliveryDateOfOrder(this.getScheduler().getCurrentTime().plusHours(3));
		}
	}

	/**
	 * A method to get the previous order in the list.
	 * @param 	order
	 * 			the current order.
	 * @return	the previous order of the current order.
	 */
	protected Order getPreviousOrder(Order order){
		if(this.getScheduler().getOrders().size() > this.getScheduler().getNumberOfOrdersToSchedule()){
			int index = this.getPendingOrders().indexOf(order);
			if(index-1 < 0)
				return null;
			else
				return this.getPendingOrders().get(index-1);
		}
		else
			if(this.getScheduler().getOrders().size()== 0)
				return null;
			return this.getScheduler().getOrders().getLast();
	}
	

	/**
	 * Method to set the car models
	 * 
	 * @param carmodels
	 * @throws IllegalArgumentException
	 */
	private void setCarModels(ArrayList<VehicleModel> carmodels) throws IllegalArgumentException {
		if (carmodels == null) throw new IllegalArgumentException("Bad list of car models!");
		this.carmodels = carmodels;
	}

	/**
	 * Method to return a list of the single task orders scheduled on the next day.
	 * @return list of the single task orders scheduled on the next day.
	 */
	private LinkedList<Order> getSingleTaskOrdersNextDay() {
		LinkedList<Order> temp = new LinkedList<Order>();
	
		for(Order order: this.getPendingOrders()){
			if(order.getUserEndDate()!= null){
				if(order.getUserEndDate().getDayOfWeek()-1 == this.getScheduler().getCurrentTime().getDayOfWeek()){
					int index = this.getPendingOrders().indexOf(order);
					temp.add(this.getPendingOrders().get(index));
				}
			}
		}
	
		return temp;
	}

	/**
	 * A method that returns the Scheduler for this OrderManager.
	 * 
	 * @return	this.scheduler
	 */
	public Scheduler getScheduler() {
		return this.scheduler;
	}
	
	@Override
	public void subscribeObserver(Observer observer) throws IllegalArgumentException {
		if (observer == null) throw new IllegalArgumentException("Bad observer!");
		this.observers.add(observer);
	}

	@Override
	public void unsubscribeObserver(Observer observer) throws IllegalArgumentException {
		if (observer == null) throw new IllegalArgumentException("Bad observer!");
		this.observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer: this.observers) {
			observer.update(this);
		}
	}
}