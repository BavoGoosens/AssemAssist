package businessmodel.scheduler;

import java.util.LinkedList;
import org.joda.time.DateTime;
import businessmodel.exceptions.IllegalSchedulingAlgorithmException;
import businessmodel.order.Order;

// Uses scheduelingAlgorithm to perform scheduling operations.
public class Scheduler {

	/**
	 * A list that holds all the shifts of this Scheduler. It holds at each moment 2 shifts of the current day.
	 */
	private LinkedList<Shift> shifts; 

	/**
	 * A list that holds all the orders of the car manufacturing company.
	 */
	private LinkedList<Order> orders;

	/**
	 * A variable that holds the current Scheduling Algorithm.
	 */
	private SchedulingAlgorithm algo;

	/**
	 * A variable that holds the delay time in minutes for this scheduler.
	 */
	private int delay;

	/**
	 * A Constructor for the class Scheduler. 
	 */
	public Scheduler(){
		this.shifts = new LinkedList<Shift>();
		this.orders = new LinkedList<Order>();
		this.changeAlgorithm("fifo");
		this.setDelay(0);
		this.generateShifts();	
	}

	/**
	 * A method to change the current algorithm that is used for scheduling.
	 * @param	algoname
	 * 			the new scheduling algorithm.
	 * @throws 	IllegalSchedulingAlgorithmException
	 * 			if the given algorithm is not implemented.
	 */
	public void changeAlgorithm(String algoname) throws IllegalSchedulingAlgorithmException{
		if (algoname == null)
			throw new NullPointerException("No scheduling algorithm supplied");
		else if (algoname.equalsIgnoreCase("fifo") || algoname.equalsIgnoreCase("first in first out") )
			this.algo = new FIFO(this);
		else if (algoname.equalsIgnoreCase("sb") || algoname.equalsIgnoreCase("specification batch"))
			this.algo = new SpecificationBatch(this);
		else
			throw new IllegalSchedulingAlgorithmException("The scheduling algorithm was not recognised");
	}

	/**
	 * A method to add a new Order. The Order will be added to the list of orders and scheduled into the system.
	 * @param	order
	 * 			the new order that needs to be scheduled.
	 */
	protected void addOrder(Order order) throws IllegalArgumentException{
		if(canAdOrder(order))
			throw new IllegalArgumentException("Not a valid Order");
		this.getOrders().add(order);
		this.scheduleOrder(order);
	}

	/**
	 * A method to check if this order can be added.
	 * @param	order
	 * 			the order that needs to be added.
	 * @return	true if the order is not null.
	 */
	private boolean canAdOrder(Order order){
		return (order != null);	
	}
	
	/**
	 * A method to schedule an individual Order into the schedule. The estimated completion time is updated.
	 * @param	order
	 * 			the order that needs to be scheduled.
	 */
	private void scheduleOrder(Order order){
		this.getAlgo().scheduleOrder(order);
	}

	/**
	 * A method to generate new shifts for the current day.
	 */
	private void generateShifts(){
		Shift currrentshift = new FreeShift(8);
		Shift endshift = new EndShift(8);
		this.getShifts().add(currrentshift);
		this.getShifts().add(endshift);
	}

	/**
	 * a method to get the orders of the car manufacturing company.
	 * @return	this.orders
	 */
	protected LinkedList<Order> getOrders() {
		return orders;
	}

	/**
	 * A method to get the previous order in the list.
	 * @param 	order
	 * 			the current order.
	 * @return	the previous order of the current order.
	 */
	protected Order getPrevious(Order order){
		int index = this.getOrders().indexOf(order);
		if(index-1 < 0)
			return null;
		else
			return this.getOrders().get(index-1);
	}

	/**
	 * A method to get the next order in the list
	 * @param 	order
	 * 			the current order
	 * @return	the next order in the list.
	 */
	private Order getNext(Order order){
		int index = this.getOrders().indexOf(order);
		if(index+1 > this.getOrders().size())
			return null;
		else
			return this.getOrders().get(index+1);
	}

	/**
	 * A method to get the current scheduling algorithm of this scheduler.
	 * @return	this.algo
	 */
	private SchedulingAlgorithm getAlgo() {
		return this.algo;
	}

	protected int getDelay() {
		return delay;
	}

	/**
	 * A method to set the delay of this scheduler to the given scheduler.
	 * @param delay
	 */
	private void setDelay(int delay) {
		this.delay = delay;
	}

	/**
	 * A method to get the shifts of the current day.
	 * @return	this.shifts
	 */
	protected LinkedList<Shift> getShifts() {
		return this.shifts;
	}
}
