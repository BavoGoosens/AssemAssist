package businessmodel;

import businessmodel.category.Engine;
import businessmodel.category.Gearbox;


public class DrivetrainWorkPostFactory extends WorkPostFactory {

	@Override
	protected AssemblyTask createBodyTask() {
		return null;
	}

	@Override
	protected AssemblyTask createColorTask() {
		return null;
	}

	@Override
	protected AssemblyTask createEngineTask() {
		return new AssemblyTask("Insert Engine", new Engine());
	}

	@Override
	protected AssemblyTask createGearboxTask() {
		return new AssemblyTask("Insert Gearbox", new Gearbox());
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
		return "Drivetrain Workpost";
	}

}
