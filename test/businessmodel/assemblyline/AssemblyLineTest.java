package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import businessmodel.MainScheduler;
import businessmodel.OrderManager;
import businessmodel.order.Order;
import businessmodel.user.GarageHolder;
import businessmodel.util.TestStandardVehicleOrder;

public class AssemblyLineTest {

	private AssemblyLine assemblyLine;
	GarageHolder holder;

	@Before
	public void setUp() throws Exception {
		AssemblyLineCFactory factory = new AssemblyLineCFactory();
		assemblyLine = factory.createAssemblyLine(new MainScheduler(new OrderManager()));
		holder = new GarageHolder("Sander","","");
	}

	@Test
	public void test() {

		this.assemblyLine.advance(null);

		TestStandardVehicleOrder order= new TestStandardVehicleOrder(holder,"Car Model A");
		this.assemblyLine.advance(order.getOrder());
		assertTrue(this.assemblyLine.getWorkPostOrders().contains(order.getOrder()));

		this.assemblyLine.workPostCompleted(30);
		assertEquals(this.assemblyLine.getTimeCurrentStatus(),30);
		this.assemblyLine.workPostCompleted(50);
		assertEquals(this.assemblyLine.getTimeCurrentStatus(),50);
		this.assemblyLine.workPostCompleted(40);
		assertEquals(this.assemblyLine.getTimeCurrentStatus(),50);
	}

	@Test(expected=IllegalStateException.class)
	public void testAdvance(){
		TestStandardVehicleOrder order= new TestStandardVehicleOrder(holder,"Car Model A");
		this.assemblyLine.advance(order.getOrder());
		this.assemblyLine.advance(order.getOrder());
	}
	
	@Test(expected=	IllegalArgumentException.class)
	public void testSetAssemblyLineScheduler(){
		this.assemblyLine.setAssemblylineScheduler(null);
	}
	
	@Test(expected=	IllegalArgumentException.class)
	public void testSetMainScheduler(){
		this.assemblyLine.setMainScheduler(null);
	}
	
	@Test(expected=	IllegalArgumentException.class)
	public void testSetResponsibleModels(){
		this.assemblyLine.setResponsibleModels(null);
	}

	@Test(expected=	IllegalArgumentException.class)
	public void testSetWorkPosts(){
		this.assemblyLine.setWorkPosts(null);
	}

}
