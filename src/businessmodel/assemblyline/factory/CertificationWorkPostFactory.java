package businessmodel.assemblyline.factory;

import businessmodel.assemblyline.AssemblyTask;
import businessmodel.assemblyline.WorkPost;

import java.util.ArrayList;

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
}
