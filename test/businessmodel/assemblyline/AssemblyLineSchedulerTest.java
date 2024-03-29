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
		assertEquals(scheduler.getShifts().get(1).getTimeSlots().size(),8);
		assertEquals(scheduler.getOrders().size(), 0);
		assertEquals(scheduler.getDelay(),0);
		
		AssemblyLineCFactory line2 = new AssemblyLineCFactory();
		assemblyLine = line2.createAssemblyLine(new MainScheduler(new OrderManager()));
		
		assertEquals(scheduler.getShifts().size(),2);
		assertEquals(scheduler.getShifts().get(0).getTimeSlots().size(),8);
		assertEquals(scheduler.getShifts().get(1).getTimeSlots().size(),8);
		assertEquals(scheduler.getOrders().size(), 0);
		assertEquals(scheduler.getDelay(),0);

	}

	@Test
	public void testAddOrder() {

		generateVehicleModels();
		addOrders();
		
		assertEquals(scheduler.getShifts().get(0).getTimeSlots().size(),6);
		assertEquals(scheduler.getOrders().size(),14);
		
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
		this.scheduler.changeAlgorithm("SpecificationBatch", null);
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

		WorkPost wp1 = assemblyLine.getWorkPostsIterator().next();

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

		WorkPost wp2 = assemblyLine.getWorkPostsIterator().next();
		iter = wp2.getPendingTasks();
		while(iter.hasNext()){
			task = iter.next();
			task.completeAssemblytask(time);
		}

		for(int i = 0; i < 14 ; i ++){
			Iterator<WorkPost> it = assemblyLine.getWorkPostsIterator();
			while(it.hasNext()){
				Iterator<AssemblyTask> iter3 = it.next().getPendingTasks();
				while (iter3.hasNext()){
					task = iter3.next();
					task.completeAssemblytask(time);
				}
			}
		}
	}
}
