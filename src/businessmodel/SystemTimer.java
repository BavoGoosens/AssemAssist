package businessmodel;

import org.joda.time.DateTime;

public class SystemTimer {
		
	/**
	 * The current time.
	 */
	private static DateTime currenttime = new DateTime(new DateTime().getYear(), new DateTime().getMonthOfYear(), new DateTime().getDayOfMonth(), 8, 0);

	/**
	 * Method to get the current system time.
	 * @return the current time in a date time object
	 */
	public static DateTime getCurrenTime(){
		return new DateTime(currenttime);
	}
	
	/**
	 * Update system time.
	 * 
	 * @param caller
	 * @param time
	 */
	protected static void updateTime(Scheduler caller, DateTime time){
		currenttime = time;
	}
}
