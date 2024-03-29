package businessmodel.assemblyline;

import businessmodel.MainScheduler;
import businessmodel.category.VehicleModel;

import java.util.ArrayList;

/**
 * This concrete factory can be used to create AssemblyLine 2.
 *
 * @author Team 10
 */
public class AssemblyLineBFactory extends AssemblyLineFactory {

    @Override
    public AssemblyLine createAssemblyLine(MainScheduler scheduler) {
        AssemblyLine line = new AssemblyLine();
        line.setMainScheduler(scheduler);
        line.setName("B");
        this.createWorkPosts(line);
        this.createResponsibleModels(line);
        this.createScheduler(line);
        line.setState(line.getOperationalState());
        return line;
    }

    @Override
    protected void createScheduler(AssemblyLine line) {
        AssemblyLineScheduler scheduler = new AssemblyLineScheduler(line);
        line.setAssemblylineScheduler(scheduler);
    }

    @Override
    protected void createWorkPosts(AssemblyLine line) {
        ArrayList<WorkPost> posts = new ArrayList<WorkPost>();

        BodyWorkPostFactory factoryA = new BodyWorkPostFactory();
        DrivetrainWorkPostFactory factoryB = new DrivetrainWorkPostFactory();
        AccessoriesWorkPostFactory factoryC = new AccessoriesWorkPostFactory();

        WorkPost bodyPost = factoryA.createWorkPost(line);
        WorkPost drivetrainPost = factoryB.createWorkPost(line);
        WorkPost accessoriesPost = factoryC.createWorkPost(line);

        posts.add(bodyPost);
        posts.add(drivetrainPost);
        posts.add(accessoriesPost);

        line.setWorkPosts(posts);
    }

    @Override
    protected void createResponsibleModels(AssemblyLine line) {
        ArrayList<VehicleModel> responsibleModels = new ArrayList<VehicleModel>();

        for (VehicleModel model : super.models) {
            if (model.getName().contains("Model A") || model.getName().contains("Model B")
                    || model.getName().contains("Model C"))
                responsibleModels.add(model);
        }

        line.setResponsibleModels(responsibleModels);
    }
}
