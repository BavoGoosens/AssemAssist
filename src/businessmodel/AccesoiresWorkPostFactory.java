package businessmodel;

import businessmodel.category.Airco;
import businessmodel.category.Seats;
import businessmodel.category.Spoiler;
import businessmodel.category.Wheels;


public class AccesoiresWorkPostFactory extends WorkPostFactory{


	@Override
	protected AssemblyTask createSeatsTask() {
		return new AssemblyTask("Install Seats", new Seats());
	}

	@Override
	protected AssemblyTask createAircoTask() {
		return new AssemblyTask("Install Airco", new Airco());
	}

	@Override
	protected AssemblyTask createWheelsTask() {
		return new AssemblyTask("Mount Wheels", new Wheels());
	}

	@Override
	protected AssemblyTask createSpoilerTask() {
		return new AssemblyTask("Install Spoiler", new Spoiler());
	}

	@Override
	protected String getName() {
		return "Accesoires Workpost";
	}

}
