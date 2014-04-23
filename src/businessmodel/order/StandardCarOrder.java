package businessmodel.order;

import java.util.ArrayList;

import businessmodel.Car;
import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.user.User;

public class StandardCarOrder extends Order {
	
	private Car car;
	
	public StandardCarOrder(User user, ArrayList<CarOption> options)
			throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		super(user);
		Car car = new Car(options);
		this.setCar(car);
	}
	
	public Car getCar() {
		return this.car;
	}
	
	private void setCar(Car car) throws IllegalArgumentException {
		if (car == null) throw new IllegalArgumentException("Bad car!");
		this.car = car;
	}
	
	public ArrayList<CarOption> getOptions() {
		return this.getCar().getOptionsClone();
	}
}
