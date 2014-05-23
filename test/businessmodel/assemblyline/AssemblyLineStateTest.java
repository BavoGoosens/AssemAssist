package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.category.Body;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.util.IteratorConverter;

public class AssemblyLineStateTest {
	
	private AssemblyLine assemblyLine;
	private VehicleManufacturingCompany vmc = new VehicleManufacturingCompany();
	
	@Before
	public void setUp() throws Exception {
		this.assemblyLine = 
				((ArrayList<AssemblyLine>) new IteratorConverter<AssemblyLine>().
						convert(this.vmc.getAssemblyLines(new Manager("Michiel", "Vandendriessche", "MichielVDD")))).get(0);
		placeOrders();
	}
	
	@Test
	public void availableStatesTest() {
		ArrayList<String> states = (ArrayList<String>) new IteratorConverter<String>().convert(this.assemblyLine.getAllPossibleStates());
		assertEquals("Broken", states.get(0));
		assertEquals("Maintenance", states.get(1));
		assertEquals("Operational", states.get(2));
	}

	@Test
	public void Transistiontest() {
		assertTrue(assemblyLine.getOperationalState() instanceof OperationalState);
		assertTrue(assemblyLine.getMaintenanceState() instanceof MaintenanceState);
		assertTrue(assemblyLine.getBrokenState() instanceof BrokenState);
		assertEquals(assemblyLine.currentState(), "Operational");
		assemblyLine.transitionToBroken();
		assertEquals(assemblyLine.currentState(), "Broken");
		assemblyLine.transitionToMaintenance();
		assertEquals(assemblyLine.currentState(), "Maintenance");
	}
	
	private void placeOrders() throws NoClearanceException, UnsatisfiedRestrictionException {
		Catalog catalog = new Catalog();
		ArrayList<VehicleOptionCategory> categories = catalog.getAllCategories();
		GarageHolder garageHolder = new GarageHolder("Bouwe", "Ceunen", "BouweC");
		ArrayList<VehicleModel> models = catalog.getAvailaleModelsClone();
		
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOptionCategory category: categories) {
			if (models.get(1).getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				chosen.add(models.get(1).getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}
		}
		
		StandardVehicleOrder order = new StandardVehicleOrder(garageHolder, chosen, models.get(1));
		StandardVehicleOrder order2 = new StandardVehicleOrder(garageHolder, chosen, models.get(1));
		this.vmc.placeOrder(order);
		this.vmc.placeOrder(order2);
		chosen.clear();
		
		for (VehicleOptionCategory category: categories) {
			if (models.get(3).getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				if (category.equals(new Body())) {
					chosen.add(models.get(3).getVehicleModelSpecification().getOptionsOfCategory(category).get(1));
				} else {
					chosen.add(models.get(3).getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
				}
			}
		}
		
		order = new StandardVehicleOrder(garageHolder, chosen, models.get(3));
		this.vmc.placeOrder(order);
	}
	
	

}
