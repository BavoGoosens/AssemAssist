package businessmodel.assemblyline;

import businessmodel.category.Airco;
import businessmodel.category.Seats;
import businessmodel.category.Spoiler;
import businessmodel.category.Wheels;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The factory for creating the AccessioresWorkPost.
 *
 * @author SWOP team 10
 */
public class AccessoriesWorkPostFactory extends WorkPostFactory {

    @Override
    protected ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost) {
        ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
        tasks.add(new AssemblyTask("Install Seats", "insert seats into the car", new Seats(), workPost, true));
        tasks.add(new AssemblyTask("Install Airco", "insert airco into the car", new Airco(), workPost, false));
        tasks.add(new AssemblyTask("Mount Wheels", "mount wheels on the car", new Wheels(), workPost, false));
        tasks.add(new AssemblyTask("Install Spoiler", "install spoiler on the car", new Spoiler(), workPost, false));
        return tasks;
    }

    @Override
    protected String createName() {
        return "Accessories Work Post";
    }

    @Override
    protected HashMap<String, Integer> createStandardTimes() {
        HashMap<String, Integer> times = new HashMap<String, Integer>();
        times.put("Car Model A", 50);
        times.put("Car Model B", 70);
        times.put("Car Model C", 60);
        times.put("Truck Model X", 60);
        times.put("Truck Model Y", 60);
        return times;
    }
}
