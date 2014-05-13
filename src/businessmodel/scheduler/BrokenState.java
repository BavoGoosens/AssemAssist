package businessmodel.scheduler;

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
	public void AssemblyLineIsBroken() {
		// wait indefinitely
	}

	@Override
	public void AssemblyLineIsOperational() {
		this.assemblyLine.setState(this.assemblyLine.getOperationalState());
	}

	@Override
	public void AssemblyLineIsMaintenance() {
		//wait four hours
		
	}

}
