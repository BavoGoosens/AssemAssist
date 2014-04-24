package businessmodel;

import java.util.ArrayList;

import businessmodel.order.Order;

public class FIFO extends SchedulingAlgorithm {

	/**
	 * Create a first come first serve algorithm.
	 * 
	 * @param scheduler
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

