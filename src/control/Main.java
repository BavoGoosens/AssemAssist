package control;

import ui.LoginView;
import businessmodel.CarManufacturingCompany;

/**
 * @author Team 10
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CarManufacturingCompany cmc = new CarManufacturingCompany();
		LoginView view = new LoginView(cmc);
		view.display();
	}
}
