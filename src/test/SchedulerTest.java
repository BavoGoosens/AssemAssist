package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import businessmodel.CarManufacturingCompany;
import businessmodel.CarModel;
import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.SystemTimer;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.ModelAFactory;
import businessmodel.exceptions.IllegalNumberException;
import businessmodel.order.Order;
import businessmodel.order.SingleTaskOrder;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;

public class SchedulerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	private Catalog catalog;
	private ArrayList<CarOptionCategory> categories;

	@Before
	public void setUp() throws Exception {
		OrderManager ord = new CarManufacturingCompany().getOrderManager();
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
		Order order1 = new StandardCarOrder(c1,chosen, modelA);
		Order order2 = new StandardCarOrder(c2,chosen,modelA);
		Order order3 = new StandardCarOrder(c3,chosen,modelA);
		Order order4 = new StandardCarOrder(c4,chosen,modelA);
		Order order5 = new StandardCarOrder(c5,chosen,modelA);
		Order order6 = new StandardCarOrder(c6,chosen,modelA);
		Order order7 = new StandardCarOrder(c7,chosen, modelA);
		Order order8 = new StandardCarOrder(c8,chosen,modelA);
		Order order9 = new StandardCarOrder(c9,chosen,modelA);
		Order order10 = new StandardCarOrder(c10,chosen,modelA);
		Order order11 = new StandardCarOrder(c11,chosen,modelA);
		Order order12 = new StandardCarOrder(c12,chosen,modelA);
		Order order13 = new StandardCarOrder(c13,chosen,modelA);
		Order order14 = new StandardCarOrder(c14,chosen,modelA);
		Order order15 = new StandardCarOrder(c15,chosen,modelA);
		Order order16 = new StandardCarOrder(c16,chosen,modelA);
		Order order17 = new StandardCarOrder(c17,chosen,modelA);
		Order order18 = new StandardCarOrder(c18,chosen,modelA);
		DateTime datetemp = SystemTimer.getCurrenTime();
		DateTime temp = new DateTime(datetemp.getYear(), datetemp.getMonthOfYear(), datetemp.getDayOfMonth(), 8, 0);
		temp = temp.plusDays(1);
		Order order19 = new SingleTaskOrder(c19,chosen, temp);
		Order order20 = new SingleTaskOrder(c20,chosen, temp);


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
		
		ord.getScheduler().ScheduleDay();
		
		assertEquals(ord.getScheduler().getOrdersClone().get(0),order1);

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
