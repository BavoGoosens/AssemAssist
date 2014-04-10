package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.CarOption;
import businessmodel.Inventory;

public abstract class Restriction {
	
	private String name;
	private Inventory inventory;

	public Restriction(String name, Inventory inventory) throws IllegalArgumentException {
		if (name == null || name.equals("")) throw new IllegalArgumentException("Bad name!");
		if (inventory == null) throw new IllegalArgumentException("Bad inventory!");
		this.name = name;
		this.inventory = inventory;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public abstract boolean check(ArrayList<CarOption> options);

}
