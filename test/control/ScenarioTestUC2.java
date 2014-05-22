package control;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;
import businessmodel.category.ModelAFactory;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.util.IteratorConverter;


public class ScenarioTestUC2 {
	
	private VehicleManufacturingCompany vmc;
	private Manager manager;
	private GarageHolder gh;

	@Before
	public void setUp() throws Exception {
		this.vmc = new VehicleManufacturingCompany();
		this.manager = new Manager("Michiel", "Vandendriessche", "MichielVDD");
		this.gh = new GarageHolder("Bouwe", "Ceunen", "BouweC");
		
		ArrayList<Order> orders = new ArrayList<Order>();
		ArrayList<VehicleOptionCategory> categories = new Catalog().getAllCategories();
		
		VehicleModel modelA = new ModelAFactory().createModel();
        ArrayList<VehicleOption> chosenA = new ArrayList<VehicleOption>();
        for (VehicleOptionCategory category: categories) {
        	if (modelA.getVehicleModelSpecification().getOptionsOfCategory(category).size() > 0) {
        		chosenA.add(modelA.getVehicleModelSpecification().getOptionsOfCategory(category).get(0));
        	}
        }
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        orders.add(new StandardVehicleOrder(gh, chosenA, modelA));
        
        for (Order order: orders) {
        	this.vmc.placeOrder(order);
        }
        
        for (int i = 1; i <= 3; i++) {
	        for (AssemblyLine assemblyLine: 
	        	(ArrayList<AssemblyLine>) new IteratorConverter<AssemblyLine>().
	        	convert(vmc.getAssemblyLines(this.manager))) {
	        	Iterator<WorkPost> workPosts = assemblyLine.getWorkPostsIterator();
	        	while (workPosts.hasNext()) {
	        		WorkPost workPost = workPosts.next();
	        		Iterator<AssemblyTask> tasks = workPost.getPendingTasks();
	        		while (tasks.hasNext()) {
	        			AssemblyTask task = tasks.next();
	        			task.completeAssemblytask(50);
	        		}
	        	}
	        }
		}
	}

	@Test
	public void testOrderFinishedCount() throws IllegalArgumentException, NoClearanceException {
		ArrayList<Order> orders = (ArrayList<Order>) new IteratorConverter<Order>().convert(vmc.getCompletedOrders(this.gh));
		assertEquals(4, orders.size());
	}
	
	@Test
	public void testOrderPendingCount() throws IllegalArgumentException, NoClearanceException {
		ArrayList<Order> orders = (ArrayList<Order>) new IteratorConverter<Order>().convert(vmc.getPendingOrders(this.gh));
		assertEquals(2, orders.size());
	}
	
	@Test
	public void testDetailsFinishedOrder() throws IllegalArgumentException, NoClearanceException {
		StandardVehicleOrder order = 
				(StandardVehicleOrder) ((ArrayList<Order>) new IteratorConverter<Order>().
						convert(vmc.getCompletedOrders(this.gh))).get(0);
		assertNotNull(order.getTimestamp());
		assertNotNull(order.getCompletionDate());
		// de eigenlijke waarden van deze timestamp en completion date worden getest in een meer gedetailleerde testklasse
	}
	
	@Test
	public void testDetailsPendingOrder() throws IllegalArgumentException, NoClearanceException {
		StandardVehicleOrder order = 
				(StandardVehicleOrder) ((ArrayList<Order>) new IteratorConverter<Order>().
						convert(vmc.getPendingOrders(this.gh))).get(0);
		assertNotNull(order.getTimestamp());
		// de eigenlijke waarden van deze timestamp en completion date worden getest in een meer gedetailleerde testklasse
	}

}
