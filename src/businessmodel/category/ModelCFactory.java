package businessmodel.category;

import java.util.ArrayList;

import org.joda.time.Period;

import businessmodel.exceptions.IllegalVehicleOptionCategoryException;

/**
 * Class representing a factory that creates C model objects.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class ModelCFactory extends VehicleModelFactory {

	/**
	 * Creates the body category of the C model with the necessary car options.
	 */
	@Override
	protected Body createBody() {
		Body body = new Body();
		VehicleOption body1 = new VehicleOption("sport", body);
		try {
			body.addOption(body1);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return body;
	}

	/**
	 * Creates the color category of the C model with the necessary car options.
	 */
	@Override
	protected Color createColor() {
		Color color = new Color();
		VehicleOption color1 = new VehicleOption("black", color);
		VehicleOption color2 = new VehicleOption("white", color);
		try {
			color.addOption(color1);
			color.addOption(color2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return color;
	}

	/**
	 * Creates the engine category of the C model with the necessary car options.
	 */
	@Override
	protected Engine createEngine() {
		Engine engine = new Engine();
		VehicleOption engine1 = new VehicleOption("performance 2.5l v6", engine);
		VehicleOption engine2 = new VehicleOption("ultra 3l v8", engine);
		try {
			engine.addOption(engine1);
			engine.addOption(engine2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return engine;
	}

	/**
	 * Creates the gear box category of the C model with the necessary car options.
	 */
	@Override
	protected Gearbox createGearbox() {
		Gearbox gearbox = new Gearbox();
		VehicleOption gearbox1 = new VehicleOption("6 speed manual", gearbox);
		try {
			gearbox.addOption(gearbox1);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return gearbox;
	}

	/**
	 * Creates the seats category of the C model with the necessary car options.
	 */
	@Override
	protected Seats createSeats() {
		Seats seats = new Seats();
		VehicleOption seats1 = new VehicleOption("leather white", seats);
		VehicleOption seats2 = new VehicleOption("leather black", seats);
		try {
			seats.addOption(seats1);
			seats.addOption(seats2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return seats;
	}

	/**
	 * Creates the airco category of the C model with the necessary car options.
	 */
	protected Airco createAirco() {
		Airco airco = new Airco();
		VehicleOption airco1 = new VehicleOption("manual", airco);
		VehicleOption airco2 = new VehicleOption("automatic", airco);
		try {
			airco.addOption(airco1);
			airco.addOption(airco2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return airco;
	}

	/**
	 * Creates the wheels category of the C model with the necessary car options.
	 */
	@Override
	protected Wheels createWheels() {
		Wheels wheels = new Wheels();
		VehicleOption wheels1 = new VehicleOption("winter", wheels);
		VehicleOption wheels2 = new VehicleOption("sports", wheels);
		try {
			wheels.addOption(wheels1);
			wheels.addOption(wheels2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return wheels;
	}

	/**
	 * Creates the spoiler category of the C model with the necessary car options.
	 */
	protected Spoiler createSpoiler() {
		Spoiler spoiler = new Spoiler();
		VehicleOption spoiler1 = new VehicleOption("low", spoiler);
		VehicleOption spoiler2 = new VehicleOption("high", spoiler);
		try {
			spoiler.addOption(spoiler1);
			spoiler.addOption(spoiler2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return spoiler;
	}
	
	/**
	 * Returns the name of the C model.
	 * 
	 * @return	The name of the C model.
	 */
	@Override
	protected String getName() {
		return "Vehicle Model C";
	}

	/**
	 * Creates a C model.
	 */
	@Override
	public VehicleModel createModel() {
		ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
		for (VehicleOption option: this.createBody().getOptionsClone()) {
			options.add(option);
		}
		for (VehicleOption option: this.createColor().getOptionsClone()) {
			options.add(option);
		}
		for (VehicleOption option: this.createEngine().getOptionsClone()) {
			options.add(option);
		}
		for (VehicleOption option: this.createGearbox().getOptionsClone()) {
			options.add(option);
		}
		for (VehicleOption option: this.createSeats().getOptionsClone()) {
			options.add(option);
		}
		for (VehicleOption option: this.createAirco().getOptionsClone()) {
			options.add(option);
		}
		for (VehicleOption option: this.createWheels().getOptionsClone()) {
			options.add(option);
		}
		for (VehicleOption option: this.createSpoiler().getOptionsClone()) {
			options.add(option);
		}
		
		VehicleModelSpecification cms = new VehicleModelSpecification(options);
		return new VehicleModel(this.getName(), cms, this.getStandardTimeToFinish());
	}
	
	@Override
	protected Period getStandardTimeToFinish() {
		Period period = new Period();
		period = period.withHours(3);
		return period;
	}

}
