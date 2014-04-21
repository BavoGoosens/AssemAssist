package control;

import businessmodel.AssemblyTask;
import businessmodel.WorkPost;

public interface AssemblyLineController extends Controller {
	
	public void select(WorkPost post);
	
	public void select(AssemblyTask task);
	
	public void assemblyLineStatus();
	
	public void finishTask(AssemblyTask task, int time);
	
}
