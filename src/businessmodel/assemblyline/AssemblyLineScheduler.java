package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import businessmodel.util.IteratorConverter;
import businessmodel.util.SafeIterator;
import businessmodel.util.Tuple;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;

import businessmodel.category.VehicleOption;
import businessmodel.exceptions.IllegalNumberException;
import businessmodel.exceptions.IllegalSchedulingAlgorithmException;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.order.Order;

/**
 * A Class that represents a assemblyLine for an AssymblyLine.
 * It makes a schedule for the assemblyLine.
 * When the schedule has been complete a new day schedule is created
 * @author SWOP team 10
 *
 */
public class AssemblyLineScheduler implements Subject {

	private LinkedList<Shift> shifts;

	private LinkedList<Order> orders;

	private SchedulingAlgorithm algorithm;

	private int delay;

	private DateTime currentTime;

	private AssemblyLine assemblyLine;

	private ArrayList<Observer> observers;

	private int dayOrdersCount = 0;

	/**
	 * A new new AssemblyLineScheduler is created.
	 * Shifts are created and the standard algorithm is set.
	 */
	protected AssemblyLineScheduler(AssemblyLine assemblyLine){
		this.shifts = new LinkedList<Shift>();
		this.orders = new LinkedList<Order>();
		this.assemblyLine = assemblyLine;
		this.observers = new ArrayList<Observer>();
		this.currentTime = new DateTime(new DateTime().getYear(),
				new DateTime().getMonthOfYear(),
				new DateTime().getDayOfMonth(), 6, 0);
		this.changeAlgorithm("fifo", null);
		this.setDelay(0);
		this.generateShifts();
	}

	/**
	 * A method to schedule a new day.
	 *
	 * The shift are cleared and new orders are added if possible.
	 */
	public void scheduleNewDay(){
		this.notifyObservers();
		this.dayOrdersCount = 0;
		this.generateShifts();
		this.setDelay(0);
		this.updateNewDayDate();
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
		this.currentTime = this.getCurrentTime().plusMinutes(time);
		updateAssemblyLineStatus();
		updateCompletedOrders();
		updateDelay(delay);
		updateEstimatedTimeOfOrders(delay);
		updateSchedule();
	}

    private int getMaximumExpectedTimeOnWorkPosts(){
        int time = 0;
        IteratorConverter<WorkPost> converter = new IteratorConverter<WorkPost>();
        for(WorkPost  wp : converter.convert(getAssemblyLine().getWorkPostsIterator())){
          if(wp.getOrder() != null && wp.getStandardTimeOfModel(wp.getOrder().getVehicleModel()) > time)
              time = wp.getStandardTimeOfModel(wp.getOrder().getVehicleModel());
        }
        return time;
    }
	/**
	 * Returns true if an order can be added to the current day.
	 *
	 * @return true if an order can be added.
	 */
	protected boolean canAddOrder(Order order){
        DateTime date =  this.getCurrentTime().withHourOfDay(22);
		return this.getEstimatedCompletionTimeOfNewOrder(order).isBefore(date.withMinuteOfHour(0));
	}

	/**
	 *  This method tries to add the given order to the assembly line
     *  according to the rules defined by the scheduling algorithm.
     *
	 * @param   order
     *                  The order you want to schedule in.
	 */
	public void addOrder(Order order){
        // try to schedule in the order according to the algorithm.
        // true if it worked
        // false otherwise
		boolean bool = scheduleOrder(order);
		if(bool){
            // if the order is scheduled in, add it to the queue.
            getOrders().add(order);
		    checkIfAssemblyLineCanAdvance();
		    setEstimatedCompletionDateOfOrder(getPreviousOrder(order), order);
           
        } else {
            // if it was not possible to schedule the order at this time, add the order
            // to the pending queue of the order manager.
            this.getAssemblyLine().getMainScheduler().orderCannotBePlaced(order);
        }
	}

	/**
	 * A method to change the current algorithm.
	 *
	 * @param 	algoName
	 * 			the name of the new algorithm.
	 * @param 	options
	 *
	 * @throws 	IllegalSchedulingAlgorithmException
	 *
	 * @throws 	IllegalArgumentException
	 */
	public void changeAlgorithm(String algoName, ArrayList<VehicleOption> options) throws IllegalSchedulingAlgorithmException, IllegalArgumentException{
		if (algoName == null){
			throw new IllegalSchedulingAlgorithmException("No scheduling algorithm supplied");}
		else if (algoName.equalsIgnoreCase("FIFO") || algoName.equalsIgnoreCase("first in first out") ){
			this.algorithm = new FIFO(this);}
		else if (algoName.equalsIgnoreCase("SpecificationBatch") || algoName.equalsIgnoreCase("specification batch")){
			if (options == null)
				throw new IllegalArgumentException("No such option");
			this.algorithm = new SpecificationBatch(this,options);}
		else
			throw new IllegalSchedulingAlgorithmException("The scheduling algorithm was not recognised");
	}

	protected void checkIfAssemblyLineCanAdvance() {
		if(this.getAssemblyLine().canAdvance())
			this.updateAssemblyLineStatus();
	}

	private void updateCompletedOrders(){
		if(this.getOrders().peekFirst()!= null && this.getOrders().peekFirst().isCompleted()){
			Order completedOrder = this.getOrders().pollFirst();
			completedOrder.setCompletionDateOfOrder(this.getCurrentTime());
			this.getAssemblyLine().getMainScheduler().finishedOrder(completedOrder, this.calculateDelay(completedOrder));
			this.dayOrdersCount++;
		}
	}
	
	private int calculateDelay(Order order) {
		Period period = new Period(order.getOrderPlacedOnAssemblyLine(), order.getCompletionDate());
		Period standardPeriod = new Period().withMinutes(this.calculateMinutes(order));
		Period delay = period.minus(standardPeriod);
		return delay.getDays()*24*60+delay.getHours()*60+delay.getMinutes();
	}

    //TODO
	private void updateSchedule(){
        checkNewDay();
		if(this.getDelay() <= -60){
			this.getShifts().getLast().addTimeSlot();
			this.getAssemblyLine().getMainScheduler().schedulePendingOrders();
			this.updateDelay(60);
			this.updateSchedule();
		}
		else if (this.getDelay() >= 60){
			if(this.getShifts().size() != 0){
				Order order = this.getShifts().getLast().removeLastTimeSlot();
				if(this.getShifts().getLast().getTimeSlots().size() == 0)
					this.getShifts().removeLast();
				if(order!= null)
					this.getAssemblyLine().getMainScheduler().placeOrderInFront(order);
				this.updateDelay(-60);
				this.updateSchedule();
			}
		}
	}

	private void updateDelay(int delay){
		this.setDelay(this.getDelay()+delay);
	}

	private void updateAssemblyLineStatus(){
        Order nextOrder = null;
        if(!this.getShifts().isEmpty()){
              nextOrder = this.getShifts().getFirst().getNextOrderForAssemblyLine();
              if(this.getShifts().getFirst().getTimeSlots().size() == 0)
			        this.getShifts().removeFirst();
        }
		this.getAssemblyLine().advance(nextOrder);
		if(nextOrder != null)
			nextOrder.setPlacedOnAssemblyLineOfOrder(this.getCurrentTime());
	}

	private void updateEstimatedTimeOfOrders(int delay){
        if(!this.getOrders().isEmpty()){
            Order firstOrder = this.getOrders().getFirst();
            firstOrder.setEstimatedDeliveryDateOfOrder(this.getCurrentTime().plusMinutes(this.minutesLastWorkPost(firstOrder)));
            boolean first = true;
            for (Order order : this.getOrders()) {
                if (first) {
                    first = false;
                } else {
                    try {
                        order.setEstimatedDeliveryDateOfOrder(this.getPreviousOrder(order).getEstimatedDeliveryDate()
                                .plusMinutes(this.minutesLastWorkPost(order)));
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

	private void updateNewDayDate() {
		DateTime currentTime = new DateTime(this.getCurrentTime().getYear(),
				getCurrentTime().getMonthOfYear(),
				getCurrentTime().getDayOfMonth(), 6, 0);
		currentTime = currentTime.plusDays(1);
		this.setCurrentTime(currentTime);
	}

	public void checkNewDay(){
		if (this.getShifts().isEmpty() || (this.getCurrentTime().getHourOfDay() >= 22 &&
                this.getCurrentTime().getMinuteOfHour() >= 0) ) {
			if(this.getAssemblyLine().canAdvance()){
                this.getAssemblyLine().getMainScheduler().startNewProductionDay();
			}
		}
	}

    public boolean couldStartNewDay() {
        // if there are no more pending orders
        // and the assembly line is empty
        return this.orders.isEmpty() && this.getAssemblyLine().getWorkPostOrders().isEmpty();
    }

	protected void setCurrentTime(DateTime currenttime) {
		this.currentTime =currenttime;
	}

	private boolean scheduleOrder(Order order){
		return this.getAlgo().scheduleOrder(order);
	}

	protected void generateShifts(){
		this.getShifts().clear();
		Shift endshift = new EndShift(8,this.getAssemblyLine().getNumberOfWorkPosts());
		Shift currrentshift = new FreeShift(8,this.getAssemblyLine().getNumberOfWorkPosts(), endshift);
		this.getShifts().add(currrentshift);
		this.getShifts().add(endshift);
	}

	private SchedulingAlgorithm getAlgo() {
		return this.algorithm;
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
	 * Return the AssemblyiLne of this assemblyLine.
	 *
	 * @return the AssemblyLine of this assemblyLine.
	 */
	public AssemblyLine getAssemblyLine() {
		return assemblyLine;
	}

	/**
	 * A method to get the current time of this AssemblyLineScheduler.
	 *
	 * @return
	 */
	public DateTime getCurrentTime(){
		return this.currentTime;
	}

	/**
	 * Returns the current orders of this assemblyLine.
	 *
	 * @return
	 */
    protected LinkedList<Order> getOrders() {
	    return this.orders;
	}
    
	/**
	 * Returns the current orders of this assemblyLine.
	 *
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public LinkedList<Order> getOrdersClone() {
	    return (LinkedList<Order>) this.orders.clone();
	}

	/**
	 * Returns the shifts of this assemblyLine.
	 *
	 * @return the shift of this assemblyLine.
	 */
	protected LinkedList<Shift> getShifts() {
		return this.shifts;
	}

	/**
	 * A method that returns the current delay of this day assemblyLine.
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
			return this.currentTime.plusMinutes(this.calculateMinutes(order));
	}

	public int calculateMinutes(Order order){
		IteratorConverter<WorkPost> converter = new IteratorConverter<>();
		if(order.getVehicleModel() == null)
			return converter.convert(this.getAssemblyLine().getWorkPostsIterator()).size()*60;
		int minutes = 0;
		for(WorkPost wp : converter.convert(this.getAssemblyLine().getWorkPostsIterator())){
			minutes += wp.getStandardTimeOfModel(order.getVehicleModel());
		}
		return minutes;
	}

	public int minutesLastWorkPost(Order order) {
		IteratorConverter<WorkPost> converter = new IteratorConverter<>();
		if(order.getVehicleModel() == null)
			return 60;
		return converter.convert(this.getAssemblyLine().getWorkPostsIterator())
				.get(converter.convert(this.getAssemblyLine().getWorkPostsIterator()).size()-1)
				.getStandardTimeOfModel(order.getVehicleModel());
	}

	@Override
	public void subscribeObserver(Observer observer) throws IllegalArgumentException {
		if (observer == null) throw new IllegalArgumentException("Bad observer!");
		this.observers.add(observer);
	}

	@Override
	public void unSubscribeObserver(Observer observer) throws IllegalArgumentException {
		if (observer == null) throw new IllegalArgumentException("Bad observer!");
		this.observers.remove(observer);
	}

	/**
	 * This method notifies all the current observers when a new day is created.
	 * Currently the only observer is VehicleStatistics
	 */
	@Override
	public void notifyObservers() {
		for (Observer observer: this.observers) {
			observer.update(this);
		}
	} 

	public String currentAlgoDescription() {
		String[] full = this.algorithm.getClass().getName().split("\\.");
		return full[1];
	}

    /**
     * This method removes the pending orders for this assembly line.
     * It readds them to the mainscheduler so they can be rescheduledeer
     */
	protected void flushAssemblyLineScheduler(){
        ArrayList<Order> onAssemblyLine = this.ordersOnAssemblyLine();
		for(Order order: this.getOrders()) {
            if (!onAssemblyLine.contains(order))
                this.getAssemblyLine().getMainScheduler().orderCannotBePlaced(order);
        }
		this.getOrders().clear();
        this.getOrders().addAll(onAssemblyLine);
        this.clearTimeTable(onAssemblyLine);
	}

    protected ArrayList<Order> ordersOnAssemblyLine() {
        ArrayList<Order> onAssemblyLine = new ArrayList<>();
        Iterator<WorkPost> postIterator = this.assemblyLine.getWorkPostsIterator();
        while(postIterator.hasNext()) {
            Order order = postIterator.next().getOrder();
            if (order != null)
                onAssemblyLine.add(order);
        }
        return onAssemblyLine;
    }

    protected void clearTimeTable(ArrayList<Order> onAssemblyLine) {
        for( Shift shift : this.getShifts()){
            for(TimeSlot timeSlot : shift.getTimeSlots()){
                for (WorkSlot workSlot : timeSlot.getWorkSlots()){
                    if (!onAssemblyLine.contains(workSlot.getOrder()))
                        workSlot.removeOrder();
                }
            }
        }
    }

    /**
     * This method adds a number of  hours of delay to the current time.
     *
     * @param hours
     */
	protected void increaseCurrentTime(int hours){
        this.setCurrentTime(this.getCurrentTime().plusHours(hours));
        this.setDelay(this.getDelay()+hours*60);
        this.updateSchedule();
	}
}
