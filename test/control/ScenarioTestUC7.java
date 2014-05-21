package control;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.assemblyline.AssemblyLine;
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

public class ScenarioTestUC7 {
	
	private Manager manager;
	private VehicleManufacturingCompany vmc;
	private AssemblyLineController ac;
	private ArrayList<AssemblyLine> lines;
	

	@Before
	public void setUp() throws Exception {
		this.vmc = new VehicleManufacturingCompany();
		this.ac = new AssemblyLineHandler(this.vmc);
		this.manager = new Manager("Michiel", "Vandendriessche", "MichielVDD");
		this.lines = (ArrayList<AssemblyLine>) new IteratorConverter<AssemblyLine>().convert(this.vmc.getAssemblyLines(manager));
		this.placeOrders();
	}

	@Test
	public void testStatusLineA() throws NoClearanceException {
		String currentState = this.vmc.getCurrentAssemblyLineStatus(this.manager, this.lines.get(0));
		ArrayList<String> availableStates = 
				(ArrayList<String>) new IteratorConverter<String>().
				convert(this.vmc.getAvailableAssemblyLineStatus(this.manager, this.lines.get(0)));
		assertEquals("Operational", currentState);
		assertEquals("Broken", availableStates.get(0));
		assertEquals("Maintenance", availableStates.get(1));
		assertEquals("Operational", availableStates.get(2));
		this.ac.changeOperationalStatus(this.manager, this.lines.get(0), "Broken");
		currentState = this.vmc.getCurrentAssemblyLineStatus(this.manager, this.lines.get(0));
		assertEquals("Broken", currentState);
		this.ac.changeOperationalStatus(this.manager, this.lines.get(0), "Maintenance");
		currentState = this.vmc.getCurrentAssemblyLineStatus(this.manager, this.lines.get(0));
		assertEquals("Maintenance", currentState);
	}
	
	@Test
	public void testStatusLineB() throws NoClearanceException {
		String currentState = this.vmc.getCurrentAssemblyLineStatus(this.manager, this.lines.get(1));
		ArrayList<String> availableStates = 
				(ArrayList<String>) new IteratorConverter<String>().
				convert(this.vmc.getAvailableAssemblyLineStatus(this.manager, this.lines.get(1)));
		assertEquals("Operational", currentState);
		assertEquals("Broken", availableStates.get(0));
		assertEquals("Maintenance", availableStates.get(1));
		assertEquals("Operational", availableStates.get(2));
		this.ac.changeOperationalStatus(this.manager, this.lines.get(1), "Broken");
		currentState = this.vmc.getCurrentAssemblyLineStatus(this.manager, this.lines.get(1));
		assertEquals("Broken", currentState);
		this.ac.changeOperationalStatus(this.manager, this.lines.get(1), "Maintenance");
		currentState = this.vmc.getCurrentAssemblyLineStatus(this.manager, this.lines.get(1));
		assertEquals("Maintenance", currentState);
	}
	
	@Test
	public void testStatusLineC() throws NoClearanceException {
		String currentState = this.vmc.getCurrentAssemblyLineStatus(this.manager, this.lines.get(2));
		ArrayList<String> availableStates = 
				(ArrayList<String>) new IteratorConverter<String>().
				convert(this.vmc.getAvailableAssemblyLineStatus(this.manager, this.lines.get(2)));
		assertEquals("Operational", currentState);
		assertEquals("Broken", availableStates.get(0));
		assertEquals("Maintenance", availableStates.get(1));
		assertEquals("Operational", availableStates.get(2));
		this.ac.changeOperationalStatus(this.manager, this.lines.get(2), "Broken");
		currentState = this.vmc.getCurrentAssemblyLineStatus(this.manager, this.lines.get(2));
		assertEquals("Broken", currentState);
		this.ac.changeOperationalStatus(this.manager, this.lines.get(2), "Maintenance");
		currentState = this.vmc.getCurrentAssemblyLineStatus(this.manager, this.lines.get(2));
		assertEquals("Maintenance", currentState);
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
