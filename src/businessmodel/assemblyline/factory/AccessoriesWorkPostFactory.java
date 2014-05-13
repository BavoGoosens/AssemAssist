package businessmodel.assemblyline.factory;

import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;
import businessmodel.category.Airco;
import businessmodel.category.Seats;
import businessmodel.category.Spoiler;
import businessmodel.category.Wheels;

import java.util.ArrayList;

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
}
