package businessmodel.order;

import java.util.ArrayList;

import businessmodel.Car;
import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.user.User;

public class StandardCarOrder extends Order {
	
	/**
	 * A variable that contains the specific car being ordered.
	 */
	private Car car;

	public StandardCarOrder(User user, ArrayList<CarOption> options)
			throws IllegalArgumentException, NoClearanceException {
		super(user);
		this.car = new Car(options);
	}
	
	/**
	 * This method returns the new car for this order.
	 * 
	 * @return 	Car
	 * 			this.car
	 */
	@Override
	public Car getCar() {
		return car;
	}

}
