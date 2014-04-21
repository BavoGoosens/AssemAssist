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
public interface Controller {

	public abstract void quit();

	public abstract void cancel();

	public abstract void help();

}
