package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;

import businessmodel.MainScheduler;
import businessmodel.category.VehicleModel;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.order.Order;
import businessmodel.util.IteratorConverter;
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
	private LinkedList<WorkPost> workPosts = new LinkedList<>();
	private ArrayList<Observer> subscribers = new ArrayList<Observer>();
    private String name;


	/**
	 * Creates a new assembly line.
	 */
	protected AssemblyLine() {
		this.broken = new BrokenState(this);
		this.maintenance  = new MaintenanceState(this);
		this.operational  = new OperationalState(this);
	}

	/**
	 * Checks whether an order can be placed on this assembly line.
	 *
     * @param 	order
	 * 		  	The order that has to be placed.
	 *
     * @return 	True if the order can be placed.
     *
	 */
	public boolean canAddOrder(Order order){
		// als het order geen model heeft is het oké, anders checken of model kan geplaatst worden
        boolean bool = couldAcceptModel(order.getVehicleModel());
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
	 * @return True if all the WorkPosts are completed and the current state allows it.
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
   * An Order skips a WorkPost if it has nog tasks there to perform.
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
        LinkedList<WorkPost> reversed = (LinkedList<WorkPost>) this.getWorkPosts().clone();
        Collections.reverse(reversed);
        // get the potentially finished order.
        Order temp = reversed.getFirst().getOrder();
        reversed.getFirst().setNewOrder(null);
        for(int i = 1 ; i < reversed.size() ; i++){
            // get the Order from the current work post.
            Order nextOrder = reversed.get(i).getOrder();
            if(nextOrder != null){
                reversed.get(i).setNewOrder(null);
                // if there is a real order try and shift it to the next work post
                // where it will have pending orders
                WorkPost workPostForOrder = this.getNextWorkPost(reversed.get(i), reversed, nextOrder);
                if (workPostForOrder == null )
                    // this order is completed
                    nextOrder.setCompleted();
                 else
                    workPostForOrder.setNewOrder(nextOrder);
            }
        }
        // set the new order on the first post.
        reversed.getLast().setNewOrder(newOrder);
        if(temp != null)
			temp.setCompleted();
		this.timeCurrentStatus = 0;
	}

    private WorkPost getNextWorkPost(WorkPost workPost, LinkedList<WorkPost> reversed, Order order) {
        // get the next work post
        WorkPost nextWorkPost = this.previousWorkPost(workPost, reversed);
        nextWorkPost.setNewOrder(order);
        IteratorConverter<AssemblyTask> converter = new IteratorConverter<>();
        while(converter.convert(nextWorkPost.getPendingTasks()).size() == 0){
            // there are no pending tasks so try to get the next work post.
            WorkPost next = this.previousWorkPost(nextWorkPost,reversed);
            if( next != null && next.getOrder() == null){
                // there is a next work post and an order can be placed.
                // so remove the order from the current place
                nextWorkPost.setNewOrder(null);
                // put it on the next one
                next.setNewOrder(order);
                // update the current workpost
                nextWorkPost = next;
            } else if (next != null && next.getOrder() != null){
                // the next workpost can not accept the order
                // because there already is an order in process
                return nextWorkPost;
            } else {
                // if eventualy we find no next workpost the order can be finished
                nextWorkPost.setNewOrder(null);
                return null;
            }
        }
        return nextWorkPost;
    }

    private WorkPost previousWorkPost(WorkPost workPost, LinkedList<WorkPost> reversed) {
        int index = reversed.indexOf(workPost);
        if(index-1 < 0)
            return null;
        else
            return reversed.get(index-1);
    }


    /**
	 * Updates the time on the current status of the assembly line and notifies the scheduler.
	 *
	 * @param 	timeCurrentStatus
	 * 			The time spent on the current status.
	 */
	protected void workPostCompleted(int timeCurrentStatus) {
		if(timeCurrentStatus > this.timeCurrentStatus)
			this.timeCurrentStatus = timeCurrentStatus;
		this.notifyScheduler();
	}

	/**
	 * Advances (if possible) and notifies the assembly line scheduler.
	 */
	private void notifyScheduler(){
		if(canAdvance()) {
            this.getAssemblyLineScheduler().advance(this.timeCurrentStatus);
            notifyObservers();
        }
	}

	/**
	 * Returns an iterator over the list of work posts at the assembly line.
	 * @return	The list of work posts at the assembly line.
	 */
	@SuppressWarnings("unchecked")
	public Iterator<WorkPost> getWorkPostsIterator() {
        SafeIterator<WorkPost> safe = new SafeIterator<WorkPost>();
        safe.convertIterator(this.workPosts.iterator());
		return safe;
	}

	/**
	 * Returns the list of work posts of the assembly line
	 * @return 	The list of work posts.
	 */
    private LinkedList<WorkPost> getWorkPosts() {
        return this.workPosts;
    }

	/**
	 * Returns the assembly line scheduler of the assembly line.
	 * @return	The assembly line scheduler.
	 */
	public AssemblyLineScheduler getAssemblyLineScheduler() {
		return assemblylineScheduler;
	}

	/**
	 * Returns the models for which the assembly line is responsible.
	 * @return The responsible models.
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
	 * Returns the time spent working on the current status of the AssemblyLine.
	 * @return The current status of the assembly line.
	 */
	protected int getTimeCurrentStatus() {
		return timeCurrentStatus;
	}

	/**
	 * Returns all the orders that are on the assembly line.
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
	 * Sets the name of the assembly line.
	 *
	 * @param 	name
	 * 			The name of the assembly line.
	 */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the main scheduler of the assembly line to the given scheduler.
     *
     * @param 	scheduler
     * 			The main scheduler of the assembly line.
     */
	protected  void setMainScheduler(MainScheduler scheduler){
		if (scheduler == null)
			throw new IllegalArgumentException("There was no MainScheduler supplied");
		this.mainscheduler = scheduler;
	}

	/**
	 * Sets the assembly line scheduler of the assembly line to the given scheduler.
	 *
	 * @param 	scheduler
	 * 			The assembly line scheduler of the assembly line.
	 */
	protected void setAssemblylineScheduler(AssemblyLineScheduler scheduler){
		if (scheduler == null)
			throw new IllegalArgumentException("There was no scheduler supplied");
		this.assemblylineScheduler = scheduler;
	}

	/**
	 * Sets the work posts of the assembly line to the given work posts.
	 *
	 * @param 	workPosts
	 * 			The work posts of the assembly line.
	 */
	protected void setWorkPosts(List<WorkPost> workPosts){
		if (workPosts == null)
			throw new IllegalArgumentException("There were no workPosts supplied");
        LinkedList<WorkPost> list = new LinkedList<WorkPost>();
        list.addAll(workPosts);
        this.workPosts = list;
	}

	/**
	 * Sets the vehicle models for which the assembly line is responsible to the given models.
	 *
	 * @param 	models
	 * 			The models for which the assembly line is responsible.
	 */
	protected  void setResponsibleModels(List<VehicleModel> models){
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
	 * Transition to the maintenance state.
	 */
    public void transitionToMaintenance(){
        this.state.markAssemblyLineAsMaintenance();
    }

	/**
	 * Transition to the broken state.
	 */
    public void transitionToBroken(){
        this.state.markAssemblyLineAsBroken();
    }

	/**
	 * Transition to the operational state.
	 */
    public void transitionToOperational(){
        this.state.markAssemblyLineAsOperational();
    }

	/**
	 * Returns the broken state of the assembly line.
	 * @return 	The broken state of the assembly line.
	 */
	public AssemblyLineState getBrokenState() {
		return this.broken;
	}

	/**
	 * Returns the operational state of the assembly line.
	 * @return 	The operational state of the assembly line.
	 */
	public AssemblyLineState getOperationalState() {
		return this.operational;
	}

	/**
	 * Returns the maintenance state of the assembly line.
	 * @return 	The maintenance state of the assembly line.
	 */
	public AssemblyLineState getMaintenanceState() {
		return maintenance;
	}

	/**
	 * Sets the state of the assembly line to the given state.
	 *
	 * @param 	state
	 * 			The state the assembly line needs to be set to.
	 */
	protected void setState(AssemblyLineState state) {
	    this.state = state;
        this.state.initialize();
	}

	/**
	 * Returns the current state of the assembly line.
	 * @return The current state of the assembly line.
	 */
	protected AssemblyLineState getCurrentState(){
		return this.state;
	}
	/**
	 * Returns the estimated completion time of the given order.
	 *
	 * @param	order
	 * 			The order the estimated completion time is requested for.
	 * @return	The estimated completion time of the given order.
	 */
	public DateTime getEstimatedCompletionTimeOfNewOrder(Order order) throws IllegalArgumentException {
		if (order == null) throw new IllegalArgumentException("Bad order!");
		return this.getAssemblyLineScheduler().getEstimatedCompletionTimeOfNewOrder(order);
	}

	/**
	 * Returns the main scheduler of the assembly line.
	 * @return	The main scheduler of the assembly line.
	 */
	protected MainScheduler getMainScheduler(){
		return this.mainscheduler;
	}

	/**
	 * Returns a string representation of the current state of the assembly line.
	 * @return A string representation of the current state of the assembly line.
	 */
    public String currentState() {
        return this.state.toString();
    }

    /**
     * Returns all the possible states of the assembly line.
     * @return An iterator over all the possible states of the assembly line.
     */
    public Iterator<String> getAllPossibleStates() {
        ArrayList<String> possible = new ArrayList<>();
        possible.add(this.getBrokenState().toString());
        possible.add(this.getMaintenanceState().toString());
        possible.add(this.getOperationalState().toString());
        return possible.iterator();
    }

    /**
     * Returns a string representation of the assembly line.
     */
    @Override
    public String toString(){
    	return this.name + " " + this.getWorkPosts().size();
    }

    /**
     * A method to check if this AssemblyLine can process the given VehicleModel. 
     * @return true if it can handle the given VehicleModel
     */
    public boolean couldAcceptModel(VehicleModel vehicleModel) {
        Iterator<VehicleModel> models = this.getResponsibleModelsIterator();
        if (vehicleModel == null) {
            return true;
        }
        else {
            while (models.hasNext()){
                VehicleModel model = models.next();
                if (vehicleModel.getName()
                        .equalsIgnoreCase(model.getName()))
                    return true;

            }
            return false;
        }
    }

}
