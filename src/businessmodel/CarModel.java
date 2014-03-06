package businessmodel;


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
	 * 
	 */
	private CarModelSpecification cms;

	public CarModel(String carmodel,CarModelSpecification cms){
		setCarmodel(carmodel);
		setCarModelSpecification(cms);
	}
	
	public String getCarmodel() {
		return carmodel;
	}

	private void setCarmodel(String carmodel) {
		this.carmodel = carmodel;
	}	
	
	public CarModelSpecification getCms() {
		return cms;
	}

	private void setCarModelSpecification(CarModelSpecification cms) {
		this.cms = cms;
	}
}