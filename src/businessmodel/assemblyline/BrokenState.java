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
        // NOP already in this state
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
    public boolean canAdvance() {
        return false;
    }

    @Override
    public void initialize() {
        // freeze the assembly line (is done via canAdvance)
        // and flush the orders in the queue for this line.
        this.assemblyLine.getAssemblyLineScheduler().flushAssemblyLineScheduler();
    }

    @Override
	public String toString() {
		return "Broken";
	}

}
