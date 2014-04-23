package businessmodel.scheduler;

import java.util.LinkedList;

import org.joda.time.DateTime;

import businessmodel.AssemblyLine;
import businessmodel.OrderManager;
import businessmodel.category.CarOption;
import businessmodel.exceptions.IllegalNumberException;
import businessmodel.exceptions.IllegalSchedulingAlgorithmException;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.order.Order;

/**
 * A Class that represents a scheduler for an AssymblyLine.
 * @author	SWOP team 10
 *
 */
public class Scheduler implements Subject {

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
	private SchedulingAlgorithm algortime;

	/**
	 * A variable that holds the delay time in minutes for this scheduler.
	 */
	private int delay;

	/**
	 * The OrderManager that manages this scheduler.
	 */
	private OrderManager ordermanager;

	/**
	 * The AssymblyLine that this Scheduler manages.
	 */
	private AssemblyLine assemblyline;

	/**
	 * A variable that holds the current time;
	 */
	private DateTime currenttime;


	/**
	 * A Constructor for the class Scheduler. 
	 */
	public Scheduler(OrderManager ordermanager){
		this.shifts = new LinkedList<Shift>();
		this.orders = new LinkedList<Order>();
		this.assemblyline = new AssemblyLine(this);
		this.updateCurrentTime();
		this.setOrdermanager(ordermanager);
		this.changeAlgorithm("fifo", null);
		this.setDelay(0);
		this.generateShifts();
	}

	// Deel AssemblyLine

	public void advance(int time) throws IllegalNumberException{
		if (time < 0) throw new IllegalNumberException("Bad time!");
		int delay = time - 60;
		this.getCurrentTime().plusMinutes(time);
		updateAssemblylineStatus();
		updateCompletedOrders();
		updateDelay(delay);
		updateEstimatedTimeOfOrders(delay);
		this.updateSchedule();
	}

	/**
	 * A method to check if the AssymblyLine can advance.
	 * @return true if the AssyemblyLine can advance.
	 */
	public boolean canAdvance(){
		return this.getAssemblyline().canAdvance();
	}

	/**
	 * A method to update the orders of this Scheduler. The completed order is pushed to completed orders and its completion date is set.
	 */
	private void updateCompletedOrders(){
		if(this.getOrders().peekFirst().isCompleted()){
			Order completedorder = this.getOrders().pollFirst();
			completedorder.setCompletionDate(this.getCurrentTime());
			this.getOrdermanager().finishedOrder(completedorder);
		}
	}

	/**
	 * A method to update the delay of this day.
	 * @param	delay
	 * 			the delay that occurred in minutes.
	 */
	private void updateDelay(int delay){
		this.setDelay(this.getDelay()+delay);
	}

	/**
	 * A method to update The Schedule if the delay was to high or to low.
	 */
	public void updateSchedule(){
		
		if(this.getDelay() >= 60){
			
			this.getShifts().getLast().addTimeSlot();
			Order nextorder = this.getNextOrderToSchedule();
			this.scheduleOrder(nextorder);
			this.setDelay(this.getDelay()-60);
			
		}else if (this.getDelay() <= -60){
			
			Order order = this.getShifts().getLast().removeLastTimeSlot();
			this.getOrdermanager().PlaceOrderInFront(order);
			this.setDelay(this.getDelay()+60);
			
		}
	}

	/**
	 * A method to update the status of the AssemblyLine. A new order will be placed on WorkPost 1 and the other orders will advance.
	 */
	private void updateAssemblylineStatus(){
		Order nextorder = this.getShifts().getFirst().getNextOrderForAssemblyLine();
		this.getAssemblyline().advance(nextorder);
		nextorder.setPlacedOnWorkpost(this.getCurrentTime());
	}

	/**
	 * A method to update the estimated completion time of the orders that are currently scheduled.
	 * @param 	delay
	 * 			The delay that occurred last hour.
	 */
	private void updateEstimatedTimeOfOrders(int delay){
		for(Order order: this.getOrders()){
			order.updateEstimatedDate(delay);
		}
	}

	// Deel Scheduler

	public void ScheduleDay(){
		this.updateCurrentTime();
		int size = this.getNumberOfOrdersToSchedule();
		this.getOrders().addAll(this.getAlgo().schedule(this.getOrdermanager().getNbOrders(size)));
		
	} 

	private void updateCurrentTime() {
		DateTime datetemp = new DateTime();
		if(this.currenttime == null){
			this.currenttime = new DateTime(datetemp.getYear(), datetemp.getMonthOfYear(), datetemp.getDayOfMonth(), 8, 0);
		}
		else 
			currenttime = new DateTime(currenttime.getYear(), currenttime.getMonthOfYear(), currenttime.getDayOfMonth(), 8, 0);
		currenttime.plusDays(1);
	}

	private int getNumberOfOrdersToSchedule() {
		return this.getShifts().size()*this.getShifts().getFirst().getTimeSlots().size()-(this.getAssemblyline().getNumberOfWorkPosts()-1);
	}

	protected Order getNextOrderToSchedule(){
		return this.getOrdermanager().getPendingOrders().poll();
	}

	/**
	 * A method to add a new Order. The Order will be added to the list of orders and scheduled into the system.
	 * @param	order
	 * 			the new order that needs to be scheduled.
	 */
	protected void addOrder(Order order) {
		this.getOrders().add(order);
		order.setTimestamp(this.getCurrentTime());
		this.scheduleOrder(order);
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
		Shift endshift = new EndShift(8,this.getAssemblyline().getNumberOfWorkPosts());
		Shift currrentshift = new FreeShift(8,this.getAssemblyline().getNumberOfWorkPosts(), endshift);
		this.getShifts().add(currrentshift);
		this.getShifts().add(endshift);
	}

	/**
	 * A method to change the current algorithm that is used for scheduling.
	 * @param	algoname
	 * 			the new scheduling algorithm.
	 * @throws 	IllegalSchedulingAlgorithmException
	 * 			if the given algorithm is not implemented.
	 */
	public void changeAlgorithm(String algoname, CarOption option) throws IllegalSchedulingAlgorithmException{
		if (algoname == null)
			throw new NullPointerException("No scheduling algorithm supplied");
		else if (algoname.equalsIgnoreCase("fifo") || algoname.equalsIgnoreCase("first in first out") )
			this.algortime = new FIFO(this);
		else if (algoname.equalsIgnoreCase("sb") || algoname.equalsIgnoreCase("specification batch"))
			this.algortime = new SpecificationBatch(this,option );
		else
			throw new IllegalSchedulingAlgorithmException("The scheduling algorithm was not recognised");
	}

	/**
	 * a method to get the orders of the car manufacturing company.
	 * @return	this.orders
	 */
	public LinkedList<Order> getOrders() {
		return this.orders;
	}

	/**
	 * A method to get the previous order in the list.
	 * @param 	order
	 * 			the current order.
	 * @return	the previous order of the current order.
	 */
	protected Order getPreviousOrder(Order order){
		int index = this.getOrders().indexOf(order);
		if(index-1 < 0)
			return null;
		else
			return this.getOrders().get(index-1);
	}


	/**
	 * A method to get the current scheduling algorithm of this scheduler.
	 * @return	this.algo
	 */
	private SchedulingAlgorithm getAlgo() {
		return this.algortime;
	}

	protected int getDelay() {
		return delay;
	}

	/**
	 * A method to set the delay of this scheduler to the given scheduler.
	 * @param	delay
	 * 			the delay of this scheduler.
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

	protected OrderManager getOrdermanager() {
		return this.ordermanager;
	}

	private void setOrdermanager(OrderManager ordermanager) {
		this.ordermanager = ordermanager;
	}

	// TODO private 
	public AssemblyLine getAssemblyline() {
		return assemblyline;
	}

	protected Shift getNextShift(Shift shift){
		int index = this.getShifts().indexOf(shift);
		if(index + 1 >= this.getShifts().size() || this.getShifts().size() < 0)
			return null;
		else
			return this.getShifts().get(index+1);
	}

	public DateTime getCurrentTime(){
		return (DateTime) this.currenttime;
	}

	protected void setEstimatedCompletionDate(Order order){
		Order previousorder = this.getPreviousOrder(order);
		if(previousorder != null) {
			if(previousorder.getEstimateDate() == null){
				order.setEstimateDate(this.getCurrentTime().plusHours(3));
			}else if(previousorder.getEstimateDate().getHourOfDay() <= 21){
				order.setEstimateDate(previousorder.getEstimateDate().plusHours(1));
			}else {
				order.setEstimateDate(previousorder.getEstimateDate().plusDays(1).withHourOfDay(11).withMinuteOfHour(0));
			}
		}else{
			order.setEstimateDate(this.getCurrentTime().plusHours(3));
		}
	}

	@Override
	public void subscribeObserver(Observer o) {
		// TODO Auto-generated method stub
	}

	@Override
	public void unsubscribeObserver(Observer o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyObservers(Object alteredData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub

	}
}
