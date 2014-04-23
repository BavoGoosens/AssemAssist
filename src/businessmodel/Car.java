package businessmodel;

import java.util.ArrayList;

import businessmodel.category.CarOption;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.restrictions.RestrictionChecker;

/**
 * A class representing a car.
 * 
 * @author SWOP team 10 2014
 *
 */
public class Car {

	/**
	 * A list that holds all the options of a car.
	 */
	private ArrayList<CarOption> options;

	/**
	 * Creates a new car with a given list of options.
	 * 
	 * @param   options
	 *          The options of the car.
	 * @throws	IllegalArgumentException
	 * @throws 	UnsatisfiedRestrictionException 
	 */
	public Car(ArrayList<CarOption> options) throws IllegalArgumentException, UnsatisfiedRestrictionException {
		RestrictionChecker restrictionChecker = new RestrictionChecker();
		restrictionChecker.check(options);
		setOptions(options);
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
	private void setOptions(ArrayList<CarOption> options) throws IllegalArgumentException {
		if(options == null) throw new IllegalArgumentException();
		this.options = (ArrayList<CarOption>) options.clone();
	}

	/**
	 * Returns the options of the car.
	 *
	 * @return  The options of the car.
	 */
	private ArrayList<CarOption> getOptions(){
		return this.options;
	}
	
	/**
	 * Returns a cloned list of options of the car.
	 * 
	 * @return	The options (clone) of the car.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CarOption> getOptionsClone() {
		return (ArrayList<CarOption>) this.options.clone();
	}
	
	/**
	 * Returns a string representation of this car.
	 */
	@Override
	public String toString() {
		return "option = " + this.getOptions().toString();
	}

}