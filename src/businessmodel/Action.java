package businessmodel;

import java.util.ArrayList;

import component.*;

/**
 * A class representing an action.
 * 
 * @author team 10
 *
 */
public class Action {
	
	/**
	 * The description of the action
	 */
	private String description;
	
	/**
	 * The list of components needed for the action
	 */
	private ArrayList<Component> components = new ArrayList<Component>();
	
	/**
	 * A variable that specifies if this assembly task is completed.
	 */
	private boolean completed; 
	
	/**
	 * This method constructs a new action with a given description.
	 * @param 	description
	 * 			The description of the action
	 */
	public Action(String descr) {
		this.setDescription(descr);
	}
	
	/**
	 * This method constructs a new action with a given description and list of components.
	 * 
	 * @param 	description
	 * 			The description of the action
	 * @param 	components
	 * 			The list of components needed for the action
	 */
	public Action(String descr, ArrayList<Component> comp) {
		this(descr);
		this.setComponents(comp);
	}
	
	/**
	 * This method returns the description of the action.
	 * @return	this.description
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * This method returns the list of components needed for the action.
	 * @return	this.components
	 */
	public ArrayList<Component> getComponents() {
		return this.components;
	}
	
	/**
	 * This method sets the description of the action.
	 * (the description can be null)
	 * 
	 * @param	description
	 * 			The description of the action
	 */
	private void setDescription(String descr) {
		this.description = descr;
	}
	
	/**
	 * This method sets the components of the action.
	 * 
	 * @param 	components
	 * 			The components of the action.
	 * @throws 	IllegalArgumentException
	 * 			components == null
	 */
	private void setComponents(ArrayList<Component> components) throws IllegalArgumentException {
		if (components == null){
			throw new IllegalArgumentException();
		} 
		else {
			this.components = components;
		}
	}
	
	/**
	 * This method returns the object at the given index.
	 * 
	 * @param	index
	 * 			The index
	 * @return	this.getComponents().get(index)
	 */
	public Component getComponentAtIndex(int index) {
		return this.getComponents().get(index);
	}
	
	/**
	 * This method adds a component to the action.
	 * 
	 * @param	component
	 * 			The component that needs to be added.
	 */
	public void addComponent(Component component) {
		if (!this.getComponents().contains(component)) {
			this.getComponents().add(component);
		}
	}
	
	/**
	 * This method removes a component needed for the action.
	 * 
	 * @param 	component
	 * 			The component that needs to be removed.
	 */
	public void removeComponent(Component component) {
		this.getComponents().remove(component);
	}
	
	public boolean isCompleted() {
		return completed;
	}

	protected void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
