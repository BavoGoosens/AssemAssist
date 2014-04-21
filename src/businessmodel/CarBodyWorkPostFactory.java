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
	protected String getName() {
		return "Car Body Workpost";
	}

}
