package businessmodel.restrictions;

import businessmodel.Car;

public class SportSpoilerRestriction extends Restriction {

	public SportSpoilerRestriction(String name) {
		super(name);
	}

	@Override
	public boolean check(Car car) {
		return true;
	}

}
