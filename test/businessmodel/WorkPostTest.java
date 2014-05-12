package businessmodel;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.CarManufacturingCompany;
import businessmodel.VehicleModel;
import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.ModelAFactory;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;

public class WorkPostTest {
	
	private GarageHolder garageholder;
	private Order order;
	private OrderManager om;
	private Catalog catalog;
	private ArrayList<CarOptionCategory> categories;

	
	@Before
	public void setUp() throws Exception {
		CarManufacturingCompany company = new CarManufacturingCompany();
		om = company.getOrderManager();
		garageholder = new GarageHolder("bouwe", "ceunen", "bouwe");

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
		order = new StandardCarOrder(garageholder, chosen,modelA);
		company.placeOrder(order);
		
	}

	@Test
	public void test() {
		
		assertEquals(om.getScheduler().getAssemblyline().getWorkPosts().get(0).getOrder(),om.getScheduler().getOrdersClone().get(0));
	}

}
