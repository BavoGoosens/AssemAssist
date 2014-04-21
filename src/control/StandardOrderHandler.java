package control;

import ui.GarageHolderView;
import ui.OrderView;
import ui.View;
import businessmodel.CarManufacturingCompany;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.User;

public class StandardOrderHandler implements StandardOrderController{
	
	private CarManufacturingCompany cmc;
	
	private View view;
	
	public StandardOrderHandler(CarManufacturingCompany cmc, User use) {
		this.cmc = cmc;
		GarageHolderView view = new GarageHolderView(this, this.cmc, use);
		this.view = view;
		this.view.display();
	}

	@Override
	public void quit() {
		new LoginHandler(this.cmc);
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void help() {
		this.view.displayHelp();
	}

	@Override
	public void check(StandardCarOrder or) {
		View view = new OrderView(this, this.cmc);
		this.view = view;
	}

	@Override
	public void startNewOrder() {
		
	}

	@Override
	public void placeOrder(StandardCarOrder or) {
		// TODO Auto-generated method stub
		
	}

}
