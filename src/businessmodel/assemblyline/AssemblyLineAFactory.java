package businessmodel.assemblyline;

import java.util.List;

import businessmodel.category.VehicleModel;

/**
 * This concrete factory can be used to create AssemblyLine 1 and 2 since they are essentially the same.
 *
 * @author Team 10
 */
public class AssemblyLineAFactory extends AssemblyLineFactory {

	@Override
	public AssemblyLine createAssemblyLine() {
		return null;
	}

	@Override
	protected AssemblyLineScheduler createScheduler() {
		return null;
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
