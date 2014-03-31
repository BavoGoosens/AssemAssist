package restrictions;

import businessmodel.Car;

public class SportEngineRestriction extends Restriction {

	public SportEngineRestriction(String name) {
		super(name);
	}

	@Override
	public boolean check(Car car) {
		return true;
	}

}
