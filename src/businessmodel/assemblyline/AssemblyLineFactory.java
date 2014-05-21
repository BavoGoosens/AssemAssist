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
	 * Get clone of the available Vehicle Models.
	 */
    protected ArrayList<VehicleModel> models = new Catalog().getAvailaleModelsClone();

    /**
     * Create an AssemblyLine with the given MainScheduler.
     * @param scheduler
     * @return AssemblyLine
     */
    public abstract AssemblyLine createAssemblyLine(MainScheduler scheduler);

    /**
     * Create the Scheduler for the given AssemblyLine.
     * @param line
     */
    protected abstract void createScheduler(AssemblyLine line);

    /**
     * Create the WorkPosts for the given AssemblyLine.
     * @param line
     */
    protected abstract void createWorkPosts(AssemblyLine line);

    /**
     * Create the VehicleModels for which the given AssemblyLine is responsible.
     * @param line
     */
    protected abstract void createResponsibleModels(AssemblyLine line);

}
