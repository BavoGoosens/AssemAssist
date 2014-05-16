package control;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.user.User;

public interface AssemblyLineController{
	
	public void finishTask(User user, AssemblyTask task, int time);

    public void changeOperationalStatus(User user, AssemblyLine assemblyLine);
	
}
