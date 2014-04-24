package control;

import businessmodel.CarManufacturingCompany;
import businessmodel.Model;
import businessmodel.category.CarOption;

public class SchedulingHandler implements SchedulingController{


	private CarManufacturingCompany cmc;

	public SchedulingHandler(Model cmc) {
		this.cmc = (CarManufacturingCompany) cmc;
	}

	@Override
	public void selectAlgorithm(String algo, CarOption args) {
		this.cmc.changeAlgorithm(algo, args);
	}

}
