package businessmodel.assemblyline;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleModelSpecification;
import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;

public class TestWorkSlot {

	private WorkSlot workslot;
	private TestOrder testorder;

	@Before
	public void setUp() throws Exception {
		this.workslot = new WorkSlot();
	}

	@Test
	public void test() {

		try {workslot.addOrder(null);}
		catch (IllegalArgumentException e){
			e.printStackTrace();
		}
		workslot.addOrder(testorder.getOrder());
		assertTrue(this.workslot.isOccupied());
	}
}
