package businessmodel;

import java.util.ArrayList;
import java.util.Iterator;

import businessmodel.exceptions.IllegalNumberException;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.user.User;


public class CarManufacturingCompany implements Model{

	/**
	 * A variable that holds an user management.
	 */
	private ArrayList<User> users = new ArrayList<User>();

	/**
	 * A variable that holds an order manager.
	 */
	private OrderManager ordermanager;

	private Catalog catalog;

	/**
	 * A constructor for a car manufacturing company.
	 * 
	 */
	public CarManufacturingCompany() throws IllegalArgumentException {
		this.catalog = new Catalog();
		this.setOrderManager(new OrderManager(this.catalog.getAvailaleModelsClone()));
	}
	

	public User getUser(String username) throws IllegalArgumentException {
		for(User user: this.getUsers())
			if (user.getUsername().equals(username))
				return user;
		throw new IllegalArgumentException("Username doesn't exist!");
	}

	public Iterator<Order> getCompletedOrders(User user) throws IllegalArgumentException, NoClearanceException {
		return ordermanager.getCompletedOrders(user).iterator();
	}

	public Iterator<Order> getPendingOrders(User user) throws IllegalArgumentException, NoClearanceException {
		return this.getOrderManager().getPendingOrders(user).iterator();
	}
	
	public ArrayList<CarModel> getAvailableCarModels(User currentuser) throws IllegalArgumentException,
																			NoClearanceException {
		return this.getOrderManager().getCarmodels();
	}
	
	public void advanceAssemblyLine(int time) throws IllegalNumberException {
		this.getOrderManager().getScheduler().advance(time);
	}

	public OrderManager getOrderManager(){
		return this.ordermanager;
	}
	
	public void placeOrder(Order order) throws IllegalArgumentException {
		this.getOrderManager().placeOrder(order);
	}

	public void completeAssemBlyTask(AssemblyTask assemblytask, int time){
		assemblytask.completeAssemblytask(time);
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

	public void register(User user) {
		this.users.add(user);
	}


	@Override
	public Iterator<WorkPost> getWorkPosts(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Iterator<CarModel> getCarModels(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Iterator<AssemblyTask> getAvailableTasks(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Iterator<AssemblyTask> getPendingTasks(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Iterator<AssemblyTask> getCompletedTasks(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Iterator<String> getSchedulingAlgorithms(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
