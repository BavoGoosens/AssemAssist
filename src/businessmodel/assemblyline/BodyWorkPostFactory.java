package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.HashMap;

import businessmodel.category.Body;
import businessmodel.category.Color;

/**
 * Created by michielvandendriessche on 12/05/14.
 */
public class BodyWorkPostFactory extends WorkPostFactory {

	@Override
	protected ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost) {
		ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
		tasks.add(new AssemblyTask("Assembly Vehicle Body", "Place the body on the vehicle", new Body(), workPost));
		tasks.add(new AssemblyTask("Paint Vehicle","Paint the vehicle", new Color(), workPost));
		return tasks;
	}

	@Override
	protected String createName() {
		return "Body Work Post";
	}

	@Override
	protected HashMap<String, Integer> createStandardTimes() {
		HashMap<String,Integer> times = new HashMap<String,Integer>();
		times.put("Vehicle model A", 50);
		times.put("Vehicle model B", 70);
		times.put("Vehicle model C", 60);
		times.put("Vehicle model X", 90);
		times.put("Vehicle model Y", 120);
		return times;	
	}
}
