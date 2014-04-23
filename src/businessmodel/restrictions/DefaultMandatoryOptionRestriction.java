package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.category.*;


public class DefaultMandatoryOptionRestriction extends Restriction {

	public DefaultMandatoryOptionRestriction(String name, Catalog catalog) throws IllegalArgumentException {
		super(name, catalog);
	}

	@Override
	public boolean check(ArrayList<CarOption> options) throws IllegalArgumentException {
		if (options == null) throw new IllegalArgumentException("Bad list of options!");
		this.clearMandatoryCategories();
		this.clearMandatoryOptions();
		boolean restrictionViolated = false;
		ArrayList<CarOptionCategory> categories = getCatalog().getAllCategories();
		for (CarOption option: options) {
			categories.remove(option.getCategory());
		}
		for (CarOptionCategory category: categories) {
			if (!category.equals(new Spoiler()) && !category.equals(new Airco())) {
				this.addMandatoryCategory(category);
				restrictionViolated = true;
			}
		}
		return !restrictionViolated;
	}

}