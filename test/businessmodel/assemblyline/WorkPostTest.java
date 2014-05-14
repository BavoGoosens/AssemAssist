package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.assemblyline.WorkPost;
import businessmodel.assemblyline.factory.AssemblyLineAFactory;
import businessmodel.category.Body;
import businessmodel.category.Seats;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleModelSpecification;
import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;


public class WorkPostTest {
	
	private WorkPost workpost;
	private TestOrder testorder;
	
	@Before
	public void setUp() throws Exception {
		AssemblyLineAFactory factory = new AssemblyLineAFactory();
		this.workpost = new WorkPost("CarBodyPost", factory.createAssemblyLine());
		assertEquals("CarBodyPost", this.workpost.getName());
	}

	@Test
	public void testSetResponsibleTasks() {
		ArrayList<AssemblyTask> assemblytasks = new ArrayList<AssemblyTask>();
		AssemblyTask assem1 = new AssemblyTask("Test","Test", new Seats());
		AssemblyTask assem2 = new AssemblyTask("Test2","Test2", new Body());
		assemblytasks.add(assem1);
		assemblytasks.add(assem2);
		this.workpost.setResponsibleTasks(assemblytasks);
		assertEquals(this.workpost.getPendingTasks().get(0).getName(),"Test");
	}
	
	public void testOrder(){
		this.workpost.setNewOrder(testorder.getOrder());
		assertEquals(this.testorder.getOrder().getUser().getFirstname(),this.workpost.getOrder().getUser().getFirstname());
		this.workpost.switchOrders(null);
		assertEquals(this.workpost.getOrder(),null);
	}
}
