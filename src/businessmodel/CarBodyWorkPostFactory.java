package businessmodel;

import businessmodel.category.Body;
import businessmodel.category.Color;


public class CarBodyWorkPostFactory extends WorkPostFactory {

	@Override
	protected AssemblyTask createBodyTask() {
		return new AssemblyTask("Assembly Car Body", new Body());
	}

	@Override
	protected AssemblyTask createColorTask() {
		return new AssemblyTask("Paint Car", new Color());
	}

	@Override
	protected AssemblyTask createEngineTask() {
		return null;
	}

	@Override
	protected AssemblyTask createGearboxTask() {
		return null;
	}

	@Override
	protected AssemblyTask createSeatsTask() {
		return null;
	}

	@Override
	protected AssemblyTask createAircoTask() {
		return null;
	}

	@Override
	protected AssemblyTask createWheelsTask() {
		return null;
	}

	@Override
	protected AssemblyTask createSpoilerTask() {
		return null;
	}

	@Override
	protected String getName() {
		return "Car Body Workpost";
	}

}
