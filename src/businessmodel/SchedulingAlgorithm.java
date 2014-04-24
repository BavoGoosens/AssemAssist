package businessmodel;


import businessmodel.order.Order;

//Interface for subclasses.

public abstract class SchedulingAlgorithm {

	private Scheduler scheduler;

	public SchedulingAlgorithm(Scheduler scheduler){
		this.setScheduler(scheduler);
	}

	public abstract void scheduleOrder(Order order);
	
	protected Scheduler getScheduler(){
		return this.scheduler;
	}
	
	private void setScheduler(Scheduler scheduler) throws IllegalArgumentException {
		if(scheduler == null)
			throw new IllegalArgumentException("Not a scheduler");
		this.scheduler = scheduler;
	}
}