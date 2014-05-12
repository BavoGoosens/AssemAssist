package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.category.Body;
import businessmodel.category.VehicleOption;
import businessmodel.category.Engine;
import businessmodel.category.Spoiler;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

/**
 * A class representing a restriction that checks for the mandatory options and 
 * categories for a set of options with a sport body.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class SportBodyRestriction extends Restriction {

	/**
	 * Creates a new restriction.	
	 */
	public SportBodyRestriction() {}

	@Override
	public boolean check(ArrayList<VehicleOption> options) throws UnsatisfiedRestrictionException {
		if (options == null) throw new IllegalArgumentException("Bad list of options!");
		VehicleOption bodyOption = this.getBodyOption(options);
		if (bodyOption == null) return true; // als er geen body is, is deze restrictie wel voldaan
		if (bodyOption.getName().equalsIgnoreCase("sport")) {
			checkForSpoiler(options);
			checkEngine(options);
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

	private boolean checkForSpoiler(ArrayList<VehicleOption> options) throws UnsatisfiedRestrictionException {
		for (VehicleOption option: options) {
			if (option.getCategory().equals(new Spoiler())) {
				return true;
			}
		}
		throw new UnsatisfiedRestrictionException("If you choose a SPORT BODY, you must "
				+ "choose a SPOILER option.");
	}

	private boolean checkEngine(ArrayList<VehicleOption> options) throws UnsatisfiedRestrictionException {
		for (VehicleOption option: options) {
			if (option.getCategory().equals(new Engine())) {
				if (option.getName().equalsIgnoreCase("performance 2.5l v6") ||
						option.getName().equalsIgnoreCase("ultra 3l v8")) return true;
			}
		}
		throw new UnsatisfiedRestrictionException("If you choose a SPORT BODY, you must choose "
				+ "a PERFORMANCE or ULTRA ENGINE.");
	}

}