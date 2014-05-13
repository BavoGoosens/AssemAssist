package businessmodel.assemblyline;

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
	public void AssemblyLineIsBroken() {
		this.assemblyLine.setState(this.assemblyLine.getBrokenState());
		// wait indefinitely
	}

	@Override
	public void AssemblyLineIsOperational() {
		this.assemblyLine.setState(this.assemblyLine.getOperationalState());
	}

	@Override
	public void AssemblyLineIsMaintenance() {
		// wait four hours
		
	}

}
