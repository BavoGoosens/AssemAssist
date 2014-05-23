package businessmodel;

import java.util.ArrayList;

import businessmodel.category.ModelAFactory;
import businessmodel.category.ModelBFactory;
import businessmodel.category.ModelCFactory;
import businessmodel.category.ModelXFactory;
import businessmodel.category.ModelYFactory;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleModelFactory;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;


/**
 * A class representing a catalog of the car manufacturing company.
 * It holds all the models that are available and creates them through a factory.
 *
 * @author SWOP team 10
 *
 */
public class Catalog {


	private ArrayList<VehicleModel> availableModels;
	private ArrayList<VehicleModelFactory> factories;

	/**
	 * Creates a new catalog.
	 */
	public Catalog() {

		this.availableModels = new ArrayList<VehicleModel>();
		this.factories = new ArrayList<VehicleModelFactory>();
		this.factories.add(new ModelAFactory());
		this.factories.add(new ModelBFactory());
		this.factories.add(new ModelCFactory());
        this.factories.add(new ModelXFactory());
        this.factories.add(new ModelYFactory());
		this.createAllModels();
	}

	/**
	 * Returns all the car option categories that are available.
	 *
	 * @return	A list of all car option categories that exist within the available car models.
	 */
	public ArrayList<VehicleOptionCategory> getAllCategories() {
		ArrayList<VehicleOptionCategory> categories = new ArrayList<VehicleOptionCategory>();
		for (VehicleModel model: this.getAvailableModels()) {
			for (VehicleOption option: model.getPossibilities()) {
				VehicleOptionCategory category = option.getCategory();
				if (!categories.contains(category)) {
					categories.add(category);
				}
			}
		}
		return categories;
	}

	/**
	 * Returns a cloned list of all the models that are available.
	 *
	 * @return A cloned list of all available models.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<VehicleModel> getAvailaleModelsClone() {
		return (ArrayList<VehicleModel>) this.getAvailableModels().clone();
	}

	/**
	 * Method to get all the options of a car option category.
	 *
	 * @param cat
	 * @return
	 */
	public ArrayList<VehicleOption> getAllOptions(VehicleOptionCategory cat) {
		ArrayList<VehicleOption> res = new ArrayList<VehicleOption>();
		for (VehicleModel model: this.getAvailableModels()){
			for (VehicleOption option: model.getPossibilities()) {
				if (option.getCategory().equals(cat))
					res.add(option);
			}
		}
		return res;
	}

	/**
	 * Method to create all the car models.
	 */
	private void createAllModels() {
		for (VehicleModelFactory factory: this.getFactories()) {
			this.getAvailableModels().add(factory.createModel());
		}
	}

	/**
	 * Returns the factories.
	 * @return The factories in the catalog.
	 */
	private ArrayList<VehicleModelFactory> getFactories() {
		return this.factories;
	}

	/**
	 * Returns the available car models.
	 * @return The available car models in the catalog.
	 */
	private ArrayList<VehicleModel> getAvailableModels() {
		return this.availableModels;
	}

}
