package businessmodel;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import businessmodel.order.StandardVehicleOrder;
import org.junit.Test;

import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleModelSpecification;
import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.VehicleOption;
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

		
			VehicleManufacturingCompany cmc = new VehicleManufacturingCompany();
			OrderManager orderManager = cmc.getOrderManager();

			ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
			options.add(new VehicleOption("Airco", new Airco()));
			options.add(new VehicleOption("seats", new Seats()));
			options.add(new VehicleOption("Color", new Color()));
			options.add(new VehicleOption("Gearbox", new Gearbox()));
			options.add(new VehicleOption("Wheels", new Wheels()));
			
			VehicleOption engine = new VehicleOption("small engine", new Engine());
			VehicleOption body = new VehicleOption("big body", new Body());
			options.add(body);
			options.add(engine);
			
			Order order1 = new StandardVehicleOrder(new GarageHolder("bouwe", "", ""), options, new VehicleModel("lol", new VehicleModelSpecification(options)));
			cmc.placeOrder(order1);
			
			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new VehicleOption("medium engine", new Engine());
			body = new VehicleOption("big body", new Body());
			options.add(body);
			options.add(engine);

			Order order2 = new StandardVehicleOrder(new GarageHolder("sander", "", ""), options,new VehicleModel("lol", new VehicleModelSpecification(options)));
			cmc.placeOrder(order2);
			
			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new VehicleOption("medium engine", new Engine());
			body = new VehicleOption("big body", new Body());
			options.add(body);
			options.add(engine);

			Order order3 = new StandardVehicleOrder(new GarageHolder("bavo", "", ""), options,new VehicleModel("lol", new VehicleModelSpecification(options)));
			cmc.placeOrder(order3);

			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new VehicleOption("small engine", new Engine());
			body = new VehicleOption("small body", new Body());
			options.add(body);
			options.add(engine);

			Order order4 = new StandardVehicleOrder(new GarageHolder("michiel", "", ""), options,new VehicleModel("lol", new VehicleModelSpecification(options)));
			cmc.placeOrder(order4);

			
			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new VehicleOption("medium engine", new Engine());
			body = new VehicleOption("body", new Body());
			options.add(body);
			options.add(engine);

			Order order5 = new StandardVehicleOrder(new GarageHolder("lol", "", ""), options,new VehicleModel("lol", new VehicleModelSpecification(options)));
			cmc.placeOrder(order5);

			
			//----------------------------------------------------------------------------------
			
//			for(VehicleOption option : orderManager.getScheduler().getUnscheduledVehicleOptions(3))
//				System.out.println(option);
			
			cmc.changeAlgorithm("sb",  new VehicleOption("medium engine",new Engine()));
			try{
				cmc.changeAlgorithm("dsfsf",  new VehicleOption("medium engine",new Engine()));
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
