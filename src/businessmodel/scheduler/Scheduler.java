package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import org.joda.time.DateTime;
import businessmodel.exceptions.IllegalSchedulingAlgorithmException;
import businessmodel.order.Order;

public class Scheduler {

	/**
	 * A list that holds all the shifts of this Scheduler. It holds at each moment 2 shifts of the current day.
	 */
	private ArrayList<Shift> shifts; 

	/**
	 * A list that holds all the orders of the car manufacturing company.
	 */
	private LinkedList<Order> orders;

	/**
	 * A variable that holds the current Scheduling Algorithm.
	 */
	private SchedulingAlgorithm algo;

	/**
	 * A Constructor for the class Scheduler. 
	 */
	public Scheduler(){
		this.shifts = new ArrayList<Shift>();
		this.orders = new LinkedList<Order>();
		this.changeAlgorithm("fifo");
		generateShifts();	
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
	protected void addOrder(Order order){
		if(order == null) 
			throw new IllegalArgumentException();
		this.orders.add(order);
		this.reschedule(order);
	}

	/**
	 * A method that schedules a new day. The shifts are emptied.
	 */
	protected void scheduleNewDay(){
		for(Shift sh: this.getShifts())
			sh.terminate();
		this.generateShifts();
		for (Order order: this.getOrders()){
			reschedule(order);
		}
	}

	/**
	 * A method to get the shifts of the current day.
	 * @return	this.shifts
	 */
	protected ArrayList<Shift> getShifts() {
		return shifts;
	}

	/**
	 * A method to schedule an individual Order into the schedule. The estimated completion time is updated.
	 * @param	order
	 * 			the order that needs to be scheduled.
	 */
	private void reschedule(Order order){
		this.getAlgo().schedule(order);
		this.updateTimeofOrder(order);	
	}

	/**
	 * A method to update the estimated completion time of an order.
	 * @param	order
	 * 			the order for which the completion date needs to be updated.
	 */
	private void updateTimeofOrder(Order order) {
		
		DateTime date = this.getPrevious(order).getEstimateDate();
		if(date == null){
			date = new DateTime();
			date.withHourOfDay(6);
			date.withMinuteOfHour(0);
		}
		else if (date.getHourOfDay() <= 21)
			order.setEstimateDate(date.plusMinutes(60));
		else{
			date.plusHours(8);
			order.setEstimateDate(date.plusMinutes(60));
		}
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
	private LinkedList<Order> getOrders() {
		return orders;
	}

	/**
	 * A method to get the current scheduling algorithm of this scheduler.
	 * @return	this.algo
	 */
	private SchedulingAlgorithm getAlgo() {
		return this.algo;
	}

	// TODO defensief maken IllegalArgumentException denk ik ??
	/**
	 * A method to get the previous order that has been scheduled.
	 * @param 	order
	 * 			the current order that needs to be scheduled.
	 * @return	the previous order that has been scheduled.
	 */
	private Order getPrevious(Order order){
		int index = this.getOrders().indexOf(order);
		if(index-1 < 0)
			return null;
		else
			return this.getOrders().get(index-1);
	}
}
