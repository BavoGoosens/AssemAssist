package businessmodel.assemblyline.factory;

import businessmodel.category.VehicleModel;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.AssemblyLineScheduler;
import businessmodel.assemblyline.WorkPost;

import java.util.List;

/**
 * This concrete factory can be used to create AssemblyLine 1 and 2 since they are essentially the same.
 *
 * @author Team 10
 */
public class AssemblyLineAFactory extends AssemblyLineFactory {

    @Override
    public AssemblyLine createAssemblyLine() {
        List<WorkPost> posts = this.createWorkPosts();
        List<VehicleModel> models = this.createResponsibleModels();
        AssemblyLine line = AssemblyLine(posts, models);
        AssemblyLineScheduler scheduler = this.createScheduler(line);
        AssemblyLine.setScheduler(scheduler);
        return line;
    }

    @Override
    protected AssemblyLineScheduler createScheduler(AssemblyLine assemblyline) {
        return new AssemblyLineScheduler(assemblyline);
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
