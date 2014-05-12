package businessmodel.category;

import java.util.ArrayList;
import java.util.UUID;

import businessmodel.exceptions.IllegalCarOptionCategoryException;

/**
 * Class representing a car option category with the possible options 
 * for a specific car model specification.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public abstract class CarOptionCategory {
	
	/**
	 * Indicates the options of the category.
	 */
	private ArrayList<CarOption> options;
	
	/**
	 * Creates a new car option category.
	 */
	protected CarOptionCategory() {
		this.options = new ArrayList<CarOption>();
	}
	
	/**
	 * Returns the unique key of the car option category.
	 * @return	The unique key of the car option category.
	 */
	public abstract UUID getKey();
	
	/**
	 * Returns the options for the car option category.
	 * 
	 * @return	The options of the car option category.
	 */
	private ArrayList<CarOption> getOptions() {
		return this.options;
	}
	
	/**
	 * Returns a cloned object of the options of this car option category.
	 * 
	 * @return The options (clone) of the car option category.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CarOption> getOptionsClone() {
		return (ArrayList<CarOption>) this.options.clone();
	}
	
	/**
	 * Adds a new option to the car option category.
	 * 
	 * @param 	option
	 * 			The new option for the car option category.
	 * @throws 	IllegalArgumentException
	 * 			| If the option is equal to 'null'
	 * 			| option == null
	 * @throws 	IllegalCarOptionCategoryException
	 * 			| If the option is not a valid option for this car option category.
	 * 			| !this.canHaveAsOption(option)
	 */
	protected void addOption(CarOption option) throws IllegalArgumentException, IllegalCarOptionCategoryException {
		if (option == null) throw new IllegalArgumentException("Bad option!");
		if (!this.canHaveAsOption(option)) throw new IllegalCarOptionCategoryException("Wrong category!");
		this.getOptions().add(option);
	}
	
	/**
	 * Removes an option from the car option category.
	 * 
	 * @param 	option
	 * 			The option that needs to be removed.
	 * @throws 	IllegalArgumentException
	 * 			| If the option is equal to 'null'
	 * 			| option == null
	 */
	protected void removeOption(CarOption option) throws IllegalArgumentException {
		if (option == null) throw new IllegalArgumentException("Bad option!");
		this.getOptions().remove(option);
	}
	
	/**
	 * Returns the option of the car option category with the given name, if that exists.
	 * 
	 * @param 	name
	 * 			The name of the option of the car option category.
	 * @return	The option of the car option category with the given name, if that exists.
	 * @throws 	IllegalArgumentException
	 * 			| If the name is equal to 'null' or if there exists no option with the given name
	 * 			| for this car option category.
	 * 			| name == null
	 */
	protected CarOption getOptionWithName(String name) throws IllegalArgumentException {
		if (name == null) throw new IllegalArgumentException("Bad name!");
		for (CarOption option: this.getOptions()) {
			if (option.getName().equalsIgnoreCase(name)) return option;
		}
		throw new IllegalArgumentException("No option found with that name!");
	}
	
	/**
	 * Returns whether this car option category can have the given option.
	 * 
	 * @param 	option
	 * 			The option that needs to be checked for the car option category.
	 * @return	True if the car option category is equal to the car option category of the given option.
	 * @throws 	IllegalArgumentException
	 * 			| If the option is equal to 'null'
	 * 			| option == null
	 */
	private boolean canHaveAsOption(CarOption option) throws IllegalArgumentException {
		if (option == null) throw new IllegalArgumentException("Bad option!");
		return this.equals(option.getCategory());
	}
	
	/**
	 * Returns whether the car option category is equal to the given object.
	 */
	@Override
	public boolean equals(Object category) throws IllegalArgumentException {
		if (category == null) throw new IllegalArgumentException("Bad category!");
		return this.getKey().equals(((CarOptionCategory) category).getKey());
	}
	
	/**
	 * Returns a string representation of the car option category.
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName().toUpperCase();
	}

}
