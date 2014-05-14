package businessmodel.user;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private Mechanic mechanic;

	@Before
	public void setUp() throws Exception {
		mechanic = new Mechanic("Sander","Geijsen","Sander1");
	}

	@Test
	public void test() {
		assertEquals(mechanic.getFirstname(),"Sander");
		assertEquals(mechanic.getLastname(),"Geijsen");
		
	}

}
