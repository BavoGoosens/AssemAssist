package businessmodel.assemblyline;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;

public class TimeSlotTest {
	
	private TimeSlot timeslot;
	private StandardVehicleOrder order;
	
	@Before
	// TODO iets doen met de catch
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

		this.timeslot = new TimeSlot(4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWrongTimeSlot() {
		new TimeSlot(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWrongTimeSlot2() {
		new TimeSlot(0);
	}
	
	@Test
	public void testAddOrder() {
		assertEquals(4, this.timeslot.getWorkSlots().size());
		this.timeslot.addOrderToWorkSlot(order, 3);
		this.timeslot.addOrderToWorkSlot(order, 3);
		this.timeslot.addOrderToWorkSlot(order, 0);
		assertTrue(this.timeslot.workSlotOccupied(0));
		assertFalse(this.timeslot.workSlotOccupied(1));
		assertFalse(this.timeslot.workSlotOccupied(2));
		assertTrue(this.timeslot.workSlotOccupied(3));
		assertEquals(this.order, this.timeslot.getLastOrderOfLastWorkSLot());
		assertEquals(this.order, this.timeslot.getNextOrder());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testAddOrder2() {
		this.timeslot.addOrderToWorkSlot(this.order, -1);
		this.timeslot.addOrderToWorkSlot(this.order, 10);
	}
}
