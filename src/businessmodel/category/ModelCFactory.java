package businessmodel.category;

import java.util.ArrayList;

import org.joda.time.Period;

import businessmodel.CarModel;
import businessmodel.CarModelSpecification;
import businessmodel.exceptions.IllegalCarOptionCategoryException;

/**
 * Class representing a factory that creates C model objects.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class ModelCFactory extends CarModelFactory {

	/**
	 * Creates the body category of the C model with the necessary car options.
	 */
	@Override
	protected Body createBody() {
		Body body = new Body();
		CarOption body1 = new CarOption("sport", body);
		try {
			body.addOption(body1);
		} catch (IllegalCarOptionCategoryException e) {
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
		CarOption color1 = new CarOption("black", color);
		CarOption color2 = new CarOption("white", color);
		try {
			color.addOption(color1);
			color.addOption(color2);
		} catch (IllegalCarOptionCategoryException e) {
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
		CarOption engine1 = new CarOption("performance 2.5l v6", engine);
		CarOption engine2 = new CarOption("ultra 3l v8", engine);
		try {
			engine.addOption(engine1);
			engine.addOption(engine2);
		} catch (IllegalCarOptionCategoryException e) {
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
		CarOption gearbox1 = new CarOption("6 speed manual", gearbox);
		try {
			gearbox.addOption(gearbox1);
		} catch (IllegalCarOptionCategoryException e) {
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
		CarOption seats1 = new CarOption("leather white", seats);
		CarOption seats2 = new CarOption("leather black", seats);
		try {
			seats.addOption(seats1);
			seats.addOption(seats2);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return seats;
	}

	/**
	 * Creates the airco category of the C model with the necessary car options.
	 */
	protected Airco createAirco() {
		Airco airco = new Airco();
		CarOption airco1 = new CarOption("manual", airco);
		CarOption airco2 = new CarOption("automatic", airco);
		try {
			airco.addOption(airco1);
			airco.addOption(airco2);
		} catch (IllegalCarOptionCategoryException e) {
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
		CarOption wheels1 = new CarOption("winter", wheels);
		CarOption wheels2 = new CarOption("sports", wheels);
		try {
			wheels.addOption(wheels1);
			wheels.addOption(wheels2);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return wheels;
	}

	/**
	 * Creates the spoiler category of the C model with the necessary car options.
	 */
	protected Spoiler createSpoiler() {
		Spoiler spoiler = new Spoiler();
		CarOption spoiler1 = new CarOption("low", spoiler);
		CarOption spoiler2 = new CarOption("high", spoiler);
		try {
			spoiler.addOption(spoiler1);
			spoiler.addOption(spoiler2);
		} catch (IllegalCarOptionCategoryException e) {
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
		return "Model C";
	}

	/**
	 * Creates a C model.
	 */
	@Override
	public CarModel createModel() {
		ArrayList<CarOption> options = new ArrayList<CarOption>();
		for (CarOption option: this.createBody().getOptionsClone()) {
			options.add(option);
		}
		for (CarOption option: this.createColor().getOptionsClone()) {
			options.add(option);
		}
		for (CarOption option: this.createEngine().getOptionsClone()) {
			options.add(option);
		}
		for (CarOption option: this.createGearbox().getOptionsClone()) {
			options.add(option);
		}
		for (CarOption option: this.createSeats().getOptionsClone()) {
			options.add(option);
		}
		for (CarOption option: this.createAirco().getOptionsClone()) {
			options.add(option);
		}
		for (CarOption option: this.createWheels().getOptionsClone()) {
			options.add(option);
		}
		for (CarOption option: this.createSpoiler().getOptionsClone()) {
			options.add(option);
		}
		
		CarModelSpecification cms = new CarModelSpecification(options);
		return new CarModel(this.getName(), cms, this.getStandardTimeToFinish());
	}
	
	@Override
	protected Period getStandardTimeToFinish() {
		Period period = new Period();
		period = period.withHours(3);
		return period;
	}

}
