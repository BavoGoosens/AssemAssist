package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.CarOptionCategory;
import businessmodel.Inventory;
import businessmodel.category.CarOption;

public class DefaultMandatoryOptionRestriction extends Restriction {

	public DefaultMandatoryOptionRestriction(String name, Inventory inventory) throws IllegalArgumentException {
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