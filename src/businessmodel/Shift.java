package businessmodel;

import java.util.ArrayList;

import org.joda.time.DateTime;

public abstract class Shift {


	private ArrayList<TimeSlot> timeslots; 
	
	public Shift(int hours){
		timeslots = new ArrayList<TimeSlot>();
		generateTimeSlots(hours);
	}
	
	private void generateTimeSlots(int hours){
		for(int i = 0;i < hours;i++){
			NormalTimeSlot slot = new NormalTimeSlot(3);
			this.getTimeSlots().add(slot);
		}
	}
	
	protected ArrayList<TimeSlot> getTimeSlots(){
		return this.timeslots;
	}
}
