package control;


import businessmodel.order.SingleTaskOrder;
import businessmodel.user.User;

public interface SingleTaskOrderController {
	
	public void placeSingleTaskOrder(User user, SingleTaskOrder order);

}
