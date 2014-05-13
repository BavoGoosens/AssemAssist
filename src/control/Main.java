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
		
		VehicleManufacturingCompany vmc = new VehicleManufacturingCompany();
		int nbOrders = 10;
		new InitialData(nbOrders).initialize(vmc);
		
		View view = new LoginView(vmc);
		view.display();
		
		
	}
}
