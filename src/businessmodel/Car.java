package businessmodel;

import component.*;

import java.util.ArrayList;

/**
 * A class representing a car.
 * 
 * @author team 10
 *
 */

public class Car {

	/**
	 * A list that holds all the components of a car.
	 */
	private ArrayList<Component> components;

	/**
	 * A constructor to create a new car.
	 * @param   components
	 *          the components of this new car.
	 */
	public Car(ArrayList<Component> components){
		setComponents(components);
	}

	/**
	 * A method to set the components of this car to the given components.
	 * @param   components
	 *          the new components of this car.
	 */
	private void setComponents(ArrayList<Component> components) {
		this.components = components;
	}

	/**
	 * A method to get the components of a car.
	 *
	 * @return  this.components
	 */
	public ArrayList<Component> getComponents(){
		return this.components;
	}

	/**
	 * A method to add a component to the components of a car.
	 * 
	 * @param   component
	 *          the component that you want to add.
	 */
	public void addComponent(Component component) {
		this.getComponents().add(component);
	}
	
	/**
	 * A method to remove a component from a car.
	 * @param   component
	 *         the component that you want to remove.
	 */
	public void removeComponent(Component component) {
		if( this.getComponents().contains(component))
			this.getComponents().remove(component);
	}
	
//	public Component getComponent(Object object){
//		Component component1;
//		for (Component component: getComponents()){
//			if (component.getClass() == object.getClass())
//				component1 = component;
//		}
//	}
}