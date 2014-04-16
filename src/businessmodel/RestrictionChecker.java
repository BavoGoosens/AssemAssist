package businessmodel;

import java.util.ArrayList;

import businessmodel.category.Airco;
import businessmodel.category.CarOption;
import businessmodel.category.CarOptionCategory;
import businessmodel.category.Spoiler;

public class RestrictionChecker {
	
	private static Catalog inventory;

	public RestrictionChecker() {
		inventory = new Catalog();
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
			if (option.getCategory() != inventory.getCategory(new Spoiler().getKey()) && 
					option.getCategory() != inventory.getCategory(new Airco().getKey())) {
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
