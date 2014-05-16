package businessmodel.assemblyline;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.MainScheduler;
import businessmodel.category.VehicleModel;

/**
 * @author Team 10
 */
public abstract class AssemblyLineFactory {

    protected ArrayList<VehicleModel> models = new Catalog().getAvailaleModelsClone();

    public abstract AssemblyLine createAssemblyLine(MainScheduler scheduler);

    protected abstract void createScheduler(AssemblyLine line);

    protected abstract void createWorkPosts(AssemblyLine line);

    protected abstract void createResponsibleModels(AssemblyLine line);

}
