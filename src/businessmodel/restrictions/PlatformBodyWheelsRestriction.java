package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.category.*;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

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
	
	private VehicleOption getBodyOption(ArrayList<VehicleOption> options) {
		for (VehicleOption option: options) {
			if (option.getCategory().equals(new Body())) {
				return option;
			}
		}
		return null;
	}
	
	private boolean checkWheels(ArrayList<VehicleOption> options) throws UnsatisfiedRestrictionException {
		for (VehicleOption option: options) {
			if (option.getCategory().equals(new Wheels())) {
				if (option.getName().equals("heavy-duty")) return true;
			}
		}
		throw new UnsatisfiedRestrictionException("If you choose a PLATFORM BODY, you must choose HEAVY DUTY wheels.");
	}

}
