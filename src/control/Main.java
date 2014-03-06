package control;
import ui.UserInterFace;
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
		// TODO Auto-generated method stub
		CarManufacturingCompany cmc = new CarManufacturingCompany();
		UserInterFace ui = new UserInterFace();
		Controller ctrl = new Controller(cmc, ui);
		;
	}
}
