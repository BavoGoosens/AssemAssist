/**
 * 
 * @author team 10
 *
 */

public class Car {

	/**
	 * A variable that specifies the body of the car.
	 */
	private Component body;
	
	/**
	 * A variable that specifies the color of the car.
	 */
	private Component color;
	
	/**
	 * A variable that specifies the engine of the car.
	 */
	private Component engine;
	
	/**
	 * A variable that specifies the gearbox of the car.
	 */
	private Component gearbox;
	
	/**
	 * A variable that specifies the seats of the car.
	 */
	private Component seats;
	
	/**
	 * A variable that specifies the airco of the car.
	 */
	private Component airco;
	
	/**
	 * A variable that specifies the wheels of the car.
	 */
	private Component wheels;

	public Car(Component body, Component color,Component engine,Component gearbox, Component seats, Component airco, Component wheels) 
	{
		this.setBody(body);
		this.setColor(color);
		this.setEngine(engine);
		this.setGearbox(gearbox);
		this.setSeats(seats);
		this.setWheels(wheels);
	}
	
	/**
	 * A method that returns the body of this car.
	 * @return this.body
	 */
	private Component getBody() {
		return body;
	}

	/**
	 * A method that sets the body of this car to the given body.
	 * @param body
	 * 		  The body of the car.
	 */
	private void setBody(Component body) throws IllegalArgumentException{
		if (!canHaveAsBody(body)) throw new IllegalArgumentException();
		this.body = body;
	}

	/**
	 * A method that checks if the given body can be a body of the car. 
	 * @param  body
	 * 	       The body of the car.
	 * @return If the given body is an instance of Body and is different from the null-object
	 * 		   then the body of this object can be set to the given body.
	 */
	private boolean canHaveAsBody(Component body) {
		return (body != null && body instanceof Body);
	}

	/**
	 * A method that returns the color of this car.
	 * @return this.color
	 */
	private Component getColor() {
		return color;
	}

	/**
	 * A method that sets the color of this car to the given color.
	 * @param this.color = color
	 */
	private void setColor(Component color) {
		this.color = color;
	}

	/**
	 * A method that returns the engine of this car.
	 * @return this.engine
	 */
	private Component getEngine() {
		return engine;
	}

	/**
	 * A method that sets the engine of this car to the given engine.
	 * @param this.engine = engine
	 */
	private void setEngine(Component engine) {
		this.engine = engine;
	}

	/**
	 * A method that returns the gearbox of this car.
	 * @return this.gearbox
	 */
	private Component getGearbox() {
		return gearbox;
	}

	/**
	 * A method that sets the gearbox of this car to the given gearbox.
	 * @param this.gearbox = gearbox
	 */
	private void setGearbox(Component gearbox) {
		this.gearbox = gearbox;
	}

	/**
	 * A method that returns the seats of this car.
	 * @return this.seats
	 */
	private Component getSeats() {
		return seats;
	}

	/**
	 * A method that sets the body of this seats to the given seats.
	 * @param this.seats = seats
	 */
	private void setSeats(Component seats) {
		this.seats = seats;
	}

	/**
	 * A method that returns the airco of this car.
	 * @return this.airco
	 */
	private Component getAirco() {
		return airco;
	}

	/**
	 * A method that sets the airco of this car to the given airco.
	 * @param this.airco = airco
	 */
	private void setAirco(Component airco) {
		this.airco = airco;
	}

	/**
	 * A method that returns the wheels of this car.
	 * @return this.wheels
	 */
	private Component getWheels() {
		return wheels;
	}

	/**
	 * A method that sets the wheels of this car to the given wheels.
	 * @param this.wheels = wheels
	 */
	private void setWheels(Component wheels) {
		this.wheels = wheels;
	}

}
