package businessmodel.assemblyline;


import org.junit.Test;

public class SchedulingAlgorithmTest {

	private SchedulingAlgorithm scheduler;
	
	@Test(expected = IllegalArgumentException.class)
	public void test() {
		scheduler = new FIFO(null);
		scheduler.getClass();
	}
}
