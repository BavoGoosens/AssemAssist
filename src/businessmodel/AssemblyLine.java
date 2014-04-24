package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.order.Order;

/**
 * A class representing an assembly line. It currently holds 3 work post.
 * 
 * @author 	SWOP team 10 2014
 *
 */
public class AssemblyLine implements Subject{

	/**
	 * List of work posts at the assembly line.
	 */
	private ArrayList<WorkPost> workposts = new ArrayList<WorkPost>();

	/**
	 * The time spent working on the current status of the AssemblyLine.
	 */
	private int timeCurrentStatus = 0;

	/**
	 * Scheduler.
	 */
	private Scheduler scheduler;
		
	/**
	 * List of observers who have subscribed to changes from the assembly line.
	 */
	private ArrayList<Observer> subscribers = new ArrayList<Observer>();

	/**
	 * Creates a new assembly line.
	 */
	public AssemblyLine(Scheduler scheduler) throws IllegalArgumentException {
		this.setScheduler(scheduler);
		this.generateWorkPosts();
	}


	/**
	 * Checks whether the assembly line can move forward.
	 * 
	 * @return True if the assembly line can move forward.
	 */
	public boolean canAdvance() {
		for(WorkPost wp : this.getWorkPosts()){
			boolean ready = wp.isCompleted();
			if (ready == false)
				return false;
		}
		return true;
	}		

	/**
	 * Advances the assembly line and adds a new order to the assembly line.
	 * 
	 * @param 	neworder
	 * 			The new order that needs to be added to the assembly line, when moved forward.	
	 * @throws	IllegalStateException
	 * 			| If the assembly line cannot be advanced.
	 * 			| !this.canAdvance()
	 */
	public void advance(Order neworder) throws IllegalStateException {
		if (!this.canAdvance())
			throw new IllegalStateException("Cannot advance assembly line!");
		Order temp = neworder;
		for(WorkPost wp: this.getWorkPosts()){
			temp = wp.switchOrders(temp);
		}
		if(temp != null)
			temp.setCompleted();
		this.timeCurrentStatus = 0;
	}

	/**
	 * Returns the list of work posts at the assembly line.
	 * 
	 * @return	The list of work posts at the assembly line.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<WorkPost> getWorkPosts() {
		return (ArrayList<WorkPost>) this.workposts.clone();
	}

	/**
	 * Returns the number of work posts at the assembly line.
	 * 
	 * @return	The number of work posts at the assembly line.
	 */
	public int getNumberOfWorkPosts(){
		return this.getWorkPosts().size();
	}

	/**
	 * A method to get the time spent working on the current status of the AssemblyLine.
	 * @return
	 */
	public int getTimeCurrentStatus() {
		return timeCurrentStatus;
	}

	/**
	 * A method when a work post is completed.
	 * @param timeCurrentStatus
	 */
	protected void WorkPostCompleted(int timeCurrentStatus) {
		if(timeCurrentStatus > this.timeCurrentStatus)
			this.timeCurrentStatus = timeCurrentStatus;
		this.notifyScheduler();
	}


	/**
	 * Returns all the orders that are on the assembly line.
	 * 
	 * @return  A list with all the orders that are currently on the assembly line.
	 */
	protected LinkedList<Order> getWorkPostOrders(){
		LinkedList<Order> orders = new LinkedList<Order>();
		for(WorkPost wp: this.getWorkPosts())
			if (wp.getOrder() != null)
				orders.add(wp.getOrder());
		return orders;
	}


	/**
	 * A method to notify the scheduler if a work post is completed, if all work posts are completed the assembly line advances.
	 */
	private void notifyScheduler(){
		boolean completed = true;
		for(WorkPost wp: this.getWorkPosts()){
			if(!wp.isCompleted())
				completed = false;
		}
		if(completed)
			this.getScheduler().advance(this.timeCurrentStatus);
	}

	/**
	 * A method to generate the work posts?.
	 */
	private void generateWorkPosts(){
		WorkPost post1 = new WorkPost("Car Body Post", this);
		WorkPost post2 = new WorkPost("Drivetrain Post", this);
		WorkPost post3 = new WorkPost("Accesoires Post", this);	
		this.workposts.add(post1);
		this.workposts.add(post2);
		this.workposts.add(post3);
	}


	private Scheduler getScheduler() {
		return scheduler;
	}

	private void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Override
	public void subscribeObserver(Observer observer) {
		if (!subscribers.contains(observer))
			this.subscribers.add(observer);
	}


	@Override
	public void unsubscribeObserver(Observer observer) {
		if (this.subscribers.contains(observer))
			this.subscribers.remove(observer);		
	}


	@Override
	public void notifyObservers() {
		for (Observer obs : this.subscribers)
			obs.update(this);
	}
}