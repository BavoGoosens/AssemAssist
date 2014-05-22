package businessmodel.category;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.exceptions.IllegalVehicleOptionCategoryException;

public class VehicleTest {
	
	private Vehicle vehicle;
	private ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();

	@Before
	public void setUp() throws Exception {
		Catalog catalog = new Catalog();
		VehicleModel model = catalog.getAvailaleModelsClone().get(4);
		ArrayList<VehicleOptionCategory> categories = catalog.getAllCategories();
		for (VehicleOptionCategory category: categories) {
			if (model.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				this.chosen.add(model.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}
		}
		this.vehicle = new Vehicle(this.chosen);
	}

	@Test
	public void test() {
		assertEquals(7, this.vehicle.getOptionsClone().size());
		assertArrayEquals(this.chosen.toArray(), this.vehicle.getOptionsClone().toArray());
	}
	
	@Test
	public void testAddTruckOptions() throws IllegalArgumentException, IllegalVehicleOptionCategoryException {
		this.vehicle.addTruckOptions();
		Protection protection = new Protection();
		Storage storage = new Storage();
		Certification certification = new Certification();
		VehicleOption protection1 = new VehicleOption("cargo protection", protection);
		VehicleOption storage1 = new VehicleOption("tool storage", storage);
		VehicleOption certification1 = new VehicleOption("maximum cargo load certification", certification);
		protection.addOption(protection1);
		storage.addOption(storage1);
		certification.addOption(certification1);
		this.chosen.add(protection1);
		this.chosen.add(storage1);
		this.chosen.add(certification1);
		assertEquals(10, this.vehicle.getOptionsClone().size());
		assertArrayEquals(this.chosen.toArray(), this.vehicle.getOptionsClone().toArray());
	}
	
	@Test
	public void testToString() {
		assertEquals("option = "+this.chosen.toString(), this.vehicle.toString());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWrongVehicle() {
		new Vehicle(null);
	}

}
