package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import businessmodel.MainScheduler;
import businessmodel.OrderManager;

public class AssemblyLineStateTest {
	
	AssemblyLine assemblyLine;
	
	@Before
	public void setUp() throws Exception {
		this.assemblyLine = new AssemblyLineAFactory().createAssemblyLine(new MainScheduler(new OrderManager()));
	}

	@Test
	public void test() {
		assertTrue(assemblyLine.getOperationalState() instanceof OperationalState);
		assertTrue(assemblyLine.getMaintenanceState() instanceof MaintenanceState);
		assertTrue(assemblyLine.getBrokenState() instanceof BrokenState);
		assertEquals(assemblyLine.currentState(), "Operational");
		assemblyLine.transitionToBroken();
		assertEquals(assemblyLine.currentState(), "Broken");
		assemblyLine.transitionToMaintenance();
		assertEquals(assemblyLine.currentState(), "Maintenance");
	}
	
	

}
