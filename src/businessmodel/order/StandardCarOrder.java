package businessmodel.order;

import java.util.ArrayList;

import businessmodel.Car;
import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.user.User;

public class StandardCarOrder extends Order {
	
	public StandardCarOrder(User user, ArrayList<CarOption> options)
			throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		super(user, options,null);
	}
}
