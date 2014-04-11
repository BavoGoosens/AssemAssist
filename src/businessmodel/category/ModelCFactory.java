package businessmodel.category;

import businessmodel.exceptions.IllegalCarOptionCategoryException;

public class ModelCFactory extends CarModelFactory {

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

	@Override
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

	@Override
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
	
	@Override
	protected String getName() {
		return "Model C";
	}

}
