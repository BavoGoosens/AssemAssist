package businessmodel.restrictions;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.NEW;

import businessmodel.category.Airco;
import businessmodel.category.Certification;
import businessmodel.category.Protection;
import businessmodel.category.Spoiler;
import businessmodel.category.Storage;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

/**
 * Class representing a restriction that checks the default mandatory options.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class DefaultMandatoryOptionRestriction extends Restriction {
	
	private ArrayList<VehicleOptionCategory> categories;

	/**
	 * Creates a new restriction.	
	 */
	public DefaultMandatoryOptionRestriction(ArrayList<VehicleOptionCategory> categories) throws IllegalArgumentException {
		this.setCategories(categories);
	}
	
	private void setCategories(ArrayList<VehicleOptionCategory> categories) throws IllegalArgumentException {
		if (categories == null) throw new IllegalArgumentException("Bad list of categories!");
		this.categories = categories;
	}
	
	private ArrayList<VehicleOptionCategory> getCategories() {
		return this.categories;
	}

	@Override
	public boolean check(ArrayList<VehicleOption> options) throws IllegalArgumentException, UnsatisfiedRestrictionException {
		if (options == null) throw new IllegalArgumentException("Bad list of options!");
		ArrayList<VehicleOptionCategory> unimplementedMandatoryCategories = new ArrayList<VehicleOptionCategory>();
		ArrayList<VehicleOptionCategory> categories = getCategories();
		for (VehicleOption option: options) {
			categories.remove(option.getCategory());
		}
		for (VehicleOptionCategory category: categories) {
			if (!category.equals(new Spoiler()) && !category.equals(new Airco()) 
					&& !category.equals(new Certification()) && !category.equals(new Protection()) && !category.equals(new Storage())) {
				unimplementedMandatoryCategories.add(category);
			}
		}
		if (unimplementedMandatoryCategories.size() > 0) {
			String message = "You haven't chosen an option of the following mandatory categories:\n";
			for (VehicleOptionCategory category: unimplementedMandatoryCategories) {
				message += "- "+category+"\n";
			}
			throw new UnsatisfiedRestrictionException(message);
		}
		return true;
	}

}