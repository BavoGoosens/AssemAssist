package businessmodel;

import java.util.ArrayList;

import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.restrictions.RestrictionChecker;

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
	 * 			| If the list of options is equal to 'null'
	 * 			| options == null
	 * 		
	 */
	public CarModelSpecification(ArrayList<CarOption> options) throws IllegalArgumentException {
		this.setOptions(options);
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
	 * Returns the options of the car model specification.
	 * 
	 * @return	The options of the car model specification.
	 */
	private ArrayList<CarOption> getOptions() {
		return this.options;
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
	
	/**
	 * Checks whether the restrictions for a car are satisfied.
	 * 
	 * @param 	car
	 * 			The car that needs to be checked.
	 * @return	True if the car doesn't violate any restrictions.
	 * @throws 	IllegalArgumentException
	 * 			| If the car is equal to 'null'	
	 * 			| car == null
	 * @throws 	UnsatisfiedRestrictionException
	 * 			| If the car violates any of of the restrictions.
	 */
	public boolean checkRestrictions(Car car) throws IllegalArgumentException, UnsatisfiedRestrictionException {
		if (car == null) throw new IllegalArgumentException("Bad car!");
		RestrictionChecker checker = new RestrictionChecker();
		return this.checkCarModel(car) && checker.check(car.getOptionsClone());
	}
	
	/**
	 * Checks if a car model of the given car is valid.
	 * 
	 * @param 	car
	 * 			The car that needs to be checked.
	 * @return	True if the car model of the given car is valid.
	 * @throws 	IllegalArgumentException
	 * 			| If the given car is equal to 'null'
	 * 			| car == null
	 * @throws 	UnsatisfiedRestrictionException
	 * 			| If the car model of the given car is not valid.
	 */
	private boolean checkCarModel(Car car) throws IllegalArgumentException, UnsatisfiedRestrictionException {
		if (car == null) throw new IllegalArgumentException("Bad car!");
		ArrayList<CarOption> wrongOptions = new ArrayList<CarOption>();
		for (CarOption option: car.getOptionsClone()) {
			if (!this.getOptions().contains(option)) {
				wrongOptions.add(option);
			}
		}
		if (wrongOptions.size() > 0) {
			String message = "The following options do not belong to the car model you've chosen:\n";
			for (CarOption option: wrongOptions) {
				message += "\n- "+option;
			}
			throw new UnsatisfiedRestrictionException(message);
		}
		return true;
	}
}


