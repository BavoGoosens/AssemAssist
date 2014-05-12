package businessmodel;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.VehicleModel;
import businessmodel.VehicleModelSpecification;
import businessmodel.Catalog;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;

public class VehicleModelSpecificationTest {
	
	private Catalog inventory;
	private VehicleModelSpecification modelASpec;

	@Before
	public void setUp() {
		inventory = new Catalog();
		VehicleModel model = inventory.getAvailaleModelsClone().get(0);
		
		modelASpec = model.getVehicleModelSpecification();
		
	}
	
	@Test
	public void testModel() {
		ArrayList<CarOption> options = new ArrayList<CarOption>();
		for (CarOptionCategory category: inventory.getAllCategories()) {
			options.addAll(modelASpec.getOptionsOfCategory(category));
		}
	}
}
