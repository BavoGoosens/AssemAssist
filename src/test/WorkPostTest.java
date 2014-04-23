package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


import businessmodel.AssemblyTask;
import businessmodel.CarModel;
import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.WorkPost;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.ModelAFactory;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;

public class WorkPostTest {
	
	private GarageHolder garageholder;
	private Order order;
	private OrderManager om;
	private Catalog catalog;
	private ArrayList<Order> orders;
	private ArrayList<CarOptionCategory> categories;

	
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
		order = new StandardCarOrder(garageholder, chosen);
		om.addOrder(order);
		
	}

	@Test
	public void test() {
		
		ArrayList<AssemblyTask> tasksWorkPost1 = new ArrayList<AssemblyTask>();
		
		WorkPost post1 = new WorkPost("Test", tasksWorkPost1, om.getScheduler().getAssemblyline());		

		tasksWorkPost1.add(new AssemblyTask("Assembly Car Body","assemble blabla", new Body(),post1));
		tasksWorkPost1.add(new AssemblyTask("Paint Car", "assemble paint", new Color(),post1));

		post1.setNewOrder(om.getScheduler().getOrders().get(0));
		assertEquals(post1.getOrder(),om.getScheduler().getOrders().get(0));
	}

}
