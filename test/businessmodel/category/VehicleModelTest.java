package businessmodel.category;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class VehicleModelTest {
	
	private VehicleModel model;
	private VehicleModelSpecification vms;
	private ArrayList<VehicleOption> options;

	@Before
	public void setUp() throws Exception {
		options = new ArrayList<VehicleOption>();
		options.add(new VehicleOption("sedan", new Body()));
		options.add(new VehicleOption("manual", new Airco()));
		options.add(new VehicleOption("winter", new Wheels()));
		options.add(new VehicleOption("6 speed manual", new Gearbox()));
		options.add(new VehicleOption("low", new Spoiler()));
		options.add(new VehicleOption("vinyl grey", new Seats()));
		options.add(new VehicleOption("black", new Color()));
		options.add(new VehicleOption("standard", new Engine()));
		vms = new VehicleModelSpecification(options);
		String name = "Test model";
		model = new VehicleModel(name, vms);
	}

	@Test
	public void test() {
		assertEquals("Test model", model.getName());
		assertEquals(vms, model.getVehicleModelSpecification());
		assertArrayEquals(options.toArray(), model.getPossibilities().toArray());
		assertEquals("Vehicle model: Test model", this.model.toString());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void wrongTest() {
		new VehicleModel(null, vms);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void wronTest2() {
		new VehicleModel("Test", null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void wrongTest3() {
		new VehicleModel("", vms);
	}
}
