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
	 * The wrong cate
	 */
	private CarOptionCategory category;

	public IllegalCarOptionCategoryException() {}

	public IllegalCarOptionCategoryException(String message) {
		super(message);
	}
	
	public IllegalCarOptionCategoryException(CarOptionCategory category) {
		this.category = category;
	}
	
	public IllegalCarOptionCategoryException(CarOptionCategory category, String message) {
		super(message);
		this.category = category;
	}
	
	public CarOptionCategory getCategory() {
		return this.category;
	}

}
