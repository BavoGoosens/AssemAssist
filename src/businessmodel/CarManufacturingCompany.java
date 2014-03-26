package businessmodel;

import java.util.ArrayList;

import exceptions.IllegalNumberException;
import exceptions.NoClearanceException;

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
	 * A constructor for a car manufacturing company.
	 * 
	 */
	public CarManufacturingCompany(OrderManager ordermanager, ArrayList<User> users) throws IllegalArgumentException {
		this.setUsers(users);
		this.setOrderManager(ordermanager);
	}
	

	public User getUser(String username) throws IllegalArgumentException {
		for(User user: this.getUsers())
			if (user.getFirstname().equals(username))
				return user;
		throw new IllegalArgumentException("Username doesn't exist!");
	}

	public ArrayList<Order> getCompletedOrders(User user) throws IllegalArgumentException, NoClearanceException {
		if (user == null) throw new IllegalArgumentException("Bad user!");
		if (!user.canPlaceOrder()) throw new NoClearanceException(user);
		return ordermanager.getCompletedOrders(user);
	}

	public ArrayList<Order> getPendingOrders(User user) throws IllegalArgumentException, NoClearanceException {
		if (user == null) throw new IllegalArgumentException("Bad user!");
		if (!user.canPlaceOrder()) throw new NoClearanceException(user);
		return this.getOrderManager().getPendingOrders(user);
	}
	
	public ArrayList<CarModel> getAvailableCarModels(User currentuser) throws IllegalArgumentException,
																			NoClearanceException {
		if (currentuser == null) throw new IllegalArgumentException("Bad user!");
		if (!currentuser.canPlaceOrder()) throw new NoClearanceException(currentuser);
		return this.getOrderManager().getCarmodels();
	}
	
	public void advanceAssemblyLine(int time) throws IllegalNumberException {
		if (time < 0) throw new IllegalNumberException(time, "Bad time!");
		this.getOrderManager().getProductionScheduler().advance(time);
	}

	public OrderManager getOrderManager(){
		return this.ordermanager;
	}
	
	public void placeOrder(Order order) throws IllegalArgumentException {
		if (order == null) throw new IllegalArgumentException("Bad order!");
		this.getOrderManager().placeOrder(order);
	}

	private ArrayList<User> getUsers() {
		return this.users;
	}


	/**
	 * A method to set the user manager of this class to the given user manager.
	 * 
	 * @param 	usermanager
	 * 			the new user manager of this car manufacturing company.
	 */
	private void setUsers(ArrayList<User> users) throws IllegalArgumentException {
		if (users == null) throw new IllegalArgumentException("Bad list of users!");
		this.users = users;
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


}
