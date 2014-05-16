package control;

import java.util.ArrayList;

import businessmodel.category.VehicleOption;
import businessmodel.user.User;

public interface SchedulingController {

	public void selectAlgorithm(User user, String algo, ArrayList<VehicleOption> options);
	
}
