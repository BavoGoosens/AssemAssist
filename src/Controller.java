import ui.UserInterFace;
import businessmodel.Inventory;
import businessmodel.ProductionSchedule;
import businessmodel.User;
import businessmodel.UserManagement;

/**
 * This class is the main controller instance. 
 * It handles all the communication between the user interface and the business logic layer.
 * @author BatCave
 *
 */
public class Controller {
	
	private UserInterFace ui = new UserInterFace();
	
	private UserManagement um = new UserManagement();
	
	private ProductionSchedule ps = new ProductionSchedule();
	
	private Inventory in = new Inventory();
	
	public void run(){
		User current_user = null;
		boolean auth = false;
		while (auth == false) {
			String[] login = ui.loginPrompt();
			if (! um.isUserInSystem(login[0]) ) {
				ui.displayString("We could not find you in the System \n \n");
			} else {
				auth = um.authenticate(login[0],login[1]);
				if (auth) current_user = um.getUser(login[0]); 
			}
		}
		if (um.canPlaceOrder(current_user)){
			orderNewCar(current_user);
		}else if( um.isMechanic(current_user)){
			performAssemblyTask(current_user);
		}else{
			advanceAssemblyLine();
		}
		
	}

	private void advanceAssemblyLine() {
		// TODO Auto-generated method stub
		
	}

	private void performAssemblyTask(User current_user) {
		// TODO Auto-generated method stub
		
	}

	private void orderNewCar(User current_user) {
		// TODO Auto-generated method stub
		
	}

}
