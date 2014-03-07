package businessmodel;
import java.util.ArrayList;

/**
 * A class that represents an assembly task
 * @author SWOP team 10 2013-2014
 *
 */
public class AssemblyTask {

	/**
	 * A variable that holds the indiviudal actions of a assembly task
	 */
	private ArrayList<Action> actions;
	
	/**
	 * A variable that specifies if this assembly task is completed.
	 */
	private boolean completed; 
	
	/**
	 * A constructor for the class assembly task.
	 * 
	 * @param   actions
	 *          the actions that are part of this assembly process.
	 */
	public AssemblyTask(ArrayList<Action> actions){
		this.setActions(actions);
		setCompleted(false);
	}
	
	/**
	 * A method that adds an action to an assembly task.
	 * 
	 * @param   action
	 * 			the action that is added to the list of actions of this assembly task
	 */
	public void addAction(Action action){
		this.getActions().add(action);
	}
	
	/**
	 * A method that removes an action to an assembly task.
	 * 
	 * @param   action
	 * 			the action that is removed from the list of actions of this assembly task
	 */
	public void removeAction(Action action){
		this.getActions().remove(action);
	}
	
	/**
	 * A method to get the actions of an assembly task.
	 * 
	 * @return  this.actions
	 */
	private ArrayList<Action> getActions() {
		return this.actions;
	}

	/**
	 * A method to check if this assembly task is completed.
	 * 
	 * @return true if all actions of this assembly task are completed.
	 */
	public boolean isCompleted(){
		boolean completed = true;
		for(Action action: this.getActions())
			if (action.isCompleted() == false)
				completed = false;
		return completed;
	}
	
	/**
	 * A method to set the actions of this assembly task to the given assembly task.
	 * @param actions
	 */
	private void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}
	
}
