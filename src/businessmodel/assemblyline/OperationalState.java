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
	public void markAssemblyLineAsBroken() {
		this.assemblyLine.setState(this.assemblyLine.getBrokenState());
	}

	@Override
	public void markAssemblyLineAsOperational() {
		
	}

	@Override
	public void markAssemblyLineAsMaintenance() {
		this.assemblyLine.setState(this.assemblyLine.getMaintenanceState());	
	}

    @Override
    public String toString() {
        return "Operational";
    }
}
