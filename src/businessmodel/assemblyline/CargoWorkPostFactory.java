package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by michielvandendriessche on 13/05/14.
 */
public class CargoWorkPostFactory extends WorkPostFactory {

    @Override
    protected ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost) {
        ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
        tasks.add(new AssemblyTask("Install Tool Storage", "install tool storage into the truck", workPost));
        tasks.add(new AssemblyTask("Add Cargo Protection", "add cargo protection to the truck", workPost));
        return tasks;
    }

    @Override
    protected String createName() {
        return "Cargo Work Post";
    }

	@Override
	protected HashMap<String, Integer> createStandardTimes() {
		HashMap<String,Integer> times = new HashMap<String,Integer>();
		times.put("Vehicle Model A", 50);
		times.put("Vehicle Model B", 70);
		times.put("Vehicle Model C", 60);
		times.put("Vehicle Model X", 60);
		times.put("Vehicle Model Y", 60);
		return times;	
	}
}
