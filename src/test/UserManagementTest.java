package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.user.Mechanic;
import businessmodel.user.User;

public class UserManagementTest {
	
	private Mechanic mechanic;
	private GarageHolder garageholder;
	private Manager manager;
	
	
	@Before
	public void setUp() throws Exception {
		mechanic = new Mechanic("Sander","Geijsen","Sander1");
		garageholder = new GarageHolder("Sander","Geijsen","Sander2");
		manager = new Manager("Sander","Geijsen","Sander3");
		
	}

	@Test
	public void test() {
		assertFalse(mechanic.canPlaceOrder());
		assertFalse(manager.canPlaceOrder());
		assertTrue(garageholder.canPlaceOrder());
		assertFalse(mechanic.canAdvanceAssemblyLine());
		assertFalse(garageholder.canAdvanceAssemblyLine());
		assertTrue(manager.canAdvanceAssemblyLine());
	
	}

}
