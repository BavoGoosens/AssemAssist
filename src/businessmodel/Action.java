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
	 * The description of this action.
	 */
	private String description;

	/**
	 * The list of components of this action.
	 */
	private ArrayList<String> component_types = new ArrayList<String>();

	/**
	 * This method constructs a new action with a given description.
	 * 
	 * @param 	description
	 * 			The description of the new action.
	 */
	public Action(String descr) {
		this.setDescription(descr);
	}

	/**
	 * This method returns the description of the action.
	 * 
	 * @return	the description of this action.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * This method returns the list of components needed for this action.
	 * 
	 * @return	the components of this action.
	 */
	public ArrayList<String> getComponents() {
		return this.component_types;
	}

	/**
	 * This method sets the description of the action.
	 * 
	 * @param	description
	 * 			The description of the action
	 */
	private void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method adds a component to the action.
	 * 
	 * @param	component
	 * 			The component that needs to be added.
	 */
	protected void addComponent(Component component) {
		if (!this.getComponents().contains(component.getClass().getName())) 
			this.getComponents().add(component.getClass().getName());
	}
}
