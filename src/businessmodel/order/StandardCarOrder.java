package businessmodel.order;

import java.util.ArrayList;

import businessmodel.Car;
import businessmodel.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.user.User;

public class StandardCarOrder extends Order {
	
	public StandardCarOrder(User user, ArrayList<CarOption> options)
			throws IllegalArgumentException, NoClearanceException {
		super(user, options);
	}
}
