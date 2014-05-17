package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import businessmodel.MainScheduler;
import businessmodel.OrderManager;
import businessmodel.category.Seats;
import businessmodel.user.GarageHolder;
import businessmodel.util.TestStandardVehicleOrder;


public class WorkPostTest {

	private WorkPost workpost;
	private TestStandardVehicleOrder testorder;

	@Before
	public void setUp() throws Exception {
		testorder = new TestStandardVehicleOrder(new GarageHolder("","",""),"Car Model A");
		AssemblyLineAFactory factory = new AssemblyLineAFactory();
		this.workpost = new WorkPost("CarBodyPost", factory.createAssemblyLine(new MainScheduler(new OrderManager())));
		assertEquals("CarBodyPost", this.workpost.getName());
	}

	@Test
	public void testSetResponsibleTasks() {

		ArrayList<AssemblyTask> assemblytasks = new ArrayList<AssemblyTask>();
		AssemblyTask assem1 = new AssemblyTask("Test","Test", new Seats());
		assemblytasks.add(assem1);
		assemblytasks.add(assem1);
		this.workpost.setResponsibleTasks(assemblytasks);
		assertEquals(this.workpost.getResponsibleTasksClone().get(0).getName(),"Test");
	}

	@Test
	public void testOrder(){
		this.workpost.setNewOrder(testorder.getOrder());
		assertEquals(this.testorder.getOrder().getUser().getFirstname(),this.workpost.getOrder().getUser().getFirstname());
		Iterator<AssemblyTask> iter = this.workpost.getFinishedTasks();
		this.workpost.switchOrders(null);
		assertEquals(this.workpost.getOrder(),null);

		assertEquals(this.workpost.toString(),"CarBodyPost");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetName(){
		this.workpost = new WorkPost(null,new AssemblyLine());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPossiblyAssemblyTasks(){
		this.workpost.setResponsibleTasks(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPossiblyAssemblyTasks(){
		this.workpost.possibleAssemblyTasks(null);
	}
}
