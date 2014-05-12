package businessmodel;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessmodel.order.StandardVehicleOrder;
import org.junit.Before;
import org.junit.Test;

import businessmodel.CarManufacturingCompany;
import businessmodel.VehicleModel;
import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.ModelAFactory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.user.GarageHolder;

public class OrderManagerTest {

	private CarManufacturingCompany cmc;
	private OrderManager om;
	private GarageHolder garageholder;
	private Order order;
	CarOption option;
	CarOption option2;
	private Catalog catalog;
	private ArrayList<CarOptionCategory> categories;

	@Before
	public void setUp() throws Exception {
		cmc = new CarManufacturingCompany();
		om = cmc.getOrderManager();
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
		order = new StandardVehicleOrder(garageholder, chosen, modelA);
		cmc.placeOrder(order);
	}

	@Test
	public void test() {
		try {
			
			assertTrue(om.getScheduler().getOrdersClone().contains(order));
			Iterator<Order> iter = cmc.getPendingOrders(garageholder);
			List<Order> copy = new ArrayList<Order>();
			while (iter.hasNext())
			    copy.add(iter.next());			
			assertTrue(copy.contains(order));
	
			

		} catch (IllegalArgumentException | NoClearanceException e) {
			System.out.println(e.getMessage());
		}
	}

}
