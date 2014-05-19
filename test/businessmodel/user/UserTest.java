package businessmodel.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private Mechanic mechanic;
	private GarageHolder garageHolder;
	private Manager manager;
	private CustomShopManager csm;

	@Before
	public void setUp() throws Exception {
		mechanic = new Mechanic("Sander","Geijsen","SanderG");
		manager = new Manager("Michiel", "Vandendriessche", "MichielVDD");
		garageHolder = new GarageHolder("Bouwe", "Ceunen", "BouweC");
		csm = new CustomShopManager("Bavo", "Goosens", "BavoG");
	}

	@Test
	public void mechanicTest() {
		assertEquals(mechanic.getFirstname(), "Sander");
		assertEquals(mechanic.getLastname(), "Geijsen");
		assertEquals(mechanic.getUsername(), "SanderG");
		assertEquals("Sander Geijsen (SanderG)", mechanic.toString());
		assertFalse(mechanic.canPlaceOrder());
		assertTrue(mechanic.canPerfomAssemblyTask());
		assertFalse(mechanic.canOrderSingleTask());
		assertFalse(mechanic.canViewStatistics());
		assertTrue(mechanic.canViewAssemblyLines());
		assertFalse(mechanic.canChangeAlgorithm());
		assertFalse(mechanic.canChangeOperationalStatus());
	}
	
	@Test
	public void managerTest() {
		assertEquals(manager.getFirstname(), "Michiel");
		assertEquals(manager.getLastname(), "Vandendriessche");
		assertEquals(manager.getUsername(), "MichielVDD");
		assertEquals("Michiel Vandendriessche (MichielVDD)", manager.toString());
		assertFalse(manager.canPlaceOrder());
		assertFalse(manager.canPerfomAssemblyTask());
		assertFalse(manager.canOrderSingleTask());
		assertTrue(manager.canViewStatistics());
		assertTrue(manager.canViewAssemblyLines());
		assertTrue(manager.canChangeAlgorithm());
		assertTrue(manager.canChangeOperationalStatus());
	}
	
	@Test
	public void garageHolderTest() {
		assertEquals(garageHolder.getFirstname(), "Bouwe");
		assertEquals(garageHolder.getLastname(), "Ceunen");
		assertEquals(garageHolder.getUsername(), "BouweC");
		assertEquals("Bouwe Ceunen (BouweC)", garageHolder.toString());
		assertTrue(garageHolder.canPlaceOrder());
		assertFalse(garageHolder.canPerfomAssemblyTask());
		assertFalse(garageHolder.canOrderSingleTask());
		assertFalse(garageHolder.canViewStatistics());
		assertFalse(garageHolder.canViewAssemblyLines());
		assertFalse(garageHolder.canChangeAlgorithm());
		assertFalse(garageHolder.canChangeOperationalStatus());
	}
	
	@Test
	public void customShopManagerTest() {
		assertEquals(csm.getFirstname(), "Bavo");
		assertEquals(csm.getLastname(), "Goosens");
		assertEquals(csm.getUsername(), "BavoG");
		assertEquals("Bavo Goosens (BavoG)", csm.toString());
		assertTrue(csm.canPlaceOrder());
		assertFalse(csm.canPerfomAssemblyTask());
		assertTrue(csm.canOrderSingleTask());
		assertFalse(csm.canViewStatistics());
		assertFalse(csm.canViewAssemblyLines());
		assertFalse(csm.canChangeAlgorithm());
		assertFalse(csm.canChangeOperationalStatus());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalUserTest() {
		new GarageHolder(null, "lastname", "username");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalUserTest2() {
		new GarageHolder("firstname", null, "username");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalUserTest3() {
		new GarageHolder("firstname", "lastname", null);
	}

}
