package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.order.Order;

public abstract class Shift {

	private LinkedList<TimeSlot> timeslots;
	
	private int numberofworkposts;

	public Shift(int hours, int numberofworkposts){
		this.setNumberOfWorkPosts(numberofworkposts);
		generateTimeSlots(hours);
	}

	/**
	 * A method to check if an order can be added to this shift.
	 * @param	order
	 * 			the order that you want added.
	 * @return	returns a list of TimeSlot's if the order can be scheduled.
	 * 			null if the order cannot be added.
	 */
	protected ArrayList<TimeSlot> canAddOrder(Order order){
		return null;
	}

	/**
	 * A method to add a Tie
	 */
	protected void addTimeSlot(){
		TimeSlot slot = new TimeSlot(this.getNumberofworkposts());
		this.getTimeSlots().add(slot);
	}

	protected Order removeLastTimeSlot(){
		Order temp = this.getTimeSlots().getLast().getWorkSlots().getLast().getOrder();
		this.getTimeSlots().removeLast();
		return temp;
	}

	protected void addOrderToSlots(Order order, ArrayList<TimeSlot> timeslots){
		int count = 0;
		for(TimeSlot timeslot: timeslots){
			timeslot.addOrderToWorkSlot(order, count++);
		}
	}

	protected TimeSlot getNextTimeSlot(TimeSlot timeslot){
		int index = this.getTimeSlots().indexOf(timeslot);
		if(index + 1 >= this.getTimeSlots().size() || this.getTimeSlots().size() < 0)
			return null;
		else
			return this.getTimeSlots().get(index+1);
	}

	protected Order getNextOrderForAssemblyLine() {
		TimeSlot newtimeslot = this.getTimeSlots().pollFirst();
		if(!newtimeslot.getWorkSlots().isEmpty())
			return newtimeslot.getWorkSlots().get(0).getOrder();
		else
			return null;
	}
	
	protected LinkedList<TimeSlot> getTimeSlots() {
		return timeslots;
	}

	protected int getNumberofworkposts() {
		return this.numberofworkposts;
	}

	private void generateTimeSlots(int hours){
		this.timeslots = new LinkedList<TimeSlot>();
		for(int i = 0;i < hours;i++){
			TimeSlot slot = new TimeSlot(this.getNumberofworkposts());
			this.getTimeSlots().add(slot);
		}
	}

	private void setNumberOfWorkPosts(int numberofworkposts) {
		this.numberofworkposts = numberofworkposts;
	}
}
