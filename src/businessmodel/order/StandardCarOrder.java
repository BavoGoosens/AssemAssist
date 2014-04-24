package businessmodel.order;

import java.util.ArrayList;

import org.joda.time.Period;

import businessmodel.Car;
import businessmodel.CarModel;
import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.user.User;

/**
 * A class representing a standard car order.
 * This class extends an abstract order.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class StandardCarOrder extends Order {
	
	/**
	 * The car of the order.
	 */
	private Car car;
	/**
	 * The car model of the order.
	 */
	private CarModel carModel;
	
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
	public StandardCarOrder(User user, ArrayList<CarOption> options, CarModel model)
			throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		super(user);
		Car car = new Car(options);
		model.getCarModelSpecification().checkRestrictions(car);
		this.setCar(car);
		this.setCarModel(model);
	}
	
	/**
	 * Returns the car of that is ordered.
	 * 
	 * @return The car of the order.
	 */
	public Car getCar() {
		return this.car;
	}
	
	/**
	 * Returns the car model that is ordered.
	 * 
	 * @return The car model that is ordered.
	 */
	public CarModel getCarModel() {
		return this.carModel;
	}
	
	/**
	 * Returns the standard time to finish the order.
	 * 
	 * @return The standard time needed to finish the order of the specific model.
	 */
	public Period getStandardTimeToFinish() {
		return this.getCarModel().getStandardTimeToFinish();
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
	private void setCar(Car car) throws IllegalArgumentException {
		if (car == null) throw new IllegalArgumentException("Bad car!");
		this.car = car;
	}
	
	/**
	 * Sets the car model of the order to the given car model.
	 * 
	 * @param 	model
	 * 			The car model that is ordered.
	 * @throws 	IllegalArgumentException
	 * 			| If the car model is equal to 'null'
	 * 			| model == null
	 */
	private void setCarModel(CarModel model) throws IllegalArgumentException {
		if (model == null) throw new IllegalArgumentException("Bad car model!");
		this.carModel = model;
	}
	
	/**
	 * Returns the options of the car.
	 * 
	 * @return	The options of the car that is ordered.
	 */
	public ArrayList<CarOption> getOptions() {
		return this.getCar().getOptionsClone();
	}
}
