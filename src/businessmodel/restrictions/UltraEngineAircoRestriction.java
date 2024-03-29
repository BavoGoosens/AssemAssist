package businessmodel.restrictions;

import businessmodel.category.Airco;
import businessmodel.category.Engine;
import businessmodel.category.VehicleOption;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

import java.util.ArrayList;

/**
 * A class representing a restriction that checks for the mandatory options and
 * categories for a set of options with an ultra engine.
 *
 * @author SWOP team 10
 */
public class UltraEngineAircoRestriction extends Restriction {

    /**
     * Creates a new restriction.
     */
    public UltraEngineAircoRestriction() {
    }

    @Override
    public boolean check(ArrayList<VehicleOption> options) throws UnsatisfiedRestrictionException {
        VehicleOption engineOption = getEngineOption(options);
        if (engineOption == null) return false;
        if (engineOption.getName().equalsIgnoreCase("ultra 3l v8")) {
            return checkAirco(options);
        }
        return true;
    }

    /**
     * Get Engine option.
     *
     * @param options
     * @return
     */
    private VehicleOption getEngineOption(ArrayList<VehicleOption> options) {
        for (VehicleOption option : options) {
            if (option.getCategory().equals(new Engine())) {
                return option;
            }
        }
        return null;
    }

    /**
     * Check Airco restriction.
     *
     * @param options
     * @return
     * @throws UnsatisfiedRestrictionException
     */
    private boolean checkAirco(ArrayList<VehicleOption> options) throws UnsatisfiedRestrictionException {
        for (VehicleOption option : options) {
            if (option.getCategory().equals(new Airco())) {
                if (option.getName().equalsIgnoreCase("manual")) return true;
            }
        }
        throw new UnsatisfiedRestrictionException("If you choose an ULTRA ENGINE, you must "
                + "choose the MANUAL AIRCO");
    }

}
