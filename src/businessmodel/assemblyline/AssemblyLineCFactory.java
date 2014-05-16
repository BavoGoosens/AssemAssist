package businessmodel.assemblyline;

import java.util.ArrayList;

import businessmodel.MainScheduler;
import businessmodel.category.VehicleModel;

/**
 *@author Team 10
 */
public class AssemblyLineCFactory extends AssemblyLineFactory {
    @Override
    public AssemblyLine createAssemblyLine(MainScheduler scheduler) {
        AssemblyLine line = new AssemblyLine();
        line.setMainScheduler(scheduler);
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
        CargoWorkPostFactory factoryB = new CargoWorkPostFactory();
        DrivetrainWorkPostFactory factoryC = new DrivetrainWorkPostFactory();
        AccessoriesWorkPostFactory factoryD = new AccessoriesWorkPostFactory();
        CertificationWorkPostFactory factoryE = new CertificationWorkPostFactory();

        WorkPost bodyPost = factoryA.createWorkPost(line);
        WorkPost cargoPost = factoryB.createWorkPost(line);
        WorkPost drivetrainPost = factoryC.createWorkPost(line);
        WorkPost accessoriesPost = factoryD.createWorkPost(line);
        WorkPost certificationPost = factoryE.createWorkPost(line);

        posts.add(bodyPost);
        posts.add(cargoPost);
        posts.add(drivetrainPost);
        posts.add(accessoriesPost);
        posts.add(certificationPost);

        line.setWorkPosts(posts);
    }

    @Override
    protected void createResponsibleModels(AssemblyLine line) {
        ArrayList<VehicleModel> responsibleModels = new ArrayList<VehicleModel>();

        for (VehicleModel model : super.models){
            if (model.getName().contains("model A") || model.getName().contains("model B")
                    || model.getName().contains("model C") || model.getName().contains("model X")
                    || model.getName().contains("model Y"))
                responsibleModels.add(model);
        }

        line.setResponsibleModels(responsibleModels);
    }
}
