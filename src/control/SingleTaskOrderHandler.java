package control;

import org.joda.time.DateTime;

import businessmodel.CarManufacturingCompany;
import businessmodel.Model;
import businessmodel.order.SingleTaskOrder;
import businessmodel.user.User;

public class SingleTaskOrderHandler implements SingleTaskOrderController {


	private CarManufacturingCompany cmc;

	public SingleTaskOrderHandler(Model cmc) {
		this.cmc = (CarManufacturingCompany) cmc;
	}

	@Override
	public void placeSingleTaskOrder(SingleTaskOrder order) {
		this.cmc.placeOrder(order);
	}

}
