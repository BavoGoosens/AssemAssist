package businessmodel.assemblyline;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;
import businessmodel.util.TestStandardVehicleOrder;

public class TestWorkSlot {

	private WorkSlot workslot;
	private StandardVehicleOrder order;

	@Before
	public void setUp() throws Exception {
		Catalog catalog = new Catalog();
		VehicleModel model = catalog.getAvailaleModelsClone().get(0);
		ArrayList<VehicleOptionCategory> categories = catalog.getAllCategories();
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOptionCategory category: categories) {
			if (model.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				chosen.add(model.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}
		}
		this.order = new StandardVehicleOrder(new GarageHolder("Michiel", "Vandendriessche", "MichielVDD"), chosen, model);
		this.workslot = new WorkSlot();
	}
	
	@Test
	public void test() {
		this.workslot.addOrder(this.order);
		assertEquals(this.order, workslot.getOrder());
		assertTrue(this.workslot.isOccupied());
		this.workslot.removeOrder();
		assertFalse(this.workslot.isOccupied());
	}
}
