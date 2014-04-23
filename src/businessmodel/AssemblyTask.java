package businessmodel;

import businessmodel.category.CarOptionCategory;

/**
 * A class representing an assembly task.
 * 
 * @author SWOP team 10 2014
 *
 */
public class AssemblyTask {

	/**
	 * The name of the assembly task.
	 */
	private String name;

	/**
	 * Specifies if this assembly task has been completed.
	 */
	private boolean completed = false;
	
	/**
	 * The category of the car options that this assembly task manages. 
	 */
	private CarOptionCategory category;
	
	private WorkPost workpost;

	/**
	 * Creates a new assembly task.
	 * 
	 * @param 	name
	 * 			The name for the assembly task.
	 * @param 	category
	 * 			The category for the assembly task.
	 * @throws 	IllegalArgumentException
	 * 
	 */
	public AssemblyTask(String name, CarOptionCategory category, WorkPost workpost) throws IllegalArgumentException {
		this.setWorkpost(workpost);
		this.setName(name);
		this.setCategory(category);
	}

	/**
	 * Sets the name of this assembly task to the given name.
	 * 
	 * @param   name
	 *          The new name for the assembly task.
	 * @throws 	IllegalArgumentException
	 * 			| If the name is equal to 'null'
	 * 			| name == null
	 */			
	private void setName(String name) throws IllegalArgumentException{
		if(name == null) 
			throw new IllegalArgumentException();
		this.name = name;
	}
	
	/**
	 * Returns the name of this assembly task.
	 * 
	 * @return  The name of this assembly task.
	 */
	protected String getName() {
		return this.name;
	}

	/**
	 * A method to set this assembly task to completed.
	 */
	//TODO protected zetten nadat SchedulerTest is verwijderd.
	public void completeAssemblytask(int time){
		this.completed = true;
		this.notifyWorkPost(time);
	}
	
	/**
	 * Checks if this assembly task is completed.
	 * 
	 * @return	True if the assembly task is completed.
	 */
	public boolean isCompleted(){
		return this.completed;
	}
	
	/**
	 * Returns a string representation of the assembly task.
	 */
	@Override
	public String toString(){
		return this.getName();
	}
	
	/**
	 * Returns the category of the assembly task.
	 * 
	 * @return	The category of the assembly task.
	 */
	protected CarOptionCategory getCategory() {
		return category;
	}
	
	/**
	 * Sets the category of the assembly task.
	 * 
	 * @param 	category
	 * 			The category for the assembly task.
	 * @throws 	IllegalArgumentException
	 * 			| If the category is equal to 'null'
	 * 			| category == null
	 */
	private void setCategory(CarOptionCategory category) throws IllegalArgumentException {
		if (category == null) throw new IllegalArgumentException("Bad category!");
		this.category = category;
	}

	private void notifyWorkPost(int time){
		this.getWorkpost().AssemblyTaskCompleted(time);
	}

	public WorkPost getWorkpost() {
		return workpost;
	}

	public void setWorkpost(WorkPost workpost) {
		this.workpost = workpost;
	}
}
