package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.assemblyline.AssemblyLine;
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

	private LinkedList<Order> completedorders;

	private MainScheduler mainscheduler;

	private LinkedList<Order> pendingorders;

	/**
	 * A constructor for the class OrderManager.
	 *
	 * @param    vehiclemodels
	 *           the car models that a car manufacturing company offers.
	 */
	public OrderManager() throws IllegalArgumentException {
		this.pendingorders = new LinkedList<Order>();
		this.completedorders = new LinkedList<Order>();
		this.mainscheduler = new MainScheduler(this);
		this.observers = new ArrayList<Observer>();
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
		if (order == null)
			throw new IllegalArgumentException("Bad order!");
		AssemblyLine line = this.getMainScheduler().placeOrder(order);
		if(line!= null){
			order.setTimestampOfOrder(line.getAssemblyLineScheduler().getCurrentTime());
			this.setEstimatedCompletionDateOfOrder(order, line);}
		else{
			this.getPendingOrders().add(order);
		}
	}

	public LinkedList<Order> getPendingOrders(){
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
		for(AssemblyLine line: this.getMainScheduler().getAssemblyLines()){
			pendingorders.addAll(line.getAssemblyLineScheduler().getOrdersClone());
			for (Order order: this.getPendingOrders()){
				if (order.getUser() == user)
					pendingorders.add(order);
			}
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
	public void finishedOrder(Order finished) throws IllegalArgumentException {
		if (finished == null)
			throw new IllegalArgumentException("Bad order!");
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
	public LinkedList<Order> getNbOrders(int nb, AssemblyLine line) {
		if (nb < 0)
			throw new IllegalNumberException("Bad number!");
		LinkedList<Order> res = new LinkedList<Order>();
		LinkedList<Order> single_task_orders = getSingleTaskOrdersNextDay(line);
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
	public void placeOrderInFront(Order order) {
		this.getPendingOrders().add(order);
	}

	/**
	 * Method to set an estimated completion time for a particular order.
	 * @param order
	 */
	public void setEstimatedCompletionDateOfOrder(Order order, AssemblyLine line){

		Order previousorder = this.getPreviousOrder(order, line);
		if(previousorder != null) {
			if(previousorder.getEstimatedDeliveryDate() == null){
				order.setEstimatedDeliveryDateOfOrder(line.getAssemblyLineScheduler().getCurrentTime().plusHours(3));
			}else if(previousorder.getEstimatedDeliveryDate().getHourOfDay() <= 21){
				order.setEstimatedDeliveryDateOfOrder(previousorder.getEstimatedDeliveryDate().plusHours(1));
			}else {
				order.setEstimatedDeliveryDateOfOrder(previousorder.getEstimatedDeliveryDate().plusDays(1).withHourOfDay(11).withMinuteOfHour(0));
			}
		}else{
			order.setEstimatedDeliveryDateOfOrder(line.getAssemblyLineScheduler().getCurrentTime().plusHours(3));
		}
	}

	public LinkedList<Order> getPendingOrders(){
		return this.pendingorders;
	}

	/**
	 * A method to add an order to this order manager.
	 *
	 * @param   order
	 *          the order that needs to be added.
	 */
	protected void placeOrder(Order order) throws IllegalArgumentException {
		if (order == null) 
			throw new IllegalArgumentException("Bad order!");
		AssemblyLine line = this.getMainScheduler().placeOrder(order);
		if(line!= null){
			order.setTimestampOfOrder(line.getAssemblyLineScheduler().getCurrentTime());
			this.setEstimatedCompletionDateOfOrder(order, line);}
		else{
			this.getPendingOrders().add(order);
		}
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
		for(AssemblyLine line: this.getMainScheduler().getAssemblylines()){
			pendingorders.addAll(line.getAssemblyLineScheduler().getOrdersClone());
			for (Order order: this.getPendingOrders()){
				if (order.getUser() == user)
					pendingorders.add(order);
			}
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

	protected MainScheduler getMainScheduler() {
		return this.mainscheduler;
	}

	/**
	 * A method to get the previous order in the list.
	 * @param 	order
	 * 			the current order.
	 * @return	the previous order of the current order.
	 */
	// Nakijken of het werkt met de clone
	protected Order getPreviousOrder(Order order, AssemblyLine line){
		if(line.getAssemblyLineScheduler().getOrders().size() > line.getAssemblyLineScheduler().getNumberOfOrdersToSchedule()){
			int index = this.getPendingOrders().indexOf(order);
			if(index-1 < 0)
				return null;
			else
				return this.getPendingOrders().get(index-1);
		}else{
			if(line.getAssemblyLineScheduler().getOrders().size() <= 1)
				return null;
		}

		return line.getAssemblyLineScheduler().getOrders().get(line.getAssemblyLineScheduler().getOrders().size()-2);
	}

	/**
	 * Method to return a list of the single task orders scheduled on the next day.
	 * @return list of the single task orders scheduled on the next day.
	 */
	private LinkedList<Order> getSingleTaskOrdersNextDay(AssemblyLine line) {
		LinkedList<Order> temp = new LinkedList<Order>();
		for(Order order: this.getPendingOrders()){
			if(order.getUserEndDate()!= null){
				if(order.getUserEndDate().getDayOfWeek()-1 == line.getAssemblyLineScheduler().getCurrentTime().getDayOfWeek()){
					int index = this.getPendingOrders().indexOf(order);
					temp.add(this.getPendingOrders().get(index));
				}
			}
		}
		return temp;
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
