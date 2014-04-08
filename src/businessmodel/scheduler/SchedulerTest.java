package businessmodel.scheduler;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import businessmodel.CarModel;
import businessmodel.CarOption;
import businessmodel.CarOptionCategory;
import businessmodel.OrderManager;
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
		GarageHolder c1 = new GarageHolder("","","");
		CarOption c2 = new CarOption("Henk",new CarOptionCategory("Henk"));
		ArrayList<CarOption> henk1 = new ArrayList<CarOption>();
		henk1.add(c2);
		Order order = new StandardCarOrder(c1,henk1);
		Order order1 = new StandardCarOrder(c1,henk1);
		Order order2 = new StandardCarOrder(c1,henk1);
		ord.addOrder(order);
		ord.addOrder(order1);
		ord.addOrder(order2);
		ord.getScheduler().ScheduleDay();
	}

	@Test
	public void test() {
	}

}
