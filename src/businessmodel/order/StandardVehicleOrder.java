package businessmodel.order;

import java.util.ArrayList;

import businessmodel.category.Vehicle;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.user.User;

/**
 * A class representing a standard car order.
 * This class extends an abstract order.
 * 
 * @author SWOP team 10
 *
 */
public class StandardVehicleOrder extends Order {
	
	/**
	 * The car of the order.
	 */
	private Vehicle vehicle;
	
	/**
	 * Creates a new standard car order with a given user, a set of options and a car model.
	 * 
	 * @param	user
	 * 			The user that placed the order.
	 * @param 	options
	 * 			The options that are ordered.
	 * @param 	model
	 * 			The model that is ordered.
	 * @throws 	IllegalArgumentException
	 * @throws 	NoClearanceException
	 * @throws 	UnsatisfiedRestrictionException
	 */
	public StandardVehicleOrder(User user, ArrayList<VehicleOption> options, VehicleModel model)
			throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		super(user, model);
		Vehicle car = new Vehicle(options);
		model.getVehicleModelSpecification().checkRestrictions(car);
		if (model.getName().contains("Truck")) car.addTruckOptions();
		this.setVehicle(car);
	}
	
	/**
	 * Returns the car of that is ordered.
	 * 
	 * @return The car of the order.
	 */
	public Vehicle getVehicle() {
		return this.vehicle;
	}
	
	/**
	 * Sets the car of the order to the given car.
	 * 
	 * @param 	car
	 * 			The car that is ordered.
	 * @throws 	IllegalArgumentException
	 * 			| If the car is equal to 'null'
	 * 			| car == null
	 */
	private void setVehicle(Vehicle car) throws IllegalArgumentException {
		if (car == null) throw new IllegalArgumentException("Bad car!");
		this.vehicle = car;
	}
	
	
	/**
	 * Returns the options of the car.
	 * 
	 * @return	The options of the car that is ordered.
	 */
	public ArrayList<VehicleOption> getOptions() {
		return this.getVehicle().getOptionsClone();
	}
	
	@Override
	public String toString() {
		return super.toString() + " Order: "+ this.getVehicleModel().getName() + " with options: " + this.getOptions().toString();
	}
}
