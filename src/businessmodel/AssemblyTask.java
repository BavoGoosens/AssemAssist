package businessmodel;

import java.util.ArrayList;
import java.util.HashMap;

import businessmodel.category.CarOption;
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
	
	private String action_description;

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
	public AssemblyTask(String name, String description_of_actions, CarOptionCategory category, WorkPost workpost) throws IllegalArgumentException {
		this.setWorkpost(workpost);
		this.setName(name);
		this.setCategory(category);
		this.setDescription(description_of_actions);
	}
	
	public AssemblyTask(String name, String description_of_actions, CarOptionCategory category) throws IllegalArgumentException {
		this.setName(name);
		this.setCategory(category);
		this.setDescription(description_of_actions);
	}
	
	public ArrayList<CarOption> getInstallableOptions() {
		ArrayList<CarOption> oplist = new Catalog().getAllOptions(this.category);
		
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<CarOption> result = new ArrayList<CarOption>();
		
		for(CarOption option: oplist)
			if(!list.contains(option.getName())){
				result.add(option);
				list.add(option.getName());
			}
		return result;
	}

	public String getDescription(){
		return this.action_description;
	}

	/**
	 * Checks if this assembly task is completed.
	 * 
	 * @return	True if the assembly task is completed.
	 */
	public boolean isCompleted(){
		return this.completed;
	}
	
	public WorkPost getWorkpost() {
		return workpost;
	}

	private void setWorkpost(WorkPost workpost) {
		this.workpost = workpost;
	}

	/**
	 * A method to set this assembly task to completed.
	 */
	protected void completeAssemblytask(int time){
		this.completed = true;
		this.notifyWorkPost(time);
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
	 * Returns the category of the assembly task.
	 * 
	 * @return	The category of the assembly task.
	 */
	protected CarOptionCategory getCategory() {
		return category;
	}

	private void notifyWorkPost(int time){
		this.getWorkpost().AssemblyTaskCompleted(this,time);
	}

	private void setDescription(String description_of_actions) {
		this.action_description = description_of_actions;
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

	/**
	 * Returns a string representation of the assembly task.
	 */
	@Override
	public String toString(){
		return this.getName();
	}
}
