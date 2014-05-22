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

public class ShiftTest {
	
	private StandardVehicleOrder order;
	private FreeShift shift;
	private EndShift endShift;

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
		this.endShift = new EndShift(8, 3);
		this.shift = new FreeShift(8, 3, endShift);
	}

	@Test
	public void test() {
		this.shift.addTimeSlot();
		assertEquals(this.shift.getTimeSlots().size(), 9);
		this.shift.removeLastTimeSlot();
		assertEquals(this.shift.getTimeSlots().size(), 8);
		ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();
		timeslots.add(shift.getTimeSlots().get(0));
		timeslots.add(shift.getTimeSlots().get(1));
		timeslots.add(shift.getTimeSlots().get(2));
		shift.addOrderToSlots(order, timeslots);
		assertEquals(order, shift.getTimeSlots().get(0).getNextOrder());
		TimeSlot slot = shift.getNextTimeSlot(shift.getTimeSlots().get(0));
		assertEquals(slot,shift.getTimeSlots().get(1));
	}
	
	@Test
	public void testFreeShift() {
		assertEquals(this.endShift, this.shift.getNextShift());
		ArrayList<TimeSlot> slots = new ArrayList<TimeSlot>();
		slots.add(this.shift.getTimeSlots().get(0));
		slots.add(this.shift.getTimeSlots().get(1));
		slots.add(this.shift.getTimeSlots().get(2));
		assertArrayEquals(slots.toArray(), this.shift.canAddOrder(order).toArray());
	}
	
	@Test
	public void testEndShift() {
		ArrayList<TimeSlot> slots = new ArrayList<TimeSlot>();
		slots.add(this.endShift.getTimeSlots().get(0));
		slots.add(this.endShift.getTimeSlots().get(1));
		slots.add(this.endShift.getTimeSlots().get(2));
		assertArrayEquals(slots.toArray(), this.endShift.canAddOrder(order).toArray());
	}
}
