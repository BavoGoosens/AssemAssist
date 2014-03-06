package businessmodel;

import component.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing a car.
 * 
 * @author team 10
 *
 */

public class Car {

	/**
	 * A list that holds all the components of a car and specifies for each component if it is completed.
	 */
	private HashMap<Component,Boolean> components;

	/**
	 * A constructor to create a new car.
	 * @param   components
	 *          the components of this new car.
	 */
	public Car(ArrayList<Component> components){
		setComponents(components);
	}

	/**
	 * A method to set the components of this car to the given components .
	 * @param   components
	 *          the new components of this car.
	 */
	private void setComponents(ArrayList<Component> components) {
		for(Component component: components){
			this.components.put(component,false);
		}
	}

	/**
	 * A method to get the components of a car.
	 *
	 * @return  this.components
	 */
	public HashMap<Component,Boolean> getComponents(){
		return this.components;
	}

	/**
	 * A method to add a component to the components of a car.
	 * 
	 * @param   component
	 *          the component that you want to add.
	 */
	public void addComponent(Component component) {
		this.getComponents().put(component,false);
	}

	/**
	 * A method to remove a component from a car.
	 * @param   component
	 *         the component that you want to remove.
	 */
	public void removeComponent(Component component) {
		if (this.getComponents().containsKey(component))
			this.getComponents().remove(component);
	}

	public ArrayList<Component> getCompletedComponents(){
		ArrayList<Component> completedcomponents = new ArrayList<Component>();
		return completedcomponents;
	}
}