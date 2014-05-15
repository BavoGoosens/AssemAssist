package control;


import businessmodel.Model;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.order.StandardVehicleOrder;

public class StandardOrderHandler implements StandardOrderController{
	
	private VehicleManufacturingCompany cmc;
	
		
	public StandardOrderHandler(Model model) {
		this.cmc = (VehicleManufacturingCompany) model;
	}

	@Override
	public void placeOrder(StandardVehicleOrder or) {
		this.cmc.placeOrder(or);
	}

}
