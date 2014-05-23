package businessmodel.category;

import businessmodel.exceptions.IllegalVehicleOptionCategoryException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

import java.util.ArrayList;

/**
 * A class representing a car.
 *
 * @author SWOP team 10 2014
 */
public class Vehicle {

    /**
     * A list that holds all the options of a car.
     */
    private ArrayList<VehicleOption> options;

    /**
     * Creates a new car with a given list of options.
     *
     * @param options The options of the car.
     * @throws UnsatisfiedRestrictionException
     * @throws IllegalArgumentException
     */
    public Vehicle(ArrayList<VehicleOption> options) throws IllegalArgumentException {
        this.setOptions(options);
    }

    /**
     * Returns a cloned list of options of the car.
     *
     * @return The options (clone) of the car.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<VehicleOption> getOptionsClone() {
        return (ArrayList<VehicleOption>) this.options.clone();
    }

    /**
     * Returns the options of the car.
     *
     * @return The options of the car.
     */
    private ArrayList<VehicleOption> getOptions() {
        return this.options;
    }

    /**
     * Sets the options of this car to the given options.
     *
     * @param options The options of the car.
     * @throws IllegalArgumentException | If the list of options is equal to 'null'
     *                                  | options == null
     */
    @SuppressWarnings("unchecked")
    private void setOptions(ArrayList<VehicleOption> options) throws IllegalArgumentException {
        if (options == null) throw new IllegalArgumentException();
        this.options = (ArrayList<VehicleOption>) options.clone();
    }

    /**
     * Add options for a Truck.
     */
    public void addTruckOptions() {
        Protection protection = new Protection();
        Storage storage = new Storage();
        Certification certification = new Certification();
        VehicleOption protection1 = new VehicleOption("cargo protection", protection);
        VehicleOption storage1 = new VehicleOption("tool storage", storage);
        VehicleOption certification1 = new VehicleOption("maximum cargo load certification", certification);
        try {
            protection.addOption(protection1);
            storage.addOption(storage1);
            certification.addOption(certification1);
            this.options.add(protection1);
            this.options.add(storage1);
            this.options.add(certification1);
        } catch (IllegalVehicleOptionCategoryException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a string representation of this car.
     */
    @Override
    public String toString() {
        return "option = " + this.getOptions().toString();
    }

}