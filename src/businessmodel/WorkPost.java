package businessmodel;

import java.util.ArrayList;

import businessmodel.category.CarOption;
import businessmodel.order.Order;

/**
 * A class representing a work post.
 * 
 * @author SWOP Team 10 2014
 *
 */
public class WorkPost {

	/**
	 * The name of the work post
	 */
	private String name;

	/**
	 * The tasks this work post can handle.
	 */
	private ArrayList<AssemblyTask> responsibleAssemblyTasks;

	/**
	 * The tasks that are pending at the work post.
	 */
	private ArrayList<AssemblyTask> pendingTasks;

	/**
	 * The order the working post is currently handling.
	 */
	private Order orderInProcess ;

	private int time_order_in_process;

	private AssemblyLine assemblyline;


	/**
	 * This method constructs a new work post with a given name and a given set of assembly tasks.
	 *
	 * @param name
	 * The name of the work post
	 * @param tasks
	 * The tasks this work post is responsible for.
	 * @throws IllegalArgumentException
	 */
	public WorkPost(String name, ArrayList<AssemblyTask> tasks, AssemblyLine assemblyline) throws IllegalArgumentException {
		this.setName(name);
		this.setAssemblyline(assemblyline);
		this.pendingTasks = new ArrayList<AssemblyTask>();
		this.setResponsibletasks(tasks);
	}

	/**
	 * This method returns the name of the work post.
	 * 
	 * @return	The name of the work post.
	 */
	public String getName() {
		return this.name;
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
	 * Returns the tasks that are pending at the work post.
	 * 
	 * @return	The tasks that are pending at the work post
	 */
	public ArrayList<AssemblyTask> getPendingTasks() {
		return this.pendingTasks;
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
	private void setPendingTasks(ArrayList<AssemblyTask> tasks) throws IllegalArgumentException {
		if (tasks == null) throw new IllegalArgumentException();
		this.pendingTasks = (ArrayList<AssemblyTask>) tasks.clone();
	}

	/**
	 * Returns the list of assembly tasks the work post is responsible for.
	 * 
	 * @return The list with all the assembly tasks this work post is responsible for.
	 */
	private ArrayList<AssemblyTask> getResponsibleTasks() {
		return this.responsibleAssemblyTasks;
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
	 * Sets the list of assembly tasks this work post is responsible for.
	 * 
	 * @param 	responsibleTasks
	 * 		  	The new list with all the assembly tasks the work post is responsible for.
	 * @throws	IllegalArgumentException
	 * 			| If the list of responsible tasks is equal to 'null'
	 * 			| responsibleTasks == null
	 */
	public void setResponsibletasks(ArrayList<AssemblyTask> responsibleTasks) throws IllegalArgumentException {
		if (responsibleTasks == null) throw new IllegalArgumentException("Bad list of responsible tasks!");
		this.responsibleAssemblyTasks = responsibleTasks;
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
	 * This method sets the order the work post is currently working on to the given order.
	 * 
	 * @param	orderInProcess
	 * 			The new order the work post is currently working on.
	 */
	public void setOrder(Order order_in_process) {
		this.orderInProcess = order_in_process;
		this.time_order_in_process = 0;
	}


	/**
	 * Returns whether the work post is in a completed state.
	 * 
	 * @return True if all tasks that are pending at the work post are completed.
	 */
	public boolean isCompleted(){
		for(AssemblyTask task: this.getPendingTasks()){
			if(task.isCompleted() == false)
				return false;
		}
		return true;
	}

	/**
	 * This method sets the order the work post is currently working on to 
	 * the given order and refreshes the assembly tasks.
	 * 
	 * @param   The order that this work post needs to start working on.
	 */
	public void setNewOrder(Order order) {
		this.setOrder(order);
		this.refreshAssemblyTasks();
	}

	/**
	 * This method refreshes the pending tasks that need to be done for the current order. 
	 */
	private void refreshAssemblyTasks() {
		if (this.getOrder() != null) {
			ArrayList<CarOption> carParts = this.getOrder().getCarOptions();
			ArrayList<AssemblyTask> newPendingTasks = this.possibleAssemblyTasks(carParts);
			this.setPendingTasks(newPendingTasks);
		}
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
	protected ArrayList<AssemblyTask> possibleAssemblyTasks(ArrayList<CarOption> carOptions) throws IllegalArgumentException {
		if (carOptions == null) 
			throw new IllegalArgumentException("Bad list of car parts!");
		ArrayList<AssemblyTask> result = new ArrayList<AssemblyTask>();
		for(AssemblyTask assem : this.getResponsibleTasks()){
			for(CarOption option: carOptions){
				if(option.getCategory().equals(assem.getCategory()))
					result.add(new AssemblyTask(assem.getName(),assem.getCategory(),this));
			}
		}
		return result;
	}

	protected void AssemblyTaskCompleted(int time) {
		this.setTime_order_in_process(this.getTime_order_in_process()+time);
		this.notifyAssemBlyLine();
	}

	protected void notifyAssemBlyLine(){
		boolean completed= true;
		for(AssemblyTask assemblytask: this.getPendingTasks()){
			if(!assemblytask.isCompleted())
				completed = false;
		}
		if(completed){
			this.getAssemblyline().WorkPostCompleted(this.getTime_order_in_process());
		}
	}

	@Override
	public String toString(){
		return this.getName();
	}

	private int getTime_order_in_process() {
		return time_order_in_process;
	}

	private void setTime_order_in_process(int time){
		this.time_order_in_process = time;
	}


	protected AssemblyLine getAssemblyline() {
		return assemblyline;
	}

	private void setAssemblyline(AssemblyLine assemblyline) {
		this.assemblyline = assemblyline;
	}

	/**
	 * Refreshes the order the work post is currently working on an returns the previous order.
	 * 
	 * @param	order
	 * 			The new order the work post needs to start working on.
	 * @return	The order the work post was previously working on.
	 */
	public Order switchOrders(Order order) {
		Order temp = this.getOrder();
		this.setNewOrder(order);
		return temp;
	}
}