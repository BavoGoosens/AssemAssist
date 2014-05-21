package control;

import java.util.ArrayList;

import businessmodel.category.VehicleOption;
import businessmodel.user.User;

/**
 * The Controller for the scheduling.
 * 
 * @author Team 10
 *
 */
public interface SchedulingController {

	/**
	 * Select the given algorithm, with the given User, with the given VehicleOptions.
	 * @param user
	 * @param algo
	 * @param options
	 */
	public void selectAlgorithm(User user, String algo, ArrayList<VehicleOption> options);
	
}
