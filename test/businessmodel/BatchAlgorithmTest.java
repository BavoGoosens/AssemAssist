package businessmodel;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import businessmodel.order.StandardVehicleOrder;
import org.junit.Test;

import businessmodel.CarManufacturingCompany;
import businessmodel.VehicleModel;
import businessmodel.VehicleModelSpecification;
import businessmodel.OrderManager;
import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.CarOption;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.Gearbox;
import businessmodel.category.Seats;
import businessmodel.category.Wheels;
import businessmodel.exceptions.IllegalSchedulingAlgorithmException;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.Order;
import businessmodel.user.GarageHolder;


public class BatchAlgorithmTest {

	@Test
	public void test() throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {

		
			CarManufacturingCompany cmc = new CarManufacturingCompany();
			OrderManager orderManager = cmc.getOrderManager();

			ArrayList<CarOption> options = new ArrayList<CarOption>();
			options.add(new CarOption("Airco", new Airco()));
			options.add(new CarOption("seats", new Seats()));
			options.add(new CarOption("Color", new Color()));
			options.add(new CarOption("Gearbox", new Gearbox()));
			options.add(new CarOption("Wheels", new Wheels()));
			
			CarOption engine = new CarOption("small engine", new Engine());
			CarOption body = new CarOption("big body", new Body());
			options.add(body);
			options.add(engine);
			
<<<<<<< HEAD
			Order order1 = new StandardVehicleOrder(new GarageHolder("bouwe", "", ""), options, new CarModel("lol", new CarModelSpecification(options)));
=======
			Order order1 = new StandardCarOrder(new GarageHolder("bouwe", "", ""), options, new VehicleModel("lol", new VehicleModelSpecification(options)));
>>>>>>> 6195e12575253383c0105a029c43ce5513c4c2e5
			cmc.placeOrder(order1);
			
			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new CarOption("medium engine", new Engine());
			body = new CarOption("big body", new Body());
			options.add(body);
			options.add(engine);

	
<<<<<<< HEAD
			Order order2 = new StandardVehicleOrder(new GarageHolder("sander", "", ""), options,new CarModel("lol", new CarModelSpecification(options)));
=======
			Order order2 = new StandardCarOrder(new GarageHolder("sander", "", ""), options,new VehicleModel("lol", new VehicleModelSpecification(options)));
>>>>>>> 6195e12575253383c0105a029c43ce5513c4c2e5
			cmc.placeOrder(order2);
			
			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new CarOption("medium engine", new Engine());
			body = new CarOption("big body", new Body());
			options.add(body);
			options.add(engine);

<<<<<<< HEAD
			Order order3 = new StandardVehicleOrder(new GarageHolder("bavo", "", ""), options,new CarModel("lol", new CarModelSpecification(options)));
=======
			Order order3 = new StandardCarOrder(new GarageHolder("bavo", "", ""), options,new VehicleModel("lol", new VehicleModelSpecification(options)));
>>>>>>> 6195e12575253383c0105a029c43ce5513c4c2e5
			cmc.placeOrder(order3);

			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new CarOption("small engine", new Engine());
			body = new CarOption("small body", new Body());
			options.add(body);
			options.add(engine);

<<<<<<< HEAD
			Order order4 = new StandardVehicleOrder(new GarageHolder("michiel", "", ""), options,new CarModel("lol", new CarModelSpecification(options)));
=======
			Order order4 = new StandardCarOrder(new GarageHolder("michiel", "", ""), options,new VehicleModel("lol", new VehicleModelSpecification(options)));
>>>>>>> 6195e12575253383c0105a029c43ce5513c4c2e5
			cmc.placeOrder(order4);

			
			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new CarOption("medium engine", new Engine());
			body = new CarOption("body", new Body());
			options.add(body);
			options.add(engine);

<<<<<<< HEAD
			Order order5 = new StandardVehicleOrder(new GarageHolder("lol", "", ""), options,new CarModel("lol", new CarModelSpecification(options)));
=======
			Order order5 = new StandardCarOrder(new GarageHolder("lol", "", ""), options,new VehicleModel("lol", new VehicleModelSpecification(options)));
>>>>>>> 6195e12575253383c0105a029c43ce5513c4c2e5
			cmc.placeOrder(order5);

			
			//----------------------------------------------------------------------------------
			
//			for(CarOption option : orderManager.getScheduler().getUnscheduledCarOptions(3))
//				System.out.println(option);
			
			cmc.changeAlgorithm("sb",  new CarOption("medium engine",new Engine()));
			try{
				cmc.changeAlgorithm("dsfsf",  new CarOption("medium engine",new Engine()));
			}catch(IllegalSchedulingAlgorithmException ex){
				ex.getMessage();
			}
			orderManager.getScheduler().ScheduleDay();

//			for(Order order: orderManager.getScheduler().getOrdersClone())
//				System.out.println(order.toString());
			
			assertEquals(orderManager.getScheduler().getOrdersClone().get(0),order2);
			assertEquals(orderManager.getScheduler().getOrdersClone().get(1),order3);
			assertEquals(orderManager.getScheduler().getOrdersClone().get(2),order5);
			assertEquals(orderManager.getScheduler().getOrdersClone().get(3),order1);
			assertEquals(orderManager.getScheduler().getOrdersClone().get(4),order4);

		
	}

}
