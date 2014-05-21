package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.joda.time.DateTime;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.order.Order;
import businessmodel.user.User;

/**
 * A class that represents an order manager. This class handles all the orders for a car manufacturing company.
 *
 * @author SWOP team 10
 */
public class OrderManager implements Subject {

	private ArrayList<Observer> observers;
	private LinkedList<Order> completedorders;
	private MainScheduler mainscheduler;
	private LinkedList<Order> pendingorders;
	private final int MILIS_ONE_DAY = 86400000;

	/**
	 * A constructor for the class OrderManager.
	 */
	public OrderManager() throws IllegalArgumentException {
		this.pendingorders = new LinkedList<Order>();
		this.completedorders = new LinkedList<Order>();
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
        order.setTimestampOfOrder(this.getMainScheduler().getTime());
        this.getMainScheduler().placeOrder(order);
		if(order.getEstimatedDeliveryDate() == null)
			addOrderToPendingOrders(order);
	}

	/**
	 * A method to place an order in front of the pending orders.
	 * @param order
	 */
	public void placeOrderInFront(Order order) {
		if(this.getPendingOrders().size()==0)
			placeOrder(order);
        this.addOrderToPendingOrders(order);
	}

   protected void orderCannotBePlaced(Order order){
      this.addOrderToPendingOrders(order);
   }
	/**
	 * Scheduler the pending orders.
	 */
	protected void schedulePendingOrders(){
		scheduleSingleTaskOrders();
		Order[] tempList = this.getPendingOrders().toArray(new Order[this.getPendingOrders().size()]);
		for(int i = 0 ; i < tempList.length ; i ++){
            Order order = tempList[i];
			getPendingOrders().remove(order);
			this.placeOrder(order);
		}
	}

	/**
	 * Scheduler the SingleTaskOrders.
	 */
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
	/**
	 * Get completed orders.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public LinkedList<Order> getCompletedOrdersClone(){
		return (LinkedList<Order>) this.getCompletedOrders().clone();
	}

	/**
	 * Get pending orders.
	 * @return pendingorders
	 */
	protected LinkedList<Order> getPendingOrders(){
		return this.pendingorders;
	}

	/**
	 * Get MainScheduler.
	 * @return
	 */
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
		ArrayList<Order> pendingOrders = new ArrayList<Order>();
		for(AssemblyLine line: this.getMainScheduler().getAssemblyLines()){
			pendingOrders.addAll(line.getAssemblyLineScheduler().getOrders());
		}
        for (Order order: this.getPendingOrders()){
            if (order.getUser() == user)
                pendingOrders.add(order);
        }
		return pendingOrders;
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
	 * Add order to the pending orders of the OrderManager.
	 * @param order
	 */
	private void addOrderToPendingOrders(Order order) {
		setEstimatedTimeOfPendingOrder(order);
		if(!this.getPendingOrders().contains(order))
            this.getPendingOrders().add(order);
	}
	
	/**
	 * Set the estimated delivery date of the given order.
	 * @param order
	 */
	private void setEstimatedTimeOfPendingOrder(Order order){
		if(this.getPendingOrders().size() == 0){
            DateTime date = this.getMainScheduler().getTime().plusDays(1);
            setHenk(order,date);
        }
		else {
            if (this.getPendingOrders().getLast().getEstimatedDeliveryDate().
                    plus(this.getMainScheduler().getAssemblyLineSchedulers().
                            get(0).minutesLastWorkPost(order)).getHourOfDay() > 22){
                DateTime date = this.getPendingOrders().getLast().getEstimatedDeliveryDate().plusDays(1);
                setHenk(order,date);
            }
            else
                order.setEstimatedDeliveryDateOfOrder(this.getPendingOrders().getLast().getEstimatedDeliveryDate()
                        .plus(this.getMainScheduler().getAssemblyLineSchedulers().get(0).minutesLastWorkPost(order)));
        }
	}

	/**
	 * Method to set the henk.
	 * @param order
	 * @param date
	 */
    private void setHenk(Order order, DateTime date){
        date = date.withHourOfDay(8);
        date = date.withMinuteOfHour(0);
        date = date.plusMinutes(this.getMainScheduler().
                getAssemblyLineSchedulers().get(0).calculateMinutes(order));
        order.setEstimatedDeliveryDateOfOrder(date);
    }

	@Override
	public void subscribeObserver(Observer observer) throws IllegalArgumentException {
		if (observer == null) throw new IllegalArgumentException("Bad observer!");
		this.observers.add(observer);
	}

	@Override
	public void unSubscribeObserver(Observer observer) throws IllegalArgumentException {
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
