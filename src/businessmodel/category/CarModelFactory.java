package businessmodel.category;

import java.util.ArrayList;

import businessmodel.CarModel;
import businessmodel.CarModelSpecification;

public abstract class CarModelFactory {

	public CarModelFactory() {}

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
		return new CarModel(this.getName(), cms);

	}

	protected Body createBody() {
		return new Body();
	}

	protected Color createColor() {
		return new Color();
	}

	protected Engine createEngine() {
		return new Engine();
	}

	protected Gearbox createGearbox() {
		return new Gearbox();
	}

	protected Seats createSeats() {
		return new Seats();
	}

	protected Airco createAirco() {
		return new Airco();
	}

	protected Wheels createWheels() {
		return new Wheels();
	}

	protected Spoiler createSpoiler() {
		return new Spoiler();
	}

	protected abstract String getName();


}
