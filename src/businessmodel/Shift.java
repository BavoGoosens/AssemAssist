package businessmodel;

import java.util.ArrayList;

import org.joda.time.DateTime;

public abstract class Shift {

	/**
	 * 
	 */
	private ArrayList<TimeSlot> timeslots; 
	
	/**
	 * 
	 */
	private DateTime currenttime;
	
	
	public Shift(){
		timeslots = new ArrayList<TimeSlot>();
	}
	
	/**
	 * A method to set the current time of this schift to the given currenttime
	 * @param beginday
	 * @throws IllegalArgumentException
	 */
	protected void updateCurrentTime(DateTime beginday) throws IllegalArgumentException {
		if(beginday == null) 
			throw new IllegalArgumentException("Date cannot be null");
		this.currenttime = beginday;
	}
}
