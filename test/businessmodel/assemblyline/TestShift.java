package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.order.Order;

public class TestShift {
	
	private TestOrder testorder;
	private Shift shift;

	@Before
	public void setUp() throws Exception {
		this.testorder = new TestOrder();
		EndShift shift1 = new EndShift(8,3);
		this.shift = new FreeShift(8,3,shift1);
	}

	@Test
	// TODO iets met de catch doen.
	public void test() {
		this.shift.addTimeSlot();
		assertEquals(this.shift.getTimeSlots().size(), 9);
		this.shift.removeLastTimeSlot();
		assertEquals(this.shift.getTimeSlots().size(), 8);
		ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();
		timeslots.add(shift.getTimeSlots().get(0));
		timeslots.add(shift.getTimeSlots().get(1));
		timeslots.add(shift.getTimeSlots().get(2));
		shift.addOrderToSlots(testorder.getOrder(), timeslots);
		TimeSlot slot = shift.getNextTimeSlot(shift.getTimeSlots().get(0));
		assertEquals(slot,shift.getTimeSlots().get(1));
		try{slot = shift.getNextTimeSlot(shift.getTimeSlots().get(7)); }
		catch (IndexOutOfBoundsException e) {	
		}
		Order order = shift.getNextOrderForAssemblyLine();
	}
}
