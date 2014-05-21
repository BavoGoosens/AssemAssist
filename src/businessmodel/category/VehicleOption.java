package businessmodel.category;

import java.util.UUID;

/**
 * Class representing a car option of a specific car option category.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class VehicleOption {
	
	/**
	 * Indicates the name of the car option.
	 */
	private String name;
	/**
	 * Indicates the car option category of the car option.
	 */
	private final VehicleOptionCategory category;
	
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
	public VehicleOption(String name, VehicleOptionCategory category) throws IllegalArgumentException {
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
	public VehicleOptionCategory getCategory() {
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

	@Override
	public String toString() {
		return this.getCategory().toString()+": "+this.getName();
	}
	
	@Override
	public boolean equals(Object carOption) throws IllegalArgumentException {
		if (carOption == null || !(carOption instanceof VehicleOption)) throw new IllegalArgumentException("Cannot compare this object: "+carOption+" to a car option!");
		VehicleOption option = (VehicleOption) carOption;
		return option.getCategory().equals(this.getCategory()) && option.getName().equalsIgnoreCase(this.getName());
	}

}
