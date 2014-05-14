package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.category.ModelAFactory;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.order.Order;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;

public class AssemblyLineTest {

	private ArrayList<Order> orders;
	private StandardVehicleOrder order1;
	private StandardVehicleOrder order2;
	private StandardVehicleOrder order3;

	private OrderManager om;


	private Catalog catalog;


	private ArrayList<VehicleOptionCategory> categories;


	private VehicleManufacturingCompany cmc = new VehicleManufacturingCompany();

	@Before
	public void setUp() throws Exception {
		VehicleManufacturingCompany cmc = new VehicleManufacturingCompany();
		om = cmc.getOrderManager();
		orders = new ArrayList<Order>();

		this.catalog = new Catalog();
		this.categories = this.catalog.getAllCategories();

		VehicleModel modelA = new ModelAFactory().createModel();
		ArrayList<VehicleOption> chosen = new ArrayList<VehicleOption>();
		for (VehicleOptionCategory category: this.categories) {
			ArrayList<VehicleOption> options = modelA.getVehicleModelSpecification().getOptionsOfCategory(category);
			if (options.size() > 0) {
				chosen.add(options.get(0));
			}

		}

		order1 = new StandardVehicleOrder(new GarageHolder("1", "", ""), chosen, modelA);
		order2 = new StandardVehicleOrder(new GarageHolder("2", "", ""), chosen, modelA);
		order3 = new StandardVehicleOrder(new GarageHolder("3", "", ""), chosen, modelA);


		orders.add(order1);
		orders.add(order2);
		orders.add(order3);


		for(Order order: orders)
			cmc.placeOrder(order);

	}

	@Test
	public void test() {
		AssemblyLine testassembly = om.getScheduler().getAssemblyline();

		assertEquals(testassembly.getWorkPosts().get(0).getResponsibleTasksClone().get(0).toString(),"Assembly Vehicle Body");

		for(WorkPost wp1 : om.getScheduler().getAssemblyline().getWorkPosts()){
			if(wp1.getOrder()!= null);
//				System.out.println(wp1.getOrder().getUser());
			else;
//				System.out.println("0");
		} 

		assertEquals(testassembly.getWorkPosts().get(0).getOrder(),orders.get(0));
		assertEquals(testassembly.getWorkPosts().get(1).getOrder(),null);
		assertEquals(testassembly.getWorkPosts().get(2).getOrder(),null);

//		System.out.println("===========");
		WorkPost wp = testassembly.getWorkPosts().get(0);

		Iterator<AssemblyTask> iter = cmc.getPendingTasks(wp);
		List<AssemblyTask> copy = new ArrayList<AssemblyTask>();
		while (iter.hasNext())
			copy.add(iter.next());
		for(AssemblyTask assem : copy)
			cmc.finishTask(assem, 20);


		assertEquals(testassembly.getWorkPosts().get(0).getOrder(),orders.get(1));
		assertEquals(testassembly.getWorkPosts().get(1).getOrder(),orders.get(0));
		assertEquals(testassembly.getWorkPosts().get(2).getOrder(),null);

		for(WorkPost wp1 : om.getScheduler().getAssemblyline().getWorkPosts()){
			if(wp1.getOrder()!= null);
//				System.out.println(wp1.getOrder().getUser());
			else;
//				System.out.println("0");
		}

		wp = testassembly.getWorkPosts().get(0);

		Iterator<AssemblyTask> iter1 = cmc.getPendingTasks(wp);
		List<AssemblyTask> copy1 = new ArrayList<AssemblyTask>();
		while (iter1.hasNext())
			copy1.add(iter1.next());
		for(AssemblyTask assem : copy1)
			cmc.finishTask(assem, 20);



		wp = testassembly.getWorkPosts().get(1);

		Iterator<AssemblyTask> iter11 = cmc.getPendingTasks(wp);
		List<AssemblyTask> copy11 = new ArrayList<AssemblyTask>();
		while (iter11.hasNext())
			copy11.add(iter11.next());
		for(AssemblyTask assem : copy11)
			cmc.finishTask(assem, 20);

		assertEquals(testassembly.getWorkPosts().get(0).getOrder(),orders.get(2));
		assertEquals(testassembly.getWorkPosts().get(1).getOrder(),orders.get(1));
		assertEquals(testassembly.getWorkPosts().get(2).getOrder(),orders.get(0));

		for(int i=0; i < 3; i++){
			for(WorkPost wp1 : om.getScheduler().getAssemblyline().getWorkPosts()){
				if(wp1.getOrder()!= null);
//					System.out.println(wp1.getOrder().getUser());
				else;
//					System.out.println("0");
			}
//			System.out.println("=====");

			for(WorkPost wp1: testassembly.getWorkPosts()){

				Iterator<AssemblyTask> iter111 = cmc.getPendingTasks(wp1);
				List<AssemblyTask> copy111 = new ArrayList<AssemblyTask>();
				while (iter111.hasNext())
					copy111.add(iter111.next());
				for(AssemblyTask assem : copy111)
					cmc.finishTask(assem, 20);
			}
		}

		assertEquals(testassembly.getWorkPosts().get(0).getOrder(),null);
		assertEquals(testassembly.getWorkPosts().get(1).getOrder(),null);
		assertEquals(testassembly.getWorkPosts().get(2).getOrder(),null);

	}

}
