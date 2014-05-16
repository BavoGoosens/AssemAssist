package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestWorkSlot {

	private WorkSlot workslot;
	private TestOrder testorder;

	@Before
	public void setUp() throws Exception {
		this.workslot = new WorkSlot();
		testorder = new TestOrder();
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