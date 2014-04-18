package businessmodel;

import java.util.ArrayList;


public abstract class WorkPostFactory {

	public WorkPost createWorkPost(){
		
		ArrayList<AssemblyTask> tempTasks = new ArrayList<AssemblyTask>();
		
		tempTasks.add(this.createBodyTask());
		tempTasks.add(this.createColorTask());
		tempTasks.add(this.createEngineTask());
		tempTasks.add(this.createGearboxTask());
		tempTasks.add(this.createSeatsTask());
		tempTasks.add(this.createAircoTask());
		tempTasks.add(this.createWheelsTask());
		tempTasks.add(this.createSpoilerTask());
		
		ArrayList<AssemblyTask> tasks = new ArrayList<AssemblyTask>();
		for (AssemblyTask assemblyTask: tempTasks)
			if (assemblyTask != null)
				tasks.add(assemblyTask);
		
		return new WorkPost(this.getName(), tasks);
		
	}

	protected abstract AssemblyTask createBodyTask();
	protected abstract AssemblyTask createColorTask();
	protected abstract AssemblyTask createEngineTask();
	protected abstract AssemblyTask createGearboxTask();
	protected abstract AssemblyTask createSeatsTask();
	protected abstract AssemblyTask createAircoTask();
	protected abstract AssemblyTask createWheelsTask();
	protected abstract AssemblyTask createSpoilerTask();
	protected abstract String getName();
	
}
