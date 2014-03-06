import ui.UserInterFace;
import businessmodel.CarManufacturingCompany;
import businessmodel.CarModel;
import businessmodel.Catalogus;
import businessmodel.GarageHolder;
import businessmodel.Inventory;
import businessmodel.Mechanic;
import businessmodel.ProductionSchedule;
import businessmodel.User;
import businessmodel.UserManagement;

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

	public Controller(CarManufacturingCompany cmc, UserInterFace ui){
		this.cmc = cmc;
		this.ui = ui;
	}
	
	public void run(){
		String[] res;
		boolean login = false;
		while (login == false) {
			res = this.ui.loginPrompt();
			login = cmc.login(res[0], res[1]);
		}
		User cu = cmc.getUser(res[0]);
		if (cu instanceof GarageHolder){
			this.orderNewCar(cu);
		} else if( cu instanceof Mechanic){
			this.performAssemblyTask(cu);
		} else {
			this.advanceAssemblyLine();
		}
	}


	private void advanceAssemblyLine() {
		// TODO Auto-generated method stub

	}

	private void performAssemblyTask(User current_user) {
		// TODO Auto-generated method stub

	}

	private void orderNewCar(User current_user) {
		while (true) {
			String response = ui.displayOrderOverview(cmc.getCompletedOrders(current_user), cmc.getPendingOrders(current_user));
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
				this.ui.displayOrderHelp();
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
