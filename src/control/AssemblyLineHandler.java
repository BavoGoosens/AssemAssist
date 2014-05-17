package control;

import businessmodel.Model;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.user.User;

public class AssemblyLineHandler implements AssemblyLineController{

	private VehicleManufacturingCompany cmc;

	public AssemblyLineHandler (Model cmc) {
		this.cmc = (VehicleManufacturingCompany) cmc;
	}

	@Override
	public void finishTask(User user, AssemblyTask task, int time) throws NoClearanceException {
		if (user.canPerfomAssemblyTask())
            this.cmc.finishTask(task, time);
        else throw new NoClearanceException();
	}

    @Override
    public void changeOperationalStatus(User user, AssemblyLine assemblyLine, String status) throws NoClearanceException {
        if (user.canChangeOperationalStatus())
            this.cmc.changeAssemblyLineStatus(assemblyLine, status);
        else
            throw new NoClearanceException();
    }

}
