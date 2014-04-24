package test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.CarModel;
import businessmodel.CarModelSpecification;
import businessmodel.Catalog;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;

public class CarModelSpecificationTest {
	
	private Catalog inventory;
	private CarModelSpecification modelASpec;

	@Before
	public void setUp() {
		inventory = new Catalog();
		CarModel model = inventory.getAvailaleModelsClone().get(0);
		
		modelASpec = model.getCarModelSpecification();
		
	}
	
	@Test
	public void testModel() {
		ArrayList<CarOption> options = new ArrayList<CarOption>();
		for (CarOptionCategory category: inventory.getAllCategories()) {
			options.addAll(modelASpec.getOptionsOfCategory(category));
		}
		System.out.println("Model A:");
		for (CarOption option: options) {
			System.out.println("-: "+option);
		}
	}

}
