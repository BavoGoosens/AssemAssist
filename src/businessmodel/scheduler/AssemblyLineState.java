package businessmodel.scheduler;

public interface AssemblyLineState {

	public abstract void AssemblyLineIsBroken();
	public abstract void AssemblyLineIsOperational();
	public abstract void AssemblyLineIsMaintenance();
	
}
