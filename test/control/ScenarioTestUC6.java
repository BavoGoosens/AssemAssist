package control;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.util.IteratorConverter;

public class ScenarioTestUC6 {
	
	private VehicleManufacturingCompany vmc;
	private SchedulingController sc;
	private Manager manager;

	@Before
	public void setUp() throws Exception {
		this.vmc = new VehicleManufacturingCompany();
		this.sc = new SchedulingHandler(this.vmc);
		this.manager = new Manager("Michiel", "Vandendriessche", "MichielVDD");
		this.placeOrders();
	}

	@Test
	public void testAvailableAlgorithms() throws NoClearanceException {
		ArrayList<String> algos = (ArrayList<String>) new IteratorConverter<String>().
				convert(this.vmc.getSchedulingAlgorithms(this.manager));
		String currentAlgo = this.vmc.getCurrentSystemWideAlgorithm(this.manager);
		assertEquals(2, algos.size());
		assertEquals("FIFO", algos.get(0));
		assertEquals("SpecificationBatch", algos.get(1));
		assertEquals("FIFO", currentAlgo);
	} 
	
	@Test
	public void testChooseAlgorithm() throws NoClearanceException {
		ArrayList<ArrayList<VehicleOption>> optionLists = (ArrayList<ArrayList<VehicleOption>>)
				new IteratorConverter<ArrayList<VehicleOption>>().
				convert(this.vmc.getUnscheduledVehicleOptions(this.manager));
		assertEquals(1, optionLists.size());
		assertEquals(8, optionLists.get(0).size());
		ArrayList<VehicleOption> options = optionLists.get(0);
		assertEquals("BODY: sedan", options.get(0).toString());
		assertEquals("COLOR: red", options.get(1).toString());
		assertEquals("ENGINE: standard 2l v4", options.get(2).toString());
		assertEquals("GEARBOX: 6 speed manual", options.get(3).toString());
		assertEquals("SEATS: leather white", options.get(4).toString());
		assertEquals("AIRCO: manual", options.get(5).toString());
		assertEquals("WHEELS: winter", options.get(6).toString());
		assertEquals("SPOILER: low", options.get(7).toString());
		
		this.sc.selectAlgorithm(this.manager, "SpecificationBatch", options);
		String currentAlgo = this.vmc.getCurrentSystemWideAlgorithm(this.manager);
		assertEquals("SpecificationBatch", currentAlgo);
		
		this.sc.selectAlgorithm(this.manager, "FIFO", null);
		currentAlgo = this.vmc.getCurrentSystemWideAlgorithm(this.manager);
		assertEquals("FIFO", currentAlgo);
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
		this.vmc.placeOrder(order);
		this.vmc.placeOrder(order);
		this.vmc.placeOrder(order);
	}

}
