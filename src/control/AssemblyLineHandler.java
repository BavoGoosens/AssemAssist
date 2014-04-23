package control;

import businessmodel.AssemblyTask;
import businessmodel.CarManufacturingCompany;
import businessmodel.Model;
import businessmodel.WorkPost;
import businessmodel.user.User;

public class AssemblyLineHandler implements AssemblyLineController{

	private CarManufacturingCompany cmc;

	private User user;

	public AssemblyLineHandler (Model cmc, User user) {
		this.cmc = (CarManufacturingCompany) cmc;
		this.user = user;
	}

	@Override
	public void finishTask(AssemblyTask task, int time) {
		this.cmc.finishTask(task, time, this.user);
	}

}
