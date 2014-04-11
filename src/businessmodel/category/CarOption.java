package businessmodel.category;

public class CarOption {

	private String name;
	private final CarOptionCategory category;
	
	public CarOption(String name, CarOptionCategory category) throws IllegalArgumentException {
		if (category == null) throw new IllegalArgumentException("Bad category!");
		this.category = category;
		this.setName(name);
	}
	
	public String getName() {
		return this.name;
	}
	
	public CarOptionCategory getCategory() {
		return this.category;
	}
	
	private void setName(String name) throws IllegalArgumentException {
		if (name == null || name.equals("")) throw new IllegalArgumentException("Bad name!");
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.getCategory().toString()+": "+this.getName();
	}

}
