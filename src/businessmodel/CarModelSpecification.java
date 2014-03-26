package businessmodel;

import java.util.ArrayList;
import java.util.HashMap;

import component.*;

/**
 * This class represents a car model specification.
 * It gives an overview of lists of Components that are available for a car model.
 * 
 * @author SWOP Team 10
 *
 */
public class CarModelSpecification {
	
	private HashMap<String, ArrayList<Component>> spec;

	/**
	 * This method constructs the car model specification.
	 * 
	 * @param 	parts 
	 * 			The list of components this specification consists of.
	 */
	public CarModelSpecification(ArrayList<String> types,  ArrayList<Component> parts) {
		this.setTypes(types);
		this.setComponents(parts);
	}
	
	private void setTypes(ArrayList<String> types) {
		for (String type: types){
			this.spec.put(type, new ArrayList<Component>());
		}
	}

	private void setComponents(ArrayList<Component> parts) {
		for (Component part : parts){
			String type = part.getClass().getName();
			ArrayList<Component> list = this.spec.get(type);
			list.add(part);
		}
		
	}
	
	/**
	 * A method to get all the components of this car model specification.
	 * 
	 * @return A list of list of all the components of a car model specification
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, ArrayList<Component>> getPossibilities(){
		return (HashMap<String, ArrayList<Component>>) this.spec.clone();
	}
	
	@Override
	public String toString() {
		return this.spec.toString();
	}
}


