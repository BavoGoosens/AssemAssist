package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Mechanic;

public class UserTest {
	
	private Mechanic mechanic;

	@Before
	public void setUp() throws Exception {
		mechanic = new Mechanic("Sander","Geijsen","Sander1","henk");
	}

	@Test
	public void test() {
		assertEquals(mechanic.getFirstname(),"Sander");
		assertEquals(mechanic.getLastname(),"Geijsen");
		mechanic.authenticate("Sander1","henk");
		mechanic.authenticate("Sander7","henk");
		mechanic.updateUser("Sander1", "henk", "Piet", "jan");
	}

}
