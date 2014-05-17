package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import businessmodel.MainScheduler;
import businessmodel.OrderManager;

import org.junit.Before;
import org.junit.Test;

import businessmodel.category.Seats;
import businessmodel.user.GarageHolder;


public class WorkPostTest {
	
	private WorkPost workpost;
	private TestOrder testorder;
	
	@Before
	public void setUp() throws Exception {
		testorder = new TestOrder(new GarageHolder("","",""),"");
		AssemblyLineAFactory factory = new AssemblyLineAFactory();
		this.workpost = new WorkPost("CarBodyPost", factory.createAssemblyLine(new MainScheduler(new OrderManager())));
		assertEquals("CarBodyPost", this.workpost.getName());
	}

	@Test
	public void testSetResponsibleTasks() {
		
		try {this.workpost.setResponsibleTasks(null);}
		catch (IllegalArgumentException e) {
			
		}
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
		this.workpost.switchOrders(null);
		assertEquals(this.workpost.getOrder(),null);
	}
}
