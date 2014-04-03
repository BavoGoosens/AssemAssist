package businessmodel.scheduler;

import java.util.ArrayList;

import org.joda.time.DateTime;

public abstract class Shift {


	private ArrayList<TimeSlot> timeslots; 
	
	public Shift(int hours){
		timeslots = new ArrayList<TimeSlot>();
		generateTimeSlots(hours);
	}
	
	public Shift(int hours,TimeSlot timeslot){
		timeslots = new ArrayList<TimeSlot>();
		generateTimeSlots(hours);
		timeslots.set(0, timeslot);
		
	}
	
	private void generateTimeSlots(int hours){
		for(int i = 0;i < hours;i++){
			TimeSlot slot = new TimeSlot(3);
			this.getTimeSlots().add(slot);
		}
	}
	
	protected ArrayList<TimeSlot> getTimeSlots(){
		return this.timeslots;
	}
	
	protected void addTimeSlot(){
		TimeSlot temp = new TimeSlot(3);
	}
	
	protected void removeTimeSlot(){
		
	}
	
	protected TimeSlot endOfShift(){
		//TODO: maak dit 
		return null;
		
	}
	
}
