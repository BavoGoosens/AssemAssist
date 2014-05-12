package control;

import businessmodel.VehicleManufacturingCompany;
import businessmodel.Model;
import businessmodel.category.VehicleOption;

public class SchedulingHandler implements SchedulingController{


	private VehicleManufacturingCompany cmc;

	public SchedulingHandler(Model cmc) {
		this.cmc = (VehicleManufacturingCompany) cmc;
	}

	@Override
	public void selectAlgorithm(String algo, VehicleOption args) {
		this.cmc.changeAlgorithm(algo, args);
	}

}
