package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import businessmodel.CarModel;
import businessmodel.OrderManager;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.Engine;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;

public class OrderTest {
	
	private GarageHolder garageholder;
	private DateTime date;
	private OrderManager orderManager ;
	@Before
	public void setUp() throws Exception {
		
		ArrayList<CarModel> carmodels = new ArrayList<CarModel>();
		orderManager = new OrderManager(carmodels);
		garageholder = new GarageHolder("bouwe", "ceunen", "bouwe");
		
		CarOption option = new CarOption("small engine", new Engine() );
		CarOption option2 = new CarOption("big body", new Body() );
		ArrayList<CarOption> options = new ArrayList<CarOption>();
		options.add(option);
		options.add(option2);
		orderManager.addOrder(new StandardCarOrder(garageholder, options));
		
		date = orderManager.getScheduler().getCurrentTime().plusHours(3);
	}

	@Test
	public void test() {
		
		assertEquals(orderManager.getPendingOrders().get(0).getUser(),this.garageholder);
		assertEquals(orderManager.getPendingOrders().get(0).getEstimateDate(),this.date);
		assertEquals(orderManager.getPendingOrders().get(0).isCompleted(),false);
		
	}

}
