package businessmodel.scheduler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import businessmodel.VehicleManufacturingCompany;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.user.User;
import control.InitialData;

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
		new InitialData(nbOrders).initialize(vmc);
		User user = vmc.login("wow", "");
		int count = 0;
		try {
			while(vmc.getPendingOrders(user).hasNext())
				count++;
		} catch (IllegalArgumentException | NoClearanceException e) {
			e.printStackTrace();
		}
		assertEquals(nbOrders,count);
		
	}

}