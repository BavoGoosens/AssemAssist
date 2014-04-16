package businessmodel.exceptions;

import businessmodel.category.CarOptionCategory;


public class IllegalCarOptionCategoryException extends Exception {
	
	private static final long serialVersionUID = 3241001476462475004L;
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
