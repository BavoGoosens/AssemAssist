package businessmodel.category;

import java.util.ArrayList;

import org.joda.time.Period;

import businessmodel.VehicleModel;
import businessmodel.VehicleModelSpecification;
import businessmodel.exceptions.IllegalVehicleOptionCategoryException;

/**
 * Class representing a factory that creates A model objects.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class ModelAFactory extends VehicleModelFactory {
	
	/**
	 * Creates the body category of the A model with the necessary car options.
	 */
	@Override
	protected Body createBody() {
		Body body = new Body();
		VehicleOption body1 = new VehicleOption("sedan", body);
		VehicleOption body2 = new VehicleOption("break", body);
		try {
			body.addOption(body1);
			body.addOption(body2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return body;
	}
	
	/**
	 * Creates the color category of the A model with the necessary car options.
	 */
	@Override
	protected Color createColor() {
		Color color = new Color();
		VehicleOption color1 = new VehicleOption("red", color);
		VehicleOption color2 = new VehicleOption("blue", color);
		VehicleOption color3 = new VehicleOption("black", color);
		VehicleOption color4 = new VehicleOption("white", color);
		try {
			color.addOption(color1);
			color.addOption(color2);
			color.addOption(color3);
			color.addOption(color4);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return color;
	}
	
	/**
	 * Creates the engine category of the A model with the necessary car options.
	 */
	@Override
	protected Engine createEngine() {
		Engine engine = new Engine();
		VehicleOption engine1 = new VehicleOption("standard 2l v4", engine);
		VehicleOption engine2 = new VehicleOption("performance 2.5l v6", engine);
		try {
			engine.addOption(engine1);
			engine.addOption(engine2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return engine;
	}
	
	/**
	 * Creates the gear box category of the A model with the necessary car options.
	 */
	@Override
	protected Gearbox createGearbox() {
		Gearbox gearbox = new Gearbox();
		VehicleOption gearbox1 = new VehicleOption("6 speed manual", gearbox);
		VehicleOption gearbox2 = new VehicleOption("5 speed manual", gearbox);
		VehicleOption gearbox3 = new VehicleOption("5 speed automatic", gearbox);
		try {
			gearbox.addOption(gearbox1);
			gearbox.addOption(gearbox2);
			gearbox.addOption(gearbox3);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return gearbox;
	}
	
	/**
	 * Creates the seats category of the A model with the necessary car options.
	 */
	@Override
	protected Seats createSeats() {
		Seats seats = new Seats();
		VehicleOption seats1 = new VehicleOption("leather white", seats);
		VehicleOption seats2 = new VehicleOption("leather black", seats);
		VehicleOption seats3 = new VehicleOption("vinyl grey", seats);
		try {
			seats.addOption(seats1);
			seats.addOption(seats2);
			seats.addOption(seats3);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return seats;
	}
	
	/**
	 * Creates the airco category of the A model with the necessary car options.
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
	 * Creates the wheels category of the A model with the necessary car options.
	 */
	@Override
	protected Wheels createWheels() {
		Wheels wheels = new Wheels();
		VehicleOption wheels1 = new VehicleOption("winter", wheels);
		VehicleOption wheels2 = new VehicleOption("comfort", wheels);
		VehicleOption wheels3 = new VehicleOption("sports", wheels);
		try {
			wheels.addOption(wheels1);
			wheels.addOption(wheels2);
			wheels.addOption(wheels3);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return wheels;
	}
	
	/**
	 * Returns the name of the A model.
	 * 
	 * @return	The name of the A model.
	 */
	@Override
	protected String getName() {
		return "Vehicle model A";
	}

	/**
	 * Creates an A model.
	 */
	@Override
	public VehicleModel createModel() {
		
		ArrayList<VehicleOption> options = new ArrayList<VehicleOption>();
		
		for (VehicleOption option: this.createBody().getOptionsClone()) 
			options.add(option);
		
		for (VehicleOption option: this.createColor().getOptionsClone()) 
			options.add(option);
		
		for (VehicleOption option: this.createEngine().getOptionsClone()) 
			options.add(option);
		
		for (VehicleOption option: this.createGearbox().getOptionsClone())
			options.add(option);
		
		for (VehicleOption option: this.createSeats().getOptionsClone()) 
			options.add(option);
		
		for (VehicleOption option: this.createAirco().getOptionsClone()) 
			options.add(option);
		
		for (VehicleOption option: this.createWheels().getOptionsClone())
			options.add(option);
		
		VehicleModelSpecification cms = new VehicleModelSpecification(options);
		return new VehicleModel(this.getName(), cms, this.getStandardTimeToFinish());
	}
	
	@Override
	protected Period getStandardTimeToFinish() {
		Period period = new Period();
		period = period.withMinutes(30);
		period = period.withHours(2);
		return period;
	}
}
