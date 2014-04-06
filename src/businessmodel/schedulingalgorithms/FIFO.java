package businessmodel.schedulingalgorithms;

import businessmodel.scheduler.Scheduler;

public class FIFO extends SchedulingAlgorithm {

	private Scheduler scheduler;
	
	public FIFO(Scheduler scheduler){
		this.scheduler = scheduler;
	}

}
