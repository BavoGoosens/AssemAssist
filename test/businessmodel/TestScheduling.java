package businessmodel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.TestOrder;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.TimeSlot;
import businessmodel.assemblyline.WorkPost;
import businessmodel.assemblyline.WorkSlot;
import businessmodel.order.Order;
import businessmodel.user.GarageHolder;
import businessmodel.user.Mechanic;


public class TestScheduling {


	@Before
	public void setUp() throws Exception {

		ArrayList<GarageHolder> holders = new ArrayList<GarageHolder>();		
		for (int i =0 ; i< 12; i++){
			GarageHolder holder = new GarageHolder(Integer.toString(i),"Geijsen","Test");
			holders.add(holder);
		}
		Mechanic man = new Mechanic("henk","","");
		String name1 = "Car Model A";
		String name2 = "Car Model B";
		String name3 = "Car Model C";
		String name4 = "Truck Model X";
		String name5 = "Truck Model Y";
		ArrayList<String> names = new ArrayList<String>();
		names.add(name1);
		names.add(name2);
		names.add(name3);
		names.add(name4);
		names.add(name5);

		Random ram = new Random();


		VehicleManufacturingCompany vhc = new VehicleManufacturingCompany();
		ArrayList<Order> orders = new ArrayList<Order>();		
		for (int i =0 ; i< 12; i++){
			int j = ram.nextInt(4-0);
			TestOrder ord = new TestOrder(holders.get(i), names.get(j));
			orders.add(ord.getOrder());
		}

		for(int i =0 ; i< orders.size()-1;i++){
			vhc.placeOrder(orders.get(i));

		}

		Iterator<AssemblyLine> iter = vhc.getAssemblyLines(man);
		while(iter.hasNext()){
			AssemblyLine assem = iter.next();
			System.out.println("-----------------New Assem");
			for(TimeSlot timeslot : assem.getAssemblyLineScheduler().getShifts().get(0).getTimeSlots()){
				System.out.println("--------New TimeSlot");
				for(WorkSlot wp : timeslot.getWorkSlots()){
					if(wp.getOrder()!= null){
						System.out.println(wp.getOrder().getUser().getFirstname());
						System.out.println(wp.getOrder().toString());

					}else{
						System.out.println("No order");
					}
				}
			}
		}


		Iterator<AssemblyLine> iter1 = vhc.getAssemblyLines(man);
		while(iter1.hasNext()){
			AssemblyLine assem = iter1.next();

			WorkPost wp1 = assem.getWorkPosts().get(0);
			Iterator<AssemblyTask> iter2 = vhc.getPendingTasks(man, wp1);
			while (iter2.hasNext()){
				AssemblyTask task = iter2.next();
				vhc.finishTask(task, 20);
			}
			wp1 = assem.getWorkPosts().get(0);
			iter2 = vhc.getPendingTasks(man, wp1);
			while (iter2.hasNext()){
				AssemblyTask task = iter2.next();
				vhc.finishTask(task, 20);
			}
			wp1 = assem.getWorkPosts().get(1);
			iter2 = vhc.getPendingTasks(man, wp1);
			while (iter2.hasNext()){
				AssemblyTask task = iter2.next();
				vhc.finishTask(task, 20);
			}
			for(int i = 0; i < 14 ; i ++){
				for(WorkPost wp: assem.getWorkPosts()){
					Iterator<AssemblyTask> iter3 = vhc.getPendingTasks(man, wp);
					while (iter3.hasNext()){
						AssemblyTask task = iter3.next();
						vhc.finishTask(task, 20);
					}
				}
			}
		}

		int count = 0;

		for (GarageHolder holder: holders){
			Iterator<Order> henk =  vhc.getCompletedOrders(holder);

			while(henk.hasNext()){
				henk.next();
				count++;
			}
		}
		System.out.println(count);
	}

	@Test
	public void test() {
	}
}
