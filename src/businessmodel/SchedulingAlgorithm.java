package businessmodel;


import businessmodel.order.Order;

//Interface for subclasses.

public abstract class SchedulingAlgorithm {

	private Scheduler scheduler;

	/**
	 * Creates a scheduling algorithm with a scheduler.
	 * @param scheduler
	 */
	public SchedulingAlgorithm(Scheduler scheduler){
		this.setScheduler(scheduler);
	}

	/**
	 * Method to schedule order. 
	 * @param order
	 */
	public abstract void scheduleOrder(Order order);
	
	/**
	 * Get the scheduler.
	 * @return scheduler
	 */
	protected Scheduler getScheduler(){
		return this.scheduler;
	}
	
	/**
	 * Method to set the scheduler.
	 * 
	 * @param scheduler
	 * @throws IllegalArgumentException
	 */
	private void setScheduler(Scheduler scheduler) throws IllegalArgumentException {
		if(scheduler == null)
			throw new IllegalArgumentException("Not a scheduler");
		this.scheduler = scheduler;
	}
}