package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.category.*;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

public class UltraEngineAircoRestriction extends Restriction {

	public UltraEngineAircoRestriction(String name, Catalog catalog)
			throws IllegalArgumentException {
		super(name, catalog);
	}

	@Override
	public boolean check(ArrayList<CarOption> options) throws UnsatisfiedRestrictionException {
		CarOption engineOption = getEngineOption(options);
		if (engineOption == null) return false;
		if (engineOption.getName().equalsIgnoreCase("ultra 3l v8")) {
			return checkAirco(options);
		}
		return true;
	}
	
	private CarOption getEngineOption(ArrayList<CarOption> options) {
		for (CarOption option: options) {
			if (option.getCategory().equals(new Engine())) {
				return option;
			}
		}
		return null;
	}
	
	private boolean checkAirco(ArrayList<CarOption> options) throws UnsatisfiedRestrictionException {
		for (CarOption option: options) {
			if (option.getCategory().equals(new Airco())) {
				if (option.getName().equalsIgnoreCase("manual")) return true;
			}
		}
		throw new UnsatisfiedRestrictionException("If you choose an ULTRA ENGINE, you must "
				+ "choose the MANUAL AIRCO");
	}

}
