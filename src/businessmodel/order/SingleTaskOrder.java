package businessmodel.order;

import java.util.ArrayList;
import businessmodel.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.user.User;

public class SingleTaskOrder extends Order {
	
	public SingleTaskOrder(User user, ArrayList<CarOption> options) throws IllegalArgumentException, NoClearanceException {
		super(user,options);
	}
}
