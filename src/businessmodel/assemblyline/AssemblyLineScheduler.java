package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.joda.time.DateTime;

import businessmodel.category.VehicleOption;
import businessmodel.exceptions.IllegalNumberException;
import businessmodel.exceptions.IllegalSchedulingAlgorithmException;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.order.Order;

/**
 * A Class that represents a assemblyline for an AssymblyLine.
 * It makes a schedule for the assemblyLine.
 * When the schedule has been complete a new day schedule is created
 * @author SWOP team 10
 *
 */
public class AssemblyLineScheduler implements Subject {

	private LinkedList<Shift> shifts;

	private LinkedList<Order> orders;

	private SchedulingAlgorithm algortime;

	private int delay;

	private DateTime currenttime;

	private AssemblyLine assemblyline;

	private ArrayList<Observer> observers;

	private int dayOrdersCount = 0;

	/**
	 * A new new AssemblyLineScheduler is created.
	 * Shifts are created and the standard algorithm is set.
	 */
	protected AssemblyLineScheduler(AssemblyLine assemblyline){
		this.shifts = new LinkedList<Shift>();
		this.orders = new LinkedList<Order>();
		this.assemblyline = assemblyline;
		this.observers = new ArrayList<Observer>();
		this.currenttime = new DateTime(new DateTime().getYear(), 
				new DateTime().getMonthOfYear(),
				new DateTime().getDayOfMonth(), 8, 0);
		this.changeAlgorithm("fifo", null);
		this.setDelay(0);
		this.generateShifts();
	}

	/**
	 *Get the number of orders that has been scheduled so far for this day.
	 */
	public int getDayOrdersCount() {
		return this.dayOrdersCount;
	}

	/**
	 * A method to schedule a new day.
	 * 
	 * The shift are cleared and new orders are added if possible.
	 */
	public void ScheduleDay(){
		this.dayOrdersCount = 0;
		this.generateShifts();
		this.updateCurrentTime();
		int size = this.getNumberOfOrdersToSchedule();
		for(Order order:this.getAssemblyline().getMainScheduler().getNbOrders(size, this.getAssemblyline())){
			this.addOrderToSchedule(order);
		}
	}

	// for testing
	@SuppressWarnings("unchecked")
	/**
	 * Get a clone of the current orders in the schedule.
	 * @return a clone of the current orders.
	 */
	// TODO: werken met iterators
	public LinkedList<Order> getOrdersClone(){
		return (LinkedList<Order>) this.orders.clone();
	}

	/**
	 * Return the AssemblyiLne of this assemblyline.
	 * 
	 * @return the AssemblyLine of this assemblyline.
	 */
	public AssemblyLine getAssemblyline() {
		return assemblyline;
	}

	/**
	 * A method to get the current time of this AssemblyLineScheduler.
	 * 
	 * @return
	 */
	public DateTime getCurrentTime(){
		return this.currenttime;
	}

	/**
	 * Returns true is the AssemblyLine can advance.
	 * 
	 * @return true if the AssemblyLine can advance.
	 */
	protected boolean canAdvance(){
		return this.getAssemblyline().canAdvance();
	}

	/**
	 * The AssemblyLine is advanced.
	 * The delay is updated and the estimated time is updated.
	 * 
	 * @param 	time
	 * 			the time spent on last AssemblyLine status.
	 * @throws 	IllegalNumberException
	 * 			if(time < 0)
	 */
	protected void advance(int time) throws IllegalNumberException{
		if (time < 0) 
			throw new IllegalNumberException("Bad time!");
		int delay = time - 60;
		this.currenttime = this.getCurrentTime().plusMinutes(time);
		updateAssemblylineStatus();
		updateCompletedOrders();
		updateDelay(delay);
		updateEstimatedTimeOfOrders(delay);
		this.updateSchedule();
		if (this.getShifts().size() == 0) {
			notifyObservers();
			this.ScheduleDay();
		}
	}

	/**
	 * Returns true if an order can be added to the current day.
	 * 
	 * @return true if an order can be added.
	 */
	public boolean canAddOrder(){
		int count = 0;
		for(Shift shift: this.getShifts())
			count += shift.getTimeSlots().size();
		count = count -2;
		return this.getOrders().size() < count;
	}

	/**
	 * Add this order to the schedule of this day.
	 * the timestamp of this order is set. This is the time that the order is scheduled.
	 * If it is the first order it will be placed on the AssemblyLine.
	 * @param 	order
	 * 			the order that needs to be added.
	 */
	public void addOrderToSchedule(Order order){
		this.getAlgo().scheduleOrder(order);
		order.setTimestampOfOrder(this.getCurrentTime());
		this.getOrders().add(order);
		boolean advance = true;
		for(WorkPost wp : this.getAssemblyline().getWorkPosts()){
			if(wp.getOrder() != null)
				advance = false;
		}
		if(advance)
			this.updateAssemblylineStatus();
	}

	/**
	 * returns the amount  of orders that can be scheduled for this day
	 * 
	 * @return the number of order that can be scheduled.
	 */
	public int getNumberOfOrdersToSchedule() {
		return this.getShifts().size()*this.getShifts().getFirst().getTimeSlots().size()-(this.getAssemblyline().getNumberOfWorkPosts()-1);
	}

	/**
	 * A method to change the current algorithm.
	 * 
	 * @param 	algoname
	 * 			the name of the new algorithm.
	 * @param 	option
	 * 			
	 * @throws 	IllegalSchedulingAlgorithmException
	 * 
	 * @throws 	IllegalArgumentException
	 */
	public void changeAlgorithm(String algoname, ArrayList<VehicleOption> options) throws IllegalSchedulingAlgorithmException, IllegalArgumentException{

		if (algoname == null){
			throw new NullPointerException("No scheduling algorithm supplied");
		}else if (algoname.equalsIgnoreCase("fifo") || algoname.equalsIgnoreCase("first in first out") ){
			this.algortime = new FIFO(this);
		}else if (algoname.equalsIgnoreCase("sb") || algoname.equalsIgnoreCase("specification batch")){

			if (options == null) throw new IllegalArgumentException("No such option");
			if (!this.checkOptionsForSpecificationBatch(options)) throw new IllegalArgumentException("Too little orders with that option ( less than 3 )");

			this.algortime = new SpecificationBatch(this,options);

		}else{
			throw new IllegalSchedulingAlgorithmException("The scheduling algorithm was not recognised");
		}
	}

	/**
	 * Returns the current orders of this assemblyline.
	 * 
	 * @return
	 */
	public LinkedList<Order> getOrders() {
		return this.orders;
	}

	/**
	 * Returns the shifts of this assemblyline.
	 * 
	 * @return the shift of this assemblyline.
	 */
	// TODO protected maken
	public LinkedList<Shift> getShifts() {
		return this.shifts;
	}

	/** 
	 * A method to get the previous order that had been scheduled.
	 * 
	 * @param 	order
	 * 			the current order.
	 * @return	the previous order
	 */
	protected Order getPreviousOrder(Order order){
		int index = this.getOrders().indexOf(order);
		if(index-1 < 0)
			return null;
		else
			return this.getOrders().get(index-1);
	}

	/**
	 * A method that returns the current delay of this day assemblyline.
	 * 
	 * @return
	 */
	protected int getDelay() {
		return delay;
	}

	/**
	 * Returns the next shift of the given shift.
	 * 
	 * @param 	shift
	 * 			the shift you want the next shift from.
	 * @return	the next shift
	 */
	protected Shift getNextShift(Shift shift){
		int index = this.getShifts().indexOf(shift);
		if(index + 1 >= this.getShifts().size() || this.getShifts().size() < 0)
			return null;
		else
			return this.getShifts().get(index+1);
	}

	/**
	 * Returns the next order that needs to be scheduled.
	 * 
	 * @return
	 */
	// TODO rekening houden met de singleTaskOrders
	protected Order getNextOrderToSchedule(){
		return this.getAssemblyline().getMainScheduler().getPendingOrders().poll();
	}

	private void updateCompletedOrders(){
		if(this.getOrders().peekFirst()!= null && this.getOrders().peekFirst().isCompleted()){
			Order completedorder = this.getOrders().pollFirst();
			completedorder.setCompletionDateOfOrder(this.getCurrentTime());
			this.getAssemblyline().getMainScheduler().finishedOrder(completedorder);
			this.dayOrdersCount++;
		}
	}

	private void updateSchedule(){

		if(this.getDelay() <= -60){

			this.getShifts().getLast().addTimeSlot();
			Order nextorder = this.getNextOrderToSchedule();
			if(nextorder!= null)
				this.scheduleOrder(nextorder);
			this.updateDelay(60);
			this.updateSchedule();

		}else if (this.getDelay() >= 60){

			Order order = this.getShifts().getLast().removeLastTimeSlot();
			if(this.getShifts().getLast().getTimeSlots().size() == 0)
				this.getShifts().removeLast();
			if(order!= null)
				this.getAssemblyline().getMainScheduler().placeOrderInFront(order);
			this.updateDelay(-60);
			this.updateSchedule();
		}
	}

	private void updateDelay(int delay){
		this.setDelay(this.getDelay()+delay);
	}

	private void updateAssemblylineStatus(){
		Order nextorder = this.getShifts().getFirst().getNextOrderForAssemblyLine();
		if(this.getShifts().getFirst().getTimeSlots().size() == 0)
			this.getShifts().removeFirst();
		this.getAssemblyline().advance(nextorder);
		if(nextorder != null)
			nextorder.setPlacedOnAssemblyLineOfOrder(this.getCurrentTime());
	}

	private void updateEstimatedTimeOfOrders(int delay){
		for(Order order: this.getOrders()){
			order.updateEstimatedDate(delay);
		}
	}

	private void updateCurrentTime() {
		DateTime currenttime = new DateTime(this.getCurrentTime().getYear(), 
				getCurrentTime().getMonthOfYear(), 
				getCurrentTime().getDayOfMonth(), 8, 0);
		currenttime = currenttime.plusDays(1);
		this.setCurrentTime(currenttime);
	}

	private void setCurrentTime(DateTime currenttime) {
		this.currenttime =currenttime;
	}

	private void scheduleOrder(Order order){
		this.getAlgo().scheduleOrder(order);
	}

	protected void generateShifts(){
		this.getShifts().clear();
		Shift endshift = new EndShift(8,this.getAssemblyline().getNumberOfWorkPosts());
		Shift currrentshift = new FreeShift(8,this.getAssemblyline().getNumberOfWorkPosts(), endshift);
		this.getShifts().add(currrentshift);
		this.getShifts().add(endshift);
	}

	private SchedulingAlgorithm getAlgo() {
		return this.algortime;
	}

	private void setDelay(int delay) {
		this.delay = delay;
	}

	//TODO
	private boolean checkOptionsForSpecificationBatch(ArrayList<VehicleOption> options) {
		
		int orderCount = 0;
		
		for(Order order: this.getOrders()){
			int count = 0;
			for(VehicleOption opt: options){
				for(VehicleOption opt2: order.getOptions()){
					if (opt.toString().equals(opt2.toString())) count++;
				}
			}
			if (count == order.getOptions().size()) orderCount++;
		}
		
		if (orderCount < 3)
			return false;
		return true;
	}

	/**
	 * Method to check if a VehicleOption occurs in more than 3 orders
	 * @param maxNumber
	 * @return
	 */
	//TODO check this shit, moet alle sets teruggeven die in meer dan of gelijk aan 3 orders komen
	public ArrayList<VehicleOption> getUnscheduledVehicleOptions(int maxNumber){
		
		HashMap<String, Integer> list = new HashMap<String, Integer>();
		ArrayList<String> options = new ArrayList<String>();
		//arraylist vehicleoption
		HashMap<String, VehicleOption> result = new HashMap<String, VehicleOption>();
		
		for(Order order: this.getOrders()){
			for(VehicleOption option: order.getOptions()){
				if (list.containsKey(option.getName())){
					int count = list.get(option.getName());
					list.remove(option.getName());
					list.put(option.getName(),++count) ;
				}else {
					options.add(option.getName());
					list.put(option.getName(), 1);
					result.put(option.getName(), option);
				}
			}
		}
		
		for (String optionName: options)
			if (list.get(optionName) < 3)
				result.remove(optionName);
		
		return new ArrayList<VehicleOption>(result.values());
	}

	@Override
	public void subscribeObserver(Observer observer) throws IllegalArgumentException {
		if (observer == null) throw new IllegalArgumentException("Bad observer!");
		this.observers.add(observer);
	}

	@Override
	public void unsubscribeObserver(Observer observer) throws IllegalArgumentException {
		if (observer == null) throw new IllegalArgumentException("Bad observer!");
		this.observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer: this.observers) {
			observer.update(this);
		}
	}

	public String currentAlgoDescription() {
		String[] full = this.algortime.getClass().getName().split("\\.");
		return full[1];
	}

	// TODO rekening houden met de Standard Completion Date
	protected DateTime getEstimatedCompletionTimeOfNewOrder(Order order) {
		if(this.getOrders().size() > 0)
			return this.getOrders().getLast().getEstimatedDeliveryDate().plusHours(1);
		else
			return this.currenttime.plusHours(3);
	}
}
