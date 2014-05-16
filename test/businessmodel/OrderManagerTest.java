package businessmodel;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessmodel.order.StandardVehicleOrder;
import org.junit.Before;
import org.junit.Test;

import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.category.ModelAFactory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.user.GarageHolder;

public class OrderManagerTest {

	private VehicleManufacturingCompany cmc;
	private OrderManager om;
	private GarageHolder garageholder;
	private Order order;
	VehicleOption option;
	VehicleOption option2;
	private Catalog catalog;
	private ArrayList<VehicleOptionCategory> categories;

	@Before
	public void setUp() throws Exception {
		cmc = new VehicleManufacturingCompany();
		om = cmc.getOrderManager();
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
		order = new StandardVehicleOrder(garageholder, chosen, modelA);
		cmc.placeOrder(order);
	}

	@Test
	public void test() {
		try {
			
			assertTrue(om.getMainScheduler().getAssemblyLineSchedulers().get(0).getOrdersClone().contains(order));
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
