package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.Inventory;
import businessmodel.category.CarOption;

public class SportBodyRestriction extends Restriction {

	public SportBodyRestriction(String name, Inventory inventory) throws IllegalArgumentException {
		super(name, inventory);
	}

	@Override
	public boolean check(ArrayList<CarOption> options) {
		CarOption bodyOption = this.getBodyOption(options);
		if (bodyOption == null) return false;
		if (bodyOption.getName().equalsIgnoreCase("sport")) {
			return checkForSpoiler(options) && checkEngine(options);
		}
		return true;
	}

	private CarOption getBodyOption(ArrayList<CarOption> options) {
		for (CarOption option: options) {
			if (option.getCategory() == this.getInventory().getBody()) {
				return option;
			}
		}
		return null;
	}

	private boolean checkForSpoiler(ArrayList<CarOption> options) {
		for (CarOption option: options) {
			if (option.getCategory() == this.getInventory().getSpoiler()) {
				return true;
			}
		}
		return false;
	}

	private boolean checkEngine(ArrayList<CarOption> options) {
		for (CarOption option: options) {
			if (option.getCategory() == this.getInventory().getEngine()) {
				return option.getName().equalsIgnoreCase("performance 2.5l v6") ||
						option.getName().equalsIgnoreCase("ultra 3l v8");
			}
		}
		return false;
	}

}