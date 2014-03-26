package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class that represents an assembly line. It currently holds 3 work post.
 * 
 * @author 	SWOP team 10 2014
 *
 */
public class AssemblyLine {

	/**
	 * List of work posts at the assembly line.
	 */
	private ArrayList<WorkPost> workposts = new ArrayList<WorkPost>();

	/**
	 * A constructor for the class AssemblyLine.
	 */
	public AssemblyLine(ArrayList<WorkPost> workposts) throws IllegalArgumentException {
		this.setWorkPosts(workposts);
	}

	/**
	 * A method to set the work posts of this workpost to the given work posts.
	 * @param workposts
	 */
	private void setWorkPosts(ArrayList<WorkPost> workposts) throws IllegalArgumentException {
		if (workposts == null) throw new IllegalArgumentException("Bad list of work posts!");
		this.workposts = workposts;
	}

	/**
	 * This method returns the list of work posts at the assembly line.
	 * 
	 * @return	ArrayList<WorkPost>
	 * 			this.workposts
	 */
	public ArrayList<WorkPost> getWorkPosts() {
		return this.workposts;
	}

	/**
	 * This method checks whether the assembly line can move forward.
	 * 
	 * @return boolean
	 * 		   true is the assembly line can move forward. false otherwise.
	 */
	public boolean canAdvance() {
		for(WorkPost wp : this.getWorkPosts()){
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
	 * @param 	neworder 
	 * 		  	The Order that is added at the front of the line.
	 * 
	 * @return 	Order
	 * 		   	A finished order if the last WorkPost was working on an Order. 
	 */
	public Order advance(Order neworder) throws IllegalStateException {
		if (!this.canAdvance()) throw new IllegalStateException("Cannot advance assembly line!");
		// order can be 'null'
		Order temp1 = this.getWorkPosts().get(0).getOrder();
		Order temp2 = this.getWorkPosts().get(1).getOrder();
		Order finished = this.getWorkPosts().get(2).getOrder();
		
		this.getWorkPosts().get(0).setNewOrder(neworder);
		this.getWorkPosts().get(1).setNewOrder(temp1);
		this.getWorkPosts().get(2).setNewOrder(temp2);

		return finished;
	}
	
	/**
	 * A method that returns all the orders that are on the assembly line.
	 * 
	 * @return  LinkedList<Order>
	 * 		    A list with all the orders that are on the assembly line.
	 */
	protected LinkedList<Order> getWorkPostOrders(){
		LinkedList<Order> orders = new LinkedList<Order>();
		for(WorkPost wp: this.getWorkPosts())
			if (wp.getOrder() != null)
				orders.add(wp.getOrder());
		
		return orders;
	}
}