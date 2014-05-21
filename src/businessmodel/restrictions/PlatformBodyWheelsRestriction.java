package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.category.Body;
import businessmodel.category.VehicleOption;
import businessmodel.category.Wheels;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

/**
 * A class representing a restriction.
 * 
 * @author SWOP team 10 
 *
 */
public class PlatformBodyWheelsRestriction extends Restriction {

	public PlatformBodyWheelsRestriction() {}

	@Override
	public boolean check(ArrayList<VehicleOption> options)
			throws IllegalArgumentException, UnsatisfiedRestrictionException {
		if (options == null) throw new IllegalArgumentException("Bad list of options!");
		VehicleOption bodyOption = this.getBodyOption(options);
		if (bodyOption == null) return true; // als er geen body is, is deze restrictie wel voldaan
		if (bodyOption.getName().equalsIgnoreCase("platform")) {
			this.checkWheels(options);
		}
		return true;
	}
	
	/**
	 * Get body Option.
	 * @param options
	 * @return
	 */
	private VehicleOption getBodyOption(ArrayList<VehicleOption> options) {
		for (VehicleOption option: options) {
			if (option.getCategory().equals(new Body())) {
				return option;
			}
		}
		return null;
	}
	
	/**
	 * Check wheels restriction.
	 * @param options
	 * @return
	 * @throws UnsatisfiedRestrictionException
	 */
	private boolean checkWheels(ArrayList<VehicleOption> options) throws UnsatisfiedRestrictionException {
		for (VehicleOption option: options) {
			if (option.getCategory().equals(new Wheels())) {
				if (option.getName().equals("heavy-duty")) return true;
			}
		}
		throw new UnsatisfiedRestrictionException("If you choose a PLATFORM BODY, you must choose HEAVY DUTY wheels.");
	}

}
