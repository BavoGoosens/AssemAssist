package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.AssemblyLine;
import businessmodel.AssemblyTask;
import businessmodel.CarManufacturingCompany;
import businessmodel.CarModel;
import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.WorkPost;
import businessmodel.category.*;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;

public class AssemblyLineTest {


	private GarageHolder garageholder;


	private ArrayList<Order> orders;
	private StandardCarOrder order1;
	private StandardCarOrder order2;
	private StandardCarOrder order3;
	private StandardCarOrder order4;
	private OrderManager om;


	private Catalog catalog;


	private ArrayList<CarOptionCategory> categories;


	private CarManufacturingCompany cmc = new CarManufacturingCompany();

	@Before
	public void setUp() throws Exception {

		ArrayList<CarModel> carmodels = new ArrayList<CarModel>();
		om = new OrderManager(carmodels);
		garageholder = new GarageHolder("bouwe", "ceunen", "bouwe");
		orders = new ArrayList<Order>();

		this.catalog = new Catalog();
		this.categories = this.catalog.getAllCategories();

		CarModel modelA = new ModelAFactory().createModel();
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		for (CarOptionCategory category: this.categories) {
			ArrayList<CarOption> options = modelA.getCarModelSpecification().getOptionsOfCategory(category);
			if (options.size() > 0) {
				chosen.add(options.get(0));
			}

		}

		order1 = new StandardCarOrder(garageholder, chosen);
		order2 = new StandardCarOrder(garageholder, chosen);
		order3 = new StandardCarOrder(garageholder, chosen);
		order4 = new StandardCarOrder(garageholder, chosen);

		orders.add(order1);
		orders.add(order2);
		orders.add(order3);
		orders.add(order4);

		for(Order order: orders)
			om.addOrder(order);

	}

	@Test
	public void test() {
		AssemblyLine testassembly = om.getScheduler().getAssemblyline();
		assertEquals(testassembly.getWorkPosts().get(0).getResponsibleTasksClone().get(0).toString(),"Assembly Car Body");

		testassembly.getWorkPosts().get(0).setNewOrder(orders.get(0));
		testassembly.getWorkPosts().get(1).setNewOrder(orders.get(1));
		testassembly.getWorkPosts().get(2).setNewOrder(orders.get(2));


		for(WorkPost wp: om.getScheduler().getAssemblyline().getWorkPosts()){
			if(wp.getOrder() != null){
				for(AssemblyTask assem : wp.getPendingTasks())
					cmc.completeAssemBlyTask(assem, 20);

			}
		}


		assertEquals(testassembly.getWorkPosts().get(0).getOrder(),null);
		assertEquals(testassembly.getWorkPosts().get(1).getOrder(),orders.get(0));
		assertEquals(testassembly.getWorkPosts().get(2).getOrder(),orders.get(1));

	}

}
