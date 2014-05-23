package businessmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.joda.time.DateTime;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.observer.OrderStatisticsObserver;
import businessmodel.observer.OrderStatisticsSubject;
import businessmodel.order.Order;
import businessmodel.user.User;
import org.joda.time.Period;

/**
 * A class that represents an order manager. This class handles all the orders for a car manufacturing company.
 *
 * @author SWOP team 10
 */
public class OrderManager implements OrderStatisticsSubject {

	private ArrayList<OrderStatisticsObserver> observers;
	private LinkedList<Order> completedOrders;
	private MainScheduler mainscheduler;
	private LinkedList<Order> pendingOrders;
	private final int MILIS_ONE_DAY = 86400000;

	/**
	 * A constructor for the class OrderManager.
	 */
	public OrderManager() throws IllegalArgumentException {
		this.pendingOrders = new LinkedList<Order>();
        this.completedOrders = new LinkedList<Order>();
		this.mainscheduler = new MainScheduler(this);
		this.observers = new ArrayList<OrderStatisticsObserver>();
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
        // The time the order was placed
        if(order.getTimestamp() == null)
            order.setTimestampOfOrder(this.getMainScheduler().getTime());
        // try to place the order on one of the assembly lines
        this.getMainScheduler().placeOrder(order);
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
	public void finishedOrder(Order finished, int actualDelay) throws IllegalArgumentException {
		if (finished == null)
			throw new IllegalArgumentException("Bad order!");
		this.completedOrders.add(finished);
		this.notifyObservers(finished, actualDelay);
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
	 * @return pendingOrders
	 */
	protected LinkedList<Order> getPendingOrders(){
        return this.pendingOrders;
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
			pendingOrders.addAll(line.getAssemblyLineScheduler().getOrdersClone());
		}
        pendingOrders.addAll(this.getPendingOrders());
        ArrayList<Order> result = new ArrayList<>();
        for (Order order: pendingOrders){
            if (order.getUser() == user)
                result.add(order);
        }
		return result;
	}

	/**
	 * A method to get the completed orders of this order manager.
	 *
	 * @return  the completed orders of this order manager.
	 */
	protected LinkedList<Order> getCompletedOrders(){
		return this.completedOrders;
	}

	/**
	 * A method to get the completed orders of a given user of this order manager.
	 *
	 * @return  the completed orders of a given user of this order manager.
	 */
	protected ArrayList<Order> getCompletedOrders(User user) throws IllegalArgumentException, NoClearanceException {
		if (user == null) throw new IllegalArgumentException("Bad user!");
		if (!user.canPlaceOrder()) throw new NoClearanceException(user);
		ArrayList<Order> completedOrders = new ArrayList<Order>();
		for (Order order: this.getCompletedOrders()){
			if (order.getUser() == user)
				completedOrders.add(order);
		}
		return (ArrayList<Order>) completedOrders;
	}


	/**
	 * Add order to the pending orders of the OrderManager.
	 * @param order
	 */
	private void addOrderToPendingOrders(Order order) {
		if(!this.getPendingOrders().contains(order))
            this.getPendingOrders().add(order);
        updateEstimatedTimesOfPendingOrders();
	}
	
	/**
	 * Set the estimated delivery date of the given order.
	 */
	private void updateEstimatedTimesOfPendingOrders(){
        HashMap<AssemblyLine, LinkedList<Order>> estimator = new HashMap<>();
        for (AssemblyLine assemblyLine : this.getMainScheduler().getAssemblyLines())
            estimator.put(assemblyLine, new LinkedList<Order>());
        for ( Order order :this.getPendingOrders()) {
            // bepaal welke assemblyline het snelst gaat
            AssemblyLine line = this.determineBestAssemblyLine(order, estimator);
            LinkedList<Order> goal = estimator.get(line);
            // als het het eerste order is begin nieuwe dag op op basis van de datum van de
            // Mainscheduler plus 1
            if (goal.isEmpty()) {
                DateTime date = this.getMainScheduler().getTime().plusDays(1);
                newDayDate(order, date, line);
                goal.add(order);
            } else {
                // anders neem estimate vorig order en voeg er de verwachte tijd aan toe
                DateTime date = goal.getLast().getEstimatedDeliveryDate().
                        plusMinutes(line.getAssemblyLineScheduler().minutesLastWorkPost(order));
                if (date.getHourOfDay() >= 22 & date.getMinuteOfHour() >= 0) {
                    DateTime date1 = goal.getLast().getEstimatedDeliveryDate().plusDays(1);
                    newDayDate(order, date1, line);
                } else {
                    order.setStandardTimeToFinish(line.getAssemblyLineScheduler().calculateMinutes(order));
                    order.setEstimatedDeliveryDateOfOrder(goal.getLast().getEstimatedDeliveryDate()
                            .plusMinutes(line.getAssemblyLineScheduler().minutesLastWorkPost(order)));
                }
                goal.add(order);
            }
        }
	}

	/**
	 * Method to set the henk.
	 * @param order
	 * @param estimator
	 */

    private AssemblyLine determineBestAssemblyLine(Order order, HashMap<AssemblyLine, LinkedList<Order>> estimator) {
        AssemblyLine result= null;

        ArrayList<AssemblyLine> canAcceptModel = new ArrayList<>();
        for (AssemblyLine assemblyLine : this.getMainScheduler().getAssemblyLines() ){
                if (assemblyLine.couldAcceptModel(order.getVehicleModel()))
                    canAcceptModel.add(assemblyLine);
        }

        AssemblyLine first = canAcceptModel.get(0);
        DateTime fastestDate;
        if (estimator.get(first).isEmpty()){
            DateTime date = this.getMainScheduler().getTime().plusDays(1);
            newDayDate(order, date, first);
            fastestDate = order.getEstimatedDeliveryDate();
        } else {
            fastestDate = estimator.get(first).getLast().getEstimatedDeliveryDate().plusMinutes(first.getAssemblyLineScheduler().minutesLastWorkPost(order));
        }
        result = first;
        for(AssemblyLine line : canAcceptModel) {
            LinkedList<Order> candidate = estimator.get(line);
            DateTime date;
            if (candidate.isEmpty()){
                date = this.getMainScheduler().getTime().plusDays(1);
                newDayDate(order, date, line);
                date = order.getEstimatedDeliveryDate();
            }else {
                date = candidate.getLast().getEstimatedDeliveryDate().plusMinutes(line.getAssemblyLineScheduler().minutesLastWorkPost(order));
            }
            if (date.isBefore(fastestDate)) {
                fastestDate = date;
                result = line;
            }
        }
        return result;
    }

    private void newDayDate(Order order, DateTime date, AssemblyLine assemblyLine){
        int standard = assemblyLine.getAssemblyLineScheduler().calculateMinutes(order);
        order.setStandardTimeToFinish(standard);
        date = date.withHourOfDay(6);
        date = date.withMinuteOfHour(0);
        date = date.plusMinutes(standard);
        order.setEstimatedDeliveryDateOfOrder(date);
    }

	@Override
	public void subscribeObserver(OrderStatisticsObserver observer) throws IllegalArgumentException {
		if (observer == null) throw new IllegalArgumentException("Bad observer!");
		this.observers.add(observer);
	}

	@Override
	public void unSubscribeObserver(OrderStatisticsObserver observer) throws IllegalArgumentException {
		if (observer == null) throw new IllegalArgumentException("Bad observer!");
		this.observers.remove(observer);
	}
	
	public void notifyObservers(Order finished, int actualDelay) {
		for (OrderStatisticsObserver observer: this.observers) {
			observer.update(finished, actualDelay);
		}
	}
}
