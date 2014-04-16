package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.category.Airco;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.Spoiler;

public class DefaultMandatoryOptionRestriction extends Restriction {

	public DefaultMandatoryOptionRestriction(String name, Catalog inventory) throws IllegalArgumentException {
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
			if (category != this.getInventory().getCategory(new Spoiler().getKey()) && 
					category != this.getInventory().getCategory(new Airco().getKey())) {
				return false;
			}
		}
		return true;
	}

}