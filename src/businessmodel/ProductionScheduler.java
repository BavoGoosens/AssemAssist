package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

import org.joda.time.DateTime;

import businessmodel.exceptions.IllegalNumberException;
import businessmodel.order.Order;


/**
 * A class that is responsible for scheduling orders for an individual day.
 * 
 * @author Team 10
 *
 */
public class ProductionScheduler {

	/**
	 * A variable that holds todays date and current time. 
	 */
	private DateTime today;

	/**
	 * The current delay of the production scheduler in minutes of this day.
	 */
	private int delay = 0;

	/**
	 * The number of minutes this Scheduler can use today.
	 */
	private int availableTime;

	/**
	 * The AssemblyLine for this Scheduler.
	 */
	private AssemblyLine assemblyline;

	/**
	 * The Orders the scheduler will try to handle today.
	 */
	private LinkedList<Order> dayorders;

	/**
	 * The OrderManager that supplies and manages the orders for this Scheduler.
	 */
	private OrderManager ordermanager;

	/**
	 * A method that construct a ProductionScheduler.
	 * 
	 * @param	OrderManager
	 * 			The order manager from which this production scheduler can get it's orders.
	 * 
	 * @param	DateTime
	 * 			The date and time when this scheduler starts.
	 * 
	 * @param 	AssemblyLine
	 * 			the assembly line of this ProductionScheduler.
	 */
	public ProductionScheduler(OrderManager ordermanager, DateTime start,AssemblyLine assemblyline) {	
		this.setToday(start);
		this.setAvailableTime(17*60);
		this.setOrderManager(ordermanager);
		this.setAssemblyline(assemblyline);
		this.setDayOrders(new LinkedList<Order>());
	}

	/**
	 * This method advances the assembly line if possible.
	 * 
	 * @param 	int time
	 * 			An integer representing the time spent during this stage (in minutes). 
	 */
	public void advance(int time) throws IllegalNumberException {
		if (time < 0) throw new IllegalNumberException(time, "Bad time!");
		Order finished = null;
		if (this.getAssemblyline().canAdvance()){
			Order neworder = this.getNextDayOrder();
			finished = this.getAssemblyline().advance(neworder);

			this.updateDaySchedule(time);
			this.setToday(this.getToday().plusMinutes(time));

			if (finished != null){
				this.getOrderManager().finishedOrder(finished);
				this.getDayorders().remove(finished);
			}

			if (this.getAvailableTime() <= 0)
				this.startNewDay();
		}
	}

	/**
	 * This method checks whether there is an Order ready 
	 * 
	 * @return boolean
	 * 		   A boolean which is true when the update was successful.
	 * 		   The boolean is false when there is no more time today.
	 */
	public boolean update(){
		if (this.checkToAddOrder()){
			addDayOrder();
			return true;
		} else 
			return false;
	}

	/**
	 * This method updates the day schedule. 
	 * It calculates the delay and checks if new orders can be fetched or if orders have to be rescheduled.
	 * 
	 * @param 	time
	 * 			The time spent during this phase.	
	 */
	public void updateDaySchedule(int time){
		if (time < 0) throw new IllegalNumberException(time, "Bad time!");
		int timediff = time - 60;
		for(Order or : this.getDayorders()){
			or.setEstimateDate(or.getEstimateDate().plusMinutes(timediff));
		}
		this.setAvailableTime(this.getAvailableTime() - time);
		this.setDelayTime(this.getDelayTime() + timediff); 
		if (this.checkToAddOrder())
			addDayOrder();
		if (checkToRemoveOrder())
			removeLastOrderOfDay();
		this.checkDelaytime();
	}

	/**
	 * A method that adds an order to todays production schedule.
	 */
	private void addDayOrder() {
		Order or = this.getOrderManager().getPendingOrders().poll();
		if (or != null ){
			if(this.getDayorders().isEmpty() || 
					this.getAssemblyline().getWorkPostOrders().contains(this.getDayorders().peekLast())){
				or.setEstimateDate(this.getToday().plusHours(3));
				this.getDayorders().add(or);
			} else {
				or.setEstimateDate(this.getDayorders().peekLast().getEstimateDate().plusMinutes(60));
				this.getDayorders().add(or);
			}
		}
	}

	/**
	 * A method that starts a new day.
	 */
	private void startNewDay(){
		if (this.getDelayTime() > 0 )
			this.setAvailableTime(60*16 - this.getDelayTime());
		else 
			this.setAvailableTime(60*16);
		this.setDelayTime(0);
		DateTime tommorow = this.getToday().plusDays(1).withHourOfDay(6);
		this.setToday(tommorow);
		this.makeDaySchedule();
	}

	/**
	 * A method that makes a new day schedule.
	 */
	public void makeDaySchedule(){
		LinkedList<Order> day = this.getOrderManager().getNbOrders(this.getAvailableTime()/60 - 2);
		this.setDayOrders(day);
		this.addDayOrder();
	}

	/**
	 * A method that checks whether it is possible to schedule in an Order for today.
	 * 
	 * @return boolean
	 * 		   True if the order can be scheduled. False otherwise. 
	 */
	private boolean checkToAddOrder() {
		int temp = this.getAvailableTime() - (60 * this.getDayorders().size()) - (2 * 60);
		if (temp/60 >= 1)
			return true;
		return false;
	}

	/**
	 * A method that checks whether it is necessary to move an Order from today to a later date and time.
	 * 
	 * @return boolean
	 * 		   True if the order can be scheduled. False otherwise. 
	 */
	private boolean checkToRemoveOrder(){
		int temp = this.getAvailableTime() - (60 * this.getDayorders().size()) - (2 * 60);
		if(temp/60 <= -1)
			return true;
		return false;
	}

	/**
	 * A method to update the delay time.
	 */
	private void checkDelaytime() {
		if (this.getDelayTime() > 60){
			this.setDelayTime(this.getDelayTime()-60);
		}
		else if (this.getDelayTime() < -60) {
			this.setDelayTime(this.getDelayTime()+60);
		}
	}

	/**
	 * A method that returns the next unassigned order in todays schedule.
	 * 
	 * @return Order
	 * 		   null if there is no next order. Otherwise the next order in line is returned.
	 */
	private Order getNextDayOrder() {
		for(Order or : this.getDayorders()){
			if (!this.getAssemblyline().getWorkPostOrders().contains(or))
				return or;
		}
		return null;
	}

	/**
	 * A method to remove the last order of this day.
	 */
	private void removeLastOrderOfDay() {
		Order temp = this.getDayorders().pollLast();
		this.getOrderManager().getPendingOrders().addFirst(temp);
	}

	/**
	 * A method that returns the OrderManager for this ProductionScheduler.
	 * 
	 * @return 	OrderManager
	 * 		   	The OrderManager that supplies and manages the orders for this ProductionScheduler.
	 */
	public OrderManager getOrderManager() {
		return this.ordermanager;
	}

	/**
	 *  A method that sets the OrderManager for this ProductionScheduler.
	 *  
	 * @param 	ordermanager
	 * 		  	The OrderManager that supplies and manages the orders for this ProductionScheduler.
	 */
	private void setOrderManager(OrderManager ordermanager) throws IllegalArgumentException {
		if (ordermanager == null) throw new IllegalArgumentException("Bad order manager");
		this.ordermanager = ordermanager;
	}

	/**
	 * This method returns the list of orders that will probably be finished today. 
	 * There might be delays or speedups that cause the list to shrink of grow.
	 * 
	 * @return 	LinkedList<Order>
	 * 		   	A LinkedList with all the pending orders for today.
	 * 
	 */
	protected LinkedList<Order> getDayorders() {
		return this.dayorders;
	}

	/**
	 * A method that sets the list of orders that will be finished today.
	 * 
	 * @param 	dayorders
	 * 			A LinkedList<Order> that will be scheduled and finished today.
	 */
	private void setDayOrders(LinkedList<Order> dayorders) throws IllegalArgumentException {
		if (dayorders == null) throw new IllegalArgumentException("Bad list of day orders!");
		this.dayorders = dayorders;
	}

	/**
	 * A method that returns the number of minutes that can be spend today on the assembly line.
	 *
	 * @return	int 
	 * 			The amount of available minutes this scheduler has left.
	 */
	public int getAvailableTime() {
		return this.availableTime;
	}

	/**
	 * A method that sets the available minutes the scheduler can fill.
	 * 
	 * @param 	daytime
	 * 			An integer with the number of available minutes.
	 */
	private void setAvailableTime(int daytime) {
		this.availableTime = daytime;
	}

	/**Date
	 * This method returns the assembly line that this production scheduler uses.
	 * 
	 * @return 	AssemblyLine
	 * 		   	The Assembly line that this production scheduler uses.
	 */
	public AssemblyLine getAssemblyline() {
		return this.assemblyline;
	}

	/**
	 * This method sets the assembly line that this production scheduler uses.
	 * 
	 * @param	AssemblyLine
	 * 		   	The Assembly line that this production scheduler uses.
	 */
	private void setAssemblyline(AssemblyLine assemblyline) throws IllegalArgumentException {
		if (assemblyline == null) throw new IllegalArgumentException("Bad assembly line!");
		this.assemblyline = assemblyline;
	}

	/**
	 * This method returns the start date and time for this production scheduler.
	 * 
	 * @return 	DateTime
	 * 			The start date and time for this scheduler
	 */
	public DateTime getToday() {
		return this.today;
	}

	/**
	 * This method sets the start date and time for this production scheduler.
	 * 
	 * @param 	today
	 * 			The start date and time for this scheduler
	 */
	public void setToday(DateTime today) throws IllegalArgumentException {
		if (today == null) throw new IllegalArgumentException("Bad date for today!");
		this.today = today;
	}

	/**
	 * A method that returns the amount of delay for this scheduler in minutes.
	 * 
	 * @return	int
	 * 			An integer equal to the amount of delay in minutes for this scheduler.
	 */			
	public int getDelayTime() {
		return this.delay;
	}

	/**
	 * A method that sets the amount of delay for this scheduler in minutes.
	 * 
	 * @param	overlaytime
	 * 			An integer equal to the amount of delay in minutes for this scheduler.
	 */		
	private void setDelayTime(int overlaytime) {
		this.delay = overlaytime;
	}

	/**
	 * A method to get the assembly tasks of the next iteration.
	 * 
	 * @return A list of assembly tasks for the next iteration of the system.
	 */
	//TODO: aanpassen waar deze juist staat
	public ArrayList<AssemblyTask> getFutureAssemblyTasks(){
		ArrayList<AssemblyTask> assemblytasks = new ArrayList<AssemblyTask>();
		Order temp = this.getOrderManager().getPendingOrders().peekFirst();

		for(WorkPost workpost: this.getAssemblyline().getWorkPosts()){
			if(temp != null){
				for(AssemblyTask assem: workpost.possibleAssemblyTasks(temp.getCar().getOptionsClone())){
					assemblytasks.add(assem);
				}
			}
			temp = workpost.getOrder();

		}
		return assemblytasks;
	}
} 
