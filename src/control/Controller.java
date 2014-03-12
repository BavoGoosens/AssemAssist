package control;
import java.util.ArrayList;
import java.util.Date;

import ui.UserInterFace;
import businessmodel.Action;
import businessmodel.AssemblyTask;
import businessmodel.CarManufacturingCompany;
import businessmodel.CarModel;
import businessmodel.Inventory;
import businessmodel.Order;
import businessmodel.OrderManager;
import businessmodel.ProductionScheduler;
import businessmodel.User;
import businessmodel.UserManagement;
import businessmodel.WorkPost;

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

	public boolean canPerformAssemblyTask(User currentuser) {
		return this.cmc.canPerformAssemblyTask(currentuser);
	}

	public boolean canAdvanceAssemblyLine(User currentuser) {
		return this.cmc.canAdvanceAssemblyLine(currentuser);
	}

	public void placeOrder(Order order) {
		this.cmc.placeOrder(order);
	}

	public Date getCompletionTimeEstimate() {
		return new Date();
	}

	public ArrayList<WorkPost> getWorkingStations() {
//		ArrayList<WorkPost> workPostList = new ArrayList<WorkPost>();
//		WorkPost w = new WorkPost("Car body post");
//		WorkPost w2 = new WorkPost("Drivetrain post");
//		WorkPost w3 = new WorkPost("Accessoires post");
//
//		ArrayList<Action> actions = new ArrayList<Action>();
//		actions.add(new Action("take paint"));
//		actions.add(new Action("paint"));
//		actions.add(new Action("get rid of paint"));
//		
//		ArrayList<Action> actions2 = new ArrayList<Action>();
//		actions2.add(new Action("get body"));
//		actions2.add(new Action("put body on"));
//		
//		ArrayList<Action> actions3 = new ArrayList<Action>();
//		actions3.add(new Action("get engine"));
//		actions3.add(new Action("install engine"));
//		
//		w.addPendingTask(new AssemblyTask("painting car blue", actions));
//		w.addPendingTask(new AssemblyTask("install body", actions2));
//		
//		w2.addPendingTask(new AssemblyTask("install engine", actions3));
//		
//		workPostList.add(w);
//		workPostList.add(w2);
//		workPostList.add(w3);
		return this.cmc.getOrderManager().getProductionScheduler().getAssemblyline().getWorkPosts();
	}

}
