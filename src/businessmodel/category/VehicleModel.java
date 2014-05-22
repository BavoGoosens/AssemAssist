package businessmodel.category;

import java.util.ArrayList;

import org.joda.time.Period;

/**
 * A class representing a car model. 
 * 
 * @author SWOP Team 10 2014
 *
 */
public class VehicleModel {

	private String name;
	private VehicleModelSpecification cms;
	private Period standardTimeToFinish;

	/**
	 * Creates a new car model with a given name, car model specification and standard time to finish.
	 * 
	 * @param   name
	 *          The name for the new car model.
	 * @param   cms
	 *          The car model specification for the car new model.
	 * @param	standardTimeToFinish
	 * 			The standard time to finish this car model.
	 * @throws	IllegalArgumentException
	 * 
	 */
	public VehicleModel(String name,VehicleModelSpecification cms, Period standardTimeToFinish) 
			throws IllegalArgumentException {
		this.setVehicleModel(name);
		this.setVehicleModelSpecification(cms);
		this.setStandardTimeToFinish(standardTimeToFinish);
	}
	
	/**
	 * Returns a list of all the possible options of the car model.
	 * 
	 * @return	List of all the possible options of the car model.
	 * 			This depends on the car model specification.
	 */
	public ArrayList<VehicleOption> getPossibilities(){
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
	 * Returns the standard time to finish this car model.
	 * 
	 * @return The standard time to finish.
	 */
	public Period getStandardTimeToFinish() {
		return this.standardTimeToFinish;
	}

	/**
	 * Returns the car model specification of the car model.
	 * 
	 * @return  The car model specification of the car model.
	 */
	public VehicleModelSpecification getVehicleModelSpecification() {
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
	private void setVehicleModelSpecification(VehicleModelSpecification cms) throws IllegalArgumentException {
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
	private void setVehicleModel(String name) throws IllegalArgumentException {
		if (name == null || name.equals("")) throw new IllegalArgumentException("Bad name for vehiclemodel!");
		this.name = name;
	}
	
	/**
	 * Method to set the standard time to finish.
	 * 
	 * @param standardTimeToFinish
	 */
	private void setStandardTimeToFinish(Period standardTimeToFinish) throws IllegalArgumentException {
		if (standardTimeToFinish == null) throw new IllegalArgumentException("Bad standard time to finish!");
		this.standardTimeToFinish = standardTimeToFinish;
	}

	/**
	 * Returns a string representation of the car model.
	 */
	@Override
	public String toString() {
		return "Vehicle model: " + name;
	}

}