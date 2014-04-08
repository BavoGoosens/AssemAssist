package restrictions;

import java.util.ArrayList;

import businessmodel.CarOption;
import businessmodel.Inventory;

public class UltraEngineAircoRestriction extends Restriction {

	public UltraEngineAircoRestriction(String name, Inventory inventory)
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
		return false;
	}
	
	private CarOption getEngineOption(ArrayList<CarOption> options) {
		for (CarOption option: options) {
			if (option.getCategory() == getInventory().getEngine()) {
				return option;
			}
		}
		return null;
	}
	
	private boolean checkAirco(ArrayList<CarOption> options) {
		for (CarOption option: options) {
			if (option.getCategory() == getInventory().getAirco()) {
				return option.getName().equalsIgnoreCase("manual");
			}
		}
		return false;
	}

}
