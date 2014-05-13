package businessmodel;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.VehicleModel;
import businessmodel.Catalog;
import businessmodel.category.VehicleOptionCategory;

public class CatalogTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		Catalog catalog = new Catalog();
		ArrayList<VehicleOptionCategory> categories = catalog.getAllCategories();
		for (VehicleOptionCategory category: categories) {
			assertNotNull(category);
		}
	}
	
	@Test
	public void test2() {
		Catalog catalog = new Catalog();
		ArrayList<VehicleModel> models = catalog.getAvailaleModelsClone();
		for (VehicleModel model: models) {
			assertNotNull(model);
		}
	}

}
