package control;


import businessmodel.order.SingleTaskOrder;
import businessmodel.user.User;

/**
 * The controller for a SingleTaskOrder.
 * 
 * @author Team 10
 *
 */
public interface SingleTaskOrderController {
	
	/**
	 * Place a SingleTaskOrder.
	 * @param user
	 * @param order
	 */
	public void placeSingleTaskOrder(User user, SingleTaskOrder order);

}
