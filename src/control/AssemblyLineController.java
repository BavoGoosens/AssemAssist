package control;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;

public interface AssemblyLineController{
	
	public void finishTask(AssemblyTask task, int time);

    public void changeOperationalStatus(AssemblyLine assemblyLine);
	
}
