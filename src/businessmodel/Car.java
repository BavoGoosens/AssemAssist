package businessmodel;

import component.*;

import java.util.ArrayList;

/**
 * A class representing a car.
 * 
 * @author SWOP team 10 2014
 *
 */
public class Car {

	/**
	 * A list that holds all the components of a car.
	 */
	private ArrayList<Component> components;

	/**
	 * A constructor to create a new car.
	 * 
	 * @param   components
	 *          the components of this new car.
	 */
	public Car(ArrayList<Component> components){
		setComponents(components);
	}

	/**
	 * A method to set the components of this car to the given components.
	 * 
	 * @param   components
	 *          the new components of this car.
	 * @throws 	IllegalArgumentException
	 * 			if components is null
	 */
	private void setComponents(ArrayList<Component> components) throws IllegalArgumentException {
		if(components == null)
			throw new IllegalArgumentException();
		this.components = components;
	}

	/**
	 * A method to get the components of this car.
	 *
	 * @return  this.components
	 */
	public ArrayList<Component> getComponents(){
		return this.components;
	}

	/**
	 * A method to add a component to the components of this car.
	 * 
	 * @param   component
	 *          the component that you want to add.
	 */
	public void addComponent(Component component) {
		this.getComponents().add(component);
	}
	
	/**
	 * A method to remove a component from this car.
	 * 
	 * @param   component
	 *          the component that you want to remove.
	 */
	public void removeComponent(Component component) {
		if( this.getComponents().contains(component))
			this.getComponents().remove(component);
	}
	
	@Override
	public String toString() {
		return "components= " + components.toString();
	}

}