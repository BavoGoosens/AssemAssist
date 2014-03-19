package businessmodel;
import java.util.ArrayList;

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
	 * A variable that holds the individual actions of this assembly task.
	 */
	private ArrayList<Action> actions;

	/**
	 * A variable that specifies if this assembly task has been completed.
	 */
	private boolean completed = false; 

	/**
	 * A constructor for the class assembly task.
	 * 
	 * @param   actions
	 *          the actions that are part of this assembly process.   
	 * @param   name
	 *          the name of this assembly process.
	 */
	public AssemblyTask(String name, ArrayList<Action> actions){
		this.setName(name);
		this.setActions(actions);
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
	 * A method to get the actions of this assembly task.
	 * 
	 * @return  ArrayList<Action>
	 * 			the actions of this assembly task.
	 */
	public ArrayList<Action> getActions() {
		return this.actions;
	}
	
	/**
	 * A method to get the name of this assembly task.
	 * 
	 * @return  String
	 * 			the name of this assembly task.
	 */
	private String getName() {
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

	/**
	 * A method to set the actions of this assembly task to the given actions.
	 * 
	 * @param 	actions
	 * 			the new actions of this assembly task.
	 */
	private void setActions(ArrayList<Action> actions) {
		if (actions == null)
			this.actions = new ArrayList<Action>();
		else
			this.actions = actions;
	}
	
	@Override
	public String toString(){
		return this.getName();
	}
	
	/**
	 * This method returns a string with the name of the assembly task and all the actions that need to be performed.
	 * 
	 * @return  String
	 * 			A string with on the first line the name of the assembly task and starting on the third line all the actions.
	 */
	public String toCompleteString(){
		String result = this.getName() + "\n Actions: \n";
		for( Action act : this.getActions()){
			result += act.getDescription();
		}
		return result;
	}
}
