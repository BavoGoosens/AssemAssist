package businessmodel;

import java.util.Iterator;

import businessmodel.scheduler.AssemblyLine;
import businessmodel.scheduler.AssemblyTask;
import businessmodel.scheduler.WorkPost;
import org.joda.time.DateTime;

import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.observer.Observer;
import businessmodel.order.Order;
import businessmodel.statistics.VehicleStatistics;
import businessmodel.statistics.OrderStatistics;
import businessmodel.user.User;

/**
 * Interface for a model.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public interface Model {
	
	/**
	 * Returns an iterator over the pending orders of the given user.
	 * 
	 * @param 	user
	 * 			The given user.
	 * @return	The pending orders the given user has placed.
	 * @throws 	IllegalArgumentException
	 * @throws 	NoClearanceException
	 * 			| If the user has no clearance to place orders.
	 */
	public Iterator<Order> getPendingOrders(User user) throws IllegalArgumentException, NoClearanceException;
	
	/**
	 * Returns an iterator over the completed orders of the given user.
	 * @param 	user
	 * 			The given user.
	 * @return	The completed orders the given user has placed.
	 * @throws 	IllegalArgumentException
	 * @throws 	NoClearanceException
	 * 			| If the user has no clearance to place orders.
	 */
	public Iterator<Order> getCompletedOrders(User user) throws IllegalArgumentException, NoClearanceException;
	
	/**
	 * Returns an iterator over the work posts for the given user.
	 * 
	 * @param 	user
	 * 			The given user.
	 * @return	The work posts for the given user.
	 */
	public Iterator<WorkPost> getWorkPosts(User user);
	
	/**
	 * Returns an iterator over the car models for the given user.
	 * 
	 * @param 	user
	 * 			The given user.
	 * @return	The car models for the given user.
	 */
	public Iterator<VehicleModel> getVehicleModels(User user);
	
	/**
	 * Returns an iterator over the available assembly tasks for the given user.
	 * 
	 * @param 	user
	 * 			The given user
	 * @return	The available assembly tasks for the given user.
	 */
	public Iterator<AssemblyTask> getAvailableTasks(User user);
	
	/**
	 * Returns an iterator over the scheduling algorithms for the given user.
	 * 
	 * @param 	user
	 * 			The given user.
	 * @return	The scheduling algorithms for the given user.
	 */
	public Iterator<String> getSchedulingAlgorithms(User user);

	/**
	 * Returns the user with the given user name and password. If that exists.
	 * 
	 * @param 	username
	 * 			The user name.
	 * @param 	password
	 * 			The password.
	 * @return	The user in the system with the given user name and password.
	 */
	public User login(String username, String password);
	
	/**
	 * Registers a new user.
	 * 
	 * @param 	user
	 * 			The user that needs to be registered.
	 */
	public void register(User user);
	
	/**
	 * Registers the given observer to the assembly line.
	 * 
	 * @param 	observer
	 * 			The observer that needs to be subscribed to the assembly line.
	 * @return	The assembly line the observer is subscribed to.
	 */
	public AssemblyLine registerAssemblyLineObserver(Observer observer);

	/**
	 * Returns the car statistics of the model.
	 * @return The car statistics of the model.
	 */
	public VehicleStatistics getVehicleStatistics();

	/**
	 * Returns the order statistics of the model.
	 * @return The order statistics of the model.
	 */
	public OrderStatistics getOrderStatistics();

	/**
	 * Returns an iterator over the work posts of the model.
	 * @return The work posts of the model.
	 */
	public Iterator<WorkPost> getWorkPosts();

	/**
	 * Returns an iterator over the pending tasks at a given work post.
	 * 
	 * @param 	wp
	 * 			The given work post.
	 * @return	The pending tasks at the given work post.
	 */
	public Iterator<AssemblyTask> getPendingTasks(WorkPost wp);

	/**
	 * Returns an iterator over the finished tasks at a given work post.
	 * 
	 * @param 	wp
	 * 			The given work post.
	 * @return	The finished tasks at a given work post.
	 */
	public Iterator<AssemblyTask> getFinishedTasks(WorkPost wp);
	
	/**
	 * Returns an iterator over the unscheduled car options of the model.
	 * 
	 * @return The unscheduled car options of the model.
	 */
	public Iterator<VehicleOption> getUnscheduledVehicleOptions(int num);

	/**
	 * Returns a string representation of the current algorithm in the model.
	 * 
	 * @return	The current algorithm in the model.
	 */
	public String getCurrentAlgo();

	/**
	 * Returns the system time.
	 * @return	The system time.
	 */
	public DateTime getSystemTime();

}
