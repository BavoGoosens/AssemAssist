package control;


import businessmodel.CarManufacturingCompany;
import businessmodel.Model;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.User;

public class StandardOrderHandler implements StandardOrderController{
	
	private CarManufacturingCompany cmc;
	
	private User user;
		
	public StandardOrderHandler(Model model, User use) {
		this.cmc = (CarManufacturingCompany) model;
		this.user = use;
	}

	@Override
	public void placeOrder(StandardCarOrder or) {
		this.cmc.placeOrder(or);
	}

}
