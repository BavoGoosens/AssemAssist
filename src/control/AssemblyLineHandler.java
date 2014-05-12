package control;

import businessmodel.AssemblyTask;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.Model;

public class AssemblyLineHandler implements AssemblyLineController{

	private VehicleManufacturingCompany cmc;

	public AssemblyLineHandler (Model cmc) {
		this.cmc = (VehicleManufacturingCompany) cmc;
	}

	@Override
	public void finishTask(AssemblyTask task, int time) {
		this.cmc.finishTask(task, time);
	}

}
