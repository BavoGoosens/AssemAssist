package businessmodel.scheduler;

import java.util.ArrayList;

import businessmodel.order.Order;

public class SpecificationBatch extends SchedulingAlgorithm {
	
	public SpecificationBatch(Scheduler scheduler){
		super(scheduler);
	}
	
	@Override
	public void schedule(){
		for(Order order: this.getScheduler().getOrders())
			this.scheduleOrder(order);
	}

	@Override
	public void scheduleOrder(Order order) {
		
	}

	@Override
	public void updateSchedule(){

	}

}

