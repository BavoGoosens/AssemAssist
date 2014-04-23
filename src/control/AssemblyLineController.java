package control;

import businessmodel.AssemblyTask;

public interface AssemblyLineController{
	
	public void finishTask(AssemblyTask task, int time);
	
}
