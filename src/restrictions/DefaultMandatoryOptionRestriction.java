package restrictions;

import java.util.ArrayList;

import businessmodel.Car;
import businessmodel.CarOption;

public class DefaultMandatoryOptionRestriction extends Restriction {

	public DefaultMandatoryOptionRestriction(String name) {
		super(name);
	}

	@Override
	public boolean check(Car car) {
		return true;
	}

}
