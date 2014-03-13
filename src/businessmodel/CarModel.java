package businessmodel;

import java.util.ArrayList;

import component.Component;

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
	private String carmodel;
	
	/**
	 * A variable the specifies the CarMoelSpecification of this car.
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
	public CarModel(String carmodel,CarModelSpecification cms){
		setCarmodel(carmodel);
		setCarModelSpecification(cms);
	}
	
	/**
	 * A method to get the car model.
	 * 
	 * @return   the name of this car model.
	 */
	public String getCarmodel() {
		return this.carmodel;
	}

	/**
	 * A method to set name the car model to the given name.
	 * 
	 * @param    carmodel
	 *           the new name of this car model.
	 */
	private void setCarmodel(String carmodel) {
		this.carmodel = carmodel;
	}	
	
	/**
	 * A method to get the car model specification of this car model.
	 * 
	 * @return   the car manufacturing company of this car model.
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
		if(cms == null){
			throw new IllegalArgumentException();
		}
		this.cms = cms;
	}
	
	/**
	 * A method that return a list of all the possible components ordered by type. 
	 * 
	 * @return	List of  list of components, ordered by type.
	 */
	public ArrayList<Component[]> getPossibilities(){
		return this.cms.getPosibilities();
	}
	
	@Override
	public String toString() {
		return "carmodel= " + carmodel;
	}

}