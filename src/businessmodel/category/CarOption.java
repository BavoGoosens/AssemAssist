package businessmodel.category;

import java.util.UUID;

/**
 * Class representing a car option of a specific car option category.
 * 
 * @author team 10
 *
 */
public class CarOption {
	
	/**
	 * Indicates the name of the car option.
	 */
	private String name;
	/**
	 * Indicates the car option category of the car option.
	 */
	private final CarOptionCategory category;
	
	/**
	 * Creates a new car option.
	 * 
	 * @param 	name
	 * 			The name of the car option.
	 * @param 	category
	 * 			The car option category of the car option.
	 * @throws 	IllegalArgumentException
	 * 			| If category is equal to 'null'
	 * 			| category == null
	 */
	public CarOption(String name, CarOptionCategory category) throws IllegalArgumentException {
		if (category == null) throw new IllegalArgumentException("Bad category!");
		this.category = category;
		this.setName(name);
	}
	
	/**
	 * Returns the name of the car option.
	 * 
	 * @return	The name of the car option.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the car option category of the car option.
	 * 
	 * @return	The car option category of the car option.
	 */
	public CarOptionCategory getCategory() {
		return this.category;
	}
	
	/**
	 * Returns the unique key of the car option category of the car option.
	 * 
	 * @return	The unique key of the car option category of the car option.
	 */
	public UUID getCategoryKey() {
		return this.category.getKey();
	}
	
	/**
	 * Sets the name of the car option.
	 * 
	 * @param 	name
	 * 			The name of the car option.
	 * @throws 	IllegalArgumentException
	 * 			| If the name is equal to 'null' or the empty string.
	 * 			| name == null || name.equals("")
	 */
	private void setName(String name) throws IllegalArgumentException {
		if (name == null || name.equals("")) throw new IllegalArgumentException("Bad name!");
		this.name = name;
	}
	
	/**
	 * Returns a string representing the car option.
	 */
	@Override
	public String toString() {
		return this.getCategory().toString()+": "+this.getName();
	}

}
