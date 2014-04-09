package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.order.Order;

public class FIFO extends SchedulingAlgorithm {

	public FIFO(Scheduler scheduler){
		super(scheduler);
	}

	@Override
	public void schedule(LinkedList<Order> orders){
		for(Order order: orders)
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

	@Override
	public void updateSchedule(){
		if(this.getScheduler().getDelay() >= 60){
			this.getScheduler().getShifts().getLast().addTimeSlot();
			Order nextorder = this.getScheduler().getNextOrderToSchedule();
			this.scheduleOrder(nextorder);
		}
		else if (this.getScheduler().getDelay() <= 60 ){
			Order order = this.getScheduler().getShifts().getLast().removeLastTimeSlot();
			this.getScheduler().getOrdermanager().getPendingOrders().addFirst(order);
		}
	}
}

