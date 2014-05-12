package businessmodel;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import businessmodel.CarManufacturingCompany;
import businessmodel.VehicleModel;
import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.ModelAFactory;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;

public class OrderTest {
	
	private GarageHolder garageholder;
	private DateTime date;
	private ArrayList<CarOptionCategory> categories;
	private Catalog catalog;
	private OrderManager om;
	@Before
	public void setUp() throws Exception {
		
		CarManufacturingCompany cmc = new CarManufacturingCompany();
		
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
		cmc.placeOrder(new StandardCarOrder(garageholder, chosen, modelA));
		
		date = om.getScheduler().getCurrentTime().plusHours(3);
	}

	@Test
	public void test() {

		assertEquals(om.getScheduler().getOrdersClone().get(0).getUser(),this.garageholder);
		assertEquals(om.getScheduler().getOrdersClone().get(0).getEstimatedDeliveryDate(),this.date);
		assertEquals(om.getScheduler().getOrdersClone().get(0).isCompleted(),false);

		
	}

}
