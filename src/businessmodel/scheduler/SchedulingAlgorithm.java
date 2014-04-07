package businessmodel.scheduler;

import businessmodel.order.Order;

public abstract class SchedulingAlgorithm {

	private Scheduler scheduler;

	public SchedulingAlgorithm(Scheduler scheduler){
		this.scheduler = scheduler;
	}

	protected Scheduler getScheduler(){
		return this.scheduler;
	}
	
	public void schedule(Order order) {
		
	}
}

