package businessmodel.exceptions;

import businessmodel.category.VehicleOptionCategory;

/**
 * A class representing an illegal car option category exception.
 * This exception is thrown when a car option is added to a wrong category object.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class IllegalVehicleOptionCategoryException extends Exception {
	
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The category that caused the exception.
	 */
	private VehicleOptionCategory category;
	
	/**
	 * Creates a new illegal car option exception.
	 */
	public IllegalVehicleOptionCategoryException() {}
	
	/**
	 * Creates a new illegal car option exception with a given message.
	 * @param 	message
	 * 			The message of the exception.
	 */
	public IllegalVehicleOptionCategoryException(String message) {
		super(message);
	}
	
	/**
	 * Creates a new illegal car option exception with a given category.
	 * 
	 * @param 	category
	 * 			The category of the exception.
	 */
	public IllegalVehicleOptionCategoryException(VehicleOptionCategory category) {
		this.setCategory(category);
	}
	
	/**
	 * Creates a new illegal car option exception with a given category and a given message.
	 * 
	 * @param	category
	 * 			The category of the exception.
	 * @param 	message
	 * 			The message of the exception.
	 */
	public IllegalVehicleOptionCategoryException(VehicleOptionCategory category, String message) {
		super(message);
		this.setCategory(category);
	}
	
	/**
	 * Returns the category of the exception.
	 * 
	 * @return	The category that caused the exception.
	 */
	public VehicleOptionCategory getCategory() {
		return this.category;
	}

	private void setCategory(VehicleOptionCategory category) {
		this.category = category;
	}

}
