package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;

import businessmodel.MainScheduler;
import businessmodel.category.VehicleModel;
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

    private List<VehicleModel> responsibleModels;
    private AssemblyLineState broken;
	private AssemblyLineState maintenance;
	private AssemblyLineState operational;
	private AssemblyLineState state;
	private MainScheduler mainscheduler;
	private ArrayList<WorkPost> workposts = new ArrayList<WorkPost>();
	private int timeCurrentStatus = 0;
	private AssemblyLineScheduler assemblylineScheduler;
	private ArrayList<Observer> subscribers = new ArrayList<Observer>();

	/**
	 * Creates a new assembly line.
	 */
	protected AssemblyLine(AssemblyLineScheduler scheduler, MainScheduler mainscheduler) throws IllegalArgumentException {
		this.broken = new BrokenState(this);
		this.maintenance  = new MaintenanceState(this);
		this.operational  = new OperationalState(this);
		this.setState(operational);
		this.mainscheduler = mainscheduler;
		this.setScheduler(scheduler);
		this.generateWorkPosts();
	}

    protected AssemblyLine(){
        this.broken = new BrokenState(this);
        this.maintenance  = new MaintenanceState(this);
        this.operational  = new OperationalState(this);
        this.setState(operational);
    }

    protected  void setMainScheduler(MainScheduler scheduler){
        if (scheduler != null)
            this.mainscheduler = scheduler;
        else
            throw new IllegalArgumentException("There was no MainScheduler supplied");
    }
    protected void setAssemblylineScheduler(AssemblyLineScheduler scheduler){
        if (scheduler != null)
            this.assemblylineScheduler = scheduler;
        else
            throw new IllegalArgumentException("There was no scheduler supplied");
    }

    protected void setWorkPosts( List<WorkPost> workPosts){
        if (workPosts != null)
            this.workposts = (ArrayList<WorkPost>) workPosts;
        else
            throw new IllegalArgumentException("There were no workposts supplied");
    }

    protected  void setResponsibleModels( List<VehicleModel> models){
        if (models != null)
            this.responsibleModels = models;
        else
            throw  new IllegalArgumentException("There were no models supplied");
    }
    
    protected Iterator<VehicleModel> getResponsibleModelsIterator() {
    	return this.responsibleModels.iterator();
    }

	/**
	 * Checks whether the assembly line can move forward.
	 * 
	 * @return True if the assembly line can move forward.
	 */
	protected boolean canAdvance() {
		for(WorkPost wp : this.getWorkPosts()){
			boolean ready = wp.isCompleted();
			if (!ready)
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
			this.getAssemblyLineScheduler().advance(this.timeCurrentStatus);
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


	public AssemblyLineScheduler getAssemblyLineScheduler() {
		return assemblylineScheduler;
	}

	private void setScheduler(AssemblyLineScheduler scheduler) {
		this.assemblylineScheduler = scheduler;
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
	
	public DateTime getEstimatedCompletionTimeOfNewOrder(Order order) {
		return this.getAssemblyLineScheduler().getEstimatedCompletionTimeOfNewOrder(order);
	}
	
	protected MainScheduler getMainScheduler(){
		return this.mainscheduler;
	}
}