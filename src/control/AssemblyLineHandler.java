package control;

import businessmodel.Model;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.user.User;

public class AssemblyLineHandler implements AssemblyLineController{

	private VehicleManufacturingCompany cmc;

	public AssemblyLineHandler (Model cmc) {
		this.cmc = (VehicleManufacturingCompany) cmc;
	}

	@Override
	public void finishTask(User user, AssemblyTask task, int time) {
		this.cmc.finishTask(task, time);
	}

    @Override
    public void changeOperationalStatus(User user, AssemblyLine assemblyLine, String status) {
        // TODO: Maken ajaaa

    }

}
