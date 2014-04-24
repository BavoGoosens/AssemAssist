package businessmodel;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.Gearbox;
import businessmodel.category.Seats;
import businessmodel.category.Wheels;
import businessmodel.order.Order;
import businessmodel.order.SingleTaskOrder;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;

public class SchedulerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		CarManufacturingCompany cmc = new CarManufacturingCompany();
		OrderManager ord = cmc.getOrderManager();
		GarageHolder c1 = new GarageHolder("1","","");
		GarageHolder c2 = new GarageHolder("2","","");
		GarageHolder c3 = new GarageHolder("3","","");
		GarageHolder c4 = new GarageHolder("4","","");
		GarageHolder c5 = new GarageHolder("5","","");
		GarageHolder c6 = new GarageHolder("6","","");
		GarageHolder c7 = new GarageHolder("7","","");
		GarageHolder c8 = new GarageHolder("8","","");
		GarageHolder c9 = new GarageHolder("9","","");
		GarageHolder c10 = new GarageHolder("10","","");
		GarageHolder c11 = new GarageHolder("11","","");
		GarageHolder c12 = new GarageHolder("12","","");
		GarageHolder c13 = new GarageHolder("13","","");
		GarageHolder c14 = new GarageHolder("14","","");
		GarageHolder c15 = new GarageHolder("15","","");
		GarageHolder c16 = new GarageHolder("16","","");
		GarageHolder c17 = new GarageHolder("17","","");
		GarageHolder c18 = new GarageHolder("18","","");
		GarageHolder c19 = new GarageHolder("19","","");
		GarageHolder c20 = new GarageHolder("20","","");

		CarOption airco = new CarOption("Arico", new Airco());
		CarOption body = new CarOption("Body", new Body());
		CarOption seats = new CarOption("seats", new Seats());
		CarOption color = new CarOption("Color", new Color());
		CarOption gearbox = new CarOption("Gearbox", new Gearbox());
		CarOption engine = new CarOption("Engine", new Engine());
		CarOption wheels = new CarOption("Wheels", new Wheels());
		ArrayList<CarOption> henk1 = new ArrayList<CarOption>();
		henk1.add(airco);
		henk1.add(body);
		henk1.add(seats);
		henk1.add(color);
		henk1.add(gearbox);
		henk1.add(engine);
		henk1.add(wheels);
		Order order1 = new StandardCarOrder(c1,henk1);
		Order order2 = new StandardCarOrder(c2,henk1);
		Order order3 = new StandardCarOrder(c3,henk1);
		Order order4 = new StandardCarOrder(c4,henk1);
		Order order5 = new StandardCarOrder(c5,henk1);
		Order order6 = new StandardCarOrder(c6,henk1);
		Order order7 = new StandardCarOrder(c7,henk1);
		Order order8 = new StandardCarOrder(c8,henk1);
		Order order9 = new StandardCarOrder(c9,henk1);
		Order order10 = new StandardCarOrder(c10,henk1);
		Order order11 = new StandardCarOrder(c11,henk1);
		Order order12 = new StandardCarOrder(c12,henk1);
		Order order13 = new StandardCarOrder(c13,henk1);
		Order order14 = new StandardCarOrder(c14,henk1);
		Order order15 = new StandardCarOrder(c15,henk1);
		Order order16 = new StandardCarOrder(c16,henk1);
		Order order17 = new StandardCarOrder(c17,henk1);
		Order order18 = new StandardCarOrder(c18,henk1);
		DateTime datetemp = new DateTime();
		DateTime temp = new DateTime(datetemp.getYear(), datetemp.getMonthOfYear(), datetemp.getDayOfMonth()+1, 8, 0);
		Order order19 = new SingleTaskOrder(c19,henk1, temp);
		Order order20 = new SingleTaskOrder(c20,henk1, temp);


		ord.placeOrder(order1);
		ord.placeOrder(order2);
		ord.placeOrder(order3);
		ord.placeOrder(order4);
		ord.placeOrder(order5);
		ord.placeOrder(order6);
		ord.placeOrder(order19);
		ord.placeOrder(order7);
		ord.placeOrder(order8);
		ord.placeOrder(order9);
		ord.placeOrder(order10);
		ord.placeOrder(order11);
		ord.placeOrder(order12);
		ord.placeOrder(order13);
		ord.placeOrder(order14);
		ord.placeOrder(order15);

		ord.placeOrder(order20);
		ord.placeOrder(order16);
		ord.placeOrder(order17);
		ord.placeOrder(order18);


		// test
		for(int k =0; k<2 ; k++){
			for(int i =0 ; i< 7; i++){
				System.out.println(">>>>>new WorkSlot");
				for(int j = 0; j< 3; j++){
					if(ord.getScheduler().getShifts().get(k).getTimeSlots().get(i).getWorkSlots().get(j).getOrder()!= null){
						System.out.println(ord.getScheduler().getShifts().get(k).getTimeSlots().get(i).getWorkSlots().get(j).getOrder().toString());
					}else{
						System.out.println("0");
					}
				}
			}
		}
		System.out.println("----------------------------------------------------------");

		AssemblyLine testassembly = ord.getScheduler().getAssemblyline();
		
		assertEquals(testassembly.getWorkPosts().get(0).getResponsibleTasksClone().get(0).toString(),"Assembly Car Body");

		for(WorkPost wp1 : ord.getScheduler().getAssemblyline().getWorkPosts()){
			if(wp1.getOrder()!= null)
				System.out.println(wp1.getOrder().getUser());
			else
				System.out.println("0");
		} 
		
		
		System.out.println("===========");
		WorkPost wp = testassembly.getWorkPosts().get(0);

		Iterator<AssemblyTask> iter = cmc.getPendingTasks(wp);
		List<AssemblyTask> copy = new ArrayList<AssemblyTask>();
		while (iter.hasNext())
		    copy.add(iter.next());
		for(AssemblyTask assem : copy)
				cmc.finishTask(assem, 20);
		
		for(WorkPost wp1 : ord.getScheduler().getAssemblyline().getWorkPosts()){
			if(wp1.getOrder()!= null)
				System.out.println(wp1.getOrder().getUser());
			else
				System.out.println("0");
		}
		
		wp = testassembly.getWorkPosts().get(0);
		
		Iterator<AssemblyTask> iter1 = cmc.getPendingTasks(wp);
		List<AssemblyTask> copy1 = new ArrayList<AssemblyTask>();
		while (iter1.hasNext())
		    copy1.add(iter1.next());
		for(AssemblyTask assem : copy1)
				cmc.finishTask(assem, 20);
		
		wp = testassembly.getWorkPosts().get(1);

		Iterator<AssemblyTask> iter11 = cmc.getPendingTasks(wp);
		List<AssemblyTask> copy11 = new ArrayList<AssemblyTask>();
		while (iter11.hasNext())
		    copy11.add(iter11.next());
		for(AssemblyTask assem : copy11)
				cmc.finishTask(assem, 20);
		
		for(int i=0; i < 16; i++){
			for(WorkPost wp1 : ord.getScheduler().getAssemblyline().getWorkPosts()){
				if(wp1.getOrder()!= null)
					System.out.println(wp1.getOrder().getUser());
				else
					System.out.println("0");
			}
			System.out.println("=====");

			for(WorkPost wp1: testassembly.getWorkPosts()){

				Iterator<AssemblyTask> iter111 = cmc.getPendingTasks(wp1);
				List<AssemblyTask> copy111 = new ArrayList<AssemblyTask>();
				while (iter111.hasNext())
				    copy111.add(iter111.next());
				for(AssemblyTask assem : copy111)
						cmc.finishTask(assem, 20);
			}
		}

		for(Order order: ord.getCompletedOrders()){
			System.out.println(order.getUser());
			System.out.println(order.getCompletionDate().toString());
		}
	}

	@Test
	public void test() {
	}

}
