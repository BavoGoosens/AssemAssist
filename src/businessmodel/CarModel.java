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
	
	public CarModel(String carmodel){
		setCarmodel(carmodel);
	}
	
	public String getCarmodel() {
		return carmodel;
	}

	private void setCarmodel(String carmodel) {
		this.carmodel = carmodel;
	}

}