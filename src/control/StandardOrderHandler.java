package control;

import businessmodel.CarManufacturingCompany;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.User;

public class StandardOrderHandler implements StandardOrderController{

	public StandardOrderHandler(CarManufacturingCompany cmc, User use) {
		this.user = use;
		this.cmc = cmc;
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void check(StandardCarOrder or) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startNewOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeOrder(StandardCarOrder or) {
		// TODO Auto-generated method stub
		
	}

}
