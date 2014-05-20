package businessmodel.assemblyline;

import java.util.ArrayList;

import businessmodel.Catalog;
import businessmodel.MainScheduler;
import businessmodel.category.VehicleModel;

/**
 * Factory for the AssemblyLines
 * 
 * @author Team 10
 * 
 */
public abstract class AssemblyLineFactory {

	/**
	 * 
	 */
    protected ArrayList<VehicleModel> models = new Catalog().getAvailaleModelsClone();

    /**
     * 
     * @param scheduler
     * @return
     */
    public abstract AssemblyLine createAssemblyLine(MainScheduler scheduler);

    /**
     * 
     * @param line
     */
    protected abstract void createScheduler(AssemblyLine line);

    /**
     * 
     * @param line
     */
    protected abstract void createWorkPosts(AssemblyLine line);

    /**
     * 
     * @param line
     */
    protected abstract void createResponsibleModels(AssemblyLine line);

}
