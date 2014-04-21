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
		LinkedList<Order> orders2 = this.prioritySchedule(orders);
		for(Order order: orders2)
			this.scheduleOrder(order);
	}

	private LinkedList<Order> prioritySchedule(LinkedList<Order> orders) {
		LinkedList<Order> orders2 = this.prioritySchedule(orders);
		for(Order order: orders){
			if(order.getClass().getName() == "SingleTaskOrder")
				this.scheduleOrder(order);
		}
		return orders2;
	}

	@Override
	public void scheduleOrder(Order order) {
		ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();
		for (Shift sh: this.getScheduler().getShifts()){
			timeslots = sh.canAddOrder(order);
			if(timeslots!= null){
				sh.addOrderToSlots(order,timeslots);
				this.getScheduler().setEstimatedCompletionDate(order);
				break;
			}
		}
	}
}

