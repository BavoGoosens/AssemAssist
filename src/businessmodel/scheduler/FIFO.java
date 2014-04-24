package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.order.Order;

public class FIFO extends SchedulingAlgorithm {

	public FIFO(Scheduler scheduler){
		super(scheduler);
	}

	@Override
	public LinkedList<Order> schedule(LinkedList<Order> orders){
		for(Order order: orders)
			this.scheduleOrder(order);
		return orders;
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

