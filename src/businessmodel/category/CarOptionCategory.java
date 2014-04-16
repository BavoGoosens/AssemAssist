package businessmodel.category;

import java.util.ArrayList;
import java.util.UUID;

import businessmodel.exceptions.IllegalCarOptionCategoryException;

public abstract class CarOptionCategory {
	
	private ArrayList<CarOption> options;

	protected CarOptionCategory() {
		this.options = new ArrayList<CarOption>();
	}
	
	public abstract UUID getKey();
	public abstract CarOptionCategory create(); 
	
	private ArrayList<CarOption> getOptions() {
		return this.options;
	}
	
	@SuppressWarnings("unchecked")
	protected ArrayList<CarOption> getOptionsClone() {
		return (ArrayList<CarOption>) this.options.clone();
	}
	
	protected void addOption(CarOption option) throws IllegalArgumentException, IllegalCarOptionCategoryException {
		if (option == null) throw new IllegalArgumentException("Bad option!");
		if (!this.canHaveAsOption(option)) throw new IllegalCarOptionCategoryException("Wrong category!");
		this.getOptions().add(option);
	}
	
	protected void removeOption(CarOption option) throws IllegalArgumentException {
		if (option == null) throw new IllegalArgumentException("Bad option!");
		this.getOptions().remove(option);
	}
	
	protected CarOption getOptionWithName(String name) throws IllegalArgumentException {
		if (name == null) throw new IllegalArgumentException("Bad name!");
		for (CarOption option: this.getOptions()) {
			if (option.getName().equalsIgnoreCase(name)) return option;
		}
		throw new IllegalArgumentException("No option found with that name!");
	}
	
	private boolean canHaveAsOption(CarOption option) throws IllegalArgumentException {
		if (option == null) throw new IllegalArgumentException("Bad option!");
		return this.equals(option.getCategory());
	}
	
	@Override
	public boolean equals(Object category) throws IllegalArgumentException {
		if (category == null) throw new IllegalArgumentException("Bad category!");
		//todo: checken of category een CarOptionCategory is!
		return this.getKey().equals(((CarOptionCategory) category).getKey());
	}
	
	@Override
	public String toString() {
		return this.getClass().getName().toUpperCase();
	}

}
