package control;

import businessmodel.scheduler.AssemblyTask;

public interface AssemblyLineController{
	
	public void finishTask(AssemblyTask task, int time);
	
}
