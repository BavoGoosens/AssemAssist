package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by michielvandendriessche on 12/05/14.
 */
public abstract class WorkPostFactory {
    protected WorkPost createWorkPost(AssemblyLine assemblyLine) {
        WorkPost workPost = new WorkPost(this.createName(), assemblyLine);
        workPost.setResponsibleTasks(this.createAssemblyTasks(workPost));
        workPost.setStandardtimes(this.createStandardTimes());
        return workPost;
    }
	protected abstract ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost);
    protected abstract HashMap<String, Integer> createStandardTimes();
    protected abstract String createName();
}
