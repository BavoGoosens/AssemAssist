package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

public class MultipleCategoryRestriction extends Restriction {

	public MultipleCategoryRestriction(String name, Catalog catalog)
			throws IllegalArgumentException {
		super(name, catalog);
	}

	@Override
	public boolean check(ArrayList<CarOption> options)
			throws IllegalArgumentException, UnsatisfiedRestrictionException {
		ArrayList<CarOptionCategory> doubleCategories = new ArrayList<CarOptionCategory>();
		for (int i = 0; i < options.size(); i++) {
			CarOptionCategory category = options.get(i).getCategory();
			for (int j = i+1; j < options.size(); j++) {
				if (options.get(j).getCategory().equals(category) && !doubleCategories.contains(category)) {
					doubleCategories.add(category);
					break;
				}
			}
		}
		if (doubleCategories.size() > 0) {
			String message = "You cannot choose multiple options for the following categories:\n";
			for (CarOptionCategory category: doubleCategories) {
				message += "- "+category+"\n";
			}
			throw new UnsatisfiedRestrictionException(message);
		}
		return true;
	}

}