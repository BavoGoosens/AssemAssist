package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Mechanic;
import control.Controller;

public class ControllerTest {
	
	private Mechanic mechanic;

	@Before
	public void setUp() throws Exception {
		mechanic = new Mechanic("Sander","Geijsen","Sander1","henk");
	}

	@Test
	public void test() {
		Controller controller = new Controller();
		assertEquals(controller.authenticate("Bouwe2014", "henk"), true);
		assertEquals(controller.canPlaceOrder(mechanic), false);
		assertEquals(controller.canAdvanceAssemblyLine(mechanic), false);
		assertEquals(controller.canPerformAssemblyTask(mechanic), true);
	}

}
