package BusinessModel;

import java.util.ArrayList;
import Component.*;

public class CarModel {
	
	private ArrayList<Body> bodies;
	
	private ArrayList<Color> colors;
	
	private ArrayList<Engine> engines;
	
	private ArrayList<Gearbox> gearboxes;
	
	private ArrayList<Seats> seats;
	
	private ArrayList<Airco> aircos;
	
	private ArrayList<Wheels> wheels;
	
	public CarModel(ArrayList<Body> bodies, ArrayList<Color> colors,
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
	
	public Car getCarWithIndices(int bodyIndex, int colorIndex, int engineIndex,
								int gearboxIndex, int seatsIndex, int aircoIndex, int wheelsIndex) {
		return new Car(this.getBodies().get(bodyIndex), this.getColors().get(colorIndex),
						this.getEngines().get(engineIndex), this.getGearboxes().get(gearboxIndex),
						this.getSeats().get(seatsIndex), this.getAircos().get(aircoIndex),
						this.getWheels().get(wheelsIndex));
	}

}
