package businessmodel.scheduler;

import java.util.ArrayList;
import businessmodel.order.Order;

public class FIFO extends SchedulingAlgorithm {

	public FIFO(Scheduler scheduler){
		super(scheduler);
	}

	@Override
	public void schedule(){
		for(Order order: this.getScheduler().getOrders())
			this.scheduleOrder(order);
	}

	@Override
	public void scheduleOrder(Order order) {
		ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();
		for (Shift sh: this.getScheduler().getShifts()){
			timeslots = sh.canAddOrder(order);
			if(timeslots!= null){
				sh.addOrderToSlots(order,timeslots);
				order.updateEstimatedCompletionTimeOfOrder(this.getScheduler().getPrevious(order));
				break;
			}
		}
	}
}

