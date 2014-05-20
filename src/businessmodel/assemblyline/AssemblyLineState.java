package businessmodel.assemblyline;

/**
 * The State of the AssemblyLine.
 * 
 * @author Team 10
 * 
 */
public interface AssemblyLineState {

	public abstract void markAssemblyLineAsBroken();
	public abstract void markAssemblyLineAsOperational();
	public abstract void markAssemblyLineAsMaintenance();
    public abstract boolean canPlaceOrder();
    public abstract void initialize();
	
}
