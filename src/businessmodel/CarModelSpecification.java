package businessmodel;

import java.util.ArrayList;

import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;

/**
 * This class represents a car model specification.
 * It gives an overview of lists of options that are available for a car model.
 * 
 * @author SWOP Team 10
 *
 */
public class CarModelSpecification {
	
	/**
	 * The options that are available for a car model with this car model specification.
	 */
	private ArrayList<CarOption> options;

	/**
	 * Creates a new car model specification with a given list of options.
	 * 
	 * @param 	options
	 * 			The options for the new car model specification.
	 * @throws 	IllegalArgumentException
	 * 		
	 */
	public CarModelSpecification(ArrayList<CarOption> options) throws IllegalArgumentException {
		this.setOptions(options);
	}
	
	/**
	 * Returns the options of the car model specification.
	 * 
	 * @return	The options of the car model specification.
	 */
	private ArrayList<CarOption> getOptions() {
		return this.options;
	}
	
	/**
	 * Returns a cloned object of the list of options of the car model specification.
	 * 
	 * @return	A cloned list of the options of the car model specification.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CarOption> getOptionsClone() {
		return (ArrayList<CarOption>) this.getOptions().clone();
	}
	
	/**
	 * Returns a list of options with a given category of the car model specification.
	 * 
	 * @param	category
	 * 			The category of the options.
	 * @return	A list of options with a given category of the car model specification
	 * @throws 	IllegalArgumentException
	 * 			| If the category is equal to 'null'
	 * 			| category == null
	 */
	public ArrayList<CarOption> getOptionsOfCategory(CarOptionCategory category) throws IllegalArgumentException {
		if (category == null) throw new IllegalArgumentException("Bad category!");
		ArrayList<CarOption> options = new ArrayList<CarOption>();
		for (CarOption option: this.getOptions()) {
			if (option.getCategory().equals(category)) {
				options.add(option);
			}
		}
		return options;
	}
	
	/**
	 * Sets the options of the car model specification to the given list of options.
	 * 
	 * @param 	options
	 * 			The options for the car model specification.
	 * @throws 	IllegalArgumentException
	 * 			| If the list of options is equal to 'null'
	 * 			| options == null
	 */
	@SuppressWarnings("unchecked")
	private void setOptions(ArrayList<CarOption> options) throws IllegalArgumentException {
		if (options == null) throw new IllegalArgumentException("Bad list of options!");
		this.options = (ArrayList<CarOption>) options.clone();
	}
}


