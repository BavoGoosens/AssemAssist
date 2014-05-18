package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import businessmodel.OrderManager;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.category.Airco;
import businessmodel.category.Body;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.Gearbox;
import businessmodel.category.Seats;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleModelSpecification;
import businessmodel.category.VehicleOption;
import businessmodel.category.Wheels;
import businessmodel.exceptions.IllegalSchedulingAlgorithmException;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.Order;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;
import businessmodel.user.User;


public class BatchAlgorithmTest {

	@Test
	public void test() throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {

		
			VehicleManufacturingCompany vmc = new VehicleManufacturingCompany();

			ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
			options.add(new VehicleOption("Airco", new Airco()));
			options.add(new VehicleOption("seats", new Seats()));
			options.add(new VehicleOption("Color", new Color()));
			options.add(new VehicleOption("Gearbox", new Gearbox()));
			options.add(new VehicleOption("Wheels", new Wheels()));
			
			User user = vmc.login("wow", "");
			
			VehicleOption engine = new VehicleOption("small engine", new Engine());
			VehicleOption body = new VehicleOption("big body", new Body());
			options.add(body);
			options.add(engine);
			
			Order order1 = new StandardVehicleOrder(new GarageHolder("1","",""), options, new VehicleModel("Car Model A", new VehicleModelSpecification(options)));
			vmc.placeOrder(order1);
			
			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new VehicleOption("medium engine", new Engine());
			body = new VehicleOption("big body", new Body());
			options.add(body);
			options.add(engine);

			Order order2 = new StandardVehicleOrder(new GarageHolder("2","",""), options,new VehicleModel("Car Model A", new VehicleModelSpecification(options)));
			vmc.placeOrder(order2);
			
			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new VehicleOption("medium engine", new Engine());
			body = new VehicleOption("big body", new Body());
			options.add(body);
			options.add(engine);

			Order order3 = new StandardVehicleOrder(new GarageHolder("3","",""), options,new VehicleModel("Car Model A", new VehicleModelSpecification(options)));
			vmc.placeOrder(order3);

			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new VehicleOption("medium engine", new Engine());
			body = new VehicleOption("big body", new Body());
			options.add(body);
			options.add(engine);

			Order order4 = new StandardVehicleOrder(new GarageHolder("4","",""), options,new VehicleModel("Car Model A", new VehicleModelSpecification(options)));
			vmc.placeOrder(order4);

			
			//---------------------------------------------------------------------------------
			
			options.remove(body);
			options.remove(engine);
			engine = new VehicleOption("medium engine", new Engine());
			body = new VehicleOption("body", new Body());
			options.add(body);
			options.add(engine);

			Order order5 = new StandardVehicleOrder(new GarageHolder("5","",""), options,new VehicleModel("Car Model A", new VehicleModelSpecification(options)));
			vmc.placeOrder(order5);

			
			//----------------------------------------------------------------------------------
			
//			for(VehicleOption option : orderManager.getScheduler().getUnscheduledVehicleOptions(3))
//				System.out.println(option);
			
			ArrayList<VehicleOption> options1 = new ArrayList<VehicleOption>();
			options1.add(new VehicleOption("medium engine", new Engine()));
			options1.add(new VehicleOption("big body", new Body()));
			vmc.changeSystemWideAlgorithm("sb", options1 );
			
			boolean bool = true;
			try{
				vmc.changeSystemWideAlgorithm("dsfsf", options1);
			}catch(IllegalSchedulingAlgorithmException ex){
				bool = false;
			}
			
			assertEquals(false, bool);
//			for(Order order: orderManager.getScheduler().getOrdersClone())
//				System.out.println(order.toString());
			
			Iterator<AssemblyLine> assem = vmc.getAssemblyLines(vmc.login("woww",""));
			ArrayList<AssemblyLine> listAssem = new ArrayList<AssemblyLine>();
			
			while(assem.hasNext()){
				listAssem.add(assem.next());
//				System.out.println(assem.next().getAssemblyLineScheduler().getOrders().size());
			}
			
//			System.out.println(listAssem.get(0).getAssemblyLineScheduler().getOrders().get(0).getUser());
//			System.out.println(listAssem.get(0).getAssemblyLineScheduler().getOrders().get(1).getUser());
//			System.out.println(listAssem.get(0).getAssemblyLineScheduler().getOrders().get(2).getUser());
//			System.out.println(listAssem.get(1).getAssemblyLineScheduler().getOrders().get(0).getUser());
//			System.out.println(listAssem.get(1).getAssemblyLineScheduler().getOrders().get(1).getUser());
//			
			assertEquals(listAssem.get(0).getAssemblyLineScheduler().getOrders().get(0), order3);
			assertEquals(listAssem.get(0).getAssemblyLineScheduler().getOrders().get(1), order1);
			assertEquals(listAssem.get(0).getAssemblyLineScheduler().getOrders().get(2), order5);
			assertEquals(listAssem.get(1).getAssemblyLineScheduler().getOrders().get(0), order2);
			assertEquals(listAssem.get(1).getAssemblyLineScheduler().getOrders().get(1), order4);
			
//			assertEquals(listAssem.get(0).getWorkPostOrders(),order2);
//			assertEquals(orderManager.getMainScheduler().getAssemblyLineSchedulers().get(0).getOrdersClone().get(1),order3);
//			assertEquals(orderManager.getMainScheduler().getAssemblyLineSchedulers().get(0).getOrdersClone().get(2),order5);
//			assertEquals(orderManager.getMainScheduler().getAssemblyLineSchedulers().get(0).getOrdersClone().get(3),order1);
//			assertEquals(orderManager.getMainScheduler().getAssemblyLineSchedulers().get(0).getOrdersClone().get(4),order4);

		
	}

}
