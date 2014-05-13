package businessmodel.assemblyline;

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

	
	private AssemblyLineState broken;
	private AssemblyLineState maintenance;
	private AssemblyLineState operational;
	private AssemblyLineState state;
	private ArrayList<WorkPost> workposts = new ArrayList<WorkPost>();
	private int timeCurrentStatus = 0;
	private AssemblyLineScheduler scheduler;
	private ArrayList<Observer> subscribers = new ArrayList<Observer>();

	/**
	 * Creates a new assembly line.
	 */
	protected AssemblyLine(AssemblyLineScheduler scheduler) throws IllegalArgumentException {
		
		this.broken = new BrokenState(this);
		this.maintenance  = new MaintenanceState(this);
		this.operational  = new OperationalState(this);
		this.setState(operational);
		
		this.setScheduler(scheduler);
		this.generateWorkPosts();
	}


	/**
	 * Checks whether the assembly line can move forward.
	 * 
	 * @return True if the assembly line can move forward.
	 */
	protected boolean canAdvance() {
		for(WorkPost wp : this.getWorkPosts()){
			boolean ready = wp.isCompleted();
			if (ready)
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
	protected void advance(Order neworder) throws IllegalStateException {
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
	protected int getNumberOfWorkPosts(){
		return this.getWorkPosts().size();
	}

	/**
	 * A method to get the time spent working on the current status of the AssemblyLine.
	 * @return
	 */
	protected int getTimeCurrentStatus() {
		return timeCurrentStatus;
	}

	/**
	 * Updates the work post and the assemblyline with the current status.
	 * 
	 * @param 	timeCurrentStatus
	 * 			The current status.		
	 */
	protected void workPostCompleted(int timeCurrentStatus) {
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
	 * Notifies the assemblyline if a work post is completed, if all work posts are completed the assembly line advances.
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
	 * Generates work posts.
	 */
	private void generateWorkPosts(){
		WorkPost post1 = new WorkPost("Vehicle Body Post", this);
		WorkPost post2 = new WorkPost("Drivetrain Post", this);
		WorkPost post3 = new WorkPost("Accesoires Post", this);	
		this.workposts.add(post1);
		this.workposts.add(post2);
		this.workposts.add(post3);
	}


	private AssemblyLineScheduler getScheduler() {
		return scheduler;
	}

	private void setScheduler(AssemblyLineScheduler scheduler) {
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

	/**
	 * @return broken state assembly line
	 */
	public AssemblyLineState getBrokenState() {
		return this.broken;
	}


	/**
	 * @return operational state assembly line
	 */
	public AssemblyLineState getOperationalState() {
		return this.operational;
	}


	/**
	 * @return maintenance state assembly line
	 */
	public AssemblyLineState getMaintenanceState() {
		return maintenance;
	}


	/**
	 * Method to set the state of an assembly line
	 * @param state
	 */
	public void setState(AssemblyLineState state) {
		this.state = state;
	}

}