package control;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;
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

public class ScenarioTestUC5 {
	
	private VehicleManufacturingCompany vmc;
	private Manager manager;

	@Before
	public void setUp() throws Exception {
		this.vmc = new VehicleManufacturingCompany();
		this.manager = new Manager("Michiel", "Vandendriessche", "MichielVDD");
		this.placeOrders();
	}

	@Test
	public void testOrderStatsAverage() throws NoClearanceException {
		int average = this.vmc.getOrderStatistics(this.manager).getAverage();
		System.out.println(average);
	}
	
	@Test
	public void testOrderStatsMedian() throws NoClearanceException {
		int median = this.vmc.getOrderStatistics(this.manager).getMedian();
		System.out.println(median);
	}
	
	@Test
	public void testVehicleStatsAverage() throws NoClearanceException {
		int average = this.vmc.getOrderStatistics(this.manager).getAverage();
		System.out.println(average);
	}
	
	@Test
	public void testVehicleStatsMedian() throws NoClearanceException {
		int median = this.vmc.getOrderStatistics(this.manager).getMedian();
		System.out.println(median);
	}
	
	private void placeOrders() throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
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
		for (int i = 0; i <= 30; i++) {
			this.vmc.placeOrder(new StandardVehicleOrder(garageHolder, chosen, models.get(1)));
		}
	}

}
