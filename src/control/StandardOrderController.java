package control;

import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.User;

/**
 * The controller for the StandardOrder.
 * 
 * @author Team 10
 *
 */
public interface StandardOrderController {
 
	/**
	 * Place StandardOrder.
	 * @param user
	 * @param or
	 */
	public void placeOrder(User user, StandardVehicleOrder or);
	
}
