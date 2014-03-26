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

	public Controller(CarManufacturingCompany cmc){
		this.cmc = cmc;
		this.ui = new UserInterFace(this);
	}

	public void run(){
		this.ui.login();
	}


	public User getUser(String username) {
		return cmc.getUser(username);
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


	public boolean canPlaceOrder(User currentuser) {
		return currentuser.canPlaceOrder();
	}

	public boolean canPerformAssemblyTask(User currentuser) {
		return currentuser.canPerfomAssemblyTask();
	}

	public boolean canAdvanceAssemblyLine(User currentuser) {
		return currentuser.canAdvanceAssemblyLine();
	}
	
	public boolean canOrderSingleTask(User currentuser) {
		return currentuser.canOrderSingleTask();
	}

	
	public void advanceAssemblyLine(int time) {
		this.cmc.advanceAssemblyLine(time);
	}

	public void placeOrder(Order order) {
		this.cmc.placeOrder(order);
	}

	public Date getCompletionTimeEstimate() {
		return new Date();
	}

	public ArrayList<WorkPost> getWorkingStations() {
		return this.cmc.getOrderManager().getProductionScheduler().getAssemblyline().getWorkPosts();
	}

	public boolean canAdvanceAssemblyLine() {
		return this.cmc.getOrderManager().getProductionScheduler().getAssemblyline().canAdvance();
	}

	public ArrayList<AssemblyTask> overview() {
		ArrayList<AssemblyTask> overviewList = new ArrayList<AssemblyTask>();
		for(WorkPost wp: this.getWorkingStations())
			for (AssemblyTask as : wp.getPendingTasks())
				overviewList.add(as);
		return overviewList;
		
		
	}
	
	public ArrayList<AssemblyTask> futureOverview() {
		return this.cmc.getOrderManager().getProductionScheduler().getFutureAssemblyTasks();
		
		
	}


}
