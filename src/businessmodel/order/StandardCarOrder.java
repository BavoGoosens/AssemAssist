package businessmodel.order;

import java.util.ArrayList;

import org.joda.time.Period;

import businessmodel.Car;
import businessmodel.CarModel;
import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.user.User;

public class StandardCarOrder extends Order {
	
	private Car car;
	private CarModel carModel;
	
	public StandardCarOrder(User user, ArrayList<CarOption> options, CarModel model)
			throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		super(user);
		Car car = new Car(options);
		model.getCarModelSpecification().checkRestrictions(car);
		this.setCar(car);
		this.setCarModel(model);
	}
	
	public Car getCar() {
		return this.car;
	}
	
	public CarModel getCarModel() {
		return this.carModel;
	}
	
	public Period getStandardTimeToFinish() {
		return this.getCarModel().getStandardTimeToFinish();
	}
	
	private void setCar(Car car) throws IllegalArgumentException {
		if (car == null) throw new IllegalArgumentException("Bad car!");
		this.car = car;
	}
	
	private void setCarModel(CarModel model) throws IllegalArgumentException {
		if (model == null) throw new IllegalArgumentException("Bad car model!");
		this.carModel = model;
	}
	
	public ArrayList<CarOption> getOptions() {
		return this.getCar().getOptionsClone();
	}
}
