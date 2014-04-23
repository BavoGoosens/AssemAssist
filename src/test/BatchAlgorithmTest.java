package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import businessmodel.Car;
import businessmodel.CarModel;
import businessmodel.CarModelSpecification;

import businessmodel.OrderManager;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.Engine;
import businessmodel.exceptions.IllegalCarOptionCategoryException;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.scheduler.Scheduler;
import businessmodel.scheduler.SchedulingAlgorithm;
import businessmodel.scheduler.SpecificationBatch;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.user.Mechanic;
import businessmodel.user.User;

public class BatchAlgorithmTest {

	@Test
	public void test() {
		
		try {
			ArrayList<CarModel> carmodels = new ArrayList<CarModel>();
			OrderManager orderManager = new OrderManager(carmodels);
		
			CarOption option = new CarOption("small engine", new Engine() );
			CarOption option2 = new CarOption("big body", new Body() );
			ArrayList<CarOption> options = new ArrayList<CarOption>();
			options.add(option);
			options.add(option2);
			orderManager.addOrder((new StandardCarOrder(new GarageHolder("bouwe", "ceunen", "bouwe"), options)));
			
			CarOption option1 = new CarOption("medium engine", new Engine());
			CarOption option21 = new CarOption("big body", new Body() );
			ArrayList<CarOption> options1 = new ArrayList<CarOption>();
			options1.add(option1);
			options1.add(option21);
			orderManager.addOrder(new StandardCarOrder(new GarageHolder("sander", "ceunen", "bouwe"), options1));
			
			CarOption option11 = new CarOption("medium engine",new Engine());
			CarOption option211 = new CarOption("big body", new Body() );
			ArrayList<CarOption> options11 = new ArrayList<CarOption>();
			options11.add(option11);
			options11.add(option211);
			orderManager.addOrder(new StandardCarOrder(new GarageHolder("bavo", "ceunen", "bouwe"), options11));
			
			CarOption option111 = new CarOption("small engine",new Engine());
			ArrayList<CarOption> options111 = new ArrayList<CarOption>();
			options111.add(option111);
			orderManager.addOrder(new StandardCarOrder(new GarageHolder("michiel", "ceunen", "bouwe"), options111));
			
			CarOption option1111 = new CarOption("medium engine",new Engine());
			ArrayList<CarOption> options1111 = new ArrayList<CarOption>();
			options1111.add(option1111);
			orderManager.addOrder(new StandardCarOrder(new GarageHolder("lol", "ceunen", "bouwe"), options1111));
			
			orderManager.getScheduler().changeAlgorithm("sb");
			
			orderManager.getScheduler().ScheduleDay();
			
			for(Order order: orderManager.getScheduler().getOrders())
				System.out.println(order);
		
			
//			algo.schedule();
			
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoClearanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
