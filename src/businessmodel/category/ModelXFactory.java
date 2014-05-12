package businessmodel.category;

import java.util.ArrayList;

import org.joda.time.Period;

import businessmodel.CarModel;
import businessmodel.CarModelSpecification;
import businessmodel.exceptions.IllegalCarOptionCategoryException;

public class ModelXFactory extends CarModelFactory {

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
		return new CarModel(this.getName(), cms, this.getStandardTimeToFinish());
	}

	@Override
	protected Body createBody() {
		Body body = new Body();
		CarOption body1 = new CarOption("platform", body);
		CarOption body2 = new CarOption("closed", body);
		try {
			body.addOption(body1);
			body.addOption(body2);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return body;
	}

	@Override
	protected Color createColor() {
		Color color = new Color();
		CarOption color1 = new CarOption("green", color);
		CarOption color2 = new CarOption("white", color);
		try {
			color.addOption(color1);
			color.addOption(color2);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return color;
	}

	@Override
	protected Engine createEngine() {
		Engine engine = new Engine();
		CarOption engine1 = new CarOption("standard", engine);
		CarOption engine2 = new CarOption("hybrid", engine);
		try {
			engine.addOption(engine1);
			engine.addOption(engine2);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return engine;
	}

	@Override
	protected Gearbox createGearbox() {
		Gearbox gearbox = new Gearbox();
		CarOption gearbox1 = new CarOption("8 speed manual", gearbox);
		CarOption gearbox2 = new CarOption("automatic", gearbox);
		try {
			gearbox.addOption(gearbox1);
			gearbox.addOption(gearbox2);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return gearbox;
	}

	@Override
	protected Seats createSeats() {
		Seats seats = new Seats();
		CarOption seats1 = new CarOption("vinyl grey", seats);
		CarOption seats2 = new CarOption("vinyl black", seats);
		try {
			seats.addOption(seats1);
			seats.addOption(seats2);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return seats;
	}
	
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

	@Override
	protected Wheels createWheels() {
		Wheels wheels = new Wheels();
		CarOption wheels1 = new CarOption("standard", wheels);
		CarOption wheels2 = new CarOption("heavy-duty", wheels);
		try {
			wheels.addOption(wheels1);
			wheels.addOption(wheels2);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return wheels;
	}

	@Override
	protected String getName() {
		return "Model X";
	}

	@Override
	protected Period getStandardTimeToFinish() {
		// TODO Auto-generated method stub
		return null;
	}

}
