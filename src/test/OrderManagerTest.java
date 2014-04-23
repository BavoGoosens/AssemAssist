package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.CarModel;
import businessmodel.OrderManager;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.Engine;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;

public class OrderManagerTest {

	private OrderManager om;
	private GarageHolder garageholder;
	private Order order;
	CarOption option;
	CarOption option2;

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
		try {
			
			assertTrue(om.getPendingOrders().contains(order));
			assertTrue(om.getPendingOrders(garageholder).contains(order));
			om.finishedOrder(order);
			assertTrue(om.getCompletedOrders().contains(order));
			assertTrue(om.getCompletedOrders(garageholder).contains(order));
			

		} catch (IllegalArgumentException | NoClearanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
