package businessmodel.restrictions;

import businessmodel.category.Airco;
import businessmodel.category.Spoiler;
import businessmodel.category.VehicleOption;
import businessmodel.category.VehicleOptionCategory;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

import java.util.ArrayList;

/**
 * Class representing a restriction that checks the default mandatory options.
 *
 * @author SWOP team 10 2013-2014
 */
public class DefaultMandatoryOptionRestriction extends Restriction {

    private ArrayList<VehicleOptionCategory> categories;

    /**
     * Creates a new restriction.
     */
    public DefaultMandatoryOptionRestriction(ArrayList<VehicleOptionCategory> categories) throws IllegalArgumentException {
        this.setCategories(categories);
    }

    /**
     * Get categories.
     *
     * @return categories
     */
    private ArrayList<VehicleOptionCategory> getCategories() {
        return this.categories;
    }

    /**
     * Set the given categories.
     *
     * @param categories
     * @throws IllegalArgumentException
     */
    private void setCategories(ArrayList<VehicleOptionCategory> categories) throws IllegalArgumentException {
        if (categories == null) throw new IllegalArgumentException("Bad list of categories!");
        this.categories = categories;
    }

    @Override
    public boolean check(ArrayList<VehicleOption> options) throws IllegalArgumentException, UnsatisfiedRestrictionException {
        if (options == null) throw new IllegalArgumentException("Bad list of options!");
        ArrayList<VehicleOptionCategory> unimplementedMandatoryCategories = new ArrayList<VehicleOptionCategory>();
        ArrayList<VehicleOptionCategory> categories = getCategories();
        for (VehicleOption option : options) {
            categories.remove(option.getCategory());
        }
        for (VehicleOptionCategory category : categories) {
            if (!category.equals(new Spoiler()) && !category.equals(new Airco())) {
                unimplementedMandatoryCategories.add(category);
            }
        }
        if (unimplementedMandatoryCategories.size() > 0) {
            String message = "You haven't chosen an option of the following mandatory categories:\n";
            for (VehicleOptionCategory category : unimplementedMandatoryCategories) {
                message += "- " + category + "\n";
            }
            throw new UnsatisfiedRestrictionException(message);
        }
        return true;
    }

}