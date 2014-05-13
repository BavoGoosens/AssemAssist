package businessmodel.category;

import java.util.ArrayList;

import businessmodel.category.VehicleOption;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

/**
 * A class representing a car.
 * 
 * @author SWOP team 10 2014
 *
 */
public class Vehicle {

	/**
	 * A list that holds all the options of a car.
	 */
	private ArrayList<VehicleOption> options;

	/**
	 * Creates a new car with a given list of options.
	 * 
	 * @param   options
	 *          The options of the car.
	 * @throws	IllegalArgumentException
	 * @throws 	UnsatisfiedRestrictionException 
	 */
	public Vehicle(ArrayList<VehicleOption> options) throws IllegalArgumentException {
		this.setOptions(options);
	}

	/**
	 * Returns a cloned list of options of the car.
	 * 
	 * @return	The options (clone) of the car.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<VehicleOption> getOptionsClone() {
		return (ArrayList<VehicleOption>) this.options.clone();
	}
	
	/**
	 * Returns the options of the car.
	 *
	 * @return  The options of the car.
	 */
	private ArrayList<VehicleOption> getOptions(){
		return this.options;
	}

	/**
	 * Sets the options of this car to the given options.
	 * 
	 * @param   options
	 *          The options of the car.
	 * @throws 	IllegalArgumentException
	 * 			| If the list of options is equal to 'null'
	 * 			| options == null
	 */
	@SuppressWarnings("unchecked")
	private void setOptions(ArrayList<VehicleOption> options) throws IllegalArgumentException {
		if(options == null) throw new IllegalArgumentException();
		this.options = (ArrayList<VehicleOption>) options.clone();
	}

	/**
	 * Returns a string representation of this car.
	 */
	@Override
	public String toString() {
		return "option = " + this.getOptions().toString();
	}

}