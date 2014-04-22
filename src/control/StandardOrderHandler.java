package control;


import businessmodel.Model;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.User;

public class StandardOrderHandler implements StandardOrderController{
	
	private Model cmc;
	
	private User user;
		
	public StandardOrderHandler(Model model, User use) {
		this.cmc = model;
		this.user = use;
	}

	@Override
	public void placeOrder(StandardCarOrder or) {
		// TODO Auto-generated method stub
		
	}

}
