package businessmodel.assemblyline;

import static org.junit.Assert.*;

import org.junit.Test;

public class SchedulingAlgorithmTest {

	private SchedulingAlgorithm scheduler;

	@Test(expected = IllegalArgumentException.class)
	public void test() {
		this.scheduler = new FIFO(null);
	}

}
