package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


import businessmodel.AssemblyTask;
import businessmodel.CarManufacturingCompany;
import businessmodel.CarModel;
import businessmodel.OrderManager;
import businessmodel.WorkPost;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.Engine;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.scheduler.Scheduler;
import businessmodel.user.GarageHolder;

public class ProductionSchedulerTest {
	
	private GarageHolder garageholder;
	private ArrayList<Order> orders;
	private OrderManager om;
	private StandardCarOrder order;

	@Before
	public void setUp() throws Exception {
		
		ArrayList<CarModel> carmodels = new ArrayList<CarModel>();
		om = new OrderManager(carmodels);
		garageholder = new GarageHolder("bouwe", "ceunen", "bouwe");
		orders = new ArrayList<Order>();
		
		CarOption option = new CarOption("small engine", new Engine() );
		CarOption option2 = new CarOption("big body", new Body() );
		ArrayList<CarOption> options = new ArrayList<CarOption>();
		options.add(option);
		options.add(option2);
		order = new StandardCarOrder(garageholder, options);
		orders.add(order);
		om.addOrder(order);
		
	}

	@SuppressWarnings("unused")
	@Test
	public void test() {
		CarManufacturingCompany cmc = new CarManufacturingCompany();
		
		Scheduler prodsched = om.getScheduler();
		assertTrue(om.getCompletedOrders().isEmpty());
		
		for(Order order: this.orders)
			om.placeOrder(order);
		
		assertTrue(om.getCompletedOrders().isEmpty());
		assertEquals(orders.get(0),om.getPendingOrders().get(0));
		om.getScheduler().ScheduleDay();
		prodsched.advance(60);
		
		for(Order order:this.orders){
			for(WorkPost wp: prodsched.getAssemblyline().getWorkPosts()){
				if(wp.getOrder() != null){
					for(AssemblyTask assem : wp.getPendingTasks())
							cmc.completeAssemBlyTask(assem, 20);
					
				}
			}
		}
		
		for(Order order:this.orders){
			for(WorkPost wp: prodsched.getAssemblyline().getWorkPosts()){
				if(wp.getOrder() != null){
					for(AssemblyTask assem : wp.getPendingTasks())
							cmc.completeAssemBlyTask(assem, 20);
					
				}
			}
			
		}
		for(Order order:this.orders){
			for(WorkPost wp: prodsched.getAssemblyline().getWorkPosts()){
				if(wp.getOrder() != null){
					for(AssemblyTask assem : wp.getPendingTasks())
							cmc.completeAssemBlyTask(assem, 20);
					
				}
			}
			
		}
		assertTrue(om.getPendingOrders().isEmpty());
	}

}
