package businessmodel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;
import businessmodel.category.ModelAFactory;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.order.Order;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.statistics.OrderStatistics;
import businessmodel.statistics.StatisticsManager;
import businessmodel.user.GarageHolder;
import businessmodel.util.IteratorConverter;
import businessmodel.util.Tuple;

public class OrderStatisticsTest {
	
	private OrderStatistics statistics;

	@Before
	public void setUp() throws Exception {
		OrderManager om = new OrderManager();
		StatisticsManager statisticsManager = new StatisticsManager(om);
		this.statistics = statisticsManager.getOrderStatistics();
		
		ArrayList<StandardVehicleOrder> orders = new ArrayList<StandardVehicleOrder>();
        ArrayList<VehicleOptionCategory> categories = new Catalog().getAllCategories();
        GarageHolder gh = new GarageHolder("Michiel", "Vandendriessche", "MichielVDD");

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
        
        
        
        for (Order order: orders) {
        	om.placeOrder(order);
        }
        
        
        
        for (int i = 1; i <= 4; i++) {
	        for (AssemblyLine assemblyLine: om.getMainScheduler().getAssemblyLines()) {
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
	public void testLast() {
		ArrayList<Tuple<Order, Integer>> lastTwo = statistics.getLast(2);
		assertEquals(2, lastTwo.size());
	}
	
	@Test
	public void testMedian() {
		assertEquals(250, this.statistics.getMedian());
	}
	
	@Test
	public void testAverage() {
		assertEquals(240, this.statistics.getAverage());
	}

}
