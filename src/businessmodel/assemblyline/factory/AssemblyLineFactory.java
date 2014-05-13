package businessmodel.assemblyline.factory;

import businessmodel.Catalog;
import businessmodel.MainScheduler;
import businessmodel.category.VehicleModel;
import businessmodel.assemblyline.AssemblyLine;
import businessmodel.assemblyline.WorkPost;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Team 10
 */
public abstract class AssemblyLineFactory {

    protected ArrayList<VehicleModel> models = new Catalog().getAvailaleModelsClone();

    public abstract AssemblyLine createAssemblyLine();

    protected abstract MainScheduler createScheduler();

    protected abstract List<WorkPost> createWorkPosts();

    protected abstract List<VehicleModel> createResponsibleModels();

}
