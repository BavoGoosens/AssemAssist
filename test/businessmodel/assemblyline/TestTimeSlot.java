package businessmodel.assemblyline;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestTimeSlot {
	
	private TimeSlot timeslot;
	private TestOrder testorder;
	
	@Before
	// TODO iets doen met de catch
	public void setUp() throws Exception {
		testorder = new TestOrder();
		
		try {this.timeslot = new TimeSlot(-1);}
		catch (IllegalArgumentException e) {
			
		}
		try {this.timeslot = new TimeSlot(0);}
		catch (IllegalArgumentException e) {
			
		}
		this.timeslot = new TimeSlot(4);
	}

	@Test
	// TODO iets doen met de catch
	public void testAddOrder() {
		assertEquals(4,this.timeslot.getWorkSlots().size());
		try {this.timeslot.addOrderToWorkSlot(testorder.getOrder(), -1);}
		catch (IllegalArgumentException e) {
			
		}
		try {this.timeslot.addOrderToWorkSlot(testorder.getOrder(), 10);}
		catch (IllegalArgumentException e) {
			
		}
		this.timeslot.addOrderToWorkSlot(testorder.getOrder(), 3);
		this.timeslot.addOrderToWorkSlot(testorder.getOrder(), 0);
		assertTrue(this.timeslot.workSlotOccupied(3));
		
		assertEquals(testorder.getOrder(),this.timeslot.getLastOrderOfLastWorkSLot());
		assertEquals(testorder.getOrder(),this.timeslot.getNextOrder());
		}
}
