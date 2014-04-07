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
			sh.addOrderToSlots(order,timeslots);
			order.updateEstimatedCompletionTimeOfOrder(this.getScheduler().getPrevious(order));
		}
	}

	@Override
	//TODO aanpassen
	public void updateSchedule(){
		if(this.getScheduler().getDelay() >= 60){
			this.getScheduler().getShifts().getLast().addTimeSlot();
			// get the next order that needs to be scheduled.
			// this.scheduleOrder();
		}
		else if (this.getScheduler().getDelay() <= 60 ){
			this.getScheduler().getShifts().getLast().removeLastTimeSlot();
		}
		//
	}


}

