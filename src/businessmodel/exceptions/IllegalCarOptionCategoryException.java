package businessmodel.exceptions;

import businessmodel.category.CarOptionCategory;

/**
 * A class representing an illegal car option category exception.
 * This exception is thrown when a car option is added to a wrong category object.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class IllegalCarOptionCategoryException extends Exception {
	
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The category that caused the exception.
	 */
	private CarOptionCategory category;
	
	/**
	 * Creates a new illegal car option exception.
	 */
	public IllegalCarOptionCategoryException() {}
	
	/**
	 * Creates a new illegal car option exception with a given message.
	 * @param 	message
	 * 			The message of the exception.
	 */
	public IllegalCarOptionCategoryException(String message) {
		super(message);
	}
	
	/**
	 * Creates a new illegal car option exception with a given category.
	 * 
	 * @param 	category
	 * 			The category of the exception.
	 */
	public IllegalCarOptionCategoryException(CarOptionCategory category) {
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
	public IllegalCarOptionCategoryException(CarOptionCategory category, String message) {
		super(message);
		this.setCategory(category);
	}
	
	/**
	 * Returns the category of the exception.
	 * 
	 * @return	The category that caused the exception.
	 */
	public CarOptionCategory getCategory() {
		return this.category;
	}

	private void setCategory(CarOptionCategory category) {
		this.category = category;
	}

}
