package control;

import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.User;

public interface StandardOrderController {
 
	public void placeOrder(User user, StandardVehicleOrder or);
	
}
