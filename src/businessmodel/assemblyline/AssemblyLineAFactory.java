package businessmodel.assemblyline;

import java.util.ArrayList;

import businessmodel.category.VehicleModel;

/**
 * This concrete factory can be used to create AssemblyLine 1 and 2 since they are essentially the same.
 *
 * @author Team 10
 */
public class AssemblyLineAFactory extends AssemblyLineFactory {

    @Override
    public AssemblyLine createAssemblyLine() {
        AssemblyLine line = new AssemblyLine();
        this.createWorkPosts(line);
        this.createResponsibleModels(line);
        this.createScheduler(line);
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
    //TODO: debuggen om te checken of dit juist gebeurt of werken met een techniek.
    protected void createResponsibleModels(AssemblyLine line) {
        ArrayList<VehicleModel> responsibleModels = new ArrayList<VehicleModel>();

        for (VehicleModel model : super.models){
            if (model.getName().contains("Model A") || model.getName().contains("Model B"))
                responsibleModels.add(model);
        }

        line.setResponsibleModels(responsibleModels);
    }
}
