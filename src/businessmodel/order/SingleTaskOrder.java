package businessmodel.order;

import businessmodel.AssemblyTask;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.user.User;

public class SingleTaskOrder extends Order {
	
	private AssemblyTask task;

	public SingleTaskOrder(User user, AssemblyTask assem) throws IllegalArgumentException,
			NoClearanceException {
		super(user);
		this.task = assem;
	}

}
