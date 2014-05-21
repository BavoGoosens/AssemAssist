package control;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.user.User;

/**
 * The controller for the AssemblyLine.
 * 
 * @author Team 10
 *
 */
public interface AssemblyLineController{
	
	/**
	 * Finish the given AssemblyTask, with the given User, with the given time.
	 * @param user
	 * @param task
	 * @param time
	 * @throws NoClearanceException
	 */
	public void finishTask(User user, AssemblyTask task, int time) throws NoClearanceException;

	/**
	 * Change the state of the AssemblyLine.
	 * @param user
	 * @param assemblyLine
	 * @param status
	 * @throws NoClearanceException
	 */
    public void changeOperationalStatus(User user, AssemblyLine assemblyLine, String status) throws NoClearanceException;
	
}
