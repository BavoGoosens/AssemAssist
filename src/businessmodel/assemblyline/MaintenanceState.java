package businessmodel.assemblyline;

import businessmodel.observer.Observer;
import businessmodel.observer.Subject;

public class MaintenanceState implements AssemblyLineState {

	AssemblyLine assemblyLine;
	
	
	/**
	 * Constructor for maintenance state of the assembly line
	 * @param assemblyLine
	 */
	public MaintenanceState(AssemblyLine assemblyLine){
		if (assemblyLine == null) throw new IllegalStateException("Not a valid assembly line.");
		this.assemblyLine = assemblyLine;
	}
	
	@Override
	public void markAssemblyLineAsBroken() {
		this.assemblyLine.setState(this.assemblyLine.getBrokenState());
		// wait indefinitely
	}

	@Override
	public void markAssemblyLineAsOperational() {
		this.assemblyLine.setState(this.assemblyLine.getOperationalState());
	}

	@Override
	public void markAssemblyLineAsMaintenance() {
		// wait four hours
		
	}

    @Override
    public boolean canPlaceOrder() {
        return true;
    }

    @Override
    public String toString() {
        return "Maintenance";
    }

}
