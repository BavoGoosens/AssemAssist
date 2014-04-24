package businessmodel.category;

import org.joda.time.Period;

import businessmodel.CarModel;

/**
 * Abstract class representing a car model factory.
 * This factory creates car model objects.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public abstract class CarModelFactory {

	/**
	 * Creates a new car model factory.
	 */
	public CarModelFactory() {}

	/**
	 * Creates a new car model with the necessary car options.
	 */
	public abstract CarModel createModel();

	/**
	 * Creates the body category of the model with the necessary car options.
	 */
	protected abstract Body createBody();

	/**
	 * Creates the color category of the model with the necessary car options.
	 */
	protected abstract Color createColor();

	/**
	 * Creates the engine category of the model with the necessary car options.
	 */
	protected abstract Engine createEngine();

	/**
	 * Creates the gear box category of the model with the necessary car options.
	 */
	protected abstract Gearbox createGearbox();

	/**
	 * Creates the seats category of the model with the necessary car options.
	 */
	protected abstract Seats createSeats();

	/**
	 * Creates the wheels category of the model with the necessary car options.
	 */
	protected abstract Wheels createWheels();

	/**
	 * Returns the name of the car model factory.
	 * 
	 * @return	The name of the car model factory.
	 */
	protected abstract String getName();

	protected abstract Period getStandardTimeToFinish();

}