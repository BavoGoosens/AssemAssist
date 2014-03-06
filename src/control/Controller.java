package control;
import java.util.ArrayList;

import ui.UserInterFace;
import businessmodel.CarManufacturingCompany;
import businessmodel.CarModel;
import businessmodel.Order;
import businessmodel.User;

/**
 * This class is the main controller instance. 
 * It handles the communication between the user interface and the business logic layer and responds to user events.
 * 
 * @author BatCave
 *
 */
public class Controller {

	private UserInterFace ui;

	private CarManufacturingCompany cmc; 

	public Controller(){
		this.cmc = new CarManufacturingCompany(this);
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

}
