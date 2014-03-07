package businessmodel;
import java.util.ArrayList;

/**
 * A class that represents an assembly task
 * @author SWOP team 10 2013-2014
 *
 */
public class AssemblyTask {

	/**
	 * A variable that holds the individual actions of a assembly task
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
		this.completed = false;
	}
	
	/**
	 * A constructor for the assembly task.
	 */
	public AssemblyTask() {
		this.setActions(new ArrayList<Action>());
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
	 * This method returns whether the assembly task is completed.
	 * @return	True if the assembly task is completed.
	 */
	public boolean isCompleted() {
		return completed;
	}
	
	/**
	 * This method completes the assembly task.
	 */
	public void complete() {
		this.completed = true;
	}
	
	/**
	 * This method sets the actions for the assembly task.
	 * @param 	actions
	 * 			The actions of the assembly task.
	 */
	private void setActions(ArrayList<Action> actions) throws IllegalArgumentException {
		if (actions == null) throw new IllegalArgumentException();
		this.actions = actions;
	}
	
}
