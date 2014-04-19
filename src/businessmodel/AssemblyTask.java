package businessmodel;

/**
 * A class that represents an assembly task.
 * 
 * @author SWOP team 10 2014
 *
 */
public class AssemblyTask {

	/**
	 * A variable that holds the name of this assembly task.
	 */
	private String name;

	/**
	 * A variable that specifies if this assembly task has been completed.
	 */
	private boolean completed = false; 
	
	private CarOptionCategory category;

	/**
	 * A constructor for the class assembly task.
	 * 
	 * @param   name
	 *          the name of this assembly process.
	 */
	public AssemblyTask(String name, CarOptionCategory category) throws IllegalArgumentException {
		this.setName(name);
		this.setCategory(category);
	}

	/**
	 * A method to set the name of this assembly task to the given name.
	 * 
	 * @param   name
	 *          the new name of this assembly task.
	 * @throws 	IllegalArgumentException
	 * 			if the given name is null
	 */			
	private void setName(String name) throws IllegalArgumentException{
		if(name == null) 
			throw new IllegalArgumentException();
		this.name = name;
	}
	
	/**
	 * A method to get the name of this assembly task.
	 * 
	 * @return  String
	 * 			the name of this assembly task.
	 */
	protected String getName() {
		return this.name;
	}

	/**
	 * A method to set this assembly task to completed.
	 */
	public void completeAssemblytask(){
		this.completed = true;
	}
	
	/**
	 * A method to check if this assembly task is completed.
	 * 
	 * @return  boolean
	 * 			true if all actions of this assembly task are completed.
	 */
	public boolean isCompleted(){
		return this.completed;
	}
	
	@Override
	public String toString(){
		return this.getName();
	}
	
	protected CarOptionCategory getCategory() {
		return category;
	}

	private void setCategory(CarOptionCategory category) {
		this.category = category;
	}
}
