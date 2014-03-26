package businessmodel;

import java.util.ArrayList;

import control.Controller;

public class CarManufacturingCompany {

	/**
	 * A variable that holds an user management.
	 */
	private UserManagement usermanagement = new UserManagement();

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
	public CarManufacturingCompany(OrderManager ordermanager, UserManagement userm){
		this.setController();
		this.setUserManagement(userm);
		this.setOrderManager(ordermanager);
	}
	
	/**
	 * A method to check if a given user name and password belong to a user of the system.
	 * 
	 * @param 	username
	 * 			the user name of the user that wants to login
	 * @param 	password
	 * 			the password of the user that wants to login.
	 * @return	true if the combination of the user name and the password matches a user in the system.
	 */
	public boolean login(String username, String password) {
		return this.usermanagement.authenticate(username, password);		
	}

	public User getUser(String username) {
		return this.getUsermanagement().getUser(username);
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
	public boolean canPlaceOrder(User currentuser) {
		return this.getUsermanagement().canPlaceOrder(currentuser);
	}
	public ArrayList<CarModel> getAvailableCarModels(User currentuser) {
		if (this.canPlaceOrder(currentuser)){
			return this.getOrderManager().getCarmodels();
		}
		return null;
	}
	public boolean canPerformAssemblyTask(User currentuser) {
		return this.getUsermanagement().isMechanic(currentuser);
	}
	public boolean canAdvanceAssemblyLine(User currentuser) {
		return this.getUsermanagement().canControlAssemblyLine(currentuser);
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

	private UserManagement getUsermanagement() {
		return usermanagement;
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
	private void setUserManagement(UserManagement usermanager) {
		this.usermanagement = usermanager;
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
