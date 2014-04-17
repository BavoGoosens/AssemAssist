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

	protected abstract Body createBody();

	protected abstract Color createColor();

	protected abstract Engine createEngine();

	protected abstract Gearbox createGearbox();

	protected abstract Seats createSeats();

	protected abstract Airco createAirco();

	protected abstract Wheels createWheels();

	protected abstract Spoiler createSpoiler();

	protected abstract String getName();


}
