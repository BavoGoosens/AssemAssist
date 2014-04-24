package control;


import businessmodel.CarManufacturingCompany;
import businessmodel.Model;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.User;

public class StandardOrderHandler implements StandardOrderController{
	
	private CarManufacturingCompany cmc;
	
		
	public StandardOrderHandler(Model model) {
		this.cmc = (CarManufacturingCompany) model;
	}

	@Override
	public void placeOrder(StandardCarOrder or) {
		this.cmc.placeOrder(or);
	}

}
