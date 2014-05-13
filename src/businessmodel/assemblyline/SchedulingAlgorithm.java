package businessmodel.assemblyline;


import businessmodel.assemblyline.AssemblyLineScheduler;
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
	 * The assemblyline of the scheduling algorithm.
	 */
	private AssemblyLineScheduler scheduler;

	/**
	 * Creates a scheduling algorithm with a assemblyline.
	 * @param 	scheduler
	 * 			The assemblyline for the scheduling algorithm.
	 */
	protected SchedulingAlgorithm(AssemblyLineScheduler scheduler){
		this.setScheduler(scheduler);
	}

	/**
	 * Schedules order. 
	 * @param 	order
	 * 			The order to be scheduled.
	 */
	protected abstract void scheduleOrder(Order order);
	
	/**
	 * Returns the assemblyline.
	 * @return 	The assemblyline of the scheduling algorithm.
	 */
	protected AssemblyLineScheduler getScheduler(){
		return this.scheduler;
	}
	
	/**
	 * Sets the assemblyline.
	 * 
	 * @param 	scheduler
	 * @throws	IllegalArgumentException
	 */
	private void setScheduler(AssemblyLineScheduler scheduler) throws IllegalArgumentException {
		if(scheduler == null)
			throw new IllegalArgumentException("Not a assemblyline");
		this.scheduler = scheduler;
	}
}