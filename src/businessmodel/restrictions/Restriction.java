package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;

public abstract class Restriction {
	
	private String name;
	private Catalog catalog;
	private ArrayList<CarOption> mandatoryOptions;
	private ArrayList<CarOptionCategory> mandatoryCategories;

	public Restriction(String name, Catalog catalog) throws IllegalArgumentException {
		if (name == null || name.equals("")) throw new IllegalArgumentException("Bad name!");
		if (catalog == null) throw new IllegalArgumentException("Bad catalog!");
		this.name = name;
		this.catalog = catalog;
		this.mandatoryOptions = new ArrayList<CarOption>();
		this.mandatoryCategories = new ArrayList<CarOptionCategory>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public Catalog getCatalog() {
		return this.catalog;
	}
	
	public ArrayList<CarOption> getMandatoryOptions() {
		return this.mandatoryOptions;
	}
	
	public ArrayList<CarOptionCategory> getMandatoryCategories() {
		return this.mandatoryCategories;
	}
	
	public abstract boolean check(ArrayList<CarOption> options);
	
	protected void clearMandatoryOptions() {
		this.mandatoryOptions.clear();
	}
	
	protected void clearMandatoryCategories() {
		this.mandatoryCategories.clear();
	}
	
	protected void addMandatoryOptions(ArrayList<CarOption> options) {
		if (options == null) throw new IllegalArgumentException("Bad list of options!");
		for (CarOption option: options) {
			this.addMandatoryOption(option);
		}
	}
	
	protected void addMandatoryCategories(ArrayList<CarOptionCategory> categories) {
		if (categories == null) throw new IllegalArgumentException("Bad list of categories!");
		for (CarOptionCategory category: categories) {
			this.addMandatoryCategory(category);
		}
	}
	
	protected void addMandatoryOption(CarOption option) {
		if (option == null) throw new IllegalArgumentException("Bad option!");
		this.getMandatoryOptions().add(option);
	}
	
	protected void addMandatoryCategory(CarOptionCategory category) {
		if (category == null) throw new IllegalArgumentException("Bad category!");
		this.getMandatoryCategories().add(category);
	}

}
