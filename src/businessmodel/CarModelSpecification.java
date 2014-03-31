package businessmodel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents a car model specification.
 * It gives an overview of lists of Components that are available for a car model.
 * 
 * @author SWOP Team 10
 *
 */
public class CarModelSpecification {
	
	private ArrayList<OptionList> optionLists;

	/**
	 * This method constructs the car model specification.
	 * 
	 * @param 	parts 
	 * 			The list of components this specification consists of.
	 */
	public CarModelSpecification(ArrayList<OptionList> optionLists) throws IllegalArgumentException {
		this.setOptionLists(optionLists);
	}
	
	private ArrayList<OptionList> getOptionLists() {
		return this.optionLists;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<OptionList> getOptionListsClone() {
		return (ArrayList<OptionList>) this.optionLists.clone();
	}

	@SuppressWarnings("unchecked")
	private void setOptionLists(ArrayList<OptionList> optionLists) throws IllegalArgumentException {
		if (optionLists == null) throw new IllegalArgumentException("Bad list of option lists!");
		this.optionLists = (ArrayList<OptionList>) optionLists.clone();
	}
	
	/**
	 * A method to get all the components of this car model specification.
	 * 
	 * @return A list of list of all the components of a car model specification
	 */
	public ArrayList<CarOption> getPossibilities() {
		ArrayList<CarOption> possibilities = new ArrayList<CarOption>();
		for (OptionList list: this.getOptionLists()) {
			for (CarOption option: list.getOptionsClone()) {
				possibilities.add(option);
			}
		}
		return possibilities;
	}
}


