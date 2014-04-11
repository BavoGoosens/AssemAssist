package businessmodel;

import java.util.ArrayList;

import businessmodel.category.CarOption;

public class RestrictionChecker {
	
	private static Inventory inventory;

	public RestrictionChecker() {
		inventory = new Inventory();
	}
	
	public static boolean check(ArrayList<CarOption> options) {
		return checkDefaultMandatoryOptionsRestriction(options) &&
				checkSportBodyRestriction(options) &&
				checkSportEngineRestriction(options);
	}
	
	private static boolean checkDefaultMandatoryOptionsRestriction(ArrayList<CarOption> options) {
		ArrayList<CarOptionCategory> categories = inventory.getAllCategories();
		for (CarOptionCategory category: categories) {
			for (CarOption option: options) {
				if (option.getCategory() == category) {
					options.remove(option);
				}
			}
		}
		for (CarOption option: options) {
			if (option.getCategory() != inventory.getSpoiler() && 
					option.getCategory() != inventory.getAirco()) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean checkSportBodyRestriction(ArrayList<CarOption> options) {
		return true;
	}
	
	private static boolean checkSportEngineRestriction(ArrayList<CarOption> options) {
		return true;
	}

}
