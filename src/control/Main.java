package control;

import ui.LoginView;
import ui.View;
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
		View view = new LoginView(cmc);
		view.display();
	}
}
