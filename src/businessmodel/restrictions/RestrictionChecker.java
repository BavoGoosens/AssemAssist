package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.category.CarOption;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

/**
 * A class representing a checker that can check a set of options against all the restrictions it holds.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class RestrictionChecker {
	
	/**
	 * The restrictions the restriction checker holds.
	 */
	private ArrayList<Restriction> restrictions;
	
	/**
	 * Creates a new restriction checker and creates the necessary restrictions.
	 */
	public RestrictionChecker() {
		this.restrictions = new ArrayList<Restriction>();
		this.generateRestrictions();
	}
	
	/**
	 * Returns the restrictions the restriction checker holds.
	 * 
	 * @return The restrictions the restriction checker holds.
	 */
	private ArrayList<Restriction> getRestrictions() {
		return this.restrictions;
	}
	
	/**
	 * Creates the restrictions the restriction checker holds.
	 */
	private void generateRestrictions() {
		this.addRestriction(new DefaultMandatoryOptionRestriction(new Catalog().getAllCategories()));
		this.addRestriction(new SportBodyRestriction());
		this.addRestriction(new UltraEngineAircoRestriction());
		this.addRestriction(new MultipleCategoryRestriction());
	}
	
	/**
	 * Adds a restriction to the restriction checker.
	 * 
	 * @param 	restriction
	 * 			The restriction that needs to be added to the restriction checker.
	 */
	public void addRestriction(Restriction restriction) {
		if (restriction == null) throw new IllegalArgumentException("Bad restriction!");
		this.getRestrictions().add(restriction);
	}
	
	/**
	 * Checks the given set of options against all the restrictions it holds.
	 * 
	 * @param	options
	 * 			The given set of options
	 * @return	True if all restrictions are satisfied.
	 * @throws 	IllegalArgumentException
	 * @throws 	UnsatisfiedRestrictionException
	 * 			| If the given set of options violates one of the restrictions
	 */
	public boolean check(ArrayList<CarOption> options) throws IllegalArgumentException, UnsatisfiedRestrictionException {
		for (Restriction restriction: this.getRestrictions()) {
			if (!restriction.check(options)) return false;
		}
		return true;
	}

}
