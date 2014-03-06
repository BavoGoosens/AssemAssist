package businessmodel;

import java.util.ArrayList;

import control.Controller;

public class CarManufacturingCompany {
	
	private UserManagement um = new UserManagement();
	
	private Inventory inv;
	
	private OrderManager om;
	
	private ProductionScheduler ps;
	
	private Controller control;
	
	public CarManufacturingCompany(Controller control){
		this.control = control;
	}
	public boolean login(String username, String password) {
		return this.um.authenticate(username, password);		
	}

	public User getUser(String username) {
		return this.um.getUser(username);
	}

	public ArrayList<Order> getCompletedOrders(User user) {
		return om.getCompletedOrders(user);
	}

	public ArrayList<Order> getPendingOrders(User user) {
		return om.getPendingOrders(user);
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
	
	

}
