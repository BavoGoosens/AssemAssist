package businessmodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

import component.*;

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
	 * The Orders the scheduler will try to handle today.
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
		ArrayList<Action> actions = this.makeActions();
		this.setAssemblyline(new AssemblyLine(actions));
		this.setDayOrders(new LinkedList<Order>());
	}

	/**
	 * This method advances the assembly line if possible.
	 */
	public void advance(int time){
		Order finished = null;
		if (this.getAssemblyline().canAdvance()){
			Order neworder = this.getNextDayOrder();
			finished = this.getAssemblyline().advance(neworder);
		}
		this.updateDaySchedule(time);
		if (finished != null){
			this.getOrderManager().finishedOrder(finished);
			this.getDayorders().remove(finished);
			this.updateDaySchedule(time);
			if (this.getAvailableTime() == 0)
				this.startNewDay();
		}
		
	}

	/**
	 * A method that returns the next unscheduled order in todays schedule.
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
	public void makeDaySchedule(){
		LinkedList<Order> day = this.getOrderManager().getNbOrders(this.getAvailableTime()/60 - 2);
		this.setDayOrders(day);
		this.addDayOrder();
	}

	public void updateDaySchedule(int time){
		int timediff = time - 60;
		for(Order or : this.getDayorders()){
			or.getDate().add(Calendar.MINUTE, timediff);
		}
		this.setAvailableTime(this.getAvailableTime() - time);
		this.setDelayTime(this.getDelayTime() + timediff); 
		if (this.checkToAddOrder())
			addDayOrder();
		removeLastOrderOfDay();
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
	 * A method that checks whether it is possible to schedule in an Order for today.
	 * 
	 * @return boolean
	 * 		   True if the order can be scheduled. False otherwise. 
	 */
	public boolean checkToAddOrder() {
		int temp = this.getAvailableTime() - (60 * this.getDayorders().size()) - (2 * 60);
		if (temp/60 >= 1)
			return true;
		return false;
	}

	/**
	 * A method that adds an order to todays production schedule.
	 */
	private void addDayOrder() {
		Order or = this.getOrderManager().getPendingOrders().poll();
		if (or != null ){
			if(this.getDayorders().isEmpty() || 
					this.getAssemblyline().getWorkPostOrders().contains(this.getDayorders().peekLast())){
				Calendar copy = (Calendar) this.getToday().clone();
				copy.add(Calendar.HOUR_OF_DAY, 3);
				or.setDate(copy);
				this.getDayorders().add(or);
			} else {
				Calendar copy = (Calendar) this.getDayorders().peekLast().getDate().clone();
				copy.add(Calendar.HOUR_OF_DAY, 1);
				or.setDate(copy);
				this.getDayorders().add(or);
			}
		}
		this.checkDelaytime();
	}
	
	/**
	 * A method to update the delay time.
	 */
	private void checkDelaytime() {
		if (this.getDelayTime() > 60){
			this.setDelayTime(this.getDelayTime()-60);
			this.getOrderManager().updateEstimatedTime(-60);
		}
		else if (this.getDelayTime() < -60) {
			this.setDelayTime(this.getDelayTime()+60);
			this.getOrderManager().updateEstimatedTime(60);;		
		}
	}

	/**
	 * A method to remove the last order of this day.
	 */
	private void removeLastOrderOfDay() {
		Order temp = this.getDayorders().pollLast();
		this.getOrderManager().getPendingOrders().addFirst(temp);
		this.getOrderManager().updateEstimatedTime(-60);
	}

	public int getDelayTime() {
		return delay;
	}

	/**
	 * A method that returns the OrderManager for this ProductionScheduler.
	 * 
	 * @return OrderManager
	 * 		   The OrderManager that supplies and manages the orders for this ProductionScheduler.
	 */
	public OrderManager getOrderManager() {
		return ordermanager;
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

	public int getAvailableTime() {
		return availableTime;
	}

	/**
	 * This method returns the assembly line that this production scheduler uses.
	 * 
	 * @return AssemblyLine
	 * 		   The Assembly line that this production scheduler uses.
	 */
	public AssemblyLine getAssemblyline() {
		return assemblyline;
	}

	public Calendar getToday() {
		return today;
	}

	public LinkedList<Order> getScheduledOrders(){
		LinkedList<Order> temp = this.getDayorders();
		temp.addAll(this.getAssemblyline().getWorkPostOrders());
		return temp;
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

	private void setDelayTime(int overlaytime) {
		this.delay = overlaytime;
	}

	private void setAvailableTime(int daytime) {
		this.availableTime = daytime;
	}

	private void setAssemblyline(AssemblyLine assemblyline) {
		this.assemblyline = assemblyline;
	}

	public void setToday(Calendar today) {
		this.today = today;
	}

	private void setDayOrders(LinkedList<Order> dayorders) {
		this.dayorders = dayorders;
	}

	private ArrayList<Action> makeActions() {
		Action action1 = new Action("Paint car");
		Action action2 = new Action("Assembly Car Body");
		Action action3 = new Action("Insert engine");
		Action action4 = new Action("Insert gearbox");
		Action action5 = new Action("Install seats");
		Action action6 = new Action("Install Airco");
		Action action7 = new Action("Mount Wheels");
		action1.addComponent(new Body());
		action2.addComponent(new Color());
		action3.addComponent(component);
		action4.addComponent(component);
		action5.addComponent(component);
		action6.addComponent(component);
		action7.addComponent(component);


		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(action1);
		actions.add(action2);
		actions.add(action3);
		actions.add(action4);
		actions.add(action5);
		actions.add(action6);
		actions.add(action7);
		return actions;
	}
} 
