package businessmodel.order;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.category.ModelAFactory;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.user.GarageHolder;

public class OrderTest {
	
	private GarageHolder garageholder;
	private DateTime date;
	private ArrayList<VehicleOptionCategory> categories;
	private Catalog catalog;
	private VehicleManufacturingCompany vmc;
	@Before
	public void setUp() throws Exception {
		
		vmc = new VehicleManufacturingCompany();
		
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
		vmc.placeOrder(new StandardVehicleOrder(garageholder, chosen, modelA));
		
		date = vmc.getAssemblyLines(garageholder).next().getAssemblyLineScheduler().getCurrentTime().plusHours(3);
	}

	@Test
	public void test() {

		try{
		assertEquals(vmc.getAssemblyLines(vmc.login("wowww", "")).next().getAssemblyLineScheduler().getOrders().get(0).getUser(),this.garageholder);
		assertEquals(vmc.getAssemblyLines(vmc.login("wowww", "")).next().getAssemblyLineScheduler().getOrders().get(0).getEstimatedDeliveryDate(),this.date);
		assertEquals(vmc.getAssemblyLines(vmc.login("wowww", "")).next().getAssemblyLineScheduler().getOrders().get(0).isCompleted(),false);
		}catch(NoClearanceException ex){
			System.out.println(ex.toString());
		}
		
	}

}
