package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.category.VehicleOption;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

/**
 * A class representing an abstract restriction.
 * 
 * @author SWOP team 10 
 *
 */
public abstract class Restriction {

	/**
	 * Creates a new restriction.	
	 */
	public Restriction() {}
	
	/**
	 * Checks whether the given set of options violates the restriction or not.
	 * 
	 * @param 	options
	 * 			The set of options.
	 * @return	True if the set options doesn't violate the restriction.
	 * @throws 	IllegalArgumentException
	 * 			| If the set of options is equal to 'null'
	 * 			| options == null
	 * @throws 	UnsatisfiedRestrictionException
	 * 			| If the given set of options violates the restriction
	 */
	public abstract boolean check(ArrayList<VehicleOption> options) throws IllegalArgumentException, UnsatisfiedRestrictionException;

}
