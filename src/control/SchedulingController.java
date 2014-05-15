package control;

import java.util.ArrayList;

import businessmodel.category.VehicleOption;

public interface SchedulingController {

	public void selectAlgorithm(String algo, ArrayList<VehicleOption> options);
	
}
