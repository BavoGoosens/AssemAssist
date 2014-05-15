package businessmodel;

import java.util.ArrayList;
import java.util.Iterator;

import org.joda.time.DateTime;

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
import businessmodel.user.CustomShopManager;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.user.Mechanic;
import businessmodel.user.User;


public class VehicleManufacturingCompany implements Model {

	/**
	 * List of the users.
	 */
	private ArrayList<User> users = new ArrayList<User>();
	
	/**
	 * The order manager.
	 */
	private OrderManager ordermanager;
	
	/**
	 * The task manager.
	 */
	private TaskManager taskmanager;
	
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
	 * @throws	IllegalArgumentException
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
		return ordermanager.getCompletedOrders(user).iterator();
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
		return assemblyLine.getWorkPosts().iterator();
	}

	@Override
	public Iterator<VehicleModel> getVehicleModels(User user) {
		return this.catalog.getAvailaleModelsClone().iterator();
	}

	@Override
	public Iterator<String> getSchedulingAlgorithms(User user) {
		ArrayList<String> algos = new ArrayList<String>(); 
		algos.add("FIFO"); 
		algos.add("SpecificationBatch"); 
		return algos.iterator();
	}

	@Override
	public User login(String username, String password) {
		return this.getUser(username);
	}

	@Override
	public VehicleStatistics getVehicleStatistics() {
		return this.statisticsmanager.getVehicleStatistics();
	}

	@Override
	public OrderStatistics getOrderStatistics() {
		return this.statisticsmanager.getOrderStatistics();
	}

    @Override
    // TODO public of?
	public Iterator<AssemblyTask> getPendingTasks(WorkPost wp) {
		return wp.getPendingTasksIterator();
	}

	@Override
	// TODO public of?
	public Iterator<AssemblyTask> getFinishedTasks(WorkPost wp) {
		return wp.getFinishedTasksIterator();
	}

	@Override
	public Iterator<AssemblyTask> getAvailableTasks(User user) {
		return this.taskmanager.getSingleTaskOrders()
	}

	@Override
	public Iterator<VehicleOption> getUnscheduledVehicleOptions(int num) {
		return this.getOrderManager().getMainScheduler().getUnscheduledVehicleOptions(num);
	}

	@Override
	public String getCurrentSystemWideAlgo() {
		return this.ordermanager.getMainScheduler().currentSystemWideAlgorithmDescription();
	}

	
	/**
	 * Returns the current time of the system.
	 * @return The current time of the system.
	 */
	public DateTime getSystemTime(AssemblyLine assemblyLine){
		return new DateTime(assemblyLine.getAssemblyLineScheduler().getCurrentTime());
	}

    @Override
    public Iterator<AssemblyLine> getAssemblyLines() {
        return null;
    }

    /**
	 * Completes an assembly task with the given time.
	 * 
	 * @param 	task
	 * 			The task that needs to be completed.
	 * @param 	time
	 * 			The time that was needed to complete the assembly task.
	 */
	public void finishTask(AssemblyTask task, int time) {
		task.completeAssemblytask(time);
	}

	/**
	 * Changes the scheduling algorithm to the given algorithm.
	 * @param 	algo
	 * 			The new algorithm
	 * @param 	option
	 */
	public void changeSystemWideAlgorithm(String algo, VehicleOption option) {
		this.getOrderManager().getMainScheduler().changeSystemWideAlgorithm(algo, option);
	}

	/**
	 * Places an order.
	 * @param 	order
	 * 			The order that needs to be placed.
	 * @throws 	IllegalArgumentException
	 */
	public void placeOrder(Order order) throws IllegalArgumentException {
		this.getOrderManager().placeOrder(order);
	}
	
	/**
	 * Returns the users of the car manufacturing company.
	 * @return The users of the car manufacturing company.
	 */
	private ArrayList<User> getUsers() {
		return this.users;
	}

	/**
	 * Returns the order manager of the car manufacturing company.
	 * @return The order manager of the car manufacturing company.
	 */
	public OrderManager getOrderManager(){
		return this.ordermanager;
	}
	
	/**
	 * A method to set the order manager of this class to the given order manager.
	 * 
	 * @param 	ordermanager
	 * 			the new order manager of this car manufacturing company.
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
		for(User user: this.getUsers())
			if (user.getUsername().equals(username))
				return user;
		throw new IllegalArgumentException("Username doesn't exist!");
	}

}
