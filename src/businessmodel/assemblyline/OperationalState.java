package businessmodel.assemblyline;

import java.util.Iterator;

import org.joda.time.DateTime;

import businessmodel.exceptions.NoClearanceException;
import businessmodel.util.IteratorConverter;

public class OperationalState implements AssemblyLineState {

	private AssemblyLine assemblyLine;
	private boolean looping = true;


	/**
	 * Constructor for operational state of the assembly line
	 * @param assemblyLine
	 */
	public OperationalState(AssemblyLine assemblyLine){

		if (assemblyLine == null) throw new IllegalStateException("Not a valid assembly line.");
		this.assemblyLine = assemblyLine;

	}

	@Override
	public void markAssemblyLineAsBroken() {
		this.assemblyLine.setState(this.assemblyLine.getBrokenState());
	}

	@Override
	public void markAssemblyLineAsOperational() {
		
	}

	@Override
	public void markAssemblyLineAsMaintenance() {
		this.assemblyLine.setState(this.assemblyLine.getMaintenanceState());
		this.assemblyLine.setState(this.assemblyLine.getMaintenanceState());
		this.processOrdersOnAssemblyLine();
		DateTime date = this.assemblyLine.getAssemblyLineScheduler().getCurrentTime();
		date.plusHours(4);
		this.assemblyLine.getAssemblyLineScheduler().setCurrentTime(date);
	}
	
	private void processOrdersOnAssemblyLine() {
		IteratorConverter<WorkPost> converter = new IteratorConverter<>();
		looping = true;
		while (looping){
			try {
				CompleteWorkPost(this.assemblyLine, converter.convert(this.assemblyLine.getWorkPostsIterator()).size());
			} catch (NoClearanceException e) {}
		}		
	}

	private void CompleteWorkPost(AssemblyLine assem, int i) throws NoClearanceException{
		looping = false;
        for(int j = 0 ; j < i ; j++){
            IteratorConverter<WorkPost> converter = new IteratorConverter<>();
			WorkPost wp1 = converter.convert(assem.getWorkPostsIterator()).get(j);
			Iterator<AssemblyTask> iter2 = wp1.getPendingTasks();
			while (iter2.hasNext()){
				AssemblyTask task = iter2.next();
				task.completeAssemblytask(20);
                looping = true;
            }
		}
	}


    @Override
    public boolean canPlaceOrder() {
        return true;
    }

    @Override
    public String toString() {
        return "Operational";
    }
}
