package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

public class AssemblyLine {

	/**
	 * List of work posts at the assembly line.
	 */
	private ArrayList<WorkPost> workposts = new ArrayList<WorkPost>();

	/**
	 * This method constructs a new assembly line with an empty list of work posts.
	 */
	public AssemblyLine() {
		this.getWorkposts().add(new WorkPost("car body post"));
		this.getWorkposts().add(new WorkPost("drive train post"));
		this.getWorkposts().add(new WorkPost("accessories post"));
	}

	/**
	 * This method returns the list of work posts at the assembly line.
	 * @return	this.workposts
	 */
	public ArrayList<WorkPost> getWorkposts() {
		return this.workposts;
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
		for (WorkPost wp : this.getWorkposts())
			shift = wp.moveAlong(shift);
		return shift;
	}
	
	/**
	 * A method that returns the all the orders that are on the assembly line.
	 * 
	 * @return LinkedList<Order>
	 * 		   A list with all the orders that are on the assembly line.
	 */
	protected LinkedList<Order> getWorkPostOrders(){
		LinkedList<Order> orders = new LinkedList<Order>();
		for(WorkPost wp: this.getWorkposts()){
			orders.add(wp.getOrder());
		}
		return orders;
	}

}
