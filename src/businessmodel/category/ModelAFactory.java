package businessmodel.category;

import java.util.ArrayList;

import businessmodel.CarModel;
import businessmodel.CarModelSpecification;
import businessmodel.exceptions.IllegalCarOptionCategoryException;

/**
 * Class representing a factory that creates A model objects.
 * 
 * @author team 10
 *
 */
public class ModelAFactory extends CarModelFactory {
	
	/**
	 * Creates the body category of the A model with the necessary car options.
	 */
	@Override
	protected Body createBody() {
		Body body = new Body();
		CarOption body1 = new CarOption("sedan", body);
		CarOption body2 = new CarOption("break", body);
		try {
			body.addOption(body1);
			body.addOption(body2);
		} catch (IllegalCarOptionCategoryException e) {
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
		CarOption color1 = new CarOption("red", color);
		CarOption color2 = new CarOption("blue", color);
		CarOption color3 = new CarOption("black", color);
		CarOption color4 = new CarOption("white", color);
		try {
			color.addOption(color1);
			color.addOption(color2);
			color.addOption(color3);
			color.addOption(color4);
		} catch (IllegalCarOptionCategoryException e) {
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
		CarOption engine1 = new CarOption("standard 2l v4", engine);
		CarOption engine2 = new CarOption("performance 2.5l v6", engine);
		try {
			engine.addOption(engine1);
			engine.addOption(engine2);
		} catch (IllegalCarOptionCategoryException e) {
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
		CarOption gearbox1 = new CarOption("6 speed manual", gearbox);
		CarOption gearbox2 = new CarOption("5 speed manual", gearbox);
		CarOption gearbox3 = new CarOption("5 speed automatic", gearbox);
		try {
			gearbox.addOption(gearbox1);
			gearbox.addOption(gearbox2);
			gearbox.addOption(gearbox3);
		} catch (IllegalCarOptionCategoryException e) {
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
		CarOption seats1 = new CarOption("leather white", seats);
		CarOption seats2 = new CarOption("leather black", seats);
		CarOption seats3 = new CarOption("vinyl grey", seats);
		try {
			seats.addOption(seats1);
			seats.addOption(seats2);
			seats.addOption(seats3);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return seats;
	}
	
	/**
	 * Creates the airco category of the A model with the necessary car options.
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
	 * Creates the wheels category of the A model with the necessary car options.
	 */
	@Override
	protected Wheels createWheels() {
		Wheels wheels = new Wheels();
		CarOption wheels1 = new CarOption("winter", wheels);
		CarOption wheels2 = new CarOption("comfort", wheels);
		CarOption wheels3 = new CarOption("sports", wheels);
		try {
			wheels.addOption(wheels1);
			wheels.addOption(wheels2);
			wheels.addOption(wheels3);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return wheels;
	}
	
	/**
	 * Returns the name of the A model.
	 */
	@Override
	protected String getName() {
		return "Model A";
	}


	/**
	 * Creates an A model.
	 */
	@Override
	public CarModel createModel() {
		
		ArrayList<CarOption> options = new ArrayList<CarOption>();
		
		for (CarOption option: this.createBody().getOptionsClone()) 
			options.add(option);
		
		for (CarOption option: this.createColor().getOptionsClone()) 
			options.add(option);
		
		for (CarOption option: this.createEngine().getOptionsClone()) 
			options.add(option);
		
		for (CarOption option: this.createGearbox().getOptionsClone())
			options.add(option);
		
		for (CarOption option: this.createSeats().getOptionsClone()) 
			options.add(option);
		
		for (CarOption option: this.createAirco().getOptionsClone()) 
			options.add(option);
		
		for (CarOption option: this.createWheels().getOptionsClone())
			options.add(option);
		
		
		CarModelSpecification cms = new CarModelSpecification(options);
		return new CarModel(this.getName(), cms);
	}
	
	

}
