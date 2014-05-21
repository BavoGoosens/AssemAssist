package control;

import java.util.ArrayList;

import businessmodel.Model;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.category.VehicleOption;
import businessmodel.user.User;

/**
 * The handler for the scheduling ( changing the algorithm ).
 * 
 * @author Team 10
 *
 */
public class SchedulingHandler implements SchedulingController{


	private VehicleManufacturingCompany cmc;

	/**
	 * Constructor with given VehicleManufacturingCompany.
	 * @param cmc
	 */
	public SchedulingHandler(Model cmc) {
		this.cmc = (VehicleManufacturingCompany) cmc;
	}

	@Override
	public void selectAlgorithm(User user, String algo, ArrayList<VehicleOption> options) {
		this.cmc.changeSystemWideAlgorithm(algo, options);
	}

}
