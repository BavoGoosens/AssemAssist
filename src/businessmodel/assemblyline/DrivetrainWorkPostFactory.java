package businessmodel.assemblyline;

import java.util.ArrayList;

import businessmodel.category.Engine;
import businessmodel.category.Gearbox;

/**
 * Created by michielvandendriessche on 13/05/14.
 */
public class DrivetrainWorkPostFactory extends WorkPostFactory {

    @Override
    protected ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost) {
        ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
        tasks.add(new AssemblyTask("Insert Engine", "insert engine into the car", new Engine(),workPost));
        tasks.add(new AssemblyTask("Insert Gearbox","insert gearbox into the car", new Gearbox(),workPost));
        return tasks;
    }

    @Override
    protected String createName() {
        return "Drivetrain Work Post";
    }
}
