package businessmodel.order;

import java.util.ArrayList;

import org.joda.time.DateTime;

import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.user.User;

public class SingleTaskOrder extends Order {
	
	public SingleTaskOrder(User user, ArrayList<CarOption> options, DateTime user_end_date) throws IllegalArgumentException, NoClearanceException {
		super(user,options, user_end_date);
	}
}
