package businessmodel;

import java.util.ArrayList;

import businessmodel.category.CarOption;
import businessmodel.order.Order;

/**
 * A class that represents work posts in a factory.
 * 
 * @author SWOP Team 10 2014
 *
 */
public class WorkPost {

	/**
	 * The name of this work post
	 */
	private String name;

	/**
	 * The tasks this WorkPost can handle.
	 */
	private ArrayList<AssemblyTask> responsibletasks;

	/**
	 * The tasks that are pending at the work post.
	 */
	private ArrayList<AssemblyTask> pendingtasks;

	/**
	 * The order the working post is currently handling.
	 */
	private Order order_in_process ;

	/**
	 * This method constructs a new work post with a given name.
	 * 
	 * @param	name
	 * 			The name of the work post
	 */
	public WorkPost(String name) throws IllegalArgumentException {
		this.setName(name);
		this.pendingtasks = new ArrayList<AssemblyTask>();
	}

	/**
	 * This method constructs a new work post with a given name and given assembly tasks.
	 * 
	 * @param	name
	 * 			The name of the work post
	 * @param 	tasks
	 * 			The tasks this work post is responsible for.
	 */
	public WorkPost(String name, ArrayList<AssemblyTask> tasks) throws IllegalArgumentException {
		this(name);
		this.setResponsibletasks(tasks);
	}

	/**
	 * This method returns the name of the work post.
	 * 
	 * @return	String 
	 * 			this.name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This method sets the name of the work post.
	 * 
	 * @param	name
	 * 			The name of the work post
	 * 
	 * @throws 	IllegalArgumentException
	 * 			name == null
	 */
	private void setName(String name) throws IllegalArgumentException {
		if (name == null) 
			throw new IllegalArgumentException();
		this.name = name;
	}

	/**
	 * This method returns the tasks that are pending at the work post.
	 * 
	 * @return	ArrayList<AssemblyTask>
	 * 			this.tasks
	 */
	public ArrayList<AssemblyTask> getPendingTasks() {
		return this.pendingtasks;
	}

	/**
	 * This method sets the tasks that are pending at the work post.
	 * 
	 * @param	tasks
	 * 			The task that are pending at the work post.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			tasks == null
	 */
	private void setPendingTasks(ArrayList<AssemblyTask> tasks) throws IllegalArgumentException {
		if (tasks == null) throw new IllegalArgumentException();
		this.pendingtasks = tasks;
	}

	/**
	 * This method returns the list of AssemblyTasks this WorkPost is responsible for.
	 * 
	 * @return ArrayList<AssemblyTask>
	 * 		   An ArrayList with all the AssemblyTasks this WorkPost is responsible for.
	 */
	public ArrayList<AssemblyTask> getResponsibletasks() {
		return responsibletasks;
	}

	/**
	 * This method sets the list of AssemblyTasks this WorkPost is responsible for.
	 * 
	 * @param responsibletasks
	 * 		  An ArrayList with all the AssemblyTasks this WorkPost is responsible for.
	 */
	public void setResponsibletasks(ArrayList<AssemblyTask> responsibletasks) throws IllegalArgumentException {
		if (responsibletasks == null) throw new IllegalArgumentException("Bad list of responsible tasks!");
		this.responsibletasks = responsibletasks;
	}

	/**
	 * This method returns the Order the Work Post is working on.
	 * 
	 * @return Order
	 * 		   The Order the WorkPost is working on.
	 */
	public Order getOrder() {
		return order_in_process;
	}

	/**
	 * This method sets the Order the Work Post is working on.
	 * 
	 * @param order_in_process
	 *        The Order the WorkPost is working on.
	 */
	public void setOrder(Order order_in_process) {
		this.order_in_process = order_in_process;
	}

	/**
	 * This method checks whether all the pending AssemblyTasks are completed.
	 * 
	 * @return boolean
	 * 		   true if all tasks have been completed. false otherwise.
	 */
	public boolean isCompleted(){
		for(AssemblyTask task: this.getPendingTasks()){
			if(task.isCompleted() == false)
				return false;
		}
		return true;
	}

	/**
	 * This method sets the order the work post is working on.
	 * 
	 * @param   Order
	 * 			The order that this work post needs to start working on.
	 */
	public void setNewOrder(Order order) {
		this.setOrder(order);
		this.refreshAssemblyTasks();
	}

	/**
	 * This method refreshes the pending tasks that need to be done for the current order. 
	 */
	private void refreshAssemblyTasks() {
		if (this.getOrder() != null ){
			ArrayList<CarOption> carparts = this.getOrder().getCar().getOptionsClone();
			ArrayList<AssemblyTask> newPendingTasks = this.possibleAssemblyTasks(carparts);
			this.setPendingTasks(newPendingTasks);
		}
	}

	/**
	 * This method constructs the list of AssemblyTasks that this WorkPost can carry out 
	 * based on the orders car components.
	 * 
	 * @param carparts
	 * 		  A list of components that need to be installed.
	 * 
	 * @return ArrayList<AssemblyTask>
	 *         A list of AssemblyTasks that need to be carried out to install (some of) the components.
	 */
	protected ArrayList<AssemblyTask> possibleAssemblyTasks(ArrayList<CarOption> carOptions) throws IllegalArgumentException {
		if (carOptions == null) throw new IllegalArgumentException("Bad list of car parts!");
		ArrayList<AssemblyTask> result = new ArrayList<AssemblyTask>();
		for(AssemblyTask task :this.getResponsibletasks()){
			for(Action action: task.getActions()){
				for(CarOption option: carOptions){
					if(action.getComponents().contains(option.getClass().getName()))
						result.add(task);	
				}		
			}
		}
		return result;
	}

	@Override
	public String toString(){
		return this.getName();
	}

	public Order switchOrders(Order order) {
		Order temp = this.getOrder();
		this.setNewOrder(order);
		return temp;
	}
}
