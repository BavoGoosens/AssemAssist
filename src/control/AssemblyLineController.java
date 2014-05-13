package control;

import businessmodel.assemblyline.AssemblyTask;

public interface AssemblyLineController{
	
	public void finishTask(AssemblyTask task, int time);
	
}
