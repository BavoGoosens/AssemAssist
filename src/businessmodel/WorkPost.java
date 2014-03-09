package businessmodel;

import java.util.ArrayList;

public class WorkPost {
	
	/**
	 * The name of the work post
	 */
	private String name;
	/**
	 * The tasks that are pending at the work post
	 * The first element of the list is the current task
	 */
	private ArrayList<AssemblyTask> tasks;
	
	/**
	 * This method constructs a new work post with a given name.
	 * 
	 * @param	name
	 * 			The name of the work post
	 */
	public WorkPost(String name) {
		this.setName(name);
		this.tasks = new ArrayList<AssemblyTask>();
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
		this.setTasks(tasks);
	}
	
	/**
	 * This method returns the name of the work post.
	 * @return	this.name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * This method returns the tasks that are pending at the work post.
	 * @return	this.tasks
	 */
	public ArrayList<AssemblyTask> getTasks() {
		return this.tasks;
	}
	
	/**
	 * This method sets the name of the work post.
	 * 
	 * @param	name
	 * 			The name of the work post
	 * @throws 	IllegalArgumentException
	 * 			name == null
	 */
	private void setName(String name) throws IllegalArgumentException {
		if (name == null) throw new IllegalArgumentException();
		this.name = name;
	}
	
	/**
	 * This method sets the tasks that are pending at the work post.
	 * 
	 * @param	tasks
	 * 			The task that are pending at the work post.
	 * @throws 	IllegalArgumentException
	 * 			tasks == null
	 */
	private void setTasks(ArrayList<AssemblyTask> tasks) throws IllegalArgumentException {
		if (tasks == null) throw new IllegalArgumentException();
		this.tasks = tasks;
	}
	
	/**
	 * This method adds a task at the work post.
	 * 
	 * @param 	task
	 * 			The task that needs to be added
	 */
	public void addTask(AssemblyTask task) {
		this.getTasks().add(task);
	}
	
	/**
	 * This method removes a task at the work post.
	 * 
	 * @param	task
	 * 			The task that needs to be removed
	 */
	public void removeTask(AssemblyTask task) {
		this.getTasks().remove(task);
	}
	
	/**
	 * This method returns the current task at the work post.
	 * @return	The first element of the list of tasks at the work post.
	 */
	public AssemblyTask getCurrentTask() {
		return this.getTasks().get(0);
	}
	
	public boolean isCompleted(){
		boolean temp = true;
		for(AssemblyTask task: this.getTasks()){
			if(task.isCompleted() == false)
				temp = false;
		}
		return temp;
	}
	
	@Override
	public String toString(){
		return this.getName();
	}

}
