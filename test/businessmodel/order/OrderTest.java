package businessmodel.order;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.user.GarageHolder;
import businessmodel.user.Mechanic;

public class OrderTest {
	
	private StandardVehicleOrder svo;
	private ArrayList<VehicleOption> chosen;
	private GarageHolder gh;
	private VehicleModel model;
	
	@Before
	public void setUp() throws Exception {
		Catalog catalog = new Catalog();
		this.model = catalog.getAvailaleModelsClone().get(0);
		this.gh = new GarageHolder("Michiel", "Vandendriessche", "MichielVDD");
		this.chosen = new ArrayList<VehicleOption>();
		ArrayList<VehicleOptionCategory> categories = catalog.getAllCategories();
		for (VehicleOptionCategory category: categories) {
			if (model.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				this.chosen.add(model.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}
		}
		this.svo = new StandardVehicleOrder(this.gh, chosen, model);
	}

	@Test
	public void test() {
		assertArrayEquals(this.chosen.toArray(), this.svo.getOptions().toArray());
		assertEquals(this.gh, this.svo.getUser());
		assertFalse(this.svo.isCompleted());
		assertEquals(this.model.getStandardTimeToFinish(), this.svo.getStandardTimeToFinish());
		DateTime estimatedDeliveryDate = new DateTime(2014, 6, 2, 11, 0);
		this.svo.setEstimatedDeliveryDateOfOrder(estimatedDeliveryDate);
		assertEquals(estimatedDeliveryDate, this.svo.getEstimatedDeliveryDate());
		this.svo.updateEstimatedDate(20);
		assertEquals(estimatedDeliveryDate.plusMinutes(20), this.svo.getEstimatedDeliveryDate());
		DateTime timestamp = new DateTime(2014, 6, 1, 8, 0);
		this.svo.setTimestampOfOrder(timestamp);
		assertEquals(timestamp, this.svo.getTimestamp());
		DateTime timeOnAssemblyLine = new DateTime(2014, 6, 2, 8, 0);
		this.svo.setPlacedOnAssemblyLineOfOrder(timeOnAssemblyLine);
		assertEquals(timeOnAssemblyLine, this.svo.getOrderPlacedOnAssemblyLine());
		DateTime completionDate = new DateTime(2014, 6, 2, 14, 0);
		this.svo.setCompletionDateOfOrder(completionDate);
		assertEquals(completionDate, this.svo.getCompletionDate());
		this.svo.setCompleted();
		assertTrue(this.svo.isCompleted());
	}
	
	@Test (expected = NoClearanceException.class)
	public void testNoClearance() throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		Mechanic mechanic = new Mechanic("Bouwe", "Ceunen", "BouweC");
		new StandardVehicleOrder(mechanic, null, null);
	}

}
