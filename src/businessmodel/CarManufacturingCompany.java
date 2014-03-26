package businessmodel;

import java.util.ArrayList;

import control.Controller;

public class CarManufacturingCompany {

	/**
	 * A variable that holds an user management.
	 */
	private ArrayList<User> users = new ArrayList<User>();

	/**
	 * A variable that holds an order manager.
	 */
	private OrderManager ordermanager;

	/**
	 * A variable that holds an controller.
	 */
	private Controller controller;

	/**
	 * A constructor for a car manufacturing company.
	 * 
	 * @param 	control
	 * 			The controller that controls this car manufacturing company.
	 */
	public CarManufacturingCompany(OrderManager ordermanager, ArrayList<User> users){
		this.setController();
		this.setUsers(users);
		this.setOrderManager(ordermanager);
	}
	

	public User getUser(String username) {
		for(User user: this.getUsers())
			if (user.getFirstname().equals(username))
				return user;
		throw new IllegalArgumentException("Username doesn't exist");
	}

	public ArrayList<Order> getCompletedOrders(User user) {
		try{
			return ordermanager.getCompletedOrders(user);
		}catch (NullPointerException e){
			return null;		
		}
	}

	public ArrayList<Order> getPendingOrders(User user) {
		try {
			return this.getOrdermanager().getPendingOrders(user);
		}catch (NullPointerException e){
			return null;
		}
	}
	
	public ArrayList<CarModel> getAvailableCarModels(User currentuser) {
		if (this.canPlaceOrder(currentuser)){
			return this.getOrderManager().getCarmodels();
		}
		return null;
	}
	
	public boolean canPlaceOrder(User currentUser) {
		return currentUser.canPlaceOrder();
	}
	public boolean canPerformAssemblyTask(User currentUser) {
		return currentUser.canPerfomAssemblyTask();
	}
	public boolean canAdvanceAssemblyLine(User currentUser) {
		return currentUser.canAdvanceAssemblyLine();
	}
	
	public void advanceAssemblyLine(int time) {
		this.getOrderManager().getProductionScheduler().advance(time);
	}

	public OrderManager getOrderManager(){
		return this.ordermanager;
	}
	
	public void placeOrder(Order order){
		this.getOrdermanager().placeOrder(order);
	}

	private ArrayList<User> getUsers() {
		return this.users;
	}

	private OrderManager getOrdermanager() {
		return ordermanager;
	}

	private Controller getController() {
		return controller;
	}

	/**
	 * A method to set the controller of this class to the given controller.
	 * 
	 * @param 	controller
	 * 			the new controller of this car manufacturing company.
	 */
	private void setController(Controller controller) {
		this.controller = new Controller(this);
	}

	/**
	 * A method to set the user manager of this class to the given user manager.
	 * 
	 * @param 	usermanager
	 * 			the new user manager of this car manufacturing company.
	 */
	private void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	/**
	 * A method to set the order manager of this class to the given order manager.
	 * 
	 * @param 	ordermanager
	 * 			the new order manager of this car manufacturing company.
	 */
	private void setOrderManager(OrderManager ordermanager) {
		this.ordermanager = ordermanager;
	}
}
