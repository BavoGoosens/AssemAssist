package businessmodel;

import java.util.ArrayList;

import component.Component;

/**
 * This car represents a car model. 
 * 
 * @author SWOP Team 10
 *
 */
public class CarModel {

	/**
	 * a variable that describes the model of the car. for example an Audi A6
	 */
	private String carmodel;
	
	/**
	 * a variable the specifies the CarMoelSpecification of this car.
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
	 * @return   this.carmodel
	 */
	public String getCarmodel() {
		return this.carmodel;
	}

	/**
	 * A method to set name the car model to the given name.
	 * @param    carmodel
	 *           the new name of this car model.
	 */
	private void setCarmodel(String carmodel) {
		this.carmodel = carmodel;
	}	
	
	/**
	 * A method to get the car model specification of this car model.
	 * @return   this.cms
	 */
	public CarModelSpecification getCarModelSpecification() {
		return cms;
	}

	/**
	 * A method to set the car model specification of this car model to the given car model specification.
	 * @param   cms
	 *          the new car model specification of this car model.
	 */
	private void setCarModelSpecification(CarModelSpecification cms) {
		this.cms = cms;
	}
}