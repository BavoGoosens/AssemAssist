package restrictions;

import businessmodel.Car;

public class UltraEngineAircoRestriction extends Restriction {

	public UltraEngineAircoRestriction(String name) {
		super(name);
	}

	@Override
	public boolean check(Car car) {
		return true;
	}

}
