package control;

import ui.LoginView;
import businessmodel.CarManufacturingCompany;
import businessmodel.user.CustomShopManager;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.user.Mechanic;
import businessmodel.user.User;

public class LoginHandler implements LoginController{
	
	private CarManufacturingCompany cmc;
	
	private LoginView view;

	public LoginHandler(CarManufacturingCompany cmc) {
		this.cmc = cmc;
		LoginView view = new LoginView(this, this.cmc);
		this.view = view;
		view.display();
	}

	@Override
	public void quit() {
		LoginView view = new LoginView(this, this.cmc);
		this.view = view;
		view.display();
	}

	@Override
	public void cancel() {
		this.view.displayHelp();
		this.view.display();
	}

	@Override
	public void login(String uname, String password) {
		User use = this.cmc.getUser(uname);
		if (use.equals(null))
			this.view.register();
		else{
			if (use instanceof GarageHolder){
				new StandardOrderHandler(this.cmc, use);				
			} else if (use instanceof Mechanic){
				new AssemblyLineHandler(this.cmc, use);	
			} else if (use instanceof Manager){
				new ManagerHandler(this.cmc, use);	
			} else if (use instanceof CustomShopManager){
				new SingleTaskOrderHandler(this.cmc, use);
			} else {
				this.view.error();
			}
		}
	}

	@Override
	public void help() {
		this.view.displayHelp();
	}
	
	@Override
	public void register(String fname, String lname, String username, String type){
		if(type.equalsIgnoreCase("garageholder")){
			GarageHolder jef = new GarageHolder(fname, lname, username);
			this.cmc.register(jef);
			this.login(username, "");
		} else if (type.equalsIgnoreCase("mechanic")){
			Mechanic mich = new Mechanic(fname, lname, username);
			this.cmc.register(mich);
			this.login(username, "");
		} else if (type.equalsIgnoreCase("manager")){
			Manager manny = new Manager(fname, lname, username);
			this.cmc.register(manny);
			this.login(username, "");
		} else if (type.equalsIgnoreCase("customshopmanager")){
			CustomShopManager cas = new CustomShopManager(fname, lname, username);
			this.cmc.register(cas);
			this.login(username, "");
		} else
			this.view.error();
	}

}
