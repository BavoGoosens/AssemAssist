import ui.UserInterFace;
import businessmodel.CarModel;
import businessmodel.Catalogus;
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

	private Catalogus ca = new Catalogus();

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
		this.orderOverview(current_user);
		String help = "to place a new order enter: order <CR> "
				+ "\n to quit enter: quit <CR> "
				+ "\n to view your order overview enter: overview <CR> \n >"
				+ "to see the list of available commands enter: help <CR>";
		this.ui.displayString(help);
		while (true) {
			String response = ui.getInput();
			if (response.equals("quit")){
				// go back to login screen
				break;
			} else if (response.equals("order")){
				String catalog = this.ca.getAvailableCarModels();
				ui.displayString(catalog + "\n please select the car model you wish to order by entering the apropriate number: \n >");
				response = ui.getInput();
				if (response.equals("cancel")){
					// display the overview and wait for input
					this.orderOverview(current_user);
					continue;
				} else if (response.matches("\\d")){
					int res = Integer.parseInt(response);
					CarModel cm = this.ca.getCarModel(res);
					
				}
			} else if (response.equals("help")) {
				this.ui.displayString(help);
			} else {
				this.orderOverview(current_user);
			}
		}
		this.run();
	}

	private void orderOverview(User user){
		String finished_orders = this.ps.getCompletedOrders(user);
		String future_orders = this.ps.getPendingOrders(user);
		ui.displayString("Your completed orders: \n \n "+ finished_orders);
		ui.displayString("Your pending orders: \n \n" + future_orders);
	}

}
