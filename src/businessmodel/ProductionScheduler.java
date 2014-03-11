package businessmodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
/**
 * A class that is responsible for scheduling in orders.
 * 
 * @author Team 10
 *
 */
public class ProductionScheduler {

	/**
	 * The current delay of the production scheduler in minutes.
	 */
	private int delay = 0;
	
	/**
	 * The number of hours this Scheduler can use today.
	 */
	private int daytime = 16;

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
	public ProductionScheduler(OrderManager ordermanager) {	
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
	private LinkedList<Order> getDayorders() {
		return dayorders;
	}
	
	/**
	 * This method advances the assembly line if possible.
	 */
	public void advance(){
		Order finished = null; 
		if (this.getAssemblyline().canAdvance()){
			Order p = this.dayorders.getFirst();
			finished = this.getAssemblyline().advance(p);
		}
		this.getOrderManager().finishedOrder(finished);
		this.updateDaySchedule();
	}
	
	/**
	 * This method constructs a day schedule.
	 */
	private void makeDaySchedule(){
		this.getOrderManager().getNbOrders(this.getDaytime() - 2);
	}

	/**
	 * 
	 */
	private void updateDaySchedule(){
		if (this.getOverlaytime() > 60 && (this.getDaytime()-this.getCurrenttime()>3)){
			this.setOverlaytime(this.getOverlaytime()-60);
			addDayOrder();
			this.getOrderManager().updateEstimatedTime();
		}
		if (delay < -60 && (this.getDaytime()-this.getCurrenttime() > 2)) {
			this.delay += 60;
			removeLastOrderOfDay();
			this.getOrderManager().updateEstimatedTime();
		}
	}

	/**
	 * 
	 */
	private void removeLastOrderOfDay() {
		Order temp = this.getDayorders().get(this.getDayorders().size()-1);
		this.getOrderManager().getOrders().addFirst(temp);
		this.getDayorders().remove(this.getDayorders().size()-1);
	}

	/**
	 * 
	 */
	private void addDayOrder() {
		this.getDayorders().add(this.getOrderManager().getPendingOrders().getFirst());
		this.getOrderManager().getPendingOrders().removeFirst();
	}

	private int getOverlaytime() {
		return delay;
	}

	private void setOverlaytime(int overlaytime) {
		this.delay = overlaytime;
	}

	private int getDaytime() {
		return daytime;
	}

	private void setDaytime(int daytime) {
		this.daytime = daytime;
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

	private void setDayorders(LinkedList<Order> dayorders) {
		this.dayorders = dayorders;
	}
	
	private LinkedList<Order> getAllOrders(){
		return this.getOrderManager().getOrders();
	}
}
