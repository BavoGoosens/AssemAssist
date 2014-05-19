package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.util.IteratorConverter;
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
	 * A method to schedule a new day.
	 * 
	 * The shift are cleared and new orders are added if possible.
	 */
	protected void ScheduleDay(){
		this.dayOrdersCount = 0;
		this.generateShifts();
		this.updateCurrentTime();
		this.getAssemblyline().getMainScheduler().schedulePendingOrders();
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
        checkNewDay();
        updateSchedule();
	}

	/**
	 * Returns true if an order can be added to the current day.
	 * 
	 * @return true if an order can be added.
	 */
	protected boolean canAddOrder(Order order){
		return this.getEstimatedCompletionTimeOfNewOrder(order).isBefore((this.getCurrentTime().withHourOfDay(22)));
	}

	/**
	 * 
	 * @param order
	 */
	public void addOrder(Order order){
		scheduleOrder(order);
		order.setTimestampOfOrder(this.getCurrentTime());
		getOrders().add(order);
		CheckIfAssemblyLineCanAdvance();
		order.setTimestampOfOrder(this.getCurrentTime());
		setEstimatedCompletionDateOfOrder(getPreviousOrder(order), order);
		order.setAssemblyLine(this.getAssemblyline());
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
			throw new IllegalSchedulingAlgorithmException("No scheduling algorithm supplied");}
		else if (algoname.equalsIgnoreCase("fifo") || algoname.equalsIgnoreCase("first in first out") ){
			this.algortime = new FIFO(this);}
		else if (algoname.equalsIgnoreCase("sb") || algoname.equalsIgnoreCase("specification batch")){
			if (options == null) 
				throw new IllegalArgumentException("No such option");
			this.algortime = new SpecificationBatch(this,options);}
		else
			throw new IllegalSchedulingAlgorithmException("The scheduling algorithm was not recognised");	
	}

	private void CheckIfAssemblyLineCanAdvance() {
		if(this.getAssemblyline().canAdvance())
			this.updateAssemblylineStatus();		
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
			this.getAssemblyline().getMainScheduler().schedulePendingOrders();
			this.updateDelay(60);
			this.updateSchedule();
		}
		else if (this.getDelay() >= 60){
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

	private void checkNewDay(){

		if (this.getShifts().isEmpty()) {
			notifyObservers();
			this.ScheduleDay();
		}
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

	/**
	 *Get the number of orders that has been scheduled so far for this day.
	 */
	public int getDayOrdersCount() {
		return this.dayOrdersCount;
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
	public LinkedList<Shift> getShifts() {
		return this.shifts;
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
	 * Method to set an estimated completion time for a particular order.
	 * @param order
	 */
	protected void setEstimatedCompletionDateOfOrder(Order previousorder, Order order){
		if(previousorder != null) 
			order.setEstimatedDeliveryDateOfOrder(previousorder.getEstimatedDeliveryDate().plusMinutes(minutesLastWorkPost(order)));
		else
			order.setEstimatedDeliveryDateOfOrder(this.getCurrentTime().plusMinutes(calculateMinutes(order)));
	}

	protected DateTime getEstimatedCompletionTimeOfNewOrder(Order order) {
		if(this.getOrders().size() > 0)
			return this.getOrders().getLast().getEstimatedDeliveryDate().plusMinutes(
					this.minutesLastWorkPost(order));
		else
			return this.currenttime.plusMinutes(this.calculateMinutes(order));
	}

	public int calculateMinutes(Order order){
        IteratorConverter<WorkPost> converter = new IteratorConverter<>();
		if(order.getVehicleModel() == null)
			return converter.convert(this.getAssemblyline().getWorkPostsIterator()).size()*60;
		int minutes = 0;
		for(WorkPost wp : converter.convert(this.getAssemblyline().getWorkPostsIterator())){
			minutes += wp.getStandardTimeOfModel(order.getVehicleModel());
		}
		return minutes;
	}

	public int minutesLastWorkPost(Order order) {
        IteratorConverter<WorkPost> converter = new IteratorConverter<>();
        if(order.getVehicleModel() == null)
			return 60;
		return converter.convert(this.getAssemblyline().getWorkPostsIterator())
                .get(converter.convert(this.getAssemblyline().getWorkPostsIterator()).size()-1)
                .getStandardTimeOfModel(order.getVehicleModel());
	}


//	//TODO check this shit, moet alle sets teruggeven die in meer dan of gelijk aan 3 orders komen
//	public ArrayList<VehicleOption> getUnscheduledVehicleOptions(int maxNumber){
//
//		HashMap<String, Integer> list = new HashMap<String, Integer>();
//		ArrayList<String> options = new ArrayList<String>();
//		//arraylist vehicleoption
//		HashMap<String, VehicleOption> result = new HashMap<String, VehicleOption>();
//
//		for(Order order: this.getOrders()){
//			for(VehicleOption option: order.getOptions()){
//				if (list.containsKey(option.getName())){
//					int count = list.get(option.getName());
//					list.remove(option.getName());
//					list.put(option.getName(),++count) ;
//				}else {
//					options.add(option.getName());
//					list.put(option.getName(), 1);
//					result.put(option.getName(), option);
//				}
//			}
//		}
//
//		for (String optionName: options)
//			if (list.get(optionName) < 3)
//				result.remove(optionName);
//
//		return new ArrayList<VehicleOption>(result.values());
//	}

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
}
