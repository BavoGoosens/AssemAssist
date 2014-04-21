package control;

import businessmodel.order.StandardCarOrder;

public interface StandardOrderController extends Controller {
 
	public void check(StandardCarOrder or);
	
	public void startNewOrder();
	
	public void placeOrder(StandardCarOrder or);
	
}
