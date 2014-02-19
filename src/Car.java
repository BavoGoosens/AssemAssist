/**
 * A class representing a car.
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

	/**
	 * A method that constructs a new car.
	 * 
	 * @param body
	 *        the body of the car.
	 * @param color
	 *        the color of the car.
	 * @param engine
	 *        the engine of the car.
	 * @param gearbox
	 *        the gearbox of the car.
	 * @param seats
	 *        the seats of the car.
	 * @param airco
	 *        the airco of the car.
	 * @param wheels
	 *        the wheels of the car.
	 */
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
	 * 
	 * @return this.body
	 */
	private Component getBody() {
		return body;
	}

	/**
	 * A method that sets the body of this car to the given body.
	 * 
	 * @param  body
	 * 		   The body of the car.
	 * @throws IllegalArgumentException
	 *         !canHaveAsBody
	 */
	private void setBody(Component body) throws IllegalArgumentException{
		if (!canHaveAsBody(body)) throw new IllegalArgumentException();
		this.body = body;
	}

	/**
	 * A method that checks if the given body can be a body of the car. 
	 * 
	 * @param  body
	 * 	       The body of the car.
	 * @return True if the given body is an instance of Body and is different from the null-object
	 */
	private boolean canHaveAsBody(Component body) {
		return (body != null && body.getType() == Type.ENGINE);
	}

	/**
	 * A method that returns the color of this car.
	 * 
	 * @return this.color
	 */
	private Component getColor() {
		return color;
	}

    /**
	 * A method that sets the color of this car to the given color.
	 * 
     * @param  color
     * 	       The color of this car
     * @throws IllegalArgumentException
     *         !canHaveAsColor
     */
	private void setColor(Component color) throws IllegalArgumentException {
		if(!canHaveAsColor(color)) throw new IllegalArgumentException();
		this.color = color;
	}

	/**
	 * A method that checks if the given color van be a color for the car.
	 * 
	 * @param  color
	 * 		   The color of the car.
	 * @return True if the given color is an instance of Color and is different from the null-object
	 */		  
	private boolean canHaveAsColor(Component color) {
		return (color != null && color.getType() == Type.COLOR);
	}

	/**
	 * A method that returns the engine of this car.
	 * 
	 * @return this.engine
	 */
	private Component getEngine() {
		return engine;
	}

    /**
     * A method that sets the engine of the car to the given engine.
     * @param  engine
     *         the engine of the car.
     * @throws IllegalArgumentException
     *         !canHaveAsEngine(engine)
     */
	private void setEngine(Component engine) throws IllegalArgumentException {
		if (!canHaveAsEngine(engine)) throw new IllegalArgumentException();
		this.engine = engine;
	}

	/**
	 * A method that checks if the engine of the car can be the given engine.
	 * 
	 * @param   engine
	 *          the engine of the car
	 * @return  True if the given engine is an enum type ENGINE and is different from the null-object
	 */
	private boolean canHaveAsEngine(Component engine) {
		return (engine != null && engine.getType() == type.ENGINE);
	}

	/**
	 * A method that returns the gearbox of this car.
	 * 
	 * @return this.gearbox
	 */
	private Component getGearbox() {
		return gearbox;
	}

	/**
	 * A method that sets the gearbox of this car to the given gearbox.
	 * 
	 * @param  gearbox
	 *         the gearbox of this car.
	 * @throws IllegalArgumentException
	 *         !canHaveAsGearbox
	 */
	private void setGearbox(Component gearbox) throws IllegalArgumentException {
		if (!canHaveAsGearbox(gearbox)) throw new IllegalArgumentException();
		this.gearbox = gearbox;
	}

	/**
	 * A method that checks if the given gearbox can be the gearbox of the car.
	 * 
	 * @param  gearbox
	 *         the gearbox of the car.
	 * @return True if the given gearbox is an enum type GEARBOX and is different from the null-object
	 */
	private boolean canHaveAsGearbox(Component gearbox) {
		return (gearbox != null && gearbox.getType() == type.GEARBOX);
	}

	/**
	 * A method that returns the seats of this car.
	 * 
	 * @return this.seats
	 */
	private Component getSeats() {
		return seats;
	}

	/**
	 * A method that sets the body of this seats to the given seats.
	 * 
	 * @param  seats
	 *         the seats of this car.
	 * @throws IllegalArgumentException
	 *         !canHaveAsSeats(seats)
	 */
	private void setSeats(Component seats) throws IllegalArgumentException {
		if (!canHaveAsSeats(seats)) throw new IllegalArgumentException();
		this.seats = seats;
	}

	/**
	 * A method that checks if the given seats can be seats for the car.
	 * 
	 * @param  seats
	 *         the seats of the car
	 * @return True if the given seats is an enum type SEATS and is different from the null-object
	 */
	private boolean canHaveAsSeats(Component seats) {
		return (seats != null && seats.getType() == Type.SEATS);
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
