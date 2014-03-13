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
	private ArrayList<String> component_types = new ArrayList<String>();

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
		ArrayList<String> types = new ArrayList<String>();
		for (Component cp : comp ){
			types.add(cp.getClass().getName());
		}
		this.setComponents(types);
	}

	/**
	 * This method returns the description of the action.
	 * @return	this.description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * This method returns the list of components needed for this action.
	 * @return	this.components
	 */
	public ArrayList<String> getComponents() {
		return this.component_types;
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
	private void setComponents(ArrayList<String> components) throws IllegalArgumentException {
		if (components == null){
			throw new IllegalArgumentException();
		} 
		else {
			this.component_types = components;
		}
	}

	/**
	 * This method adds a component to the action.
	 * 
	 * @param	component
	 * 			The component that needs to be added.
	 */
	public void addComponent(Component component) {
		if (!this.getComponents().contains(component.getClass().getName())) {
			this.getComponents().add(component.getClass().getName());
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

//	/**
//	 * A method to check is this action is completed.
//	 * @return true if all the components are completed.
//	 */
//	public boolean isCompleted() {
//		boolean ready = true;
//		for(String type: this.getComponents()){
//			if(component.isCompleted() == false)
//				ready = false;
//		}
//		return ready;
//	}
//
//	/**
//	 * A method to set this method to completed.
//	 * @param true or false
//	 */
//	public void setCompleted(boolean completed) {
//		for(Component component: this.getComponents()){
//			component.setCompleted(true);
//		}	
//	}
}
