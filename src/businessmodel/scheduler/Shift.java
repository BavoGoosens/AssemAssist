package businessmodel.scheduler;

import java.util.LinkedList;

import businessmodel.order.Order;

public abstract class Shift {

	private LinkedList<TimeSlot> timeslots; 
	
	private int numberofworkposts = 3;
	
	public Shift(int hours){
		generateTimeSlots(hours);
	}

	public Shift(int hours,TimeSlot timeslot){
		generateTimeSlots(hours);
		timeslots.set(0, timeslot);
		
	}
	
	private void generateTimeSlots(int hours){
		this.timeslots = new LinkedList<TimeSlot>();
		for(int i = 0;i < hours;i++){
			TimeSlot slot = new TimeSlot(this.getNumberofworkposts());
			this.getTimeSlots().add(slot);
		}
	}
	
	protected void addTimeSlot(){
		
	}
	
	protected void removeTimeSlot(){
		
	}
	
	protected LinkedList<TimeSlot> getTimeSlots() {
		return timeslots;
	}

	private int getNumberofworkposts() {
		return numberofworkposts;
	}

	protected void terminate(){
		for(TimeSlot timeslot: this.getTimeSlots()){
			timeslot.terminate();
		}
		this.timeslots = null;
	}
	
	protected TimeSlot getNext(TimeSlot timeslot){
		int index = this.getTimeSlots().indexOf(timeslot);
		if(index + 1 >= this.getTimeSlots().size() || this.getTimeSlots().size() < 0)
			return null;
		else
			return this.getTimeSlots().get(index+1);
	}

	protected TimeSlot getPrevious(TimeSlot timeslot){
		int index = this.getTimeSlots().indexOf(timeslot);
		if(index-1 < 0 || this.getTimeSlots().size() < 0)
			return null;
		else
			return this.getTimeSlots().get(index-1);
	}
}
