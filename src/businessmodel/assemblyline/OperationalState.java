package businessmodel.assemblyline;

public class OperationalState implements AssemblyLineState {

	AssemblyLine assemblyLine;

	/**
	 * Constructor for operational state of the assembly line
	 * @param assemblyLine
	 */
	public OperationalState(AssemblyLine assemblyLine){

		if (assemblyLine == null) throw new IllegalStateException("Not a valid assembly line.");
		this.assemblyLine = assemblyLine;

	}

	@Override
	public void AssemblyLineIsBroken() {
		this.assemblyLine.setState(this.assemblyLine.getBrokenState());
	}

	@Override
	public void AssemblyLineIsOperational() {
		// TODO Auto-generated method stub
	}

	@Override
	public void AssemblyLineIsMaintenance() {
		this.assemblyLine.setState(this.assemblyLine.getMaintenanceState());	
	}

}
