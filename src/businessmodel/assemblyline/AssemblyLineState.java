package businessmodel.assemblyline;

/**
 * The State of the AssemblyLine.
 * 
 * @author Team 10
 * 
 */
public interface AssemblyLineState {

	/**
	 * Mark the AssemblyLine as Broken.
	 */
	public abstract void markAssemblyLineAsBroken();
	
	/**
	 * Mark the AssemblyLine as Operational.
	 */
	public abstract void markAssemblyLineAsOperational();
	
	/**
	 * Mark the AssemblyLine as Maintenance.
	 */
	public abstract void markAssemblyLineAsMaintenance();
	
	/**
	 * True if an order can be placed according to the state of the AssemblyLine.
	 * @return if the order can be placed
	 */
    public abstract boolean canPlaceOrder();
    
    /**
     * Init.
     */
    public abstract void initialize();
	
}
