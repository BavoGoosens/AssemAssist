package control;
import java.util.ArrayList;
import java.util.Date;

import ui.LoginView;
import ui.UserInterFace;
import ui.View;
import businessmodel.Action;
import businessmodel.AssemblyTask;
import businessmodel.CarManufacturingCompany;
import businessmodel.CarModel;
import businessmodel.Catalog;
import businessmodel.OrderManager;
import businessmodel.ProductionScheduler;
import businessmodel.WorkPost;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsupportedMethodException;
import businessmodel.order.Order;
import businessmodel.user.User;

/**
 * This class is the main controller instance. 
 * It handles the communication between the user interface and the business logic layer and responds to user events.
 * 
 * @author Team 10
 *
 */
public abstract class Controller {

	private View ui;

	private CarManufacturingCompany cmc; 

	protected Controller(CarManufacturingCompany cmc){
		this.cmc = cmc;
	}
	
	protected View getView(){
		return this.ui;
	}
	
	protected void setView(View view){
		this.ui = view;
	}
	
	protected CarManufacturingCompany getCarManufacturingCompany(){
		return this.cmc;
	}

	public static void run(CarManufacturingCompany cmc){
		Controller control = new LoginHandler(cmc);
	}
	
	public void login(String uname, String password){
		throw new UnsupportedMethodException();
	}

	public abstract void quit();

	public abstract void cancel();

	public void check(Order or){
		throw new UnsupportedMethodException();
	}

	public void startNewOrder(){
		throw new UnsupportedMethodException();
	}

	public void select(WorkPost wp){
		throw new UnsupportedMethodException();
	}

	public void AssemblyLineStatus(){
		throw new UnsupportedMethodException();
	}

	public void displayStatistics(){
		throw new UnsupportedMethodException();
	}

	public void changeAlgorithm(){
		throw new UnsupportedMethodException();
	}

	public void pendingOrders(User user){
		throw new UnsupportedMethodException();
	}

	public void completedOrders(User user){		
		throw new UnsupportedMethodException();
	}

	public void getWorkPosts(){
		throw new UnsupportedMethodException();
	}

	public void availableTasks(){
		throw new UnsupportedMethodException();
	}

}
