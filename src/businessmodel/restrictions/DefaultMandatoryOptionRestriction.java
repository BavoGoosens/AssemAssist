package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.category.*;
import businessmodel.exceptions.UnsatisfiedRestrictionException;


public class DefaultMandatoryOptionRestriction extends Restriction {

	public DefaultMandatoryOptionRestriction(String name, Catalog catalog) throws IllegalArgumentException {
		super(name, catalog);
	}

	@Override
	public boolean check(ArrayList<CarOption> options) throws IllegalArgumentException, UnsatisfiedRestrictionException {
		if (options == null) throw new IllegalArgumentException("Bad list of options!");
		ArrayList<CarOptionCategory> unimplementedMandatoryCategories = new ArrayList<CarOptionCategory>();
		ArrayList<CarOptionCategory> categories = getCatalog().getAllCategories();
		for (CarOption option: options) {
			categories.remove(option.getCategory());
		}
		for (CarOptionCategory category: categories) {
			if (!category.equals(new Spoiler()) && !category.equals(new Airco())) {
				unimplementedMandatoryCategories.add(category);
			}
		}
		if (unimplementedMandatoryCategories.size() > 0) {
			String message = "You haven't chosen an option of the following mandatory categories:\n";
			for (CarOptionCategory category: unimplementedMandatoryCategories) {
				message += "- "+category+"\n";
			}
			throw new UnsatisfiedRestrictionException(message);
		}
		return true;
	}

}