package control;

import businessmodel.VehicleManufacturingCompany;
import businessmodel.Model;
import businessmodel.order.SingleTaskOrder;

public class SingleTaskOrderHandler implements SingleTaskOrderController {


	private VehicleManufacturingCompany cmc;

	public SingleTaskOrderHandler(Model cmc) {
		this.cmc = (VehicleManufacturingCompany) cmc;
	}

	@Override
	public void placeSingleTaskOrder(SingleTaskOrder order) {
		this.cmc.placeOrder(order);
	}

}
