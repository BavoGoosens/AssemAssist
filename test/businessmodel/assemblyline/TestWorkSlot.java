package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import businessmodel.user.GarageHolder;
import businessmodel.util.TestStandardVehicleOrder;

public class TestWorkSlot {

	private WorkSlot workslot;
	private TestStandardVehicleOrder testorder;

	@Before
	public void setUp() throws Exception {
		this.workslot = new WorkSlot();
		testorder = new TestStandardVehicleOrder(new GarageHolder("","",""),"Car Model A");
	}

	@Test
	// TODO Iets doen met de IllegalArgumentException
	public void test() {

		try {workslot.addOrder(null);}
		catch (IllegalArgumentException e){
			
		}
		workslot.addOrder(testorder.getOrder());
		assertEquals(workslot.getOrder(),testorder.getOrder());
		assertTrue(this.workslot.isOccupied());
	}
}
