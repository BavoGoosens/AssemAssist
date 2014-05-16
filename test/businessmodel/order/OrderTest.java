package businessmodel.order;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.category.ModelAFactory;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;

public class OrderTest {
	
	private GarageHolder garageholder;
	private DateTime date;
	private ArrayList<VehicleOptionCategory> categories;
	private Catalog catalog;
	private OrderManager om;
	@Before
	public void setUp() throws Exception {
		
		VehicleManufacturingCompany cmc = new VehicleManufacturingCompany();
		
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
		cmc.placeOrder(new StandardVehicleOrder(garageholder, chosen, modelA));
		
		date = om.getMainScheduler().getAssemblyLineSchedulers().get(0).getCurrentTime().plusHours(3);
	}

	@Test
	public void test() {

		assertEquals(om.getMainScheduler().getAssemblyLineSchedulers().get(0).getOrdersClone().get(0).getUser(),this.garageholder);
		assertEquals(om.getMainScheduler().getAssemblyLineSchedulers().get(0).getOrdersClone().get(0).getEstimatedDeliveryDate(),this.date);
		assertEquals(om.getMainScheduler().getAssemblyLineSchedulers().get(0).getOrdersClone().get(0).isCompleted(),false);

		
	}

}
