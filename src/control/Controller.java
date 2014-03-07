package control;
import java.util.ArrayList;
import java.util.Date;

import ui.UserInterFace;
import businessmodel.CarManufacturingCompany;
import businessmodel.CarModel;
import businessmodel.Inventory;
import businessmodel.Order;
import businessmodel.OrderManager;
import businessmodel.ProductionScheduler;
import businessmodel.User;
import businessmodel.UserManagement;

/**
 * This class is the main controller instance. 
 * It handles the communication between the user interface and the business logic layer and responds to user events.
 * 
 * @author Team 10
 *
 */
public class Controller {

	private UserInterFace ui;

	private CarManufacturingCompany cmc; 

	public Controller(UserManagement um, ProductionScheduler ps, OrderManager om, Inventory inv){
		this.cmc = new CarManufacturingCompany(this, um, ps, om, inv);
		this.ui = new UserInterFace(this);
	}
	
	public void run(){
		this.ui.login();
	}

	public boolean authenticate(String username, String password) {
		return this.cmc.login(username, password);
	}

	public User getUser(String username) {
		return cmc.getUser(username);
	}

	public boolean canPlaceOrder(User currentuser) {
		return this.cmc.canPlaceOrder(currentuser);
	}

	public ArrayList<Order> getCompletedOrders(User currentuser) {
		return this.cmc.getCompletedOrders(currentuser);
	}

	public ArrayList<Order> getPendingOrders(User currentuser) {
		return this.cmc.getPendingOrders(currentuser);
	}

	public ArrayList<CarModel> getAvailableCarModels(User currentuser) {
		return this.cmc.getAvailableCarModels(currentuser);		
	}

	public boolean canPerformAssemblyTask(User currentuser) {
		return this.cmc.canPerformAssemblyTask(currentuser);
	}

	public boolean canAdvanceAssemblyLine(User currentuser) {
		return this.cmc.canAdvanceAssemblyLine(currentuser);
	}

	public void placeOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	public Date getCompletionTimeEstimate() {
		// TODO Auto-generated method stub
		return new Date();
	}

}
