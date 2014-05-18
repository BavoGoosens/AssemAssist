package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import businessmodel.MainScheduler;
import businessmodel.OrderManager;
import businessmodel.exceptions.IllegalNumberException;
import businessmodel.exceptions.IllegalSchedulingAlgorithmException;
import businessmodel.order.Order;
import businessmodel.user.CustomShopManager;
import businessmodel.user.GarageHolder;
import businessmodel.util.TestSingleTaskOrder;
import businessmodel.util.TestStandardVehicleOrder;

public class AssemblyLineSchedulerTest {

	AssemblyLine assemblyLine;
	AssemblyLineScheduler scheduler;
	GarageHolder holder;
	CustomShopManager holder2;
	ArrayList<String> names;

	@Before
	public void setUp() throws Exception {
		AssemblyLineAFactory line = new AssemblyLineAFactory();
		assemblyLine = line.createAssemblyLine(new MainScheduler(new OrderManager()));
		scheduler = assemblyLine.getAssemblyLineScheduler();

		assertEquals(scheduler.getShifts().size(),2);
		assertEquals(scheduler.getShifts().get(0).getTimeSlots().size(),8);
		assertEquals(scheduler.getOrders().size(), 0);
		assertEquals(scheduler.getDelay(),0);
	}

	@Test
	public void testAddOrder() {

		generateVehicleModels();
		addOrders();
		
		assertEquals(scheduler.getShifts().get(0).getTimeSlots().size(),5);
		assertEquals(scheduler.getOrders().size(),12);
		assertEquals(assemblyLine.getWorkPosts().get(0).getOrder().getUser().getFirstname(),"Sander2");
		
		completeOrders(20);
		this.scheduler.changeAlgorithm("first in first out", null);
//		this.scheduler.changeAlgorithm("specification batch", null);
		
		addOrders();
		completeOrders(70);
	}
	
	@Test(expected=IllegalNumberException.class)
	public void testAdvance(){
		this.scheduler.advance(-1);		
	}
	
	@Test(expected=IllegalSchedulingAlgorithmException.class)
	public void testChangeAlgo1(){
		this.scheduler.changeAlgorithm("Henk", null);
	}

	@Test(expected=IllegalSchedulingAlgorithmException.class)
	public void testChangeAlgo2(){
		this.scheduler.changeAlgorithm(null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChangeAlgo3(){
		this.scheduler.changeAlgorithm("sb", null);
	}
	
	private void generateVehicleModels() {
		holder = new GarageHolder("Sander","","");
		holder2 = new CustomShopManager("Sander2","","");
		String name1 = "Car Model A";
		String name2 = "Car Model B";
		String name3 = "Car Model C";
		String name4 = "Truck Model X";
		String name5 = "Truck Model Y";
		names = new ArrayList<String>();
		names.add(name1);
		names.add(name2);
		names.add(name3);
		names.add(name4);
		names.add(name5);		
	}

	private void addOrders(){
		ArrayList<Order> orders = new ArrayList<Order>();
		for(int i =0 ;i < 4 ; i++){
			TestSingleTaskOrder ord = new TestSingleTaskOrder(holder2, names.get(i%4),scheduler.getCurrentTime().plusDays(2));
			orders.add(ord.getOrder());
			if(scheduler.canAddOrder(ord.getOrder()))
				scheduler.addOrder(ord.getOrder());
		}
		
		for(int i =0 ;i < 20 ; i++){
			TestStandardVehicleOrder ord = new TestStandardVehicleOrder(holder, names.get(i%4));
			orders.add(ord.getOrder());
			if(scheduler.canAddOrder(ord.getOrder()))
				scheduler.addOrder(ord.getOrder());
		}
	}

	private void completeOrders(int time) {

		WorkPost wp1 = assemblyLine.getWorkPosts().get(0);

		AssemblyTask task;
		Iterator<AssemblyTask> iter = wp1.getPendingTasks();
		while(iter.hasNext()){
			task = iter.next();
			task.completeAssemblytask(time);
		}

		iter = wp1.getPendingTasks();
		while(iter.hasNext()){
			task = iter.next();
			task.completeAssemblytask(time);
		}

		WorkPost wp2 = assemblyLine.getWorkPosts().get(1);
		iter = wp2.getPendingTasks();
		while(iter.hasNext()){
			task = iter.next();
			task.completeAssemblytask(time);
		}

		for(int i = 0; i < 14 ; i ++){
			for(WorkPost wp: assemblyLine.getWorkPosts()){
				Iterator<AssemblyTask> iter3 = wp.getPendingTasks();
				while (iter3.hasNext()){
					task = iter3.next();
					task.completeAssemblytask(time);
				}
			}
			if(i == 0){
				assertEquals(scheduler.getDayOrdersCount(),1);
			}
		}
	}
}
