package control;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import businessmodel.VehicleManufacturingCompany;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.user.User;

public class InitialDataTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		VehicleManufacturingCompany vmc = new VehicleManufacturingCompany();
		int nbOrders = 10;
		try {
			new InitialData().initialize(vmc);
		} catch (NoClearanceException e1) {
			e1.printStackTrace();
		}
		User user = vmc.login("wow", "");
		int count = 0;
		try {
			Iterator<Order> it = vmc.getPendingOrders(user);
			while(it.hasNext()){
				it.next();
				count++;
			}
		} catch (IllegalArgumentException | NoClearanceException e) {
			e.printStackTrace();
		}
		
		assertEquals(6,count);
		
//		try {
//			count = 0;
//			Iterator<Order> it = vmc.getPendingOrders(user);
//			while(it.hasNext()){
//				it.next();
//				count++;
//			}
//			assertEquals(9, count);
//		} catch (IllegalArgumentException | NoClearanceException e) {
//			e.printStackTrace();
//		}
		
		
		
	}

}
