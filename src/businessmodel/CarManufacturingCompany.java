package businessmodel;

import java.util.ArrayList;

import control.Controller;

public class CarManufacturingCompany {

	private UserManagement um = new UserManagement();

	private Inventory inv;

	private OrderManager om;

	private Controller control;

	public CarManufacturingCompany(Controller control){
		this.control = control;
		this.um = new UserManagement();
		this.om = new OrderManager();
	}
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
		this.om.addOrder(order);
	}
}
