package businessmodel;

import java.util.ArrayList;

import component.*;

/**
 * This class represents a car model specification.
 * It gives an overview of lists of Components that are available for a car model.
 * 
 * @author SWOP Team 10
 *
 */
public class CarModelSpecification {
	
	/**
	 * A list with all the available body types.
	 */
	private ArrayList<Body> bodies;

	/**
	 * A list of available colors for this model.
	 */
	private ArrayList<Color> colors;

	/**
	 * A list of available engines for this car model.
	 */
	private ArrayList<Engine> engines;

	/**
	 * A list of available gearboxes.
	 */
	private ArrayList<Gearbox> gearboxes;

	/**
	 * A list of available seats.
	 */
	private ArrayList<Seats> seats;

	/**
	 * A list of available airco installations.
	 */
	private ArrayList<Airco> aircos;

	/**
	 * A list of available wheel types.
	 */
	private ArrayList<Wheels> wheels;

	/**
	 * This method constructs the car model.
	 * 
	 * @param bodies The list of available car bodies.
	 * 
	 * @param colors The list of available car colors.
	 * 
	 * @param engines The list of available car engines.
	 * 
	 * @param gearboxes The list of available car gearboxes.
	 * 
	 * @param seats The list of available car seats.
	 * 
	 * @param aircos The list of available car aircos.
	 * 
	 * @param wheels The list of available car wheels.
	 */
	public CarModelSpecification(ArrayList<Body> bodies, ArrayList<Color> colors,
			ArrayList<Engine> engines, ArrayList<Gearbox> gearboxes,
			ArrayList<Seats> seats, ArrayList<Airco> aircos,
			ArrayList<Wheels> wheels) {
		this.setBodies(bodies);
		this.setColors(colors);
		this.setEngines(engines);
		this.setGearboxes(gearboxes);
		this.setSeats(seats);
		this.setAircos(aircos);
		this.setWheels(wheels);
	}
	
	/**
	 * A method to get the different bodies for a car model.
	 * 
	 * @return this.bodies
	 */
	public ArrayList<Body> getBodies() {
		return this.bodies;
	}

	/**
	 * A method to get the different colors for a car model.
	 * 
	 * @return this.colors
	 */
	public ArrayList<Color> getColors() {
		return this.colors;
	}

	/**
	 * A method to get the different engines of a car model.
	 * 
	 * @return this.engines
	 */
	public ArrayList<Engine> getEngines() {
		return this.engines;
	}

	/**
	 * A method to get the different gear boxes of a car model.
	 * 
	 * @return this.gearboxes
	 */
	public ArrayList<Gearbox> getGearboxes() {
		return this.gearboxes;
	}

	/**
	 * A method to get the different seats of a car model.
	 * 
	 * @return this.seats
	 */
	public ArrayList<Seats> getSeats() {
		return this.seats;
	}

	/**
	 * A method to get the different aircos of a car model.
	 * 
	 * @return this.aircos
	 */
	public ArrayList<Airco> getAircos() {
		return this.aircos;
	}

	/**
	 * A method to get the different wheels of a car model.
	 * 
	 * @return this.wheels
	 */
	public ArrayList<Wheels> getWheels() {
		return this.wheels;
	}

	/**
	 * A method that sets the different bodies for a car model to the given bodies.
	 * 
	 * @param  bodies 
	 *         the different bodies of a car model.
	 * @throws IllegalArgumentException
	 *         bodies == null
	 */
	private void setBodies(ArrayList<Body> bodies) throws IllegalArgumentException {
		if (bodies == null) throw new IllegalArgumentException();
		this.bodies = bodies;
	}

	/**
	 * A method that sets the different colors for a car model to the given colors.
	 * 
	 * @param  colors 
	 *         the different colors of a car model.
	 * @throws IllegalArgumentException
	 *         colors == null
	 */
	private void setColors(ArrayList<Color> colors) throws IllegalArgumentException {
		if (colors == null) throw new IllegalArgumentException();
		this.colors = colors;
	}

	/**
	 * A method that sets the different engines for a car model to the given engines.
	 * 
	 * @param  engines 
	 *         the different engines of a car model.
	 * @throws IllegalArgumentException
	 *         engines == null
	 */
	private void setEngines(ArrayList<Engine> engines) throws IllegalArgumentException {
		if (engines == null) throw new IllegalArgumentException();
		this.engines = engines;
	}

	/**
	 * A method that sets the different gear boxes for a car model to the given gear boxes.
	 * 
	 * @param  gearboxes 
	 *         the different gear boxes of a car model.
	 * @throws IllegalArgumentException
	 *         gearboxes == null
	 */
	private void setGearboxes(ArrayList<Gearbox> gearboxes) throws IllegalArgumentException {
		if (gearboxes == null) throw new IllegalArgumentException();
		this.gearboxes = gearboxes;
	}

	/**
	 * A method that sets the seats bodies for a car model to the given seats.
	 * 
	 * @param  seats 
	 *         the different seats of a car model.
	 * @throws IllegalArgumentException
	 *         seats == null
	 */
	private void setSeats(ArrayList<Seats> seats) throws IllegalArgumentException {
		if (seats == null) throw new IllegalArgumentException();
		this.seats = seats;
	}

	/**
	 * A method that sets the different aircos for a car model to the given aircos.
	 * 
	 * @param  aircos 
	 *         the different aircos of a car model.
	 * @throws IllegalArgumentException
	 *         aircos == null
	 */
	private void setAircos(ArrayList<Airco> aircos) throws IllegalArgumentException {
		if (aircos == null) throw new IllegalArgumentException();
		this.aircos = aircos;
	}

	/**
	 * A method that sets the different wheels for a car model to the given wheels.
	 * 
	 * @param  wheels 
	 *         the different wheels of a car model.
	 * @throws IllegalArgumentException
	 *         wheels == null
	 */
	private void setWheels(ArrayList<Wheels> wheels) throws IllegalArgumentException {
		if (wheels == null) throw new IllegalArgumentException();
		this.wheels = wheels;
	}
	
	/**
	 * A method to get all the components of this car model specification.
	 * 
	 * @return all the components
	 */
	public ArrayList<Component[]> getPosibilities(){
		ArrayList<Component[]> posibilities=  new ArrayList<Component[]>();
		
		posibilities.add(this.getBodies().toArray(new Component[this.getBodies().size()]));
		posibilities.add(this.getColors().toArray(new Component[this.getColors().size()]));
		posibilities.add(this.getEngines().toArray(new Component[this.getEngines().size()]));
		posibilities.add(this.getSeats().toArray(new Component[this.getSeats().size()]));
		posibilities.add(this.getWheels().toArray(new Component[this.getWheels().size()]));
		posibilities.add(this.getGearboxes().toArray(new Component[this.getGearboxes().size()]));
		posibilities.add(this.getAircos().toArray(new Component[this.getAircos().size()]));
		
		return posibilities;
	}
	
	@Override
	public String toString() {
		return "bodies= " + bodies.toString() + ", colors= " + colors.toString()
				+ ", engines=  " + engines.toString() + ", gearboxes= " + gearboxes.toString()
				+ ", seats= " + seats.toString() + ", aircos= " + aircos.toString() + ", wheels= "
				+ wheels.toString();
	}
}


