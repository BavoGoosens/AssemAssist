package businessmodel;

import java.util.ArrayList;

import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.CarModelFactory;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.Gearbox;
import businessmodel.category.ModelAFactory;
import businessmodel.category.ModelBFactory;
import businessmodel.category.ModelCFactory;
<<<<<<< HEAD
import businessmodel.category.Seats;
import businessmodel.category.Spoiler;
import businessmodel.category.Wheels;
import businessmodel.exceptions.IllegalCarOptionCategoryException;
=======
>>>>>>> 10f1a3a1e1567f9a33494156d9341b89044c7497

/**
 * A class that represents an inventory for a factory. Here we hold all the different component.
 * 
 * @author SWOP team 10 2014
 *
 */
public class Catalog {
	
<<<<<<< HEAD
	private ArrayList<CarModel> available_models;
	private ArrayList<CarOptionCategory> categories;
=======
	ArrayList<CarModel> availableModels;
	ArrayList<CarModelFactory> factories;
>>>>>>> 10f1a3a1e1567f9a33494156d9341b89044c7497

	/**
	 * A Constructor that creates a new inventory list.
	 */
	public Catalog() {
<<<<<<< HEAD
		
		this.available_models = new ArrayList<CarModel>();
		this.createCategories();
		this.createAllModels();
	}
	

	private void createCategories() {
		
		categories = new ArrayList<CarOptionCategory>();
		
		categories.add(new Airco().create());
		categories.add(new Seats().create());
		categories.add(new Body().create());
		categories.add(new Color().create());
		categories.add(new Spoiler().create());
		categories.add(new Wheels().create());
		categories.add(new Gearbox().create());
		categories.add(new Engine().create());
		
	}


	private void createAllModels() {
		
		CarModelFactory factoryA = new ModelAFactory();
		CarModelFactory factoryB = new ModelBFactory();
		CarModelFactory factoryC = new ModelCFactory();
		
		available_models.add(factoryA.createModel());
		available_models.add(factoryB.createModel());
		available_models.add(factoryC.createModel());
	}

	public ArrayList<CarOptionCategory> getAllCategories() {
		return categories;
	}
	
	public CarOptionCategory getCategory(UUID id) {
		for(CarOptionCategory category: categories)
			if (category.getKey().equals(id))
				return category;
		return null;
	}
=======
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
>>>>>>> 10f1a3a1e1567f9a33494156d9341b89044c7497
}
