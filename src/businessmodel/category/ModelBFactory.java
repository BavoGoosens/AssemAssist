package businessmodel.category;

import businessmodel.exceptions.IllegalCarOptionCategoryException;

public class ModelBFactory extends CarModelFactory {

	@Override
	protected Body createBody() {
		Body body = new Body();
		CarOption body1 = new CarOption("sedan", body);
		CarOption body2 = new CarOption("break", body);
		CarOption body3 = new CarOption("sport", body);
		try {
			body.addOption(body1);
			body.addOption(body2);
			body.addOption(body3);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return body;
	}

	@Override
	protected Color createColor() {
		Color color = new Color();
		CarOption color1 = new CarOption("red", color);
		CarOption color2 = new CarOption("blue", color);
		CarOption color3 = new CarOption("green", color);
		CarOption color4 = new CarOption("yellow", color);
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

	@Override
	protected Engine createEngine() {
		Engine engine = new Engine();
		CarOption engine1 = new CarOption("standard 2l v4", engine);
		CarOption engine2 = new CarOption("performance 2.5l v6", engine);
		CarOption engine3 = new CarOption("ultra 3l v8", engine);
		try {
			engine.addOption(engine1);
			engine.addOption(engine2);
			engine.addOption(engine3);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return engine;
	}

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

	@Override
	protected Spoiler createSpoiler() {
		Spoiler spoiler = new Spoiler();
		CarOption spoiler1 = new CarOption("low", spoiler);
		try {
			spoiler.addOption(spoiler1);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return spoiler;
	}
	
	@Override
	protected String getName() {
		return "Model B";
	}

}