package control;

import ui.LoginView;
import ui.View;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.exceptions.NoClearanceException;

/**
 * This is the main class of the system.
 *
 * @author Team 10
 */
public class Main {

	/**
     * This is the main method that iitializes the entire system.
     *
	 * @param   args
     *                  If some arguments are supplied no initial data will be added.
     *                  Otherwise the system will contain some orders if you login with username wow and password wow.
   	 */
	public static void main(String[] args){
		VehicleManufacturingCompany vmc = new VehicleManufacturingCompany();

        if (args.length == 0)
            new InitialData().initialize(vmc);

        View view = new LoginView(vmc);
		view.display();
	}
}
