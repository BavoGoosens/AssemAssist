package BusinessModel;
/**
 * A class representing a specific car order
 * 
 * @author Team 10
 *
 */
public class Order {
	
	/**
	 * A variable that contains the specific car being ordered
	 */
	private Car car;
	
	/**
	 * A variable that represents the order is completed or not.
	 */
	private boolean completed;
	
	/**
	 * The constructor for an order
	 * 
	 * @param car
	 *        the car the user wants to order
	 */
	public Order( Car car){
		setCar(car);
		this.completed = false;
	}
	
	/**
	 * This method returns the ordered car
	 * 
	 * @return this.car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * This method sets the car being ordered 
	 * 
	 * @param car
	 * 		  the car the user wants to order
	 */
	private void setCar(Car car) {
		this.car = car;
	}

	/**
	 * a method to see if the order is completed or not.
	 * 
	 * @return   this.completed
	 */
	public boolean getCompleted(){
		return this.completed;
	}
}
