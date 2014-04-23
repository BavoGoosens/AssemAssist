package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import businessmodel.CarModel;
import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.WorkPost;
import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.exceptions.IllegalNumberException;
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
		GarageHolder c19 = new GarageHolder("19","","");
		GarageHolder c20 = new GarageHolder("20","","");

		CarOption blabla = new CarOption("Henk", new Airco());
		ArrayList<CarOption> henk1 = new ArrayList<CarOption>();
		henk1.add(blabla);
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


		ord.addOrder(order1);
//		ord.addOrder(order2);
//		ord.addOrder(order3);
//		ord.addOrder(order4);
//		ord.addOrder(order5);
//		ord.addOrder(order6);
//		ord.addOrder(order19);
//		ord.addOrder(order7);
//		ord.addOrder(order8);
//		ord.addOrder(order9);
//		ord.addOrder(order10);
//		ord.addOrder(order11);
//		ord.addOrder(order12);
//		ord.addOrder(order13);
//		ord.addOrder(order14);
//		ord.addOrder(order15);
//		ord.addOrder(order20);
//		ord.addOrder(order16);
//		ord.addOrder(order17);
//		ord.addOrder(order18);
		
		ord.getScheduler().ScheduleDay();
		
		assertEquals(ord.getScheduler().getOrders().get(0),order1);

		try{
		ord.getScheduler().advance(-1);
		}catch(IllegalNumberException ex){
			ex.getNumber();
			ex.getMessage();
		}

	



	}

	@Test
	public void test() {
		
			
	}

}
