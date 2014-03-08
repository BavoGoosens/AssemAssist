package businessmodel;

import component.Component;
import java.util.ArrayList;

/**
 * A class that represents work posts in a factory.
 * 
 * @author Team 10
 *
 */
public class WorkPost {
	
	/**
	 * The name of the work post
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
	private Order order_in_process = null;
	
	/**
	 * This method constructs a new work post with a given name.
	 * 
	 * @param	name
	 * 			The name of the work post
	 */
	public WorkPost(String name) {
		this.setName(name);
		this.pendingtasks = new ArrayList<AssemblyTask>();
	}
	
	/**
	 * This method constructs a new work post with a given name and given assembly tasks.
	 * 
	 * @param	name
	 * 			The name of the work post
	 * @param 	tasks
	 * 			The tasks that are pending at the work post
	 */
	public WorkPost(String name, ArrayList<AssemblyTask> tasks) {
		this(name);
		this.setResponsibletasks(tasks);
	}
	
	/**
	 * This method returns the name of the work post.
	 * @return	this.name
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
		if (name == null) throw new IllegalArgumentException();
		this.name = name;
	}
	
	/**
	 * This method returns the tasks that are pending at the work post.
	 * 
	 * @return	this.tasks
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
	 * This method adds a task at the work post.
	 * 
	 * @param 	task
	 * 			The task that needs to be added
	 */
	public void addPendingTask(AssemblyTask task) {
		this.getPendingTasks().add(task);
	}
	
	/**
	 * This method removes a task at the work post.
	 * 
	 * @param	task
	 * 			The task that needs to be removed
	 */
	public void removePendingTask(AssemblyTask task) {
		this.getPendingTasks().remove(task);
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
	public void setResponsibletasks(ArrayList<AssemblyTask> responsibletasks) {
		this.responsibletasks = responsibletasks;
	}

	/**
	 * This method returns the Order the Work Post is working on.
	 * 
	 * @return Order
	 * 		   The Order the WorkPost is working on.
	 */
	private Order getOrder() {
		return order_in_process;
	}

	/**
	 * This method sets the Order the Work Post is working on.
	 * 
	 * @param order_in_process
	 *        The Order the WorkPost is working on.
	 */
	private void setOrder(Order order_in_process) {
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
	 * This method returns the order that this WorkPost was handling. 
	 * It also adds the next order and updates the tasks that need to be carried out.
	 * 
	 * @param neworder
	 * 		  The Order that will be added.
	 * @return Order
	 * 		   The finished order (for this WorkPost).
	 */
	public Order moveAlong(Order neworder) {
		Order finished = this.getOrder();
		this.setOrder(neworder);
		this.refreshAssemblyTasks();
		return finished;
	}

	/**
	 * This method refreshes the pending tasks that need to be done for the current order. 
	 */
	private void refreshAssemblyTasks() {
		Order currentOrder = this.getOrder();
		ArrayList<Component> carparts = currentOrder.getCar().getComponents();
		ArrayList<AssemblyTask> newPendingTasks = this.possibleAssemblyTasks(carparts);
		this.setPendingTasks(newPendingTasks);
	}

	/**
	 * 
	 * @param carparts
	 * @return
	 */
	private ArrayList<AssemblyTask> possibleAssemblyTasks(
			ArrayList<Component> carparts) {
		// TODO Auto-generated method stub
		return null;
	}

}
