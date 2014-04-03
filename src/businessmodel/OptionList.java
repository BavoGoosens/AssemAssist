package businessmodel;

import java.util.ArrayList;

import businessmodel.exceptions.IllegalCarOptionCategoryException;


public class OptionList {
	
	private final CarOptionCategory category;
	private ArrayList<CarOption> options;

	public OptionList(CarOptionCategory category) throws IllegalArgumentException, IllegalCarOptionCategoryException{
		if (category == null) throw new IllegalArgumentException("Bad category!");
		this.category = category;
		this.setOptions(new ArrayList<CarOption>());
	}
	
	public OptionList(CarOptionCategory category, ArrayList<CarOption> options) throws IllegalArgumentException, IllegalCarOptionCategoryException {
		this(category);
		this.setOptions(options);
	}
	
	public CarOptionCategory getCategory() {
		return this.category;
	}
	
	private ArrayList<CarOption> getOptions() {
		return this.options;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<CarOption> getOptionsClone() {
		return (ArrayList<CarOption>) this.options.clone();
	}
	
	@SuppressWarnings("unchecked")
	private void setOptions(ArrayList<CarOption> options) throws IllegalArgumentException,
																 IllegalCarOptionCategoryException {
		if (options == null) throw new IllegalArgumentException("Bad list of options!");
		if (!this.canHaveAsOptions(options)) 
			throw new IllegalCarOptionCategoryException("List contains option(s) of wrong category!");
		this.options = (ArrayList<CarOption>) options.clone();
	}
	
	public void addOption(CarOption option) throws IllegalArgumentException, IllegalCarOptionCategoryException {
		if (option == null) throw new IllegalArgumentException("Bad option!");
		if (!this.canHaveAsOption(option)) throw new IllegalCarOptionCategoryException(option.getCategory(), "Option of wrong category!");
		this.getOptions().add(option);
	}
	
	public void removePossibleOption(CarOption option) throws IllegalArgumentException {
		if (option == null) throw new IllegalArgumentException("Bad option!");
		this.getOptions().remove(option);
	}
	
	public boolean hasAsPossibleOption(CarOption option) throws IllegalArgumentException {
		if (option == null) throw new IllegalArgumentException("Bad option!");
		return this.getOptions().contains(option);
	}

	private boolean canHaveAsOptions(ArrayList<CarOption> options) {
		for (CarOption option: options) {
			if (!this.canHaveAsOption(option)) return false;
		}
		return true;
	}
	
	private boolean canHaveAsOption(CarOption option) {
		return option.getCategory().hasAsPossibleOption(option);
	}

}
