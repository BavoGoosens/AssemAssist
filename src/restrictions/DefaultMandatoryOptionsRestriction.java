package restrictions;

import java.util.ArrayList;

import businessmodel.CarOption;
import businessmodel.CarOptionCategory;
import businessmodel.Inventory;

public class DefaultMandatoryOptionsRestriction extends Restriction {

	public DefaultMandatoryOptionsRestriction(String name, Inventory inventory) throws IllegalArgumentException {
		super(name, inventory);
	}

	@Override
	public boolean check(ArrayList<CarOption> options) throws IllegalArgumentException {
		if (options == null) throw new IllegalArgumentException("Bad list of options!");
		ArrayList<CarOptionCategory> categories = this.getInventory().getAllCategories();
		for (CarOptionCategory category: categories) {
			for (CarOption option: options) {
				if (option.getCategory() == category) {
					options.remove(option);
				}
			}
		}
		for (CarOption option: options) {
			if (option.getCategory() != this.getInventory().getSpoiler() && 
					option.getCategory() != this.getInventory().getAirco()) {
				return false;
			}
		}
		return true;
	}

}
