package businessmodel.assemblyline;

public interface AssemblyLineState {

	public abstract void markAssemblyLineAsBroken();
	public abstract void markAssemblyLineAsOperational();
	public abstract void markAssemblyLineAsMaintenance();
    public abstract boolean canPlaceOrder();
	
}
