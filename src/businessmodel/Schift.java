package businessmodel;

import java.util.ArrayList;

import org.joda.time.DateTime;

public abstract class Schift {

	/**
	 * 
	 */
	private ArrayList<TimeSlot> timeslots; 
	
	/**
	 * 
	 */
	private DateTime currenttime;
	
	
	public Schift(){
		timeslots = new ArrayList<TimeSlot>();
		
	}
	
}
