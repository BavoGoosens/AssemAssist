package control;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.Order;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.util.IteratorConverter;

public class ScenarioTestUC5 {
	
	private VehicleManufacturingCompany vmc;
	private Manager manager;
	private boolean looping;
	private GarageHolder garageHolder;

	@Before
	public void setUp() throws Exception {
		this.vmc = new VehicleManufacturingCompany();
		this.manager = new Manager("Michiel", "Vandendriessche", "MichielVDD");
		this.garageHolder = new GarageHolder("Bouwe", "Ceunen", "BouweC");
		this.placeOrders();
		for (int i = 0; i <= 2; i++) {
			this.processOrders();
		}
	}

	@Test
	public void testOrderStatsAverage() throws NoClearanceException {
		int average = this.vmc.getOrderStatistics(this.manager).getAverage();
		assertEquals(-4, average);
	}
	
	@Test
	public void testOrderStatsMedian() throws NoClearanceException {
		int median = this.vmc.getOrderStatistics(this.manager).getMedian();
		assertEquals(30, median);
	}
	
	@Test
	public void testVehicleStatsAverage() throws NoClearanceException {
		int average = (int) this.vmc.getVehicleStatistics(this.manager).getAverage();
		assertEquals(40, average);
	}
	
	@Test
	public void testVehicleStatsMedian() throws NoClearanceException {
		int median = this.vmc.getVehicleStatistics(this.manager).getMedian();
		assertEquals(40, median);
	}
	
	private void processOrders() throws NoClearanceException {
		IteratorConverter<WorkPost> converter = new IteratorConverter<>();
		Iterator<AssemblyLine> iter1 = this.vmc.getAssemblyLines(this.manager);
		DateTime beginDateTime = this.vmc.getSystemTime();
		while(iter1.hasNext()){
			looping = true;
			AssemblyLine assem = iter1.next();
			DateTime assemblyLineDateTime = assem.getAssemblyLineScheduler().getCurrentTime();
			DateTime result = assemblyLineDateTime.minus(beginDateTime.getMillis());

			while (looping == true && result.getMillis() < 86400000){
				assemblyLineDateTime = assem.getAssemblyLineScheduler().getCurrentTime();
				result = assemblyLineDateTime.minus(beginDateTime.getMillis());
				CompleteWorkPost(assem, converter.convert(assem.getWorkPostsIterator()).size());
			}
		}
	}

	/**
	 * Complete the WorkPosts from the given AssemblyLine.
	 * @param assem
	 * @param i
	 * @throws NoClearanceException
	 */
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
	
	private void placeOrders() throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		Catalog catalog = new Catalog();
		ArrayList<VehicleOptionCategory> categories = catalog.getAllCategories();
		ArrayList<VehicleModel> models = catalog.getAvailaleModelsClone();
		
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOptionCategory category: categories) {
			if (models.get(0).getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
				chosen.add(models.get(0).getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
			}
		}

		for (int i = 0; i < 100; i++) {
			this.vmc.placeOrder(new StandardVehicleOrder(garageHolder, chosen, models.get(0)));
		}
		
	}

}
