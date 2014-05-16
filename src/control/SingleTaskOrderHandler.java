package control;

import businessmodel.Model;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.order.SingleTaskOrder;
import businessmodel.user.User;

public class SingleTaskOrderHandler implements SingleTaskOrderController {


	private VehicleManufacturingCompany cmc;

	public SingleTaskOrderHandler(Model cmc) {
		this.cmc = (VehicleManufacturingCompany) cmc;
	}

	@Override
	public void placeSingleTaskOrder(User user, SingleTaskOrder order) {
		this.cmc.placeOrder(order);
	}

}
