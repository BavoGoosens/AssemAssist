package businessmodel;

import java.util.ArrayList;

import businessmodel.exceptions.IllegalCarOptionCategoryException;

/**
 * A class that represents an inventory for a factory. Here we hold all the different component.
 * 
 * @author SWOP team 10 2014
 *
 */
public class Inventory {

	private CarOptionCategory body;
	private CarOptionCategory color;
	private CarOptionCategory engine;
	private CarOptionCategory gearbox;
	private CarOptionCategory seats;
	private CarOptionCategory airco;
	private CarOptionCategory wheels;
	private CarOptionCategory spoiler;

	/**
	 * A Constructor that creates a new inventory list.
	 */
	public Inventory() {
		this.body = new CarOptionCategory("body");
		this.color = new CarOptionCategory("color");
		this.engine = new CarOptionCategory("engine");
		this.gearbox = new CarOptionCategory("gearbox");
		this.seats = new CarOptionCategory("seats");
		this.airco = new CarOptionCategory("airco");
		this.wheels = new CarOptionCategory("wheels");
		this.spoiler = new CarOptionCategory("spoiler");
		
		CarOption body1 = new CarOption("sedan", this.body);
		CarOption body2 = new CarOption("break", this.body);
		CarOption body3 = new CarOption("sport", this.body);
		
		try {
			this.body.addPossibleOption(body1);
			this.body.addPossibleOption(body2);
			this.body.addPossibleOption(body3);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		
		CarOption color1 = new CarOption("black", this.color);
		CarOption color2 = new CarOption("white", this.color);
		CarOption color3 = new CarOption("yellow", this.color);
		CarOption color4 = new CarOption("green", this.color);
		CarOption color5 = new CarOption("red", this.color);
		CarOption color6 = new CarOption("blue", this.color);
		
		try {
			this.color.addPossibleOption(color1);
			this.color.addPossibleOption(color2);
			this.color.addPossibleOption(color3);
			this.color.addPossibleOption(color4);
			this.color.addPossibleOption(color5);
			this.color.addPossibleOption(color6);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		
		CarOption engine1 = new CarOption("standard 2l v4", this.engine);
		CarOption engine2 = new CarOption("performance 2.5l v6", this.engine);
		CarOption engine3 = new CarOption("ultra 3l v8", this.engine);
		
		try {
			this.engine.addPossibleOption(engine1);
			this.engine.addPossibleOption(engine2);
			this.engine.addPossibleOption(engine3);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		
		CarOption gearbox1 = new CarOption("6 speed manual", this.gearbox);
		CarOption gearbox2 = new CarOption("5 speed manual", this.gearbox);
		CarOption gearbox3 = new CarOption("5 speed automatic", this.gearbox);
		
		try {
			this.gearbox.addPossibleOption(gearbox1);
			this.gearbox.addPossibleOption(gearbox2);
			this.gearbox.addPossibleOption(gearbox3);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		
		CarOption seats1 = new CarOption("leather black", this.seats);
		CarOption seats2 = new CarOption("leather white", this.seats);
		CarOption seats3 = new CarOption("vinyl grey", this.seats);
		
		try {
			this.seats.addPossibleOption(seats1);
			this.seats.addPossibleOption(seats2);
			this.seats.addPossibleOption(seats3);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		
		CarOption airco1 = new CarOption("manual", this.airco);
		CarOption airco2 = new CarOption("automatic", this.airco);
		
		try {
			this.airco.addPossibleOption(airco1);
			this.airco.addPossibleOption(airco2);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		
		CarOption wheels1 = new CarOption("winter", this.wheels);
		CarOption wheels2 = new CarOption("comfort", this.wheels);
		CarOption wheels3 = new CarOption("sports", this.wheels);
		
		try {
			this.wheels.addPossibleOption(wheels1);
			this.wheels.addPossibleOption(wheels2);
			this.wheels.addPossibleOption(wheels3);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		
		CarOption spoiler1 = new CarOption("low", this.spoiler);
		CarOption spoiler2 = new CarOption("high", this.spoiler);
		
		try {
			this.spoiler.addPossibleOption(spoiler1);
			this.spoiler.addPossibleOption(spoiler2);
		} catch (IllegalCarOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<CarOptionCategory> getAllCategories() {
		ArrayList<CarOptionCategory> categories = new ArrayList<CarOptionCategory>();
		categories.add(this.body);
		categories.add(this.color);
		categories.add(this.engine);
		categories.add(this.gearbox);
		categories.add(this.seats);
		categories.add(this.airco);
		categories.add(this.wheels);
		categories.add(this.spoiler);
		return categories;
	}
	
	public CarOptionCategory getBody() {
		return this.body;
	}
	
	public CarOptionCategory getColor() {
		return this.color;
	}
	
	public CarOptionCategory getEngine() {
		return this.engine;
	}
	
	public CarOptionCategory getGearbox() {
		return this.gearbox;
	}
	
	public CarOptionCategory getSeats() {
		return this.seats;
	}
	
	public CarOptionCategory getAirco() {
		return this.airco;
	}
	
	public CarOptionCategory getWheels() {
		return this.wheels;
	}
	
	public CarOptionCategory getSpoiler() {
		return this.spoiler;
	}
}
