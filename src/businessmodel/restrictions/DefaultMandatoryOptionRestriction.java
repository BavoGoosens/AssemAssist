package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.category.Airco;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.Spoiler;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

/**
 * Class representing a restriction that checks the default mandatory options.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class DefaultMandatoryOptionRestriction extends Restriction {
	
	private ArrayList<CarOptionCategory> categories;

	/**
	 * Creates a new restriction.	
	 */
	public DefaultMandatoryOptionRestriction(ArrayList<CarOptionCategory> categories) throws IllegalArgumentException {
		this.setCategories(categories);
	}
	
	private void setCategories(ArrayList<CarOptionCategory> categories) throws IllegalArgumentException {
		if (categories == null) throw new IllegalArgumentException("Bad list of categories!");
		this.categories = categories;
	}
	
	private ArrayList<CarOptionCategory> getCategories() {
		return this.categories;
	}

	@Override
	public boolean check(ArrayList<CarOption> options) throws IllegalArgumentException, UnsatisfiedRestrictionException {
		if (options == null) throw new IllegalArgumentException("Bad list of options!");
		ArrayList<CarOptionCategory> unimplementedMandatoryCategories = new ArrayList<CarOptionCategory>();
		ArrayList<CarOptionCategory> categories = getCategories();
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