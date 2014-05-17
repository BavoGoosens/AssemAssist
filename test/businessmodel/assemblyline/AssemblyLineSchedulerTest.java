package businessmodel.assemblyline;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import businessmodel.Catalog;
import businessmodel.MainScheduler;
import businessmodel.OrderManager;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.category.*;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.Order;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;
import businessmodel.user.User;

public class AssemblyLineSchedulerTest {

	AssemblyLine assemblyLine;
	AssemblyLineScheduler scheduler;
	GarageHolder holder;

	@Before
	public void setUp() throws Exception {
		AssemblyLineAFactory line = new AssemblyLineAFactory();
		assemblyLine = line.createAssemblyLine(new MainScheduler(new OrderManager()));
		scheduler = assemblyLine.getAssemblyLineScheduler();

		assertEquals(scheduler.getShifts().size(),2);
		assertEquals(scheduler.getShifts().get(0).getTimeSlots().size(),8);
		assertEquals(scheduler.getOrders().size(), 0);
		assertEquals(scheduler.getDelay(),0);
		assertEquals(scheduler.getNumberOfOrdersToSchedule(),14);
		
	}

	@Test
	public void testAddOrder() {
		ArrayList<Order> orders = new ArrayList<Order>();
		for(int i =0 ;i < 10 ; i++){
			TestOrder order = new TestOrder();
			orders.add(order.getOrder());
			if(scheduler.canAddOrder())
				scheduler.addOrder(order.getOrder());
		}
		assertEquals(scheduler.getShifts().get(0).getTimeSlots().size(),7);
		assertEquals(scheduler.getOrders().size(),10);
		assertEquals(assemblyLine.getWorkPosts().get(0).getOrder().getUser().getFirstname(),"Sander");
		
	}
}
