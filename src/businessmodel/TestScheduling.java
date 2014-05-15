package businessmodel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.TestOrder;
import businessmodel.assemblyline.TimeSlot;
import businessmodel.assemblyline.WorkSlot;
import businessmodel.order.Order;
import businessmodel.user.GarageHolder;


public class TestScheduling {


	@Before
	public void setUp() throws Exception {

		ArrayList<GarageHolder> holders = new ArrayList<GarageHolder>();		
		for (int i =0 ; i< 12; i++){
			GarageHolder holder = new GarageHolder(Integer.toString(i),"Geijsen","Test");
			holders.add(holder);
		}
		
		String name1 = "Vehicle Model A";
		String name2 = "Vehicle Model B";
		String name3 = "Vehicle Model C";
		String name4 = "Vehicle Model X";
		String name5 = "Vehicle Model Y";
		ArrayList<String> names = new ArrayList<String>();
		names.add(name1);
		names.add(name2);
		names.add(name3);
		names.add(name4);
		names.add(name5);
		
		Random ram = new Random();
		
		
		OrderManager ordermanager = new OrderManager();
		ArrayList<Order> orders = new ArrayList<Order>();		
		for (int i =0 ; i< 12; i++){
			int j = ram.nextInt(4-0);
			TestOrder ord = new TestOrder(holders.get(i), names.get(j));
			orders.add(ord.getOrder());
		}

		for(int i =0 ; i< orders.size()-1;i++){
			ordermanager.placeOrder(orders.get(i));

		}

		for(AssemblyLine assem: ordermanager.getMainScheduler().getAssemblyLines()){
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
	}

	@Test
	public void test() {
	}
}
