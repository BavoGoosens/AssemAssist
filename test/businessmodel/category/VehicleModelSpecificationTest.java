package businessmodel.category;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

public class VehicleModelSpecificationTest {
	
	private VehicleModelSpecification modelSpec;

	@Before
	public void setUp() {
		ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
		options.add(new VehicleOption("sedan", new Body()));
		options.add(new VehicleOption("manual", new Airco()));
		options.add(new VehicleOption("winter", new Wheels()));
		options.add(new VehicleOption("6 speed manual", new Gearbox()));
		options.add(new VehicleOption("low", new Spoiler()));
		options.add(new VehicleOption("vinyl grey", new Seats()));
		options.add(new VehicleOption("black", new Color()));
		options.add(new VehicleOption("standard", new Engine()));
		modelSpec = new VehicleModelSpecification(options);
	}
	
	@Test
	public void testModel() {
		for (VehicleOptionCategory category: new Catalog().getAllCategories()) {
			assertEquals(modelSpec.getOptionsOfCategory(category).size(), 1);
		}
	}
	
	@Test(expected=UnsatisfiedRestrictionException.class)
	public void test2() throws UnsatisfiedRestrictionException {
		ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
		options.add(new VehicleOption("break", new Body()));
		Vehicle car = new Vehicle(options);
		modelSpec.checkRestrictions(car);
	}
}
