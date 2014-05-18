package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.HashMap;

import businessmodel.category.CargoProtection;
import businessmodel.category.ToolStorage;

/**
 * Created by michielvandendriessche on 13/05/14.
 */
public class CargoWorkPostFactory extends WorkPostFactory {

    @Override
    protected ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost) {
        ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
        tasks.add(new AssemblyTask("Install Tool Storage", "install tool storage into the truck", new ToolStorage(), workPost));
        tasks.add(new AssemblyTask("Add Cargo Protection", "add cargo protection to the truck", new CargoProtection(), workPost));
        return tasks;
    }

    @Override
    protected String createName() {
        return "Cargo Work Post";
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
