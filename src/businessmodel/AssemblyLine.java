package businessmodel;

import java.util.ArrayList;

public class AssemblyLine {
	
	
	private WorkPost carbodypost;
	
	private WorkPost drivetrainpost;
	
	private WorkPost accessoriespost;
	/**
	 * List of work posts at the assembly line.
	 */
	private ArrayList<WorkPost> workposts;
	
	/**
	 * This method constructs a new assembly line with an empty list of work posts.
	 */
	public AssemblyLine() {
		this.carbodypost = new WorkPost("car body post");
		this.drivetrainpost = new WorkPost("drive train post");
		this.accessoriespost = new WorkPost("accessories post");
	}
	
	/**
	 * This method constructs a new assembly line with the given list of work posts.
	 * @param	workposts
	 * 			The list of work posts at the assembly line.
	 */
	public AssemblyLine(ArrayList<WorkPost> workposts) {
		this.setWorkposts(workposts);
	}
	
	/**
	 * This method returns the list of work posts at the assembly line.
	 * @return	this.workposts
	 */
	public ArrayList<WorkPost> getWorkposts() {
		return this.workposts;
	}
	
	/**
	 * This method sets the work posts at the assembly line.
	 * @param 	workposts
	 * 			The list of work posts that needs to be set at the assembly line.
	 * @throws 	IllegalArgumentException
	 * 			workposts == null
	 */
	public void setWorkposts(ArrayList<WorkPost> workposts) throws IllegalArgumentException {
		if (workposts == null) throw new IllegalArgumentException();
		this.workposts = workposts;
	}
	
	/**
	 * This method adds a given work post to the assembly line.
	 * @param 	workpost
	 * 			The work posts that needs to be added at the assembly line.
	 * @throws 	IllegalArgumentException
	 * 			workpost == null
	 */
	public void addWorkpost(WorkPost workpost) throws IllegalArgumentException {
		if (workpost == null) throw new IllegalArgumentException();
		this.getWorkposts().add(workpost);
	}
	
	/**
	 * This method removes a given work post from the assembly line.
	 * @param 	workpost
	 * 			The work post that needs to be removed.
	 */
	public void removeWorkpost(WorkPost workpost) {
		this.getWorkposts().remove(workpost);
	}

	/**
	 * This method checks whether the assembly line can move forward.
	 * 
	 * @return boolean
	 * 		   true is the assembly line can move forward. false otherwise.
	 */
	public boolean canAdvance() {
		for(WorkPost wp : this.getWorkposts()){
			boolean ready = wp.isCompleted();
			if (ready == false)
				return false;
		}
		return true;
	}

	/**
	 * This method advances the Assembly Line. 
	 * It adds the supplied order to the first work post and moves the rest forward. 
	 * If the last WorkPost was working on an Order. That order is finished and is returned.
	 * 
	 * @param p 
	 * 		  The Order that is added at the front of the line.
	 * @return Order
	 * 		   A finished order if the last WorkPost was working on an Order. 
	 */
	public Order advance(Order p) {
		Order shift = p;
		for (WorkPost wp : this.getWorkposts()){
			shift = wp.moveAlong(shift);
		}
		return shift;
	}

}
