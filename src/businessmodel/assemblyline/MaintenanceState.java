package businessmodel.assemblyline;

import java.util.Iterator;

import businessmodel.observer.Observer;
import businessmodel.observer.Subject;

/**
 * The Maintenance state of the AssemblyLine.
 * 
 * @author 	SWOP team 10
 *
 */
public class MaintenanceState implements AssemblyLineState, Observer {

	private AssemblyLine assemblyLine;
    private boolean isReady = false;
    private boolean isActive = false;
	
	/**
	 * Constructor for maintenance state of the assembly line
	 * @param assemblyLine
	 */
	public MaintenanceState(AssemblyLine assemblyLine){
		if (assemblyLine == null) throw new IllegalStateException("Not a valid assembly line.");
		this.assemblyLine = assemblyLine;
        assemblyLine.subscribeObserver(this);
	}
	
	@Override
	public void markAssemblyLineAsBroken() {
		this.assemblyLine.setState(this.assemblyLine.getBrokenState());
		// wait indefinitely
	}

	@Override
	public void markAssemblyLineAsOperational() {
		this.assemblyLine.setState(this.assemblyLine.getOperationalState());
	}

	@Override
	public void markAssemblyLineAsMaintenance() {
		// wait four hours
		
	}

    @Override
    public boolean canPlaceOrder() {
            return this.isReady;
    }

    @Override
    public void initialize() {
        this.isActive = true;
        this.assemblyLine.getAssemblyLineScheduler().tempName(4);
        this.isReady = false;
        this.update(this.assemblyLine);
    }

    @Override
    public String toString() {
        return "Maintenance";
    }

    @Override
    public void update(Subject subject) {
        if (this.isActive){
            Iterator<WorkPost> posts = this.assemblyLine.getWorkPostsIterator();
            boolean ready = false;
            while (posts.hasNext()){
                WorkPost post = posts.next();
                ready = post.isCompleted();
            }
            if (ready){
                // the assembly line is empty
                // now shift 4 hours and transition to Operational state
                this.assemblyLine.getAssemblyLineScheduler().increaseCurrentTime(4);
                this.isActive = false;
                this.assemblyLine.setState(this.assemblyLine.getOperationalState());
            }
        }
        // in the other case just wait till the line is empty.
    }
}
