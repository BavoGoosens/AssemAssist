package control;

import businessmodel.Model;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.order.SingleTaskOrder;
import businessmodel.user.User;

/**
 * The handler for the SingleTaskOrders.
 * 
 * @author Team 10
 *
 */
public class SingleTaskOrderHandler implements SingleTaskOrderController {


	private VehicleManufacturingCompany cmc;

	/**
	 * Constructor with the given VehicleManufacturingCompany
	 * @param cmc
	 */
	public SingleTaskOrderHandler(Model cmc) {
		this.cmc = (VehicleManufacturingCompany) cmc;
	}

	@Override
	public void placeSingleTaskOrder(User user, SingleTaskOrder order) {
		this.cmc.placeOrder(order);
	}

}
