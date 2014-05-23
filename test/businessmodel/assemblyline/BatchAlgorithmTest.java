package businessmodel.assemblyline;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

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
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;
import businessmodel.user.User;
import control.StandardOrderHandler;


public class BatchAlgorithmTest {

	private StandardOrderHandler controllerStandard;
	private User garageholder;

	@Test
	public void test() throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {


		VehicleManufacturingCompany vmc = new VehicleManufacturingCompany();

		ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
		options.add(new VehicleOption("Airco", new Airco()));
		options.add(new VehicleOption("seats", new Seats()));
		options.add(new VehicleOption("Color", new Color()));
		options.add(new VehicleOption("Gearbox", new Gearbox()));
		options.add(new VehicleOption("Wheels", new Wheels()));

		this.garageholder = vmc.login("wow", "");

		this.controllerStandard = new StandardOrderHandler(vmc);

		VehicleOption engine = new VehicleOption("medium engine", new Engine());
		VehicleOption body = new VehicleOption("big body", new Body());
		options.add(body);
		options.add(engine);

		StandardVehicleOrder order1 = new StandardVehicleOrder(new GarageHolder("1","",""), options, new VehicleModel("Car Model A", new VehicleModelSpecification(options)));
		this.controllerStandard.placeOrder(this.garageholder,order1);

		//---------------------------------------------------------------------------------

		options.remove(body);
		options.remove(engine);
		engine = new VehicleOption("medium engine", new Engine());
		body = new VehicleOption("big body", new Body());
		options.add(body);
		options.add(engine);

		StandardVehicleOrder order2 = new StandardVehicleOrder(new GarageHolder("2","",""), options,new VehicleModel("Car Model A", new VehicleModelSpecification(options)));
		this.controllerStandard.placeOrder(this.garageholder,order2);

		//---------------------------------------------------------------------------------

		options.remove(body);
		options.remove(engine);
		engine = new VehicleOption("small engine", new Engine());
		body = new VehicleOption("big body", new Body());
		options.add(body);
		options.add(engine);

		StandardVehicleOrder order3 = new StandardVehicleOrder(new GarageHolder("3","",""), options,new VehicleModel("Car Model A", new VehicleModelSpecification(options)));
		this.controllerStandard.placeOrder(this.garageholder,order3);

		//---------------------------------------------------------------------------------

		options.remove(body);
		options.remove(engine);
		engine = new VehicleOption("medium engine", new Engine());
		body = new VehicleOption("big body", new Body());
		options.add(body);
		options.add(engine);

		StandardVehicleOrder order4 = new StandardVehicleOrder(new GarageHolder("4","",""), options,new VehicleModel("Car Model A", new VehicleModelSpecification(options)));
		this.controllerStandard.placeOrder(this.garageholder,order4);


		//---------------------------------------------------------------------------------

		options.remove(body);
		options.remove(engine);
		engine = new VehicleOption("medium engine", new Engine());
		body = new VehicleOption("big body", new Body());
		options.add(body);
		options.add(engine);

		StandardVehicleOrder order5 = new StandardVehicleOrder(new GarageHolder("5","",""), options,new VehicleModel("Car Model A", new VehicleModelSpecification(options)));
		this.controllerStandard.placeOrder(this.garageholder,order5);


		//----------------------------------------------------------------------------------

		options.remove(body);
		options.remove(engine);
		engine = new VehicleOption("medium engine", new Engine());
		body = new VehicleOption("body", new Body());
		options.add(body);
		options.add(engine);

		StandardVehicleOrder order6 = new StandardVehicleOrder(new GarageHolder("6","",""), options,new VehicleModel("Car Model A", new VehicleModelSpecification(options)));
		this.controllerStandard.placeOrder(this.garageholder,order6);

		//----------------------------------------------------------------------------------

		options.remove(body);
		options.remove(engine);
		engine = new VehicleOption("medium engine", new Engine());
		body = new VehicleOption("body", new Body());
		options.add(body);
		options.add(engine);

		StandardVehicleOrder order7 = new StandardVehicleOrder(new GarageHolder("7","",""), options,new VehicleModel("Car Model A", new VehicleModelSpecification(options)));
		this.controllerStandard.placeOrder(this.garageholder,order7);

		//----------------------------------------------------------------------------------

		options.remove(body);
		options.remove(engine);
		engine = new VehicleOption("medium engine", new Engine());
		body = new VehicleOption("big body", new Body());
		options.add(body);
		options.add(engine);

		StandardVehicleOrder order8 = new StandardVehicleOrder(new GarageHolder("8","",""), options,new VehicleModel("Car Model A", new VehicleModelSpecification(options)));
		this.controllerStandard.placeOrder(this.garageholder,order8);


		ArrayList<VehicleOption> options1 = new ArrayList<VehicleOption>();
		options1.add(new VehicleOption("medium engine", new Engine()));
		options1.add(new VehicleOption("big body", new Body()));
		vmc.changeSystemWideAlgorithm("SpecificationBatch", options1 );

		boolean bool = true;
		try{
			vmc.changeSystemWideAlgorithm("dsfsf", options1);
		}catch(IllegalSchedulingAlgorithmException ex){
			bool = false;
		}

		assertEquals(false, bool);

		Iterator<AssemblyLine> assem = vmc.getAssemblyLines(vmc.login("woww",""));
		ArrayList<AssemblyLine> listAssem = new ArrayList<AssemblyLine>();

		while(assem.hasNext()){
			listAssem.add(assem.next());
		}


		assertEquals(listAssem.get(0).getAssemblyLineScheduler().getOrders().get(0), order1);
		assertEquals(listAssem.get(0).getAssemblyLineScheduler().getOrders().get(1), order5);
		assertEquals(listAssem.get(0).getAssemblyLineScheduler().getOrders().get(2), order8);
		assertEquals(listAssem.get(0).getAssemblyLineScheduler().getOrders().get(3), order3);
		assertEquals(listAssem.get(1).getAssemblyLineScheduler().getOrders().get(0), order2);
		assertEquals(listAssem.get(1).getAssemblyLineScheduler().getOrders().get(1), order4);
		assertEquals(listAssem.get(1).getAssemblyLineScheduler().getOrders().get(2), order6);
		assertEquals(listAssem.get(2).getAssemblyLineScheduler().getOrders().get(0), order7);

	}

}
