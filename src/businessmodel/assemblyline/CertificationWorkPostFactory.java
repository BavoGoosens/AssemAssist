package businessmodel.assemblyline;

import java.util.ArrayList;

import businessmodel.category.*;
import java.util.HashMap;

/**
 * Created by michielvandendriessche on 13/05/14.
 */
public class CertificationWorkPostFactory extends WorkPostFactory {

    @Override
    protected ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost) {
        ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
        tasks.add(new AssemblyTask("Certify Maximum Cargo Load", "add certification for maximum cargo load", new Certification(), workPost));
        return tasks;
    }

    @Override
    protected String createName() {
        return "Certification Work Post";
    }

	@Override
	protected HashMap<String, Integer> createStandardTimes() {
		HashMap<String,Integer> times = new HashMap<String,Integer>();
		times.put("Car Model A", 50);
		times.put("Car Model B", 70);
		times.put("Car Model C", 60);
		times.put("Truck Model X", 30);
		times.put("Truck Model Y", 45);
		return times;		}
}
