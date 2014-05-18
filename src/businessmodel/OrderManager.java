package businessmodel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import org.joda.time.DateTime;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.order.Order;
import businessmodel.user.User;
import businessmodel.util.OrderDateTimeComparator;

/**
 * A class that represents an order manager. This class handles all the orders for a car manufacturing company.
 *
 * @author SWOP team 10
 */
public class OrderManager implements Subject {

	private ArrayList<Observer> observers;

	private PriorityQueue<Order> completedorders;

	private MainScheduler mainscheduler;

	private LinkedList<Order> pendingorders;

	private final int MILIS_ONE_DAY = 86400000;

	/**
	 * A constructor for the class OrderManager.
	 *
	 * @param    vehiclemodels
	 *           the car models that a car manufacturing company offers.
	 */
	public OrderManager() throws IllegalArgumentException {
		this.pendingorders = new LinkedList<Order>();
		Comparator<Order> comparator = new OrderDateTimeComparator();
		this.completedorders = new PriorityQueue<Order>(20, comparator);
		this.mainscheduler = new MainScheduler(this);
		this.observers = new ArrayList<Observer>();
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
		if(line == null)
			addOrderToPendingOrders(order);
	}

	/**
	 * A method to place an order in front of the pending orders.
	 * @param order
	 */
	public void placeOrderInFront(Order order) {
		if(this.getPendingOrders().size()==0)
			placeOrder(order);
		this.getPendingOrders().add(order);
	}

	protected void schedulePendingOrders(){
		scheduleSingleTaskOrders();
		CopyOnWriteArrayList<Order> tempList = new CopyOnWriteArrayList<Order>(this.getPendingOrders());
		for(Order order : tempList){
			getPendingOrders().remove(order);
			this.placeOrder(order);
		}
	}

	private void scheduleSingleTaskOrders() {
		CopyOnWriteArrayList<Order> tempList = new CopyOnWriteArrayList<Order>(this.getPendingOrders());
		for(Order order : tempList){
			if(order.getUserEndDate() != null){
				if(order.getUserEndDate().isAfter(this.getMainScheduler().getTime().plusMillis(MILIS_ONE_DAY))){
					getPendingOrders().remove(order);
					placeOrder(order);
				}
			}
		}
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
		this.completedorders.add(finished);
		this.notifyObservers();
	}

	// for testing
	@SuppressWarnings("unchecked")
	public LinkedList<Order> getCompletedOrdersClone(){
		return (LinkedList<Order>) this.getCompletedOrders().clone();
	}

	protected LinkedList<Order> getPendingOrders(){
		return this.pendingorders;
	}

	public MainScheduler getMainScheduler() {
		return this.mainscheduler;
	}

	/**
	 * A method to get the pending orders of a given user of this order manager.
	 *
	 * @return 	ArrayList<Order>
	 * 			the pending orders of a given user managed by this order manager.
	 */
	protected ArrayList<Order> getPendingOrders(User user) throws IllegalArgumentException, NoClearanceException {
		if (user == null) 
			throw new IllegalArgumentException("Bad user!");
		if (!user.canPlaceOrder()) 
			throw new NoClearanceException(user);
		ArrayList<Order> pendingorders = new ArrayList<Order>();
		for(AssemblyLine line: this.getMainScheduler().getAssemblyLines()){
			pendingorders.addAll(line.getAssemblyLineScheduler().getOrders());
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
		LinkedList<Order> temp = new LinkedList<Order>();
		Iterator<Order> henk =  this.completedorders.iterator();
		while(henk.hasNext()){
			temp.add(henk.next());
		}
		return temp;
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

	protected Order getPreviousOrder(Order order, AssemblyLine line){
		int index = this.getPendingOrders().indexOf(order);
		if(index-1 < 0)
			return null;
		else
			return this.getPendingOrders().get(index-1);
	}

	private void addOrderToPendingOrders(Order order) {
		setEstimatedTimeOfPendingOrder(order);
		this.getPendingOrders().add(order);
	}
	
	private void setEstimatedTimeOfPendingOrder(Order order){
		if(this.getPendingOrders().size() == 0){
			DateTime date = this.getMainScheduler().getTime().plusDays(1);
			date.withHourOfDay(8);
			date.withMinuteOfHour(0);
			order.setEstimatedDeliveryDateOfOrder(date.plusMinutes(this.getMainScheduler().
					getAssemblyLineSchedulers().get(0).calculateMinutes(order)));
			}
		else
			order.setEstimatedDeliveryDateOfOrder(this.getPendingOrders().getLast().getEstimatedDeliveryDate().plus(60));
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
