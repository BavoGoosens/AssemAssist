package businessmodel;


import businessmodel.order.Order;

//Interface for subclasses.
/**
 * An abstract class representing a scheduling algorithm.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public abstract class SchedulingAlgorithm {

	/**
	 * The scheduler of the scheduling algorithm.
	 */
	private Scheduler scheduler;

	/**
	 * Creates a scheduling algorithm with a scheduler.
	 * @param 	scheduler
	 * 			The scheduler for the scheduling algorithm.
	 */
	public SchedulingAlgorithm(Scheduler scheduler){
		this.setScheduler(scheduler);
	}

	/**
	 * Schedules order. 
	 * @param 	order
	 * 			The order to be scheduled.
	 */
	public abstract void scheduleOrder(Order order);
	
	/**
	 * Returns the scheduler.
	 * @return 	The scheduler of the scheduling algorithm.
	 */
	protected Scheduler getScheduler(){
		return this.scheduler;
	}
	
	/**
	 * Sets the scheduler.
	 * 
	 * @param 	scheduler
	 * @throws	IllegalArgumentException
	 */
	private void setScheduler(Scheduler scheduler) throws IllegalArgumentException {
		if(scheduler == null)
			throw new IllegalArgumentException("Not a scheduler");
		this.scheduler = scheduler;
	}
}