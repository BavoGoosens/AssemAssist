package control;

import businessmodel.AssemblyTask;
import businessmodel.CarManufacturingCompany;
import businessmodel.Model;
import businessmodel.user.User;

public class AssemblyLineHandler implements AssemblyLineController{

	private CarManufacturingCompany cmc;

	public AssemblyLineHandler (Model cmc) {
		this.cmc = (CarManufacturingCompany) cmc;
	}

	@Override
	public void finishTask(AssemblyTask task, int time) {
		this.cmc.finishTask(task, time);
	}

}
