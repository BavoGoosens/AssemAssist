package control;

import java.util.ArrayList;

import businessmodel.Model;
import businessmodel.VehicleManufacturingCompany;
import businessmodel.category.VehicleOption;

public class SchedulingHandler implements SchedulingController{


	private VehicleManufacturingCompany cmc;

	public SchedulingHandler(Model cmc) {
		this.cmc = (VehicleManufacturingCompany) cmc;
	}

	@Override
	public void selectAlgorithm(String algo, ArrayList<VehicleOption> options) {
		this.cmc.changeSystemWideAlgorithm(algo, options);
	}

}
