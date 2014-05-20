package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.HashMap;

import businessmodel.category.Body;
import businessmodel.category.Color;

/**
 * The factory for creating the BodyWorkPost.
 * 
 * @author 	SWOP team 10
 *
 */
public class BodyWorkPostFactory extends WorkPostFactory {

	@Override
	protected ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost) {
		ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
		tasks.add(new AssemblyTask("Assembly Vehicle Body", "Place the body on the vehicle", new Body(), workPost, false));
		tasks.add(new AssemblyTask("Paint Vehicle","Paint the vehicle", new Color(), workPost, true));
		return tasks;
	}

	@Override
	protected String createName() {
		return "Body Work Post";
	}

	@Override
	protected HashMap<String, Integer> createStandardTimes() {
		HashMap<String,Integer> times = new HashMap<String,Integer>();
		times.put("Car Model A", 50);
		times.put("Car Model B", 70);
		times.put("Car Model C", 60);
		times.put("Truck Model X", 90);
		times.put("Truck Model Y", 120);
		return times;	
	}
}
