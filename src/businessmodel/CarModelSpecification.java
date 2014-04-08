package businessmodel;

import java.util.ArrayList;

/**
 * This class represents a car model specification.
 * It gives an overview of lists of Components that are available for a car model.
 * 
 * @author SWOP Team 10
 *
 */
public class CarModelSpecification {
	
	private ArrayList<CarOption> options;

	/**
	 * This method constructs the car model specification.
	 * 
	 * @param 	parts 
	 * 			The list of components this specification consists of.
	 */
	public CarModelSpecification(ArrayList<CarOption> options) throws IllegalArgumentException {
		this.setOptions(options);
	}
	
	private ArrayList<CarOption> getOptions() {
		return this.options;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<CarOption> getOptionsClone() {
		return (ArrayList<CarOption>) this.getOptions().clone();
	}
	
	public ArrayList<CarOption> getOptionsOfCategory(CarOptionCategory category) throws IllegalArgumentException {
		if (category == null) throw new IllegalArgumentException("Bad category!");
		ArrayList<CarOption> options = new ArrayList<CarOption>();
		for (CarOption option: this.getOptions()) {
			if (option.getCategory() == category) {
				options.add(option);
			}
		}
		return options;
	}

	@SuppressWarnings("unchecked")
	private void setOptions(ArrayList<CarOption> options) throws IllegalArgumentException {
		if (options == null) throw new IllegalArgumentException("Bad list of options!");
		this.options = (ArrayList<CarOption>) options.clone();
	}
}


