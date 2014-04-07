package businessmodel.scheduler;

import java.util.LinkedList;

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
			this.getTimeslots().add(slot);
		}
	}
	
	protected void addTimeSlot(){
		
	}
	
	protected void removeTimeSlot(){
		
	}
	
	private LinkedList<TimeSlot> getTimeslots() {
		return timeslots;
	}

	private int getNumberofworkposts() {
		return numberofworkposts;
	}

	protected void terminate(){
		for(TimeSlot timeslot: this.getTimeslots()){
			timeslot.terminate();
		}
		this.timeslots = null;
	}
}
