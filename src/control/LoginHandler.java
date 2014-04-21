package control;

import ui.LoginView;
import ui.View;
import businessmodel.CarManufacturingCompany;

public class LoginHandler extends Controller{

	protected LoginHandler(CarManufacturingCompany cmc) {
		super(cmc);
		View view = new LoginView(this, super.getCarManufacturingCompany());
		super.setView(view);
		view.display();
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
	public void login(String uname, String password) {
		// TODO Auto-generated method stub

	}
	
	

}
