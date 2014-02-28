package businessmodel;
import java.util.HashMap;

import component.Component;

/**
 * A class that represents an inventory for a factory
 * 
 * @author team 10
 *
 */
public class Inventory {


	/**
	 * A Map that contains all the components of an inventory
	 */
	private HashMap<Component,Integer> components;


	/**
	 * A Constructor that creates a new inventory list.
	 * 
	 */
	public Inventory() {
		this.components = new HashMap<Component,Integer>();
	}

	/**
	 * A method to add an component to the inventory.
	 * 
	 * @param   component
	 *          An component of the inventory. 
	 */         
	public void addComponent(Component component) {
		if (this.getComponents().containsKey(component))
			updateListAdd(component);
		else 
			this.getComponents().put(component,1);
	}

	/**
	 * A method that updates the Amount of components in an inventory list. 
	 * 
	 * @param   component
	 *          A component of the inventory list.
	 */
	private void updateListAdd(Component component){
		this.getComponents().put(component, this.getComponents().get(component) + 1);
	}

	/**
	 * A method that removes an component of the inventory list.
	 * 
	 * @param   component
	 *          the component that is removed from the inventory list.
	 */
	public void removeComponent(Component component) {
		if (this.getComponents().containsKey(component)){
			if ( this.getComponents().get(component) > 1) 
				updateListRemove(component);
			else
				this.getComponents().remove(component);
		}
	}

	private void updateListRemove(Component component) {
		this.getComponents().put(component, this.getComponents().get(component) - 1);
	}

	/**
	 * A method to return the components of this inventory.
	 * 
	 * @return this.components
	 */
	private HashMap<Component,Integer> getComponents() {
		return this.components;
	}	
}
