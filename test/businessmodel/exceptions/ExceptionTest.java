package businessmodel.exceptions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import businessmodel.order.StandardVehicleOrder;
import org.junit.Before;
import org.junit.Test;

import businessmodel.VehicleManufacturingCompany;
import businessmodel.category.VehicleModel;
import businessmodel.Catalog;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.category.ModelAFactory;
import businessmodel.user.Manager;



public class ExceptionTest {
	private Manager manager;
	private Catalog catalog;
	private ArrayList<VehicleOptionCategory> categories;

	@Before
	public void setUp() throws Exception {
		VehicleManufacturingCompany cmc = new VehicleManufacturingCompany();

		manager = new Manager("bouwe", "ceunen", "bouwe");
		
		this.catalog = new Catalog();
		this.categories = this.catalog.getAllCategories();

		VehicleModel modelA = new ModelAFactory().createModel();
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOptionCategory category: this.categories) {
			ArrayList<VehicleOption> options = modelA.getVehicleModelSpecification().getOptionsOfCategory(category);
			if (options.size() > 0) {
				chosen.add(options.get(0));
			}

		}
		try{
		cmc.placeOrder(new StandardVehicleOrder(manager, chosen,modelA));
		
		}catch (NoClearanceException ex){
			assertEquals(ex.getUser(), manager);
		}
		
	}

	@Test
	public void test() {
		
	}
}
