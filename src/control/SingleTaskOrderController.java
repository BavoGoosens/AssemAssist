package control;

import org.joda.time.DateTime;

import businessmodel.order.SingleTaskOrder;

public interface SingleTaskOrderController {
	
	public void placeSingleTaskOrder(SingleTaskOrder order, DateTime deadline);

}
