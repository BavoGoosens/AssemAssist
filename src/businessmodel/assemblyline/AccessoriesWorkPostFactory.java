package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.HashMap;

import businessmodel.category.Airco;
import businessmodel.category.Seats;
import businessmodel.category.Spoiler;
import businessmodel.category.Wheels;

/**
 * Created by michielvandendriessche on 13/05/14.
 */
public class AccessoriesWorkPostFactory extends WorkPostFactory {

    @Override
    protected ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost) {
        ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
        tasks.add(new AssemblyTask("Install Seats", "insert seats into the car", new Seats(),workPost));
        tasks.add(new AssemblyTask("Install Airco", "insert airco into the car", new Airco(),workPost));
        tasks.add(new AssemblyTask("Mount Wheels", "mount wheels on the car", new Wheels(),workPost));
        tasks.add(new AssemblyTask("Install Spoiler","install spoiler on the car", new Spoiler(),workPost));
        return tasks;
    }

    @Override
    protected String createName() {
        return "Accessories Work Post";
    }

	@Override
	protected HashMap<String, Integer> createStandardTimes() {
		HashMap<String,Integer> times = new HashMap<String,Integer>();
		times.put("Vehicle model A", 50);
		times.put("Vehicle model B", 70);
		times.put("Vehicle model C", 60);
		times.put("Vehicle model X", 60);
		times.put("Vehicle model Y", 60);
		return times;	
	}
}
