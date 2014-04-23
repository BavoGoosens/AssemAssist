package control;

import businessmodel.CarManufacturingCompany;
import businessmodel.Model;
import businessmodel.user.User;

public class SchedulingHandler implements SchedulingController{

	private User active_user;

	private CarManufacturingCompany cmc;

	public SchedulingHandler(Model cmc, User active_user) {
		this.cmc = (CarManufacturingCompany) cmc;
		this.active_user = active_user;
	}

	@Override
	public void selectAlgorithm(String algo, String[] args) {
		// TODO Auto-generated method stub

	}

}
