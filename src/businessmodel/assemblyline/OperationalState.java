package businessmodel.assemblyline;


/**
 * The Operational state of the AssemblyLine.
 * 
 * @author 	SWOP team 10
 *
 */
public class OperationalState implements AssemblyLineState {

	private AssemblyLine assemblyLine;


	/**
	 * Constructor for operational state of the assembly line
	 * @param assemblyLine
	 */
	public OperationalState(AssemblyLine assemblyLine){
		if (assemblyLine == null)
            throw new IllegalStateException("Not a valid assembly line.");
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
	}

    @Override
    public boolean canPlaceOrder() {
        return true;
    }

    @Override
    public void initialize() {
        // NOP
    }

    @Override
    public String toString() {
        return "Operational";
    }
}
