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

    protected abstract void createScheduler(AssemblyLine line);

    protected abstract void createWorkPosts(AssemblyLine line);

    protected abstract void createResponsibleModels(AssemblyLine line);

}
