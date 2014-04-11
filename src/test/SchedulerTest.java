package test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import businessmodel.CarModel;
import businessmodel.CarOptionCategory;
import businessmodel.OrderManager;
import businessmodel.category.CarOption;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;

public class SchedulerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ArrayList<CarModel> carmodels = new ArrayList<CarModel>();
		OrderManager ord = new OrderManager(carmodels);
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


		CarOption blabla = new CarOption("Henk",new CarOptionCategory("Henk"));
		ArrayList<CarOption> henk1 = new ArrayList<CarOption>();
		henk1.add(blabla);
		Order order = new StandardCarOrder(c1,henk1);
		Order order1 = new StandardCarOrder(c2,henk1);
		Order order2 = new StandardCarOrder(c3,henk1);
		Order order3 = new StandardCarOrder(c4,henk1);
		Order order4 = new StandardCarOrder(c5,henk1);
		Order order5 = new StandardCarOrder(c6,henk1);
		Order order6 = new StandardCarOrder(c7,henk1);
		Order order7 = new StandardCarOrder(c8,henk1);
		Order order8 = new StandardCarOrder(c9,henk1);
		Order order9 = new StandardCarOrder(c10,henk1);
		Order order10 = new StandardCarOrder(c11,henk1);
		Order order11 = new StandardCarOrder(c12,henk1);
		Order order12 = new StandardCarOrder(c13,henk1);
		Order order13 = new StandardCarOrder(c14,henk1);
		Order order14 = new StandardCarOrder(c15,henk1);
		Order order15 = new StandardCarOrder(c16,henk1);
		Order order16 = new StandardCarOrder(c17,henk1);
		Order order17 = new StandardCarOrder(c18,henk1);

		ord.addOrder(order);
		ord.addOrder(order1);
		ord.addOrder(order2);
		ord.addOrder(order3);
		ord.addOrder(order4);
		ord.addOrder(order5);
		ord.addOrder(order6);
		ord.addOrder(order7);
		ord.addOrder(order8);
		ord.addOrder(order9);
		ord.addOrder(order10);
		ord.addOrder(order11);
		ord.addOrder(order12);
		ord.addOrder(order13);
		ord.addOrder(order14);
		ord.addOrder(order15);
		ord.addOrder(order16);
		ord.addOrder(order17);

		ord.getScheduler().ScheduleDay();
		
		// test
		for(int k =0; k<2 ; k++){
			for(int i =0 ; i< 8; i++){
				System.out.println(">>>>>new WorkSlot");
				for(int j = 0; j< 3; j++){
					if(ord.getScheduler().getShifts().get(k).getTimeSlots().get(i).getWorkSlots().get(j).getOrder()!= null)
						System.out.println(ord.getScheduler().getShifts().get(k).getTimeSlots().get(i).getWorkSlots().get(j).getOrder().getUser().toString());
					else
						System.out.println("0");
				}
			}
		}
	}

	@Test
	public void test() {
	}

}
