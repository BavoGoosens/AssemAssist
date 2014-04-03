package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import businessmodel.UserManagement;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.user.Mechanic;

public class UserManagementTest {
	
	private Mechanic mechanic;
	private GarageHolder garageholder;
	private Manager manager;
	private UserManagement um;
	
	@Before
	public void setUp() throws Exception {
		mechanic = new Mechanic("Sander","Geijsen","Sander1","henk");
		garageholder = new GarageHolder("Sander","Geijsen","Sander2","henk");
		manager = new Manager("Sander","Geijsen","Sander3","henk");
		um = new UserManagement();
	}

	@Test
	public void test() {
		assertFalse(um.canPlaceOrder(mechanic));
		assertFalse(um.canPlaceOrder(manager));
		assertTrue(um.canPlaceOrder(garageholder));
		assertFalse(um.canControlAssemblyLine(mechanic));
		assertFalse(um.canControlAssemblyLine(garageholder));
		assertTrue(um.canControlAssemblyLine(manager));
		assertFalse(um.isMechanic(garageholder));
		assertFalse(um.isMechanic(manager));
		assertTrue(um.isMechanic(mechanic));
	}

}
