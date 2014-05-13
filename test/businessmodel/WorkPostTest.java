package businessmodel;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.category.ModelAFactory;
import businessmodel.order.Order;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;

public class WorkPostTest {
	
	private GarageHolder garageholder;
	private Order order;
	private OrderManager om;
	private Catalog catalog;
	private ArrayList<VehicleOptionCategory> categories;

	
	@Before
	public void setUp() throws Exception {
		VehicleManufacturingCompany company = new VehicleManufacturingCompany();
		om = company.getOrderManager();
		garageholder = new GarageHolder("bouwe", "ceunen", "bouwe");

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
		order = new StandardVehicleOrder(garageholder, chosen,modelA);
		company.placeOrder(order);
		
	}

	@Test
	public void test() {
		
		assertEquals(om.getScheduler().getAssemblyline().getWorkPosts().get(0).getOrder(),om.getScheduler().getOrdersClone().get(0));
	}

}
