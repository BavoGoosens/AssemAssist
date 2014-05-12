package businessmodel.scheduler;

import businessmodel.VehicleModel;

import java.util.List;

/**
 * This concrete factory can be used to create AssemblyLine 3.
 *
 * @author Team 10
 */
public class AssemblyLineBFactory extends AssemblyLineFactory {

    @Override
    public AssemblyLine createAssemblyLine() {
        return null;
    }

    @Override
    protected AssemblyLineScheduler createScheduler() {
        return null;
    }

    @Override
    protected List<WorkPost> createWorkPosts() {
        return null;
    }

    @Override
    protected List<VehicleModel> createResponsibleModels() {
        return null;
    }
}
