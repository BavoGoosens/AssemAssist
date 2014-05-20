package businessmodel;

import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;
import businessmodel.category.VehicleModel;
import businessmodel.category.VehicleOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.statistics.OrderStatistics;
import businessmodel.statistics.StatisticsManager;
import businessmodel.statistics.VehicleStatistics;
import businessmodel.user.*;
import businessmodel.util.IteratorConverter;
import businessmodel.util.SafeIterator;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Iterator;


public class VehicleManufacturingCompany implements Model {

    /**
     * List of the users.
     */
    private ArrayList<User> users = new ArrayList<>();

    /**
     * The order manager.
     */
    private OrderManager ordermanager;

    /**
     * Statistics manager.
     */
    private StatisticsManager statisticsmanager;

    /**
     * The catalog where all the available car models are stored.
     */
    private Catalog catalog;

    /**
     * A constructor for a car manufacturing company.
     *
     * @throws IllegalArgumentException
     */
    public VehicleManufacturingCompany() throws IllegalArgumentException {
        this.catalog = new Catalog();
        this.setOrderManager(new OrderManager());
        this.statisticsmanager = new StatisticsManager(this.getOrderManager());
        // for ease of use
        this.users.add(new GarageHolder("wow", "wow", "wow"));
        this.users.add(new Mechanic("wow", "wow", "woww"));
        this.users.add(new Manager("wow", "wow", "wowww"));
        this.users.add(new CustomShopManager("wow", "wow", "wowwww"));
    }

    @Override
    public Iterator<Order> getCompletedOrders(User user) throws IllegalArgumentException, NoClearanceException {
        return this.getOrderManager().getCompletedOrders(user).iterator();
    }

    @Override
    public Iterator<Order> getPendingOrders(User user) throws IllegalArgumentException, NoClearanceException {
        return this.getOrderManager().getPendingOrders(user).iterator();
    }

    @Override
    public void register(User user) {
        this.users.add(user);
    }

    @Override
    public Iterator<WorkPost> getWorkPosts(User user, AssemblyLine assemblyLine) {
        return assemblyLine.getWorkPostsIterator();
    }

    @Override
    public Iterator<VehicleModel> getVehicleModels(User user) {
        return this.catalog.getAvailaleModelsClone().iterator();
    }

    @Override
    public Iterator<String> getSchedulingAlgorithms(User user) {
        ArrayList<String> algorithms = new ArrayList<>();
        algorithms.add("FIFO");
        algorithms.add("SpecificationBatch");
        return algorithms.iterator();
    }

    @Override
    public User login(String username, String password) {
        return this.getUser(username);
    }

    @Override
    public VehicleStatistics getVehicleStatistics(User user) throws NoClearanceException {
        if (user.canViewStatistics())
            return this.statisticsmanager.getVehicleStatistics();
        else
            throw new NoClearanceException();
    }

    @Override
    public OrderStatistics getOrderStatistics(User user) throws NoClearanceException {
        if (user.canViewStatistics())
            return this.statisticsmanager.getOrderStatistics();
        else
            throw new NoClearanceException();
    }

    @Override
    public Iterator<AssemblyTask> getPendingTasks(User user, WorkPost wp) throws NoClearanceException {
        if (user.canPerfomAssemblyTask())
            return wp.getPendingTasks();
        else
            throw new NoClearanceException();
    }

    @Override
    public Iterator<AssemblyTask> getFinishedTasks(User user, WorkPost wp) throws NoClearanceException {
        if (user.canPerfomAssemblyTask())
            return wp.getFinishedTasks();
        else
            throw new NoClearanceException();
    }

    @Override
    // TODO: safe maken
    // TODO: taskmanager weg en ophalen uit workposts.
    public Iterator<AssemblyTask> getAvailableTasks(User user) {
        IteratorConverter<WorkPost> converter = new IteratorConverter<>();
        ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
        
        for (AssemblyLine line : this.getOrderManager().getMainScheduler().getAssemblyLines()) 
            for (WorkPost post : converter.convert(line.getWorkPostsIterator())) 
            	for( AssemblyTask task : post.getResponsibleTasksClone())
            		if (task.canBeOrder())
            			tasks.add(task);
 
        return tasks.iterator();
    }

    @Override
    public Iterator<ArrayList<VehicleOption>> getUnscheduledVehicleOptions(User user) throws NoClearanceException {
        if (user.canChangeAlgorithm())
            return this.getOrderManager().getMainScheduler().getUnscheduledVehicleOptions();
        else
            throw new NoClearanceException();
    }

    /**
     * Returns the current time of the system.
     *
     * @return The current time of the system.
     */
    public DateTime getSystemTime() {
        return this.getOrderManager().getMainScheduler().getTime();
    }

    @Override
    public Iterator<AssemblyLine> getAssemblyLines(User user) throws NoClearanceException {
        if (user.canViewAssemblyLines()){
            SafeIterator<AssemblyLine> safe = new SafeIterator<>();
            safe.convertIterator(this.getOrderManager().getMainScheduler().getAssemblyLines().iterator());
            return safe;
        }else
            throw new NoClearanceException();
    }

    @Override
    public String getCurrentSystemWideAlgorithm(User user) throws NoClearanceException {
        if (user.canChangeAlgorithm())
            return this.getOrderManager().getMainScheduler().getAlgorithm();
        else
            throw new NoClearanceException();
    }

    public void changeAssemblyLineStatus(AssemblyLine assemblyLine, String status) {
        if (status.equalsIgnoreCase(assemblyLine.getBrokenState().toString()))
            assemblyLine.transitionToBroken();
        if (status.equalsIgnoreCase(assemblyLine.getMaintenanceState().toString()))
            assemblyLine.transitionToMaintenance();
        if (status.equalsIgnoreCase(assemblyLine.getOperationalState().toString()))
            assemblyLine.transitionToOperational();
    }

    @Override
    public String getCurrentAssemblyLineStatus(User user, AssemblyLine selectedAssemblyLine) throws NoClearanceException {
        if(user.canChangeOperationalStatus()){
            return selectedAssemblyLine.currentState();
        } else {
            throw new NoClearanceException();
        }
    }

    @Override
    public Iterator<String> getAvailableAssemblyLineStatus(User user, AssemblyLine selectedAssemblyLine) throws NoClearanceException {
        if (user.canChangeOperationalStatus()){
            return selectedAssemblyLine.getAllPossibleStates();
        } else {
            throw new NoClearanceException();
        }
    }

    /**
     * Completes an assembly task with the given time.
     *
     * @param task The task that needs to be completed.
     * @param time The time that was needed to complete the assembly task.
     */
    public void finishTask(AssemblyTask task, int time) {
        task.completeAssemblytask(time);
    }

    /**
     * Changes the scheduling algorithm to the given algorithm.
     *
     * @param algo    The new algorithm
     * @param options
     */
    public void changeSystemWideAlgorithm(String algo, ArrayList<VehicleOption> options) {
        this.getOrderManager().getMainScheduler().changeSystemWideAlgorithm(algo, options);
    }

    /**
     * Places an order.
     *
     * @param order The order that needs to be placed.
     * @throws IllegalArgumentException
     */
    public void placeOrder(Order order) throws IllegalArgumentException {
        this.getOrderManager().placeOrder(order);
    }

    /**
     * Returns the users of the car manufacturing company.
     *
     * @return The users of the car manufacturing company.
     */
    private ArrayList<User> getUsers() {
        return this.users;
    }

    /**
     * Returns the order manager of the car manufacturing company.
     *
     * @return The order manager of the car manufacturing company.
     */
    private OrderManager getOrderManager() {
        return this.ordermanager;
    }

    /**
     * A method to set the order manager of this class to the given order manager.
     *
     * @param ordermanager the new order manager of this car manufacturing company.
     */
    private void setOrderManager(OrderManager ordermanager) throws IllegalArgumentException {
        if (ordermanager == null) throw new IllegalArgumentException("Bad ordermanager!");
        this.ordermanager = ordermanager;
    }

    /**
     * Method to get the User given a username.
     *
     * @param username
     * @return
     * @throws IllegalArgumentException
     */
    private User getUser(String username) throws IllegalArgumentException {
        for (User user : this.getUsers())
            if (user.getUsername().equals(username))
                return user;
        throw new IllegalArgumentException("Username doesn't exist!");
    }

}
