package businessmodel;

import org.joda.time.DateTime;

public class SystemTimer {
		
	private static DateTime currenttime = new DateTime(new DateTime().getYear(), new DateTime().getMonthOfYear(), new DateTime().getDayOfMonth(), 8, 0);

	public static DateTime getCurrenTime(){
		return new DateTime(currenttime);
	}
	
	protected static void updateTime(Scheduler caller, DateTime time){
		currenttime = time;
	}
}
