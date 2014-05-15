package businessmodel;

import static org.junit.Assert.*;

import java.util.ArrayList;

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

		OrderManager ordermanager = new OrderManager();
		ArrayList<Order> orders = new ArrayList<Order>();		
		for (int i =0 ; i< 12; i++){
			TestOrder ord = new TestOrder();
			ord.setUser(holders.get(i));
			orders.add(ord.getOrder());
		}

		for(int i =0 ; i< orders.size()-1;i++){
			ordermanager.placeOrder(orders.get(i));

		}

		for(AssemblyLine assem: ordermanager.getMainScheduler().getAssemblylines()){
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
