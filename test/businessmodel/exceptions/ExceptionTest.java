package businessmodel.exceptions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.CarManufacturingCompany;
import businessmodel.VehicleModel;
import businessmodel.Catalog;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.ModelAFactory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.Manager;



public class ExceptionTest {
	private Manager manager;
	private Catalog catalog;
	private ArrayList<CarOptionCategory> categories;

	@Before
	public void setUp() throws Exception {
		CarManufacturingCompany cmc = new CarManufacturingCompany();

		manager = new Manager("bouwe", "ceunen", "bouwe");
		
		this.catalog = new Catalog();
		this.categories = this.catalog.getAllCategories();

		VehicleModel modelA = new ModelAFactory().createModel();
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		for (CarOptionCategory category: this.categories) {
			ArrayList<CarOption> options = modelA.getVehicleModelSpecification().getOptionsOfCategory(category);
			if (options.size() > 0) {
				chosen.add(options.get(0));
			}

		}
		try{
		cmc.placeOrder(new StandardCarOrder(manager, chosen,modelA));
		
		}catch (NoClearanceException ex){
			assertEquals(ex.getUser(), manager);
		}
		
	}

	@Test
	public void test() {
		
	}
}