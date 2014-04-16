package businessmodel;

import java.util.ArrayList;

import businessmodel.category.CarModelFactory;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.ModelAFactory;
import businessmodel.category.ModelBFactory;
import businessmodel.category.ModelCFactory;

/**
 * A class that represents an inventory for a factory. Here we hold all the different component.
 * 
 * @author SWOP team 10 2014
 *
 */
public class Catalog {
	
	ArrayList<CarModel> availableModels;
	ArrayList<CarModelFactory> factories;

	/**
	 * A Constructor that creates a new inventory list.
	 */
	public Catalog() {
		this.availableModels = new ArrayList<CarModel>();
		this.factories = new ArrayList<CarModelFactory>();
		this.factories.add(new ModelAFactory());
		this.factories.add(new ModelBFactory());
		this.factories.add(new ModelCFactory());
		this.createAllModels();
	}
	
	private ArrayList<CarModelFactory> getFactories() {
		return this.factories;
	}
	
	private ArrayList<CarModel> getAvailableModels() {
		return this.availableModels;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<CarModel> getAvailaleModelsClone() {
		return (ArrayList<CarModel>) this.getAvailableModels().clone();
	}
	
	private void createAllModels() {
		for (CarModelFactory factory: this.getFactories()) {
			this.getAvailableModels().add(factory.createModel());
		}
	}

	public ArrayList<CarOptionCategory> getAllCategories() {
		ArrayList<CarOptionCategory> categories = new ArrayList<CarOptionCategory>();
		for (CarModel model: this.getAvailableModels()) {
			for (CarOption option: model.getPossibilities()) {
				CarOptionCategory category = option.getCategory();
				if (!categories.contains(category)) {
					categories.add(category);
				}
			}
		}
		return categories;
	}
}
