package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.MainScheduler;
import businessmodel.OrderManager;
import businessmodel.category.Seats;
import businessmodel.category.VehicleOption;

public class AssemblyTaskTest {

	private AssemblyTask task;
	private AssemblyTask task2;

	@Before
	public void setUp() throws Exception {
		AssemblyLineAFactory facta = new AssemblyLineAFactory();
		task = new AssemblyTask("Test", "Test2", new Seats(), false);
		task2 = new AssemblyTask("Test", "Test2", new Seats(), new WorkPost("henk",
				facta.createAssemblyLine(new MainScheduler(new OrderManager()))), false);
	}

	@Test
	public void test() {
		assertEquals(task.getDescription(),"Test2");
		assertEquals(task.getName(),"Test");
		assertEquals(task2.getWorkpost().getName(),"henk");
		
		task2.completeAssemblytask(20);
		assertTrue(task2.isCompleted());
		assertEquals(task2.toString(),"Test");
		assertEquals(task.getCategory().getClass(),new Seats().getClass());
		
		ArrayList<VehicleOption> options = task2.getInstallableOptions();
		assertEquals(options.get(0).getName(),"leather white");
		assertEquals(options.get(1).getName(),"leather black");
		assertEquals(options.get(2).getName(),"vinyl grey");
		assertEquals(options.get(3).getName(),"vinyl black");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetName(){
		task = new AssemblyTask(null,"",new Seats(), false);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetDecription(){
		task = new AssemblyTask("",null,new Seats(), false);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetOption(){
		task = new AssemblyTask("","",null, false);
	}
	
}
