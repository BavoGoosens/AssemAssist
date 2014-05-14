package businessmodel.assemblyline;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestTimeSlot {
	
	private TimeSlot timeslot;
	private TestOrder testorder;
	
	@Before
	public void setUp() throws Exception {
		try {this.timeslot = new TimeSlot(-1);}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		try {this.timeslot = new TimeSlot(0);}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		this.timeslot = new TimeSlot(4);
	}

	@Test
	// nagaan of andere methodes getest worden door andere tests
	public void testAddOrder() {
		assertEquals(4,this.timeslot.getWorkSlots().size());
		this.timeslot.addOrderToWorkSlot(testorder.getOrder(), -1);
		this.timeslot.addOrderToWorkSlot(testorder.getOrder(), 10);
		this.timeslot.addOrderToWorkSlot(testorder.getOrder(), 1);
		assertTrue(this.timeslot.workSlotOccupied(1));
		}
}
