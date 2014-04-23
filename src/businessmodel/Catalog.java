package businessmodel;

import java.util.ArrayList;

import businessmodel.category.*;


/**
 * A class representing a catalog of the car manufacturing company.
 * It holds all the models that are available and creates them through a factory.
 * 
 * @author SWOP team 10 2014
 *
 */
public class Catalog {
	

	private ArrayList<CarModel> availableModels;
	private ArrayList<CarModelFactory> factories;

	/**
	 * Creates a new catalog.
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
	
	/**
	 * Returns a cloned list of all the models that are available.
	 * 
	 * @return A cloned list of all available models.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CarModel> getAvailaleModelsClone() {
		return (ArrayList<CarModel>) this.getAvailableModels().clone();
	}
	
	private void createAllModels() {
		for (CarModelFactory factory: this.getFactories()) {
			this.getAvailableModels().add(factory.createModel());
		}
	}
	
	/**
	 * Returns all the car option categories that are available.
	 * 
	 * @return	A list of all car option categories that exist within the available car models.
	 */
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
