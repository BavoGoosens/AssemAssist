package businessmodel;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.util.TestStandardVehicleOrder;

public class OrderManagerTest {

	private OrderManager ordermanager;
	private ArrayList<String> names;
	private GarageHolder holder;

	@Before
	public void setUp() throws Exception {
		this.ordermanager = new OrderManager();
		generateVehicleModels();
		addOrders();
	}

	@Test
	public void test() throws IllegalArgumentException, NoClearanceException {
		TestStandardVehicleOrder order = new TestStandardVehicleOrder(holder,"Car Model A");
		ordermanager.placeOrderInFront(order.getOrder());
		ordermanager.getPendingOrders().add(order.getOrder());
		ordermanager.placeOrderInFront(order.getOrder());
		assertEquals(order.getOrder(),ordermanager.getPendingOrders().getFirst());
		
		ordermanager.finishedOrder(order.getOrder());
		assertEquals(order.getOrder(),ordermanager.getCompletedOrders().getFirst());
		
		ArrayList<Order> orders = ordermanager.getPendingOrders(holder);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPlaceOrder(){
		ordermanager.placeOrder(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFinischedOrder(){
		ordermanager.finishedOrder(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetPendingOrdersUser1() throws IllegalArgumentException, NoClearanceException{
		ordermanager.getPendingOrders(null);
	}
	
	@Test(expected = NoClearanceException.class)
	public void testGetPendingOrdersUser2() throws IllegalArgumentException, NoClearanceException{
		ordermanager.getPendingOrders(new Manager("","",""));
	}

	private void generateVehicleModels() {
		holder = new GarageHolder("Sander","","");
		String name1 = "Car Model A";
		String name2 = "Car Model B";
		String name3 = "Car Model C";
		String name4 = "Truck Model X";
		String name5 = "Truck Model Y";
		names = new ArrayList<String>();
		names.add(name1);
		names.add(name2);
		names.add(name3);
		names.add(name4);
		names.add(name5);		
	}

	private void addOrders(){
		ArrayList<Order> orders = new ArrayList<Order>();
		for(int i =0 ;i < 20 ; i++){
			TestStandardVehicleOrder ord = new TestStandardVehicleOrder(holder, names.get(i%4));
			orders.add(ord.getOrder());
			ordermanager.placeOrder(ord.getOrder());
		}
	}
}
