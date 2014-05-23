package businessmodel;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.category.VehicleOption;
import businessmodel.category.Wheels;

public class MainSchedulerTest {
	
	private MainScheduler ms;
	private OrderManager om;

	@Before
	public void setUp() throws Exception {
		this.om = new OrderManager();
		this.ms = new MainScheduler(this.om);
	}

	@Test
	public void test() {
		assertEquals(3, ms.getAssemblyLines().size());
		assertEquals(3, ms.getAssemblyLineSchedulers().size());
		assertEquals(this.om, this.ms.getOrderManager());
		ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
		options.add(new VehicleOption("Test", new Wheels()));
		this.ms.changeSystemWideAlgorithm("SpecificationBatch", options);
		assertEquals("SpecificationBatch", this.ms.getAlgorithm());
	}

}
