package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.category.*;

public class UltraEngineAircoRestriction extends Restriction {

	public UltraEngineAircoRestriction(String name, Catalog inventory)
			throws IllegalArgumentException {
		super(name, inventory);
	}

	@Override
	public boolean check(ArrayList<CarOption> options) {
		CarOption engineOption = getEngineOption(options);
		if (engineOption == null) return false;
		if (engineOption.getName().equalsIgnoreCase("ultra 3l v8")) {
			return checkAirco(options);
		}
		return true;
	}
	
	private CarOption getEngineOption(ArrayList<CarOption> options) {
		for (CarOption option: options) {

			if (option.getCategory().equals(new Engine())) {

				return option;
			}
		}
		return null;
	}
	
	private boolean checkAirco(ArrayList<CarOption> options) {
		for (CarOption option: options) {

			if (option.getCategory().equals(new Airco())) {

				return option.getName().equalsIgnoreCase("manual");
			}
		}
		return false;
	}

}
