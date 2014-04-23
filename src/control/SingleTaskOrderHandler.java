package control;

import org.joda.time.DateTime;

import businessmodel.CarManufacturingCompany;
import businessmodel.Model;
import businessmodel.order.SingleTaskOrder;
import businessmodel.user.User;

public class SingleTaskOrderHandler implements SingleTaskOrderController {

	private User active_user;

	private CarManufacturingCompany cmc;

	public SingleTaskOrderHandler(Model cmc, User use) {
		this.cmc = (CarManufacturingCompany) cmc;
		this.active_user = use;
	}

	@Override
	public void placeSingleTaskOrder(SingleTaskOrder order) {
		this.cmc.placeOrder(order);
	}

}
