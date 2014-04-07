package businessmodel.scheduler;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import businessmodel.CarOption;
import businessmodel.CarOptionCategory;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;

public class SchedulerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Scheduler sched1 = new Scheduler();
		GarageHolder c1 = new GarageHolder("","","");
		CarOption c2 = new CarOption("Henk",new CarOptionCategory("Henk"));
		ArrayList<CarOption> henk1 = new ArrayList<CarOption>();
		henk1.add(c2);
		Order order = new StandardCarOrder(c1,henk1);
		Order order1 = new StandardCarOrder(c1,henk1);
		Order order2 = new StandardCarOrder(c1,henk1);
		sched1.addOrder(order);
		sched1.addOrder(order1);
		sched1.addOrder(order2);

	}

	@Test
	public void test() {
	}

}
