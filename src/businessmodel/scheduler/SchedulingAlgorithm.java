package businessmodel.scheduler;

import businessmodel.order.Order;

//Interface for subclasses.

public abstract class SchedulingAlgorithm {

	private Scheduler scheduler;

	public SchedulingAlgorithm(Scheduler scheduler){
		this.scheduler = scheduler;
	}
	
	public abstract void schedule();
	
	public abstract void scheduleOrder(Order order);
	
	public abstract void updateSchedule();
	
	protected Scheduler getScheduler(){
		return this.scheduler;
	}
}