package businessmodel;

import businessmodel.category.Engine;
import businessmodel.category.Gearbox;


public class DrivetrainWorkPostFactory extends WorkPostFactory {

	
	@Override
	protected AssemblyTask createEngineTask() {
		return new AssemblyTask("Insert Engine", new Engine());
	}

	@Override
	protected AssemblyTask createGearboxTask() {
		return new AssemblyTask("Insert Gearbox", new Gearbox());
	}

	@Override
	protected String getName() {
		return "Drivetrain Workpost";
	}

}
