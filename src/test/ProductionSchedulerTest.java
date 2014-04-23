package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import businessmodel.AssemblyTask;
import businessmodel.CarManufacturingCompany;
import businessmodel.CarModel;
import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.WorkPost;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.Engine;
import businessmodel.category.ModelAFactory;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.scheduler.Scheduler;
import businessmodel.user.GarageHolder;

public class ProductionSchedulerTest {
	
	private GarageHolder garageholder;
	private ArrayList<Order> orders;
	private OrderManager om;
	private StandardCarOrder order;
	private ArrayList<CarOptionCategory> categories;
	private Catalog catalog;

	@Before
	public void setUp() throws Exception {
		
		om = new CarManufacturingCompany().getOrderManager();
		garageholder = new GarageHolder("bouwe", "ceunen", "bouwe");
		orders = new ArrayList<Order>();
		
		this.catalog = new Catalog();
		this.categories = this.catalog.getAllCategories();

		CarModel modelA = new ModelAFactory().createModel();
		ArrayList<CarOption> chosen = new ArrayList<CarOption>();
		for (CarOptionCategory category: this.categories) {
			ArrayList<CarOption> options = modelA.getCarModelSpecification().getOptionsOfCategory(category);
			if (options.size() > 0) {
				chosen.add(options.get(0));
			}
		}
		
		order = new StandardCarOrder(garageholder, chosen);
		orders.add(order);
		orders.add(order);
		orders.add(order);
		om.placeOrder(order);
		om.placeOrder(order);
		om.placeOrder(order);
		
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
		assertEquals(orders.get(0),om.getScheduler().getOrders().get(0));
		om.getScheduler().ScheduleDay();
		
		for(Order order:this.orders){
			for(WorkPost wp: prodsched.getAssemblyline().getWorkPosts()){
				if(wp.getOrder() != null){
					Iterator<AssemblyTask> iter = cmc.getPendingTasks(wp);
					List<AssemblyTask> copy = new ArrayList<AssemblyTask>();
					while (iter.hasNext())
					    copy.add(iter.next());
					for(AssemblyTask assem : copy)
							cmc.completeAssemBlyTask(assem, 20);
					
				}
			}
		}
		
		for(Order order:this.orders){
			for(WorkPost wp: prodsched.getAssemblyline().getWorkPosts()){
				if(wp.getOrder() != null){
					
					Iterator<AssemblyTask> iter = cmc.getPendingTasks(wp);
					List<AssemblyTask> copy = new ArrayList<AssemblyTask>();
					while (iter.hasNext())
					    copy.add(iter.next());
					for(AssemblyTask assem : copy)
							cmc.completeAssemBlyTask(assem, 20);
					
				}
			}
			
		}
		for(Order order:this.orders){
			for(WorkPost wp: prodsched.getAssemblyline().getWorkPosts()){
				if(wp.getOrder() != null){
					Iterator<AssemblyTask> iter = cmc.getPendingTasks(wp);
					List<AssemblyTask> copy = new ArrayList<AssemblyTask>();
					while (iter.hasNext())
					    copy.add(iter.next());
					for(AssemblyTask assem : copy)
							cmc.completeAssemBlyTask(assem, 20);
					
				}
			}
			
		}
		assertTrue(om.getScheduler().getOrders().isEmpty());
	}

}
