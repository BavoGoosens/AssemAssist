package businessmodel;

import java.util.ArrayList;
import java.util.UUID;

import businessmodel.category.CarModelFactory;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.ModelAFactory;
import businessmodel.category.ModelBFactory;
import businessmodel.category.ModelCFactory;
import businessmodel.exceptions.IllegalCarOptionCategoryException;

/**
 * A class that represents an inventory for a factory. Here we hold all the different component.
 * 
 * @author SWOP team 10 2014
 *
 */
public class Catalog {
	
	ArrayList<CarModel> available_models;

	/**
	 * A Constructor that creates a new inventory list.
	 */
	public Catalog() {
		this.available_models = new ArrayList<>();
		this.createAllModels();
	}
	
	private void createAllModels() {
		CarModelFactory factoryA = new ModelAFactory();
		CarModelFactory factoryB = new ModelBFactory();
		CarModelFactory factoryC = new ModelCFactory();
		
		available_models.add(factoryA.createModel());
		available_models.add(factoryB.createModel());
		available_models.add(factoryC.createModel());
	}

	public ArrayList<CarOptionCategory> getAllCategories() {}
	
	public CarOptionCategory getCategory(UUID id) {}
}
