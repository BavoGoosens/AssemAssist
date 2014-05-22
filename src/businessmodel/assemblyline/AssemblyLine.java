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
import businessmodel.util.SafeIterator;

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
	private AssemblyLineScheduler assemblylineScheduler;
	private MainScheduler mainscheduler;
	private int timeCurrentStatus = 0;
	private ArrayList<WorkPost> workPosts = new ArrayList<WorkPost>();
	private ArrayList<Observer> subscribers = new ArrayList<Observer>();
    private String name;


	/**
	 * A Constructor for the class AssemblyLine.
	 */
	protected AssemblyLine(){
		this.broken = new BrokenState(this);
		this.maintenance  = new MaintenanceState(this);
		this.operational  = new OperationalState(this);
	}

	/**
	 * Method to check whether  an order can be place on this AssemblyLine
	 *
     * @param order
	 * 		  The order that has to be placed
	 *
     * @return if the order can be placed
	 */
	public boolean canAddOrder(Order order){
		boolean bool = false;
		Iterator<VehicleModel> models = this.getResponsibleModelsIterator();
		while (models.hasNext()){
			VehicleModel model = models.next();
			if (order.getVehicleModel().getName()
                    .equalsIgnoreCase(model.getName()))
			    bool = true;
		}
        // is true when the assembly line can accept orders in this state.
        if(bool)
        	bool = this.state.canPlaceOrder();
        if(bool)
			return this.getAssemblyLineScheduler().canAddOrder(order);
		else
			return bool;
	}

	/**
	 * Checks whether the assembly line can move forward.
	 * @return True if the assembly line can move forward.
	 */
	protected boolean canAdvance() {
		boolean ready = true;
		for(WorkPost wp : this.getWorkPosts()){
			if (!wp.isCompleted())
				ready = false;
		}
        if (ready)
            ready = this.state.canAdvance();
		return ready;
	}

	/**
	 * Advances the assembly line and adds a new order to the assembly line.
	 *
	 * @param 	newOrder
	 * 			The new order that needs to be added to the assembly line, when moved forward.
	 * @throws	IllegalStateException
	 * 			| If the assembly line cannot be advanced.
	 * 			| !this.canAdvance()
	 */
	protected void advance(Order newOrder) throws IllegalStateException {
		if (!this.canAdvance())
			throw new IllegalStateException("Cannot advance assembly line!");
		Order temp = newOrder;
		for(WorkPost wp: this.getWorkPosts()){
			temp = wp.switchOrders(temp);
		}
		if(temp != null)
			temp.setCompleted();
		this.timeCurrentStatus = 0;
	}

	/**
	 * Updates the work post and the AssemblyLine with the current status.
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
	 * Notifies the AssemblyLine if a work post is completed, if all work posts are completed the assembly line advances.
	 */
	private void notifyScheduler(){
		if(canAdvance())
			this.getAssemblyLineScheduler().advance(this.timeCurrentStatus);
	}

	/**
	 * Returns the list of work posts at the assembly line.
	 *
	 * @return	The list of work posts at the assembly line.
	 */
	@SuppressWarnings("unchecked")
	public Iterator<WorkPost> getWorkPostsIterator() {
        SafeIterator<WorkPost> safe = new SafeIterator<WorkPost>();
        safe.convertIterator(this.workPosts.iterator());
		return safe;
	}

	/**
	 * Get the WorkPosts of this AssemblyLine
	 * @return WorkPosts
	 */
    private ArrayList<WorkPost> getWorkPosts() {
        return this.workPosts;
    }

	/**
	 * Returns the AssemblyLineScheduler of this AssemblyLine.
	 * @return the AssemblyLineScheduler
	 */
	public AssemblyLineScheduler getAssemblyLineScheduler() {
		return assemblylineScheduler;
	}

	/**
	 * Returns the models for which this AssemblyLine is responsible
	 * @return responsible models
	 */
	protected Iterator<VehicleModel> getResponsibleModelsIterator() {
		return this.responsibleModels.iterator();
	}

	/**
	 * Returns the number of work posts at the assembly line.
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
	 * Set the name of the AssemblyLine.
	 * @param name
	 */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Set the MainScheduler.
     * @param scheduler
     */
	protected  void setMainScheduler(MainScheduler scheduler){
		if (scheduler == null)
			throw new IllegalArgumentException("There was no MainScheduler supplied");
		this.mainscheduler = scheduler;
	}

	/**
	 * Set the AssemblyLineScheduler.
	 * @param scheduler
	 */
	protected void setAssemblylineScheduler(AssemblyLineScheduler scheduler){
		if (scheduler == null)
			throw new IllegalArgumentException("There was no scheduler supplied");
		this.assemblylineScheduler = scheduler;
	}

	/**
	 * Set WorkPosts of this AssemblyLine.
	 * @param workPosts
	 */
	protected void setWorkPosts( List<WorkPost> workPosts){
		if (workPosts == null)
			throw new IllegalArgumentException("There were no workPosts supplied");
		this.workPosts = (ArrayList<WorkPost>) workPosts;
	}

	/**
	 * Set the VehicleModels for which this AssemblyLine is responsible.
	 * @param models
	 */
	protected  void setResponsibleModels( List<VehicleModel> models){
		if (models == null)
			throw  new IllegalArgumentException("There were no models supplied");
		this.responsibleModels = models;
	}

	@Override
	public void subscribeObserver(Observer observer) {
		if (!subscribers.contains(observer))
			this.subscribers.add(observer);
	}


	@Override
	public void unSubscribeObserver(Observer observer) {
		if (this.subscribers.contains(observer))
			this.subscribers.remove(observer);
	}


	@Override
	public void notifyObservers() {
		for (Observer obs : this.subscribers)
			obs.update(this);
	}

	/**
	 * Transition to the Maintenance State.
	 */
    public void transitionToMaintenance(){
        this.state.markAssemblyLineAsMaintenance();
    }

	/**
	 * Transition to the Broken State.
	 */
    public void transitionToBroken(){
        this.state.markAssemblyLineAsBroken();
    }

	/**
	 * Transition to the Operational State.
	 */
    public void transitionToOperational(){
        this.state.markAssemblyLineAsOperational();
    }

	/**
	 * @return broke state AssemblyLine
	 */
	public AssemblyLineState getBrokenState() {
		return this.broken;
	}

	/**
	 * @return operational state AssemblyLine
	 */
	public AssemblyLineState getOperationalState() {
		return this.operational;
	}

	/**
	 * @return maintenance state AssemblyLine
	 */
	public AssemblyLineState getMaintenanceState() {
		return maintenance;
	}

	/**
	 * Method to set the state of an AssemblyLine.
	 * @param state
	 */
	protected void setState(AssemblyLineState state) {
	    this.state = state;
        this.state.initialize();
	}

   protected AssemblyLineState getCurrentState(){
       return this.state;
   }
	/**
	 * Get the estimated completion time of the given order.
	 * @param order
	 * @return
	 */
	public DateTime getEstimatedCompletionTimeOfNewOrder(Order order) {
		return this.getAssemblyLineScheduler().getEstimatedCompletionTimeOfNewOrder(order);
	}

	/**
	 * Get MainScheduler.
	 * @return MainScheduler
	 */
	protected MainScheduler getMainScheduler(){
		return this.mainscheduler;
	}

	/**
	 * Get current state of the AssemblyLine.
	 * @return current state
	 */
    public String currentState() {
        return this.state.toString();
    }

    /**
     * Get all the possible states of this AssemblyLine.
     * @return iterator of the possible states
     */
    public Iterator<String> getAllPossibleStates() {
        ArrayList<String> possible = new ArrayList<>();
        possible.add(this.getBrokenState().toString());
        possible.add(this.getMaintenanceState().toString());
        possible.add(this.getOperationalState().toString());
        return possible.iterator();
    }

    @Override
    public String toString(){
    	return this.name + " " + this.getWorkPosts().size();
    }
}
