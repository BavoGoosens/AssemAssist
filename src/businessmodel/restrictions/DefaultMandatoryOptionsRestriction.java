package businessmodel.restrictions;

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
		ArrayList<CarOptionCategory> categories = getInventory().getAllCategories();
		for (CarOption option: options) {
			categories.remove(option.getCategory());
		}
		for (CarOptionCategory category: categories) {
			if (category != this.getInventory().getSpoiler() && 
					category != this.getInventory().getAirco()) {
				return false;
			}
		}
		return true;
	}

}