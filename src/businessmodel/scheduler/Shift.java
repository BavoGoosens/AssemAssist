package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.order.Order;

public abstract class Shift {

	private LinkedList<TimeSlot> timeslots;
	
	//TODO halen uit assemblyline 
	private int numberofworkposts = 3;

	public Shift(int hours){
		generateTimeSlots(hours);
	}

	private void generateTimeSlots(int hours){
		this.timeslots = new LinkedList<TimeSlot>();
		for(int i = 0;i < hours;i++){
			TimeSlot slot = new TimeSlot(this.getNumberofworkposts());
			this.getTimeSlots().add(slot);
		}
	}

	protected void addTimeSlot(){
		TimeSlot slot = new TimeSlot(this.getNumberofworkposts());
		this.getTimeSlots().add(slot);
	}

	protected Order removeLastTimeSlot(){
		Order temp = this.getTimeSlots().getLast().getWorkSlots().getLast().getOrder();
		this.getTimeSlots().removeLast();
		return temp;
	}

	protected ArrayList<TimeSlot> canAddOrder(Order order){
		return null;
	}

	protected void addOrderToSlots(Order order, ArrayList<TimeSlot> timeslots){
		int count = 0;
		for(TimeSlot timeslot: timeslots){
			timeslot.addOrderToWorkSlot(order, count++);
		}
	}

	protected int getNumberofworkposts() {
		return numberofworkposts;
	}

	protected LinkedList<TimeSlot> getTimeSlots() {
		return timeslots;
	}

	protected TimeSlot getPrevious(TimeSlot timeslot){
		int index = this.getTimeSlots().indexOf(timeslot);
		if(index-1 < 0 || this.getTimeSlots().size() < 0)
			return null;
		else
			return this.getTimeSlots().get(index-1);
	}

	protected TimeSlot getNext(TimeSlot timeslot){
		int index = this.getTimeSlots().indexOf(timeslot);
		if(index + 1 >= this.getTimeSlots().size() || this.getTimeSlots().size() < 0)
			return null;
		else
			return this.getTimeSlots().get(index+1);
	}

	protected void terminate(){
		for(TimeSlot timeslot: this.getTimeSlots()){
			timeslot.terminate();
		}
		this.timeslots = null;
	}

	protected Order getNextOrderForAssemblyLine() {
		TimeSlot newtimeslot = this.getTimeSlots().pollFirst();
		if(!newtimeslot.getWorkSlots().isEmpty())
			return newtimeslot.getWorkSlots().get(0).getOrder();
		else
			return null;
	}
}
