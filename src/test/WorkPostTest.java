package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;


import businessmodel.AssemblyTask;
import businessmodel.CarModel;
import businessmodel.OrderManager;
import businessmodel.WorkPost;
import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.Gearbox;
import businessmodel.category.Seats;
import businessmodel.category.Spoiler;
import businessmodel.category.Wheels;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;

public class WorkPostTest {
	
	private GarageHolder garageholder;
	private ArrayList<AssemblyTask> tasks;
	private ArrayList<Order> orders;
	private Date date;
	private Order order;
	private OrderManager om;
	
	@Before
	public void setUp() throws Exception {
		ArrayList<CarModel> carmodels = new ArrayList<CarModel>();
		om = new OrderManager(carmodels);
		garageholder = new GarageHolder("bouwe", "ceunen", "bouwe");

		CarOption option = new CarOption("small engine", new Engine() );
		CarOption option2 = new CarOption("big body", new Body() );
		ArrayList<CarOption> options = new ArrayList<CarOption>();
		options.add(option);
		options.add(option2);
		order = new StandardCarOrder(garageholder, options);
		om.addOrder(order);
		
	}

	@Test
	public void test() {
		
		ArrayList<AssemblyTask> tasksWorkPost1 = new ArrayList<AssemblyTask>();
		
		WorkPost post1 = new WorkPost("Test", tasksWorkPost1);		

		tasksWorkPost1.add(new AssemblyTask("Assembly Car Body", new Body(),post1));
		tasksWorkPost1.add(new AssemblyTask("Paint Car", new Color(),post1));

		post1.setNewOrder(om.getPendingOrders().get(0));
		assertEquals(post1.getOrder(),om.getPendingOrders().get(0));
	}

}
