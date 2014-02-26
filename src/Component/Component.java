package Component;
/**
 * A class representing a component.
 * 
 * @author Team 10
 *
 */
public abstract class Component {
	
	/**
	 * A variable that contains the name of the component.
	 */
	private String name;
	
	/**
	 * A variable that contains the price of the component.
	 */
	private double price;

	/**
	 * This method constructs the component.
	 * 
	 * @param 	name
	 * 			The name of the component.
	 * @param 	price
	 * 			The price of the component.
	 * @param 	type
	 * 			The type of the component.
	 */
	public Component(String name, double price) {
		this.setName(name);
		this.setPrice(price);
	}
	
	/**
	 * This method returns the name of the component.
	 * @return this.body
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * This method sets the name of the component.
	 * @param 	name
	 * 			The name for the body.
	 * @throws 	IllegalArgumentException
	 * 			name == null
	 */
	private void setName(String name) throws IllegalArgumentException {
		if (name == null) throw new IllegalArgumentException();
		this.name = name;
	}
	
	/**
	 * This method returns the price of the component.
	 * @return this.price
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * This method sets the price for the component.
	 * @param 	price
	 * 			The price for the body.
	 * @throws	IllegalAgumentException
	 * 			price < 0
	 */
	private void setPrice(double price) throws IllegalArgumentException {
		if (price < 0) throw new IllegalArgumentException(); // prijs = 0 bij pakketvoordeel?
		this.price = price;
	}

	/**
	 * This method returns a string representation of the component.
	 */
	public String toString() {
		return ": "+this.name+" ("+this.price+" euro)";
	}
}


