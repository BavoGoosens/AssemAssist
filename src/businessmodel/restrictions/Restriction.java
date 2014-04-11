package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.category.CarOption;

public abstract class Restriction {
	
	private String name;
	private Catalog inventory;

	public Restriction(String name, Catalog inventory) throws IllegalArgumentException {
		if (name == null || name.equals("")) throw new IllegalArgumentException("Bad name!");
		if (inventory == null) throw new IllegalArgumentException("Bad inventory!");
		this.name = name;
		this.inventory = inventory;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Catalog getInventory() {
		return this.inventory;
	}
	
	public abstract boolean check(ArrayList<CarOption> options);

}
