package businessmodel.scheduler;

import java.util.LinkedList;

import businessmodel.AssemblyLine;
import businessmodel.OrderManager;
import businessmodel.exceptions.IllegalSchedulingAlgorithmException;
import businessmodel.order.Order;

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
	 * A Constructor for the class Scheduler. 
	 */
	public Scheduler(OrderManager ordermanager){
		this.shifts = new LinkedList<Shift>();
		this.orders = new LinkedList<Order>();
		this.assemblyline = new AssemblyLine();
		this.setOrdermanager(ordermanager);
		this.changeAlgorithm("fifo");
		this.setDelay(0);
		this.generateShifts();
	}

	// Deel AssemblyLine

	public boolean canAdvance(){
		return this.getAssemblyline().canAdvance();
	}
	
	public void advance(int time){
		if(!this.canAdvance())
			return;
		int delay = time - 60;
		updateOrders();
		updateAssemblylineStatus();
		updateDelay(delay);
		updateEstimatedTimeOfOrders(delay);
		this.getAlgo().updateSchedule();
	}
	
	private void updateOrders(){
		this.getOrdermanager().finishedOrder(this.getOrders().pollFirst());
	}
	
	private void updateDelay(int delay){
		this.setDelay(this.getDelay()+delay);
	}
	
	private void updateAssemblylineStatus(){
		Order nextorder = this.getShifts().getFirst().getNextOrderForAssemblyLine();
		this.getAssemblyline().advance(nextorder);
	}
	
	private void updateEstimatedTimeOfOrders(int delay){
		for(Order order: this.getOrders()){
			order.getEstimateDate().plusMinutes(delay);
		}
	}

	// Deel Scheduler
	
	public void ScheduleDay(){
		int size = this.getShifts().size()*this.getShifts().getFirst().getTimeSlots().size()-(this.getAssemblyline().getNumberOfWokrkPosts()-1);
		for(Order order: this.getOrdermanager().getNbOrders(size)){
			this.addOrder(order);
		}
	} 

	protected Order getNextOrderToSchedule(){
		return this.getOrdermanager().getPendingOrders().pollFirst();
	}
	
	/**
	 * A method to add a new Order. The Order will be added to the list of orders and scheduled into the system.
	 * @param	order
	 * 			the new order that needs to be scheduled.
	 */
	public void addOrder(Order order) {
		this.getOrders().add(order);
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
		Shift endshift = new EndShift(8,this.getAssemblyline().getNumberOfWokrkPosts());
		Shift currrentshift = new FreeShift(8,this.getAssemblyline().getNumberOfWokrkPosts(), endshift);
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
	public void changeAlgorithm(String algoname) throws IllegalSchedulingAlgorithmException{
		if (algoname == null)
			throw new NullPointerException("No scheduling algorithm supplied");
		else if (algoname.equalsIgnoreCase("fifo") || algoname.equalsIgnoreCase("first in first out") )
			this.algortime = new FIFO(this);
		else if (algoname.equalsIgnoreCase("sb") || algoname.equalsIgnoreCase("specification batch"))
			this.algortime = new SpecificationBatch(this);
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
	protected Order getPrevious(Order order){
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

	private AssemblyLine getAssemblyline() {
		return assemblyline;
	}
	
	protected Shift getNextShift(Shift shift){
		int index = this.getShifts().indexOf(shift);
		if(index + 1 >= this.getShifts().size() || this.getShifts().size() < 0)
			return null;
		else
			return this.getShifts().get(index+1);
	}
}
