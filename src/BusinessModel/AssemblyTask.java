package BusinessModel;
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
	 * A constructor for the class assembly task.
	 * 
	 * @param   actions
	 *          the actions that are part of this assembly process.
	 */
	public AssemblyTask(ArrayList<Action> actions){
		this.actions = actions;
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
}
