package businessmodel.order;

import java.util.ArrayList;

import org.joda.time.DateTime;

import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.user.User;

public class SingleTaskOrder extends Order {
	
	private DateTime userEndDate;
	private ArrayList<CarOption> options;
	
	public SingleTaskOrder(User user, ArrayList<CarOption> options, DateTime userEndDate) throws IllegalArgumentException, NoClearanceException, UnsatisfiedRestrictionException {
		super(user);
		this.setOptions(options);
		this.setUserEndDate(userEndDate);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<CarOption> getOptions() {
		return (ArrayList<CarOption>) this.options.clone();
	}
	
	public DateTime getUserEndDate() {
		return this.userEndDate;
	}
	
	@SuppressWarnings("unchecked")
	private void setOptions(ArrayList<CarOption> options) {
		if (options == null) throw new IllegalArgumentException("Bad list of options!");
		this.options = (ArrayList<CarOption>) options.clone();
	}
	
	private void setUserEndDate(DateTime userEndDate) throws IllegalArgumentException {
		if (userEndDate == null) throw new IllegalArgumentException("Bad user end date!");
		this.userEndDate = userEndDate;
	}
}
