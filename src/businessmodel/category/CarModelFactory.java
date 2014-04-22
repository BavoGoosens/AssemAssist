package businessmodel.category;

import java.util.ArrayList;

import businessmodel.CarModel;
import businessmodel.CarModelSpecification;

public abstract class CarModelFactory {

	public CarModelFactory() {}

	public abstract CarModel createModel();

	protected abstract Body createBody();
	protected abstract Color createColor();
	protected abstract Engine createEngine();
	protected abstract Gearbox createGearbox();
	protected abstract Seats createSeats();
	protected abstract Wheels createWheels();
	protected abstract String getName();

}
