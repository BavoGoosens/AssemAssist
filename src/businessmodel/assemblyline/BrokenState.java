package businessmodel.assemblyline;

public class BrokenState implements AssemblyLineState {

	AssemblyLine assemblyLine;
	
	/**
	 * Constructor for broken state of the assembly line
	 * @param assemblyLine
	 */
	public BrokenState(AssemblyLine assemblyLine){

		if (assemblyLine == null) throw new IllegalStateException("Not a valid assembly line.");
		this.assemblyLine = assemblyLine;

	}

	@Override
	public void markAssemblyLineAsBroken() {
		// wait indefinitely
	}

	@Override
	public void markAssemblyLineAsOperational() {
		this.assemblyLine.setState(this.assemblyLine.getOperationalState());
	}

	@Override
	public void markAssemblyLineAsMaintenance() {
		this.assemblyLine.setState(this.assemblyLine.getMaintenanceState());
		//wait four hours
		
	}

    @Override
    public boolean canPlaceOrder() {
        return false;
    }

    @Override
    public String toString() {
        return "Broken";
    }
}
