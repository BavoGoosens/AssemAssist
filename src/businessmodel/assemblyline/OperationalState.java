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
		// NOP
	}

	@Override
	public void markAssemblyLineAsMaintenance() {
		this.assemblyLine.setState(this.assemblyLine.getMaintenanceState());
		this.processOrdersOnAssemblyLine();
		DateTime date = this.assemblyLine.getAssemblyLineScheduler().getCurrentTime();
		date.plusHours(4);
		this.assemblyLine.getAssemblyLineScheduler().setCurrentTime(date);
	}

    //TODO
	private void processOrdersOnAssemblyLine() {

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
