package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by michielvandendriessche on 13/05/14.
 */
public class CertificationWorkPostFactory extends WorkPostFactory {

    @Override
    protected ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost) {
        ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
        tasks.add(new AssemblyTask("Certify Maximum Cargo Load", "add certification for maximum cargo load", workPost));
        return tasks;
    }

    @Override
    protected String createName() {
        return "Certification Work Post";
    }

	@Override
	protected HashMap<String, Integer> createStandardTimes() {
		HashMap<String,Integer> times = new HashMap<String,Integer>();
		times.put("Vehicle Model A", 50);
		times.put("Vehicle Model B", 70);
		times.put("Vehicle Model C", 60);
		times.put("Vehicle Model X", 30);
		times.put("Vehicle Model Y", 45);
		return times;		}
}
