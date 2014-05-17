package control;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.user.User;

public interface AssemblyLineController{
	
	public void finishTask(User user, AssemblyTask task, int time) throws NoClearanceException;

    public void changeOperationalStatus(User user, AssemblyLine assemblyLine, String status) throws NoClearanceException;
	
}
