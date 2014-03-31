package businessmodel;

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
	private ArrayList<CarOption> options;

	/**
	 * A constructor to create a new car.
	 * 
	 * @param   components
	 *          An ArrayList with all the components of this new car.
	 */
	public Car(ArrayList<CarOption> options) throws IllegalArgumentException {
		setOptions(options);
	}

	/**
	 * A method to set the components of this car to the given components.
	 * 
	 * @param   components
	 *          the new components of this car.
	 * @throws 	IllegalArgumentException
	 * 			if components is null
	 */
	@SuppressWarnings("unchecked")
	private void setOptions(ArrayList<CarOption> options) throws IllegalArgumentException {
		if(options == null) throw new IllegalArgumentException();
		this.options = (ArrayList<CarOption>) options.clone();
	}

	/**
	 * A method to get the components of this car.
	 *
	 * @return  ArrayList<Component>
	 * 			this.components
	 */
	private ArrayList<CarOption> getOptions(){
		return this.options;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<CarOption> getOptionsClone() {
		return (ArrayList<CarOption>) this.options.clone();
	}

	/**
	 * A method to add a component to the components of this car.
	 * 
	 * @param   component
	 *          the component that you want to add.
	 */
	public void addOption(CarOption option) throws IllegalArgumentException {
		if (option == null) throw new IllegalArgumentException("Bad option!");
		this.getOptions().add(option);
	}
	
	/**
	 * A method to remove a component from this car.
	 * 
	 * @param   component
	 *          the component that you want to remove.
	 */
	public void removeOption(CarOption option) throws IllegalArgumentException {
		if (option == null) throw new IllegalArgumentException("Bad option!");
		if( this.getOptions().contains(option))
			this.getOptions().remove(option);
	}
	
	@Override
	public String toString() {
		return "option= " + this.getOptions().toString();
	}

}