package control;


import businessmodel.VehicleManufacturingCompany;
import businessmodel.Model;
import businessmodel.order.StandardCarOrder;

public class StandardOrderHandler implements StandardOrderController{
	
	private VehicleManufacturingCompany cmc;
	
		
	public StandardOrderHandler(Model model) {
		this.cmc = (VehicleManufacturingCompany) model;
	}

	@Override
	public void placeOrder(StandardCarOrder or) {
		this.cmc.placeOrder(or);
	}

}
