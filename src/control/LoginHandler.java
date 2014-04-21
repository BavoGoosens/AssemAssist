package control;

import ui.LoginView;
import ui.View;
import businessmodel.CarManufacturingCompany;

public class LoginHandler implements LoginController{
	
	private CarManufacturingCompany cmc;
	
	private View view;

	public LoginHandler(CarManufacturingCompany cmc) {
		this.cmc = cmc;
		View view = new LoginView(this, this.cmc);
		this.view = view;
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

	@Override
	public void help() {
		// TODO Auto-generated method stub
		
	}
	
	

}
