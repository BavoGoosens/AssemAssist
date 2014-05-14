package businessmodel.assemblyline;

import java.util.ArrayList;

import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleModelSpecification;
import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.GarageHolder;

public class TestOrder {

	private StandardVehicleOrder order;
	
	public TestOrder(){
		makeOrder();
	}
	
	// TODO aanmaken van juist order.
	private void makeOrder(){
		GarageHolder holder = new GarageHolder("Sander","Geijsen","Test");
		ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
		VehicleModel vehiclemodel = new VehicleModel("Test", new VehicleModelSpecification(new ArrayList<VehicleOption>()));
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
	
	protected StandardVehicleOrder getOrder(){
		return this.order;
	}
}
