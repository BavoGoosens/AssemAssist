package businessmodel;

import java.util.ArrayList;

import businessmodel.category.CarOption;

/**
 * This car represents a car model. 
 * 
 * @author SWOP Team 10 2014
 *
 */
public class CarModel {

	/**
	 * A variable that describes the model of the car.
	 */
	private String name;
	
	/**
	 * A variable the specifies the CarModelSpecification of this car.
	 */
	private CarModelSpecification cms;

	/**
	 * A constructor for the class CarModel.
	 * 
	 * @param   carmodel
	 *          A string that represents the name of the car model.
	 * @param   cms
	 *          A car model specification of the car.
	 */
	public CarModel(String name,CarModelSpecification cms) throws IllegalArgumentException {
		setCarmodel(name);
		setCarModelSpecification(cms);
	}
	
	/**
	 * A method to get the car model.
	 * 
	 * @return  String
	 * 			the name of this car model.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * A method to set name the car model to the given name.
	 * 
	 * @param    carmodel
	 *           the new name of this car model.
	 */
	private void setCarmodel(String name) throws IllegalArgumentException {
		if (name == null || name.equals("")) throw new IllegalArgumentException("Bad name for carmodel!");
		this.name = name;
	}	
	
	/**
	 * A method to get the car model specification of this car model.
	 * 
	 * @return  CarModelSpecification 
	 * 			the car manufacturing company of this car model.
	 */
	public CarModelSpecification getCarModelSpecification() {
		return cms;
	}

	/**
	 * A method to set the car model specification of this car model to the given car model specification.
	 * 
	 * @param   cmss
	 *          the new car model specification of this car model.
	 * @throws 	IllegalArgumentException
	 * 			if cms == null
	 */
	private void setCarModelSpecification(CarModelSpecification cms) throws IllegalArgumentException {
		if(cms == null) throw new IllegalArgumentException("Bad car model specification!");
		this.cms = cms;
	}
	
	/**
	 * A method that return a list of all the possible components ordered by type. 
	 * 
	 * @return	List of  list of components, ordered by type.
	 */
	public ArrayList<CarOption> getPossibilities(){
		return this.cms.getOptionsClone();
	}
	
	@Override
	public String toString() {
		return "Car model: " + name;
	}

}