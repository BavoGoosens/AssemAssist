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
	private boolean looping;

	@Before
	public void setUp() throws Exception {
		this.vmc = new VehicleManufacturingCompany();
		this.manager = new Manager("Michiel", "Vandendriessche", "MichielVDD");
		this.placeOrders();
		this.processOrders();
	}

	@Test
	public void testOrderStatsAverage() throws NoClearanceException {
		int average = this.vmc.getOrderStatistics(this.manager).getAverage();
		assertEquals(19, average);
	}
	
	@Test
	public void testOrderStatsMedian() throws NoClearanceException {
		int median = this.vmc.getOrderStatistics(this.manager).getMedian();
		assertEquals(30, median);
	}
	
	@Test
	public void testVehicleStatsAverage() throws NoClearanceException {
		int average = this.vmc.getVehicleStatistics(this.manager).getAverage();
		assertEquals(11, average);
	}
	
	@Test
	public void testVehicleStatsMedian() throws NoClearanceException {
		int median = this.vmc.getVehicleStatistics(this.manager).getMedian();
		assertEquals(11, median);
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
	
	private void processOrders() throws NoClearanceException {
		IteratorConverter<WorkPost> converter = new IteratorConverter<>();
		for(AssemblyLine iter1: (ArrayList<AssemblyLine>) new IteratorConverter<AssemblyLine>().convert(this.vmc.getAssemblyLines(this.manager))){
			looping = true;
			while (looping == true ){
				CompleteWorkPost(iter1, converter.convert(iter1.getWorkPostsIterator()).size());
			}
		}

	}

	private void CompleteWorkPost(AssemblyLine assem, int i) throws NoClearanceException{
		looping = false;
		for(int j = 0 ; j < i ; j++){
			IteratorConverter<WorkPost> converter = new IteratorConverter<>();
			WorkPost wp1 = converter.convert(assem.getWorkPostsIterator()).get(j);
			Iterator<AssemblyTask> iter2 = wp1.getPendingTasks();
			while (iter2.hasNext()){
				AssemblyTask task = iter2.next();
				task.completeAssemblytask(20);
				looping = true;
			}
		}
	}

}
