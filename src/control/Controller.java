package control;

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
