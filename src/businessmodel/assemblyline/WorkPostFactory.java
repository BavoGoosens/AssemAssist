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
        return workPost;
    }
	protected abstract ArrayList<AssemblyTask> createAssemblyTasks(WorkPost workPost);
    protected abstract String createName();
}
