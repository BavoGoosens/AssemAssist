package control;

import ui.LoginView;
import ui.View;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.exceptions.NoClearanceException;

/**
 * @author Team 10
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws NoClearanceException 
	 */
	public static void main(String[] args) throws NoClearanceException {
		
		VehicleManufacturingCompany vmc = new VehicleManufacturingCompany();
		new InitialData().initialize(vmc);
		
		View view = new LoginView(vmc);
		view.display();
		
		
	}
}
