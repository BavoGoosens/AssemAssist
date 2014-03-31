package restrictions;

import businessmodel.Car;

public abstract class Restriction {

	private String name;
	
	public Restriction(String name) {
		if (name == null) throw new IllegalArgumentException("Bad name!");
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract boolean check(Car car);

}
