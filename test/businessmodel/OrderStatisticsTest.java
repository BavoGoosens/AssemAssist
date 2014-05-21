package businessmodel;

import java.util.ArrayList;
import java.util.Iterator;

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
	
	/**
	 * MEDIAAN EN GEMIDDELDE WORDEN BIJ JUISTE DELAY JUIST BEREKEND!
	 * ENKEL CHECKEN OF DELAY GOED BEREKEND WORDT!
	 * DAARNA ASSERTIONTESTS IMPLEMENTEREN
	 */
	
	@Test
	public void test() {
		ArrayList<Tuple<Order, Integer>> orders = statistics.getFinishedOrders();
		for (Tuple<Order, Integer> tuple: orders) {
			System.out.println("** Order **");
			System.out.println("Order: "+tuple.getX());
			System.out.println("Delay: "+tuple.getY()+ " minutes");
			System.out.println("** End Order **\n");
		}
	}

	@Test
	public void testLast() {
		ArrayList<Tuple<Order, Integer>> lastTwo = statistics.getLast(2);
		// TODO: assertionTest implementeren
		System.out.println("Last two orders");
		for (Tuple<Order, Integer> tuple: lastTwo) {
			System.out.println(tuple.getX());
			System.out.println(tuple.getY());
			System.out.println("");
		}
		System.out.println("");
	}
	
	@Test
	public void testMedian() {
		// TODO: assertionTest implementeren
		int median = statistics.getMedian();
		System.out.println("Median:");
		System.out.println(median);
		System.out.println("");
	}
	
	@Test
	public void testAverage() {
		// TODO: assertionTest implementeren
		int average = statistics.getAverage();
		System.out.println("Average:");
		System.out.println(average);
		System.out.println("");
	}

}
