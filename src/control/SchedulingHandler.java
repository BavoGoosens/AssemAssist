package control;

import ui.SchedulingView;
import ui.View;
import businessmodel.CarManufacturingCompany;
import businessmodel.user.User;

public class SchedulingHandler implements SchedulingController{

	private User active_user;

	private CarManufacturingCompany cmc;

	private View view;

	public SchedulingHandler(CarManufacturingCompany cmc, User active_user) {
		this.cmc = cmc;
		this.active_user = active_user;
		View view = new SchedulingView(this, this.cmc);
		this.view = view;
		this.view.displayHelp();
		this.view.display();
	}

	@Override
	public void selectAlgorithm(String algo, String[] args) {
		// TODO Auto-generated method stub

	}

}
