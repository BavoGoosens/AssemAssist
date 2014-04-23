package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;


import org.junit.Before;
import org.junit.Test;

import businessmodel.CarModel;
import businessmodel.OrderManager;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.Engine;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;



public class ExceptionTest {
	private OrderManager orderManager;
	private Manager manager;

	@Before
	public void setUp() throws Exception {
		ArrayList<CarModel> carmodels = new ArrayList<CarModel>();
		orderManager = new OrderManager(carmodels);
		manager = new Manager("bouwe", "ceunen", "bouwe");
		
		CarOption option = new CarOption("small engine", new Engine() );
		CarOption option2 = new CarOption("big body", new Body() );
		ArrayList<CarOption> options = new ArrayList<CarOption>();
		options.add(option);
		options.add(option2);
		
		try{
		orderManager.addOrder(new StandardCarOrder(manager, options));
		
		}catch (NoClearanceException ex){
			assertEquals(ex.getUser(), manager);
		}
		
	}

	@Test
	public void test() {
		
	}
}
