package businessmodel.assemblyline;

import businessmodel.category.Body;
import businessmodel.category.Color;

import java.util.ArrayList;

/**
 * Created by michielvandendriessche on 12/05/14.
 */
public class BodyWorkPostFactory extends WorkPostFactory {

    @Override
    protected ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost) {
        ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
        tasks.add(new AssemblyTask("Assembly Car Body", "Place the body on the car", new Body(), workPost));
        tasks.add(new AssemblyTask("Paint Car","Paint the car", new Color(), workPost));
        return tasks;
    }

    @Override
    protected String createName() {
        return "Body Work Post";
    }
}
