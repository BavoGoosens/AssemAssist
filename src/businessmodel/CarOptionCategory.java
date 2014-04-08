package businessmodel;

import java.util.ArrayList;

import exceptions.IllegalCarOptionCategoryException;

public class CarOptionCategory {
	
	private String name;
	private ArrayList<CarOption> possibleOptions;

	public CarOptionCategory(String name) throws IllegalArgumentException {
		this.possibleOptions = new ArrayList<CarOption>();
		this.setName(name);
	}
	
	public CarOptionCategory(String name, ArrayList<CarOption> possibleOptions) throws IllegalArgumentException, IllegalCarOptionCategoryException {
		this(name);
		this.setPossibleOptions(possibleOptions);
	}
	
	public String getName() {
		return this.name;
	}
	
	private ArrayList<CarOption> getPossibleOptions() {
		return this.possibleOptions;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<CarOption> getPossibleOptionsClone() {
		return (ArrayList<CarOption>) this.possibleOptions.clone();
	}
	
	private void setName(String name) throws IllegalArgumentException {
		if (name == null || name.equals("")) throw new IllegalArgumentException("Bad name!");
		this.name = name;
	}
	
	@SuppressWarnings("unchecked")
	private void setPossibleOptions(ArrayList<CarOption> possibleOptions) throws IllegalArgumentException,
																				 IllegalCarOptionCategoryException {
		if (possibleOptions == null) throw new IllegalArgumentException("Bad list of possible options!");
		if (!this.canHaveAsPossibleOptions(possibleOptions)) 
			throw new IllegalCarOptionCategoryException("List contains option(s) of wrong category!");
		this.possibleOptions = (ArrayList<CarOption>) possibleOptions.clone();
	}
	
	public void addPossibleOption(CarOption possibleOption) throws IllegalArgumentException, IllegalCarOptionCategoryException {
		if (possibleOption == null) throw new IllegalArgumentException("Bad option!");
		if (!this.canHaveAsPossibleOption(possibleOption)) throw new IllegalCarOptionCategoryException(possibleOption.getCategory(), "Option of wrong category!");
		this.getPossibleOptions().add(possibleOption);
	}
	
	public void removePossibleOption(CarOption possibleOption) throws IllegalArgumentException {
		if (possibleOption == null) throw new IllegalArgumentException("Bad option!");
		this.getPossibleOptions().remove(possibleOption);
	}
	
	public boolean hasAsPossibleOption(CarOption possibleOption) throws IllegalArgumentException {
		if (possibleOption == null) throw new IllegalArgumentException("Bad option!");
		return this.getPossibleOptions().contains(possibleOption);
	}

	private boolean canHaveAsPossibleOptions(ArrayList<CarOption> possibleOptions) {
		for (CarOption option: possibleOptions) {
			if (!this.canHaveAsPossibleOption(option)) return false;
		}
		return true;
	}
	
	private boolean canHaveAsPossibleOption(CarOption option) {
		return option.getCategory() == this;
	}

}
