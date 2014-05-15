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
}
