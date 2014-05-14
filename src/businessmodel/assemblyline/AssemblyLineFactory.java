package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.List;

import businessmodel.Catalog;
import businessmodel.category.VehicleModel;

/**
 * @author Team 10
 */
public abstract class AssemblyLineFactory {

    protected ArrayList<VehicleModel> models = new Catalog().getAvailaleModelsClone();

    public abstract AssemblyLine createAssemblyLine();

    protected abstract AssemblyLineScheduler createScheduler();

    protected abstract List<WorkPost> createWorkPosts();

    protected abstract List<VehicleModel> createResponsibleModels();

}
