package businessmodel;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.statistics.OrderStatistics;
import businessmodel.statistics.VehicleStatistics;
import businessmodel.user.User;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Interface for a model.
 *
 * @author SWOP team 10
 */
public interface Model {

    /**
     * Returns an iterator over the pending orders of the given user.
     *
     * @param user The given user.
     * @throws IllegalArgumentException
     * @throws NoClearanceException     | If the user has no clearance to place orders.
     * @return The pending orders the given user has placed.
     */
    public Iterator<Order> getPendingOrders(User user) throws IllegalArgumentException, NoClearanceException;

    /**
     * Returns an iterator over the completed orders of the given user.
     *
     * @param user The given user.
     * @throws IllegalArgumentException
     * @throws NoClearanceException     | If the user has no clearance to place orders.
     * @return The completed orders the given user has placed.
     */
    public Iterator<Order> getCompletedOrders(User user) throws IllegalArgumentException, NoClearanceException;

    /**
     * Returns an iterator over the work posts for the given user.
     *
     * @param user The given user.
     * @return The work posts for the given user.
     */
    public Iterator<WorkPost> getWorkPosts(User user, AssemblyLine assemblyLine) throws IllegalArgumentException, NoClearanceException;

    /**
     * Returns an iterator over the car models for the given user.
     *
     * @param user The given user.
     * @return The car models for the given user.
     */
    public Iterator<VehicleModel> getVehicleModels(User user) throws IllegalArgumentException, NoClearanceException;

    /**
     * Returns an iterator over the available assembly tasks for the given user.
     *
     * @param user The given user
     * @return The available assembly tasks for the given user.
     */
    public Iterator<AssemblyTask> getAvailableTasks(User user) throws IllegalArgumentException, NoClearanceException;

    /**
     * Returns an iterator over the scheduling algorithms for the given user.
     *
     * @param user The given user.
     * @return The scheduling algorithms for the given user.
     */
    public Iterator<String> getSchedulingAlgorithms(User user) throws IllegalArgumentException, NoClearanceException;

    /**
     * Returns the user with the given user name and password. If that exists.
     *
     * @param username The user name.
     * @param password The password.
     * @return The user in the system with the given user name and password.
     */
    public User login(String username, String password);

    /**
     * Registers a new user.
     *
     * @param user The user that needs to be registered.
     */
    public void register(User user);

    /**
     * Returns the car statistics of the model.
     *
     * @return The car statistics of the model.
     */
    public VehicleStatistics getVehicleStatistics(User user) throws IllegalArgumentException, NoClearanceException;

    /**
     * Returns the order statistics of the model.
     *
     * @return The order statistics of the model.
     */
    public OrderStatistics getOrderStatistics(User user) throws IllegalArgumentException, NoClearanceException;

    /**
     * Returns an iterator over the pending tasks at a given work post.
     *
     * @param wp The given work post.
     * @return The pending tasks at the given work post.
     */
    public Iterator<AssemblyTask> getPendingTasks(User user, WorkPost wp) throws IllegalArgumentException, NoClearanceException;

    /**
     * Returns an iterator over the finished tasks at a given work post.
     *
     * @param wp The given work post.
     * @return The finished tasks at a given work post.
     */
    public Iterator<AssemblyTask> getFinishedTasks(User user, WorkPost wp) throws IllegalArgumentException, NoClearanceException;

    /**
     * Returns an iterator over the unscheduled car options of the model.
     *
     * @return The unscheduled car options of the model.
     */
    public Iterator<ArrayList<VehicleOption>> getUnscheduledVehicleOptions(User user) throws IllegalArgumentException, NoClearanceException;

    /**
     * Returns the system time.
     *
     * @return The system time.
     */
    public DateTime getSystemTime();

    /**
     * Get the AssemblyLines with the given User.
     *
     * @param user
     * @return
     * @throws NoClearanceException
     */
    public Iterator<AssemblyLine> getAssemblyLines(User user) throws NoClearanceException;

    /**
     * Get the current system wide algorithm.
     *
     * @param user
     * @return
     * @throws NoClearanceException
     */
    public String getCurrentSystemWideAlgorithm(User user) throws NoClearanceException;

    /**
     * Get current sate of the AssemblyLine.
     *
     * @param user
     * @param selectedAssemblyLine
     * @return
     * @throws NoClearanceException
     */
    public String getCurrentAssemblyLineStatus(User user, AssemblyLine selectedAssemblyLine) throws NoClearanceException;

    /**
     * Get the available states of the AssemblyLine.
     *
     * @param user
     * @param selectedAssemblyLine
     * @return
     * @throws NoClearanceException
     */
    public Iterator<String> getAvailableAssemblyLineStatus(User user, AssemblyLine selectedAssemblyLine) throws NoClearanceException;
}
