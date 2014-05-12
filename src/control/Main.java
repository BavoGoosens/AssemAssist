package control;

import ui.LoginView;
import ui.View;
import businessmodel.VehicleManufacturingCompany;

/**
 * @author Team 10
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VehicleManufacturingCompany cmc = new VehicleManufacturingCompany();
		View view = new LoginView(cmc);
		view.display();
	}
}
