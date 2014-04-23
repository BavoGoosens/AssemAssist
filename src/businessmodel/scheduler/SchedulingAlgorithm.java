package businessmodel.scheduler;

import java.util.LinkedList;

import businessmodel.order.Order;

//Interface for subclasses.

public abstract class SchedulingAlgorithm {

	private Scheduler scheduler;

	public SchedulingAlgorithm(Scheduler scheduler){
		this.scheduler = scheduler;
	}
	
	public abstract LinkedList<Order> schedule(LinkedList<Order> orders);
	
	public abstract void scheduleOrder(Order order);
	
	protected Scheduler getScheduler(){
		return this.scheduler;
	}
}