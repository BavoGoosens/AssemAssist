package businessmodel;

import java.util.ArrayList;

import businessmodel.order.Order;

/**
 * Class representing a first come first served algorithm.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class FIFO extends SchedulingAlgorithm {

	/**
	 * Create a first come first serve algorithm with a given scheduler.
	 * 
	 * @param 	scheduler
	 * 			The scheduler for the algorithm.
	 */
	public FIFO(Scheduler scheduler){
		super(scheduler);
	}
	
	@Override
	public void scheduleOrder(Order order) {
		ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();
		for (Shift sh: this.getScheduler().getShifts()){
			timeslots = sh.canAddOrder(order);
			if(timeslots!= null){
				sh.addOrderToSlots(order,timeslots);
				break;
			}
		}
	}

}

