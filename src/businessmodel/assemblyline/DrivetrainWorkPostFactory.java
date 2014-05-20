package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.HashMap;

import businessmodel.category.Engine;
import businessmodel.category.Gearbox;

/**
 * Created by michielvandendriessche on 13/05/14.
 */
public class DrivetrainWorkPostFactory extends WorkPostFactory {

    @Override
    protected ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost) {
        ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
        tasks.add(new AssemblyTask("Insert Engine", "insert engine into the car", new Engine(),workPost, false));
        tasks.add(new AssemblyTask("Insert Gearbox","insert gearbox into the car", new Gearbox(),workPost, false));
        return tasks;
    }

    @Override
    protected String createName() {
        return "Drivetrain Work Post";
    }

	@Override
	protected HashMap<String, Integer> createStandardTimes() {
		HashMap<String,Integer> times = new HashMap<String,Integer>();
		times.put("Car Model A", 50);
		times.put("Car Model B", 70);
		times.put("Car Model C", 60);
		times.put("Truck Model X", 60);
		times.put("Truck Model Y", 60);
		return times;
	}
}
