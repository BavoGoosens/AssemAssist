package businessmodel.util;

import java.util.ArrayList;
import org.junit.Test;

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

public class TestStandardVehicleOrder {

	private StandardVehicleOrder order;
	private GarageHolder holder;

	public TestStandardVehicleOrder(GarageHolder holder, String name){
		this.setHolder(holder);
		makeOrder(holder,name);
	}

	private void setHolder(GarageHolder holder) {
		this.holder = holder;
	}

	@Test
	private void makeOrder(GarageHolder holder, String name){
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
		VehicleModel vehiclemodel = new VehicleModel(name, new VehicleModelSpecification(options));
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
