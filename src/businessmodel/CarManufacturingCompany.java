package businessmodel;

import java.util.ArrayList;

import control.Controller;

public class CarManufacturingCompany {

	/**
	 * A variable that holds an user management.
	 */
	private UserManagement um = new UserManagement();

	/**
	 * A variable that holds an inventory
	 */
	private Inventory inv;

	/**
	 * A variable that holds an order manager.
	 */
	private OrderManager om;

	/**
	 * A variable that holds an controller.
	 */
	private Controller control;

	/**
	 * A constructor for a car manufacturing company.
	 * 
	 * @param 	control
	 * 			The controller that controls this car manufacturing company.
	 */
	public CarManufacturingCompany(Controller control){
		this.control = control;
		this.um = new UserManagement();
		this.om = new OrderManager();
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
		return this.um.authenticate(username, password);		
	}

	public User getUser(String username) {
		return this.um.getUser(username);
	}

	public ArrayList<Order> getCompletedOrders(User user) {
		try{
			return om.getCompletedOrders(user);
		}catch (NullPointerException e){
			return null;		
		}
	}

	public ArrayList<Order> getPendingOrders(User user) {
		try {
			return om.getPendingOrders(user);
		}catch (NullPointerException e){
			return null;
		}
	}
	public boolean canPlaceOrder(User currentuser) {
		return this.um.canPlaceOrder(currentuser);
	}
	public ArrayList<CarModel> getAvailableCarModels(User currentuser) {
		if (this.canPlaceOrder(currentuser)){
			return this.om.getCarmodels();
		}
		return null;
	}
	public boolean canPerformAssemblyTask(User currentuser) {
		return this.um.isMechanic(currentuser);
	}
	public boolean canAdvanceAssemblyLine(User currentuser) {
		return this.um.canControlAssemblyLine(currentuser);
	}
	
	public void advanceAssemblyLine(int time) {
		this.om.getProductionScheduler().advance(time);
	}

	public OrderManager getOrderManager(){
		return this.om;
	}
	
	public void placeOrder(Order order){
		this.om.placeOrder(order);
	}
}
