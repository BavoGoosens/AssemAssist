package control;

import ui.ManagerView;
import ui.View;
import businessmodel.CarManufacturingCompany;
import businessmodel.user.User;

public class ManagerHandler implements ManagerController {

	private User active_user;
	
	private CarManufacturingCompany cmc;
	
	private View view;
	
	public ManagerHandler(CarManufacturingCompany cmc, User use) {
		this.cmc = cmc;
		this.active_user = use;
		View view = new ManagerView(this, this.cmc);
		this.view = view;
		this.view.displayHelp();
		this.view.display();
	}

	@Override
	public void quit() {
		new LoginHandler(this.cmc);
	}

	@Override
	public void cancel() {
		this.view.displayHelp();
		this.view.display();
	}

	@Override
	public void help() {
		this.view.displayHelp();
	}

	@Override
	public void checkStatistics() {
		new StatisticsHandler(this.cmc, this.active_user);
	}

	@Override
	public void changeAlgorithm() {
		new SchedulingHandler(this.cmc, this.active_user);
	}

}
