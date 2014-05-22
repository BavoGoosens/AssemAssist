package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.order.Order;
import businessmodel.util.SafeIterator;

/**
 * A class representing a work post.
 *
 * @author SWOP Team 10
 *
 */
public class WorkPost {

	private String name;
	private ArrayList<AssemblyTask> responsibleAssemblyTasks;
	private ArrayList<AssemblyTask> pendingTasks;
	private ArrayList<AssemblyTask> finishedTasks;
	private Order orderInProcess ;
	private int timeOrderInProcess;
	private AssemblyLine assemblyline;
	private HashMap<String,Integer> standardtimes;


	/**
	 * This method constructs a new work post with a given name and a given set of assembly tasks.
	 *
	 * @param 	name
	 * 			The name of the work post
	 * @param 	tasks
	 * 			The tasks this work post is responsible for.
	 * @throws 	IllegalArgumentException
	 */
	protected WorkPost(String name, AssemblyLine assemblyline) throws IllegalArgumentException {
		this.setName(name);
		this.setAssemblyline(assemblyline);
		this.responsibleAssemblyTasks = new ArrayList<AssemblyTask>();
		this.pendingTasks = new ArrayList<AssemblyTask>();
		this.finishedTasks = new ArrayList<AssemblyTask>();
	}

	/**
	 * Sets the list of assembly tasks this work post is responsible for.
	 *
	 * @param 	responsibleTasks
	 * 		  	The new list with all the assembly tasks the work post is responsible for.
	 * @throws	IllegalArgumentException
	 * 			| If the list of responsible tasks is equal to 'null'
	 * 			| responsibleTasks == null
	 */
	protected void setResponsibleTasks(ArrayList<AssemblyTask> responsibleTasks) throws IllegalArgumentException {
		if (responsibleTasks == null) throw new IllegalArgumentException("Bad list of responsible tasks!");
		for(AssemblyTask assem: responsibleTasks){
			if(!this.responsibleAssemblyTasks.contains(assem))
				this.responsibleAssemblyTasks.add(assem);
		}
	}

	/**
	 * Returns the order the work post is currently working on.
	 *
	 * @return The order the work post is currently working on.
	 */
	public Order getOrder() {
		return this.orderInProcess;
	}

	/**
	 * Returns whether the work post is in a completed state.
	 *
	 * @return True if all tasks that are pending at the work post are completed.
	 */
	public boolean isCompleted(){
		for(AssemblyTask task: this.pendingTasks){
			if(!task.isCompleted())
				return false;
		}
		return true;
	}

	/**
	 * Returns the name of the work post.
	 *
	 * @return	The name of the work post.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns a cloned list of assembly tasks the work post is responsible for.
	 *
	 * @return	A cloned list of assembly tasks the work post is responsible for.
	 */

	@SuppressWarnings("unchecked")
	public ArrayList<AssemblyTask> getResponsibleTasksClone() {
		return (ArrayList<AssemblyTask>) this.getResponsibleTasks().clone();
	}

	/**
	 * Refreshes the order the work post is currently working on an returns the previous order.
	 *
	 * @param	order
	 * 			The new order the work post needs to start working on.
	 * @return	The order the work post was previously working on.
	 */
	protected Order switchOrders(Order order) {
		Order temp = this.getOrder();
		this.setNewOrder(order);
//		if(this.pendingTasks.size() == 0){
//			this.setNewOrder(null);
//			return order;
//		}
		return temp;
	}

	/**
	 * Returns the tasks that are pending at the work post.
	 *
	 * @return	The tasks that are pending at the work post
	 */
	@SuppressWarnings("unchecked")
	public Iterator<AssemblyTask> getPendingTasks() {
		SafeIterator<AssemblyTask> safe = new SafeIterator<AssemblyTask>();
		safe.convertIterator(this.pendingTasks.iterator());
		return safe;
	}

	/**
	 * Returns the tasks that are finished at the work post.
	 *
	 * @return	The tasks that are finished at the work post
	 */
	@SuppressWarnings("unchecked")
	public Iterator<AssemblyTask> getFinishedTasks() {
		SafeIterator<AssemblyTask> safe = new SafeIterator<AssemblyTask>();
		safe.convertIterator(this.finishedTasks.iterator());
		return safe;
	}

	/**
	 * Returns the list of assembly tasks that this work post can carry out based on
	 * the given car options.
	 *
	 * @param 	carOptions
	 * 		  	A list of options that need to be installed.
	 *
	 * @return 	The list of assembly tasks that this work post can carry out based on the
	 * 			given car options.
	 */
	protected ArrayList<AssemblyTask> possibleAssemblyTasks(ArrayList<VehicleOption> carOptions) throws IllegalArgumentException {
		if (carOptions == null)
			throw new IllegalArgumentException("Bad list of car parts!");
		ArrayList<AssemblyTask> result = new ArrayList<AssemblyTask>();
		for(AssemblyTask assem : this.getResponsibleTasks()){
			for(VehicleOption option: carOptions){
				if(option.getCategory().equals(assem.getCategory()))
					result.add(new AssemblyTask(assem.getName(),assem.getDescription(),assem.getCategory(),this,assem.canBeOrdered()));
			}
		}
		return result;
	}

	/**
	 * The given AssemblyTask is completed with the given time.
	 * @param assem
	 * @param time
	 */
	protected void AssemblyTaskCompleted(AssemblyTask assem, int time) {
		this.pendingTasks.remove(assem);
		this.finishedTasks.add(assem);
		this.setTimeOrderInProcess(this.getTimeOrderInProcess()+time);
		this.getAssemblyline().notifyObservers();
		this.notifyAssemblyLine();
	}

	/**
	 * Notify the AssemblyLine when this WorkPost is completed.
	 */
	protected void notifyAssemblyLine(){
		if(isCompleted())
			this.getAssemblyline().workPostCompleted(this.getTimeOrderInProcess());
	}

	/**
	 * Get this AssemblyLine.
	 * @return this AssemblyLine
	 */
	protected AssemblyLine getAssemblyline() {
		return assemblyline;
	}

	/**
	 * This method sets the order the work post is currently working on to
	 * the given order and refreshes the assembly tasks.
	 *
	 * @param   The order that this work post needs to start working on.
	 */
	protected void setNewOrder(Order order) {
		this.setOrder(order);
		this.timeOrderInProcess = 0;
		this.pendingTasks.clear();
		this.refreshAssemblyTasks();
	}

	/**
	 * This method refreshes the pending tasks that need to be done for the current order.
	 */
	private void refreshAssemblyTasks() {
		if (this.getOrder() != null) {
			ArrayList<VehicleOption> carParts = this.getOrder().getOptions();
			ArrayList<AssemblyTask> newPendingTasks = this.possibleAssemblyTasks(carParts);
			this.setPendingTasks(newPendingTasks);
		}
	}

	/**
	 * Get the time from the Order in process.
	 * @return
	 */
	private int getTimeOrderInProcess() {
		return timeOrderInProcess;
	}

	/**
	 * Set the time from the Order in process.
	 * @param time
	 */
	private void setTimeOrderInProcess(int time){
		this.timeOrderInProcess = time;
	}


	/**
	 * This method sets the name of the work post to the given name.
	 *
	 * @param	name
	 * 			The name of the work post
	 *
	 * @throws 	IllegalArgumentException
	 * 			| If the name is equal to 'null'
	 * 			| name == null
	 */
	private void setName(String name) throws IllegalArgumentException {
		if (name == null)
			throw new IllegalArgumentException();
		this.name = name;
	}

	/**
	 * Set Order in process.
	 * @param order_in_process
	 */
	private void setOrder(Order order_in_process) {
		this.orderInProcess = order_in_process;
	}

	/**
	 * This method sets the tasks that are pending at the work post.
	 *
	 * @param	tasks
	 * 			The tasks that are pending at the work post.
	 * @throws 	IllegalArgumentException
	 * 			| If the list of tasks is equal to 'null'
	 * 			| tasks == null
	 */
	@SuppressWarnings("unchecked")
	private void setPendingTasks(ArrayList<AssemblyTask> tasks) {
		this.pendingTasks = (ArrayList<AssemblyTask>) tasks.clone();
	}

	/**
	 * Returns the list of assembly tasks the work post is responsible for.
	 * @return The list with all the assembly tasks this work post is responsible for.
	 */
	private ArrayList<AssemblyTask> getResponsibleTasks() {
		return this.responsibleAssemblyTasks;
	}

	/**
	 * Set the given AssemblyLine for this WorkPost.
	 * @param assemblyline
	 */
	private void setAssemblyline(AssemblyLine assemblyline) {
		this.assemblyline = assemblyline;
	}

	/**
	 * Get the standard time of the given VehicleModel.
	 * @param model
	 * @return
	 */
	public int getStandardTimeOfModel(VehicleModel model){
		return this.getStandardtimes().get(model.getName());
	}

	/**
	 * Get the standard times of this WorkPost.
	 * @return standardtimes
	 */
	private HashMap<String, Integer> getStandardtimes() {
		return standardtimes;
	}

	/**
	 * Set standardtimes.
	 * @param standardtimes
	 */
	protected void setStandardtimes(HashMap<String, Integer> standardtimes) {
		this.standardtimes = standardtimes;
	}

	/**
	 * Returns a string representation of the work post.
	 */
	@Override
	public String toString(){
		return this.getName();
	}
}
