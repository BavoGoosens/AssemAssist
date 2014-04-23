package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.category.CarOption;

public class RestrictionChecker {

	private ArrayList<Restriction> restrictions;
	Catalog catalog;
	
	public RestrictionChecker() {
		this.restrictions = new ArrayList<Restriction>();
		this.catalog = new Catalog();
		this.generateRestrictions();
	}
	
	private ArrayList<Restriction> getRestrictions() {
		return this.restrictions;
	}
	
	public void generateRestrictions() {
		this.addRestriction(new DefaultMandatoryOptionRestriction("Default Mandatory "
				+ "Options Restriction", this.catalog));
		this.addRestriction(new SportBodyRestriction("Sport Body Restriction", this.catalog));
		this.addRestriction(new UltraEngineAircoRestriction("Ultra Engine Airco Mandatory "
				+ "Restriction", this.catalog));
	}
	
	public void addRestriction(Restriction restriction) {
		if (restriction == null) throw new IllegalArgumentException("Bad restriction!");
		this.getRestrictions().add(restriction);
	}
	
	public boolean check(ArrayList<CarOption> options) {
		for (Restriction restriction: this.getRestrictions()) {
			if (!restriction.check(options)) return false;
		}
		return true;
	}

}
