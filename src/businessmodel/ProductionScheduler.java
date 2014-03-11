package businessmodel;

import java.util.Calendar;
import java.util.LinkedList;

/**
 * A class that is responsible for scheduling in orders for an individual day.
 * 
 * @author Team 10
 *
 */
public class ProductionScheduler {

	/**
	 * A variable that holds todays date and current time. 
	 */
	private Calendar today;

	/**
	 * The current delay of the production scheduler in minutes of this day.
	 */
	private int delay = 0;

	/**
	 * The number of hours this Scheduler can use today.
	 */
	private int availableTime;

	/**
	 * The AssemblyLine for this Scheduler.
	 */
	private AssemblyLine assemblyline;

	/**
	 * The Orders the Scheduler will try to handle today.
	 */
	private LinkedList<Order> dayorders;

	/**
	 * The OrderManager that supplies and manages the orders for this Scheduler.
	 */
	private OrderManager ordermanager;

	/**
	 * A method that construct a ProductionScheduler.
	 */
	public ProductionScheduler(OrderManager ordermanager, Calendar start) {	
		this.setToday(start);
		this.setAvailableTime(14*60);
		this.setOrderManager(ordermanager);
		this.setAssemblyline(new AssemblyLine());
		dayorders = new LinkedList<Order>();
	}

	/**
	 * A method that returns the OrderManager for this ProductionScheduler.
	 * 
	 * @return OrderManager
	 * 		   The OrderManager that supplies and manages the orders for this ProductionScheduler.
	 */
	private OrderManager getOrderManager() {
		return ordermanager;
	}

	/**
	 *  A method that sets the OrderManager for this ProductionScheduler.
	 *  
	 * @param ordermanager
	 * 		  The OrderManager that supplies and manages the orders for this ProductionScheduler.
	 */
	private void setOrderManager(OrderManager ordermanager) {
		this.ordermanager = ordermanager;
	}

	/**
	 * This method returns the list of orders that will probably be finished today. 
	 * There might be delays or speedups that cause the list to shrink of grow.
	 * 
	 * @return LinkedList<Order>
	 * 		   A LinkedList with all the pending orders for today.
	 * 
	 */
	protected LinkedList<Order> getDayorders() {
		return dayorders;
	}

	/**
	 * This method advances the assembly line if possible.
	 */
	protected void advance(int time){
		Order finished = null; 
		if (this.getAssemblyline().canAdvance() && !this.getDayorders().isEmpty() ){
			Order p = this.getDayorders().poll();
			finished = this.getAssemblyline().advance(p);
		}
		this.getOrderManager().finishedOrder(finished);
		this.updateDaySchedule(time);
		if (this.getAvailableTime() == 0)
			this.startNewDay();
	}

	/**
	 * A method that starts a new day.
	 */
	private void startNewDay(){
		if (this.getDelayTime() > 0 )
			this.setAvailableTime(60*14 - this.getDelayTime());
		else 
			this.setAvailableTime(60*14);
		this.setDelayTime(0);
		this.makeDaySchedule();
	}

	/**
	 * A method that makes a new day schedule.
	 */
	private void makeDaySchedule(){
		LinkedList<Order> day = this.getOrderManager().getNbOrders(this.getAvailableTime()/60 - 2);
		this.setDayorders(day);
	}

	public void updateDaySchedule(int time){
		this.setAvailableTime(this.getAvailableTime() - time);
		this.setDelayTime(this.getDelayTime() + (time - 60)); 
		if (this.checkToAddOrder())
			addDayOrder();
		removeLastOrderOfDay();
	}
	
	public void update(){
		if (this.checkToAddOrder())
			addDayOrder();
	}

	public boolean checkToAddOrder() {
		// temp holds the amount of minutes there is left to finish an Order.
		int temp = this.getAvailableTime() - (60 * this.getDayorders().size()) - (2 * 60);
		if (temp/60 > 1)
			return true;
		return false;
	}

	private void addDayOrder() {
		this.getDayorders().add(this.getOrderManager().getPendingOrders().poll());
	}

	private void removeLastOrderOfDay() {
		Order temp = this.getDayorders().pollLast();
		this.getOrderManager().getPendingOrders().addFirst(temp);
	}

	private int getDelayTime() {
		return delay;
	}

	private void setDelayTime(int overlaytime) {
		this.delay = overlaytime;
	}

	private int getAvailableTime() {
		return availableTime;
	}

	private void setAvailableTime(int daytime) {
		this.availableTime = daytime;
	}

	/**
	 * This method returns the assembly line that this production scheduler uses.
	 * 
	 * @return AssemblyLine
	 * 		   The Assembly line that this production scheduler uses.
	 */
	private AssemblyLine getAssemblyline() {
		return assemblyline;
	}

	private void setAssemblyline(AssemblyLine assemblyline) {
		this.assemblyline = assemblyline;
	}

	public Calendar getToday() {
		return today;
	}

	public void setToday(Calendar today) {
		this.today = today;
	}

	private void setDayorders(LinkedList<Order> dayorders) {
		this.dayorders = dayorders;
	}

	protected LinkedList<Order> getScheduledOrders(){
		LinkedList<Order> temp = this.getScheduledOrders();
		temp.addAll(this.getAssemblyline().getWorkPostOrders());
		return temp;
	}
} 
