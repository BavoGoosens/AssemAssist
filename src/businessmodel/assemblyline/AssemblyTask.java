package businessmodel.assemblyline;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;

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
	 * Description for the actions who have to be performed for completing the assembly task.
	 */
	private String actionDescription;

	/**
	 * Specifies if this assembly task has been completed.
	 */
	private boolean completed = false;
	
	/**
	 * The category of the car options that this assembly task manages. 
	 */
	private VehicleOptionCategory category;
	
	/**
	 * The workpost of the assembly task
	 */
	private WorkPost workpost;

	/**
	 * Creates a new assembly task with a given name, a description of the actions, a category and a work post.
	 * 
	 * @param 	name
	 * 			The name for the assembly task.
	 * @param	descriptionOfActions
	 * 			The description of all the actions needed for the task.
	 * @param 	category
	 * 			The category for the assembly task.
	 * @param	workpost
	 * 			The work post of the assembly task.
	 * @throws 	IllegalArgumentException
	 * 
	 */
	protected AssemblyTask(String name, String descriptionOfActions, VehicleOptionCategory category, WorkPost workpost) throws IllegalArgumentException {
		this.setWorkpost(workpost);
		this.setName(name);
		this.setCategory(category);
		this.setDescription(descriptionOfActions);
	}
	
	/**
	 * Creates a new assembly task with a given name, a description of the actions, a category.
	 * 
	 * @param 	name
	 * 			The name for the assembly task.
	 * @param	descriptionOfActions
	 * 			The description of all the actions needed for the task.
	 * @param 	category
	 * 			The category for the assembly task.
	 * @throws 	IllegalArgumentException
	 */
	protected AssemblyTask(String name, String descriptionOfActions, VehicleOptionCategory category) throws IllegalArgumentException {
		this.setName(name);
		this.setCategory(category);
		this.setDescription(descriptionOfActions);
	}

    /**
     * Creates a new assembly task with a given name, a description of the actions, a category and a work post.
     *
     * @param 	name
     * 			The name for the assembly task.
     * @param	descriptionOfActions
     * 			The description of all the actions needed for the task.
     * @param	workpost
     * 			The work post of the assembly task.
     * @throws 	IllegalArgumentException
     *
     */
    protected AssemblyTask(String name, String descriptionOfActions, WorkPost workpost) throws IllegalArgumentException {
        this.setWorkpost(workpost);
        this.setName(name);
        this.setDescription(descriptionOfActions);
    }
	
	/**
	 * Returns a unique set of all the car options that can be installed.
	 * 
	 * @return A set of all the car options that can be installed.
	 */
	public ArrayList<VehicleOption> getInstallableOptions() {
		ArrayList<VehicleOption> oplist = new Catalog().getAllOptions(this.category);
		
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<VehicleOption> result = new ArrayList<VehicleOption>();
		
		for(VehicleOption option: oplist)
			if(!list.contains(option.getName())){
				result.add(option);
				list.add(option.getName());
			}
		return result;
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
	 * Sets the assembly task to completed.
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
	protected VehicleOptionCategory getCategory() {
		return category;
	}

	/**
	 * Notifies the work post when an assembly task is completed.
	 * 
	 * @param 	time
	 * 			The time.
	 */
	private void notifyWorkPost(int time){
		this.getWorkpost().AssemblyTaskCompleted(this,time);
	}

	/**
	 * Set actions for this assembly task.
	 * @param descriptionOfActions
	 */
	private void setDescription(String descriptionOfActions) {
		this.actionDescription = descriptionOfActions;
	}

	/**
	 * Returns the description of the actions for this assembly task.
	 * @return The description of the actions for this assembly task.
	 */
	public String getDescription(){
		return this.actionDescription;
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
	private void setCategory(VehicleOptionCategory category) throws IllegalArgumentException {
		if (category == null) throw new IllegalArgumentException("Bad category!");
		this.category = category;
	}

	/**
	 * Returns the work post of this assembly task.
	 * 
	 * @return	The work post of this assembly task.
	 */
	public WorkPost getWorkpost() {
		return workpost;
	}

	private void setWorkpost(WorkPost workpost) {
		this.workpost = workpost;
	}
	

	/**
	 * Returns a string representation of the assembly task.
	 */
	@Override
	public String toString(){
		return this.getName();
	}
}
