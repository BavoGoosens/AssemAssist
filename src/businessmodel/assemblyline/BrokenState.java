package businessmodel.assemblyline;


/**
 * The Broken state of the AssemblyLine.
 * 
 * @author 	SWOP team 10
 *
 */
public class BrokenState implements AssemblyLineState {

	private AssemblyLine assemblyLine;

	/**
	 * Constructor for broken state of the assembly line
	 * @param assemblyLine
	 */
	public BrokenState(AssemblyLine assemblyLine){
		if (assemblyLine == null)
            throw new IllegalStateException("Not a valid assembly line.");
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
	}

	@Override
	public boolean canPlaceOrder() {
		return false;
	}

    @Override
    public void initialize() {
        // NOP
    }

    @Override
	public String toString() {
		return "Broken";
	}

}
