package businessmodel.category;

import java.util.ArrayList;

import org.joda.time.Period;

import businessmodel.exceptions.IllegalVehicleOptionCategoryException;

public class ModelXFactory extends VehicleModelFactory {

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
		
		for (VehicleOption option: this.createProtection().getOptionsClone())
			options.add(option);
		
		for (VehicleOption option: this.createStorage().getOptionsClone())
			options.add(option);
		
		for (VehicleOption option: this.createCertification().getOptionsClone())
			options.add(option);
		
		VehicleModelSpecification cms = new VehicleModelSpecification(options);
		return new VehicleModel(this.getName(), cms, this.getStandardTimeToFinish());
	}

	@Override
	protected Body createBody() {
		Body body = new Body();
		VehicleOption body1 = new VehicleOption("platform", body);
		VehicleOption body2 = new VehicleOption("closed", body);
		try {
			body.addOption(body1);
			body.addOption(body2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return body;
	}

	@Override
	protected Color createColor() {
		Color color = new Color();
		VehicleOption color1 = new VehicleOption("green", color);
		VehicleOption color2 = new VehicleOption("white", color);
		try {
			color.addOption(color1);
			color.addOption(color2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return color;
	}

	@Override
	protected Engine createEngine() {
		Engine engine = new Engine();
		VehicleOption engine1 = new VehicleOption("standard", engine);
		VehicleOption engine2 = new VehicleOption("hybrid", engine);
		try {
			engine.addOption(engine1);
			engine.addOption(engine2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return engine;
	}

	@Override
	protected Gearbox createGearbox() {
		Gearbox gearbox = new Gearbox();
		VehicleOption gearbox1 = new VehicleOption("8 speed manual", gearbox);
		VehicleOption gearbox2 = new VehicleOption("automatic", gearbox);
		try {
			gearbox.addOption(gearbox1);
			gearbox.addOption(gearbox2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return gearbox;
	}

	@Override
	protected Seats createSeats() {
		Seats seats = new Seats();
		VehicleOption seats1 = new VehicleOption("vinyl grey", seats);
		VehicleOption seats2 = new VehicleOption("vinyl black", seats);
		try {
			seats.addOption(seats1);
			seats.addOption(seats2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return seats;
	}
	
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

	@Override
	protected Wheels createWheels() {
		Wheels wheels = new Wheels();
		VehicleOption wheels1 = new VehicleOption("standard", wheels);
		VehicleOption wheels2 = new VehicleOption("heavy-duty", wheels);
		try {
			wheels.addOption(wheels1);
			wheels.addOption(wheels2);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return wheels;
	}
	
	protected Protection createProtection() {
		Protection protection = new Protection();
		VehicleOption protection1 = new VehicleOption("cargo protection", protection);
		try {
			protection.addOption(protection1);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return protection;
	}
	
	protected Storage createStorage() {
		Storage storage = new Storage();
		VehicleOption storage1 = new VehicleOption("tool storage", storage);
		try {
			storage.addOption(storage1);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return storage;
	}
	
	protected Certification createCertification() {
		Certification certification = new Certification();
		VehicleOption certification1 = new VehicleOption("maximum cargo load certification", certification);
		try {
			certification.addOption(certification1);
		} catch (IllegalVehicleOptionCategoryException e) {
			System.out.println(e.getMessage());
		}
		return certification;
	}

	@Override
	protected String getName() {
		return "Truck Model X";
	}

	@Override
	protected Period getStandardTimeToFinish() {
        Period period = new Period();
        period = period.withMinutes(30);
        period = period.withHours(2);
        return period;
	}

}
