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
	 * @param   user
     *                  The user who wants to finish the task
     *
	 * @param   task
     *                  The task you want to finish
     *
	 * @param   time
     *                  The time it took to finish this order.
     *
	 * @throws  NoClearanceException
     *                  If the user is not allowed to finish this task this exception will be thrown.
	 */
	public void finishTask(User user, AssemblyTask task, int time) throws NoClearanceException;

	/**
	 * Change the state of the AssemblyLine.
     *
	 * @param   user
     *                  The user who wants to change the operational status of an assembly line.
     *
	 * @param   assemblyLine
     *                  The assembly line the status needs to be changed of.
     *
	 * @param   status
     *                  The state that needs to be transitioned to.
     *
	 * @throws  NoClearanceException
     *                  If the user is not allowed to change the state this exception this exception is thrown
	 */
    public void changeOperationalStatus(User user, AssemblyLine assemblyLine, String status) throws NoClearanceException;
	
}
