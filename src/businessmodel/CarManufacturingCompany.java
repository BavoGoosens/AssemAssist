package businessmodel;

import java.util.ArrayList;
import java.util.Iterator;

import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.observer.Observer;
import businessmodel.order.Order;
import businessmodel.statistics.CarStatistics;
import businessmodel.statistics.OrderStatistics;
import businessmodel.statistics.StatisticsManager;
import businessmodel.user.CustomShopManager;
import businessmodel.user.GarageHolder;
import businessmodel.user.Manager;
import businessmodel.user.Mechanic;
import businessmodel.user.User;


public class CarManufacturingCompany implements Model{

	private ArrayList<User> users = new ArrayList<User>();
	
	private OrderManager ordermanager;
	
	private TaskManager taskmanager;
	
	private StatisticsManager statisticsmanager;
	
	private Catalog catalog;

	/**
	 * A constructor for a car manufacturing company.
	 * 
	 */
	public CarManufacturingCompany() throws IllegalArgumentException {
		this.catalog = new Catalog();
		this.setOrderManager(new OrderManager(this.catalog.getAvailaleModelsClone()));
		this.taskmanager = new TaskManager(this.getOrderManager().getScheduler().getAssemblyline().getWorkPosts());
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
	public Iterator<WorkPost> getWorkPosts(User user) {
		return this.getOrderManager().getScheduler().getAssemblyline().getWorkPosts().iterator();
	}

	@Override
	public Iterator<CarModel> getCarModels(User user) {
		return this.catalog.getAvailaleModelsClone().iterator();
	}

	@Override
	//TODO nakijken
	public Iterator<String> getSchedulingAlgorithms(User user) {
		ArrayList<String> algos = new ArrayList<String>(); 
		algos.add("FIFO"); 
		algos.add("Specification Batch"); 
		return algos.iterator();
	}

	@Override
	public User login(String username, String password) {
		return this.getUser(username);
	}

	@Override
	public AssemblyLine registerAssemblyLineObserver(Observer observer) {
		AssemblyLine line = this.getOrderManager().getScheduler().getAssemblyline();
		line.subscribeObserver(observer);
		return line;
	}

	@Override
	public CarStatistics getCarStatistics() {
		return this.statisticsmanager.getCarStatistics();
	}

	@Override
	public OrderStatistics getOrderStatistics() {
		return this.getOrderStatistics();
	}

	@Override
	public Iterator<WorkPost> getWorkPosts() {
		return this.getOrderManager().getScheduler().getAssemblyline().getWorkPosts().iterator();
	}

	@Override
	public Iterator<AssemblyTask> getPendingTasks(WorkPost wp) {
		return wp.getPendingTasks().iterator();
	}

	@Override
	public Iterator<AssemblyTask> getFinishedTasks(WorkPost wp) {
		return wp.getFinishedTasks().iterator();
	}

	@Override
	public Iterator<AssemblyTask> getAvailableTasks(User user) {
		return this.taskmanager.getSingleTaskOrders().iterator();
	}

	@Override
	public Iterator<CarOption> getUnscheduledCarOptions() {
		return this.getOrderManager().getScheduler().getUnscheduledCarOptions().iterator();
	}

	@Override
	public String getCurrentAlgo() {
		return this.ordermanager.getScheduler().currentAlgoDescription();
	}

	public OrderManager getOrderManager(){
		return this.ordermanager;
	}

	public void finishTask(AssemblyTask task, int time) {
		task.completeAssemblytask(time);
	}

	public void changeAlgorithm(String algo, CarOption option) {
		this.getOrderManager().getScheduler().changeAlgorithm(algo, option);
	}

	public void placeOrder(Order order) throws IllegalArgumentException {
		this.getOrderManager().placeOrder(order);
	}

	private ArrayList<User> getUsers() {
		return this.users;
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

	private User getUser(String username) throws IllegalArgumentException {
		for(User user: this.getUsers())
			if (user.getUsername().equals(username))
				return user;
		throw new IllegalArgumentException("Username doesn't exist!");
	}

}
