package businessmodel;

import java.util.ArrayList;

import org.joda.time.Period;

import businessmodel.category.CarOption;

/**
 * A class representing a car model. 
 * 
 * @author SWOP Team 10 2014
 *
 */
public class CarModel {

	/**
	 * The name of the car model.
	 */
	private String name;
	
	/**
	 * The car model specification of the car model.
	 */
	private CarModelSpecification cms;
	
	/**
	 * 
	 */
	private Period standardTimeToFinish;

	/**
	 * Creates a new car model with a given name, car model specification and standard time to finish.
	 * 
	 * @param   name
	 *          The name for the new car model.
	 * @param   cms
	 *          The car model specification for the car new model.
	 * @throws	IllegalArgumentException
	 * 
	 */
	public CarModel(String name,CarModelSpecification cms, Period standardTimeToFinish) 
			throws IllegalArgumentException {
		this.setCarmodel(name);
		this.setCarModelSpecification(cms);
		this.setStandardTimeToFinish(standardTimeToFinish);
	}
	
	/**
	 * Creates a new car model with a name and a car model specification.
	 * @param name
	 * @param cms
	 * @throws IllegalArgumentException
	 */
	public CarModel(String name, CarModelSpecification cms) throws IllegalArgumentException {
		this.setCarmodel(name);
		this.setCarModelSpecification(cms);
		this.setStandardTimeToFinish(new Period(60*3));
	}
	
	/**
	 * Returns a list of all the possible options of the car model.
	 * 
	 * @return	List of all the possible options of the car model.
	 * 			This depends on the car model specification.
	 */
	public ArrayList<CarOption> getPossibilities(){
		return this.cms.getOptionsClone();
	}

	/**
	 * Returns the name of the car model.
	 * 
	 * @return  The name of the car model.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Method to get the standar time to finish.
	 * @return standard time to finish
	 */
	public Period getStandardTimeToFinish() {
		return this.standardTimeToFinish;
	}

	/**
	 * Returns the car model specification of the car model.
	 * 
	 * @return  The car model specification of the car model.
	 */
	public CarModelSpecification getCarModelSpecification() {
		return cms;
	}

	/**
	 * Sets the car model specification of this car model to the given car model specification.
	 * 
	 * @param   cms
	 *          The car model specification for the car model.
	 * @throws 	IllegalArgumentException
	 * 			| If the car model specification is equal to 'null'
	 * 			| cms == null
	 */
	private void setCarModelSpecification(CarModelSpecification cms) throws IllegalArgumentException {
		if(cms == null) throw new IllegalArgumentException("Bad car model specification!");
		this.cms = cms;
	}

	/**
	 * Sets the name of the car model to the given name.
	 * 
	 * @param	name
	 *          The name for the car model.
	 * @throws	IllegalArgumentException
	 * 			| If the name is equal to 'null' or is equal to the empty string
	 * 			| name == null || name.equals("")
	 */
	private void setCarmodel(String name) throws IllegalArgumentException {
		if (name == null || name.equals("")) throw new IllegalArgumentException("Bad name for carmodel!");
		this.name = name;
	}
	
	/**
	 * Method to set the standard time to finish.
	 * @param standardTimeToFinish
	 */
	private void setStandardTimeToFinish(Period standardTimeToFinish) {
		if (standardTimeToFinish == null) throw new IllegalArgumentException("Bad standard time to finish!");
		this.standardTimeToFinish = standardTimeToFinish;
	}

	/**
	 * Returns a string representation of the car model.
	 */
	@Override
	public String toString() {
		return "Car model: " + name;
	}

}