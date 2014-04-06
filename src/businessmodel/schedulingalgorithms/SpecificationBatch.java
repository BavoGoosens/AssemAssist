package businessmodel.schedulingalgorithms;

import businessmodel.scheduler.Scheduler;

public class SpecificationBatch extends SchedulingAlgorithm {
	
	private Scheduler scheduler;

	public SpecificationBatch(Scheduler scheduler){
		this.scheduler = scheduler;
	}
}

