	package businessmodel;

	import java.util.ArrayList;

	import component.*;

	/**
	 * This car represents a car model. 
	 * A car model is an overview of lists of Components which the user can customize into a car.
	 * 
	 * @author Team 10
	 *
	 */
	public class CarModelSpecification {
		
		/**
		 * The name of this car model.
		 */
		private String name;
		
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
		 * 
		 * @param name The name of this car model.
		 */
		public CarModelSpecification(ArrayList<Body> bodies, ArrayList<Color> colors,
						ArrayList<Engine> engines, ArrayList<Gearbox> gearboxes,
						ArrayList<Seats> seats, ArrayList<Airco> aircos,
						ArrayList<Wheels> wheels, String name) {
			this.setBodies(bodies);
			this.setColors(colors);
			this.setEngines(engines);
			this.setGearboxes(gearboxes);
			this.setSeats(seats);
			this.setAircos(aircos);
			this.setWheels(wheels);
			this.setName(name);
		}
		
		/**
		 * This method returns the name of this car model.
		 * 
		 * @return String
		 * 		   The name of this car model.
		 */
		public String getName() {
			return name;
		}

		/**
		 * This method sets the name of this car model.
		 * 
		 * @param name
		 * 		  The name of this car model.
		 */
		private void setName(String name) {
			this.name = name;
		}

		public ArrayList<Body> getBodies() {
			return this.bodies;
		}
		
		public ArrayList<Color> getColors() {
			return this.colors;
		}
		
		public ArrayList<Engine> getEngines() {
			return this.engines;
		}
		
		public ArrayList<Gearbox> getGearboxes() {
			return this.gearboxes;
		}
		
		public ArrayList<Seats> getSeats() {
			return this.seats;
		}
		
		public ArrayList<Airco> getAircos() {
			return this.aircos;
		}
		
		public ArrayList<Wheels> getWheels() {
			return this.wheels;
		}
		
		private void setBodies(ArrayList<Body> bodies) throws IllegalArgumentException {
			if (bodies == null) throw new IllegalArgumentException();
			this.bodies = bodies;
		}
		
		private void setColors(ArrayList<Color> colors) throws IllegalArgumentException {
			if (colors == null) throw new IllegalArgumentException();
			this.colors = colors;
		}
		
		private void setEngines(ArrayList<Engine> engines) throws IllegalArgumentException {
			if (engines == null) throw new IllegalArgumentException();
			this.engines = engines;
		}
		
		private void setGearboxes(ArrayList<Gearbox> gearboxes) throws IllegalArgumentException {
			if (gearboxes == null) throw new IllegalArgumentException();
			this.gearboxes = gearboxes;
		}
		
		private void setSeats(ArrayList<Seats> seats) throws IllegalArgumentException {
			if (seats == null) throw new IllegalArgumentException();
			this.seats = seats;
		}
		
		private void setAircos(ArrayList<Airco> aircos) throws IllegalArgumentException {
			if (aircos == null) throw new IllegalArgumentException();
			this.aircos = aircos;
		}
		
		private void setWheels(ArrayList<Wheels> wheels) throws IllegalArgumentException {
			if (wheels == null) throw new IllegalArgumentException();
			this.wheels = wheels;
		}
		
		//public Car getCarWithIndices(int bodyIndex, int colorIndex, int engineIndex,
		//							int gearboxIndex, int seatsIndex, int aircoIndex, int wheelsIndex) {
		//	return new Car(this.getBodies().get(bodyIndex), this.getColors().get(colorIndex),
		//					this.getEngines().get(engineIndex), this.getGearboxes().get(gearboxIndex),
		//					this.getSeats().get(seatsIndex), this.getAircos().get(aircoIndex),
		//					this.getWheels().get(wheelsIndex));
		//}

	}


