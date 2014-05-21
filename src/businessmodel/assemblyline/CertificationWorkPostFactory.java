package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.HashMap;

import businessmodel.category.Certification;

/**
 * The factory for creating the CertificationWorkPost.
 * 
 * @author 	SWOP team 10
 *
 */
public class CertificationWorkPostFactory extends WorkPostFactory {

    @Override
    protected ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost) {
        ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
        tasks.add(new AssemblyTask("Certify Maximum Cargo Load", "add certification for maximum cargo load", new Certification(), workPost, false));
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
