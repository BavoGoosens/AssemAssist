package businessmodel.scheduler;

import businessmodel.order.Order;

//Interface for subclasses.

public abstract class SchedulingAlgorithm {

	private Scheduler scheduler;

	public SchedulingAlgorithm(Scheduler scheduler){
		this.scheduler = scheduler;
	}
	
	public void schedule(){}
	
	public void scheduleOrder(Order order) {}
	
	public void updateSchedule(){}
	
	protected Scheduler getScheduler(){
		return this.scheduler;
	}
}