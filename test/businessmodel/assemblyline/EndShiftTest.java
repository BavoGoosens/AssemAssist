package businessmodel.assemblyline;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EndShiftTest {
	
	private EndShift shift;

	@Before
	public void setUp() throws Exception {
		this.shift = new EndShift(3, 3);
		this.shift.addTimeSlot();
	}

	@Test
	public void test() {
		assertEquals(3, this.shift.getNumberOfWorkPosts());
	}

}
