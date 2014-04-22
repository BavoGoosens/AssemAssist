package control;

import businessmodel.AssemblyTask;
import businessmodel.WorkPost;

public interface AssemblyLineController{
	
	public void finishTask(AssemblyTask task, int time);
	
}
