package control;


import businessmodel.Model;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.order.StandardVehicleOrder;
import businessmodel.user.User;

/**
 * The handler for the StandardOrder.
 * 
 * @author Team 10
 *
 */
public class StandardOrderHandler implements StandardOrderController{
	
	private VehicleManufacturingCompany cmc;
	
	/**
	 * Constructor with the given VehicleManufacturingCompany.
	 * @param model
	 */
	public StandardOrderHandler(Model model) {
		this.cmc = (VehicleManufacturingCompany) model;
	}

	@Override
	public void placeOrder(User user, StandardVehicleOrder or) {
		this.cmc.placeOrder(or);
	}

}
