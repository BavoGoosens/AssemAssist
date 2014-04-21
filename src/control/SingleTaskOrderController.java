package control;

import org.joda.time.DateTime;

import businessmodel.order.SingleTaskOrder;

public interface SingleTaskOrderController extends Controller {
	
	public void placeSingleTaskOrder(SingleTaskOrder order, DateTime deadline);

}
