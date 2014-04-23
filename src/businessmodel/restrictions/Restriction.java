package businessmodel.restrictions;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.category.CarOption;
import businessmodel.exceptions.UnsatisfiedRestrictionException;

public abstract class Restriction {
	
	private String name;
	private Catalog catalog;

	public Restriction(String name, Catalog catalog) throws IllegalArgumentException {
		if (name == null || name.equals("")) throw new IllegalArgumentException("Bad name!");
		if (catalog == null) throw new IllegalArgumentException("Bad catalog!");
		this.name = name;
		this.catalog = catalog;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Catalog getCatalog() {
		return this.catalog;
	}
	
	public abstract boolean check(ArrayList<CarOption> options) throws IllegalArgumentException, UnsatisfiedRestrictionException;

}
