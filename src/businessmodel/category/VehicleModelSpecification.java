package businessmodel.category;

import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.restrictions.RestrictionChecker;

import java.util.ArrayList;

/**
 * This class represents a car model specification.
 * It gives an overview of lists of options that are available for a car model.
 *
 * @author SWOP Team 10
 */
public class VehicleModelSpecification {

    /**
     * The options that are available for a car model with this car model specification.
     */
    private ArrayList<VehicleOption> options;

    /**
     * Creates a new car model specification with a given list of options.
     *
     * @param options The options for the new car model specification.
     * @throws IllegalArgumentException | If the list of options is equal to 'null'
     *                                  | options == null
     */
    public VehicleModelSpecification(ArrayList<VehicleOption> options) throws IllegalArgumentException {
        this.setOptions(options);
    }

    /**
     * Returns a cloned object of the list of options of the car model specification.
     *
     * @return A cloned list of the options of the car model specification.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<VehicleOption> getOptionsClone() {
        return (ArrayList<VehicleOption>) this.getOptions().clone();
    }

    /**
     * Returns a list of options with a given category of the car model specification.
     *
     * @throws IllegalArgumentException | If the category is equal to 'null'
     *                                  | category == null
     * @param    category The category of the options.
     * @return A list of options with a given category of the car model specification
     */
    public ArrayList<VehicleOption> getOptionsOfCategory(VehicleOptionCategory category) throws IllegalArgumentException {
        if (category == null) throw new IllegalArgumentException("Bad category!");
        ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
        for (VehicleOption option : this.getOptions()) {
            if (option.getCategory().equals(category)) {
                options.add(option);
            }
        }
        return options;
    }

    /**
     * Returns the options of the car model specification.
     *
     * @return The options of the car model specification.
     */
    private ArrayList<VehicleOption> getOptions() {
        return this.options;
    }

    /**
     * Sets the options of the car model specification to the given list of options.
     *
     * @param options The options for the car model specification.
     * @throws IllegalArgumentException | If the list of options is equal to 'null'
     *                                  | options == null
     */
    @SuppressWarnings("unchecked")
    private void setOptions(ArrayList<VehicleOption> options) throws IllegalArgumentException {
        if (options == null) throw new IllegalArgumentException("Bad list of options!");
        this.options = (ArrayList<VehicleOption>) options.clone();
    }

    /**
     * Checks whether the restrictions for a car are satisfied.
     *
     * @param car The car that needs to be checked.
     * @throws IllegalArgumentException        | If the car is equal to 'null'
     *                                         | car == null
     * @throws UnsatisfiedRestrictionException | If the car violates any of of the restrictions.
     * @return True if the car doesn't violate any restrictions.
     */
    public boolean checkRestrictions(Vehicle car) throws IllegalArgumentException, UnsatisfiedRestrictionException {
        if (car == null) throw new IllegalArgumentException("Bad car!");
        RestrictionChecker checker = new RestrictionChecker();
        return this.checkVehicleModel(car) && checker.check(car.getOptionsClone());
    }

    /**
     * Checks if a car model of the given car is valid.
     *
     * @param car The car that needs to be checked.
     * @throws IllegalArgumentException        | If the given car is equal to 'null'
     *                                         | car == null
     * @throws UnsatisfiedRestrictionException | If the car model of the given car is not valid.
     * @return True if the car model of the given car is valid.
     */
    private boolean checkVehicleModel(Vehicle car) throws IllegalArgumentException, UnsatisfiedRestrictionException {
        if (car == null) throw new IllegalArgumentException("Bad car!");
        ArrayList<VehicleOption> wrongOptions = new ArrayList<VehicleOption>();
        for (VehicleOption option : car.getOptionsClone()) {
            if (!this.getOptions().contains(option)) {
                wrongOptions.add(option);
            }
        }
        if (wrongOptions.size() > 0) {
            String message = "The following options do not belong to the car model you've chosen:\n";
            for (VehicleOption option : wrongOptions) {
                message += "\n- " + option;
            }
            throw new UnsatisfiedRestrictionException(message);
        }
        return true;
    }
}


