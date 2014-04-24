package businessmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.joda.time.DateTime;

import businessmodel.category.CarOption;
import businessmodel.exceptions.IllegalNumberException;
import businessmodel.exceptions.IllegalSchedulingAlgorithmException;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.order.Order;

/**
 * A Class that represents a scheduler for an AssymblyLine.
 * @author SWOP team 10
 *
 */
public class Scheduler implements Subject {

	private LinkedList<Shift> shifts; 

	private LinkedList<Order> orders;

	private SchedulingAlgorithm algortime;

	private int delay;

	private OrderManager ordermanager;

	private AssemblyLine assemblyline;

	private ArrayList<Observer> observers;
	
	private int dayOrdersCount = 0;

	protected Scheduler(OrderManager ordermanager){
		this.shifts = new LinkedList<Shift>();
		this.orders = new LinkedList<Order>();
		this.assemblyline = new AssemblyLine(this);
		this.observers = new ArrayList<Observer>();
		this.setOrdermanager(ordermanager);
		this.changeAlgorithm("fifo", null);
		this.setDelay(0);
		this.generateShifts();
	}
	
	public int getDayOrdersCount() {
		return this.dayOrdersCount;
	}

	public boolean canAdvance(){
		return this.getAssemblyline().canAdvance();
	}

	public void advance(int time) throws IllegalNumberException{
		if (time < 0) throw new IllegalNumberException("Bad time!");
		int delay = time - 60;
		DateTime currenttime = this.getCurrentTime().plusMinutes(time);
		SystemTimer.updateTime(this, currenttime);
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

	public boolean canAddOrder(){
		int count = 0;
		for(Shift shift: this.getShifts())
			count += shift.getTimeSlots().size();
		count = count -2;
		return this.getOrders().size() < count;
	}

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

	public int getNumberOfOrdersToSchedule() {
		return this.getShifts().size()*this.getShifts().getFirst().getTimeSlots().size()-(this.getAssemblyline().getNumberOfWorkPosts()-1);
	}

	public void changeAlgorithm(String algoname, CarOption option) throws IllegalSchedulingAlgorithmException, IllegalArgumentException{

		if (algoname == null){
			throw new NullPointerException("No scheduling algorithm supplied");
		}else if (algoname.equalsIgnoreCase("fifo") || algoname.equalsIgnoreCase("first in first out") ){
			this.algortime = new FIFO(this);
		}else if (algoname.equalsIgnoreCase("sb") || algoname.equalsIgnoreCase("specification batch")){

			if (option == null) throw new IllegalArgumentException("No such option");
			if (!this.checkOptionsForSpecificationBatch(option)) throw new IllegalArgumentException("Too little orders with that option ( less than 3 )");
			
			this.algortime = new SpecificationBatch(this,option);
		}else{
			throw new IllegalSchedulingAlgorithmException("The scheduling algorithm was not recognised");
		}
	}

	public void ScheduleDay(){
		this.dayOrdersCount = 0;
		this.generateShifts();
		this.updateCurrentTime();
		int size = this.getNumberOfOrdersToSchedule();
		for(Order order: this.getOrdermanager().getNbOrders(size)){
			this.addOrderToSchedule(order);
			this.getOrders().add(order);
		}
	}

	// for testing
	@SuppressWarnings("unchecked")
	public LinkedList<Order> getOrdersClone(){
		return (LinkedList<Order>) this.orders.clone();
	}

	public AssemblyLine getAssemblyline() {
		return assemblyline;
	}

	public DateTime getCurrentTime(){
		return SystemTimer.getCurrenTime();
	}

	protected LinkedList<Order> getOrders() {
		return this.orders;
	}

	protected LinkedList<Shift> getShifts() {
		return this.shifts;
	}

	protected Order getPreviousOrder(Order order){
		int index = this.getOrders().indexOf(order);
		if(index-1 < 0)
			return null;
		else
			return this.getOrders().get(index-1);
	}

	protected int getDelay() {
		return delay;
	}

	protected OrderManager getOrdermanager() {
		return this.ordermanager;
	}

	protected Shift getNextShift(Shift shift){
		int index = this.getShifts().indexOf(shift);
		if(index + 1 >= this.getShifts().size() || this.getShifts().size() < 0)
			return null;
		else
			return this.getShifts().get(index+1);
	}

	protected Order getNextOrderToSchedule(){
		return this.getOrdermanager().getPendingOrders().poll();
	}

	private void updateCompletedOrders(){
		if(this.getOrders().peekFirst()!= null && this.getOrders().peekFirst().isCompleted()){
			Order completedorder = this.getOrders().pollFirst();
			completedorder.setCompletionDateOfOrder(this.getCurrentTime());
			this.getOrdermanager().finishedOrder(completedorder);
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
				this.getOrdermanager().placeOrderInFront(order);
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
			nextorder.setPlacedOnWorkpostOfOrder(this.getCurrentTime());
	}

	private void updateEstimatedTimeOfOrders(int delay){
		for(Order order: this.getOrders()){
			order.updateEstimatedDate(delay);
		}
	}

	private void updateCurrentTime() {
		DateTime currenttime = new DateTime(SystemTimer.getCurrenTime().getYear(), 
				SystemTimer.getCurrenTime().getMonthOfYear(), 
				SystemTimer.getCurrenTime().getDayOfMonth(), 8, 0);
		currenttime = currenttime.plusDays(1);
		SystemTimer.updateTime(this, currenttime);
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

	private boolean checkOptionsForSpecificationBatch(CarOption option) {

		int count = 0;
		for(Order order: this.getOrders())
			if (order.getOptions().toString().contains(option.toString()))
				count++;

		if (count < 3)
			return false;
		return true;

	}
	
	private void setOrdermanager(OrderManager ordermanager) throws IllegalArgumentException{
		if(ordermanager == null)
			throw new IllegalArgumentException("Not an ordermanager");
		this.ordermanager = ordermanager;
	}

	/**
	 * Method to check if a CarOption occurs in more than 3 orders
	 * @param maxNumber
	 * @return
	 */
	public ArrayList<CarOption> getUnscheduledCarOptions(int maxNumber){

		HashMap<String, Integer> list = new HashMap<String, Integer>();
		ArrayList<String> options = new ArrayList<String>();
		HashMap<String, CarOption> result = new HashMap<String, CarOption>();

		for(Order order: this.getOrders()){
			for(CarOption option: order.getOptions()){
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


		return new ArrayList<CarOption>(result.values());

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
}
