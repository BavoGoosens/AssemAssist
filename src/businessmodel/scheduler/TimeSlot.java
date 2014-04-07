package businessmodel.scheduler;

import java.util.ArrayList;

/**
 * A class that represents a TimeSlot. Each TimeSlot had a number of WorkPost's. 
 * @author SWOP 2014 team 10
 *
 */
public class TimeSlot {

	/**
	 * A list that holds the WorkSlot's of this TimeSlot.
	 */
	private ArrayList<WorkSlot> workslots;

	/**
	 * A constructor for the class TimeSlot. A number of WorkSlots will be generated.
	 * @param	sizeworkposts
	 * 			the number of workSlots that will be generated.
	 * 
	 */
	public TimeSlot(int sizeworkposts){
		generateWorkSlots(sizeworkposts);
	}

	/**
	 * A method to get the WorkSlot's of this TimeSlot.
	 * @return	this.workslots
	 */
	protected ArrayList<WorkSlot> getWorkSlots(){
		return this.workslots;
	}

	/**
	 * A method that generates the WorkSlot's of this TimeSlot.
	 * @param	sizeworkposts
	 * 			the number of WorkSlot's that will be generated.
	 */
	private void generateWorkSlots(int sizeworkposts){
		for(int i = 0; i< sizeworkposts; i++){
			WorkSlot temp = new WorkSlot();
			this.getWorkSlots().add(temp);
		}
	}
	
	/**
	 * A method to terminate this object of TimeSlot.
	 */
	protected void terminate(){
		for(WorkSlot wp: this.getWorkSlots()){
			wp.terminate();
		}
		this.workslots = null;
	}
}
