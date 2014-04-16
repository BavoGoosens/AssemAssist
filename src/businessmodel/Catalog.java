package businessmodel;

import java.util.ArrayList;
import java.util.UUID;

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
import businessmodel.category.Seats;
import businessmodel.category.Spoiler;
import businessmodel.category.Wheels;
import businessmodel.exceptions.IllegalCarOptionCategoryException;

/**
 * A class that represents an inventory for a factory. Here we hold all the different component.
 * 
 * @author SWOP team 10 2014
 *
 */
public class Catalog {
	
	private ArrayList<CarModel> available_models;
	private ArrayList<CarOptionCategory> categories;

	/**
	 * A Constructor that creates a new inventory list.
	 */
	public Catalog() {
		
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
}
