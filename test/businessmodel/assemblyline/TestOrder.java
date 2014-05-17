package businessmodel.assemblyline;

import java.util.ArrayList;

import businessmodel.category.Body;
import businessmodel.category.Color;
import businessmodel.category.Engine;
import businessmodel.category.Gearbox;
import businessmodel.category.Seats;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleModelSpecification;
import businessmodel.category.VehicleOption;
import businessmodel.category.Wheels;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;

public class TestOrder {

	private StandardVehicleOrder order;
	
	public TestOrder(){
		makeOrder();
	}
	
	private void makeOrder(){
		GarageHolder holder = new GarageHolder("Sander","Geijsen","Test");
		ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
		VehicleOption option1 = new VehicleOption("Seats",new Seats());
		VehicleOption option2 = new VehicleOption("Body",new Body());
		VehicleOption option3 = new VehicleOption("Engine",new Engine());
		VehicleOption option4 = new VehicleOption("Gearbox",new Gearbox());
		VehicleOption option5 = new VehicleOption("Color",new Color());
		VehicleOption option6 = new VehicleOption("Wheels",new Wheels());
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);
		options.add(option6);
		VehicleModel vehiclemodel = new VehicleModel("Vehicle Model A", new VehicleModelSpecification(options));
		try {
			order = new StandardVehicleOrder(holder,options, vehiclemodel);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoClearanceException e) {
			e.printStackTrace();
		} catch (UnsatisfiedRestrictionException e) {
			e.printStackTrace();
		}
	}
	
	public StandardVehicleOrder getOrder(){
		return this.order;
	}
}
